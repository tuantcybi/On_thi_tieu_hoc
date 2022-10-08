package com.example.on_thi_tieu_hoc.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.R;
import com.example.on_thi_tieu_hoc.databinding.ActivityHomeDanhmucBinding;

public class DanhMucLopHocActivity  extends AppCompatActivity
 implements  View.OnClickListener{
    ActivityHomeDanhmucBinding activityHomeDanhmucBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeDanhmucBinding = ActivityHomeDanhmucBinding.inflate(getLayoutInflater());
        setContentView(activityHomeDanhmucBinding.getRoot());
        setSupportActionBar(activityHomeDanhmucBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Trở về");
        activityHomeDanhmucBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activityHomeDanhmucBinding.cLop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhMucLopHocActivity.this,HomeLop1Activity.class));
            }
        });
        activityHomeDanhmucBinding.cLop2.setOnClickListener(this::onClick);
        activityHomeDanhmucBinding.cLop3.setOnClickListener(this::onClick);
        activityHomeDanhmucBinding.cLop4.setOnClickListener(this::onClick);
        activityHomeDanhmucBinding.cLop5.setOnClickListener(this::onClick);
        activityHomeDanhmucBinding.person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhMucLopHocActivity.this,ThongtincanhanActivity.class));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cLop2:
              case   R.id.cLop3:
            case  R.id.cLop4:
            case  R.id.cLop5:
                startActivity(new Intent(DanhMucLopHocActivity.this,HomeLop2_5Activity.class));
        }
    }
}
