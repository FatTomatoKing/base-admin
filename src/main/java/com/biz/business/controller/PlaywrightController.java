package com.biz.business.controller;


import com.biz.mvc.authentication.annotation.Permit;
import com.biz.mvc.vo.ResponseResult;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mock")
@RequiredArgsConstructor
public class PlaywrightController {

    private final BrowserContext browserContext;

    @GetMapping("/youtube")
    @Permit(required = false)
    public ResponseResult<List<Map<String, String>>> mockYoutube(){
        List<Map<String, String>> resultList;
        Page page = browserContext.newPage();
        try {
            page.navigate("https://www.youtube.com/");

            page.fill("input[name='search_query']", "李子柒");

            page.press("input[name='search_query']", "Enter");

            page.waitForSelector("#contents ytd-video-renderer");

            resultList = new ArrayList<>();
            // 提取搜索结果
            for (int i = 1; i <= 10; i++) {
                String selector = String.format("#contents ytd-video-renderer:nth-child(%d)", i);

                if (!page.isVisible(selector, new Page.IsVisibleOptions().setTimeout(2000))) {
                    break;
                }

                Map<String, String> videoInfo = new HashMap<>();

                // 获取标题
                String title = page.textContent(selector + " #video-title");
                // 获取链接
                String link = page.getAttribute(selector + " #video-title", "href");


                videoInfo.put("title", title != null ? title.trim() : "");
                videoInfo.put("link", link != null ? "https://www.youtube.com" + link : "");

                resultList.add(videoInfo);
            }
        } finally {
            page.close();
        }

        return ResponseResult.ofSuccess(resultList);
    };


}

