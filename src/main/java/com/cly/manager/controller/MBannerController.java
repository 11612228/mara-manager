package com.cly.manager.controller;

import com.cly.manager.bean.BannerBean;
import com.cly.manager.bean.UserInfoBean;
import com.cly.manager.service.BannerService;
import com.cly.manager.service.BannerServiceImpl;
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
public class MBannerController {
    BannerService bannerService = new BannerServiceImpl();
    UserInfoService userInfoService = new UserInfoServiceImpl();
    List<BannerBean> bannerBeanList;
    int pagesize = 20;
    double fpagesize = 20.0;
    int pageNumber;
    int[] pageList;

    @GetMapping("/addBanner")
    public String modifyBanner(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        UserInfoBean userInfo = null;
        model.addAttribute("userInfoBean",userInfoBean);
        return "addbanner";
    }

    @GetMapping("/modifyBanner")
    public String modifyBanner(@RequestParam(name="bid") int bid, Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        BannerBean bannerBean = null;
        try {
            bannerBean = bannerService.getBannerBean(bid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("bannerBean",bannerBean);
        return "bannerinfo";
    }

    @GetMapping("/deleteBanner")
    public String deleteBanner(@RequestParam(name="bid") int bid, Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        bannerService.deleteBannerBean(bid);
        return "redirect:/bannerPage?page=1";
    }

    @GetMapping(value = "/bannerPage")
    public String getBannerPage(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model, HttpServletRequest request) {
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        if(page!=1){
            boolean flag = true;
            HttpSession session = request.getSession();
            try {
                bannerBeanList = (List<BannerBean>) session.getAttribute("bannerBeanList");
            }catch (Exception e){
                System.out.println(e.toString());
                flag = false;
            }
            if(flag && bannerBeanList!=null){
                if(bannerBeanList.size()>(page-1)*pagesize){
                    int pageNumber = (int)Math.ceil(bannerBeanList.size()/fpagesize);
                    int[] pageList = new int[pageNumber];
                    for(int i =0;i<pageNumber;i++){
                        pageList[i] = i+1;
                    }
                    model.addAttribute("userInfoBean", userInfoBean);
                    model.addAttribute("bannerBeanList", bannerBeanList.subList((page-1)*pagesize,Math.min(page*7,bannerBeanList.size())));
                    model.addAttribute("page",page);
                    model.addAttribute("pageList",pageList);
                    return "bannerlist";
                }
            }
        }
        try {
            bannerBeanList = bannerService.getBannerBeanList();
            HttpSession session = request.getSession();
            session.setAttribute("bannerBeanList",bannerBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageNumber = (int)Math.ceil(bannerBeanList.size()/fpagesize);
        int[] pageList = new int[pageNumber];
        for(int i =0;i<pageNumber;i++){
            pageList[i] = i+1;
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("bannerBeanList", bannerBeanList.subList(0,Math.min(pagesize,bannerBeanList.size())));
        model.addAttribute("page",page);
        model.addAttribute("pageList",pageList);
        return "bannerlist";
    }

    @PostMapping("/updateBanner")
    public String updateBanner(@RequestParam(name="bid") int bid,
                                    @RequestParam(name="file") MultipartFile file,
                                    Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        BannerBean bannerBean = null;
        UserInfoBean userInfo = null;
        boolean flag = false;
        try {
            bannerBean = bannerService.getBannerBean(bid);
            if(!imgSrc.equals("default.png"))
                bannerBean.setImgSrc(imgSrc);
            flag = bannerService.updateBannerBean(bannerBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }

    @PostMapping("/newBanner")
    public String newBanner(
            @RequestParam(name="file") MultipartFile file,
            Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        BannerBean bannerBean = new BannerBean();
        bannerBean.setImgSrc(imgSrc);
        bannerService.addBannerBean(bannerBean);
        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }
}
