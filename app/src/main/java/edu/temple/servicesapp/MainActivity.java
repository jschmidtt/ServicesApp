package edu.temple.servicesapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    //Objects to get broadcast
    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra("upload_status"), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Click Button
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent
                Intent myServiceIntent = new Intent(MainActivity.this, UploadFileServiceNonIntent.class);
                myServiceIntent.putExtra("some_key", "SomeData");
                startService(myServiceIntent);
            }
        });
    }

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
}
