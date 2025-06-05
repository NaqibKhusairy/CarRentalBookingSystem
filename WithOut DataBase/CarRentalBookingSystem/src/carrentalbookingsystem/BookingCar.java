package carrentalbookingsystem;                                                                                                 //package Name

import java.util.Scanner;                                                                                                       //inport library scanner

public class BookingCar extends ArrayData                                                                                       //class name with inheritance
{
    private String Username , newname , name, phone, ic , Jenama , NoPlate , Tarikh , MasaStart , MasaTamat;                    //Private Sting Variable
    
    public BookingCar(String Username)                                                                                          //Constructor with parameter
    {
        super();                                                                                                                //call parent constructor (ArrayData)
        this.Username = Username;                                                                                               //Variable Username = Username in parameter
        Scanner BookingInput = new Scanner(System.in);                                                                          //set scanner variable to BookingInput

        //Get Data From Array dari acc method dalam ArrayData class 
        //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari acc method dalam ArrayData Class dengan kiraan i+1
        for (int i = 0; i < acc.length; i++) 
        {
            name = acc[i][0];                                                                                         //Set name = data dalam array row i col 0

            if (name.equals(Username))                                                                                          //Jika  variable name = value Username , do
            {                                                                                        
                newname = acc[i][0];                                                                                  //Set newname = data dalam array row i col 0
                phone = acc[i][1];                                                                                    //Set phone = data dalam array row i col 1
                ic = acc[i][2];                                                                                       //Set ic = data dalam array row i col 2
                System.out.println("Username      : " + newname);                                                               //print newname
                System.out.println("Phone         : " + phone);                                                                 //print phone
                System.out.println("IC            : " + ic);                                                                    //print ic
            }
        }
        System.out.print("Jenama Kereta : ");                                                                                   //print Jenama Kereta
        Jenama = BookingInput.nextLine();                                                                                       //Jenama = input from user
        System.out.print("No Plate      : ");                                                                                   //print No Plate
        NoPlate = BookingInput.nextLine();                                                                                      //NoPlate = input from user
        System.out.print("Tarikh        : ");                                                                                   //print Tarikh
        Tarikh = BookingInput.nextLine();                                                                                       //Tarikh = input from user
        System.out.print("Masa Start    : ");                                                                                   //print Masa Start
        MasaStart = BookingInput.nextLine();                                                                                    //MasaStart = input from user
        System.out.print("Masa Tamat    : ");                                                                                   //print Masa Tamat
        MasaTamat = BookingInput.nextLine();                                                                                    //MasaTamat = input from user
        
        String[][] newBook = new String[booking.length + 1][8];                                                       // Tambah user baru ke booking
        
       //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari booking method dalam ArrayData Class dengan kiraan i+1
        for (int i = 0; i < booking.length; i++) 
        {
            //For Loop dengan loop bermula j = 0 , loop sehingga j = 8 dengan kiraan j+1
            for (int j = 0; j < 8; j++)
            {
                //set newBook array row i col j and save dalam booking method dalam ArrayData Class row i col j
                newBook[i][j] = booking[i][j];
            }
        }
        
        newBook[booking.length][0] = newname;                                                                         //newname = new array dalam row 0
        newBook[booking.length][1] = phone;                                                                           //phone = new array dalam row 1
        newBook[booking.length][2] = ic;                                                                              //ic = new array dalam row 2
        newBook[booking.length][3] = Jenama;                                                                          //Jenama = new array dalam row 3
        newBook[booking.length][4] = NoPlate;                                                                         //NoPlate = new array dalam row 4
        newBook[booking.length][5] = Tarikh;                                                                          //Tarikh = new array dalam row 5
        newBook[booking.length][6] = MasaStart;                                                                       //MasaStart = new array dalam row 6
        newBook[booking.length][7] = MasaTamat;                                                                       //MasaTamat = new array dalam row 7
        
        booking = newBook;                                                                                            //update array
        
        System.out.println("------------------------");                                                                         //print line
        System.out.println(newname + "Booking Sucsessfull");                                                                    //print Booking Sucsessfull
    }
}
