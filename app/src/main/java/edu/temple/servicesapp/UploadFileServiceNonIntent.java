package edu.temple.servicesapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UploadFileServiceNonIntent extends Service {
    public UploadFileServiceNonIntent() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Log passed data
        Log.i("I'll upload the file ", intent.getStringExtra("some_key"));

        //Broadcast using intent this is not a targeted intent
        Intent responseIntent = new Intent();
        responseIntent.setAction(getPackageName() + ".ACTION_RESPONSE");

        //"PAUSE"
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        responseIntent.putExtra("upload_status", "upload_failed");
        sendBroadcast(responseIntent);

        //Do Work
        //Stop
        //stopSelf(startId);

        return super.onStartCommand(intent, flags, startId);
    }
}
