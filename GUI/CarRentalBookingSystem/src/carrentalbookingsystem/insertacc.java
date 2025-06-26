package carrentalbookingsystem;                                                                     //package Name

import java.sql.*;                                                                                  //import all library in java.sql
import javax.swing.*;                                                                               //import all library from javax.swing

public class insertacc extends connectionDB                                                         //class name with inheritance
{
    private account acc;                                                                            //Create variable account
    private String RegisterUsername;
    
    public insertacc(account acc , String RegisterUsername)                                         //Constructor
    {
        super();                                                                                    //call parent constructor (connectionDB)
        this.acc = acc;                                                                             //Variable acc = acc in parameter
        this.RegisterUsername = RegisterUsername;
            
        try                                                                                         //Exception Handling for finding errors
        {
            //Connect to database
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);                  //Establish a database connection		
            //get Username from database
            PreparedStatement Getuser = conn.prepareStatement(
                "SELECT username FROM users WHERE username = ?"
            );
            Getuser.setString(1, acc.getUsername());                                                //value checking column username get from RegisterUsername
            ResultSet Setuser = Getuser.executeQuery();                                             //run Getuser Query
            if(Setuser.next())                                                                      //If User Found , do
            {
                JOptionPane.showMessageDialog(null,
                "Username already exists. Please choose a different username.",
                "Registration Failed",JOptionPane.WARNING_MESSAGE);
                RegisterGUI register = new RegisterGUI();
            }
            else                                                                                    //selain dari itu , do
            {
                //insert input data to database
                PreparedStatement insertUser = conn.prepareStatement
                (
                    "INSERT INTO users (username, phone, ic, password) VALUES (?, ?, ?, ?)"
                );
                insertUser.setString(1, acc.getUsername());                                          //value insert column username get from RegisterUsername
                insertUser.setString(2, acc.getPhone());                                             //value insert column phone get from RegisterPhone
                insertUser.setString(3, acc.getIc());                                                //value insert column ic get from RegisterIC
                insertUser.setString(4, acc.getPassword());                                          //value insert column password get from RegisterPassword
                insertUser.executeUpdate();                                                         //Execute SQL statement
                
                JOptionPane.showMessageDialog(null, "Welcome new user! " + RegisterUsername);
                DashboardGUI Dashboard = new DashboardGUI(RegisterUsername);                        //Call DashboardGUI class with pass parimeter
            }
        }
        catch (Exception e)                                                                         //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                         //print error
            System.exit(1);                                                                         //exit program
        }
    }
    
}
