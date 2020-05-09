package com.smbms.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;

import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.service.RoleService;
import com.smbms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    /**
     * 查看用户详情
     * @param uid
     * @param model
     * @return
     * @throws Exception
     */
   @RequestMapping("/userlook")
    public String lookUser(String uid, Model model)throws Exception{
        long userid = Long.parseLong(uid);
        System.out.println(userid);
        User user = userService.findUserById(userid);
        if(user==null){
            System.out.println(user.toString());
        }
        System.out.println(user);
        model.addAttribute("user",user);
        return "userview";
    }

    /**
     * 增加用户
     * @param user
     * @param model
     * @return
     * @throws Exception
     */

    @PostMapping("/adduser.do")
    public String addUser(User user, Model model)throws Exception{
        int i = userService.insertUser(user);
        System.out.println("i"+"-----------------"+i);
        if (i<=0){
            return "error";
        }
        return "redirect:/userlist.do";


    }

    /**
     * 增加用户验证
     *
     */
    @RequestMapping("/finduser.do")
    @ResponseBody
    public Map<String, String> finUser(String userCode){
        System.out.println("userCode="+userCode);
        Map<String, String> map = new HashMap<>();

        String usercode="";
        if(StringUtils.isNullOrEmpty(userCode)){
            map.put("userCode","exist");
        }else {
            User user= userService.findUserCode(userCode);
            if(user!=null){
                map.put("userCode","exist");
            }else {
                map.put("userCode","");
            }
        }
        return map;
    }


    /**
     * 修改用户信息
     */
    @PostMapping("/uploduser.do")
    public String uplodeUserByid(User user,String uid,Model model)throws Exception{
        Long l=null;
        if (user!=null&&uid!=null){
            l = Long.parseLong(uid);
            user.setId(l);
        }
        int i = userService.uplodUserById(user);
        System.out.println("i="+i);
        return "redirect:/userlist.do";
    }
    /**
     * 删除用户
     *
     */
    @RequestMapping("/deleteuser.do")
    @ResponseBody
    public Map<String, String> removeUserById(Long uid)throws Exception{
        User user = userService.findUserById(uid);
        int i = userService.deleteUserByid(uid);
        Map<String, String> map = new HashMap<String,String>();
        String delResult="";
        if(user == null){
            delResult="notexist";
        }else if (i < 0){
            delResult="false";
        }else{
            delResult = "true";
        }
        map.put("delResult",delResult);
      //  return JSONArray.toJSONString(map);
        return map;

    }
    /**
     * 综合查询
     */
    @RequestMapping("/userlist.do")
    public String selectUserListBy(String queryname,Long queryUserRole,String pageIndex,Model model,HttpSession session)throws Exception{
        System.out.println("queryUserRole="+queryUserRole);
        Integer pageNum =1;
        if(pageIndex!=null){
            pageNum = Integer.parseInt(pageIndex);
        }
        List<Role> roleList = roleService.selectRoleList();
        PageInfo<User> pageInfo= userService.selectUserListByYe(pageNum,8,queryname,queryUserRole);
        model.addAttribute("pageInfo",pageInfo);
        session.setAttribute("roleList",roleList);
        model.addAttribute("queryUserName",queryname);
        model.addAttribute("queryUserRole",queryUserRole);
        return "userlist";
    }

}
