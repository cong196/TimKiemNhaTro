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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nhatro.adapter.ExpandableHeightGridView;
import com.nhatro.adapter.Grid_Facilities_Adapter;
import com.nhatro.adapter.Grid_Quan_Huyen_Adapter;
import com.nhatro.model.Item_Grid_Facilities;
import com.nhatro.model.QuanHuyen;
import com.nhatro.sqlite.SQLiteDataController;
import com.nhatro.sqlite.SQLite_QuanHuyen;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import io.apptik.widget.MultiSlider;


public class Filter extends AppCompatActivity {


    private int min = 0;
    private int max = 10000000;

    private int minArea = 0;
    private int maxArea = 200;
    private int soNguoiO = 1;
    private int tinhTP;

    GridView gridFacilities;
    ArrayList<Item_Grid_Facilities> lstFacilities = new ArrayList<>();
    ArrayList<QuanHuyen> listQuanHuyen = new ArrayList<>();

    ArrayList<Integer> lstChonQuanHuyen;

    boolean[] selectedFacitilies;
    boolean sex;

    String tenTP = "";
    ExpandableHeightGridView gridQuanHuyen;
    Grid_Quan_Huyen_Adapter grid_quan_huyen_adapter;

    TextView btnOK, btnCancel, txtTPHT;
    RadioButton radNam, radNu;
    SegmentedGroup radGroup;
    CheckBox checkNhaTro, checkNhaNguyenCan, checkTimOGhep;
    LinearLayout btnChonTinh;
    SQLite_QuanHuyen sqLite_quanHuyen;

    private boolean timPhongTro, timNhaNguyenCan, timTimOGhep; // check đánh dấu các tin cần lọc


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

        final TextView txtSoNguoiO = (TextView) findViewById(R.id.txtSoNguoi);
        final MultiSlider sliderSoNguoiO = (MultiSlider) findViewById(R.id.sliderSoNguoi);


        createDB();

        lstChonQuanHuyen = new ArrayList<>();
        sqLite_quanHuyen = new SQLite_QuanHuyen(getApplicationContext());

        gridQuanHuyen = (ExpandableHeightGridView) findViewById(R.id.gridQuan);
        gridQuanHuyen.setExpanded(true);
        gridQuanHuyen.setFocusable(false);


        checkNhaNguyenCan = (CheckBox) findViewById(R.id.checkNhaNguyenCan);
        checkNhaTro = (CheckBox) findViewById(R.id.checkPhongTro);
        checkTimOGhep = (CheckBox) findViewById(R.id.checkTimOGhep);
        btnChonTinh = (LinearLayout) findViewById(R.id.lblTinh);
        txtTPHT = (TextView) findViewById(R.id.txtTPHT);


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");


        timNhaNguyenCan = bundle.getBoolean("timNhaNguyenCan");
        timPhongTro = bundle.getBoolean("timPhongTro");
        timTimOGhep = bundle.getBoolean("timTimOGhep");

        lstChonQuanHuyen = bundle.getIntegerArrayList("lstChonQuanHuyen");
        sex = bundle.getBoolean("sex");
        min = bundle.getInt("minPrice");
        max = bundle.getInt("maxPrice");
        tinhTP = bundle.getInt("tinhTP");
        minArea = bundle.getInt("minArea");
        maxArea = bundle.getInt("maxArea");
        soNguoiO = bundle.getInt("soNguoiO");
        tenTP = bundle.getString("tenTP");
        ///


        checkNhaNguyenCan.setChecked(timNhaNguyenCan);
        checkNhaTro.setChecked(timPhongTro);
        checkTimOGhep.setChecked(timTimOGhep);

        txtTPHT.setText(tenTP); // Tên thành phố đang tìm kiếm

        sliderSoNguoiO.getThumb(0).setValue(soNguoiO);
        txtSoNguoiO.setText(String.valueOf(soNguoiO) + " người");

        sliderSoNguoiO.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                soNguoiO = value;
                txtSoNguoiO.setText(String.valueOf(soNguoiO) + " người");
            }
        });
        ////
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
        //////

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
        gridFacilities.setFocusable(false);

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


        listQuanHuyen = getDsQH(tinhTP);

        for (int i = 0; i < listQuanHuyen.size(); i++) {
            for (int j = 0; j < lstChonQuanHuyen.size(); j++) {
                if (listQuanHuyen.get(i).getId() == lstChonQuanHuyen.get(j)) {
                    listQuanHuyen.get(i).setSelect(true);
                    break;
                }
            }
            //listQuanHuyen.g
        }

        grid_quan_huyen_adapter = new Grid_Quan_Huyen_Adapter(getApplicationContext(), R.layout.grid_quan_huyen_item, listQuanHuyen);
        grid_quan_huyen_adapter.notifyDataSetChanged();
        gridQuanHuyen.setAdapter(grid_quan_huyen_adapter);

        gridQuanHuyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                boolean tmp = listQuanHuyen.get(i).isSelect();
                if(tmp){
                    for(int j=0;j<lstChonQuanHuyen.size();j++){
                        if(lstChonQuanHuyen.get(j) == listQuanHuyen.get(i).getId()) {
                            lstChonQuanHuyen.remove(j);
                            break;
                        }
                    }

                } else {
                    lstChonQuanHuyen.add(listQuanHuyen.get(i).getId());
                }
                listQuanHuyen.get(i).setSelect(!tmp);
                grid_quan_huyen_adapter.notifyDataSetChanged();

                //selectedFacitilies[i] = !tmp;
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

        btnChonTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Chọn Tỉnh",Toast.LENGTH_SHORT).show();

                Intent inten1 = new Intent(getApplicationContext(), ChonTinh.class);
                Bundle bundle = new Bundle();

                bundle.putInt("tinhTP", tinhTP);
                inten1.putExtra("data", bundle);
                startActivityForResult(inten1, 100);

                //Toast.makeText(getApplicationContext(),String.valueOf(tinhTP),Toast.LENGTH_SHORT).show();
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
                bundle.putIntegerArrayList("lstChonQuanHuyen",lstChonQuanHuyen);
                bundle.putBoolean("timNhaNguyenCan", checkNhaNguyenCan.isChecked());
                bundle.putBoolean("timPhongTro", checkNhaTro.isChecked());
                bundle.putBoolean("timTimOGhep", checkTimOGhep.isChecked());

                bundle.putString("tenTP", tenTP);
                bundle.putInt("soNguoiO", soNguoiO);
                bundle.putInt("maxArea", maxArea);
                bundle.putInt("minArea", minArea);
                bundle.putBoolean("sex", sex);
                bundle.putInt("minPrice", min);
                bundle.putInt("maxPrice", max);
                bundle.putBoolean("changeFilter", true);
                bundle.putBooleanArray("arrFacilities", selectedFacitilies);
                bundle.putInt("tinhTP", tinhTP);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == 100) {
            Bundle bundle = data.getBundleExtra("datas");
            tinhTP = bundle.getInt("tinhTP");
            tenTP = bundle.getString("tenTP");
            txtTPHT.setText(bundle.getString("tenTP"));
            /*listQuanHuyen.clear();
            grid_quan_huyen_adapter.notifyDataSetChanged();*/

            listQuanHuyen = getDsQH(tinhTP);
            lstChonQuanHuyen.clear();
            //getDsQH();
            //grid_quan_huyen_adapter.notifyDataSetChanged();
            //gridQuanHuyen.setAdapter(grid_quan_huyen_adapter);


            grid_quan_huyen_adapter = new Grid_Quan_Huyen_Adapter(getApplicationContext(), R.layout.grid_quan_huyen_item, listQuanHuyen);
            grid_quan_huyen_adapter.notifyDataSetChanged();
            gridQuanHuyen.setAdapter(grid_quan_huyen_adapter);

        }

    }

    public ArrayList<QuanHuyen> getDsQH(int id) {
        //Toast.makeText(getApplicationContext(),String.valueOf(tinhTP),Toast.LENGTH_SHORT).show();
        ArrayList<QuanHuyen> data = new ArrayList<>();
        data = sqLite_quanHuyen.getDSQH(id);

        return data;
    }

    private void createDB() {
// khởi tạo database
        SQLiteDataController sql = new SQLiteDataController(this);
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
