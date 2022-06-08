package com.chengdu.longblog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengdu.longblog.entity.User;
import com.chengdu.longblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserService userService;

    /* 登录到首页 */
    @GetMapping
    public String index(){
        return "/admin/login";
    }

    /* 登录功能 */
    @RequestMapping("/login")
    public String login(User user, HttpSession session, RedirectAttributes redirectAttributes){
        User user1 = userService.getOne(new QueryWrapper<User>(user));
        System.out.println(user1+"===========");
        if(!StringUtils.isEmpty(user1)) {
            session.setAttribute("user",user1);
            return "/admin/index";
        }
        redirectAttributes.addAttribute("message","用户名或密码错误");
        return "redirect: ";
    }

    /* 注销用户 */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect: ";
    }





}
