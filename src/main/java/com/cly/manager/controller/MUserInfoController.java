package com.cly.manager.controller;

import com.cly.manager.bean.UserInfoBean;
import com.cly.manager.service.UserInfoService;
import com.cly.manager.service.UserInfoServiceImpl;
import com.cly.manager.util.Interceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MUserInfoController {
    UserInfoService userInfoService = new UserInfoServiceImpl();
    @GetMapping("/userInfoBeanList")
    public String getUserInfoBeanList(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
           return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        List<UserInfoBean> userInfoBeanList = null;
        try {
            userInfoBeanList = userInfoService.getUserInfoBeanList();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("userInfoBean",userInfoBean);
            return "management";
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("userInfoBeanList",userInfoBeanList);
        return "userinfo";
    }

    @GetMapping("/deleteuserInfo")
    public String deleteUserInfoBean(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        List<UserInfoBean> userInfoBeanList = null;
        return "index";
    }

    @GetMapping("/modifyuserInfo")
    public String modifyUserInfoBean(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        List<UserInfoBean> userInfoBeanList = null;
        return "index";
    }

    @GetMapping("/adduserInfo")
    public String addUserInfoBean(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        List<UserInfoBean> userInfoBeanList = null;
        return "index";
    }

    @PostMapping("newUser")
    public String newUserInfoBean(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        List<UserInfoBean> userInfoBeanList = null;
        return "index";
    }

    @PostMapping("updateUser")
    public String updateUserInfoBean(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        List<UserInfoBean> userInfoBeanList = null;
        return "index";
    }
}
