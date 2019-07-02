package com.cly.manager.dao;

import com.cly.manager.bean.AlumniBean;
import com.cly.manager.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumniDaoImpl implements AluminDao {
    ConnectDB dbutil = new ConnectDB();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    List<AlumniBean> alumniBeanList = null;
    @Override
    public List<AlumniBean> fetchAlumniBeanList() throws Exception {
        alumniBeanList = new ArrayList<>();
        connection = dbutil.getConnection();
        String sql="select * from Alumni a";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement .executeQuery();

        while(resultSet.next()){
            int aid = resultSet.getInt("aid");
            String email = resultSet.getString("email");
            String aName = resultSet.getString("aName");
            String position = resultSet.getString("position");
            String education = resultSet.getString("education");


            AlumniBean alumniBean = new AlumniBean();
            alumniBean.setAid(aid);
            alumniBean.setEmail(email);
            alumniBean.setaName(aName);
            alumniBean.setEducation(education);
            alumniBean.setPosition(position);

            alumniBeanList.add(alumniBean);

        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return alumniBeanList;
    }
}
