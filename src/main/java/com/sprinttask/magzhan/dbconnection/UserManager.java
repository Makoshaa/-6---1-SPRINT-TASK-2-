package com.sprinttask.magzhan.dbconnection;

import com.sprinttask.magzhan.db.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class UserManager {

    @Autowired
    private DBConnector dbConnector;

    public ArrayList<ApplicationRequest> getRequestList(){
        ArrayList<ApplicationRequest> requests=new ArrayList<>();

        try {
            PreparedStatement preparedStatement=
                    dbConnector.getConnection().prepareStatement("SELECT * FROM crm");
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                ApplicationRequest applicationRequest=new ApplicationRequest();
                applicationRequest.setId(resultSet.getLong("id"));
                applicationRequest.setUserName(resultSet.getString("name"));
                applicationRequest.setCourseName(resultSet.getString("course"));
                applicationRequest.setCommentary(resultSet.getString("commentary"));
                applicationRequest.setPhone(resultSet.getString("phone"));
                applicationRequest.setHandled(resultSet.getBoolean("handled"));
            }
            preparedStatement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return requests;
    }

    public void addRequest(ApplicationRequest applicationRequest){
        try {
            PreparedStatement preparedStatement=
                    dbConnector.getConnection().prepareStatement("INSERT INTO crm (namePeople, course, commentary, phone, handled)" +
                            "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, applicationRequest.getUserName());
            preparedStatement.setString(2,applicationRequest.getCourseName());
            preparedStatement.setString(3,applicationRequest.getCommentary());
            preparedStatement.setString(4,applicationRequest.getPhone());
            preparedStatement.setBoolean(5, applicationRequest.isHandled());

            preparedStatement.executeUpdate();


            preparedStatement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
