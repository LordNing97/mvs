package com.xy.mvs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SkipController {

    /**
     * 跳转到登录页面
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

//    public String index(Integer id,Model model){
//        model.addAttribute("id",id);
//        return "index";
//    }

    /**
     * 跳转到用户管理页面
     */
    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    /**
     * 跳转到商城订单管理页面
     */
    @RequestMapping("/mallOrder")
    public String mallOrder(){
        return "mall_order";
    }

    /**
     * 跳转到抽奖订单管理页面
     */
    @RequestMapping("/lotteryOrder")
    public String lotteryOrder(){
        return "lottery_order";
    }

    /**
     * 跳转到商城产品类型管理页面
     */
    @RequestMapping("/mailProductType")
    public String mailProductType(){
        return "mail_product_type";
    }

    /**
     * 跳转到抽奖产品类型管理页面
     */
    @RequestMapping("/lotteryProductType")
    public String lotteryProductType(){
        return "lottery_product_type";
    }

    /**
     * 跳转到商城产品管理页面
     */
    @RequestMapping("/mailProduct")
    public String mailProduct(){
        return "mail_product";
    }

    /**
     * 跳转到抽奖产品管理页面
     */
    @RequestMapping("/lotteryProduct")
    public String lotteryProduct(){
        return "lottery_product";
    }

    /**
     * 跳转到客户管理页面
     */
    @RequestMapping("/customer")
    public String customer(){
        return "customer";
    }

    /**
     * 跳转到抽奖设置页面
     */
    @RequestMapping("/lotterySettings")
    public String lotterySettings(){
        return "lottery_settings";
    }

    /**
     * 跳转到未开奖页面
     */
    @RequestMapping("/noprize")
    public String noprize(){
        return "noprize";
    }

    /**
     * 跳转到进行中页面
     */
    @RequestMapping("/isongoing")
    public String isongoing(){
        return "isongoing";
    }

    /**
     * 跳转到已结束页面
     */
    @RequestMapping("/finished")
    public String finished(){
        return "finished";
    }

}




















