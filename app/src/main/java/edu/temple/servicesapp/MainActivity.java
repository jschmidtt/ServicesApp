package edu.temple.servicesapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    //Uncomment for broadcast type of service
    /*
    //Objects to get broadcast
    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra("upload_status"), Toast.LENGTH_LONG).show();
        }
    };
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Click Button
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected) myService.uploadContent("Text to upload.");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Define intent and bindService
        Intent myServiceIntent = new Intent(MainActivity.this, BoundService.class);
        myServiceIntent.putExtra("inerface","simple");
        bindService(myServiceIntent, connection, Context.BIND_AUTO_CREATE);

    }

    //Uncomment for broadcast type of service
    /*
    //Register here
    @Override
    protected void onResume() {
        super.onResume();
        //New intent filter object for register
        IntentFilter filter = new IntentFilter();
        filter.addAction(getPackageName() + ".ACTION_RESPONSE");
        registerReceiver(myReceiver, filter);
    }

    //UnRegister here
    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(myReceiver);

    }
    */

    //Binder
    BoundService.ServiceBinder binder;

    BoundService myService;

    boolean isConnected;

    //Creating service connection to be passed
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Cast
            binder = (BoundService.ServiceBinder) service;
            myService = binder.getService();
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };
}
