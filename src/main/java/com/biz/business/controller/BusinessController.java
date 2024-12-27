package com.biz.business.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suyh
 * @since 2024-10-22
 */
@Tag(name = "业务示例接口类")
@RestController
@RequestMapping("/business")
@RequiredArgsConstructor
@Validated
@Slf4j
public class BusinessController {
}
