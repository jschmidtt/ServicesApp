package edu.temple.servicesapp;

import android.app.Service;
import android.content.Intent;
import android.media.Image;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

public class BoundService extends Service {
    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //Return my binder
        /*
        if("advanced".equals(intent.getStringExtra("interface")))
            return new UploadBinder();
        else
            return new SimpleUploadBinder();
         */
        return new ServiceBinder();
    }

    //Functionality "upload something"
    //This could be used within binder methods
    public void uploadContent(Object object){
        Log.d("tag",object.toString());
    }

    //Create our Binder
    //The methods here are what the clients will see/use
    //Advanced binder
    class UploadBinder extends Binder{

        //Method exposed to clients
        public void uploadText(String textToUpload){
            //PreProcess Text
            //within binder
            uploadContent(textToUpload);
        }

        //Method exposed to client
        public void uploadImage(File image){
            uploadContent(image.getAbsolutePath());
        }
    }

    //Simple Binder
    class SimpleUploadBinder extends Binder{
        public void upload(Object object){
            uploadContent(object);
        }
    }

    //Another Method of a Binder
    class ServiceBinder extends Binder{
        public BoundService getService(){
            return BoundService.this;
        }
    }
}
