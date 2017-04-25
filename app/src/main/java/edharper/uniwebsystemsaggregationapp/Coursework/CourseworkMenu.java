package edharper.uniwebsystemsaggregationapp.Coursework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import edharper.uniwebsystemsaggregationapp.R;

/**
 * @file CourseworkMenu.java
 * @author Ed Harper
 * @date 20/03/2017
 *
 * Coursework menu activity
 */

public class CourseworkMenu extends Activity implements View.OnClickListener {

    private ImageButton homeButton;
    private Button cCWButton, rCWButton, fCWButton;

    private Intent intent;

    // Coursework type Variables
    private final String TYPE = CourseworkGlobals.TYPE;
    private final String CURRENT_CW = CourseworkGlobals.CURRENT_CW;
    private final String RECEIVED_CW = CourseworkGlobals.RECEIVED_CW;
    private final String FUTURE_CW = CourseworkGlobals.FUTURE_CW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursework_menu);

        // Init buttons
        homeButton = (ImageButton)findViewById(R.id.home_button);
        homeButton.setOnClickListener(this);
        cCWButton = (Button)findViewById(R.id.current_cw_button);
        cCWButton.setOnClickListener(this);
        rCWButton = (Button)findViewById(R.id.received_cw_button);
        rCWButton.setOnClickListener(this);
        fCWButton = (Button)findViewById(R.id.future_cw_button);
        fCWButton.setOnClickListener(this);

    }

    /**
     * Onclick for all buttons
     * @param view
     */
    @Override
    public void onClick(View view){

        // Switch on clicked button
        switch (view.getId()){
            case R.id.home_button:
                goBack();
                break;
            case R.id.current_cw_button:
                intent = new Intent("edharper.uniwebsystemsaggregationapp.CourseworkScraper");
                intent.putExtra(TYPE, CURRENT_CW);
                startActivity(intent);
                break;
            case R.id.received_cw_button:
                intent = new Intent("edharper.uniwebsystemsaggregationapp.CourseworkScraper");
                intent.putExtra(TYPE, RECEIVED_CW);
                startActivity(intent);
                break;
            case R.id.future_cw_button:
                intent = new Intent("edharper.uniwebsystemsaggregationapp.CourseworkScraper");
                intent.putExtra(TYPE, FUTURE_CW);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void goBack(){
        finish();
    }
}
