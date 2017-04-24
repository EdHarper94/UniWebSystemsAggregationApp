package edharper.uniwebsystemsaggregationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Start login screen
        Intent login = new Intent("edharper.uniwebsystemsaggregationapp.Login");
        startActivity(login);

        /*
        // DEBUG CODE (development)
        PerformLogin pl = new PerformLogin("", "", new PerformLogin.PerformLoginResponse() {
            @Override
            // Check the result
            public void loginFinished(String result) {
                if (result.equals("LOGIN_FAIL")) {
                    Toast.makeText(getApplicationContext(), "Incorrect username/password. Please try again.", Toast.LENGTH_SHORT).show();
                } else if (result.equals("CONNECTION_FAIL")){
                    Toast.makeText(getApplicationContext(), "Currently unable to connect to server. Please try again later.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent("com.egwh.scienceintranetscraper.StaffHoursScraper");
                    startActivity(intent);
                }
            }});
        pl.execute();
        */
    }
}



