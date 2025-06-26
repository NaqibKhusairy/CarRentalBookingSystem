package carrentalbookingsystem;                                                                             //package Name

import javax.swing.*;                                                                                       //import all library from javax.swing
import java.awt.*;                                                                                          //import all library from java.awt
import java.awt.event.ActionEvent;                                                                          //import ActionEvent from event library from jawa.awt
import java.awt.event.ActionListener;                                                                       //import ActionListener from event library from jawa.awt

class LoginGUI extends JFrame                                                                               //class name with inheritance(extends to JFrame)
{
    JLabel lbl1, lbluname, lblpass;                                                                         //Object For JLabel
    JTextField txtusername;                                                                                 //Object For JTextField
    JPasswordField txtpassword;                                                                             //Object For JPasswordField
    JButton btnLogin, btnRegister;                                                                          //Object For JButton
    String Username , Password;                                                                             //String Variable
    
    public LoginGUI()                                                                                       //Constructor
    {
        setSize(500, 353);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("LOGIN - CAR RENTAL BOOKING SYSTEM");
        
        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);
        
        lbl1 = new JLabel("LOGIN");
        lbl1.setForeground(Color.black);
        lbl1.setFont(new Font("ARIAL", Font.BOLD, 40));
        lbl1.setBounds(180, 30, 300, 60);

        lbluname = new JLabel("Username :");
        lbluname.setBounds(100, 100, 80, 30);

        txtusername = new JTextField();
        txtusername.setBounds(170, 100, 200, 30);

        lblpass = new JLabel("Password :");
        lblpass.setBounds(100, 150, 80, 30);

        txtpassword = new JPasswordField();
        txtpassword.setBounds(170, 150, 200, 30);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(210, 210, 80, 25);
        
        btnRegister = new JButton("Register New Account");
        btnRegister.setBounds(170, 256, 160, 25);

        add(lbl1);
        add(lbluname);
        add(lblpass);
        add(txtusername);
        add(txtpassword);
        add(btnLogin);
        add(btnRegister);

        setVisible(true);
        
        btnLogin.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String Username = txtusername.getText();
                char[] enteredPassword = txtpassword.getPassword();
                String Password = new String(enteredPassword);
                LoginProcess login = new LoginProcess(Username , Password);
                setVisible(false);
            }
        });
        
         btnRegister.addActionListener(new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                RegisterGUI registerGUI = new RegisterGUI();
                setVisible(false);
                
            }
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