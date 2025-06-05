package carrentalbookingsystem;                                                                     //package Name

import java.sql.*;                                                                                  //import all library in java.sql

public class insertacc extends connectionDB                                                        //class name with inheritance
{
    private account acc;                                                                            //Create variable account
    
    public insertacc(account acc)                                                                  //Constructor
    {
        super();                                                                                    //call parent constructor (connectionDB)
        this.acc = acc;                                                                             //Variable acc = acc in parameter4
        
        // Semak jika username telah wujud
        boolean existed = false;                                                                    //Set boolean existed value to false
            
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
                existed = true;                                                                     //Set boolean existed value to true
            }
                
            if (existed)                                                                            //if  existed value is true , do
            {
                System.out.println("------------------------");                                     //print line
                System.out.println("Username already exists! Please try again!");                   //Print Warning
                CarRentalBookingSystem mainpage = new CarRentalBookingSystem();                     //Call CarRentalBookingSystem class
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
                
                System.out.println("------------------------");                                     //print line
                System.out.println("Welcome new user! " + acc.getUsername());                        //print Welcome
                dashboard Dashboard = new dashboard(acc.getUsername());                              //Call dashboard class with pass parimeter
            }
        }
        catch (Exception e)                                                                         //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                         //print error
            System.exit(1);                                                                         //exit program
        }
    }
    
}
