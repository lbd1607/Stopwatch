package com.missouristate.davis916.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Laura Davis CIS 262-902
 * This app uses a thread with loopers and
 * handlers to demonstrate a stopwatch.
 */

public class MainActivity extends Activity {
    //UI elements; buttons will toggle in visibility
    private TextView timeDisplay;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    //Time elements
    private WatchTime watchTime;
    private long timeInMilliseconds = 0L;

    //The handler for the thread element
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Activate activity and layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create references to UI components
        timeDisplay = (TextView) findViewById(R.id.textView);
        startBtn = (Button) findViewById(R.id.button);
        stopBtn = (Button) findViewById(R.id.button2);
        resetBtn = (Button) findViewById(R.id.button3);

        //Hide the stop and reset button
        stopBtn.setEnabled(false);
        resetBtn.setEnabled(false);

        //Instantiate the object that models stopwatch time
        watchTime = new WatchTime();

        //Instantiate a handler to run on the UI thread
        mHandler = new Handler();
    }//end onCreate()

    public void startTimer(View view){
        //Set the start and reset buttons to invisible and stop button to visible
        stopBtn.setEnabled(true);
        startBtn.setEnabled(false);
        resetBtn.setEnabled(false);

        //Set the start time and call the custom handler
        watchTime.setStartTime(SystemClock.uptimeMillis());
        mHandler.postDelayed(updateTimerRunnable, 20);
    }//end startTimer()

    //Runnable object
    private Runnable updateTimerRunnable = new Runnable() {
        @Override
        public void run() {
            //Compute time difference
            timeInMilliseconds = SystemClock.uptimeMillis() -
                    watchTime.getStartTime();
            watchTime.setTimeUpdate(watchTime.getStoredTime() +
                    timeInMilliseconds);
            int time = (int) (watchTime.getTimeUpdate() / 1000);

            //Compute minutes, seconds, and milliseconds
            int minutes = time / 60;
            int seconds = time % 60;
            int milliseconds = (int) (watchTime.getTimeUpdate() % 100);

            //Display the time in the TextView
            timeDisplay.setText(String.format("%02d", minutes) + ":" +
                String.format("%02d", seconds) + ":" +
                String.format("%02d", milliseconds));

            //Specify no time lapse between posting
            mHandler.postDelayed(this, 0);
        }//end run
    };//end Runnable

    public void stopTimer(View view){
        //Disable the start button and enable the stop and reset buttons
        stopBtn.setEnabled(false);
        startBtn.setEnabled(true);
        resetBtn.setEnabled(true);

        //Update the stored time value
        watchTime.addStoredTime(timeInMilliseconds);

        //Handler clears the message queue
        mHandler.removeCallbacks(updateTimerRunnable);
    }//end stopTimer()

    public void resetTimer(View view){
        watchTime.resetWatchTime();
        timeInMilliseconds = 0L;

        int minutes = 0;
        int seconds = 0;
        int milliseconds = 0;

        //Display the time in the TextView
        timeDisplay.setText(String.format("%02d", minutes) + ":" +
            String.format("%02d", seconds) + ":" +
            String.format("%02d", milliseconds));
    }//end resetTimer()

    //Menu stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }//end createOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button,
        //as long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected

}//end MainActivity class
