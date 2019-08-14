package com.cly.manager.controller;

import com.cly.manager.bean.PublicationBean;
import com.cly.manager.bean.UserInfoBean;
import com.cly.manager.service.PublicationService;
import com.cly.manager.service.PublicationServiceImpl;
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
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class MPublicationController {
    UserInfoService userInfoService = new UserInfoServiceImpl();
    PublicationService publicationService = new PublicationServiceImpl();
    List<PublicationBean> publicationBeanList = null;
    int pagesize = 20;
    double fpagesize = 20.0;
    int pageNumber;
    int[] pageList;

    @GetMapping("/addPublication")
    public String modifyPublication(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        UserInfoBean userInfo = null;
        model.addAttribute("userInfoBean",userInfoBean);
        return "addpublication";
    }

    @GetMapping("/modifyPublication")
    public String modifyPublication(@RequestParam(name="pid") int pid, Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        PublicationBean publicationBean = null;
        UserInfoBean userInfo = null;
        try {
            publicationBean = publicationService.getPublication(pid);
            userInfo = userInfoService.getUserInfo(publicationBean.getUid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("publicationBean",publicationBean);
        model.addAttribute("userInfo",userInfo);
        return "publicationinfo";
    }

    @GetMapping("/deletePublication")
    public String deletePublication(@RequestParam(name="pid") int pid, Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        publicationService.deletePublication(pid);
        List<PublicationBean> publicationBeanList = null;
        try {
            publicationBeanList = publicationService.getPublicationList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/publicationPage?page=1&amp;year=0";
    }
    @PostMapping("/updatePublication")
    public String updatePublication(@RequestParam(name="pid") int pid,
                                    @RequestParam(name="content") String content,
                                    @RequestParam(name="pdfUrl") String pdfUrl,
                                    @RequestParam(name="file") MultipartFile file,
                                    @RequestParam(name="authorName") String authorName,
                                    @RequestParam(name="year") int year,
                                    Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        PublicationBean publicationBean = null;
        UserInfoBean userInfo = null;
        try {
            userInfo = userInfoService.getUserInfo(authorName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean flag = false;
        try {
            publicationBean = publicationService.getPublication(pid);
            publicationBean.setContent(content);
            publicationBean.setPdfUrl(pdfUrl);
            publicationBean.setYear(year);
            if(!imgSrc.equals("default.png"))
                publicationBean.setImgSrc(imgSrc);
            publicationBean.setUid(userInfo.getUid());
            flag = publicationService.updatePublication(publicationBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }

    @PostMapping("/newPublication")
    public String newPublication(
            @RequestParam(name="content") String content,
            @RequestParam(name="pdfUrl") String pdfUrl,
            @RequestParam(name="file") MultipartFile file,
            @RequestParam(name="authorName") String authorName,
            @RequestParam(name="year") int year,
            Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        UserInfoBean userInfo = null;
        try {
            userInfo = userInfoService.getUserInfo(authorName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PublicationBean publicationBean = new PublicationBean();
        publicationBean.setContent(content);
        publicationBean.setPdfUrl(pdfUrl);
        publicationBean.setYear(year);
        publicationBean.setImgSrc(imgSrc);
        publicationBean.setUid(userInfo.getUid());
        publicationService.addPublication(publicationBean);

        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }

    @GetMapping(value = "/publicationPage")
    public String getPublicationPage(@RequestParam(name = "page", required = false, defaultValue = "1") int page, @RequestParam(name = "year", required = false, defaultValue = "0") int year, Model model, HttpServletRequest request) {
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        if(page!=1){
            boolean flag = true;
            HttpSession session = request.getSession();
            try {
                publicationBeanList = (List<PublicationBean>) session.getAttribute("publicationBeanList");
            }catch (Exception e){
                System.out.println(e.toString());
                flag = false;
            }
            if(flag && publicationBeanList!=null){
                if(publicationBeanList.size()>(page-1)*pagesize){
                    int pageNumber = (int)Math.ceil(publicationBeanList.size()/fpagesize);
                    int[] pageList = new int[pageNumber];
                    for(int i =0;i<pageNumber;i++){
                        pageList[i] = i+1;
                    }
                    model.addAttribute("publicationBeanList", publicationBeanList.subList((page-1)*pagesize,Math.min(page*7,publicationBeanList.size())));
                    model.addAttribute("page",page);
                    model.addAttribute("pageList",pageList);
                    model.addAttribute("year",year);
                    return "publicationlist";
                }
            }
        }
        try {
            if(year == 0) {
                publicationBeanList = publicationService.getPublicationList();
            }else{
                publicationBeanList = publicationService.getPublicationList(year);
            }
            HttpSession session = request.getSession();
            session.setAttribute("publicationBeanList",publicationBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageNumber = (int)Math.ceil(publicationBeanList.size()/fpagesize);
        int[] pageList = new int[pageNumber];
        for(int i =0;i<pageNumber;i++){
            pageList[i] = i+1;
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("publicationBeanList", publicationBeanList.subList(0,Math.min(pagesize,publicationBeanList.size())));
        model.addAttribute("page",1);
        model.addAttribute("pageList",pageList);
        model.addAttribute("year",year);
        return "publicationlist";
    }
}
