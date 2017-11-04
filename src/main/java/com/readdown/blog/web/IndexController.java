package com.readdown.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by feng on 2017/11/4.
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

    @GetMapping("/types")
    public String types(){
        return "types";
    }

    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }


    @GetMapping("/archives")
    public String archives(){
        return "archives";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
