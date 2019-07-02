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
            String pName = resultSet.getString("pName");
            int year = resultSet.getInt("year");
            int uid = resultSet.getInt("uid");
            String pdfUrl = resultSet.getString("pdfUrl");

            PublicationBean publicationBean = new PublicationBean();
            publicationBean.setContent(content);
            publicationBean.setImgSrc(imgSrc);
            publicationBean.setYear(year);
            publicationBean.setUid(uid);
            publicationBean.setpName(pName);
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
            String pName = resultSet.getString("pName");
            int uid = resultSet.getInt("uid");
            String pdfUrl = resultSet.getString("pdfUrl");


            PublicationBean publicationBean = new PublicationBean();
            publicationBean.setContent(content);
            publicationBean.setImgSrc(imgSrc);
            publicationBean.setYear(year);
            publicationBean.setUid(uid);
            publicationBean.setpName(pName);
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
            String pName = resultSet.getString("pName");
            int year = resultSet.getInt("year");
            String pdfUrl = resultSet.getString("pdfUrl");


            PublicationBean publicationBean = new PublicationBean();
            publicationBean.setContent(content);
            publicationBean.setImgSrc(imgSrc);
            publicationBean.setYear(year);
            publicationBean.setUid(uid);
            publicationBean.setpName(pName);
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
            String pName = resultSet.getString("pName");
            int year = resultSet.getInt("year");
            String pdfUrl = resultSet.getString("pdfUrl");

            publicationBean.setContent(content);
            publicationBean.setImgSrc(imgSrc);
            publicationBean.setYear(year);
            publicationBean.setUid(uid);
            publicationBean.setpName(pName);
            publicationBean.setPid(pid);
            publicationBean.setPdfUrl(pdfUrl);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return publicationBean;
    }

    @Override
    public boolean addAPublication(PublicationBean publicationBean) {
        return false;
    }

    @Override
    public boolean updatePublication(PublicationBean publicationBean) {
        return false;
    }
}
