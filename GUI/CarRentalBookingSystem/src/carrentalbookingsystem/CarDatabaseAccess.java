package carrentalbookingsystem;

import javax.swing.DefaultComboBoxModel;
import java.sql.*;

public class CarDatabaseAccess implements CarDataAccess {

    private final String DB_URL = "jdbc:mysql://localhost:3306/CarRental";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    @Override
    public DefaultComboBoxModel<String> getAllCarBrands() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-- Select Car --");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT carModel FROM car");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                model.addElement(rs.getString("carModel"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public DefaultComboBoxModel<String> getPlatesByBrand(String jenama) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-- Select Plate --");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT plateNumber FROM car WHERE carModel = ?")) {

            stmt.setString(1, jenama);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addElement(rs.getString("plateNumber"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
}
