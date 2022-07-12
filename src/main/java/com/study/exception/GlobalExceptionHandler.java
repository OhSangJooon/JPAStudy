package com.study.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/* API 처리용 예외처리 어노테이션 선언
*  페이지 예외처리
*  스프링은 예외 처리를 위해 @ControllerAdvice와 @ExceptionHandler 등의 기능을 지원.
    @ControllerAdvice는 컨트롤러 전역에서 발생할 수 있는 예외를 잡아 Throw,
    @ExceptionHandler는 특정 클래스에서 발생할 수 있는 예외를 잡아 Throw
    일반적으로 @ExceptionHandler는 @ControllerAdvice가 선언된 클래스에 포함된 메서드에 선언.
    * @RestControllerAdvice 는 @ControllerAdvice에 @ResponseBody가 적용된 형태
    * */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(final RuntimeException e) {
        log.error("handleRuntimeException : {}", e.getMessage());
        return e.getMessage();
    }

}