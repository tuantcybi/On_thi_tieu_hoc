package com.example.on_thi_tieu_hoc;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;

public class OnThiTieuHocApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
       try {
           FirebaseApp.initializeApp(getApplicationContext());
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
