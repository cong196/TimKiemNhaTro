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

import java.util.ArrayList;

/**
 * Created by CongHoang on 3/15/2018.
 */

public class Grid_Quan_Huyen_Adapter extends ArrayAdapter {

    ArrayList<QuanHuyen> data = new ArrayList<>();


    public Grid_Quan_Huyen_Adapter(@NonNull Context context, int resource, ArrayList<QuanHuyen> data) {
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
        v = inflater.inflate(R.layout.grid_quan_huyen_item, null);
        TextView textView = (TextView) v.findViewById(R.id.txtTenQuanHuyen);

        textView.setText(data.get(position).getTen());

        boolean tmp = data.get(position).isSelect();
        if(tmp) {
            textView.setTextColor(Color.parseColor("#2196f3"));
        } else {
            textView.setTextColor(Color.parseColor("#808080"));
        }

        return v;

    }

}
