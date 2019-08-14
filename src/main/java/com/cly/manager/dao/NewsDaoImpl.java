package com.cly.manager.dao;

import com.cly.manager.bean.NewsBean;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;

    @Override
    public List<NewsBean> fetchNewsList() throws Exception {
        List<NewsBean> newsBeanList=new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from News n  order by nid desc";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            int nid = resultSet.getInt("nid");
            String imgSrc = resultSet.getString("imgSrc");
            String content = resultSet.getString("content");
            String title = resultSet.getString("title");
            int year = resultSet.getInt("year");
            int month = resultSet.getInt("month");
            int day = resultSet.getInt("day");
            int uid = resultSet.getInt("uid");

            NewsBean newsBean = new NewsBean();
            newsBean.setNid(nid);
            newsBean.setContent(content);
            newsBean.setImgSrc(imgSrc);
            newsBean.setDay(day);
            newsBean.setTitle(title);
            newsBean.setYear(year);
            newsBean.setMonth(month);
            newsBean.setUid(uid);

            newsBeanList.add(newsBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return newsBeanList;
    }

    @Override
    public List<NewsBean> fetchNewsList(int year) throws Exception {
        List<NewsBean> newsBeanList=new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from News n where year = ? order by nid desc";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,year);
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            int nid = resultSet.getInt("nid");
            String imgSrc = resultSet.getString("imgSrc");
            String content = resultSet.getString("content");
            String title = resultSet.getString("title");
            int month = resultSet.getInt("month");
            int day = resultSet.getInt("day");
            int uid = resultSet.getInt("uid");

            NewsBean newsBean = new NewsBean();
            newsBean.setNid(nid);
            newsBean.setContent(content);
            newsBean.setImgSrc(imgSrc);
            newsBean.setDay(day);
            newsBean.setTitle(title);
            newsBean.setYear(year);
            newsBean.setMonth(month);
            newsBean.setUid(uid);

            newsBeanList.add(newsBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return newsBeanList;
    }

    @Override
    public NewsBean fetchNews(int nid) throws Exception {
        NewsBean newsBean = new NewsBean();
        connection = dbutil.getConnection();
        String sql="select * from News n where nid=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, nid); //将sql段第一个？代替
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            String imgSrc = resultSet.getString("imgSrc");
            String content = resultSet.getString("content");
            String title = resultSet.getString("title");
            int year = resultSet.getInt("year");
            int month = resultSet.getInt("month");
            int day = resultSet.getInt("day");
            int uid = resultSet.getInt("uid");

            newsBean.setNid(nid);
            newsBean.setContent(content);
            newsBean.setImgSrc(imgSrc);
            newsBean.setDay(day);
            newsBean.setTitle(title);
            newsBean.setYear(year);
            newsBean.setMonth(month);
            newsBean.setUid(uid);
        }

        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return newsBean;
    }

    @Override
    public boolean addNews(NewsBean newsBean) {
        try {
            connection = dbutil.getConnection();
            String sql="insert into News (imgSrc,content,title,uid,day,year,month) values (?,?,?,?,?,?,?) ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, newsBean.getImgSrc()); //将sql段第一个？代替
            preparedStatement.setString(2, newsBean.getContent()); //将sql段第一个？代替
            preparedStatement.setString(3, newsBean.getTitle()); //将sql段第一个？代替
            preparedStatement.setInt(4,newsBean.getUid());
            preparedStatement.setInt(5, newsBean.getDay()); //将sql段第一个？代替
            preparedStatement.setInt(6, newsBean.getYear()); //将sql段第一个？代替
            preparedStatement.setInt(7, newsBean.getMonth()); //将sql段第一个？代替


            int rtn =preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateNews(NewsBean newsBean) {
        try {
            connection = dbutil.getConnection();
            String sql="UPDATE News SET imgSrc=?, content=?, title=?, uid=?, day=?, year=?, month=? where nid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, newsBean.getImgSrc()); //将sql段第一个？代替
            preparedStatement.setString(2, newsBean.getContent()); //将sql段第一个？代替
            preparedStatement.setString(3, newsBean.getTitle()); //将sql段第一个？代替
            preparedStatement.setInt(4,newsBean.getUid());
            preparedStatement.setInt(5, newsBean.getDay()); //将sql段第一个？代替
            preparedStatement.setInt(6, newsBean.getYear()); //将sql段第一个？代替
            preparedStatement.setInt(7, newsBean.getMonth()); //将sql段第一个？代替
            preparedStatement.setInt(8, newsBean.getNid()); //将sql段第一个？代替

            int rtn =preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return true;
        } catch (Exception e) {
           return false;
        }
    }

    @Override
    public boolean deleteNews(int nid) {
        try {
            connection = dbutil.getConnection();
            String sql="delete from News where nid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, nid);
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }
}
