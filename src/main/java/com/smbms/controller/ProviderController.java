package com.smbms.controller;

import com.smbms.pojo.Bill;
import com.smbms.pojo.Provider;
import com.smbms.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    //查询用户列表
    @RequestMapping("/proList.do")
    public String selectProlist(String queryProName,String queryProCode,Model model){
        List<Provider> providerList = providerService.selectProListBy(queryProName,queryProCode);
        model.addAttribute("providerList",providerList);
        return "providerlist";

    }

    @RequestMapping("/lookProList.do")
    public String selectProByid(String proid, Model model){
        Long l = Long.parseLong(proid);
        Provider provider = providerService.selectProListById(l);
        model.addAttribute("provider",provider);
        return "providerview";
    }
    //查询该供应商的信息返回前端  跳转页面
    @RequestMapping("/selectPro.do")
    public String selectProByid(Long proid,Model model){
        Provider provider = providerService.selectProListById(proid);
        model.addAttribute("provider",provider);
        return "providermodify";
    }

    //修改供应商信息
    @RequestMapping("/updatePro.do")
    public String updateProById(Provider provider){
        System.out.println("provider="+provider);
        int i = providerService.updateProById(provider);
        System.out.println("i="+i);
        return "redirect:proList.do";
    }

    //跳转至增加供应商信息页面
    @RequestMapping("/jumpPro.do")
    public String JumpPro(){
        return "provideradd";
    }
    @RequestMapping("/addPro.do")
    public String insertPro(Provider provider){
        provider.setCreationDate(new Date());
        int i = providerService.insertProBy(provider);
        return "redirect:proList.do";

    }
    //deleteajax    删除用户用的ajax
    @RequestMapping("delprovider.do")
    @ResponseBody
    public Map<String,String> delAjax(Long proid){
        System.out.println("proid="+proid);
        String delResult="";
        List<Bill> bill = providerService.selectProBillListById(proid);
        System.out.println("bill="+bill.size());

        if(proid==null){
            delResult="notexist";
        }else if(bill!=null){
            delResult="true";
        }else {
            delResult="false";
        }
        Map<String,String> map = new HashMap<>();
        map.put("delResult",delResult);
        return map;
    }

}
