package com.cly.manager.dao;

import com.cly.manager.bean.EducationBean;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationDaoImpl implements EducationDao {
    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    @Override
    public List<EducationBean> fetchEducationBeanList(int uid) throws Exception {
        List<EducationBean> educationBeanList = new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Education e where uid = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,uid);
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            int eid = resultSet.getInt("eid");
            String education = resultSet.getString("education");

            EducationBean educationBean = new EducationBean();
            educationBean.setEducation(education);
            educationBean.setUid(uid);
            educationBean.setEid(eid);

            educationBeanList.add(educationBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return educationBeanList;
    }

    @Override
    public boolean addEducationBean(EducationBean educationBean) {
        try {
            connection = dbutil.getConnection();
            String sql = "INSERT INTO Education (education, uid) VALUES (?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, educationBean.getEducation());
            preparedStatement.setInt(2, educationBean.getUid());
            int result = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return result!=0;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteEducationBean(int uid) {
        try {
            connection = dbutil.getConnection();
            String sql = "DELETE from Education where uid = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uid);
            int result = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return result != 0;
        }catch (Exception e){
            return false;
        }
    }
}
