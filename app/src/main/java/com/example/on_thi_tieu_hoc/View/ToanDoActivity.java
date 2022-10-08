package com.example.on_thi_tieu_hoc.View;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.Model.ToanDoModel;
import com.example.on_thi_tieu_hoc.Presenter.ToanDoView;
import com.example.on_thi_tieu_hoc.databinding.ActivityTimXBinding;
import com.example.on_thi_tieu_hoc.databinding.ActivityToandoBinding;

import java.util.ArrayList;
import java.util.Random;

public class ToanDoActivity extends AppCompatActivity
 implements ToanDoView {

    ActivityToandoBinding binding;
    int number_1 = 0 ;
    int number_2=0;
    ArrayList<ToanDoModel> arrayList;
    int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityToandoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Toán đố");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       InitQuestion();

    }

    private void InitQuestion() {
         arrayList = new ArrayList<>();
         ToanDoModel toanDoModel = new ToanDoModel(this);
         toanDoModel.getDataToanDo();

         binding.editketqua.setOnEditorActionListener(new TextView.OnEditorActionListener() {
             @Override
             public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                 return true;
             }
         });

    }

    @Override
    public void getDataToanDo(String id,String cauhoi, String dapan) {
        arrayList.add(new ToanDoModel(cauhoi,dapan));
        InitCauHoi(k);
    }

    private void InitCauHoi(int pos) {

        ToanDoModel toanDoModel = arrayList.get(pos);
        binding.txtcauhoi.setText(toanDoModel.getCauhoi());

    }
}