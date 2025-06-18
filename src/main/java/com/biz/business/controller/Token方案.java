package com.biz.business.controller;

/**
 * TODO
 *
 * @author hzlei
 * @date 2025/6/19 01:10
 */
import com.google.common.util.concurrent.RateLimiter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.Map;
import java.util.concurrent.*;

public class MultiKeyTelegramSender {

    // token -> TokenLimiter
    private final Map<String, TokenLimiter> tokenLimiterMap = new ConcurrentHashMap<>();
    // key: token:chatId -> SenderQueue
    private final Map<String, SenderQueue> queueMap = new ConcurrentHashMap<>();

    // 发送任务
    public void sendFile(String token, String botUsername, long chatId, File file) {
        // 1. 获取或创建 TokenLimiter
        TokenLimiter tokenLimiter = tokenLimiterMap.computeIfAbsent(token, t -> new TokenLimiter(30.0)); // 30/s
        // 2. 获取或创建 SenderQueue
        String key = token + ":" + chatId;
        queueMap.computeIfAbsent(key, k -> {
            double rate;
            if (isGroup(chatId)) {
                rate = 30.0 / 60.0; // 群组：每分钟30次
            } else {
                rate = 1.0; // 非群组：每秒1次
            }
            SenderQueue sq = new SenderQueue(token, botUsername, chatId, rate, tokenLimiter);
            sq.start();
            return sq;
        }).addFile(file);
    }

    // 判断是否为群组
    private boolean isGroup(long chatId) {
        return chatId < 0;
    }

    // token级别限流器
    static class TokenLimiter {
        private final RateLimiter rateLimiter;
        public TokenLimiter(double permitsPerSecond) {
            this.rateLimiter = RateLimiter.create(permitsPerSecond);
        }
        public void acquire() {
            rateLimiter.acquire();
        }
    }

    // 队列+限流器+worker
    static class SenderQueue {
        private final String token;
        private final String botUsername;
        private final long chatId;
        private final LinkedBlockingQueue<File> fileQueue = new LinkedBlockingQueue<>();
        private final RateLimiter rateLimiter;
        private final ExecutorService worker = Executors.newSingleThreadExecutor();
        private final TokenLimiter tokenLimiter;

        public SenderQueue(String token, String botUsername, long chatId, double rate, TokenLimiter tokenLimiter) {
            this.token = token;
            this.botUsername = botUsername;
            this.chatId = chatId;
            this.rateLimiter = RateLimiter.create(rate);
            this.tokenLimiter = tokenLimiter;
        }

        public void addFile(File file) {
            fileQueue.offer(file);
        }

        public void start() {
            worker.submit(() -> {
                TelegramFileSender bot = new TelegramFileSender(token, botUsername, chatId);
                while (true) {
                    try {
                        File file = fileQueue.take();
                        rateLimiter.acquire(); // chatId级别限流
                        tokenLimiter.acquire(); // token全局限流
                        boolean success = sendFileWithRetry(bot, file, 3);
                        if (!success) {
                            System.err.println("最终失败: " + file.getName());
                            // TODO: 失败入库或日志
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }

        // 失败重试3次
        private boolean sendFileWithRetry(TelegramFileSender bot, File file, int maxRetries) {
            int retries = 0;
            while (retries < maxRetries) {
                try {
                    if (bot.sendFile(file)) {
                        return true;
                    }
                } catch (Exception e) {
                    System.out.println("发送异常: " + e.getMessage());
                }
                retries++;
                try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException ignored) {}
            }
            return false;
        }
    }

    // 单个bot发送逻辑
    static class TelegramFileSender extends TelegramLongPollingBot {
        private final String botToken;
        private final String botUsername;
        private final long chatId;

        public TelegramFileSender(String botToken, String botUsername, long chatId) {
            this.botToken = botToken;
            this.botUsername = botUsername;
            this.chatId = chatId;
        }

        @Override
        public String getBotUsername() { return botUsername; }
        @Override
        public String getBotToken() { return botToken; }
        @Override
        public void onUpdateReceived(org.telegram.telegrambots.meta.api.objects.Update update) {}

        public boolean sendFile(File file) {
            try {
                SendDocument sendDocument = new SendDocument();
                sendDocument.setChatId(chatId);
                sendDocument.setDocument(new InputFile(file));
                Message message = execute(sendDocument);
                System.out.println("发送成功: " + file.getName());
                return true;
            } catch (TelegramApiException e) {
                System.out.println("发送失败: " + file.getName() + "，错误: " + e.getMessage());
                return false;
            }
        }
    }

    // 示例入口
    public static void main(String[] args) {
        MultiKeyTelegramSender sender = new MultiKeyTelegramSender();

        // 群组
        sender.sendFile("token1", "bot1", -1001234567890L, new File("file1.txt"));
        // 非群组
        sender.sendFile("token1", "bot1", 123456789L, new File("file2.txt"));
        // 另一个token
        sender.sendFile("token2", "bot2", -1009876543210L, new File("file3.txt"));

        // 阻塞主线程
        try { Thread.currentThread().join(); } catch (InterruptedException ignored) {}
    }
}