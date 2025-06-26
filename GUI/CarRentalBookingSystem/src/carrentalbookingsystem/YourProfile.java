package carrentalbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

class IcNumber
{
    private String ic;
    public String readIc(String ic)
    {
        this.ic = ic;
        return ic;
    }
}

class Bfday extends IcNumber
{
    private String[] monthNames = 
    {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    private String Day , MonthNames , birthday;
    private int month , year;
    public String readIc(String ic)
    {
        Day = ic.substring(4, 6);
        month = Integer.parseInt(ic.substring(2, 4));
        MonthNames = monthNames[month - 1];
        year = Integer.parseInt(ic.substring(0, 2)); 
        if (year >= 0 && year <= 25)
        {
            year = year + 2000; 
        } 
        else
        {
            year = year + 1900;
        }
        birthday = Day + " " + MonthNames + " " + year;
        return birthday;
    }
}

class Gender extends IcNumber
{
    private int gender;
    public String readIc(String ic)
    {
        gender = Integer.parseInt(ic.substring(11));
        gender %=2 ; 
        if (gender == 0)  
        {
            return "Female";
        }
        else          
        {
            return "Male";
        }
    }
}

class YourProfile extends JFrame 
{
    private JLabel titleLabel , usernameLabel , phoneLabel , icLabel , passwordLabel , 
            usernameTxt , icTxt , genderLabel, genderTxt , BfdayLabel, BfdayTxt;
    private JTextField phoneTextField , passwordTextField;
    private JButton backButton , submitButton;
    private String Username , newname , phone , ic , password , updatename , updateic , updatePhone , updatePassword;
    
    public YourProfile(String Username)
    {
        this.Username = Username;
        
        IcNumber IcNum = new IcNumber();
        IcNumber Bfday = new Bfday();
        IcNumber Gender = new Gender();
        
        setTitle("YOUR PROFIL - CAR RENTAL BOOKING SYSTEM");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

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

        // read data
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
        
        phoneTextField = new JTextField(phone);
        phoneTextField.setBounds(200, 130, 200, 30);
        add(phoneTextField);
        
        icLabel = new JLabel("IC :");
        icLabel.setBounds(100, 160, 200, 30);
        add(icLabel);
        
        icTxt = new JLabel(IcNum.readIc(ic));
        icTxt.setBounds(200, 160, 200, 30);
        add(icTxt);
        
        genderLabel = new JLabel("Gender :");
        genderLabel.setBounds(100, 190, 200, 30);
        add(genderLabel);
        
        genderTxt = new JLabel(Gender.readIc(ic));
        genderTxt.setBounds(200, 190, 200, 30);
        add(genderTxt);
        
        BfdayLabel = new JLabel("Birthday :");
        BfdayLabel.setBounds(100, 220, 200, 30);
        add(BfdayLabel);
        
        BfdayTxt = new JLabel(Bfday.readIc(ic));
        BfdayTxt.setBounds(200, 220, 200, 30);
        add(BfdayTxt);
        
        passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds(100, 250, 200, 30);
        add(passwordLabel);
        
        passwordTextField = new JTextField(password);
        passwordTextField.setBounds(200, 250, 200, 30);
        add(passwordTextField);
        
        submitButton = new JButton("Update Profile");
        submitButton.setBounds(120, 310, 250, 25);
        add(submitButton);
        
        setVisible(true);
        
        backButton.addActionListener(e -> {
            DashboardGUI Dashboard = new DashboardGUI(Username);
            setVisible(false);
        });
        
        submitButton.addActionListener(e -> {
            updatename = usernameTxt.getText();
            updateic = icTxt.getText();
            updatePhone = phoneTextField.getText();
            updatePassword = passwordTextField.getText();
            updateProfil update = new updateProfil(Username , updatename , updateic , updatePhone , updatePassword);
            setVisible(false);
        });
    }

    private void readData() 
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CarRental", "root", "");
            PreparedStatement Getuser = conn.prepareStatement(
                "SELECT * FROM users WHERE username = ? "
            );
            Getuser.setString(1, Username);
            ResultSet rsUser = Getuser.executeQuery();
            while (rsUser.next()) 
            {
                newname = rsUser.getString("username");
                phone = rsUser.getString("phone");
                ic = rsUser.getString("ic");
                password = rsUser.getString("password");
            }
        }
         catch (SQLException e) {
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