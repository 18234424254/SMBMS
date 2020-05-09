package com.smbms.controller;

import com.smbms.pojo.User;
import com.smbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public String doLogin(String userCode, String userPassword,Model model, HttpSession httpSession)throws Exception{
        User user = userService.login(userCode, userPassword);
        if(user==null){//登录失败
            model.addAttribute("error","用户名密码错误!");
            return "forward:login.jsp";
        }
            httpSession.setAttribute("loginUser",user);
            return "frame";
    }

    @GetMapping("/logout.do")
    public String doLogout(HttpSession session){
      session.removeAttribute("loginUser");
      return "redirect:/login.jsp";

    }

}
