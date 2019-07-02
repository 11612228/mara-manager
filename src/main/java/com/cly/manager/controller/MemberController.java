package com.cly.manager.controller;

import com.cly.manager.bean.*;
import com.cly.manager.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    UserInfoService userInfoService = new UserInfoServiceImpl();
    EducationService educationService = new EducationServiceImpl();
    ExperienceService experienceService = new ExperienceServiceImpl();
    AwardService awardService = new AwardServiceImpl();
    InterestService interestService = new InterestServiceImpl();
    PublicationService publicationService = new PublicationServiceImpl();

    UserInfoBean userInfoBean = null;
    List<EducationBean> educationBeanList = null;
    List<ExperienceBean> experienceBeanList = null;
    List<InterestBean> interestBeanList = null;
    List<AwardBean> awardBeanList = null;
    List<PublicationBean> publicationBeanList = null;
    @RequestMapping(value = "/memberInfo")
    public String getMemberInfo (@RequestParam(name="uid", required=false, defaultValue="1") int uid, Model model) {
        try {
            userInfoBean =userInfoService.getUserInfo(uid);
            publicationBeanList = publicationService.getPublicationListByUid(uid);
            educationBeanList = educationService.getEducationList(uid);
            experienceBeanList = experienceService.getExperienceBeanList(uid);
            awardBeanList = awardService.getAwardBeanList(uid);
            interestBeanList = interestService.getInterestBeanList(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean", userInfoBean);
        model.addAttribute("publicationBeanList", publicationBeanList);
        model.addAttribute("educationBeanList",educationBeanList);
        model.addAttribute("experienceBeanList",experienceBeanList);
        model.addAttribute("awardBeanList",awardBeanList);
        model.addAttribute("interestBeanList",interestBeanList);
        return "memberInfo";
    }
}
