package com.readdown.blog.web;

import com.readdown.blog.service.BlogService;
import com.readdown.blog.service.TagService;
import com.readdown.blog.service.TypeService;
import com.readdown.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by feng on 2017/11/4.
 */
@Controller
public class IndexController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 10, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTag(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlog(8));
        return "index";
    }


    @PostMapping("/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String query , Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blogs/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }


    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlog(3));
        return "_fragments::newblogList";
    }



}
