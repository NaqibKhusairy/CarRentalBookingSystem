package carrentalbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DashboardGUI  extends JFrame 
{
    private String Username;
    JLabel lbl1;
    JButton btn1 , btn2 , btn3 , btn4;
    
    public DashboardGUI(String Username)
    {
        this.Username = Username;
        
        setSize(500, 353);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DASHBOARD - CAR RENTAL BOOKING SYSTEM");
        
        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);
        
        lbl1 = new JLabel("DASHBOARD");
        lbl1.setForeground(Color.black);
        lbl1.setFont(new Font("ARIAL", Font.BOLD, 40));
        lbl1.setBounds(110, 30, 300, 60);

        btn1 = new JButton("VIEW AVAILABLE TIME");
        btn1.setBounds(150, 100, 200, 25);

        btn2 = new JButton("BOOKING CAR");
        btn2.setBounds(150, 150, 200, 25);

        btn3 = new JButton("YOUR PROFIL");
        btn3.setBounds(150, 200, 200, 25);

        btn4 = new JButton("LOGOUT");
        btn4.setBounds(150, 250, 200, 25);

        add(lbl1);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);

        setVisible(true);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                BookedCar booked = new BookedCar(Username);
                setVisible(false);
                
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                BookingCar booking = new BookingCar(Username);
                setVisible(false);
                
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                YourProfile profil = new YourProfile(Username);
                setVisible(false);
                
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
        
                if (result == JOptionPane.YES_OPTION) {
                    LoginGUI Login = new LoginGUI();
                    setVisible(false);
                }
            }
        });
    }
    // Inner class untuk gradient panel
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Warna gradient
            Color color1 = Color.decode("#009e9f");
            Color color2 = Color.decode("#224d93");

            int width = getWidth();
            int height = getHeight();

            // Gradient dari atas ke bawah
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
}