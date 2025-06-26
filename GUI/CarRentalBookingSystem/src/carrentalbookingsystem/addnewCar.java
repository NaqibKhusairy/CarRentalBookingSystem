package carrentalbookingsystem;

import javax.swing.*;
import java.sql.*;

class addnewCar extends connectionDB {
    private String CarModel, PlateNumber;

    addnewCar(String CarModel, String PlateNumber) {
        super();
        this.CarModel = CarModel;
        this.PlateNumber = PlateNumber;
        
        if (CarModel.equals("") || PlateNumber.equals(""))
        {       
            JOptionPane.showMessageDialog(null, "Please enter All Field!", 
                    "Missing Info", JOptionPane.WARNING_MESSAGE);
            new AddCarForm();
        }
        else
        {
            Car newcar = new Car(CarModel , PlateNumber);
            insertnewcar ins = new insertnewcar(newcar);
        }
        
    }
    
}
