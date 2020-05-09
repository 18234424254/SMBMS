package com.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.smbms.pojo.Bill;
import com.smbms.pojo.Provider;
import com.smbms.service.BillService;
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
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private ProviderService providerService;
    //综合查血用户列表
    @RequestMapping("/billList.do")
    public String selectBillList(Integer queryIsPayment, Long queryProviderId, String queryProductName, Model model, HttpSession session){
        System.out.println("queryIsPayment--------------"+queryIsPayment);
        System.out.println("queryProviderId----------"+queryProviderId);
        System.out.println("queryProductName------------"+queryProductName);
        List<Bill> billList =  billService.findBillList(queryIsPayment,queryProductName,queryProviderId);
        List<Provider> providerList = providerService.selectBillProListBy();
        model.addAttribute("providerList",providerList);
        model.addAttribute("billList",billList);
        //session.setAttribute("providerList",providerList);
        //session.setAttribute("billList",billList);
        return "billlist";
    }

    //查询bill列表详情
    @RequestMapping("/lookBill.do")
    public String seeLookBillByid(Long billid,Model model){
        Bill bill = billService.selectBillById(billid);
        model.addAttribute("bill",bill);
        return "billview";
    }
    //查询并跳转页面
    @RequestMapping("/uplodbill.do")
    public String updateBillListByid(Long billid,Model model){
        Bill bill = billService.selectBillById(billid);
        model.addAttribute("bill",bill);
        return "billmodify";
    }
    //供应商请求ajax
    @RequestMapping(value = "/billajax.do",produces={"text/html;charset=UTF-8"})
    @ResponseBody
    public String selectAjax(){
        List<Provider> providerList = providerService.selectBillProListBy();
        String s = JSON.toJSONString(providerList);
        return s;

    }
    //修改用户
    @RequestMapping("/updateBill.do")
    public String updateBillById(Bill bill,Model model){
        System.out.println("bill="+bill);
      int i =   billService.updateBillById(bill);
        return "redirect:billList.do";
    }
    //跳转至增加订单页面
    @RequestMapping("/jumpbill.do")
    public String jumpBill(){
        return"billadd";
    }

    //增加订单
    @RequestMapping("addbill.do")
    public String addBillBy(Bill bill){
        bill.setCreationDate(new Date());
        System.out.println("bill="+bill);
        int i = billService.addBillBy(bill);
        return "redirect:billList.do";
    }

    //删除订单信息ajax
    @RequestMapping("/delbill.do")
    @ResponseBody
    public Map<String,String> removeBill(Long billid){

        Map<String,String> map= new HashMap<>();
        System.out.println("billid="+billid);
        String delResult="";
        if (billid==null){
            delResult="notexist";
        }
        Bill bill = billService.selectBillById(billid);
        if(bill!=null){
            delResult="true";
            billService.deletebill(billid);
        }else {
            delResult="false";
        }
        map.put("delResult",delResult);
        return map;
    }

}
