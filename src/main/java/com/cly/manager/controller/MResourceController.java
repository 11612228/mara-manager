package com.cly.manager.controller;

import com.cly.manager.bean.ResourceBean;
import com.cly.manager.bean.UserInfoBean;
import com.cly.manager.service.ResourceService;
import com.cly.manager.service.ResourceServiceImpl;
import com.cly.manager.service.UserInfoService;
import com.cly.manager.service.UserInfoServiceImpl;
import com.cly.manager.util.ImgUtil;
import com.cly.manager.util.Interceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MResourceController {
    ResourceService resourceService = new ResourceServiceImpl();
    UserInfoService userInfoService = new UserInfoServiceImpl();
    List<ResourceBean> resourceBeanList;
    int pagesize = 20;
    double fpagesize = 20.0;
    int pageNumber;
    int[] pageList;

    @GetMapping("/addResource")
    public String modifyBanner(Model model, HttpServletRequest request) {
        if (!Interceptor.getInterceptor(request)) {
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        UserInfoBean userInfo = null;
        model.addAttribute("userInfoBean", userInfoBean);
        return "addresource";
    }

    @GetMapping("/modifyResource")
    public String modifyBanner(@RequestParam(name = "resid") int resid, Model model, HttpServletRequest request) {
        if (!Interceptor.getInterceptor(request)) {
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        ResourceBean resourceBean = null;
        try {
            resourceBean = resourceService.getResourceBean(resid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean", userInfoBean);
        model.addAttribute("resourceBean", resourceBean);
        return "resourceinfo";
    }

    @GetMapping("/deleteResource")
    public String deleteBanner(@RequestParam(name = "resid") int resid, Model model, HttpServletRequest request) {
        if (!Interceptor.getInterceptor(request)) {
            return "index";
        }
        resourceService.deleteResourceBean(resid);
        return "redirect:/resourcePage?page=1";
    }

    @GetMapping(value = "/resourcePage")
    public String getBannerPage(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model, HttpServletRequest request) {
        if (!Interceptor.getInterceptor(request)) {
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        if (page != 1) {
            boolean flag = true;
            HttpSession session = request.getSession();
            try {
                resourceBeanList = (List<ResourceBean>) session.getAttribute("resourceBeanList");
            } catch (Exception e) {
                System.out.println(e.toString());
                flag = false;
            }
            if (flag && resourceBeanList != null) {
                if (resourceBeanList.size() > (page - 1) * pagesize) {
                    int pageNumber = (int) Math.ceil(resourceBeanList.size() / fpagesize);
                    int[] pageList = new int[pageNumber];
                    for (int i = 0; i < pageNumber; i++) {
                        pageList[i] = i + 1;
                    }
                    model.addAttribute("userInfoBean", userInfoBean);
                    model.addAttribute("resourceBeanList", resourceBeanList.subList((page - 1) * pagesize, Math.min(page * 7, resourceBeanList.size())));
                    model.addAttribute("page", page);
                    model.addAttribute("pageList", pageList);
                    return "resourcelist";
                }
            }
        }
        try {
            resourceBeanList = resourceService.getResourceBeanList();
            HttpSession session = request.getSession();
            session.setAttribute("resourceBeanList", resourceBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageNumber = (int) Math.ceil(resourceBeanList.size() / fpagesize);
        int[] pageList = new int[pageNumber];
        for (int i = 0; i < pageNumber; i++) {
            pageList[i] = i + 1;
        }
        model.addAttribute("userInfoBean", userInfoBean);
        model.addAttribute("resourceBeanList", resourceBeanList.subList(0, Math.min(pagesize, resourceBeanList.size())));
        model.addAttribute("page", page);
        model.addAttribute("pageList", pageList);
        return "resourcelist";
    }

    @PostMapping("/updateResource")
    public String updateBanner(@RequestParam(name = "resid") int resid,
                               @RequestParam(name = "file") MultipartFile file,
                               @RequestParam(name = "pName") String pName,
                               @RequestParam(name = "pNameC") String pNameC,
                               Model model, HttpServletRequest request) {
        if (!Interceptor.getInterceptor(request)) {
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        ResourceBean resourceBean = null;
        UserInfoBean userInfo = null;
        boolean flag = false;
        try {
            resourceBean = resourceService.getResourceBean(resid);
            if (!imgSrc.equals("default.png"))
                resourceBean.setImgSrc(imgSrc);
            resourceBean.setpName(pName);
            resourceBean.setpNameC(pNameC);
            flag = resourceService.updateResourceBean(resourceBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean", userInfoBean);
        return "management";
    }

    @PostMapping("/newResource")
    public String newBanner(
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(name = "pName") String pName,
            @RequestParam(name = "pNameC") String pNameC,
            Model model, HttpServletRequest request) {
        if (!Interceptor.getInterceptor(request)) {
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        ResourceBean resourceBean = new ResourceBean();
        resourceBean.setImgSrc(imgSrc);
        resourceBean.setpName(pName);
        resourceBean.setpNameC(pNameC);
        resourceService.addResourceBean(resourceBean);
        model.addAttribute("userInfoBean", userInfoBean);
        return "management";
    }
}
