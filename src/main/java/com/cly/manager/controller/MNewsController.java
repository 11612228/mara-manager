package com.cly.manager.controller;

import com.cly.manager.bean.NewsBean;
import com.cly.manager.bean.UserInfoBean;
import com.cly.manager.service.NewsService;
import com.cly.manager.service.NewsServiceImpl;
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
public class MNewsController {
    NewsService newsService = new NewsServiceImpl();
    List<NewsBean> newsBeanList = null;
    int pagesize = 7;
    double fpagesize = 7.0;
    int pageNumber;
    int[] pageList;

    @GetMapping("/addNews")
    public String modifyNews(Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        UserInfoBean userInfo = null;
        model.addAttribute("userInfoBean",userInfoBean);
        return "addnews";
    }

    @GetMapping("/modifyNews")
    public String modifyNews(@RequestParam(name="nid") int nid, Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        NewsBean newsBean = null;
        try {
            newsBean = newsService.getNews(nid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("newsBean",newsBean);
        return "newsinfo";
    }

    @GetMapping("/deleteNews")
    public String deleteNews(@RequestParam(name="nid") int nid, Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        newsService.deleteNews(nid);
        try {
            newsBeanList = newsService.getNewsList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/newsPage?page=1&amp;year=0";
    }
    @PostMapping("/updateNews")
    public String updateNews(@RequestParam(name="nid") int nid,
                                    @RequestParam(name="content") String content,
                                    @RequestParam(name="title") String title,
                                    @RequestParam(name="file") MultipartFile file,
                                    @RequestParam(name="day") int day,
                                    @RequestParam(name="month") int month,
                                    @RequestParam(name="year") int year,
                                    Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        NewsBean newsBean = null;
        boolean flag = false;
        try {
            String imgSrc = ImgUtil.getImgName(file);
            newsBean = newsService.getNews(nid);
            newsBean.setContent(content);
            newsBean.setDay(day);
            newsBean.setMonth(month);
            newsBean.setYear(year);
            newsBean.setTitle(title);
            newsBean.setImgSrc(imgSrc);
            flag = newsService.updateNews(newsBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }

    @PostMapping("/newNews")
    public String newNews(
            @RequestParam(name="content") String content,
            @RequestParam(name="title") String title,
            @RequestParam(name="file") MultipartFile file,
            @RequestParam(name="day") int day,
            @RequestParam(name="month") int month,
            @RequestParam(name="year") int year,
            Model model, HttpServletRequest request){
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        String imgSrc = ImgUtil.getImgName(file);
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        NewsBean newsBean = new NewsBean();
        newsBean.setContent(content);
        newsBean.setDay(day);
        newsBean.setMonth(month);
        newsBean.setYear(year);
        newsBean.setTitle(title);
        if(!imgSrc.equals("default.png"))
            newsBean.setImgSrc(imgSrc);
        newsService.addNews(newsBean);
        model.addAttribute("userInfoBean",userInfoBean);
        return "management";
    }

    @GetMapping(value = "/newsPage")
    public String getNewsPage(@RequestParam(name = "page", required = false, defaultValue = "1") int page, @RequestParam(name = "year", required = false, defaultValue = "0") int year, Model model, HttpServletRequest request) {
        if(!Interceptor.getInterceptor(request)){
            return "index";
        }
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("userInfoBean");
        if(page!=1){
            boolean flag = true;
            HttpSession session = request.getSession();
            try {
                newsBeanList = (List<NewsBean>) session.getAttribute("newsBeanList");
            }catch (Exception e){
                System.err.println(e.toString());
                flag = false;
            }
            if(flag && newsBeanList!=null){
                if(newsBeanList.size()>(page-1)*pagesize){
                    int pageNumber = (int)Math.ceil(newsBeanList.size()/fpagesize);
                    int[] pageList = new int[pageNumber];
                    for(int i =0;i<pageNumber;i++){
                        pageList[i] = i+1;
                    }
                    model.addAttribute("userInfoBean",userInfoBean);
                    model.addAttribute("newsBeanList", newsBeanList.subList((page-1)*pagesize,Math.min(page*pagesize,newsBeanList.size())));
                    model.addAttribute("page",page);
                    model.addAttribute("pageList",pageList);
                    model.addAttribute("year",year);
                    return "newslist";
                }
            }
        }
        try {
            if(year == 0) {
                newsBeanList = newsService.getNewsList();
            }else{
                newsBeanList = newsService.getNewsList(year);
            }
            HttpSession session = request.getSession();
            session.setAttribute("newsBeanList",newsBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageNumber = (int)Math.ceil(newsBeanList.size()/fpagesize);
        int[] pageList = new int[pageNumber];
        for(int i =0;i<pageNumber;i++){
            pageList[i] = i+1;
        }
        model.addAttribute("userInfoBean",userInfoBean);
        model.addAttribute("newsBeanList", newsBeanList.subList(0,Math.min(pagesize,newsBeanList.size())));
        model.addAttribute("page",page);
        model.addAttribute("pageList",pageList);
        model.addAttribute("year",year);
        return "newslist";
    }
}
