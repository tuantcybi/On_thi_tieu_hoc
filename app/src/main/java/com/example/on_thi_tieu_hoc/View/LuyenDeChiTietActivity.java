package com.example.on_thi_tieu_hoc.View;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.R;
import com.example.on_thi_tieu_hoc.databinding.ActivityLuyendeChitietBinding;

import java.util.Random;

public class LuyenDeChiTietActivity  extends AppCompatActivity {
    ActivityLuyendeChitietBinding  binding;

    Random rd_1= new Random();
    Random rd_2= new Random();
    int number_1_1 =0;
    int number_1_2 =0;
    int number_2_1 =0;
    int number_2_2 =0;
    int number_1_1_X =0;
    int number_1_2_X =0;
    int number_2_2_X =0;
    int number_2_1_X =0;
    int number_1TD=0;
    int number_2TD=0;
    String cauhoi = "";
    int diem_1=0;
    int diem_2=0;
    int diem_3=0;
    int diem_4=0;
    int diem_5=0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLuyendeChitietBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Luyện Thi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        InitC1();
        InitC2();
        InitC3();

        binding.btnnopbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ketqua_1 = binding.editKetQua1.getText().toString().trim();
                String ketqua_2 = binding.editKetQua2.getText().toString().trim();
                String ketqua_1_X = binding.editKetQuaX1.getText().toString().trim();
                String ketqua_2_X = binding.editKetQuaX2.getText().toString().trim();
                String ketqua_3_TD = binding.editdapAn.getText().toString().trim();
                if(ketqua_1.length()>0){
                    if(ketqua_2.length()>0){
                       int number_a = Integer.parseInt(ketqua_1);
                       int number_b = Integer.parseInt(ketqua_2);
                       if(number_a == number_1_1+number_1_2){
                           diem_1=2;
                       }else{
                           diem_1=0;
                       }
                        if(number_b == number_2_1 - number_2_2){
                            diem_2=2;
                        }else{
                            diem_2=0;
                        }
                        if(ketqua_1_X.length()>0){
                            if(ketqua_2_X.length()>0){
                                int number_a_x = Integer.parseInt(ketqua_1_X);
                                int number_b_x = Integer.parseInt(ketqua_2_X);
                                if(number_a_x == number_1_2_X - number_1_1_X){
                                    diem_3=2;
                                }else{
                                    diem_3=0;
                                }
                                if(number_b_x == number_2_1_X + number_2_2_X){
                                    diem_4=2;
                                }else{
                                    diem_4=0;
                                }
                                
                                if(ketqua_3_TD.length()>0){
                                    int number_KQ = Integer.parseInt(ketqua_3_TD);
                                    if(number_KQ == number_1TD+ number_2TD){
                                        diem_5=2;
                                    }else{
                                        diem_5=0;
                                    }
                                    int tongdiem = diem_1+diem_2+diem_3+diem_4+diem_5;
                                    ShowDiaLogKetQua(tongdiem);
                                }else{
                                    Toast.makeText(LuyenDeChiTietActivity.this, "", Toast.LENGTH_SHORT).show();
                                }
                               


                            }else{
                                Toast.makeText(LuyenDeChiTietActivity.this, "Câu 2 ý b không để trống ", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LuyenDeChiTietActivity.this, "Câu 2 ý a không để trống ", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(LuyenDeChiTietActivity.this, "Câu 1 ý b không để trống ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LuyenDeChiTietActivity.this, "Câu 1 ý a không để trống ", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void ShowDiaLogKetQua(int tongdiem) {
        Dialog dialog =new Dialog(this);
        dialog.setContentView(R.layout.dialog_final);
        dialog.show();
        TextView txttongdiem = dialog.findViewById(R.id.txttongdiem);

        txttongdiem.setText("Tổng điểm : "+tongdiem);
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

    }

    private void InitC3() {
        number_1TD = rd_1.nextInt(50)+1;
        number_2TD = rd_1.nextInt(50)+1;
        cauhoi = " Có "+number_1TD+" con vịt dưới nước và "+number_2TD+" con vịt trên bờ. Hỏi" +
                " có bao nhiêu con vịt";
        binding.txttoando.setText(cauhoi);
    }

    private void InitC1() {
        number_1_1 = rd_1.nextInt(50)+1;
        number_1_2 = rd_1.nextInt(50)+1;
        binding.txtnumber1.setText(number_1_1+"");
        binding.txtnumber2.setText(number_1_2+"");
        number_2_1 = rd_1.nextInt(50)+10;
        number_2_2 = rd_1.nextInt(10)+1;
        binding.txtnumber12.setText(number_2_1+"");
        binding.txtnumber22.setText(number_2_2+"");


    }
    private void InitC2() {
        number_1_1_X = rd_1.nextInt(10)+1;
        number_1_2_X = rd_1.nextInt(90)+10;
        binding.txtnumberX1.setText(number_1_1_X+"");
        binding.txtnumberX2.setText(number_1_2_X+"");
        number_2_1_X = rd_1.nextInt(50)+10;
        number_2_2_X = rd_1.nextInt(50)+10;
        binding.txtnumberX12.setText(number_2_1_X+"");
        binding.txtnumberX22.setText(number_2_2_X+"");


    }
}
