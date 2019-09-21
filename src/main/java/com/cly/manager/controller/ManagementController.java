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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ManagementController {
    UserInfoService userInfoService = new UserInfoServiceImpl();
    UserInfoBean userInfoBean;

    @GetMapping(value = "/mIndex")
    public String getMIndex(@RequestParam(value = "id") int id, Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        switch (id){
            case 0:
                model.addAttribute("userInfoBean",userInfoBean);
                return "management";
            case 1:
                return "redirect:/userInfoPage?page=1";
            case 12:
                return "redirect:/adduserInfo";
            case 2:
                return "redirect:/picturePage?page=1&amp;year=0";
            case 22:
                return "redirect:/addPicture";
            case 3:
                return "redirect:/publicationPage?page=1&amp;year=0";
            case 32:
                return "redirect:/addPublication";
            case 4:
                return "redirect:/bannerPage?page=1";
            case 42:
                return "redirect:/addBanner";
            case 5:
                return "redirect:/newsPage?page=1&amp;year=0";
            case 52:
                return "redirect:/addNews";
            case 6:
                return "redirect:/resourcePage?page=1";
            case 62:
                return "redirect:/addResource";
            case 404:
                request.getSession().removeAttribute("userInfoBean");
                return "index";
            default:
                model.addAttribute("userInfoBean",userInfoBean);
                return "management";
        }
    }




//    @PostMapping(value = "/upload")
//    public String fileUpload(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "uid") int uid, Model model) {
//        if (file.isEmpty()) {
//            System.out.println("文件为空空");
//        }
//        String fileName = file.getOriginalFilename();  // 文件名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//        String filePath = "//Users//cly//Desktop//linjh//"; // 上传后的路径
//        fileName = UUID.randomUUID() + suffixName; // 新文件名
//        File dest = new File(filePath + fileName);
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String imgSrc = "/"+fileName;
//        model.addAttribute("imgSrc",fileName);
//        return "userinfo";
//    }

}
