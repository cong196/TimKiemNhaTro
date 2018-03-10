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
import com.nhatro.model.Item_Grid_Facilities;

import java.util.ArrayList;

/**
 * Created by CongHoang on 3/1/2018.
 */

public class Grid_Facilities_Adapter extends ArrayAdapter {

    ArrayList<Item_Grid_Facilities> data = new ArrayList<>();

    public Grid_Facilities_Adapter(@NonNull Context context, int resource, ArrayList<Item_Grid_Facilities> data) {
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
        v = inflater.inflate(R.layout.grid_facilities_items, null);
        TextView textView = (TextView) v.findViewById(R.id.txtTitleIcon);
        ImageView imageView = (ImageView) v.findViewById(R.id.iconx);

        textView.setText(data.get(position).getName());
        imageView.setImageResource(data.get(position).getImg());

        boolean tmp = data.get(position).isSelected();
        if(tmp) {
            textView.setTextColor(Color.parseColor("#2196f3"));
            imageView.setColorFilter(Color.parseColor("#2196f3"));
        } else {
            textView.setTextColor(Color.parseColor("#808080"));
            imageView.setColorFilter(Color.parseColor("#808080"));
        }

        return v;

    }
}
