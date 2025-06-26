package carrentalbookingsystem;

import javax.swing.*;
import java.sql.*;

class updateProfil extends connectionDB {
    private String Username, updatename, updateic, updatePhone, updatePassword;

    public updateProfil(String Username, String updatename, String updateic, String updatePhone, String updatePassword) {
        this.Username = Username;
        this.updatename = updatename;
        this.updateic = updateic;
        this.updatePhone = updatePhone;
        this.updatePassword = updatePassword;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRental", "root", "");
            String query = "UPDATE users SET username = ?, ic = ?, phone = ?, password = ? WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, updatename);
            pst.setString(2, updateic);
            pst.setString(3, updatePhone);
            pst.setString(4, updatePassword);
            pst.setString(5, updatename);

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Profile updated successfully!");
                YourProfile profil = new YourProfile(Username);
            } else {
                JOptionPane.showMessageDialog(null, "No changes made or username not found.");
            }
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Update failed: " + e.getMessage());
        }
    }
}
