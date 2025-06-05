package carrentalbookingsystem;                                                         //package Name

public class Book                                                                       //Class Name
{
    private String name , phone , ic , carModel , plateNumber , date , startTime;       //Private Variable String
    private String endTime , Gender;                                                    //Private Variable String
    private int gender;                                                                 //Private Variable gender

    public Book(String name, String phone, String ic, String carModel, 
            String plateNumber, String date, String startTime, String endTime)          //Constructor with parameter
    {
        this.name = name;                                                               //Variable name = name in parameter
        this.phone = phone;                                                             //Variable phone = phone in parameter
        this.ic = ic;                                                                   //Variable ic = ic in parameter
        this.carModel = carModel;                                                       //Variable carModel = carModel in parameter
        this.plateNumber = plateNumber;                                                 //Variable plateNumber = plateNumber in parameter
        this.date = date;                                                               //Variable date = date in parameter
        this.startTime = startTime;                                                     //Variable startTime = startTime in parameter
        this.endTime = endTime;                                                         //Variable endTime = endTime in parameter
    }
    
    public String getName()                                                             //getName String Method
    {
	return name;                                                                    //return value name
    }
	
    public String getPhone()                                                            //getPhone String Method
    {
        return phone;                                                                   //return value phone
    }
	
    public String getIc()                                                               //getPhone String Method
    {
        return ic;                                                                      //return value ic
    }
	
    public String getCarModel()                                                         //getCarModel String Method
    {
        return carModel;                                                                //return value carModel
    }
	
    public String getPlateNumber()                                                      //getPlateNumber String Method
    { 
        return plateNumber;                                                             //return value plateNumber
    }
    
    public String getDate()                                                             //getDate String Method
    { 
        return date;                                                                    //return value date
    }
    
    public String getStartTime()                                                        //getStartTime String Method
    { 
        return startTime;                                                               //return value startTime
    }
    
    public String getEndTime()                                                          //getEndTime String Method
    { 
        return endTime;                                                                 //return value endTime
    }

    public String getGender()                                                           //getGender String Method
    {
        gender = Integer.parseInt(ic.substring(11));                                    //int gender value = int from subtring(11) ic
        gender %=2 ;                                                                    //gender = gender mod 2
        if (gender == 0)                                                                //apabila gender = 0 , do
        {
            Gender = "Female";                                                          //set value of String Gender = Female
        }
        else                                                                            //apabila selain 0 , do
        {
            Gender = "Male";                                                            //set value of String Gender = Male
        }
        return Gender;                                                                  //return value Gender
    }
}
