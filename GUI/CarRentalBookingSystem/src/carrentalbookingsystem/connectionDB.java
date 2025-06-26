package carrentalbookingsystem;

import java.sql.*;                                                                                          //import all library in java.sql

public class connectionDB {
    protected String dbpassword = "";                                                                       //Private variable value String for dbpassword
    protected String dbusername = "root";                                                                   //Private variable value String for dbusername
    protected String urlWithoutDB = "jdbc:mysql://localhost:3306/";                                         //Private variable value String for urlWithoutDB
    protected String urlWithDB = "jdbc:mysql://localhost:3306/CarRental";                                   //Private variable value String for urlWithDB
    protected String driver = "com.mysql.cj.jdbc.Driver";                                                   //Private variable value String for driver
    protected Connection conn;                                                                              //Private variable for Connection
    protected Statement stmt;
    
    public connectionDB()                                                                                   //Constructor
    {
        try                                                                                                 //Exception Handling for finding errors
        {
            Class.forName(driver);                                                                          //Load the JDBC driver

            conn = DriverManager.getConnection(urlWithoutDB, dbusername, dbpassword);                       //Establish a database connection
            stmt = conn.createStatement();                                                                  //Create a Statement object

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS CarRental");                                  //SQL statement to create database if nor exists

            conn.close();                                                                                   //close the connection

            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);                          //Establish a database connection

            conn.close();                                                                                   //close the connection
        } 
        catch (Exception e)                                                                                 //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                                 //print array
            System.exit(1);                                                                                 //exit program
        }
    }
}
