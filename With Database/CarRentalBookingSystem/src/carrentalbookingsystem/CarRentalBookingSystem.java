package carrentalbookingsystem;                                                                             //package Name

import java.util.Scanner;                                                                                   //import library scanner

public class CarRentalBookingSystem                                                                         //Class Name
{
    
    public static void main(String[] args)                                                                  //Main Method
    {
        int Choose;                                                                                         //int variable
        Scanner scan = new Scanner(System.in);                                                              //set scanner variable to scan
        CreateDatabase createDatabase = new CreateDatabase();                                               //call login class
        
        do {
            System.out.println("------------------------");                                                 //Print Line
            System.out.println("1. Login Your Account\n2. Register Your Account\n3. Exit Program");         //Print Pilihan
            System.out.print("------------------------\nChoose: ");                                         //Print Line and new line Print Choose
            Choose = scan.nextInt();                                                                        //Choose = input from user
            
            switch (Choose)
            {
                case 1 ->                                                                                   //Jika nilai Choose = 1 , do
                {
                    login login = new login();                                                              //call login class
                }
                case 2 ->                                                                                   //Jika nilai Choose = 2 , do 
                {
                    register register = new register();                                                     //call register class
                }
                case 3 ->                                                                                   //Jika nilai Choose = 3 , do 
                {
                    System.out.println("Thank you. Program exited.");                                       //Print Thank You
                }
                default ->                                                                                  //Jika nilai Choose selain 1 / 2 / 3 , do 
                {
                    System.out.println("------------------------");                                         //Print line
                    System.out.println("Please Choose Between 1 - 3 Only !");                               //Print Warning
                }
            }

            if (Choose == 3)                                                                                //Jika nilai Choose = 3 , do 
            {
                break;                                                                                      //Exit loop
            }
            
        } while (true);                                                                                     //kekal loop apabila while is true
    }
}
