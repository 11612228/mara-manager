package com.cly.manager.dao;

import com.cly.manager.bean.ExperienceBean;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDaoImpl implements ExperienceDao {
    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    @Override
    public List<ExperienceBean> fetchExperienceBeanList(int uid) throws Exception {
        List<ExperienceBean> experienceBeanList = new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Experience e where uid = ?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,uid);
        resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            int eid = resultSet.getInt("eid");
            String experience = resultSet.getString("experience");

            ExperienceBean experienceBean = new ExperienceBean();
            experienceBean.setExperience(experience);
            experienceBean.setUid(uid);
            experienceBean.setEid(eid);

            experienceBeanList.add(experienceBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return experienceBeanList;
    }

    @Override
    public boolean addExperienceBean(ExperienceBean experienceBean) {
        try {
            connection = dbutil.getConnection();
            String sql = "INSERT INTO Experience (experience, uid) VALUES (?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, experienceBean.getExperience());
            preparedStatement.setInt(2, experienceBean.getUid());
            int result = preparedStatement.executeUpdate();
            dbutil.closeDBResource(connection, preparedStatement, resultSet);
            return result!=0;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteExperienceBean(int uid) {
        try {
            connection = dbutil.getConnection();
            String sql = "DELETE from Experience where uid = ?;";
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
