package edu.temple.servicesapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class UploadFileService extends IntentService {


    public UploadFileService() {
        super("UploadFileService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Log passed Data
        Log.i("I'll upload the file ", intent.getStringExtra("some_key"));

    }

}
