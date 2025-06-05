package carrentalbookingsystem;                                                                         //package Name

import java.util.Scanner;                                                                               //import library scanner

public class register                                                                                   //class name
{
    private String RegisterUsername, RegisterPhone, RegisterIC, RegisterPassword;                       //Private Sting Variable

    public register()                                                                                   //Constructor
    {
        Scanner RegisterInput = new Scanner(System.in);                                                 //set scanner variable to RegisterInput
        while (true)                                                                                    //while loop until return value false (always loop)
        {
            System.out.println("------------------------");                                             //print line
            System.out.print("Username    : ");                                                         //print Username
            RegisterUsername = RegisterInput.nextLine();                                                //RegisterUsername = input from user
            System.out.print("Phone Number: ");                                                         //print Phone Number
            RegisterPhone = RegisterInput.nextLine();                                                   //RegisterPhone = input from user
            System.out.print("IC Number   : ");                                                         //print IC Number
            RegisterIC = RegisterInput.nextLine();                                                      //RegisterIC = input from user
            System.out.print("Password    : ");                                                         //print Password
            RegisterPassword = RegisterInput.nextLine();                                                //RegisterPassword = input from user

            if (RegisterUsername.equals("") || RegisterPhone.equals("") ||                              //If RegisterUsername , RegisterPhone ,
                    RegisterIC.equals("") || RegisterPassword.equals(""))                               //RegisterIC , RegisterPassword is null , do
            {                                                                                           
                System.out.println("Please enter all fields!");                                         //Print Warning
                continue;                                                                               //continue loop
            }
            
            if (RegisterUsername.equals("admin"))                                                       //If LoginUsername = admin , do
            {
                System.out.println("------------------------");                                         //print line
                System.out.println("Are You Admin? Please Login!");                                     //Print Warning
                CarRentalBookingSystem mainpage = new CarRentalBookingSystem();                         //Call CarRentalBookingSystem class 
                break;                                                                                  //stop loop
            }
            
            account acc = new account(RegisterUsername , RegisterPhone , RegisterIC , RegisterPassword);//call account class with pass parimeter
            insertacc ins = new insertacc(acc);                                                         //call insertdata class
            break;
        }
    }
}
