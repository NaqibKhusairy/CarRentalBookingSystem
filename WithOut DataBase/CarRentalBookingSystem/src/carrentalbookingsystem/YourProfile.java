package carrentalbookingsystem;                                                                         //package Name

public class YourProfile extends ArrayData                                                              //class name with inheritance
{
    private String Username , name, phone, ic, gender, birthday, password;                              //Private Sting Variable
    String[] monthNames =                                                                               //Array Variable
    {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    public YourProfile(String Username)                                                                 //Constructor with parameter
    {
        super();                                                                                        //call parent constructor (ArrayData)
        this.Username = Username;                                                                       //Variable Username = Username in parameter
        displayProfile();                                                                               //call displayProfile Method
    }

    public void displayProfile()                                                                        //displayProfile Method
    {
        System.out.println("----------------------------");                                             //print line
        System.out.println("          YOUR PROFILE      ");                                             //print YOUR PROFILE
        System.out.println("----------------------------");                                             //print line

        //Get Data From Array dari acc method dalam ArrayData class 
        //For Loop dengan loop bermula i = 0 , loop sehingga i = saiz dari acc method dalam ArrayData Class dengan kiraan i+1
        for (int i = 0; i < acc.length; i++) 
        {
            name = acc[i][0];                                                                           //Set name = data dalam array row i col 0
            phone = acc[i][1];                                                                          //Set phone = data dalam array row i col 1
            ic = acc[i][2];                                                                             //Set ic = data dalam array row i col 2
            password = acc[i][3];                                                                       //Set password = data dalam array row i col 3

            if (name.equals(Username))                                                                  //Jika name value = Username value , do
            {
                //Set Birtday value format dengan call ReadDay , ReadMonth & ReadYear Method sambil pass value ic
                birthday = ReadDay(ic) + " " + ReadMonth(ic) + " " + ReadYear(ic);
                gender = ReadGender(ic);                                                                //Set gender value dengal call ReadGender sambil pass value ic

                System.out.println("Username : " + name);                                               //Print name
                System.out.println("Phone    : " + phone);                                              //Print phone
                System.out.println("IC       : " + ic);                                                 //Print ic
                System.out.println("Gender   : " + gender);                                             //Print gender
                System.out.println("Birthday : " + birthday);                                           //Print birthday
                System.out.println("Password : " + password);                                           //Print password
                break;                                                                                  //Stop Loop
            }
        }

        System.out.println("----------------------------");                                             //Print Line
    }

    // Fungsi untuk baca tarikh lahir dari IC
    public String ReadDay(String myKadNumber)                                                           //ReadDay Method with Parameter 
    {
                return myKadNumber.substring(4, 6);                                                     //return substring(4, 6) from myKadNumber
    }

    public String ReadMonth(String myKadNumber)                                                         //ReadMonth Method with Parameter
    {
        //Set Int month get from substring(2,4) from myKadNumber sambil tukar ia menjadi integer
        int month = Integer.parseInt(myKadNumber.substring(2, 4));                                      
        return monthNames[month - 1];                                                                   //Return Value from array monthNames 
    }
    

    public int ReadYear(String myKadNumber)                                                             //ReadYear Method with Parameter
    {
        //Set Int year get from substring(0,2) from myKadNumber sambil tukar ia menjadi integer
        int year = Integer.parseInt(myKadNumber.substring(0, 2)); 
        if (year >= 0 && year <= 25)                                                                    //Jika year diantara 0 hingga 25 , do
        {
            return year + 2000;                                                                         //Return value year + 2000
        } 
        else                                                                                            //Selain itu , do 
        {
            return year + 1900;                                                                         //Return value year + 1900
        }
    }

    public String ReadGender(String myKadNumber)                                                        //ReadGender Method with Parameter
    {
        //Set Int genderDigit get from substring(11) from myKadNumber sambil tukar ia menjadi integer
        int  gender = Integer.parseInt(myKadNumber.substring(11));
        gender %=2 ;                                                                                    //gender = gender mod 2
        if (gender == 0)                                                                                //apabila gender = 0 , do
        {
            return "Female";                                                                            //Return value Female
        }
        else                                                                                            //apabila selain 0 , do
        {
            return "Male";                                                                              //Return value Male
        }
    }
}