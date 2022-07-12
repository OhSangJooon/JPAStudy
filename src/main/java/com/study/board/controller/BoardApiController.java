package com.study.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardApiController {
    /*
    * 페이지를 처리하는 Controller와 API를 처리하는 Controller를 따로 구성
    * API를 처리하는 RestController 전역에서 공통된 예외 처리 적용
    * */
    @GetMapping("/test")
    public String test() {
        throw new RuntimeException("Holy! Exception...");
    }

}