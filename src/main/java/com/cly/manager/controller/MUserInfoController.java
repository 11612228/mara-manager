package com.cly.manager.controller;

import com.cly.manager.bean.*;
import com.cly.manager.service.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MUserInfoController {
    UserInfoService userInfoService = new UserInfoServiceImpl();
    EducationService educationService = new EducationServiceImpl();
    AwardService awardService = new AwardServiceImpl();
    InterestService interestService = new InterestServiceImpl();
    ExperienceService experienceService = new ExperienceServiceImpl();
    List<UserInfoBean> userInfoBeanList = null;
    int pagesize = 20;
    double fpagesize = 20.0;
    int pageNumber;
    int[] pageList;
    @GetMapping("/userInfoPage")
    public String getUserInfoPage(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model, HttpServletRequest request) {
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        if(page!=1){
            boolean flag = true;
            HttpSession session = request.getSession();
            try {
                userInfoBeanList = (List<UserInfoBean>) session.getAttribute("userInfoBeanList");
            }catch (Exception e){
                System.out.println(e.toString());
                flag = false;
            }
            if(flag && userInfoBeanList!=null){
                if(userInfoBeanList.size()>(page-1)*pagesize){
                    int pageNumber = (int)Math.ceil(userInfoBeanList.size()/fpagesize);
                    int[] pageList = new int[pageNumber];
                    for(int i =0;i<pageNumber;i++){
                        pageList[i] = i+1;
                    }
                    model.addAttribute("userInfoBeanList", userInfoBeanList.subList((page-1)*pagesize,Math.min(page*7,userInfoBeanList.size())));
                    model.addAttribute("page",page);
                    model.addAttribute("pageList",pageList);
                    return "userinfolist";
                }
            }
        }
        try {
            userInfoBeanList = userInfoService.getUserInfoBeanList();
            HttpSession session = request.getSession();
            session.setAttribute("userInfoBeanList",userInfoBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageNumber = (int)Math.ceil(userInfoBeanList.size()/fpagesize);
        int[] pageList = new int[pageNumber];
        for(int i =0;i<pageNumber;i++){
            pageList[i] = i+1;
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("userInfoBeanList", userInfoBeanList.subList(0,Math.min(pagesize,userInfoBeanList.size())));
        model.addAttribute("page",1);
        model.addAttribute("pageList",pageList);
        return "userinfolist";
    }

    @GetMapping("/deleteuserInfo")
    public String deleteUserInfoBean(@RequestParam(name="uid") int uid,Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        userInfoService.deleteUserInfo(uid);
        List<UserInfoBean> userInfoBeanList = null;
        try {
            userInfoBeanList = userInfoService.getUserInfoBeanList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("userInfoBeanList",userInfoBeanList);
        return "userinfolist";
    }

    @GetMapping("/modifyuserInfo")
    public String modifyUserInfoBean(@RequestParam(name="uid") int uid,Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        UserInfoBean userInfo = null;
        List<ExperienceBean> experienceBeanList = null;
        List<EducationBean> educationBeanList = null;
        List<InterestBean> interestBeanList = null;
        List<AwardBean> awardBeanList = null;
        StringBuilder experience = new StringBuilder();
        StringBuilder education = new StringBuilder();
        StringBuilder interest= new StringBuilder();
        StringBuilder award = new StringBuilder();
        try {
            userInfo = userInfoService.getUserInfo(uid);
            experienceBeanList = experienceService.getExperienceBeanList(uid);
            educationBeanList = educationService.getEducationList(uid);
            awardBeanList = awardService.getAwardBeanList(uid);
            interestBeanList = interestService.getInterestBeanList(uid);
            for (ExperienceBean experienceBean:experienceBeanList) {
                experience.append( experienceBean.getExperience()+";");
            }
            for (EducationBean educationBean:educationBeanList) {
                education.append(educationBean.getEducation()+";");
            }
            for (AwardBean awardBean:awardBeanList) {
                award.append(awardBean.getAward()+";");
            }
            for (InterestBean interestBean:interestBeanList) {
                interest.append(interestBean.getInterest()+";");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("experience",experience.toString());
        model.addAttribute("education",education.toString());
        model.addAttribute("award",award.toString());
        model.addAttribute("interest",interest.toString());

        return "userinfo";
    }

    @GetMapping("/adduserInfo")
    public String addUserInfoBean(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        model.addAttribute("userInfoBean",userInfoBean);
        return "adduserinfo";
    }


    @PostMapping("/newUser")
    public String newUserInfoBean(
                                  @RequestParam(name="userName") String userName,
                                  @RequestParam(name="password") String password,
                                  @RequestParam(name="file") MultipartFile file,
                                  @RequestParam(name="realName") String realName,
                                  @RequestParam(name="address") String address,
                                  @RequestParam(name="email") String email,
                                  @RequestParam(name="office") String office,
                                  @RequestParam(name="telephone") String telephone,
                                  @RequestParam(name="post") String post,
                                  @RequestParam(name="brief") String brief,
                                  @RequestParam(name="education") String education,
                                  @RequestParam(name="experience") String experience,
                                  @RequestParam(name="award") String award,
                                  @RequestParam(name="interest") String interest,
            Model model, HttpServletRequest request) {
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        System.out.println(userName+" "+password);
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        UserInfoBean userInfo = new UserInfoBean();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        userInfo.setImgSrc(imgSrc);
        userInfo.setAddress(address);
        userInfo.setBrief(brief);
        userInfo.setRealName(realName);
        userInfo.setOffice(office);
        userInfo.setPost(post);
        userInfo.setTelephone(telephone);
        userInfo.setEmail(email);
        String[] educationArray = education.split(";");
        String[] experienceArray = experience.split(";");
        String[] awardArray = award.split(";");
        String[] interestArray = interest.split(";");
        boolean m = userInfoService.addUserInfo(userInfo);
        if(m) {
            System.out.println("yes");
        }else{
            System.out.println("no");
        }
        int uid = -1;
        try {
            uid = userInfoService.login(userName,password).getUid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(String edu: educationArray){
            if(!edu.equals("")) {
                EducationBean educationBean = new EducationBean();
                educationBean.setUid(uid);
                educationBean.setEducation(edu);
                educationService.addEducationBean(educationBean);
            }
        }

        for(String exp: experienceArray){
            if(!exp.equals("")) {
                ExperienceBean experienceBean = new ExperienceBean();
                experienceBean.setUid(uid);
                experienceBean.setExperience(exp);
                experienceService.addExperienceBean(experienceBean);
            }
        }

        for(String awd: awardArray){
            if(!awd.equals("")) {
                AwardBean awardBean = new AwardBean();
                awardBean.setUid(uid);
                awardBean.setAward(awd);
                awardService.addAwardBean(awardBean);
            }
        }

        for(String inte: interestArray){
            if(!inte.equals("")) {
                InterestBean interestBean = new InterestBean();
                interestBean.setUid(uid);
                interestBean.setInterest(inte);
                interestService.addInterestBean(interestBean);
            }
        }
        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }

    @PostMapping("/updateUser")
    public String updateUserInfoBean(
                                     @RequestParam(name="file") MultipartFile file,
                                     @RequestParam(name="uid") String suid,
                                     @RequestParam(name="realName") String realName,
                                     @RequestParam(name="address") String address,
                                     @RequestParam(name="email") String email,
                                     @RequestParam(name="office") String office,
                                     @RequestParam(name="telephone") String telephone,
                                     @RequestParam(name="post") String post,
                                     @RequestParam(name="brief") String brief,
                                     @RequestParam(name="education") String education,
                                     @RequestParam(name="experience") String experience,
                                     @RequestParam(name="award") String award,
                                     @RequestParam(name="interest") String interest,
                                     Model model, HttpServletRequest request)
    {
        int uid = Integer.parseInt(suid);
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        UserInfoBean userInfo = new UserInfoBean();
        userInfo.setUid(uid);
        userInfo.setImgSrc(imgSrc);
        userInfo.setAddress(address);
        userInfo.setBrief(brief);
        userInfo.setRealName(realName);
        userInfo.setOffice(office);
        userInfo.setPost(post);
        userInfo.setTelephone(telephone);
        userInfo.setEmail(email);
        String[] educationArray = education.split(";");
        String[] experienceArray = experience.split(";");
        String[] awardArray = award.split(";");
        String[] interestArray = interest.split(";");

        educationService.deleteEducationBean(uid);
        awardService.deleteAwardBean(uid);
        interestService.deleteInterestBean(uid);
        experienceService.deleteExperienceBean(uid);

        for(String edu: educationArray){
            if(!edu.equals("")) {
                EducationBean educationBean = new EducationBean();
                educationBean.setUid(uid);
                educationBean.setEducation(edu);
                educationService.addEducationBean(educationBean);
            }
        }

        for(String exp: experienceArray){
        if(!exp.equals("")) {
            ExperienceBean experienceBean = new ExperienceBean();
            experienceBean.setUid(uid);
            experienceBean.setExperience(exp);
            experienceService.addExperienceBean(experienceBean);
            }
        }

        for(String awd: awardArray){
            if(!awd.equals("")) {
                AwardBean awardBean = new AwardBean();
                awardBean.setUid(uid);
                awardBean.setAward(awd);
                awardService.addAwardBean(awardBean);
            }
        }

        for(String inte: interestArray){
            if(!inte.equals("")) {
                InterestBean interestBean = new InterestBean();
                interestBean.setUid(uid);
                interestBean.setInterest(inte);
                interestService.addInterestBean(interestBean);
            }
        }
        System.out.println(userInfo.getImgSrc());
        boolean m = userInfoService.updateUserInfo(userInfo);
        if(m) {
            System.out.println("yes");
        }else{
            System.out.println("no");
        }
        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }
}
