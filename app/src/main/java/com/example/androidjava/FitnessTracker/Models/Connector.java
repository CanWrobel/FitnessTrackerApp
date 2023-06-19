package com.example.androidjava.FitnessTracker.Models;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.Statement;

public class Connector {
    Connection connection;
    Statement statement;
    String query;
    Resultset resultset;

}
