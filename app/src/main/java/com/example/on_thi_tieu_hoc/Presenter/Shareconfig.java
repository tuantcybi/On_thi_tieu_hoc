package com.example.on_thi_tieu_hoc.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

public class Shareconfig {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public  Shareconfig(Context context){
        sharedPreferences = context.getSharedPreferences("ID",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public  void putID(String iduser){
        editor.putString("ID",iduser);
        editor.commit();
    }
    public  String getID(){
        return  sharedPreferences.getString("ID","");
    }
}
