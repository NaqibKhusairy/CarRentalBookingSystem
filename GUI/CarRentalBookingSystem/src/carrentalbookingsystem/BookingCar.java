package carrentalbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingCar extends JFrame {
    private JLabel titleLabel, usernameLabel, phoneLabel, icLabel, jenamaLabel,
            plateLabel, tarikhLabel, masaStartLabel, masaTamatLabel, usernameTxt,
            phoneTxt, icTxt;
    private JButton backButton, submitButton;
    private JComboBox<String> jenama_option, plate_option, date_option, masaStart_option, masaTamat_option;

    private final String[] options_empty = {"-- Select Car First --"};
    private final String[] optionsmasa_empty = {"-- Select Start Time First --"};
    private final String[] options_masaTamat = {"-- Select End Time --"};

    private String username, newname, phone, ic, jenama, NoPlate, Tarikh, MasaStart, MasaTamat;

    private CarDataAccess carData = new CarDatabaseAccess();

    public BookingCar(String username) {
        this.username = username;

        setTitle("BOOKING CAR - CAR RENTAL BOOKING SYSTEM");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 25);
        add(backButton);

        titleLabel = new JLabel("BOOKING CAR");
        titleLabel.setFont(new Font("ARIAL", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(100, 30, 300, 60);
        add(titleLabel);

        readData();

        usernameLabel = new JLabel("Username :");
        usernameLabel.setBounds(100, 100, 200, 30);
        add(usernameLabel);

        usernameTxt = new JLabel(newname);
        usernameTxt.setBounds(200, 100, 200, 30);
        add(usernameTxt);

        phoneLabel = new JLabel("Phone :");
        phoneLabel.setBounds(100, 130, 200, 30);
        add(phoneLabel);

        phoneTxt = new JLabel(phone);
        phoneTxt.setBounds(200, 130, 200, 30);
        add(phoneTxt);

        icLabel = new JLabel("IC :");
        icLabel.setBounds(100, 160, 200, 30);
        add(icLabel);

        icTxt = new JLabel(ic);
        icTxt.setBounds(200, 160, 200, 30);
        add(icTxt);

        jenamaLabel = new JLabel("Jenama Kereta :");
        jenamaLabel.setBounds(100, 190, 200, 30);
        add(jenamaLabel);

        jenama_option = new JComboBox<>(carData.getAllCarBrands());
        jenama_option.setBounds(200, 190, 200, 30);
        add(jenama_option);

        plateLabel = new JLabel("No Plate :");
        plateLabel.setBounds(100, 220, 200, 30);
        add(plateLabel);

        plate_option = new JComboBox<>(options_empty);
        plate_option.setBounds(200, 220, 200, 30);
        add(plate_option);

        tarikhLabel = new JLabel("Tarikh :");
        tarikhLabel.setBounds(100, 250, 200, 30);
        add(tarikhLabel);

        date_option = new JComboBox<>();
        date_option.addItem("-- Select Date --");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i <= 365; i++) {
            LocalDate futureDate = today.plusDays(i);
            date_option.addItem(futureDate.format(formatter));
        }
        date_option.setBounds(200, 250, 200, 30);
        add(date_option);

        masaStartLabel = new JLabel("Masa Start :");
        masaStartLabel.setBounds(100, 280, 200, 30);
        add(masaStartLabel);

        masaStart_option = new JComboBox<>();
        masaStart_option.addItem("-- Select Start Time --");
        for (int hour = 8; hour <= 20; hour++) {
            for (int min = 0; min < 60; min += 30) {
                masaStart_option.addItem(String.format("%02d:%02d", hour, min));
            }
        }
        masaStart_option.setBounds(200, 280, 200, 30);
        add(masaStart_option);

        masaTamatLabel = new JLabel("Masa Tamat :");
        masaTamatLabel.setBounds(100, 310, 200, 30);
        add(masaTamatLabel);

        masaTamat_option = new JComboBox<>(optionsmasa_empty);
        masaTamat_option.setBounds(200, 310, 200, 30);
        add(masaTamat_option);

        submitButton = new JButton("Submit");
        submitButton.setBounds(120, 360, 250, 25);
        add(submitButton);

        setVisible(true);

        backButton.addActionListener(e -> {
            DashboardGUI dashboard = new DashboardGUI(username);
            setVisible(false);
        });

        jenama_option.addActionListener(e -> {
            String selectedJenama = (String) jenama_option.getSelectedItem();
            if (selectedJenama == null || selectedJenama.equals("-- Select Car --")) {
                plate_option.setModel(new DefaultComboBoxModel<>(options_empty));
            } else {
                plate_option.setModel(carData.getPlatesByBrand(selectedJenama));
            }
        });

        masaStart_option.addActionListener((ActionEvent e) -> {
            MasaStart = (String) masaStart_option.getSelectedItem();
            if (MasaStart == null || MasaStart.equals("-- Select Start Time --")) {
                masaTamat_option.setModel(new DefaultComboBoxModel<>(optionsmasa_empty));
            } else {
                int hourStart = Integer.parseInt(MasaStart.substring(0, 2));
                int minStart = Integer.parseInt(MasaStart.substring(3, 5));

                DefaultComboBoxModel<String> options = new DefaultComboBoxModel<>();
                for (int hour = hourStart; hour <= 23; hour++) {
                    for (int min = (hour == hourStart ? minStart + 30 : 0); min < 60; min += 30) {
                        options.addElement(String.format("%02d:%02d", hour, min));
                    }
                }
                options.addElement("00:00");
                masaTamat_option.setModel(options);
            }
        });

        submitButton.addActionListener(e -> {
            jenama = (String) jenama_option.getSelectedItem();
            NoPlate = (String) plate_option.getSelectedItem();
            Tarikh = (String) date_option.getSelectedItem();
            MasaStart = (String) masaStart_option.getSelectedItem();
            MasaTamat = (String) masaTamat_option.getSelectedItem();

            if (jenama.equals("-- Select Car --") || NoPlate.equals("-- Select Plate --") ||
                    Tarikh.equals("-- Select Date --") || MasaStart.equals("-- Select Start Time --") ||
                    MasaTamat.equals("-- Select End Time --") || MasaTamat.equals("-- Select Start Time First --")) {

                JOptionPane.showMessageDialog(null, "Please enter all fields!", "Missing Info", JOptionPane.WARNING_MESSAGE);
            } else {
                BookingProcess booking = new BookingProcess(username, newname, phone, ic, jenama, NoPlate, Tarikh, MasaStart, MasaTamat);
                setVisible(false);
            }
        });
    }

    private void readData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRental", "root", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                newname = rs.getString("username");
                phone = rs.getString("phone");
                ic = rs.getString("ic");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Color color1 = Color.decode("#009e9f");
            Color color2 = Color.decode("#224d93");
            int width = getWidth();
            int height = getHeight();
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
}
