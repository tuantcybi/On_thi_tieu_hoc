package com.example.on_thi_tieu_hoc.View.Admin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.Adapter.DeThiAdapter;
import com.example.on_thi_tieu_hoc.Model.DeThi;
import com.example.on_thi_tieu_hoc.Presenter.DethiView;
import com.example.on_thi_tieu_hoc.R;
import com.example.on_thi_tieu_hoc.databinding.ActivityCreateDethiBinding;
import com.example.on_thi_tieu_hoc.databinding.ActivityHomeBinding;
import com.example.on_thi_tieu_hoc.databinding.HomeActivityAdminBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeAdminActivity extends AppCompatActivity
 implements DethiView {
    ArrayList<DeThi> arrayList;
    DeThiAdapter deThiAdapter;
    HomeActivityAdminBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  HomeActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Quản Lý ADMIN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        arrayList = new ArrayList<>();
        DeThi deThi = new DeThi(this::getDataDeThi);
        deThi.HandlegetData();

        binding.CQLDETHI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(HomeAdminActivity.this,CreateDeThiActivity.class));
            }
        });

        binding.CLTOANDO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdminActivity.this,CreateToanDoActivity.class));
            }
        });



    }



    @Override
    public void getDataDeThi(String id, String ten) {

    }
}
