package edharper.uniwebsystemsaggregationapp.Email;

/**
 * Created by eghar on 13/04/2017.
 */

public class EmailUser {

    private static String emailAddress;
    private static String password;

    public EmailUser(String username, String password){
        this.emailAddress = username + "@swansea.ac.uk";
        this.password = password;
    }

    public static String getEmailAddress(){
        return emailAddress;
    }

    public static String getPassword(){
        return password;
    }

}
