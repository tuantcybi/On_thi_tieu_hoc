package com.example.on_thi_tieu_hoc.View;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.Model.HistoryModel;
import com.example.on_thi_tieu_hoc.databinding.ActivityPhepCongBinding;
import com.example.on_thi_tieu_hoc.databinding.ActivityPhepTruBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Random;

public class PhepCongActivity extends AppCompatActivity {

    ActivityPhepCongBinding binding;
    int number_1 = 0 ;
    int number_2=0;
    private  String id_user ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhepCongBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Phép Cộng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FirebaseFirestore.getInstance().collection("User")
                .whereEqualTo("email", FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    id_user = queryDocumentSnapshots.getDocuments().get(0).getId();
            }
        });
       InitQuestion();

    }

    private void InitQuestion() {
        Random rd = new Random();
        number_1 = rd.nextInt(50)+1;
        number_2 = rd.nextInt(50)+1;

        binding.txtNumber1.setText(number_1+"");
        binding.txtNumber2.setText(number_2+"");
        binding.editketqua.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    if(v.getText().toString().length()>0){
                        int ketqua = Integer.parseInt(v.getText().toString().trim());
                        if(ketqua == number_1 + number_2){
                            Random rd = new Random();
                            number_1 = rd.nextInt(50)+10;
                            number_2 = rd.nextInt(10)+1;

                            binding.txtNumber1.setText(number_1+"");
                            binding.txtNumber2.setText(number_2+"");
                            binding.editketqua.setText("");
                            HistoryModel.instances.AddHistory(id_user,"Phép cộng : "+number_1+" + "+number_2+" chính xác");
                            Toast.makeText(PhepCongActivity.this, "Chính xác, tiếp tục nào ", Toast.LENGTH_SHORT).show();
                        }else{
                            HistoryModel.instances.AddHistory(id_user,"Phép cộng : "+number_1+" + "+number_2+" sai !");
                            Toast.makeText(PhepCongActivity.this, "Sai rồi ! , nhập lại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(PhepCongActivity.this, "Vui lòng nhập kết quả !", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    }
}