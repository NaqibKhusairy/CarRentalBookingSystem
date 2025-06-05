package carrentalbookingsystem;                                                                         //package Name

import java.sql.*;                                                                                      //import all library in java.sql
import java.util.Scanner;                                                                               //import library scanner

public class CancelCustBooking extends connectionDB                                                     //class name with inheritance
{
    private String Username , name, phone, ic, Jenama, NoPlate, Tarikh, MasaStart, MasaTamat;           //Private Sting Variable
    boolean found = false;                                                                              //Boolean variable with value

    public CancelCustBooking(String Username)                                                           //Constructor with parameter
    {
        super();                                                                                        //call parent constructor (connectionDB)
        this.Username = Username;                                                                       //Variable Username = Username in parameter
        Scanner CancelInput = new Scanner(System.in);                                                   //set scanner variable to CancelInput

        System.out.print("Customer Username : ");                                                       //print Customer Username
        name = CancelInput.nextLine();                                                                  //name = input from user
        System.out.print("Customer Phone    : ");                                                       //print Customer Phone
        phone = CancelInput.nextLine();                                                                 //phone = input from user
        System.out.print("Customer IC       : ");                                                       //print Customer IC
        ic = CancelInput.nextLine();                                                                    //ic = input from user
        System.out.print("Jenama Kereta     : ");                                                       //print Jenama Kereta
        Jenama = CancelInput.nextLine();                                                                //Jenama = input from user
        System.out.print("No Plate          : ");                                                       //print No Plate
        NoPlate = CancelInput.nextLine();                                                               //NoPlate = input from user
        System.out.print("Tarikh            : ");                                                       //print Tarikh
        Tarikh = CancelInput.nextLine();                                                                //Tarikh = input from user
        System.out.print("Masa Start        : ");                                                       //print Masa Start
        MasaStart = CancelInput.nextLine();                                                             //MasaStart = input from user
        System.out.print("Masa Tamat        : ");                                                       //print Masa Tamat
        MasaTamat = CancelInput.nextLine();                                                             //MasaTamat = input from user

        try                                                                                             //Exception Handling for finding errors
        {
            //Connect to database
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);                      //Establish a database connection
            
            //delete from database WHARE name = ? AND carModel = ? AND plateNumber = ? AND date = ? AND startTime = ? AND endTime = ? 
            PreparedStatement deleteBooking = conn.prepareStatement(
                "DELETE FROM bookings WHERE name = ? AND phone = ? AND ic = ? "
                + "AND carModel = ? AND plateNumber = ? AND date = ? "
                + "AND startTime = ? AND endTime = ?"
            );
            deleteBooking.setString(1, name);                                                           //value checking column name get from name
            deleteBooking.setString(2, phone);                                                          //value checking column phone get from phone
            deleteBooking.setString(3, ic);                                                             //value checking column ic get from ic
            deleteBooking.setString(4, Jenama);                                                         //value checking column carModel get from Jenama
            deleteBooking.setString(5, NoPlate);                                                        //value checking column plateNumber get from NoPlate
            deleteBooking.setDate(6, Date.valueOf(Tarikh));                                             //value checking column date get from Tarikh
            deleteBooking.setTime(7, Time.valueOf(MasaStart + ":00"));                                  //value checking column startTime get from MasaStart
            deleteBooking.setTime(8, Time.valueOf(MasaTamat + ":00"));                                  //value checking column endTime get from MasaTamat
                
            int deleted = deleteBooking.executeUpdate();                                                //run Delete query and new value for int delete if sucsess
                
            if (deleted > 0)                                                                            //if value if deleted more than 0 , do
            {
                found = true;                                                                           //set found value to true
            }

            conn.close();                                                                               //close the connection
        }
        catch (Exception e)                                                                             //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                             //print error
            System.exit(1);                                                                             //exit program
        }

        if (found)                                                                                      //if found value if true , do
        {                                                                            
            System.out.println(name + "'s booking successfully canceled.");                             //Print successfully canceled
        } 
        else                                                                                            //Selain itu , do
        {                                        
            System.out.println(name + "'s Booking for this time and car not found");                    //Print Alert
        }
    }
}
