package com.example.boardapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


// 컨트롤러에서 던진 예외를 처리하는 곳
@ControllerAdvice // 컨트롤러 예외처리 빈으로 등록한다
public class GlobalExceptionHandler {

    // 기록기(logger): 서버에서 일지(log)를 작성하는 사람
    private static final Logger logger =  LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    //잘못된 인자 예외(IllegalArgumentException) 을 처리하는 핸들러
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgument(IllegalArgumentException e, Model model) {


        //에러 사항을 콘솔에 출력합니다 
        logger.error("잘못된 요청 발생: {}", e);

        //클라이언트에게 알라기 위한 용도
        model.addAttribute("status",400);
        model.addAttribute("message", e.getMessage());

        //에러 페이지 전송  예) 페이지를 찾을 수 없습니다 
        return "error";
    }
  //나머지 예외를 처리하는 핸들러 
   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public String handleAllException(Exception e, Model model) {

    //개발자 확인용: 에러 사항을 콘솔에 출력한다 
    logger.error("서버 내부 오류 발생 : {} " , e);

    //클라이언트 확인용 
    model.addAttribute("status", 500);
    model.addAttribute("message", "서버에 문제가 발생했습니다");

    //에러페이지 전송 예) 서버에 문제가 생겼습니다 나중에 다시 시도하세요
    return "error";
   }
}
