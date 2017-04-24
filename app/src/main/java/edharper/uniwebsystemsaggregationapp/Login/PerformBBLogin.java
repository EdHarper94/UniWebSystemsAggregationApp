package edharper.uniwebsystemsaggregationapp.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * @file PerformBBLogin.java
 * @author  Ed Harper
 * @date 23/03/2017
 * @see CookieStorage
 *
 * Performs login on Blackboard using JSoup and stores cookies in CookieStorage
 */

public class PerformBBLogin extends AsyncTask<Void, Void, String> {

    /**
     * Interface to pass back perform BB login result
     */
    public interface PerformBBLoginResponse{
        void loginResult(String result);
    }

    // Delegate variable
    public PerformBBLoginResponse delegate = null;

    final String BASE_URL = "https://blackboard.swan.ac.uk/webapps/portal/execute/tabs/tabAction?tab_tab_group_id=_49_1";
    final String LOGIN_URL = "https://blackboard.swan.ac.uk/webapps/login/";
    final String NEXT_URL = "https://blackboard.swan.ac.uk/webapps/portal/execute/tabs/tabAction?tab_tab_group_id=_23_1";
    final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
    final String HEADER = "blackboard.swan.ac.uk";
    final String LOGIN = "Login";

    final private String CONNECTION_FAIL = "CONNECTION_FAIL";
    final private String LOGIN_FAIL = "LOGIN_FAIL";
    final private String SUCCESS = "SUCCESS";

    // Cookie map variable
    private Map<String, String> cookies;
    // Init Cookie Store
    private CookieStorage cookieStorage = new CookieStorage();

    private String username;
    private String password;

    private int statusCode;
    private Exception exception;
    private String successTitle = "";

    private LoginTaskCounter loginTaskCounter;

    /**
     * Initialises a new instance of PerformBBLogin
     * @param delegate
     */
    public PerformBBLogin(String username, String password, LoginTaskCounter lc, PerformBBLoginResponse delegate){
        this.username = username;
        this.password = password;
        this.loginTaskCounter = lc;
        this.delegate = delegate;
    }


    /**
     * Perform login and store cookies in Cookie Storage
     *
     * @param params
     * @return
     */
    public String doInBackground(Void... params) {

        try {
            // HTTP Get request
            Connection.Response getReq = Jsoup
                    .connect(BASE_URL)
                    .method(Connection.Method.GET)
                    .execute();

            // Store cookies
            cookies = getReq.cookies();

            // Login Request
            Connection.Response loginReq = Jsoup
                    .connect(LOGIN_URL)
                    .header("Host", HEADER)
                    .referrer(LOGIN_URL)
                    .cookies(cookies)
                    .data("user_id", username)
                    .data("password", password)
                    .data("login", LOGIN)
                    .data("action", LOGIN.toLowerCase())
                    .data("new_loc", "")
                    .userAgent(USER_AGENT)
                    .method(Connection.Method.POST)
                    .execute();

            // Get Status code
            statusCode = loginReq.statusCode();
            // Store cookies
            cookies = loginReq.cookies();
            cookieStorage.storeCookies(cookies,LOGIN_URL);

            // Request to check we are now logged in
            Document checkSuccess = Jsoup
                    .connect(NEXT_URL)
                    .userAgent(USER_AGENT)
                    .referrer(LOGIN_URL)
                    .cookies(cookies)
                    .get();

            // Login success check variable
            successTitle = checkSuccess.title();

        } catch (Exception e) {
            this.exception = e;
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Checks connection and log in was success and passes result back to calling method
     * @param result
     */
    protected void onPostExecute(String result) {
        if(statusCode != 200){
            result = CONNECTION_FAIL;
        }
        else if(successTitle.equals("Welcome – Blackboard Learn")) {
            result = LOGIN_FAIL;
            cookieStorage.removeCookies();
        }else{
            result = SUCCESS;
        }
        // Set result
        delegate.loginResult(result);
        // Notify counter task has finished
        loginTaskCounter.taskFinished();
    }
}
