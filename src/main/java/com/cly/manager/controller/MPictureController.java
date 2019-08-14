package com.cly.manager.controller;

import com.cly.manager.bean.PictureBean;
import com.cly.manager.bean.UserInfoBean;
import com.cly.manager.service.PictureService;
import com.cly.manager.service.PictureServiceImpl;
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
public class MPictureController {
    UserInfoService userInfoService = new UserInfoServiceImpl();
    PictureService pictureService = new PictureServiceImpl();
    List<PictureBean> pictureBeanList = null;
    int pagesize = 20;
    double fpagesize = 20.0;
    int pageNumber;
    int[] pageList;

    @GetMapping("/addPicture")
    public String modifyPicture(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        UserInfoBean userInfo = null;
        model.addAttribute("userInfoBean",userInfoBean);
        return "addpicture";
    }

    @GetMapping("/modifyPicture")
    public String modifyPicture(@RequestParam(name="pid") int pid, Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        PictureBean pictureBean = null;
        try {
            pictureBean = pictureService.getPicture(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("pictureBean",pictureBean);
        return "pictureinfo";
    }

    @GetMapping("/deletePicture")
    public String deletePicture(@RequestParam(name="pid") int pid, Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        pictureService.deletePicture(pid);
        List<PictureBean> pictureBeanList = null;
        try {
            pictureBeanList = pictureService.getPictureBeanList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/picturePage?page=1&amp;year=0";
    }
    @PostMapping("/updatePicture")
    public String updatePicture(@RequestParam(name="pid") int pid,
                                    @RequestParam(name="pName") String pName,
                                    @RequestParam(name="file") MultipartFile file,
                                    @RequestParam(name="year") int year,
                                    Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        PictureBean pictureBean = null;
        boolean flag = false;
        try {
            pictureBean = pictureService.getPicture(pid);
            pictureBean.setImgSrc(imgSrc);
            pictureBean.setYear(year);
            pictureBean.setpName(pName);
            if(!imgSrc.equals("default.png"))
                pictureBean.setImgSrc(imgSrc);
            flag = pictureService.updatePicture(pictureBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }

    @PostMapping("/newPicture")
    public String newPicture(
            @RequestParam(name="file") MultipartFile file,
            @RequestParam(name="pName") String pName,
            @RequestParam(name="year") int year,
            Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        PictureBean pictureBean = new PictureBean();
        pictureBean.setpName(pName);
        pictureBean.setYear(year);
        pictureBean.setImgSrc(imgSrc);
        pictureService.addPicture(pictureBean);

        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }

    @GetMapping(value = "/picturePage")
    public String getPicturePage(@RequestParam(name = "page", required = false, defaultValue = "1") int page, @RequestParam(name = "year", required = false, defaultValue = "0") int year, Model model, HttpServletRequest request) {
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        if(page!=1){
            boolean flag = true;
            HttpSession session = request.getSession();
            try {
                pictureBeanList = (List<PictureBean>) session.getAttribute("pictureBeanList");
            }catch (Exception e){
                System.out.println(e.toString());
                flag = false;
            }
            if(flag && pictureBeanList!=null){
                if(pictureBeanList.size()>(page-1)*pagesize){
                    int pageNumber = (int)Math.ceil(pictureBeanList.size()/fpagesize);
                    int[] pageList = new int[pageNumber];
                    for(int i =0;i<pageNumber;i++){
                        pageList[i] = i+1;
                    }
                    model.addAttribute("pictureBeanList", pictureBeanList.subList((page-1)*pagesize,Math.min(page*7,pictureBeanList.size())));
                    model.addAttribute("page",page);
                    model.addAttribute("pageList",pageList);
                    model.addAttribute("year",year);
                    return "picturelist";
                }
            }
        }
        try {
            if(year == 0) {
                pictureBeanList = pictureService.getPictureBeanList();
            }else{
                pictureBeanList = pictureService.getPictureBeanList(year);
            }
            HttpSession session = request.getSession();
            session.setAttribute("pictureBeanList",pictureBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageNumber = (int)Math.ceil(pictureBeanList.size()/fpagesize);
        int[] pageList = new int[pageNumber];
        for(int i =0;i<pageNumber;i++){
            pageList[i] = i+1;
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("pictureBeanList", pictureBeanList.subList(0,Math.min(pagesize,pictureBeanList.size())));
        model.addAttribute("page",1);
        model.addAttribute("pageList",pageList);
        model.addAttribute("year",year);
        return "picturelist";
    }
}
