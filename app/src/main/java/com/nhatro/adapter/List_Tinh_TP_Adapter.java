package com.nhatro.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhatro.R;
import com.nhatro.model.QuanHuyen;
import com.nhatro.model.TinhTP;

import java.util.ArrayList;

/**
 * Created by CongHoang on 3/17/2018.
 */

public class List_Tinh_TP_Adapter extends ArrayAdapter {
    ArrayList<TinhTP> data = new ArrayList<>();


    public List_Tinh_TP_Adapter(@NonNull Context context, int resource, ArrayList<TinhTP> data) {
        super(context, resource);
        this.data = data;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_list_tinh_tp, null);
        TextView textView = (TextView) v.findViewById(R.id.txtTenTP);
        ImageView img = (ImageView) v.findViewById(R.id.isChecked);

        textView.setText(data.get(position).getTen());

        boolean tmp = data.get(position).isSelect();
        if(tmp) {
            img.setVisibility(View.VISIBLE);
        } else {
            img.setVisibility(View.GONE);
        }

        return v;

    }
}
