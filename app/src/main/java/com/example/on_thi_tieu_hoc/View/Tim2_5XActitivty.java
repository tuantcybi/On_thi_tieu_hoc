package com.example.on_thi_tieu_hoc.View;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.on_thi_tieu_hoc.databinding.ActivityTimX25Binding;
import com.example.on_thi_tieu_hoc.databinding.ActivityTimXBinding;

import java.util.Random;

public class Tim2_5XActitivty extends AppCompatActivity {

    ActivityTimX25Binding binding;
    int number_1 = 0 ;
    int number_2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimX25Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Tìm X");
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
        Random rd = new Random();
        number_2 = rd.nextInt(50)+1;
        int r_d_pheptinh=  rd.nextInt(3)+0;
        switch (r_d_pheptinh){
            case 0 : binding.txtpheptinh.setText("+");break;
            case 1 : binding.txtpheptinh.setText("-");break;
            case 2 : binding.txtpheptinh.setText("x");break;
            case 3 : binding.txtpheptinh.setText("/");break;
        }
        if(r_d_pheptinh==0){
            number_1 = rd.nextInt(10)+1;
        }else if(r_d_pheptinh==2){
            number_1= rd.nextInt(10)+1;
            number_2*=number_1;
        }else if(r_d_pheptinh==3){
            number_1 = rd.nextInt(50)+1;
        }else if(r_d_pheptinh==1){
            number_1 = rd.nextInt(50)+1;
        }

        binding.txtNumber1.setText(number_1+"");
        binding.txtNumber2.setText(number_2+"");
        binding.editketqua.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    String pheptinh =binding.txtpheptinh.getText().toString().trim();
                    if(pheptinh.equals("-")){
                        if(v.getText().toString().length()>0){
                            int ketqua = Integer.parseInt(v.getText().toString().trim());
                            if(ketqua == number_1 + number_2){
                                Random rd = new Random();
                                number_2 = rd.nextInt(50)+1;
                                int r_d_pheptinh=  rd.nextInt(3)+0;
                                switch (r_d_pheptinh){
                                    case 0 : binding.txtpheptinh.setText("+");break;
                                    case 1 : binding.txtpheptinh.setText("-");break;
                                    case 2 : binding.txtpheptinh.setText("x");break;
                                    case 3 : binding.txtpheptinh.setText("/");break;
                                }
                                if(r_d_pheptinh==0){
                                    number_1 = rd.nextInt(10)+1;
                                }else if(r_d_pheptinh==2){
                                    number_1= rd.nextInt(10)+1;
                                    number_2*=number_1;
                                }else if(r_d_pheptinh==3){
                                    number_1 = rd.nextInt(50)+1;
                                }else if(r_d_pheptinh==1){
                                    number_1 = rd.nextInt(50)+1;
                                }


                                binding.txtNumber1.setText(number_1+"");
                                binding.txtNumber2.setText(number_2+"");
                                binding.editketqua.setText("");
                                Toast.makeText(Tim2_5XActitivty.this, "Chính xác, tiếp tục nào ", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Tim2_5XActitivty.this, "Sai rồi ! , nhập lại", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Tim2_5XActitivty.this, "Vui lòng nhập kết quả !", Toast.LENGTH_SHORT).show();
                        }
                    }else if(pheptinh.equals("+")){
                        int ketqua = Integer.parseInt(v.getText().toString().trim());
                        if(ketqua == number_2 - number_1){
                            Random rd = new Random();
                            number_2 = rd.nextInt(50)+1;
                            int r_d_pheptinh=  rd.nextInt(3)+0;
                            switch (r_d_pheptinh){
                                case 0 : binding.txtpheptinh.setText("+");break;
                                case 1 : binding.txtpheptinh.setText("-");break;
                                case 2 : binding.txtpheptinh.setText("x");break;
                                case 3 : binding.txtpheptinh.setText("/");break;
                            }
                            if(r_d_pheptinh==0){
                                number_1 = rd.nextInt(10)+1;
                            }else if(r_d_pheptinh==2){
                                number_1= rd.nextInt(10)+1;
                                number_2*=number_1;
                            }else if(r_d_pheptinh==3){
                                number_1 = rd.nextInt(50)+1;
                            }else if(r_d_pheptinh==1){
                                number_1 = rd.nextInt(50)+1;
                            }


                            binding.txtNumber1.setText(number_1+"");
                            binding.txtNumber2.setText(number_2+"");
                            binding.editketqua.setText("");
                            Toast.makeText(Tim2_5XActitivty.this, "Chính xác, tiếp tục nào ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Tim2_5XActitivty.this, "Sai rồi ! , nhập lại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(pheptinh.equals("x")){
                        int ketqua = Integer.parseInt(v.getText().toString().trim());
                        if(ketqua == number_2 / number_1){
                            Random rd = new Random();
                            number_2 = rd.nextInt(50)+1;
                            int r_d_pheptinh=  rd.nextInt(3)+0;
                            switch (r_d_pheptinh){
                                case 0 : binding.txtpheptinh.setText("+");break;
                                case 1 : binding.txtpheptinh.setText("-");break;
                                case 2 : binding.txtpheptinh.setText("x");break;
                                case 3 : binding.txtpheptinh.setText("/");break;
                            }
                            if(r_d_pheptinh==0){
                                number_1 = rd.nextInt(10)+1;
                            }else if(r_d_pheptinh==2){
                                number_1= rd.nextInt(10)+1;
                                number_2*=number_1;
                            }else if(r_d_pheptinh==3){
                                number_1 = rd.nextInt(50)+1;
                            }else if(r_d_pheptinh==1){
                                number_1 = rd.nextInt(50)+1;
                            }


                            binding.txtNumber1.setText(number_1+"");
                            binding.txtNumber2.setText(number_2+"");
                            binding.editketqua.setText("");
                            Toast.makeText(Tim2_5XActitivty.this, "Chính xác, tiếp tục nào ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Tim2_5XActitivty.this, "Sai rồi ! , nhập lại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        int ketqua = Integer.parseInt(v.getText().toString().trim());
                        if(ketqua == number_2 * number_1){
                            Random rd = new Random();
                            number_2 = rd.nextInt(50)+1;
                            int r_d_pheptinh=  rd.nextInt(3)+0;
                            switch (r_d_pheptinh){
                                case 0 : binding.txtpheptinh.setText("+");break;
                                case 1 : binding.txtpheptinh.setText("-");break;
                                case 2 : binding.txtpheptinh.setText("x");break;
                                case 3 : binding.txtpheptinh.setText("/");break;
                            }
                            if(r_d_pheptinh==0){
                                number_1 = rd.nextInt(10)+1;
                            }else if(r_d_pheptinh==2){
                                number_1= rd.nextInt(10)+1;
                                number_2*=number_1;
                            }else if(r_d_pheptinh==3){
                                number_1 = rd.nextInt(50)+1;
                            }else if(r_d_pheptinh==1){
                                number_1 = rd.nextInt(50)+1;
                            }

                            binding.txtNumber1.setText(number_1+"");
                            binding.txtNumber2.setText(number_2+"");
                            binding.editketqua.setText("");
                            Toast.makeText(Tim2_5XActitivty.this, "Chính xác, tiếp tục nào ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Tim2_5XActitivty.this, "Sai rồi ! , nhập lại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                return true;
            }
        });
    }
}