package com.smbms.controller;

import com.smbms.exception.UserException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//全局异处理器
public class ExceptionController {

    @ExceptionHandler
    public String handleException(Exception e, Model model){
        System.err.println("捕获异常"+e.getMessage());
        model.addAttribute("msg","编译异常："+e.getMessage());
        return "error";
    }
    //处理运行时异常
    @ExceptionHandler(RuntimeException.class)//参数表示处理指定类型的异常
    public String handleRuntimeException(Exception e,Model model){
        System.err.println("捕获异常"+e.getMessage());
        model.addAttribute("msg","运行时异常："+e.getMessage());
        return "error";
    }
    @ExceptionHandler(UserException.class)//处理用户异常
    public String HandlerUserException(Exception e,Model model){
        System.err.println("捕获异常"+e.getMessage());
        model.addAttribute("msg","用户异常："+e.getMessage());
        return "error";
    }

}
