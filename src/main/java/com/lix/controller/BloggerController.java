package com.lix.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lix.pojo.Blogger;
import com.lix.utils.CryptographyUtil;

@RequestMapping(value = "/blogger")
@Controller
public class BloggerController {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hello12";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Blogger blogger, ModelMap map) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUserName(), CryptographyUtil.md5(
                blogger.getPassword(), "Myblog"));
        try {
            subject.login(token); // 登录验证
            return "redirect:/admin/main.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            map.put("blogger", blogger);
            map.put("errorInfo", "用户名或密码错误！");
            return "login";
        }
    }

}
