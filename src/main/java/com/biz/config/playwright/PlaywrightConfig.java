package com.biz.config.playwright;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class PlaywrightConfig {

    private Playwright playwright;
    private Browser browser;

    @Bean
    public Playwright playwright() {
        this.playwright = Playwright.create();
        return playwright;
    }

    @Bean
    public Browser browser(Playwright playwright) {
        // 重新排序参数，把扩展参数放在前面
        List<String> args = new ArrayList<>();
        args.add("--no-gpu");
        args.add("--no-sandbox");
        args.add("--disable-dev-shm-usage");
        args.add("--remote-allow-origins=*");

        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(true)        // 非无头模式，可以看到浏览器界面
                .setChannel("chrome")      // 使用已安装的 Chrome
                .setSlowMo(50)             // 放慢操作，更容易看清操作过程
                .setArgs(args)
                .setDevtools(true)
        );
        return browser;
    }


    @Bean
    public BrowserContext browserContext(Browser browser) {
        // 使用 RandomStringUtils 生成随机用户代理字符串
        String randomUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36 " +
                RandomStringUtils.randomAlphabetic(10);

        return browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setUserAgent(randomUserAgent)
                .setLocale("en-US")
                .setIgnoreHTTPSErrors(true)); // 忽略 HTTPS 错误
    }

    @PreDestroy
    public void closePlaywright() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}

