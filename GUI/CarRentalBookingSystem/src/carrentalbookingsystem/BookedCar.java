package carrentalbookingsystem;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookedCar extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JTextField searchField;
    private JButton backButton;
    private JLabel titleLabel, searchLabel;
    private String username;
    private String[] columnNames;

    public BookedCar(String username) {
        this.username = username;

        setTitle("CAR BOOKED - CAR RENTAL BOOKING SYSTEM");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 25);
        add(backButton);

        titleLabel = new JLabel("CAR BOOKED");
        titleLabel.setFont(new Font("ARIAL", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(300, 20, 300, 60);
        add(titleLabel);

        searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        searchLabel.setForeground(Color.BLACK);
        searchLabel.setBounds(600, 60, 50, 25);
        add(searchLabel);

        searchField = new JTextField("");
        searchField.setBounds(660, 60, 90, 25);
        searchField.setForeground(Color.GRAY);
        add(searchField);

        if (username.equals("admin")) {
            columnNames = new String[]{"Username", "Phone", "Gender", "Jenama Kereta", "No Plate", "Tarikh", "Masa Mula", "Masa Tamat", "Action"};
        } else {
            columnNames = new String[]{"Username", "Phone", "Gender", "Jenama Kereta", "No Plate", "Tarikh", "Masa Mula", "Masa Tamat"};
        }

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 690, 280);
        panel.add(scrollPane);

        loadData();

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        backButton.addActionListener(e -> {
            if (username.equals("admin")) {
                new AdminGUI();
                setVisible(false);
            } else {
                new DashboardGUI(username);
                setVisible(false);
            }
        });

        if (username.equals("admin")) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());

                    if (col == 8) { // Paid / Cancel column
                        String action = table.getValueAt(row, col).toString();
                        if (action.equalsIgnoreCase("Paid | Cancel")) {
                            String name = table.getValueAt(row, 0).toString();
                            String plate = table.getValueAt(row, 4).toString();

                            int choice = JOptionPane.showOptionDialog(
                                null,
                                "Choose an action:",
                                "Payment Action",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new String[]{"Mark as Paid", "Cancel Booking"},
                                "Action"
                            );

                            if (choice == 0) {
                                updatePaymentStatus(name, plate);
                            } else if (choice == 1) {
                                deleteBooking(name, plate);
                            }
                        }
                    }
                }
            });
        }

        setVisible(true);
    }

    private void loadData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRental", "root", "");
            String query = "SELECT * FROM bookings ORDER BY date ASC, startTime ASC, endTime ASC";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String tarikhFormatted = rs.getString("date");
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date"));
                    tarikhFormatted = new SimpleDateFormat("dd-MM-yyyy").format(date);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                Book book = new Book(
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("ic"),
                    rs.getString("carModel"),
                    rs.getString("plateNumber"),
                    tarikhFormatted,
                    rs.getString("startTime"),
                    rs.getString("endTime")
                );

                String custUsername = book.getName();
                String phone = book.getPhone();
                String gender = book.getGender();
                String jenama = book.getCarModel();
                String plate = book.getPlateNumber();
                String tarikh = book.getDate();
                String mula = book.getStartTime();
                String tamat = book.getEndTime();
                String payment = rs.getString("payment");

                if (username.equals("admin")) {
                    if(payment.equals("unpaid"))
                    {
                        payment = "Paid | Cancel";
                    }
                    tableModel.addRow(new Object[]{custUsername, phone, gender, jenama, plate, tarikh, mula, tamat, payment});
                } else {
                    tableModel.addRow(new Object[]{custUsername, phone, gender, jenama, plate, tarikh, mula, tamat});
                }
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    private void updatePaymentStatus(String name, String plateNumber) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRental", "root", "")) {
            String query = "UPDATE bookings SET payment = 'paid' WHERE name = ? AND plateNumber = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, plateNumber);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Payment marked as 'paid'");
            refreshTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating payment: " + e.getMessage());
        }
    }

    private void deleteBooking(String name, String plateNumber) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRental", "root", "")) {
            String query = "DELETE FROM bookings WHERE name = ? AND plateNumber = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, plateNumber);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Booking cancelled.");
            refreshTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting booking: " + e.getMessage());
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // clear table
        loadData();                // reload data
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
