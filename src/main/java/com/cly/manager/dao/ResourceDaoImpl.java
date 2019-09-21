package com.cly.manager.dao;

import com.cly.manager.bean.ResourceBean;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceDaoImpl implements ResourceDao {
    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;

    List<ResourceBean> resourceBeanList = null;
    ResourceBean resourceBean = null;
    @Override
    public List<ResourceBean> fetchResourceBeanList() throws Exception {
        resourceBeanList = new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Resource r";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement .executeQuery();

        while(resultSet.next()){
            int pid = resultSet.getInt("resid");
            String imgSrc = resultSet.getString("imgSrc");
            String pName = resultSet.getString("pName");
            String pNameC = resultSet.getString("pNameC");


            resourceBean = new ResourceBean();
            resourceBean.setResid(pid);
            resourceBean.setImgSrc(imgSrc);
            resourceBean.setpName(pName);
            resourceBean.setpNameC(pNameC);

            resourceBeanList.add(resourceBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return resourceBeanList;
    }

    @Override
    public ResourceBean fetchResourceBean(int resid) throws Exception {
        connection = dbutil.getConnection();
        String sql="select * from Resource r where resid = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, resid);
        resultSet=preparedStatement .executeQuery();
        while(resultSet.next()){
            String imgSrc = resultSet.getString("imgSrc");
            String pName = resultSet.getString("pName");
            String pNameC = resultSet.getString("pNameC");
            resourceBean = new ResourceBean();
            resourceBean.setImgSrc(imgSrc);
            resourceBean.setpName(pName);
            resourceBean.setpNameC(pNameC);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return resourceBean;
    }

    @Override
    public boolean deleteResourceBean(int resid) {
        try {
            connection = dbutil.getConnection();
            String sql="delete from Resource where resid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, resid);
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addResourceBean(ResourceBean resourceBean) {
        try {
            connection = dbutil.getConnection();
            String sql="insert into Resource (imgSrc,pName,pNameC) values (?,?,?) ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,resourceBean.getImgSrc());
            preparedStatement.setString(2,resourceBean.getpName());
            preparedStatement.setString(3,resourceBean.getpNameC());
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateResourceBean(ResourceBean resourceBean) {
        try {
            connection = dbutil.getConnection();
            String sql="UPDATE Resource SET imgSrc=?, pName=?, pNameC=?  where resid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,resourceBean.getImgSrc());
            preparedStatement.setString(2,resourceBean.getpName());
            preparedStatement.setString(3,resourceBean.getpNameC());
            preparedStatement.setInt(4,resourceBean.getResid());
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return rtn!=0;
        } catch (Exception e) {
            return false;
        }
    }
}
