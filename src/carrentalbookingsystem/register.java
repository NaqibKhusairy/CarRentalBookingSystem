package carrentalbookingsystem;                                                                         //package Name

import java.util.Scanner;                                                                               //import library scanner

public class register extends ArrayData                                                                 //class name with inheritance
{
    private String RegisterUsername, RegisterPhone, RegisterIC, RegisterPassword;                       //Private Sting Variable

    public register()                                                                                   //Constructor
    {
        super();                                                                                        //call parent constructor (ArrayData)
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

            // Semak jika username telah wujud
            boolean existed = false;                                                                    //Set boolean existed value to false
            
            //Get Data From Array dari acc method dalam ArrayData class 
            //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari acc method dalam ArrayData Class dengan kiraan i+1
            for (int i = 0; i < acc.length; i++)
            {
                if (acc[i][0].equals(RegisterUsername))                                       //if value in array row i col 0 = RegisterUsername
                {
                    existed = true;                                                                     //Set boolean existed value to true
                    break;                                                                              //stop loop
                }
            }

            if (existed)                                                                                //if  existed value is true , do
            {
                System.out.println("------------------------");                                         //print line
                System.out.println("Username already exists! Please try again!");                       //Print Warning
                CarRentalBookingSystem mainpage = new CarRentalBookingSystem();                         //Call CarRentalBookingSystem class 
                break;                                                                                  //stop loop
            } 
            else                                                                                        //selain dari itu , do
            {
                String[][] newAcc = new String[acc.length + 1][4];                            // Tambah user baru ke acc

                //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari acc method dalam ArrayData Class dengan kiraan i+1
                for (int i = 0; i < acc.length; i++) 
                {
                    //For Loop dengan loop bermula j = 0 , loop sehingga j = 4 dengan kiraan j+1
                    for (int j = 0; j < 4; j++) 
                    {
                        //set newBook array row i col j and save dalam acc method dalam ArrayData Class row i col j
                        newAcc[i][j] = acc[i][j];
                    }
                }

                newAcc[acc.length][0] = RegisterUsername;                                     //RegisterUsername = new array dalam row 0
                newAcc[acc.length][1] = RegisterPhone;                                        //RegisterPhone = new array dalam row 1
                newAcc[acc.length][2] = RegisterIC;                                           //RegisterIC = new array dalam row 2
                newAcc[acc.length][3] = RegisterPassword;                                     //RegisterPassword = new array dalam row 3

                acc = newAcc;                                                                 //update array

                System.out.println("------------------------");                                         //print line
                System.out.println("Welcome new user! " + RegisterUsername);                            //print Welcome
                dashboard Dashboard = new dashboard(RegisterUsername);                                  //Call dashboard class with pass parimeter
                break;                                                                                  //Stop Loop
            }
        }
    }
}
