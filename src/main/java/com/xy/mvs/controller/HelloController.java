package com.xy.mvs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cx")
public class HelloController {

    @RequestMapping("/say")
    public String say(){
        return "Hello World";
    }

}
