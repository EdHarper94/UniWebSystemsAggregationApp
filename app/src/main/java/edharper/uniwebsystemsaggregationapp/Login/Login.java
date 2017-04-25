package edharper.uniwebsystemsaggregationapp.Login;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import edharper.uniwebsystemsaggregationapp.Email.EmailUser;
import edharper.uniwebsystemsaggregationapp.R;
/**
 * @file Login.java
 * @author Ed Harper
 * @data 22/03/2017
 *
 * Login activity that takes user credentials and passes them to login classes
 */
public class Login extends Activity{

    private final String CONNECTION_FAIL = "CONNECTION_FAIL";
    private final String LOGIN_FAIL = "LOGIN_FAIL";
    private final String SUCCESS = "SUCCESS";

    private final Context context = Login.this;

    private Boolean intranetConnection = false;
    private Boolean intranetLoginSuccess = false;
    private Boolean intranetSuccess = false;
    private Boolean BBLoginSuccess = false;
    private Boolean BBConnection = false;
    private Boolean BBSuccess = false;

    private String username;
    private String password;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText usernameField = (EditText)findViewById(R.id.username_input_field);
        final EditText passwordField = (EditText)findViewById(R.id.password_input_field);

        final Button loginButton = (Button)findViewById(R.id.login_button);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.login_progress_bar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginTaskCounter lc = new LoginTaskCounter(context, 2);
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();

                // Check fields have something in them
                if(username.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your username and password.", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);

                    // Perform intranet login with entered username and password
                    new PerformIntranetLogin(username, password,lc, new PerformIntranetLogin.PerformIntranetLoginResponse() {
                        @Override
                        // Check the result
                        public void loginFinished(String result) {

                            if(!result.equals(CONNECTION_FAIL)){
                                intranetConnection = true;
                            }
                            if(!result.equals(LOGIN_FAIL)){
                                intranetLoginSuccess = true;
                            }
                            if(result.equals(SUCCESS) && intranetConnection && intranetLoginSuccess){
                                intranetSuccess = true;
                            }
                        }}).execute();

                    // Perform Blackboard login with entered username and password
                        new PerformBBLogin(username, password, lc, new PerformBBLogin.PerformBBLoginResponse() {
                            @Override
                            public void loginResult(String result) {
                                if(!result.equals(CONNECTION_FAIL)){
                                    BBConnection = true;
                                }
                                if(!result.equals(LOGIN_FAIL)){
                                    BBLoginSuccess = true;
                                }
                                if(result.equals(SUCCESS) && BBConnection && BBLoginSuccess){
                                    BBSuccess = true;
                                }
                            }
                        }).execute();
                }
            }
        });

        // Receives broadcast once login tasks have finished and checks login was successful
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                progressBar.setVisibility(View.GONE);
                if (checkLoginsWereSucessful()) {
                    // Notify user
                    Toast.makeText(context, "Login Successful...", Toast.LENGTH_SHORT).show();

                    // Set email credentials
                    setEmailCredentials();

                    // Go to homescreen
                    Intent menuIntent = new Intent("edharper.uniwebsystemsaggregationapp.HomeScreen");
                    startActivity(menuIntent);
                } else {
                    Toast.makeText(context, "Currently unable to connect to server. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        }, new IntentFilter("all_tasks_finished"));
    }


    /**
     * Checks login statuses for both login async tasks
     * @return the login success status
     */
    public Boolean checkLoginsWereSucessful(){
        if(intranetSuccess && BBSuccess){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Set users email credentials
     */
    public void setEmailCredentials(){
        EmailUser emailUser = new EmailUser(username, password);
    }


}

