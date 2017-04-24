package edharper.uniwebsystemsaggregationapp.Login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * @file LoginTaskCounter.java
 * http://stackoverflow.com/questions/32625599/wait-for-multiple-asynctask-to-complete
 *
 * Keeps track of the amount of login tasks and fires a broadcast once all have finished.
 */

public class LoginTaskCounter {
    private int numberOfTasks;
    private final Context context;

    /**
     * Initialises login task count with provided number of tasks
     * @param context
     * @param numberOfTasks
     */
    public LoginTaskCounter(Context context, int numberOfTasks){
        this.context = context;
        this.numberOfTasks = numberOfTasks;
    }

    // Called in async tasks post execute, decrements number of tasks and broad casts once all have finished
    public void taskFinished(){
        if(--numberOfTasks ==0){
            Intent intent = new Intent();

            LocalBroadcastManager mgr = LocalBroadcastManager.getInstance(context);

            mgr.sendBroadcast(new Intent("all_tasks_finished"));
        }
    }
}
