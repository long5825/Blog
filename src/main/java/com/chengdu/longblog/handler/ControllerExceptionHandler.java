package com.chengdu.longblog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e){
        logger.error("Request URL : {} Exception : {}", request.getRequestURL(),e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("Url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.addObject("error/error");
        return mv;
    }

}
