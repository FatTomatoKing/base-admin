package com.biz.business.controller;

//import com.hot.biz.api.UserServiceFeignClient;
//import com.hot.biz.config.FeignConfig;
//import com.hot.biz.responses.UserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

//    @Resource
//    private  FeignConfig feignConfig;
//
//
//    public void test(){
//        UserServiceFeignClient userServiceFeignClient = feignConfig.userServiceFeignClient();
//        UserResponse userResponse = userServiceFeignClient.get(1);
//    }
    

}
