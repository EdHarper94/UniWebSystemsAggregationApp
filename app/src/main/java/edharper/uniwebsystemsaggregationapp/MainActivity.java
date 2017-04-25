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

    }
}



