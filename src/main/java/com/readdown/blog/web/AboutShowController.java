package com.readdown.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author feng
 * @Date 2017/11/6
 * @Time 21:32
 */
@Controller
public class AboutShowController {


    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
