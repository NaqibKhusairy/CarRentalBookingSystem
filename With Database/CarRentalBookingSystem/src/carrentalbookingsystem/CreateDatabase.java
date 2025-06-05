package carrentalbookingsystem;                                                                             //package Name

import java.sql.*;                                                                                          //import all library in java.sql

public class CreateDatabase extends connectionDB                                                            //class name with inheritance
{

    public CreateDatabase()                                                                                 //Constructor
    {
        super();                                                                                            //call parent constructor (connectionDB)
        try                                                                                                 //Exception Handling for finding errors
        {
            //Connect to database
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);                          //Establish a database connection

            //create table users in database
            String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    username VARCHAR(250),
                    phone VARCHAR(250),
                    ic VARCHAR(12),
                    password VARCHAR(250)
                )""";
            stmt = conn.createStatement();                                                                  //Create a Statement object
            stmt.executeUpdate(createUsersTable);                                                           //Execute SQL statement

            //create table bookings in database if not Exists
            String createBookingsTable = """
                CREATE TABLE IF NOT EXISTS bookings (
                    name VARCHAR(250),
                    phone VARCHAR(250),
                    ic VARCHAR(12),
                    carModel VARCHAR(250),
                    plateNumber VARCHAR(10),
                    date DATE,
                    startTime TIME,
                    endTime TIME
                )""";
            stmt.executeUpdate(createBookingsTable);                                                        //Execute SQL statement

            //checking User Exists
            PreparedStatement checkUser = conn.prepareStatement(
                "SELECT COUNT(*) FROM users WHERE username = ? AND ic = ?"
            );

            //insert data from ArayData in table users
            PreparedStatement insertUser = conn.prepareStatement
            (
                "INSERT INTO users (username, phone, ic, password) VALUES (?, ?, ?, ?)"
            );

            ArrayData arrayData = new ArrayData();                                                          //call ArrayData class
            String[][] acc = arrayData.acc();                                                               //Get acc array from acc method in ArrayData class
            String[][] booking = arrayData.booking();                                                       //Get booking array from booking method in ArrayData class
            
            //Get Data From Array dari acc method dalam ArrayData class
            //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari acc method dalam ArrayData Class dengan kiraan i+1
            for (int i = 0; i < acc.length; i++) {
                checkUser.setString(1, acc[i][0]);                                                          //value checking column username get from acc row [i] col[0]
                checkUser.setString(2, acc[i][2]);                                                          //value checking column ic get from acc row [i] col[2]
                
                ResultSet rs = checkUser.executeQuery();                                                    //Execute checking SQL statement
                rs.next();                                                                                  //Move to first result row
                
                if (rs.getInt(1) == 0)                                                                      //if data not existsing , do
                {
                    insertUser.setString(1, acc[i][0]);                                                     //value insert column username get from acc row [i] col[0]
                    insertUser.setString(2, acc[i][1]);                                                     //value insert column phone get from acc row [i] col[1]
                    insertUser.setString(3, acc[i][2]);                                                     //value insert column ic get from acc row [i] col[2]
                    insertUser.setString(4, acc[i][3]);                                                     //value insert column password get from acc row [i] col[3]
                    insertUser.executeUpdate();                                                             //Execute SQL statement
                }

                rs.close();                                                                                 //Close ResultSet
            }

            //checking Booking Exists
            PreparedStatement checkBooking = conn.prepareStatement(
                "SELECT COUNT(*) FROM bookings WHERE carModel = ? AND plateNumber = ? AND date = ? AND startTime = ? AND endTime = ?"
            );

            //fill data from ArayData in table bookings
            PreparedStatement insertBooking = conn.prepareStatement
            (
                "INSERT INTO bookings (name, phone, ic, carModel, plateNumber, date, startTime, endTime) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            
            //Get Data From Array dari booking method dalam ArrayData class
            //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari booking method dalam ArrayData Class dengan kiraan i+1
            for (int i = 0; i < booking.length; i++) 
            {
                checkBooking.setString(1, booking[i][3]);                                                   //value checking column carModel get from acc row [i] col[3]
                checkBooking.setString(2,  booking[i][4]);                                                  //value checking column plateNumber get from acc row [i] col[4]
                checkBooking.setDate(3, Date.valueOf(booking[i][5]));                                       //value checking column date get from acc row [i] col[5]
                checkBooking.setTime(4, Time.valueOf(booking[i][6] + ":00"));                               //value checking column startTime get from acc row [i] col[6]
                checkBooking.setTime(5, Time.valueOf(booking[i][7] + ":00"));                               //value checking column endTime get from acc row [i] col[7]

                ResultSet rs = checkBooking.executeQuery();                                                 //Execute checking SQL statement
                rs.next();                                                                                  //Move to first result row

                if (rs.getInt(1) == 0)                                                                      //if data not existsing , do
                {
                    insertBooking.setString(1, booking[i][0]);                                              //value insert column username get from booking row [i] col[0]
                    insertBooking.setString(2, booking[i][1]);                                              //value insert column phone get from booking row [i] col[1]
                    insertBooking.setString(3, booking[i][2]);                                              //value insert column ic get from booking row [i] col[2]
                    insertBooking.setString(4, booking[i][3]);                                              //value insert column carModel get from booking row [i] col[3]
                    insertBooking.setString(5, booking[i][4]);                                              //value insert column plateNumber get from booking row [i] col[4]
                    insertBooking.setDate(6, Date.valueOf(booking[i][5]));                                  //value insert column date get from booking row [i] col[5]
                    insertBooking.setTime(7, Time.valueOf(booking[i][6] + ":00"));                          //value insert column startTime get from booking row [i] col[6]
                    insertBooking.setTime(8, Time.valueOf(booking[i][7] + ":00"));                          //value insert column endTime get from booking row [i] col[7]
                    insertBooking.executeUpdate();                                                          //Execute SQL statement
                }

                rs.close();                                                                                 //Close ResultSet
            }

            conn.close();                                                                                   //close the connection
        } 
        catch (Exception e)                                                                                 //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                                 //print array
            System.exit(1);                                                                                 //exit program
        }
    }
}
