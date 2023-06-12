package com.example.androidjava.FitnessTracker.Models;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String uname, pass, ip, port, database;

    public Connection connectionclass()
    {
        ip =  "rdbms.strato.de";
        database = "dbs11137514";
        uname = "dbu230492";
        pass = "fitnesstracker";
        port = "3306";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" + uname + "password=" + pass + ";" ;
            connection = DriverManager.getConnection(connectionURL);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
