package carrentalbookingsystem;                                                                                                 //package Name

public class BookedCar extends ArrayData                                                                                        //class name with inheritance
{
    private String Username , name , phone , ic , carModel , plateNumber , date , startTime , endTime;                          //Private Sting Variable
    private Book[] bookings;                                                                                                    //Private Book[] (Array) Variable
    private int count = 0;                                                                                                      //Print int variable with value

    public BookedCar(String Username)                                                                                           //Constructor with parameter
    {
        super();                                                                                                                //call parent constructor (ArrayData)
        this.Username = Username;                                                                                               //Variable Username = Username in parameter
        
        //Cipta array Book dengan saiz dari booking method dalam ArrayData Class
        bookings = new Book[booking.length];                                                                              
        
        //Get Data From Array dari booking method dalam ArrayData class 
        //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari booking method dalam ArrayData Class dengan kiraan i+1
        for (int i = 0; i < booking.length; i++)
        {                                                                        
            name = booking[i][0];                                                                                               //Set Name = data dalam array row i col 0
            phone = booking[i][1];                                                                                              //Set phone = data dalam array row i col 1
            ic = booking[i][2];                                                                                                 //Set ic = data dalam array row i col 2
            carModel = booking[i][3];                                                                                           //Set carModel = data dalam array row i col 3
            plateNumber = booking[i][4];                                                                                        //Set plateNumber = data dalam array row i col 4
            date = booking[i][5];                                                                                               //Set date = data dalam array row i col 5
            startTime = booking[i][6];                                                                                          //Set startTime = data dalam array row i col 6
            endTime = booking[i][7];                                                                                            //Set endTime = data dalam array row i col 7

            Book book = new Book(name, phone, ic, carModel, plateNumber, date, startTime, endTime);                             //pass parameter to Book class

            bookings[count] = book;                                                                                             //set size for book
            count++;                                                                                                            //kira count dgn cara count + 1
        }

        displayBookings();                                                                                                      //Call Method displayBookings
    }

    public void displayBookings()                                                                                               //displayBookings Void Method 
    {
        System.out.printf("%-10s %-13s %-8s %-15s %-10s %-15s %-10s %-10s\n",
                "Username", "Phone", "Gender", "Jenama Kereta", "No Plate", "Tarikh", "Masa Start", "Masa Tamat");              //Print dalam bentuk Table format
        System.out.println("-----------------------------------------------------------------------------------------------");  //line

        //For Loop dengan loop bermula i = 0 , loop sehingga i = value count dengan kiraan i+1
        for (int i = 0; i < count; i++) 
        {
            Book book = bookings[i];                                                                                            //Dapatkan value Book pada indeks i
            System.out.printf("%-10s %-13s %-8s %-15s %-10s %-15s %-10s %-10s\n",                                               //Print dalam bentuk Table format
                book.getName(),                                                                                                 //call getName method dari book class 
                book.getPhone(),                                                                                                //call getPhone method dari book class 
                book.getGender(),                                                                                               //call getGender method dari book class 
                book.getCarModel(),                                                                                             //call getCarModel method dari book class 
                book.getPlateNumber(),                                                                                          //call getPlateNumber method dari book class 
                book.getDate(),                                                                                                 //call getDate method dari book class 
                book.getStartTime(),                                                                                            //call getStartTime method dari book class 
                book.getEndTime());                                                                                             //call getEndTime method dari book class 
            System.out.println("-----------------------------------------------------------------------------------------------");//line
        }
    }
}
