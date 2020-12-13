package com.upstream.learningapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txtResults);
    }

    public void startBackgroundService(View view) {
        Intent intent = new Intent(this,MyBackgroundService.class);
        intent.putExtra("key","value");
        startService(intent);
    }

    public void stopBackgroundService(View view) {
        Intent intent = new Intent(this,MyBackgroundService.class);
        stopService(intent);
    }

    public void startIntentService(View view) {
        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("sleepTime",12);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("my.own.broadcast");
        LocalBroadcastManager.getInstance(this).registerReceiver(myLocalBroadcastReceiver,intentFilter);


    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myLocalBroadcastReceiver);

    }

    private BroadcastReceiver myLocalBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           int result =  intent.getIntExtra("result",-1);
           txtResult.setText("Task executed in " + result);
        }
    };
}