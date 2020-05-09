package com.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.service.RoleService;
import com.smbms.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;


    @GetMapping("/role")
    public String select1(HttpSession session){
        session.getAttribute("getrolelist");
        List<Role> roleList = roleService.selectRoleList();
        session.setAttribute("json",roleList);
        return "useradd";
    }


    @RequestMapping(value="/rolelist.do",produces={"text/html;charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public String select1(){
        List<Role> roles = roleService.selectRoleList();
        String role = JSON.toJSONString(roles);
        return role;
    }

    @RequestMapping("/roleuserlist.do")
    public String selectUser(String uid,Model model)throws Exception{
        Long l = null;
        if(uid!=null){
            l = Long.parseLong(uid);
        }
        User user = userService.findUserById(l);
        model.addAttribute("user",user);
        return "usermodify";
    }
}
