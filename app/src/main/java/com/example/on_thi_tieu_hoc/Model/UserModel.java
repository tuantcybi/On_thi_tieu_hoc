package com.example.on_thi_tieu_hoc.Model;

import com.example.on_thi_tieu_hoc.Presenter.ToanDoView;
import com.google.firebase.auth.FirebaseAuth;

public class UserModel {
    private  String email;
    private String pass;
    private FirebaseAuth firebaseAuth;
    private ToanDoView callback;

    public  UserModel(ToanDoView callback){
        this.callback=callback;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public UserModel(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
}
