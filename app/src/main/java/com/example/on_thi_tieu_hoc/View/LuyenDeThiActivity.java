package com.example.on_thi_tieu_hoc.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.Adapter.DeThiAdapter;
import com.example.on_thi_tieu_hoc.Model.DeThi;
import com.example.on_thi_tieu_hoc.Presenter.DethiView;
import com.example.on_thi_tieu_hoc.R;
import com.example.on_thi_tieu_hoc.databinding.ActivityLuyenDeThiBinding;

import java.util.ArrayList;

public class LuyenDeThiActivity  extends AppCompatActivity
 implements DethiView {

    ActivityLuyenDeThiBinding binding;
    ArrayList<DeThi> arrayList;
    DeThiAdapter deThiAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLuyenDeThiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Danh sách đề thi");
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

        binding.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(LuyenDeThiActivity.this,
                        LuyenDeChiTietActivity.class).putExtra("ID",arrayList.get(position).getId()));
            }
        });



    }

    @Override
    public void getDataDeThi(String id, String ten) {
         arrayList.add(new DeThi(id,ten));
         deThiAdapter = new DeThiAdapter(arrayList,this);
         binding.lv.setAdapter(deThiAdapter);
    }
}
