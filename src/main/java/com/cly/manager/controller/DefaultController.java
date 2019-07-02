package com.cly.manager.controller;

import com.cly.manager.bean.UserInfoBean;
import com.cly.manager.util.Interceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @GetMapping("/")
    public String login(Model model, HttpServletRequest request) {
        if (Interceptor.getInterceptor(request)) {
            UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
            model.addAttribute("userInfoBean", userInfoBean);
            return "management";
        } else {

            return "index";

        }
    }
}
