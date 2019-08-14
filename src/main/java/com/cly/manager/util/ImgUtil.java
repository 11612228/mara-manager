package com.cly.manager.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImgUtil {
   public static String getImgName(MultipartFile file){
       String imgSrc = "default.png";
       System.out.println("update");
       String fileName = file.getOriginalFilename();  // 文件名
       if(!(fileName.equals("")||fileName==null)) {
           String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
           String filePath = "/home/webdep/web/upload/"; // 上传后的路径
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
           imgSrc = fileName;
       }
       return imgSrc;
   }
}
