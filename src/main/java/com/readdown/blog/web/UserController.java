package com.readdown.blog.web;

import com.readdown.blog.oauth.service.OAuthServiceDeractor;
import com.readdown.blog.oauth.service.OAuthServices;
import com.readdown.blog.oauth.service.OAuthUserService;
import com.readdown.blog.po.OAuthUser;
import com.readdown.blog.po.User;
import com.readdown.blog.service.UserService;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    OAuthServices oAuthServices;

    @Autowired
    OAuthUserService oAuthUserService;

    @GetMapping("/users/login")
    public String login(){
//        model.addAttribute("oAuthServices",oAuthServices.getAllOAuthServices());
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
            return "redirect:/users/register";
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
            return "redirect:/users/login";
        }
    }


    @GetMapping("/users/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("normalUser");
        return "redirect:/";
    }



//    @GetMapping("/oauth/{type}/callback")
//    public String callback(@RequestParam String code,@PathVariable String type,RedirectAttributes attributes,Model model){
//        OAuthServiceDeractor oAuthService = oAuthServices.getOAuthService(type);
//        Token accessToken = oAuthService.getAccessToken(null,new Verifier(code));
//        OAuthUser oAuthInfo = oAuthService.getOAuthUser(accessToken);
//
//        OAuthUser oAuthUser = oAuthUserService.getOAuthUser(oAuthInfo.getoAuthType(),oAuthInfo.getoAuthid());
//
//        if (oAuthUser==null){
//            model.addAttribute("oAuthInfo",oAuthInfo);
//            return "users/register";
//        }
//        attributes.addAttribute("oAuthInfo",oAuthInfo);
//        return "redirect:/";
//    }
//
//
//    @PostMapping("/oauth/register")
//    public String  oauthRegister(Model model, User user,
//                                 @RequestParam(value = "oAuthType", required = false, defaultValue = "") String oAuthType,
//                                 @RequestParam(value = "oAuthId", required = true, defaultValue = "") String oAuthId,
//                                 RedirectAttributes attributes){
//        OAuthUser oAuthInfo = new OAuthUser();
//        oAuthInfo.setoAuthid(oAuthId);
//        oAuthInfo.setoAuthType(oAuthType);
//
//       if ( userService.isExistUser(user.getUsername())){
//
//        }
//
//    }


}
