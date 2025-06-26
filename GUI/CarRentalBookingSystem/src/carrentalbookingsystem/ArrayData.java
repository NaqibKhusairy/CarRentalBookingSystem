package carrentalbookingsystem;

public class ArrayData
{
    private static String[][] acc =
    {                                                                            
        {"admin", "0360215499", "-", "admin"},
        {"123", "0123456789", "012345678922", "123"},
        {"naqib", "0104220085", "030617105963", "123"},
        {"user", "0195684933", "051210049586", "pass"},
        {"Ali", "0195684933", "991112015875", "pass"},
        {"Siti", "0195684986", "751210069586", "pass"}
    };
    
    private static String[][] booking =
    {                                                                        
        {"Ali", "0123456789", "991112015875", "Myvi", "ABC1234", "2025-05-21", "08:00", "10:00"},
        {"Siti", "0198765432", "751210069586", "Honda City", "XYZ5678", "2025-05-22", "10:00", "12:00"},
        {"naqib", "0104220085", "030617105963", "Axia", "UC9743", "2025-06-17", "08:00", "20:00"},
        {"123", "0123456789", "012345678922", "Saga", "UC5464", "2025-06-14", "12:00", "18:00"},
        {"user", "0195684933", "051210049586", "Saga", "UC5464", "2025-06-14", "07:00", "12:00"}
    };
    
    private static String[][] car =
    {                                                                            
        {"Perodua Myvi", "UC6919"},                                             
        {"Perodua Myvi", "UC1213"},
        {"Perodua Axia", "WLX2903"},
        {"Perodua Axia", "ABC1234"},
        {"Proton Saga", "NAS1204"},
        {"Proton Saga", "VLC1973"},
        {"Honda City", "SHA1122"},
        {"Honda City", "BCS1983"},
        {"Toyota Vios", "UITM2000"},
        {"Toyota Vios", "UPM9999"}
    };
    
    public String[][] getacc()
    {
        return acc;
    }
    
    public String[][] getbooking()
    {
        return booking;
    }
    
    public String[][] getcar()
    {
        return car;
    }
}