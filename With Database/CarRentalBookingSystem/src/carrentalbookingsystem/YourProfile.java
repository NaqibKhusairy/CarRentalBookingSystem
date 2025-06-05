package carrentalbookingsystem;                                                                         //package Name

import java.sql.*;                                                                                      //import all library in java.sql

public class YourProfile extends connectionDB                                                           //class name with inheritance
{
    private String Username , name, phone, ic, gender, birthday, password;                              //Private Sting Variable
    String[] monthNames =                                                                               //Array Variable
    {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    public YourProfile(String Username)                                                                 //Constructor with parameter
    {
        super();                                                                                        //call parent constructor (connectionDB)
        this.Username = Username;                                                                       //Variable Username = Username in parameter
        displayProfile();                                                                               //call displayProfile Method
    }

    public void displayProfile()                                                                        //displayProfile Method
    {
        System.out.println("----------------------------");                                             //print line
        System.out.println("          YOUR PROFILE      ");                                             //print YOUR PROFILE
        System.out.println("----------------------------");                                             //print line

        try                                                                                             //Exception Handling for finding errors
        {
            //Connect to database
            conn = DriverManager.getConnection(urlWithDB, dbusername, dbpassword);                      //Establish a database connection		
            //get all users data dimana username in database = username
            PreparedStatement Getuser = conn.prepareStatement(
                "SELECT * FROM users WHERE username = ? "
            );
            Getuser.setString(1, Username);                                                             //value checking column username get from Username
            ResultSet rsUser = Getuser.executeQuery();                                                  //run getalldata Query
            
            // Loop semua data dari database dimana name in database = Username
            while (rsUser.next()) 
            {
                name = rsUser.getString("username");                                                    //set newname = data get from col username in database
                phone = rsUser.getString("phone");                                                      //set phone = data get from col phone in database
                ic = rsUser.getString("ic");                                                            //set ic = data get from col ic in database
                password = rsUser.getString("password");                                                            //set ic = data get from col ic in database
                //Set Birtday value format dengan call ReadDay , ReadMonth & ReadYear Method sambil pass value ic
                birthday = ReadDay(ic) + " " + ReadMonth(ic) + " " + ReadYear(ic);
                gender = ReadGender(ic);                                                                //Set gender value dengal call ReadGender sambil pass value ic
                System.out.println("Username : " + name);                                               //Print name
                System.out.println("Phone    : " + phone);                                              //Print phone
                System.out.println("IC       : " + ic);                                                 //Print ic
                System.out.println("Gender   : " + gender);                                             //Print gender
                System.out.println("Birthday : " + birthday);                                           //Print birthday
                System.out.println("Password : " + password);                                           //Print password
            }

            rsUser.close();                                                                             //Close ResultSet
            conn.close();                                                                               //close the connection
        }
        catch (Exception e)                                                                             //if the system find the error throughout the Try-Catch Process
        {
            System.out.println("Error: " + e.getMessage());                                             //print error
            System.exit(1);                                                                             //exit program
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