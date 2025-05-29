package carrentalbookingsystem;                                                                         //package Name

import java.util.Scanner;                                                                               //import library scanner

public class login extends ArrayData                                                                    //class name with inheritance
{
    private String LoginUsername, LoginPassword , username , password;                                  //Private Sting Variable
    boolean loginSuccess = false;
    
    public login()                                                                                      //Constructor
    {
        super();                                                                                        //call parent constructor (ArrayData)
        Scanner LoginInput = new Scanner(System.in);                                                    //set scanner variable to LoginInput
		
        while (true)                                                                                    //while loop until return value false (always loop)
        {
            System.out.println("------------------------");                                             //print line
            System.out.print("Username: ");                                                             //print Username
            LoginUsername = LoginInput.nextLine();                                                      //LoginUsername = input from user
            System.out.print("Password: ");                                                             //print Password
            LoginPassword = LoginInput.nextLine();                                                      //LoginPassword = input from user

            if (LoginUsername.equals("") || LoginPassword.equals(""))                                   //If LoginUsername and LoginPassword is null , do
            {
                System.out.println("Please enter All Field!");                                          //Print Warning
            }

            //Get Data From Array dari acc method dalam ArrayData class 
            //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari acc method dalam ArrayData Class dengan kiraan i+1
            for (int x = 0; x < acc.length; x++) 
            {
                username = acc[x][0];                                                         //Set username = data dalam array row x col 0
                password = acc[x][3];                                                         //Set password = data dalam array row x col 3
                if (LoginUsername.equals(username) && LoginPassword.equals(password))                   //If LoginUsername and LoginPassword same to username and password , do
                {
                    System.out.println("------------------------");                                     //print line
                    System.out.println("Welcome! " + LoginUsername);                                    //print Welcome

                    if (LoginUsername.equals("admin"))                                                  //If LoginUsername = admin , do
                    {
                        admin Admin = new admin();                                                      //call admin class
                    } 
                    else                                                                                //Selain itu , do
                    {
                        dashboard Dashboard = new dashboard(LoginUsername);                             //Call dashboard class with pass parimeter
                    }

                    loginSuccess = true;                                                                //set boolean loginSuccess value to true
                    break;                                                                              //stop loop
                }
            }

            if (loginSuccess)                                                                           //if loginSuccess value is true , do
            {
                break;                                                                                  //stop loop
            } 
            else                                                                                        //selain itu , do
            {
                System.out.println("Username or Password invalid. Please try again.");                  //Print Warning
            }
        }
    }
}
