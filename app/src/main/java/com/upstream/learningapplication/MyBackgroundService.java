package com.upstream.learningapplication;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyBackgroundService extends Service {

    private static final String TAG = MyBackgroundService.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate, Thread name: "+ Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand, Thread name: "+ Thread.currentThread().getName());
        //Perform tasks: Dummy long operation
        new MyAsyncTask().execute(); //Background thread
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind, Thread name: "+ Thread.currentThread().getName());
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy, Thread name: "+ Thread.currentThread().getName());
        super.onDestroy();
    }
    class MyAsyncTask extends AsyncTask<Void,String,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG,"onPreExecute, Thread name: "+ Thread.currentThread().getName());


        }

        //perform tasks in bg or worker thread
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG,"doInBackground, Thread name: "+ Thread.currentThread().getName());
            int ctr = 1;
           //long dummy operation
            while (ctr<=12){
                publishProgress("Time elapsed: " + ctr + " secs");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ctr++;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.d(TAG,"onProgressUpdate, Counter Value" + values[0] +"Thread name: "+ Thread.currentThread().getName());
            super.onProgressUpdate(values);
            Toast.makeText(MyBackgroundService.this, values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d(TAG,"onPostExecute, Thread name: "+ Thread.currentThread().getName());
            super.onPostExecute(aVoid);

            stopSelf();
        }



    }
}
