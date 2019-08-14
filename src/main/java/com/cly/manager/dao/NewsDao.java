package com.cly.manager.dao;

import com.cly.manager.bean.NewsBean;

import java.util.List;

public interface NewsDao {
    List<NewsBean> fetchNewsList()throws Exception;
    List<NewsBean> fetchNewsList(int year)throws Exception;
    NewsBean fetchNews(int nid)throws Exception;

    boolean addNews(NewsBean newsBean);
    boolean updateNews(NewsBean newsBean);
    boolean deleteNews(int nid);
}
