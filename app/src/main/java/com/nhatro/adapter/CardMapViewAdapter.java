package com.nhatro.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhatro.Details;
import com.nhatro.R;
import com.nhatro.model.ItemOnMapView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CongHoang on 2/25/2018.
 */

public class CardMapViewAdapter extends PagerAdapter implements CardAdapter {
    private List<CardView> mViews;
    private ArrayList<ItemOnMapView> mData;
    private float mBaseElevation;

    public CardMapViewAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }
    public CardMapViewAdapter(ArrayList<ItemOnMapView> data) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            mViews.add(null);
        }
        this.mData = data;

    }

    public void addCardItem(ItemOnMapView item) {
        mViews.add(null);
        mData.add(item);
    }

    public ItemOnMapView getItem(int position) {
        return mData.get(position);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.itemcardview, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }
        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),String.valueOf(mData.get(position).getId()),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), Details.class);
                Bundle bundle = new Bundle();
                int idItem = mData.get(position).getId();
                bundle.putInt("iditem",idItem);
                intent.putExtra("iditem",bundle);
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(ItemOnMapView item, View view) {
        TextView titleTextView = (TextView) view.findViewById(R.id.txtTitle);
        TextView address = (TextView) view.findViewById(R.id.txtAddress);
        ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        TextView price = (TextView) view.findViewById(R.id.txtPrice);

        titleTextView.setText(item.getTitle());
        address.setText(item.getAddress());
        price.setText(String.valueOf(Math.round(item.getPrice())) + " VNĐ");


        Picasso.with(view.getContext()).load("https://i-thethao.vnecdn.net/2018/02/27/Untitled-1531-1519693943_500x300.jpg").into(avatar);


        //price.setText(String.valueOf(item.getPrice()));
    }
}
