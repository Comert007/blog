package com.readdown.blog.web;

import com.readdown.blog.po.User;
import com.readdown.blog.service.UserService;
import com.readdown.blog.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author feng
 * @Date 2017/11/12
 * @Time 15:52
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;




    @GetMapping("/users/login")
    public String login(){
        return "login";
    }

    @GetMapping("/users/register")
    public String register(Model model){
        model.addAttribute("normalUser",new User());
        return "register";
    }

    @PostMapping("/users/register")
    public String postRegister(User user, RedirectAttributes attributes, Model model){
        User u;
        if (userService.isExistUser(user.getUsername())){
            attributes.addFlashAttribute("message", "用户名已存在");
            model.addAttribute("normalUser",user);
            return "redirect:/register";
        }else {
            u = userService.saveUser(user);
            return "register_success";
        }
    }

    @GetMapping("/users/register/success")
    public String registerSuccess(){
        return "register_success";
    }

    @PostMapping("/users/login")
    public String postLogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("normalUser", user);
            return "redirect:/";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/login";
        }
    }


    @GetMapping("/users/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("normalUser");
        return "redirect:/";
    }

}
