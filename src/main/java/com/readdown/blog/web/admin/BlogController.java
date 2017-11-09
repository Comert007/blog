package com.readdown.blog.web.admin;

import com.readdown.blog.po.Blog;
import com.readdown.blog.po.User;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @author feng
 * @Date 2017/11/4
 * @Time 20:13
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";

    private static final String LIST = "admin/blogs";

    private static final String REDIRECT_LIST = "redirect:/admin/blogs";


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model,HttpSession session) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("currentUser",session.getAttribute("user"));
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return LIST;
    }



    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model,HttpSession session) {
        model.addAttribute("currentUser",session.getAttribute("user"));
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }


    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }


    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return INPUT;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes,Model model,HttpSession session) {
        blogService.deleteBlog(id);
        model.addAttribute("currentUser",session.getAttribute("user"));
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;

    }

    //TODO 可以增加后端校验
    //新增和修改
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes,Model model, HttpSession session) {

        blog.setUser((User) session.getAttribute("user"));

        blog.setType(typeService.getType(blog.getType().getId()));
        String newIds = tagService.checkAndAdd(blog.getTagIds());
        blog.setTags(tagService.listTag(newIds));

        Blog b ;
        if (blog.getId() ==null){
            b = blogService.saveBlog(blog);
        }else {
            b = blogService.updateBlog(blog.getId(),blog);
        }
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        model.addAttribute("currentUser",session.getAttribute("user"));
        return REDIRECT_LIST;
    }


}
