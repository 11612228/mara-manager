package com.cly.manager.controller;

import com.cly.manager.bean.UserInfoBean;
import com.cly.manager.service.UserInfoService;
import com.cly.manager.service.UserInfoServiceImpl;
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
import java.util.UUID;

@Controller
public class ManagementController {
    UserInfoService userInfoService = new UserInfoServiceImpl();
    UserInfoBean userInfoBean;

    @GetMapping(value = "/mIndex")
    public String getMIndex(@RequestParam(value = "id") int id, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfoBean userInfoBean = null;
        try {
            userInfoBean = (UserInfoBean) session.getAttribute("userInfoBean");
        }catch (Exception e){
            return "index";
        }
        switch (id){
            case 0:
                model.addAttribute("userInfoBean",userInfoBean);
                return "management";
            case 1:
                model.addAttribute("userInfoBean",userInfoBean);
                return "adduserinfo";
            case 2:
                model.addAttribute("userInfoBean",userInfoBean);
                return "addalumni";
            case 3:
                model.addAttribute("userInfoBean",userInfoBean);
                return "addpicture";
            case 4:
                model.addAttribute("userInfoBean",userInfoBean);
                return "addpublication";
            case 5:
                model.addAttribute("userInfoBean",userInfoBean);
                return "addbanner";
            case 6:
                model.addAttribute("userInfoBean",userInfoBean);
                return "addnews";
            default:
                return "/";
        }
    }




    @PostMapping(value = "/upload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "uid") int uid, Model model) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "//Users//cly//Desktop//WEB-INF//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgSrc = "/"+fileName;
        model.addAttribute("imgSrc",fileName);
        return "management";
    }

}
