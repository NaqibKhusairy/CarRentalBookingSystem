package carrentalbookingsystem;                                                                         //package Name

import javax.swing.*;                                                                                   //import all library from javax.swing
import java.awt.*;                                                                                      //import all library from java.awt
import java.awt.event.ActionEvent;                                                                      //import ActionEvent from event library from jawa.awt
import java.awt.event.ActionListener;                                                                   //import ActionListener from event library from jawa.awt

class RegisterGUI extends JFrame                                                                        //class name with inheritance(extends to JFrame)
{
    JLabel lbl1, lbluname , lblphone , lblpass , lblIC;
    JTextField txtusername , txtphone , txtIC;
    JPasswordField txtpassword;
    JButton btnLogin, btnRegister;
    
    public RegisterGUI()
    {
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("REGISTER - CAR RENTAL BOOKING SYSTEM");
        
        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);
        
        lbl1 = new JLabel("REGISTER");
        lbl1.setForeground(Color.black);
        lbl1.setFont(new Font("ARIAL", Font.BOLD, 40));
        lbl1.setBounds(140, 30, 500, 60);

        lbluname = new JLabel("Username :");
        lbluname.setBounds(100, 100, 80, 30);

        txtusername = new JTextField();
        txtusername.setBounds(200, 100, 200, 30);

        lblphone = new JLabel("Phone Number :");
        lblphone.setBounds(100, 160, 100, 30);

        txtphone = new JTextField();
        txtphone.setBounds(200, 160, 200, 30);

        lblIC = new JLabel("IC Number :");
        lblIC.setBounds(100, 220, 100, 30);

        txtIC = new JTextField();
        txtIC.setBounds(200, 220, 200, 30);

        lblpass = new JLabel("Password :");
        lblpass.setBounds(100, 280, 80, 30);

        txtpassword = new JPasswordField();
        txtpassword.setBounds(200, 280, 200, 30);
        
        btnRegister = new JButton("Register");
        btnRegister.setBounds(205, 340, 90, 25);

        btnLogin = new JButton("Back To Login");
        btnLogin.setBounds(170, 386, 160, 25);

        add(lbl1);
        add(lbluname);
        add(txtusername);
        add(lblphone);
        add(txtphone);
        add(lblIC);
        add(txtIC);
        add(lblpass);
        add(txtpassword);
        add(btnLogin);
        add(btnRegister);

        setVisible(true);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                LoginGUI login = new LoginGUI();
                setVisible(false);
            }
        });
        
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = txtusername.getText();
                String enteredPhone = txtphone.getText();
                String enteredIC = txtIC.getText();
                char[] enteredPassword = txtpassword.getPassword();
                String enteredPasswordString = new String(enteredPassword);
                RegisterProcess register = new RegisterProcess(enteredUsername , enteredPhone , enteredIC , enteredPasswordString);
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