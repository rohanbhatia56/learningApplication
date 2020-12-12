package com.upstream.learningapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {

    private static final String TAG = MyBackgroundService.class.getSimpleName();


    public MyIntentService() {
        super("MyBackgroundThread");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate, Thread name: " + Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Write code here..
        Log.i(TAG,"onHandleIntent, Thread name: " + Thread.currentThread().getName());
        //write long operation here
        int ctr = 1;
        //long dummy operation
        while (ctr<=12){
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
        Log.i(TAG,"onDestroy, Thread name: " + Thread.currentThread().getName());
    }
}