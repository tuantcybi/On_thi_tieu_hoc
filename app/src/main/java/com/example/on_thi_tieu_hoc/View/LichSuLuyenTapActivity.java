package com.example.on_thi_tieu_hoc.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.on_thi_tieu_hoc.Adapter.LichSuAdapter;
import com.example.on_thi_tieu_hoc.Model.HistoryModel;
import com.example.on_thi_tieu_hoc.Presenter.Shareconfig;
import com.example.on_thi_tieu_hoc.R;
import com.example.on_thi_tieu_hoc.databinding.ActivityLichSuLuyenTapBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LichSuLuyenTapActivity extends AppCompatActivity {
    ActivityLichSuLuyenTapBinding activityLichSuLuyenTapBinding;

    ArrayList<HistoryModel> arrayList;
    LichSuAdapter lichSuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLichSuLuyenTapBinding = ActivityLichSuLuyenTapBinding.inflate(getLayoutInflater());
        setContentView(activityLichSuLuyenTapBinding.getRoot());
        arrayList = new ArrayList<>();
        String iduser = new Shareconfig(this).getID();
        setSupportActionBar(activityLichSuLuyenTapBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Trở về");
        activityLichSuLuyenTapBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FirebaseFirestore.getInstance().collection("History")
                .document(iduser).collection("ALL")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot q : queryDocumentSnapshots){
                    arrayList.add(new HistoryModel(q.getId(),q.getString("name"),
                            q.getString("date_create")));
                }
                lichSuAdapter = new LichSuAdapter(arrayList,LichSuLuyenTapActivity.this);
                activityLichSuLuyenTapBinding.lv.setAdapter(lichSuAdapter);


            }
        });
    }
}