package com.upstream.learningapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class MyJobIntentService extends JobIntentService {
    private static final String TAG = MyBackgroundService.class.getSimpleName();


    public static void enqueueWork(Context context,Intent intent){
            enqueueWork(context,MyJobIntentService.class,17,intent);
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Task execution started", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        //Write code here..
        //Write code here..
        int duration = intent.getIntExtra("sleepTime",-1);
        Log.i(TAG,"onHandleWork, Thread name: " + Thread.currentThread().getName());
        //write long operation here
        int ctr = 1;
        //long dummy operation
        while (ctr<=duration){
            Log.i(  TAG,"Time elapsed: " + ctr + " secs");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctr++;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Task execution finishes", Toast.LENGTH_SHORT).show();
    }
}
