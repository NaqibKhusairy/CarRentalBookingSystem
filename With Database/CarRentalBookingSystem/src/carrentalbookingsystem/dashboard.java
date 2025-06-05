package carrentalbookingsystem;                                                                         //package Name

import java.util.Scanner;                                                                               //import library scanner

public class dashboard                                                                                  //Class Name
{
    private String Username;                                                                            //Private Sting Variable
    
    public dashboard(String Username)                                                                   //Constructor with parameter
    {
        this.Username = Username;                                                                       //Variable Username = Username in parameter
        int DashboardChoose;                                                                            //int variable
        Scanner DashboardInput = new Scanner(System.in);                                                //set scanner variable to DashboardInput
        
        do                                                                                              //do loop (always loop) sehingga condition while is false
        {
            System.out.println("------------------------");                                             //Print Line
            System.out.println("""
                               1. View Available Time
                               2. Boooking Car
                               3. Your Profil
                               4. Log Out""");                                                          //Print Pilihan
            System.out.print("------------------------\nChoose: ");                                     //Print Line and new line Print Choose
            DashboardChoose = DashboardInput.nextInt();                                                 //DashboardChoose = input from user
            
            switch (DashboardChoose)                                                                    //switch Stament for DashboardChoose
            {
                case 1 ->                                                                               //If DashboardChoose = 1
                {
                    System.out.println("------------------------");                                     //Print Line
                    System.out.println("View Available Time");                                          //Print View Available Time
                    System.out.println("------------------------");                                     //Print Line
                    BookedCar booked = new BookedCar(Username);                                         //Call BookedCar class with pass parimeter value
                }
                case 2 ->                                                                               //If DashboardChoose = 2
                {
                    System.out.println("------------------------");                                     //Print Line
                    System.out.println("Boooking Car");                                                 //Print Boooking Car
                    System.out.println("------------------------");                                     //Print Line
                    BookingCar booking = new BookingCar(Username);                                      //Call BookingCar class with pass parimeter value
                }
                case 3 ->                                                                               //If DashboardChoose = 3
                {
                    System.out.println("------------------------");                                     //Print Line
                    System.out.println("Your Profil");                                                  //Print Your Profil
                    System.out.println("------------------------");                                     //Print Line
                    YourProfile profil = new YourProfile(Username);                                     //Call YourProfile class with pass parimeter value
                }
                case 4 ->                                                                               //If DashboardChoose = 4
                {
                    System.out.println("------------------------");                                     //Print Line
                    System.out.println("Thankyou For Using Our System :)");                             //Print Thankyou
                    System.out.println("------------------------");                                     //Print Line
                    CarRentalBookingSystem mainpage = new CarRentalBookingSystem();                     //Call CarRentalBookingSystem class 
                }
                default ->                                                                              //Selain 1 , 2 , 3 dan 4
                {
                    System.out.println("------------------------");                                     //Print Line
                    System.out.println("Please Choose Between 1 until 4 Only !");                       //Print Warning
                }
            }
        }
        while(DashboardChoose != 4);                                                                    //kekal loop apabila while bukan 4
    }
}
