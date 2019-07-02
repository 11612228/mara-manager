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
                model.addAttribute("userInfoBean",userInfoBean);
                return "redirect:/userInfoBeanList";
            case 12:
                model.addAttribute("userInfoBean",userInfoBean);
                return "adduserinfo";
            case 2:
                model.addAttribute("userInfoBean",userInfoBean);
                return "picture";
            case 22:
                model.addAttribute("userInfoBean",userInfoBean);
                return "addpicture";
            case 3:
                return "publication";
            case 32:
                model.addAttribute("userInfoBean",userInfoBean);
                return "addpublication";
            case 4:
                model.addAttribute("userInfoBean",userInfoBean);
                return "banner";
            case 42:
                model.addAttribute("userInfoBean",userInfoBean);
                return "addbanner";
            case 5:
                model.addAttribute("userInfoBean",userInfoBean);
                return "news";
            case 52:
                model.addAttribute("userInfoBean",userInfoBean);
                return "addnews";
            case 404:
                request.getSession().removeAttribute("userInfoBean");
                return "index";
//            case 6:
//                model.addAttribute("userInfoBean",userInfoBean);
//                return "addalumni";
            default:
                model.addAttribute("userInfoBean",userInfoBean);
                return "management";
        }
    }




    @PostMapping(value = "/upload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "uid") int uid, Model model) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "//Users//cly//Desktop//linjh//"; // 上传后的路径
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
