package com.cly.manager.util;

import com.cly.manager.bean.UserInfoBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Interceptor {
   public static boolean getInterceptor(HttpServletRequest request) {
       HttpSession session = request.getSession();
       UserInfoBean userInfoBean = null;
       try {
           userInfoBean = (UserInfoBean) session.getAttribute("userInfoBean");
           if(userInfoBean==null){
               return false;
           }
       } catch (Exception e) {
           return false;
       }
       return true;
   }
}
