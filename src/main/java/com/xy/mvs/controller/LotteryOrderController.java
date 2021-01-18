package com.xy.mvs.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 陈璇
 * @Date 2021/1/18 15:18
 * @Version 1.0
 */
@RestController
@RequestMapping("app/lotteryOrder")
@Validated
@Api(tags = "抽奖订单接口", produces = "application/json")
public class LotteryOrderController {
}
