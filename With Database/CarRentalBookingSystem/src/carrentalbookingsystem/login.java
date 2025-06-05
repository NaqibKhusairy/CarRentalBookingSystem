package carrentalbookingsystem;                                                                         //package Name

import java.sql.*;                                                                                      //import all library in java.sql
import java.util.Scanner;                                                                               //import library scanner

public class login extends connectionDB                                                                 //class name with inheritance
{
    private String LoginUsername, LoginPassword , username , password;                                  //Private Sting Variable
    boolean loginSuccess = false;                                                                       //boolean variable
    
    public login()                                                                                      //Constructor
    {
        super();
        Scanner LoginInput = new Scanner(System.in);                                                    //set scanner variable to LoginInput
        try                                                                                             //Exception Handling for finding errors
        {
            //Connect to database
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);                      //Establish a database connection		
            //get Username and Password
            PreparedStatement Getuser = conn.prepareStatement(
                "SELECT username , password FROM users WHERE username = ?"
            );
            
            while (true)                                                                                //while loop until return value false (always loop)
            {
                System.out.println("------------------------");                                         //print line
                System.out.print("Username: ");                                                         //print Username
                LoginUsername = LoginInput.nextLine();                                                  //LoginUsername = input from user
                System.out.print("Password: ");                                                         //print Password
                LoginPassword = LoginInput.nextLine();                                                  //LoginPassword = input from user

                if (LoginUsername.equals("") || LoginPassword.equals(""))                               //If LoginUsername and LoginPassword is null , do
                {
                        System.out.println("Please enter All Field!");                                  //Print Warning
                }
                
                Getuser.setString(1, LoginUsername);                                                    //value checking column username get from LoginUsername
                ResultSet Setuser = Getuser.executeQuery();                                             //run Getuser Query
                if(Setuser.next())                                                                      //If User Found , do
                {
                    username = Setuser.getString("username");                                           //Set username value get from username in databsae
                    password = Setuser.getString("password");                                           //Set password value get from password in databsae
                    if (LoginUsername.equals(username) && LoginPassword.equals(password))               //If LoginUsername and LoginPassword same to username and password , do
                    {
                        System.out.println("------------------------");                                 //print line
                        System.out.println("Welcome! " + LoginUsername);                                //print Welcome
                        
                        if (LoginUsername.equals("admin"))                                              //If LoginUsername = admin , do
                        {
                            admin Admin = new admin();                                                  //call admin class
                        }
                        else                                                                            //Selain itu , do
                        {
                            dashboard Dashboard = new dashboard(LoginUsername);                         //Call dashboard class with pass parimeter
                        }
                        
                        loginSuccess = true;                                                            //set boolean loginSuccess value to true
                        break;                                                                          //stop loop
                    }
                }
                if (loginSuccess)                                                                       //if loginSuccess value is true , do
                {
                    break;                                                                              //stop loop
                } 
                else                                                                                    //selain itu , do
                {
                    System.out.println("Username or Password invalid. Please try again.");              //Print Warning
                }
            }
        }
        catch (Exception e)                                                                             //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                             //print error
            System.exit(1);                                                                             //exit program
        }
    }
}
