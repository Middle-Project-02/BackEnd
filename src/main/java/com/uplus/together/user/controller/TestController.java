package com.uplus.together.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test API", description = "Swagger 연결 확인용 테스트 API")
public class TestController {
    @GetMapping("/hello")
    @Operation(summary = "헬로 엔드포인트", description = "Swagger가 잘 작동하는지 확인하는 테스트용 API입니다.")
    public String hello() {
        return "Hello, Swagger!";
    }
}
