package carrentalbookingsystem;

import java.sql.*;
import javax.swing.*;    

class insertnewcar extends connectionDB
{
    private Car newcar;
    
    insertnewcar(Car newcar) 
    {
        this.newcar = newcar;
            
        try
        {
            
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);
            
            PreparedStatement Getuser = conn.prepareStatement(
                "SELECT plateNumber FROM car WHERE carModel = ?"
            );
            Getuser.setString(1, newcar.getCarModel());
            ResultSet Setuser = Getuser.executeQuery();
            if(Setuser.next())
            {
                JOptionPane.showMessageDialog(null,
                "Plate Number for "+ newcar.getCarModel() +" already exists. Please choose a different Plate Number.",
                "Registration Failed",JOptionPane.WARNING_MESSAGE);
                new AddCarForm();
            }
            else
            {
                
                PreparedStatement insertUser = conn.prepareStatement
                (
                    "INSERT INTO car (carModel, plateNumber) VALUES (?, ?)"
                );
                insertUser.setString(1, newcar.getCarModel());
                insertUser.setString(2, newcar.getPlateNumber());
                insertUser.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Sucsessfully add" + newcar.getCarModel() + " " + newcar.getPlateNumber());
                new YourCar();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
    
}
