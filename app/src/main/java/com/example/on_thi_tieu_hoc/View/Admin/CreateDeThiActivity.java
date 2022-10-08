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
import com.example.on_thi_tieu_hoc.View.LuyenDeChiTietActivity;
import com.example.on_thi_tieu_hoc.View.LuyenDeThiActivity;
import com.example.on_thi_tieu_hoc.databinding.ActivityCreateDethiBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateDeThiActivity  extends AppCompatActivity
 implements DethiView {
    ArrayList<DeThi> arrayList;
    DeThiAdapter deThiAdapter;
    ActivityCreateDethiBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityCreateDethiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Quản lý đề thi");
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

        binding.createdethi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddDethi();
            }
        });

        binding.lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ShowDiaLogUpdate(arrayList.get(position));
                return true;
            }
        });



    }

    private void ShowDiaLogUpdate(DeThi deThi) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update);
        dialog.show();
        EditText editten= dialog.findViewById(R.id.editTendithi);
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        editten.setText(deThi.getTen());
        dialog.findViewById(R.id.btncapnhat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = editten.getText().toString().trim();
                if(ten.length()>0){
                    FirebaseFirestore.getInstance().collection("Dethi")
                            .document(deThi.getId()).update("ten",ten).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                arrayList.clear();
                                DeThi deThi = new DeThi(CreateDeThiActivity.this);
                                deThi.HandlegetData();
                                dialog.cancel();
                            }
                        }
                    });
                }else{
                    Toast.makeText(CreateDeThiActivity.this, "Vui lòng nhập tên đề thi !", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.findViewById(R.id.btnxoa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = editten.getText().toString().trim();
                if(ten.length()>0){
                    FirebaseFirestore.getInstance().collection("Dethi")
                            .document(deThi.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                arrayList.clear();
                                DeThi deThi = new DeThi(CreateDeThiActivity.this);
                                deThi.HandlegetData();
                                dialog.cancel();
                            }
                        }
                    });
                }else{
                    Toast.makeText(CreateDeThiActivity.this, "Vui lòng nhập tên đề thi !", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void ShowAddDethi() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_dethi);
        dialog.show();
        EditText editten= dialog.findViewById(R.id.editTendithi);
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.btnxacnhan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = editten.getText().toString().trim();
                if(ten.length()>0){

                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("ten",ten);
                    FirebaseFirestore.getInstance().collection("Dethi")
                            .add(hashMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                arrayList.clear();
                                DeThi deThi = new DeThi(CreateDeThiActivity.this);
                                deThi.HandlegetData();
                                dialog.cancel();
                            }
                        }
                    });
                }else{
                    Toast.makeText(CreateDeThiActivity.this, "Vui lòng nhập tên đề thi !", Toast.LENGTH_SHORT).show();
                }
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
