package com.example.on_thi_tieu_hoc.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.View.HomeActivity;
import com.example.on_thi_tieu_hoc.View.SignInActivity;
import com.example.on_thi_tieu_hoc.View.SignUpActivity;
import com.example.on_thi_tieu_hoc.databinding.ActivitySignInAdminBinding;
import com.example.on_thi_tieu_hoc.databinding.ActivitySignInBinding;
import com.example.on_thi_tieu_hoc.databinding.HomeActivityAdminBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInAdminActivity extends AppCompatActivity {
    private String valid_email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ActivitySignInAdminBinding binding;
    HomeActivityAdminBinding bindingHome;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInAdminBinding.inflate(getLayoutInflater());
        bindingHome =  HomeActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(bindingHome.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bindingHome.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editEmail.getText().toString().trim();
                String matkhau = binding.editMatkhau.getText().toString().trim();
                if (email.length() > 0) {
                    if (email.matches(valid_email)) {
                        if (matkhau.length() >= 6) {
                            SignInUser(email, matkhau);
                        } else {
                            Toast.makeText(SignInAdminActivity.this, "Mật khẩu tối thiểu  6 ký tự ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignInAdminActivity.this, "Email không hợp lệ ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignInAdminActivity.this, "Email không để trống !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.txtuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInAdminActivity.this, SignInActivity.class));
            }
        });

    }

    private void SignInUser(String email, String matkhau) {

        FirebaseFirestore.getInstance().collection("Admin")
                .whereEqualTo("username",email).whereEqualTo("matkhau",matkhau).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.size()>0){
                    startActivity(new Intent(SignInAdminActivity.this,HomeAdminActivity.class));

                }else{
                    Toast.makeText(SignInAdminActivity.this, "Sai tài khoản / mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
