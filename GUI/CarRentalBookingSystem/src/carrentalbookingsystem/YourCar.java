package carrentalbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

class YourCar extends JFrame {
    private JButton backButton, addButton;
    private JTable carTable;
    private DefaultTableModel tableModel;

    public YourCar() {
        setTitle("YOUR CAR - CAR RENTAL BOOKING SYSTEM");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);

        JPanel topPanel = new JPanel(null);
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(500, 50));

        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 25);
        topPanel.add(backButton);

        panel.add(topPanel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Car Brand", "Plate Number"};
        tableModel = new DefaultTableModel(columnNames, 0);
        carTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(carTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Add Car");
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(addButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            new AdminGUI();
            setVisible(false);
        });

        addButton.addActionListener(e -> {
            new AddCarForm();
            setVisible(false);
        });

        loadCarData();

        setVisible(true);
    }

    private void loadCarData() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRental", "root", "")) {
            PreparedStatement stmt = conn.prepareStatement("SELECT carModel, plateNumber FROM car ORDER BY carModel");
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                String jenama = rs.getString("carModel");
                String plate = rs.getString("plateNumber");
                tableModel.addRow(new Object[]{jenama, plate});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    // Gradient background
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
