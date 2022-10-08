package com.example.on_thi_tieu_hoc.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.databinding.ActivityHomeBinding;
import com.example.on_thi_tieu_hoc.databinding.ActivityHomeLop25Binding;

public class HomeLop2_5Activity extends AppCompatActivity {

    ActivityHomeLop25Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeLop25Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Trở về");
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.cTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeLop2_5Activity.this,PhepTruActivity.class));
            }
        });
        binding.cCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeLop2_5Activity.this,PhepCongActivity.class));
            }
        });
        binding.cNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeLop2_5Activity.this,PhepNhanActivity.class));
            }
        });
        binding.cChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeLop2_5Activity.this,PhepChiaActivity.class));
            }
        });
        binding.cTimX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeLop2_5Activity.this,Tim2_5XActitivty.class));
            }
        });
        binding.cToanDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeLop2_5Activity.this,ToanDoActivity.class));
            }
        });
        binding.cLuyenDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeLop2_5Activity.this,LuyenDeThiActivity.class));
            }
        });
    }
}