package com.example.on_thi_tieu_hoc.Model;

import com.example.on_thi_tieu_hoc.Presenter.DethiView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DeThi {

    private  String id;
    private  String ten;
    private DethiView callback;

    public  DeThi(DethiView callback){
        this.callback=callback;

    }


    public DeThi(String id, String ten) {
        this.id = id;
        this.ten = ten;
    }
    public void HandlegetData(){
        FirebaseFirestore.getInstance().collection("Dethi")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot q : queryDocumentSnapshots){
                    callback.getDataDeThi(
                      q.getId(),
                      q.getString("ten")
                    );
                }
            }
        });
    }

    public String getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }
}
