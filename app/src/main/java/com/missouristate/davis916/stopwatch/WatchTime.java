package com.missouristate.davis916.stopwatch;

/**
 * Laura Davis CIS 262-902
 * This is the WatchTime class, which is
 * a data model for the stopwatch app.
 */

public class WatchTime {
    //Time elements
    private long mStartTime;
    private long mTimeUpdate;
    private long mStoredTime;

    public WatchTime(){
        mStartTime = 0L;
        mStoredTime = 0L;
        mTimeUpdate = 0L;
    }//end WatchTime()

    public void resetWatchTime(){
        mStartTime = 0L;
        mStoredTime = 0L;
        mTimeUpdate = 0L;
    }

    public void setStartTime(long startTime){
        mStartTime = startTime;
    }

    public long getStartTime(){
        return mStartTime;
    }

    public void setTimeUpdate(long timeUpdate){
        mTimeUpdate = timeUpdate;
    }

    public long getTimeUpdate(){
        return mTimeUpdate;
    }

    public void addStoredTime(long timeInMilliseconds){
        mStoredTime += timeInMilliseconds;
    }

    public long getStoredTime(){
        return mStoredTime;
    }
}//end WatchTime class
