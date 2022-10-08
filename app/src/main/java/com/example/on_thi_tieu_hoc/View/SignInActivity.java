package com.example.on_thi_tieu_hoc.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.Presenter.Shareconfig;
import com.example.on_thi_tieu_hoc.View.Admin.SignInAdminActivity;
import com.example.on_thi_tieu_hoc.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInActivity extends AppCompatActivity {
    private String valid_email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ActivitySignInBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                            Toast.makeText(SignInActivity.this, "Mật khẩu tối thiểu  6 ký tự ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignInActivity.this, "Email không hợp lệ ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignInActivity.this, "Email không để trống !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.txtsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
        binding.txtadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignInAdminActivity.class));
            }
        });
    }

    private void SignInUser(String email, String matkhau) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, matkhau)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                                UpdateID(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                startActivity(new Intent(SignInActivity.this, DanhMucLopHocActivity.class));
                            } else {
                                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                                Toast.makeText(SignInActivity.this, "Vui lòng vào email xác thực!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void UpdateID(String email) {
        FirebaseFirestore.getInstance().collection("User")
                .whereEqualTo("email", email).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                Shareconfig shareconfig = new Shareconfig(SignInActivity.this);
                shareconfig.putID(documentSnapshot.getId());
            }
        });
    }
}
