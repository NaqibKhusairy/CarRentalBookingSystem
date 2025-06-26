package carrentalbookingsystem;

import javax.swing.DefaultComboBoxModel;

public interface CarDataAccess {
    DefaultComboBoxModel<String> getAllCarBrands();
    DefaultComboBoxModel<String> getPlatesByBrand(String jenama);
}
