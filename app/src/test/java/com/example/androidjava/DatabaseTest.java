package com.example.androidjava;

import org.junit.Test;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {
    @Test
    public void testMain(){
        try {


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-video", "root", "fitnesstracker");

            Statement statement = connection.createStatement();

            String query = "SELECT * " +
                    "FROM fitnesstracker";
            ResultSet resultSet = statement.executeQuery(query);
            String[] result = new String[10];


            while (resultSet.next()) {
                int i = 10;
                result[i] = resultSet.getString("datum");
                i--;
                if(i == 0) break;
                //System.out.println(resultSet.getString("datum"));
                //System.out.println(resultSet.getString("steps"));
            }

            System.out.println(result[10]);
        } catch (Exception e){

        }
    }

}
