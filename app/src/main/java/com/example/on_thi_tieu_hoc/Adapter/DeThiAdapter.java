package com.example.on_thi_tieu_hoc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.on_thi_tieu_hoc.Model.DeThi;
import com.example.on_thi_tieu_hoc.R;

import java.util.ArrayList;

public class DeThiAdapter  extends BaseAdapter {
    private ArrayList<DeThi> arrayList;
    private Context context;

    public DeThiAdapter(ArrayList<DeThi> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.dong_dethi,null);
        TextView txttendethi = convertView.findViewById(R.id.txttendethi);

        DeThi deThi = arrayList.get(position);
        txttendethi.setText(deThi.getTen());
        return convertView;
    }
}
