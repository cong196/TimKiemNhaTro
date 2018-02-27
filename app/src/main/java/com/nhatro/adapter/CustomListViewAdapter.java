package com.nhatro.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhatro.Details;
import com.nhatro.Filter;
import com.nhatro.R;
import com.nhatro.model.PhongTro;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CongHoang on 2/11/2018.
 */

public class CustomListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<PhongTro> data;
    ImageView isLike;
    public CustomListViewAdapter(Context activity, ArrayList<PhongTro> data) {
        this.context = activity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public PhongTro getItem(int i) {
        return this.data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.data.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.itemlistview, null);

        TextView title = (TextView) view.findViewById(R.id.txtTitle);
        TextView address = (TextView) view.findViewById(R.id.txtAddress);
        TextView price = (TextView) view.findViewById(R.id.txtPrice);
        TextView area = (TextView) view.findViewById(R.id.area);
        TextView sex = (TextView) view.findViewById(R.id.sex);
        ImageView img = (ImageView) view.findViewById(R.id.imageItemListView);
        isLike = (ImageView) view.findViewById(R.id.islike);
        isLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"LIKE",Toast.LENGTH_SHORT).show();
            }
        });

        Picasso.with(context).load("https://www.theworlds50best.com/latinamerica/en/filestore/jpg/HC-HarrySas-slider-home.jpg").into(img);


        // getting movie data for the row
        PhongTro pt = data.get(i);

        // title
        title.setText(pt.getTieude());
        address.setText(pt.getDiachi());
        price.setText(String.valueOf(pt.getGia()));
        area.setText("Diện tích: "+ String.valueOf(pt.getDientich()) + "("+ String.valueOf(pt.getChieudai())+ "m x " +
                        String.valueOf(pt.getChieurong()) + "m)");

        sex.setText(pt.getGioitinh());
        return view;
    }

}
