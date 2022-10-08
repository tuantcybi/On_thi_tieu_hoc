package com.example.on_thi_tieu_hoc.Model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class HistoryModel {
    private String id;
    private String name;
    private String date_create;

   public static HistoryModel instances = new HistoryModel();

    public HistoryModel() {

    }
   public void AddHistory(String iduser,String name){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date_create = simpleDateFormat.format(calendar.getTime());
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("date_create",date_create);
        FirebaseFirestore.getInstance().collection("History")
                .document(iduser).collection("ALL")
                .add(hashMap).isSuccessful();
    }

    public HistoryModel(String id, String name, String date_create) {
        this.id = id;
        this.name = name;
        this.date_create = date_create;
    }

    public String getId() {
        return id;
    }

    public String getDate_create() {
        return date_create;
    }

    public String getName() {
        return name;
    }
}
