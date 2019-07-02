package com.cly.manager.dao;

import com.cly.manager.bean.UserInfoBean;
import com.cly.manager.util.CheckSqlInsert;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao {
    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;

    @Override
    public List<UserInfoBean> fetchUserInfoList() throws Exception {
        List<UserInfoBean> userInfoBeanList=new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from UserInfo u ";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            int uid = resultSet.getInt("uid");
            String imgSrc = resultSet.getString("imgSrc");
            String email = resultSet.getString("email");
            String telephone = resultSet.getString("telephone");
            String office = resultSet.getString("office");
            String post = resultSet.getString("post");
            String address = resultSet.getString("address");
            String brief = resultSet.getString("brief");
            String realName = resultSet.getString("realName");

            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUid(uid);
            userInfoBean.setImgSrc(imgSrc);
            userInfoBean.setEmail(email);
            userInfoBean.setPost(post);
            userInfoBean.setTelephone(telephone);
            userInfoBean.setOffice(office);
            userInfoBean.setBrief(brief);
            userInfoBean.setAddress(address);
            userInfoBean.setRealName(realName);

            userInfoBeanList.add(userInfoBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return userInfoBeanList;
    }

    @Override
    public List<UserInfoBean> fetchUserInfoList(String post) throws Exception {
        return null;
    }

    @Override
    public UserInfoBean fetchUserInfo(int uid) throws Exception {
        UserInfoBean userInfoBean = new UserInfoBean();
        connection = dbutil.getConnection();
        String sql="select * from UserInfo u where uid = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, uid); //将sql段第一个？代替
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            String imgSrc = resultSet.getString("imgSrc");
            String email = resultSet.getString("email");
            String telephone = resultSet.getString("telephone");
            String office = resultSet.getString("office");
            String post = resultSet.getString("post");
            String address = resultSet.getString("address");
            String brief = resultSet.getString("brief");
            String realName = resultSet.getString("realName");

            userInfoBean.setUid(uid);
            userInfoBean.setImgSrc(imgSrc);
            userInfoBean.setEmail(email);
            userInfoBean.setPost(post);
            userInfoBean.setTelephone(telephone);
            userInfoBean.setOffice(office);
            userInfoBean.setBrief(brief);
            userInfoBean.setAddress(address);
            userInfoBean.setRealName(realName);


        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return userInfoBean;
    }

    @Override
    public UserInfoBean fetchUserInfo(String userName, String password) throws Exception {
        UserInfoBean userInfoBean = null;
        userName = CheckSqlInsert.TransactSQLInjection(userName); //防止sql注入
        password = CheckSqlInsert.TransactSQLInjection(password); //防止sql注入
        connection = dbutil.getConnection();
        String sql="select * from UserInfo u where userName = ? and password = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            int uid = resultSet.getInt("uid");
            String imgSrc = resultSet.getString("imgSrc");
            String email = resultSet.getString("email");
            String telephone = resultSet.getString("telephone");
            String office = resultSet.getString("office");
            String post = resultSet.getString("post");
            String address = resultSet.getString("address");
            String brief = resultSet.getString("brief");
            String realName = resultSet.getString("realName");

            userInfoBean = new UserInfoBean();
            userInfoBean.setUid(uid);
            userInfoBean.setImgSrc(imgSrc);
            userInfoBean.setEmail(email);
            userInfoBean.setPost(post);
            userInfoBean.setTelephone(telephone);
            userInfoBean.setOffice(office);
            userInfoBean.setBrief(brief);
            userInfoBean.setAddress(address);
            userInfoBean.setRealName(realName);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return userInfoBean;
    }

    @Override
    public boolean updateUserInfo(UserInfoBean userInfoBean) {
        try {
            connection = dbutil.getConnection();
            String sql="UPDATE UserInfo SET userName=?, imgSrc=?, password=?, isAdmin=?, email=?, post=?, telephone=?, office=?, office=?, brief=?, address=? where uid = ?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, userInfoBean.getUserName());
            preparedStatement.setString(2, userInfoBean.getImgSrc());
            preparedStatement.setString(3, userInfoBean.getPassword());
            preparedStatement.setBoolean(4, userInfoBean.isAdmin());
            preparedStatement.setString(5, userInfoBean.getEmail());
            preparedStatement.setString(6, userInfoBean.getPost());
            preparedStatement.setString(7, userInfoBean.getTelephone());
            preparedStatement.setString(8, userInfoBean.getOffice());
            preparedStatement.setString(9, userInfoBean.getBrief());
            preparedStatement.setString(10, userInfoBean.getAddress());
            preparedStatement.setInt(11, userInfoBean.getUid());
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addUserInfo(UserInfoBean userInfoBean) {
        try {
            connection = dbutil.getConnection();
            String sql="insert into UserInfo (userName,imgSrc,password,isAdmin, email, post, telephone, office, brief, address) values (?,?,?,?,?,?,?,?,?,?) ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, userInfoBean.getUserName());
            preparedStatement.setString(2, userInfoBean.getImgSrc());
            preparedStatement.setString(3, userInfoBean.getPassword());
            preparedStatement.setBoolean(4, userInfoBean.isAdmin());
            preparedStatement.setString(5, userInfoBean.getEmail());
            preparedStatement.setString(6, userInfoBean.getPost());
            preparedStatement.setString(7, userInfoBean.getTelephone());
            preparedStatement.setString(8, userInfoBean.getOffice());
            preparedStatement.setString(9, userInfoBean.getBrief());
            preparedStatement.setString(10, userInfoBean.getAddress());
            int rtn = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
