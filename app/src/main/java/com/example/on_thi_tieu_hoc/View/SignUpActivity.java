package com.example.on_thi_tieu_hoc.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private String valid_email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Đăng Ký");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
            }
        });

        binding.btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editEmail.getText().toString().trim();
                String matkhau = binding.editMatkhau.getText().toString().trim();
                String matkhau_repeat = binding.editMatkhaurepeat.getText().toString().trim();
                if (email.length() > 0) {
                    if (email.matches(valid_email)) {
                        if (matkhau.length() >= 6) {
                            if (matkhau.equals(matkhau_repeat)) {
                                SignUpUser(email, matkhau);

                            } else {
                                Toast.makeText(SignUpActivity.this, "Mật khẩu không  khớp", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "Mật khẩu tối thiểu  6 ký tự ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Email không hợp lệ ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Email không để trống !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void SignUpUser(String email, String matkhau) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, matkhau)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                            Toast.makeText(SignUpActivity.this, "Vui lòng vào email để xác thực !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                            UpdateUser(email,matkhau);

                        } else {
                            Toast.makeText(SignUpActivity.this, "Đăng ký thất bại !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void UpdateUser(String email, String matkhau) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("email",email);
        hashMap.put("matkhau",matkhau);
        hashMap.put("hoten","");
        hashMap.put("diachi","");
        hashMap.put("ngaysinh","");
        FirebaseFirestore.getInstance().collection("User")
                .add(hashMap).isSuccessful();
    }
}
