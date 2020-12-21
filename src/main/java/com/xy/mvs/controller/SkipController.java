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
     * 跳转到产品类型管理页面
     */
    @RequestMapping("/stockmanage")
    public String stockmanage(){
        return "stockmanage";
    }

    /**
     * 跳转到仓库设置页面
     */
    @RequestMapping("/warehouselist")
    public String warehouselist(){
        return "warehouselist";
    }

    /**
     * 跳转到订单管理页面
     */
    @RequestMapping("/workorder")
    public String workorder(){
        return "workorder";
    }

    /**
     * 跳转到抽奖设置页面
     */
    @RequestMapping("/lotterysettings")
    public String lotterysettings(){
        return "lotterysettings";
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

    /**
     * 跳转到工单设置页面
     */
    @RequestMapping("/workorderview")
    public String workorderview(){
        return "workorderview";
    }

    /**
     * 跳转到产品管理页面
     */
    @RequestMapping("/serviceperson")
    public String serviceperson(){
        return "serviceperson";
    }

    /**
     * 跳转到客户管理页面
     */
    @RequestMapping("/customer")
    public String customer(){
        return "customer";
    }

    /**
     * 跳转到用户管理页面
     */
    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    /**
     * 跳转到抽奖订单页面
     */
    @RequestMapping("/lottery")
    public String lottery(){
        return "lottery";
    }

}




















