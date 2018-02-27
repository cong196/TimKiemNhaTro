package com.nhatro;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

import io.apptik.widget.MultiSlider;


public class Filter extends AppCompatActivity {


    private int min = 0;
    private int max = 10000000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().hide();
        getSupportActionBar().setTitle("Bộ lọc");
        final TextView minValue = (TextView) findViewById(R.id.minValue);
        final TextView maxValue = (TextView) findViewById(R.id.maxValue);
        final MultiSlider  multiSlider5 = (MultiSlider)findViewById(R.id.sliderArea);
        multiSlider5.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider,
                                       MultiSlider.Thumb thumb,
                                       int thumbIndex,
                                       int value)
            {
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                String tmp = formatter.format(value)+ " VNĐ";

                if (thumbIndex == 0) {
                    //doSmth(String.valueOf(value));
                    min = value;
                    minValue.setText(tmp);
                } else {
                    maxValue.setText(tmp);
                    max = value;
                }
            }

        });


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
