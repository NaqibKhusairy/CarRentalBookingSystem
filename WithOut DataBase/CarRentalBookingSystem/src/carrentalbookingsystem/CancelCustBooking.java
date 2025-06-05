package carrentalbookingsystem;                                                                 //package Name

import java.util.Scanner;                                                                       //import library scanner

public class CancelCustBooking extends ArrayData                                                //class name with inheritance
{
    private String Username , name, phone, ic, Jenama, NoPlate, Tarikh, MasaStart, MasaTamat;   //Private Sting Variable
    boolean found = false;                                                                      //Boolean variable with value

    public CancelCustBooking(String Username)                                                   //Constructor with parameter
    {
        super();                                                                                //call parent constructor (ArrayData)
        this.Username = Username;                                                               //Variable Username = Username in parameter
        Scanner CancelInput = new Scanner(System.in);                                           //set scanner variable to CancelInput

        System.out.print("Customer Username : ");                                               //print Customer Username
        name = CancelInput.nextLine();                                                          //name = input from user
        System.out.print("Customer Phone    : ");                                               //print Customer Phone
        phone = CancelInput.nextLine();                                                         //phone = input from user
        System.out.print("Customer IC       : ");                                               //print Customer IC
        ic = CancelInput.nextLine();                                                            //ic = input from user
        System.out.print("Jenama Kereta     : ");                                               //print Jenama Kereta
        Jenama = CancelInput.nextLine();                                                        //Jenama = input from user
        System.out.print("No Plate          : ");                                               //print No Plate
        NoPlate = CancelInput.nextLine();                                                       //NoPlate = input from user
        System.out.print("Tarikh            : ");                                               //print Tarikh
        Tarikh = CancelInput.nextLine();                                                        //Tarikh = input from user
        System.out.print("Masa Start        : ");                                               //print Masa Start
        MasaStart = CancelInput.nextLine();                                                     //MasaStart = input from user
        System.out.print("Masa Tamat        : ");                                               //print Masa Tamat
        MasaTamat = CancelInput.nextLine();                                                     //MasaTamat = input from user

        // Cari dan padam rekod yang sepadan
        //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari booking method dalam ArrayData Class dengan kiraan i+1
        for (int i = 0; i < booking.length; i++) 
        {
            //Jika maklumat dari Input dan dalam booking method dalam ArrayData Class sama , do
            if (booking[i][0].equals(name) &&
                booking[i][1].equals(phone) &&
                booking[i][2].equals(ic) &&
                booking[i][3].equals(Jenama) &&
                booking[i][4].equals(NoPlate) &&
                booking[i][5].equals(Tarikh) &&
                booking[i][6].equals(MasaStart) &&
                booking[i][7].equals(MasaTamat)) 
            {
                
                //Create new array tanpa masukkan data yang diatas dengan tolak satu row dari row asal
                String[][] CancelBook = new String[booking.length - 1][8];
                int newIndex = 0;                                                               //int variable with value 
                //For Loop dengan loop bermula j = 0 , loop sehingga j = saiz dari booking method dalam ArrayData Class dengan kiraan j+1
                for (int j = 0; j < booking.length; j++) 
                {
                    if (j != i)                                                                 //Jika nilai j tak sama dengan i , do
                    {
                        //For Loop dengan loop bermula k = 0 , loop sehingga k = 8 dengan kiraan k+1
                        for (int k = 0; k < 8; k++)
                        {
                            CancelBook[newIndex][k] = booking[j][k];                  //masukkan data yang tiadk sama degan input dalam array
                        }
                        newIndex++;                                                             //kira newIndex dengan cara newIndex = newIndex +1
                    }
                }

                booking = CancelBook;                                                 //update array
                found = true;                                                                   //set found variable to true
                break;                                                                          //break (stop loop)
            }
        }

        if (found)                                                                              //if found value if true , do
        {                                                                            
            System.out.println(name + "'s booking successfully canceled.");                     //Print successfully canceled
        } 
        else                                                                                    //Selain itu , do
        {                                        
            System.out.println("No booking found .");                                           //Print No booking found
        }
    }
}
