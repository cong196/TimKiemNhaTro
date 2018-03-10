package com.nhatro;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nhatro.adapter.ExpandableHeightGridView;
import com.nhatro.adapter.Grid_Facilities_Adapter;
import com.nhatro.model.Item_Grid_Facilities;

import java.text.DecimalFormat;
import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;
import io.apptik.widget.MultiSlider;


public class Filter extends AppCompatActivity {


    private int min = 0;
    private int max = 10000000;

    private int minArea = 0;
    private int maxArea = 200;

    GridView gridFacilities;
    ArrayList<Item_Grid_Facilities> lstFacilities = new ArrayList<>();

    boolean[] selectedFacitilies;
    boolean sex;


    TextView btnOK, btnCancel;
    RadioButton radNam, radNu;
    SegmentedGroup radGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().hide();
        getSupportActionBar().setTitle("Bộ lọc");
        final TextView minValue = (TextView) findViewById(R.id.minValue);
        final TextView maxValue = (TextView) findViewById(R.id.maxValue);
        final TextView minValueArea = (TextView) findViewById(R.id.minArea);
        final TextView maxValueArea = (TextView) findViewById(R.id.maxArea);
        final MultiSlider multiSlider5 = (MultiSlider) findViewById(R.id.sliderArea);
        final MultiSlider multiSliderArea = (MultiSlider) findViewById(R.id.sliderArea2);


        multiSliderArea.getThumb(0).setValue(minArea);
        multiSliderArea.getThumb(1).setValue(maxArea);
        minValueArea.setText(String.valueOf(minArea) + " m2");
        maxValueArea.setText(String.valueOf(maxArea) + " m2");
        multiSliderArea.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider,
                                       MultiSlider.Thumb thumb,
                                       int thumbIndex,
                                       int value) {
                if (thumbIndex == 0) {
                    minArea = value;
                    minValueArea.setText(String.valueOf(minArea) + " m2");
                } else {
                    maxArea = value;
                    maxValueArea.setText(String.valueOf(maxArea) + " m2");
                }
            }

        });




        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        sex = bundle.getBoolean("sex");
        min = bundle.getInt("minPrice");
        max = bundle.getInt("maxPrice");
        multiSlider5.getThumb(0).setValue(min);

        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String tmp = formatter.format(min) + " VNĐ";
        minValue.setText(tmp);

        multiSlider5.getThumb(1).setValue(max);

        tmp = formatter.format(max) + " VNĐ";
        maxValue.setText(tmp);

        multiSlider5.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider,
                                       MultiSlider.Thumb thumb,
                                       int thumbIndex,
                                       int value) {
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                String tmp = formatter.format(value) + " VNĐ";

                if (thumbIndex == 0) {
                    //doSmth(String.valueOf(value));
                    //HomeFragment.minSlider = value;

                    min = value;
                    minValue.setText(tmp);
                } else {
                    //HomeFragment.maxSlider = value;
                    maxValue.setText(tmp);
                    max = value;
                }
            }

        });


        //selectedFacitilies = HomeFragment.selectedFacilitiess;

//        selectedFacitilies = new boolean[15];
//        for (int i = 0; i < 15; i++) {
//            selectedFacitilies[i] = false;
//            selectedFacitilies[i] = HomeFragment.selectedFacilitiess[i];
//        }

        selectedFacitilies = bundle.getBooleanArray("arrFacilities");

        gridFacilities = (GridView) findViewById(R.id.gridFacilities);
        ExpandableHeightGridView gridFacilities = (ExpandableHeightGridView) findViewById(R.id.gridFacilities);
        //ExpandableHeightGridView gridView = new ExpandableHeightGridView(this);

        gridFacilities.setExpanded(true);

        lstFacilities.add(new Item_Grid_Facilities("Wifi", R.drawable.icons_wi_fi, selectedFacitilies[0]));
        lstFacilities.add(new Item_Grid_Facilities("Gác", R.drawable.icons_wi_fi, selectedFacitilies[1]));
        lstFacilities.add(new Item_Grid_Facilities("Toilet riêng", R.drawable.icons_wi_fi, selectedFacitilies[2]));
        lstFacilities.add(new Item_Grid_Facilities("Phòng tắm riêng", R.drawable.icons_wi_fi, selectedFacitilies[3]));
        lstFacilities.add(new Item_Grid_Facilities("Giường", R.drawable.icons_wi_fi, selectedFacitilies[4]));
        lstFacilities.add(new Item_Grid_Facilities("Tivi", R.drawable.icons_wi_fi, selectedFacitilies[5]));
        lstFacilities.add(new Item_Grid_Facilities("Tủ lạnh", R.drawable.icons_wi_fi, selectedFacitilies[6]));
        lstFacilities.add(new Item_Grid_Facilities("Bếp gas", R.drawable.icons_wi_fi, selectedFacitilies[7]));
        lstFacilities.add(new Item_Grid_Facilities("Quạt", R.drawable.icons_wi_fi, selectedFacitilies[8]));
        lstFacilities.add(new Item_Grid_Facilities("Có bảo vệ", R.drawable.icons_wi_fi, selectedFacitilies[9]));
        lstFacilities.add(new Item_Grid_Facilities("Máy lạnh", R.drawable.icons_wi_fi, selectedFacitilies[10]));
        lstFacilities.add(new Item_Grid_Facilities("Camera", R.drawable.icons_wi_fi, selectedFacitilies[11]));
        lstFacilities.add(new Item_Grid_Facilities("Cho phép vật nuôi", R.drawable.icons_wi_fi, selectedFacitilies[12]));
        lstFacilities.add(new Item_Grid_Facilities("Giờ tự do", R.drawable.icons_wi_fi, selectedFacitilies[13]));
        lstFacilities.add(new Item_Grid_Facilities("Khu để xe riêng", R.drawable.icons_wi_fi, selectedFacitilies[14]));

//        lstFacilities.add(new Item_Grid_Facilities("Wifi", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Gác", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Toilet riêng", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Phòng tắm riêng", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Giường", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Tivi", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Tủ lạnh", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Bếp gas", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Quạt", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Có bảo vệ", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Máy lạnh", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Camera", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Cho phép vật nuôi", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Giờ tự do", R.drawable.icons_wi_fi, false));
//        lstFacilities.add(new Item_Grid_Facilities("Khu để xe riêng", R.drawable.icons_wi_fi, false));


        final Grid_Facilities_Adapter myAdapter = new Grid_Facilities_Adapter(this, R.layout.grid_facilities_items, lstFacilities);
        myAdapter.notifyDataSetChanged();

        //grid.setAdapter(adapter);
        gridFacilities.setAdapter(myAdapter);

        gridFacilities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                boolean tmp = lstFacilities.get(i).isSelected();
                lstFacilities.get(i).setSelected(!tmp);
                myAdapter.notifyDataSetChanged();

                selectedFacitilies[i] = !tmp;
                //HomeFragment.selectedFacilities[i] = !HomeFragment.selectedFacilities[i];

            }
        });

        radNam = (RadioButton) findViewById(R.id.radNam);
        radNu = (RadioButton) findViewById(R.id.radNu);

        //radNam.setSelected();

        radGroup = (SegmentedGroup) findViewById(R.id.segmented2);

        if (sex) {
            radNam.setSelected(true);
            radGroup.check(R.id.radNam);
        } else {
            radNu.setSelected(true);
            radGroup.check(R.id.radNu);
        }
        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

            }
        });
        radNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = true;
                //HomeFragment.sex = true;
            }
        });
        radNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = false;
                //HomeFragment.sex = false;
            }
        });


        btnOK = (TextView) findViewById(R.id.btnTimKiem);
        btnCancel = (TextView) findViewById(R.id.btnHuy);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*HomeFragment.sex = sex;
                HomeFragment.selectedFacilitiess = selectedFacitilies;
                HomeFragment.minSlider = min;
                HomeFragment.maxSlider = max;*/

                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putBoolean("sex", sex);
                bundle.putInt("minPrice",min);
                bundle.putInt("maxPrice",max);
                bundle.putBoolean("changeFilter",true);
                bundle.putBooleanArray("arrFacilities",selectedFacitilies);
                intent.putExtra("data", bundle);
                setResult(1, intent); // phương thức này sẽ trả kết quả cho Activity1
                finish(); // Đóng Activity hiện tại

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
