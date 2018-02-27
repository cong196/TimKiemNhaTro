package com.nhatro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.nhatro.adapter.MyCustomPagerAdapter;

import java.util.HashMap;

public class Details extends AppCompatActivity {

    ViewPager viewPager;
    String images[] = {"https://pm1.narvii.com/6016/2f719b81e29dd3ca5afcc722614560f0132a98aa_hq.jpg","https://i-vnexpress.vnecdn.net/2018/02/22/xoatin-1519297927_500x300.png","https://i-vnexpress.vnecdn.net/2018/02/22/Dangian14-1519240522-4972-1519240538_500x300.jpg",
    "https://i-vnexpress.vnecdn.net/2018/02/22/cacom1-1519286181_500x300.jpg","https://i-startup.vnecdn.net/2018/02/22/57b1c6e6db5ce92c168b6a3b-960-7-9923-1621-1519292021_500x300.jpg"};
    MyCustomPagerAdapter myCustomPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent callerIntent = getIntent();
        Bundle bundle = callerIntent.getBundleExtra("iditem");
        int iditem = bundle.getInt("iditem");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(String.valueOf(iditem));

        //getSupportActionBar().hide();
        //getSupportActionBar().setTitle("Khách sạn VÍPfhgyt678797897898ghfghfghfghfgh");
        viewPager = (ViewPager) findViewById(R.id.pager);

        myCustomPagerAdapter = new MyCustomPagerAdapter(getApplicationContext(), images);
        viewPager.setAdapter(myCustomPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });



    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
