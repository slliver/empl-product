package com.zhongwang.empl.product.webapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 用一句话具体描述类的功能
 * @author: slliver
 * @date: 2019/9/3 9:43d
 * @version: 1.0
 */
@Controller
public class IndexController {

    @GetMapping({"/", ""})
    public String welcome(Model model) {
        System.out.println("this is webapi index page...");
        return "redirect:/index";
    }

    @GetMapping({"/index"})
    public String index(Model model) {
        return "index";
    }
}
