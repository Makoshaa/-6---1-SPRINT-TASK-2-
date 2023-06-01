package com.sprinttask.magzhan.beans;


import com.sprinttask.magzhan.dbconnection.DBConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

@Configuration
public class BeanConfig {

    @Bean
    public DBConnector connection(){
        DBConnector dbConnector= new DBConnector("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
        return dbConnector;
    }
}
