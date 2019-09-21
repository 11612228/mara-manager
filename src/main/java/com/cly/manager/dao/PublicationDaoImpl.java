package com.cly.manager.dao;

import com.cly.manager.bean.PublicationBean;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDaoImpl implements PublicationDao {

    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    @Override
    public List<PublicationBean> fetchPublicationList() throws Exception {

        List<PublicationBean> publicationBeanList =new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Publication p order by pid desc";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            int pid = resultSet.getInt("pid");
            String imgSrc = resultSet.getString("imgSrc");
            String content = resultSet.getString("content");
            String author = resultSet.getString("author");
            String publisher = resultSet.getString("publisher");
            int year = resultSet.getInt("year");
            int uid = resultSet.getInt("uid");
            String pdfUrl = resultSet.getString("pdfUrl");

            PublicationBean publicationBean = new PublicationBean();
            publicationBean.setContent(content);
            publicationBean.setImgSrc(imgSrc);
            publicationBean.setYear(year);
            publicationBean.setUid(uid);
            publicationBean.setAuthor(author);
            publicationBean.setPublisher(publisher);
            publicationBean.setPid(pid);
            publicationBean.setPdfUrl(pdfUrl);

            publicationBeanList.add(publicationBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return publicationBeanList;
    }

    @Override
    public List<PublicationBean> fetchPublicationList(int year) throws Exception {

        List<PublicationBean> publicationBeanList =new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Publication p where year = ? order by pid desc";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, year);
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            int pid = resultSet.getInt("pid");
            String imgSrc = resultSet.getString("imgSrc");
            String content = resultSet.getString("content");
            String author = resultSet.getString("author");
            String publisher = resultSet.getString("publisher");
            int uid = resultSet.getInt("uid");
            String pdfUrl = resultSet.getString("pdfUrl");


            PublicationBean publicationBean = new PublicationBean();
            publicationBean.setContent(content);
            publicationBean.setImgSrc(imgSrc);
            publicationBean.setYear(year);
            publicationBean.setUid(uid);
            publicationBean.setAuthor(author);
            publicationBean.setPublisher(publisher);
            publicationBean.setPid(pid);
            publicationBean.setPdfUrl(pdfUrl);

            publicationBeanList.add(publicationBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return publicationBeanList;
    }

    @Override
    public List<PublicationBean> fetchPublicationListByUid(int uid) throws Exception {

        List<PublicationBean> publicationBeanList =new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Publication p where uid = ? order by pid desc";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, uid);
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            int pid = resultSet.getInt("pid");
            String imgSrc = resultSet.getString("imgSrc");
            String content = resultSet.getString("content");
            String author = resultSet.getString("author");
            String publisher = resultSet.getString("publisher");
            int year = resultSet.getInt("year");
            String pdfUrl = resultSet.getString("pdfUrl");


            PublicationBean publicationBean = new PublicationBean();
            publicationBean.setContent(content);
            publicationBean.setImgSrc(imgSrc);
            publicationBean.setYear(year);
            publicationBean.setUid(uid);
            publicationBean.setAuthor(author);
            publicationBean.setPublisher(publisher);
            publicationBean.setPid(pid);
            publicationBean.setPdfUrl(pdfUrl);

            publicationBeanList.add(publicationBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return publicationBeanList;
    }

    @Override
    public PublicationBean fetchPublication(int pid) throws Exception {

        connection = dbutil.getConnection();
        String sql="select * from Publication p where pid = ? order by pid desc";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, pid);
        resultSet=preparedStatement.executeQuery();
        PublicationBean publicationBean = new PublicationBean();
        while(resultSet.next()){
            int uid = resultSet.getInt("uid");
            String imgSrc = resultSet.getString("imgSrc");
            String content = resultSet.getString("content");
            String author = resultSet.getString("author");
            String publisher = resultSet.getString("publisher");
            int year = resultSet.getInt("year");
            String pdfUrl = resultSet.getString("pdfUrl");

            publicationBean.setContent(content);
            publicationBean.setImgSrc(imgSrc);
            publicationBean.setYear(year);
            publicationBean.setUid(uid);
            publicationBean.setAuthor(author);
            publicationBean.setPublisher(publisher);
            publicationBean.setPid(pid);
            publicationBean.setPdfUrl(pdfUrl);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return publicationBean;
    }

    @Override
    public boolean addPublication(PublicationBean publicationBean) {
        try {
            connection = dbutil.getConnection();
            String sql="insert into Publication (year,content,uid,imgSrc,pdfUrl,author,publisher) values (?,?,?,?,?,?,?) ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,publicationBean.getYear());
            preparedStatement.setString(2,publicationBean.getContent());
            preparedStatement.setInt(3,publicationBean.getUid());
            preparedStatement.setString(4,publicationBean.getImgSrc());
            preparedStatement.setString(5,publicationBean.getPdfUrl());
            preparedStatement.setString(6,publicationBean.getAuthor());
            preparedStatement.setString(7,publicationBean.getPublisher());
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updatePublication(PublicationBean publicationBean) {
        try {
            connection = dbutil.getConnection();
            String sql="UPDATE Publication SET year=?, content=?, imgSrc=?, pdfUrl=?, author=?, publisher=?  where pid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,publicationBean.getYear());
            preparedStatement.setString(2,publicationBean.getContent());
            preparedStatement.setString(3,publicationBean.getImgSrc());
            preparedStatement.setString(4,publicationBean.getPdfUrl());
            preparedStatement.setString(5,publicationBean.getAuthor());
            preparedStatement.setString(6,publicationBean.getPublisher());
            preparedStatement.setInt(7,publicationBean.getPid());
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletePublication(int pid) {
        try {
            connection = dbutil.getConnection();
            String sql="delete from Publication where pid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, pid);
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }
}
