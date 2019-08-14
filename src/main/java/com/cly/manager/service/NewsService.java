package com.cly.manager.service;

import com.cly.manager.bean.NewsBean;

import java.util.List;

public interface NewsService {
    List<NewsBean> getRecentNews() throws Exception;
    List<NewsBean> getNewsList() throws Exception;
    List<NewsBean> getNewsList(int year) throws Exception;
    NewsBean getNews(int nid) throws Exception;
    boolean addNews(NewsBean newsBean);
    boolean updateNews(NewsBean newsBean);
    boolean deleteNews(int nid);
}
