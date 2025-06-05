package carrentalbookingsystem;                                                                         //package Name

import java.util.Scanner;                                                                               //import library scanner

public class admin                                                                                      //Class Name
{
    public admin()                                                                                      //Constructor
    {
        int AdminChoose;                                                                                //int variable
        Scanner AdminInput = new Scanner(System.in);                                                    //set scanner variable to AdminInput
		
        do                                                                                              //do loop (always loop) sehingga condition while is false
        {
            System.out.println("------------------------");                                             //Print Line
            System.out.println("1. View Boooked Car\n2. Cancel Customer Booking\n3. Log Out");          //Print Pilihan
            System.out.print("------------------------\nChoose: ");                                     //Print Line and new line Print Choose
            AdminChoose = AdminInput.nextInt();                                                         //AdminChoose = input from user
            
            switch (AdminChoose)                                                                        //switch Stament for AdminChoose
            {
                case 1 ->                                                                               //If AdminChoose = 1
                {
                    System.out.println("------------------------");                                     //Print Line
                    System.out.println("View Boooked Car");                                             //Print View Boooked Car
                    System.out.println("------------------------");                                     //Print Line
                    BookedCar booked = new BookedCar("admin");                                          //Call BookedCar class with pass parimeter value
                }
                case 2 ->                                                                               //If AdminChoose = 2
                {
                    System.out.println("------------------------");                                     //Print Line
                    System.out.println("Cancel Customer Booking");                                      //Print Cancel Customer Booking
                    System.out.println("------------------------");                                     //Print Line
                    CancelCustBooking cancel = new CancelCustBooking("admin");                          //Call CancelCustBooking class with pass parimeter value
                }
                case 3 ->                                                                               //If AdminChoose = 3
                {
                    System.out.println("------------------------");                                     //Print Line
                    System.out.println("Thankyou For Using Our System :)");                             //Print Thankyou
                    System.out.println("------------------------");                                     //Print Line
                    CarRentalBookingSystem mainpage = new CarRentalBookingSystem();                     //Call CarRentalBookingSystem class 
                }
                default ->                                                                              //Selain 1 , 2 dan 3
                {
                    System.out.println("------------------------");                                     //Print Line
                    System.out.println("Please Choose Between 1 until 3 Only !");                       //Print Warning
                }
            }
        }
        while(AdminChoose != 3);                                                                        //kekal loop apabila while bukan 3
    }
}
