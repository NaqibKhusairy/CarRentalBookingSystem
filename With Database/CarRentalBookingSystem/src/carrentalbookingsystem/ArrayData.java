package carrentalbookingsystem;                                                                                 //package Name

public class ArrayData                                                                                          //Class Name
{
    // Data akaun pengguna (username, phone, ic, password)
    private static String[][] acc =                                                                           //2D Array for acc
    {                                                                            
        {"admin", "0360215499", "-", "admin"},                                                                  //acc[0][0 , 1 , 2 , 3]
        {"123", "0123456789", "012345678922", "123"},                                                           //acc[1][0 , 1 , 2 , 3]
        {"naqib", "0104220085", "030617105963", "123"},                                                         //acc[2][0 , 1 , 2 , 3]
        {"user", "0195684933", "051210049586", "pass"},                                                         //acc[3][0 , 1 , 2 , 3]
        {"Ali", "0195684933", "991112015875", "pass"},                                                          //acc[4][0 , 1 , 2 , 3]
        {"Siti", "0195684986", "751210069586", "pass"}                                                          //acc[5][0 , 1 , 2 , 3]
    };

    // Data tempahan kenderaan (name, phone, ic, carModel, plateNumber, date, startTime, endTime)
    private static String[][] booking =                                                                       //2D Array for booking
    {                                                                        
        {"Ali", "0123456789", "991112015875", "Myvi", "ABC1234", "2025-05-21", "08:00", "10:00"},               //booking[0][0 , 1 , 2 , 3 , 4 , 5 , 6 , 7]
        {"Siti", "0198765432", "751210069586", "Honda City", "XYZ5678", "2025-05-22", "10:00", "12:00"},        //booking[1][0 , 1 , 2 , 3 , 4 , 5 , 6 , 7]
        {"naqib", "0104220085", "030617105963", "Axia", "UC9743", "2025-06-17", "08:00", "20:00"},              //booking[2][0 , 1 , 2 , 3 , 4 , 5 , 6 , 7]
        {"123", "0123456789", "012345678922", "Saga", "UC5464", "2025-06-14", "12:00", "18:00"},                //booking[3][0 , 1 , 2 , 3 , 4 , 5 , 6 , 7]
        {"user", "0195684933", "051210049586", "Saga", "UC5464", "2025-06-14", "07:00", "12:00"}                //booking[4][0 , 1 , 2 , 3 , 4 , 5 , 6 , 7]
    };
    
    public String[][] acc()
    {
        return acc;
    }
    
    public String[][] booking()
    {
        return booking;
    }
}