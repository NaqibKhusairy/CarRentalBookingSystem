package carrentalbookingsystem;                                                                         //package Name

import java.sql.*;                                                                                      //import all library in java.sql
import javax.swing.*;                                                                                   //import all library from javax.swing

public class LoginProcess extends connectionDB                                                          //class name with inheritance
{
    private String LoginUsername, LoginPassword , username , password;                                  //Private Sting Variable
    boolean loginSuccess = false;                                                                       //boolean variable
    
    public LoginProcess(String LoginUsername , String LoginPassword)                                    //Constructor
    {
        super();
        this.LoginUsername = LoginUsername;
        this.LoginPassword = LoginPassword;
        
        try                                                                                             //Exception Handling for finding errors
        {
            //Connect to database
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);                      //Establish a database connection		
            //get Username and Password
            PreparedStatement Getuser = conn.prepareStatement(
                "SELECT username , password FROM users WHERE username = ?"
            );
            
            if (LoginUsername.equals("") || LoginPassword.equals(""))                                   //If LoginUsername and LoginPassword is null , do
            {
                JOptionPane.showMessageDialog(null, "Please enter both username and password!", 
                        "Missing Info", JOptionPane.WARNING_MESSAGE);
                LoginGUI newlogin = new LoginGUI();
            }
            else
            {
                Getuser.setString(1, LoginUsername);                                                    //value checking column username get from LoginUsername
                ResultSet Setuser = Getuser.executeQuery();                                             //run Getuser Query
                if(Setuser.next())                                                                      //If User Found , do
                {
                    username = Setuser.getString("username").toLowerCase();                             //Set username value get from username in databsae
                    password = Setuser.getString("password").toLowerCase();                             //Set password value get from password in databsae
                    LoginUsername = LoginUsername.toLowerCase();
                    LoginPassword = LoginPassword.toLowerCase();

                    if (LoginUsername.equals(username) && LoginPassword.equals(password))               //If LoginUsername and LoginPassword same to username and password , do
                    {
                        if (LoginUsername.equals("admin"))                                              //If LoginUsername = admin , do
                        {
                            JOptionPane.showMessageDialog(null, "Welcome! " + LoginUsername);
                            AdminGUI Admin = new AdminGUI();                                            //call admin class
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Welcome! " + LoginUsername);
                            DashboardGUI Dashboard = new DashboardGUI(LoginUsername);                   //Call DashboardGUI class with pass parimeter
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid username or password!",
                                "Login Failed", JOptionPane.ERROR_MESSAGE);
                        LoginGUI newlogin = new LoginGUI();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                    LoginGUI newlogin = new LoginGUI();
                }
            }
        }
        catch (Exception e)                                                                             //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                             //print error
            System.exit(1);                                                                             //exit program
        }
    }

    public String getLoginUsername() {
        return LoginUsername;
    }

    public String getLoginPassword() {
        return LoginPassword;
    }
}