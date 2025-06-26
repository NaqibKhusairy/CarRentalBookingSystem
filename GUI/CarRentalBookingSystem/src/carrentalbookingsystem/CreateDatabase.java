package carrentalbookingsystem;

import java.sql.*;

public class CreateDatabase extends connectionDB
{

    public CreateDatabase()
    {
        super(); 
        try
        {
            
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);
            
            String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    username VARCHAR(250),
                    phone VARCHAR(250),
                    ic VARCHAR(12),
                    password VARCHAR(250)
                )""";
            stmt = conn.createStatement();
            stmt.executeUpdate(createUsersTable);
            
            String createBookingsTable = """
                CREATE TABLE IF NOT EXISTS bookings (
                    name VARCHAR(250),
                    phone VARCHAR(250),
                    ic VARCHAR(12),
                    carModel VARCHAR(250),
                    plateNumber VARCHAR(10),
                    date DATE,
                    startTime TIME,
                    endTime TIME,
                    payment VARCHAR(250)
                )""";
            stmt.executeUpdate(createBookingsTable);
            
            String createCarTable = """
                CREATE TABLE IF NOT EXISTS car (
                    carModel VARCHAR(250),
                    plateNumber VARCHAR(250)
                )""";
            stmt.executeUpdate(createCarTable);
            
            PreparedStatement checkUser = conn.prepareStatement(
                "SELECT COUNT(*) FROM users WHERE username = ? AND ic = ?"
            );
            
            PreparedStatement insertUser = conn.prepareStatement
            (
                "INSERT INTO users (username, phone, ic, password) VALUES (?, ?, ?, ?)"
            );

            ArrayData arrayData = new ArrayData();
            String[][] acc = arrayData.getacc();
            String[][] booking = arrayData.getbooking();
            String[][] car = arrayData.getcar();
            
            for (int i = 0; i < acc.length; i++) {
                checkUser.setString(1, acc[i][0]);
                checkUser.setString(2, acc[i][2]);

                ResultSet rs = checkUser.executeQuery();
                rs.next();

                if (rs.getInt(1) == 0)
                {
                    insertUser.setString(1, acc[i][0]);
                    insertUser.setString(2, acc[i][1]);
                    insertUser.setString(3, acc[i][2]);
                    insertUser.setString(4, acc[i][3]);
                    insertUser.executeUpdate();
                }

                rs.close();
            }
            
            Statement checkStmt = conn.createStatement();
            ResultSet rsCheck = checkStmt.executeQuery("SELECT COUNT(*) FROM bookings");
            rsCheck.next();
            int bookingCount = rsCheck.getInt(1);
            rsCheck.close();
            checkStmt.close();

            if (bookingCount == 0) {
                
                PreparedStatement checkBooking = conn.prepareStatement(
                    "SELECT COUNT(*) FROM bookings WHERE carModel = ? AND plateNumber = ? AND date = ? AND startTime = ? AND endTime = ?"
                );

                PreparedStatement insertBooking = conn.prepareStatement(
                    "INSERT INTO bookings (name, phone, ic, carModel, plateNumber, date, startTime, endTime , payment) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );

                for (int i = 0; i < booking.length; i++) {
                    checkBooking.setString(1, booking[i][3]);
                    checkBooking.setString(2,  booking[i][4]);
                    checkBooking.setDate(3, Date.valueOf(booking[i][5]));
                    checkBooking.setTime(4, Time.valueOf(booking[i][6] + ":00"));
                    checkBooking.setTime(5, Time.valueOf(booking[i][7] + ":00"));

                    ResultSet rs = checkBooking.executeQuery();
                    rs.next();

                    if (rs.getInt(1) == 0)
                    {
                        insertBooking.setString(1, booking[i][0]);
                        insertBooking.setString(2, booking[i][1]);
                        insertBooking.setString(3, booking[i][2]);
                        insertBooking.setString(4, booking[i][3]);
                        insertBooking.setString(5, booking[i][4]);
                        insertBooking.setDate(6, Date.valueOf(booking[i][5]));
                        insertBooking.setTime(7, Time.valueOf(booking[i][6] + ":00"));
                        insertBooking.setTime(8, Time.valueOf(booking[i][7] + ":00"));
                        insertBooking.setString(9, "unpaid");
                        insertBooking.executeUpdate();
                    }

                    rs.close();
                }
            }
            
            Statement carcheckStmt = conn.createStatement();
            ResultSet carrsCheck = carcheckStmt.executeQuery("SELECT COUNT(*) FROM car");
            carrsCheck.next();
            int carCount = carrsCheck.getInt(1);
            carrsCheck.close();
            carcheckStmt.close();

            if (carCount == 0) {
                
                PreparedStatement checkCar = conn.prepareStatement(
                    "SELECT COUNT(*) FROM car WHERE carModel = ? AND plateNumber = ? "
                );

                PreparedStatement insertCar = conn.prepareStatement(
                    "INSERT INTO car (carModel, plateNumber) "
                            + "VALUES (?, ?)"
                );

                for (int i = 0; i < car.length; i++) {
                    checkCar.setString(1, car[i][0]);
                    checkCar.setString(2,  car[i][1]);

                    ResultSet rs = checkCar.executeQuery();
                    rs.next();

                    if (rs.getInt(1) == 0)
                    {
                        insertCar.setString(1, car[i][0]);
                        insertCar.setString(2, car[i][1]);
                        insertCar.executeUpdate();
                    }

                    rs.close();
                }
            }

            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
