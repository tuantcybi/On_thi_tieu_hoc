package com.example.on_thi_tieu_hoc.Model;

import com.example.on_thi_tieu_hoc.Presenter.ToanDoView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ToanDoModel {

    private String cauhoi;
    private String dapan;
    private  String id;

    private ToanDoView callback;

    public ToanDoModel(String id,String cauhoi, String dapan) {
        this.id=id;
        this.cauhoi = cauhoi;
        this.dapan = dapan;
    }
    public ToanDoModel(String cauhoi, String dapan) {
        this.cauhoi = cauhoi;
        this.dapan = dapan;
    }

    public   ToanDoModel(ToanDoView callback){
        this.callback=callback;
    }

    public  void getDataToanDo(){
        FirebaseFirestore.getInstance().collection("ToanDo")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot q : queryDocumentSnapshots){
                    callback.getDataToanDo(q.getId(),q.getString("cauhoi"),q.getString("dapan"));
                }

            }
        });

    }

    public String getCauhoi() {
        return cauhoi;
    }

    public String getId() {
        return id;
    }

    public String getDapan() {
        return dapan;
    }
}
