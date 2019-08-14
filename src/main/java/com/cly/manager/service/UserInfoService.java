package com.cly.manager.service;

import com.cly.manager.bean.UserInfoBean;

import java.util.List;

public interface UserInfoService {
    List<UserInfoBean> getUserInfoBeanList() throws Exception;
    List<UserInfoBean> getUserInfoBeanList(String post) throws Exception;
    UserInfoBean login(String userName, String password) throws Exception;
    UserInfoBean getUserInfo(int uid) throws Exception;
    UserInfoBean getUserInfo(String realName) throws Exception;

    boolean addUserInfo(UserInfoBean userInfoBean);
    boolean updateUserInfo(UserInfoBean userInfoBean);
    boolean deleteUserInfo(int uid);
}
