package carrentalbookingsystem;                                                                         //package Name

public class account                                                                                    //Class Name
{
    private String username , phone , ic , password;                                                    //Private Sting Variable

    public account(String username, String phone, String ic, String password)                           //Constructor with parameter
    {
        this.username = username;                                                                       //Variable Username = Username in parameter
        this.phone = phone;                                                                             //Variable phone = phone in parameter
        this.ic = ic;                                                                                   //Variable ic = ic in parameter
        this.password = password;                                                                       //Variable password = password in parameter
    }
    
    public String getUsername() {                                                                       //getUsername String Method
        return username;                                                                                //return value username
    }

    public String getPhone() {                                                                          //getPhone String Method
        return phone;                                                                                   //return value phone
    }

    public String getIc() {                                                                             //getIc String Method
        return ic;                                                                                      //return value ic
    }

    public String getPassword() {                                                                       //getPassword String Method
        return password;                                                                                //return value password
    }
}
