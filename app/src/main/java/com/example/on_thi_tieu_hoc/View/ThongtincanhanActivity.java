package com.example.on_thi_tieu_hoc.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.on_thi_tieu_hoc.R;
import com.example.on_thi_tieu_hoc.databinding.ActivityThongtincanhanBinding;
import com.example.on_thi_tieu_hoc.databinding.DialogTtCanhanBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ThongtincanhanActivity extends AppCompatActivity {
    ActivityThongtincanhanBinding activityThongtincanhanBinding;
    private  String id_user="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityThongtincanhanBinding = ActivityThongtincanhanBinding.inflate(getLayoutInflater());
        setContentView(activityThongtincanhanBinding.getRoot());
        setSupportActionBar(activityThongtincanhanBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin cá nhân");
        activityThongtincanhanBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        FirebaseFirestore.getInstance().collection("User")
                .whereEqualTo("email", email).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                DocumentSnapshot queryDocumentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                id_user = queryDocumentSnapshot.getId();
                activityThongtincanhanBinding.txtemail.setText(email);
                activityThongtincanhanBinding.txtdiachi.setText(queryDocumentSnapshot.getString("diachi"));
                activityThongtincanhanBinding.txthoten.setText(queryDocumentSnapshot.getString("hoten"));
                activityThongtincanhanBinding.txtngaysinh.setText(queryDocumentSnapshot.getString("ngaysinh"));

            }
        });

        activityThongtincanhanBinding.txthoten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogUpdate(1);
            }
        });
        activityThongtincanhanBinding.txtdiachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogUpdate(2);
            }
        });
        activityThongtincanhanBinding.txtngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogUpdate(3);
            }
        });
        activityThongtincanhanBinding.btnlichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongtincanhanActivity.this,LichSuLuyenTapActivity.class));
            }
        });
        activityThongtincanhanBinding.btnlichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongtincanhanActivity.this,LichSuLuyenTapActivity.class));
            }
        });


    }

    private void ShowDialogUpdate(int i) {
        DialogTtCanhanBinding dl_bind;
        dl_bind = DialogTtCanhanBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(this);
        dialog.setContentView(dl_bind.getRoot());
        dialog.show();

         switch (i){
             case 1: dl_bind.editthongtin.setHint("Nhập họ tên");break;
             case 2: dl_bind.editthongtin.setHint("Nhập địa chỉ");break;
             case 3: dl_bind.editthongtin.setHint("Nhập ngày sinh");break;
         }
         dl_bind.btnxacnhan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String value = dl_bind.editthongtin.getText().toString().trim();
                 switch (i){
                     case  1: activityThongtincanhanBinding.txthoten.setText(value);
                         UpdateTT(value,"hoten");break;
                     case  2: activityThongtincanhanBinding.txtdiachi.setText(value);
                         UpdateTT(value,"diachi");break;
                     case  3: activityThongtincanhanBinding.txtngaysinh.setText(value);
                         UpdateTT(value,"ngaysinh");break;
                 }
                 dialog.cancel();
             }
         });

    }

    private void UpdateTT(String value, String key) {

        FirebaseFirestore.getInstance().collection("User")
                .document(id_user).update(key,value).isSuccessful();
    }
}