package carrentalbookingsystem;                                                                         //package Name

import java.util.Scanner;                                                                               //import library scanner

public class CarRentalBookingSystem                                                                     //Class Name
{
    
    public static void main(String[] args)                                                              //Main Method
    {
        int Choose;                                                                                     //int variable
        Scanner scan = new Scanner(System.in);                                                          //set scanner variable to scan
		
        do                                                                                              //do loop (always loop) sehingga condition while is false
        {
            System.out.println("------------------------");                                             //Print Line
            System.out.println("1. Login Your Account\n2. Register Your Account\n3. Exit Program");     //Print Pilihan
            System.out.print("------------------------\nChoose: ");                                     //Print Line and new line Print Choose
            Choose = scan.nextInt();                                                                    //Choose = input from user

            if (Choose == 1)                                                                            //Jika nila Choose = 1 , do
            {
                login login = new login();                                                              //call login class
            } 
            else if (Choose == 2)                                                                       //Jika nila Choose = 2 , do
            {
                register register = new register();                                                     //call register class
            } 
            else if (Choose == 3)                                                                       //Jika nila Choose = 3 , do
            {
                System.out.println("Thank you. Program exited.");                                       //Print Thank You
                break;                                                                                  //Exit Loop
            } 
            else                                                                                        //Selain 1 , 2 & 3 , do
            {
                System.out.println("------------------------");                                         //Print line
                System.out.println("Please Choose Between 1 - 3 Only !");                               //Print Warning
            }
        }
        while (true);                                                                                   //kekal loop apabila while is true
    }
}
