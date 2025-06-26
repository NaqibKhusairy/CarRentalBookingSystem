package carrentalbookingsystem;

import java.sql.*;
import javax.swing.*;
import java.text.SimpleDateFormat;

class BookingProcess extends connectionDB
{
    private String Username , newname , phone, ic , Jenama , NoPlate , Tarikh ,MasaStart , MasaTamat;
    
    public BookingProcess(String Username , String newname , String phone , 
            String ic , String Jenama , String NoPlate , String Tarikh , 
            String MasaStart , String MasaTamat)
    {
        super();
        this.Username = Username;
        this.newname = newname;
        this.phone = phone;
        this.ic = ic;
        this.Jenama = Jenama;
        this.NoPlate = NoPlate;
        this.Tarikh = Tarikh;
        this.MasaStart = MasaStart;
        this.MasaTamat = MasaTamat;
        
        try
        {
            java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(Tarikh);
            Tarikh = new SimpleDateFormat("yyyy-MM-dd").format(date);
                
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);
            //Check if booking exists with overlapping time
            PreparedStatement checkBooking = conn.prepareStatement(
                "SELECT COUNT(*) FROM bookings " +
                "WHERE carModel = ? AND plateNumber = ? AND date = ? " +
                "AND (? < endTime AND ? > startTime)"
            );

            //fill data In table bookings
            PreparedStatement insertBooking = conn.prepareStatement
            (
                "INSERT INTO bookings (name, phone, ic, carModel, plateNumber, date, startTime, endTime, payment) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            
            checkBooking.setString(1, Jenama);
            checkBooking.setString(2, NoPlate);
            checkBooking.setDate(3, Date.valueOf(Tarikh));
            checkBooking.setTime(4, Time.valueOf(MasaStart + ":00"));
            checkBooking.setTime(5, Time.valueOf(MasaTamat + ":00"));
            
            ResultSet rs = checkBooking.executeQuery();
            rs.next();
            
            if (rs.getInt(1) == 0)
            {
                insertBooking.setString(1, newname);
                insertBooking.setString(2, phone);
                insertBooking.setString(3, ic);
                insertBooking.setString(4, Jenama);
                insertBooking.setString(5, NoPlate);
                insertBooking.setDate(6, Date.valueOf(Tarikh));
                insertBooking.setTime(7, Time.valueOf(MasaStart + ":00"));
                insertBooking.setTime(8, Time.valueOf(MasaTamat + ":00"));
                insertBooking.setString(9, "unpaid");
                insertBooking.executeUpdate();
                
                JOptionPane.showMessageDialog(null , newname + " Booking Sucsessfull");
                DashboardGUI Dashboard = new DashboardGUI(Username); 
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                "Booking already exists for this time and car.",
                "Booking Failed",JOptionPane.WARNING_MESSAGE);
                BookingCar booking = new BookingCar(Username);
            }
        }
        catch (Exception e)                                                                             //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                             //print error
            System.exit(1);                                                                             //exit program
        }
    }
}
