package carrentalbookingsystem;                                                                             //package Name

import javax.swing.*;                                                                                       //import all library from javax.swing

public class RegisterProcess extends connectionDB                                                           //class name with inheritance
{
    private String RegisterUsername, RegisterPhone, RegisterIC, RegisterPassword;                           //Private Sting Variable
    
    public RegisterProcess(String RegisterUsername , String RegisterPhone ,
            String RegisterIC , String RegisterPassword)                                                    //Constructor
    {
        super();
        this.RegisterUsername = RegisterUsername;
        this.RegisterPhone = RegisterPhone;
        this.RegisterIC = RegisterIC;
        this.RegisterPassword = RegisterPassword;
        
        if (RegisterUsername.equals("") || RegisterPhone.equals("") ||                                      //If RegisterUsername , RegisterPhone ,
                    RegisterIC.equals("") || RegisterPassword.equals(""))                                   //RegisterIC , RegisterPassword is null , do
            {                                                                                           
                JOptionPane.showMessageDialog(null, "Please enter All Field!", 
                        "Missing Info", JOptionPane.WARNING_MESSAGE);                                       //Print Warning\
            }
            
            if (RegisterUsername.equals("admin"))                                                           //If LoginUsername = admin , do
            {
                JOptionPane.showMessageDialog(null, "Are You Admin ? Please Login First !");                //Print Warning
                LoginGUI login = new LoginGUI();
            }
            
            account acc = new account(RegisterUsername , RegisterPhone , RegisterIC , RegisterPassword);    //call account class with pass parimeter
            insertacc ins = new insertacc(acc , RegisterUsername);
    }
}
