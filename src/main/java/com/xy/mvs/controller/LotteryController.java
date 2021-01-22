package com.xy.mvs.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 陈璇
 * @Date 2021/1/22 16:52
 * @Version 1.0
 */
@RestController
@RequestMapping("app/lottery")
@Validated
@Api(tags = "抽奖接口", produces = "application/json")
public class LotteryController {
}
