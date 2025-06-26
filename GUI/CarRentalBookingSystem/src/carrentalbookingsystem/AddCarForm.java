package carrentalbookingsystem;

import javax.swing.*;
import java.awt.*;

public class AddCarForm extends JFrame  {
    private JLabel titleLabel , carModelLabel , plateNumberLabel;
    private JTextField carModelTextField , plateNumberTextField;
    private JButton backButton , submitButton;
    private String CarModel , PlateNumber;
    
    public AddCarForm()
    {
        setSize(500, 353);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ADD CAR - CAR RENTAL BOOKING SYSTEM");
        
        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        // Back button - top left
        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 25);
        add(backButton);

        // Title - center top
        titleLabel = new JLabel("YOUR PROFIL");
        titleLabel.setFont(new Font("ARIAL", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(100, 30, 300, 60);
        add(titleLabel);
        
        carModelLabel = new JLabel("Car Model :");
        carModelLabel.setBounds(100, 100, 200, 30);
        add(carModelLabel);
        
        carModelTextField = new JTextField();
        carModelTextField.setBounds(200, 100, 200, 30);
        add(carModelTextField);
        
        plateNumberLabel = new JLabel("Plate Number :");
        plateNumberLabel.setBounds(100, 140, 200, 30);
        add(plateNumberLabel);
        
        plateNumberTextField = new JTextField();
        plateNumberTextField.setBounds(200, 140, 200, 30);
        add(plateNumberTextField);
        
        submitButton = new JButton("Add Car");
        submitButton.setBounds(120, 200, 250, 25);
        add(submitButton);
        
        setVisible(true);
        
        backButton.addActionListener(e -> {
            new AdminGUI();
            setVisible(false);
        });
        
        submitButton.addActionListener(e -> {
            CarModel = carModelTextField.getText();
            PlateNumber = plateNumberTextField.getText();
            new addnewCar(CarModel , PlateNumber);
            setVisible(false);
        });
    }
    
    //Inner class untuk gradient panel
    class GradientPanel extends JPanel 
    {
        @Override
        protected void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, Color.decode("#009e9f"), 0, getHeight(), Color.decode("#224d93"));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}