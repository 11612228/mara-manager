package com.cly.manager.dao;

import com.cly.manager.bean.UserInfoBean;

import java.util.List;

public interface UserInfoDao {
    List<UserInfoBean> fetchUserInfoList()throws Exception;
    List<UserInfoBean> fetchUserInfoList(String post)throws Exception;
    UserInfoBean fetchUserInfo(int uid) throws Exception;
    UserInfoBean fetchUserInfo(String realName) throws Exception;
    UserInfoBean fetchUserInfo(String userName, String password) throws Exception;

    boolean addUserInfo(UserInfoBean userInfoBean);
    boolean updateUserInfo(UserInfoBean userInfoBean);
    boolean deleteUserInfo(int uid);
}
