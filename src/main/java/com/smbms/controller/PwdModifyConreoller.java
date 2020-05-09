package com.smbms.controller;

import com.smbms.pojo.User;
import com.smbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PwdModifyConreoller {

    @Autowired
    private UserService userService;


    @RequestMapping("/pwdmodify.do")
    public String pwdMdify(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        System.out.println("user="+user);
        return "pwdmodify";
    }

    /**
     * 验证用户密码
     * @param oldpassword
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/passwordmodif.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> passWordMdoify(String oldpassword, HttpSession session)throws Exception{
        Map<String,String> map=new HashMap<String,String>();
        System.out.println("oldpassword="+oldpassword);
        //获取session域对象
        User userlogin = (User) session.getAttribute("loginUser");
        //查询出该用户
        String result="";
        if(userlogin==null){
           result="sessionerror";
       }else if (!oldpassword.equals(userlogin.getUserPassword())){
           result= "false";
       }else {
           result="true";
       }
       map.put("result",result);
        return map;
    }


    @RequestMapping("/changePassword.do")
    public String changePassWord(String newpassword,HttpSession session)throws Exception{
        User user = (User) session.getAttribute("loginUser");
        user.setUserPassword(newpassword);
        int i = userService.selectUserPassword(user);
        session.removeAttribute("loginUser");
        return "redirect:/login.jsp";
    }











}
