package edharper.uniwebsystemsaggregationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by eghar on 17/03/2017.
 */

public class HomeScreen extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button tTButton = (Button)findViewById(R.id.timetable_button);
        Button cWButton = (Button)findViewById(R.id.coursework_button);
        Button sHButton = (Button)findViewById(R.id.staff_hours_button);
        Button emailButton = (Button)findViewById(R.id.email_client_button);

        tTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("edharper.uniwebsystemsaggregationapp.TimetableScraper");
                startActivity(intent);
            }
        });

        cWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("edharper.uniwebsystemsaggregationapp.CourseworkMenu");
                startActivity(intent);
            }
        });

        sHButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("edharper.uniwebsystemsaggregationapp.StaffHoursScraper");
                startActivity(intent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("edharper.uniwebsystemsaggregationapp.Inbox");
                startActivity(intent);
            }
        });



    }
}
