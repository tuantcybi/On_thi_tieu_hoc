package com.example.on_thi_tieu_hoc.View.Admin;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.Adapter.DeThiAdapter;
import com.example.on_thi_tieu_hoc.Adapter.ToanDoAdapter;
import com.example.on_thi_tieu_hoc.Model.DeThi;
import com.example.on_thi_tieu_hoc.Model.ToanDoModel;
import com.example.on_thi_tieu_hoc.Presenter.DethiView;
import com.example.on_thi_tieu_hoc.Presenter.ToanDoView;
import com.example.on_thi_tieu_hoc.R;
import com.example.on_thi_tieu_hoc.databinding.ActivityCreateDethiBinding;
import com.example.on_thi_tieu_hoc.databinding.ActivityCreateToandoBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateToanDoActivity extends AppCompatActivity
 implements ToanDoView {
    ArrayList<ToanDoModel> arrayList;
    ToanDoAdapter deThiAdapter;
    ActivityCreateToandoBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityCreateToandoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Quản lý toán đố");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        arrayList = new ArrayList<>();
       ToanDoModel toanDoModel = new ToanDoModel(this);
        toanDoModel.getDataToanDo();

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

    private void ShowDiaLogUpdate(ToanDoModel deThi) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_toando);
        dialog.show();
        EditText editten= dialog.findViewById(R.id.editTendithi);
        EditText editdapan= dialog.findViewById(R.id.editdapan);
        editdapan.setText(deThi.getDapan()+"");
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        editten.setText(deThi.getCauhoi());
        dialog.findViewById(R.id.btncapnhat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = editten.getText().toString().trim();
                String dapan = editdapan.getText().toString().trim();
                if(ten.length()>0){
                    FirebaseFirestore.getInstance().collection("ToanDo")
                            .document(deThi.getId()).update("cauhoi",ten,"dapan",dapan).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                arrayList.clear();
                                ToanDoModel toanDoModel = new ToanDoModel(CreateToanDoActivity.this);
                                toanDoModel.getDataToanDo();
                                dialog.cancel();
                            }
                        }
                    });
                }else{
                    Toast.makeText(CreateToanDoActivity.this, "Vui lòng nhập tên đề thi !", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.findViewById(R.id.btnxoa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = editten.getText().toString().trim();
                if(ten.length()>0){
                    FirebaseFirestore.getInstance().collection("ToanDo")
                            .document(deThi.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                arrayList.clear();
                                ToanDoModel toanDoModel = new ToanDoModel(CreateToanDoActivity.this);
                                toanDoModel.getDataToanDo();
                                dialog.cancel();
                            }
                        }
                    });
                }else{
                    Toast.makeText(CreateToanDoActivity.this, "Vui lòng nhập tên đề thi !", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void ShowAddDethi() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_toando);
        dialog.show();
        EditText editten= dialog.findViewById(R.id.editTendithi);
        EditText editdapan= dialog.findViewById(R.id.editdapan);
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
                String dapan = editdapan.getText().toString().trim();
                if(ten.length()>0){
                    if(dapan.length()>0){
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("cauhoi",ten);
                        hashMap.put("dapan",dapan);
                        FirebaseFirestore.getInstance().collection("ToanDo")
                                .add(hashMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()){
                                    arrayList.clear();
                                    ToanDoModel deThi = new ToanDoModel(CreateToanDoActivity.this);
                                    deThi.getDataToanDo();
                                    dialog.cancel();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(CreateToanDoActivity.this, "Đáp án không để trống", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(CreateToanDoActivity.this, "Vui lòng nhập tên đề thi !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    public void getDataToanDo(String id,String cauhoi, String dapan) {
        arrayList.add(new ToanDoModel(id,cauhoi,dapan));
        deThiAdapter = new ToanDoAdapter(arrayList,this);
        binding.lv.setAdapter(deThiAdapter);
    }
}
