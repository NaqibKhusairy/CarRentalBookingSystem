package carrentalbookingsystem;                                                                         //package Name

import java.sql.*;                                                                                      //import all library in java.sql
import java.util.Scanner;                                                                               //inport library scanner

public class BookingCar extends connectionDB                                                            //class name with inheritance
{
    private String Username , newname , phone, ic , Jenama , NoPlate , Tarikh ,MasaStart , MasaTamat;   //Private Sting Variable
    
    public BookingCar(String Username)                                                                  //Constructor with parameter
    {
        super();                                                                                        //call parent constructor (connectionDB)
        this.Username = Username;                                                                       //Variable Username = Username in parameter
        Scanner BookingInput = new Scanner(System.in); 
        
        try                                                                                             //Exception Handling for finding errors
        {
            //Connect to database
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);                      //Establish a database connection		
            //get all users data dimana username in database = username
            PreparedStatement Getuser = conn.prepareStatement(
                "SELECT * FROM users WHERE username = ? "
            );
            Getuser.setString(1, Username);                                                             //value checking column username get from Username
            ResultSet rsUser = Getuser.executeQuery();                                                  //run getalldata Query
            
            // Loop semua data dari database dimana name in database = Username
            while (rsUser.next()) 
            {
                newname = rsUser.getString("username");                                                 //set newname = data get from col username in database
                phone = rsUser.getString("phone");                                                      //set phone = data get from col phone in database
                ic = rsUser.getString("ic");                                                            //set ic = data get from col ic in database
                System.out.println("Username      : " + newname);                                       //print newname
                System.out.println("Phone         : " + phone);                                         //print phone
                System.out.println("IC            : " + ic);                                            //print ic
            }
        
            System.out.print("Jenama Kereta : ");                                                       //print Jenama Kereta
            Jenama = BookingInput.nextLine();                                                           //Jenama = input from user
            System.out.print("No Plate      : ");                                                       //print No Plate
            NoPlate = BookingInput.nextLine();                                                          //NoPlate = input from user
            System.out.print("Tarikh        : ");                                                       //print Tarikh
            Tarikh = BookingInput.nextLine();                                                           //Tarikh = input from user
            System.out.print("Masa Start    : ");                                                       //print Masa Start
            MasaStart = BookingInput.nextLine();                                                        //MasaStart = input from user
            System.out.print("Masa Tamat    : ");                                                       //print Masa Tamat
            MasaTamat = BookingInput.nextLine();                                                        //MasaTamat = input from user

            //Check if booking exists with overlapping time
            PreparedStatement checkBooking = conn.prepareStatement(
                "SELECT COUNT(*) FROM bookings " +
                "WHERE carModel = ? AND plateNumber = ? AND date = ? " +
                "AND (? < endTime AND ? > startTime)"
            );

            //fill data In table bookings
            PreparedStatement insertBooking = conn.prepareStatement
            (
                "INSERT INTO bookings (name, phone, ic, carModel, plateNumber, date, startTime, endTime) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            
            checkBooking.setString(1, Jenama);                                                          //value checking column carModel get from Jenama
            checkBooking.setString(2, NoPlate);                                                         //value checking column plateNumber get from NoPlate
            checkBooking.setDate(3, Date.valueOf(Tarikh));                                              //value checking column date get from Tarikh
            checkBooking.setTime(4, Time.valueOf(MasaStart + ":00"));                                   //value checking column startTime get from MasaStart
            checkBooking.setTime(5, Time.valueOf(MasaTamat + ":00"));                                   //value checking column endTime get from MasaTamat

            ResultSet rs = checkBooking.executeQuery();                                                 //Execute checking SQL statement
            rs.next();                                                                                  //Move to first result row

            if (rs.getInt(1) == 0)                                                                      //if data not existsing , do
            {
                insertBooking.setString(1, newname);                                                    //value insert column username get from newname
                insertBooking.setString(2, phone);                                                      //value insert column phone get from phone
                insertBooking.setString(3, ic);                                                         //value insert column ic get from ic
                insertBooking.setString(4, Jenama);                                                     //value insert column carModel get from Jenama
                insertBooking.setString(5, NoPlate);                                                    //value insert column plateNumber get from NoPlate
                insertBooking.setDate(6, Date.valueOf(Tarikh));                                         //value insert column date get from Tarikh
                insertBooking.setTime(7, Time.valueOf(MasaStart + ":00"));                              //value insert column startTime get from MasaStart
                insertBooking.setTime(8, Time.valueOf(MasaTamat + ":00"));                              //value insert column endTime get from MasaTamat
                insertBooking.executeUpdate();                                                          //Execute SQL statement
        
                System.out.println("------------------------");                                         //print line
                System.out.println(newname + " Booking Sucsessfull");                                   //print Booking Sucsessfull
            } 
            else                                                                                        //if existed , do
            {
                System.out.println("Booking already exists for this time and car.");                    //Print Alert
            }

            rsUser.close();                                                                             //Close ResultSet
            rs.close();                                                                                 //Close ResultSet
            conn.close();                                                                               //close the connection
        }
        catch (Exception e)                                                                             //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                             //print error
            System.exit(1);                                                                             //exit program
        }
    }
}
