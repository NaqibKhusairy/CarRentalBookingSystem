package carrentalbookingsystem;                                                                         //package Name

import java.sql.*;                                                                                      //import all library in java.sql

public class BookedCar extends connectionDB                                                             //class name with inheritance
{
    private String Username;                                                                            //Private Sting Variable

    public BookedCar(String Username)                                                                   //Constructor with parameter
    {
        super();                                                                                        //call parent constructor (connectionDB)
        this.Username = Username;                                                                       //Variable Username = Username in parameter
        displayBookings();                                                                              //Call Method displayBookings
    }

    public void displayBookings()                                                                       //displayBookings Void Method 
    {
        System.out.printf("%-10s %-13s %-8s %-15s %-10s %-15s %-10s %-10s\n",
                "Username", "Phone", "Gender", "Jenama Kereta", "No Plate", "Tarikh",
                "Masa Start", "Masa Tamat");                                                            //Print dalam bentuk Table format
        System.out.println("----------------------------------------------------"
                + "-------------------------------------------");                                       //line
        try                                                                                             //Exception Handling for finding errors
        {
            //Connect to database
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);                      //Establish a database connection		
            //get all bookings data
            PreparedStatement getalldata = conn.prepareStatement(
                "SELECT * FROM bookings"
            );
            ResultSet rs = getalldata.executeQuery();                                                   //run getalldata Query
            
            // Loop semua data dari database past to Book.java and print
            while (rs.next()) 
            {
                //call Book class and pass parameter
                Book book = new Book(rs.getString("name") , rs.getString("phone") ,
                    rs.getString("ic") , rs.getString("carModel") , 
                    rs.getString("plateNumber") , rs.getString("date") , 
                    rs.getString("startTime") , rs.getString("endTime"));                               
                
                System.out.printf("%-10s %-13s %-8s %-15s %-10s %-15s %-10s %-10s\n",                   //Print dalam bentuk Table format
                book.getName(),                                                                         //call getName method dari book class 
                book.getPhone(),                                                                        //call getPhone method dari book class 
                book.getGender(),                                                                       //call getGender method dari book class 
                book.getCarModel(),                                                                     //call getCarModel method dari book class 
                book.getPlateNumber(),                                                                  //call getPlateNumber method dari book class 
                book.getDate(),                                                                         //call getDate method dari book class 
                book.getStartTime(),                                                                    //call getStartTime method dari book class 
                book.getEndTime());                                                                     //call getEndTime method dari book class 
            System.out.println("----------------------------------------------------"
                    + "-------------------------------------------");                                   //line
            }

            conn.close();                                                                               //close connection
        }
        catch (Exception e)                                                                             //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                             //print error
            System.exit(1);                                                                             //exit program
        }
    }
}
