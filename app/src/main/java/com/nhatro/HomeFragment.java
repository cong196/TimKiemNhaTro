package com.nhatro;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    boolean moDanhSach = true; // Đánh dấu mở danh sách hay ko
    TextView txtCheDoXem;
    ImageView imgBanDo;
    int REQUEST_CODE = 1;

    //android.support.v4.app.FragmentManager fragmentManager = getChildFragmentManager();
    private int minSlider = 0,soNguoiO = 1;
    private int maxSlider = 10000000;
    private int maxArea = 200;
    private int minArea = 0;
    private int chonSapXep = 0, tempChon = 0;
    private boolean[] selectedFacilitiess = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private boolean sex = true;

    ArrayList<Integer> lstChonQuanHuyen;



    private boolean isFragmentMap = false; // Biến đánh dấu cho biết đã add fragmnent home chưa
    private boolean changeFilter = false;
    private int tinhTP; // Biến đánh dấu đang lọc theo tỉnh tp nào...
    private String tenTP; // Tên TP đang tìm kiếm
    private boolean timPhongTro, timNhaNguyenCan, timTimOGhep; // check đánh dấu các tin cần lọc
    MapFragment mapFragment = new MapFragment();
    ListFragment listFragment = new ListFragment();
    android.support.v4.app.FragmentManager fragmentManager1;


    public HomeFragment() {
        // Required empty public constructor
    }

    LinearLayout btnSapXep, btnBoLoc, btnMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_home, container, false);
        final CharSequence[] sapXep = new CharSequence[] {"Mặc định","Giá từ thấp đến cao","Giá từ cao xuống thấp"};

        lstChonQuanHuyen = new ArrayList<>();


        fragmentManager1 = getChildFragmentManager();
        fragmentManager1.beginTransaction().add(R.id.framDanhSach, listFragment).commit();

        btnSapXep = (LinearLayout) v.findViewById(R.id.btnSapXep);
        btnBoLoc = (LinearLayout) v.findViewById(R.id.btnBoLoc);
        btnMap = (LinearLayout) v.findViewById(R.id.btnMap);
        txtCheDoXem = (TextView) v.findViewById(R.id.txtCheDoXem);
        imgBanDo = (ImageView) v.findViewById(R.id.imgBanDo);

        tinhTP = 1;
        tenTP = "Hà Nội";
        timNhaNguyenCan = false;
        timPhongTro = false;
        timTimOGhep = false;
        btnBoLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Filter.class);

                Bundle bundle = new Bundle();

                bundle.putBoolean("timNhaNguyenCan", timNhaNguyenCan);
                bundle.putBoolean("timPhongTro",timPhongTro);
                bundle.putBoolean("timTimOGhep",timTimOGhep);
                bundle.putIntegerArrayList("lstChonQuanHuyen",lstChonQuanHuyen);
                bundle.putString("tenTP",tenTP);
                bundle.putInt("maxPrice", maxSlider);
                bundle.putInt("minPrice", minSlider);
                bundle.putBoolean("sex", sex);
                bundle.putInt("minArea", minArea);
                bundle.putInt("maxArea", maxArea);
                bundle.putBooleanArray("arrFacilities", selectedFacilitiess);
                bundle.putInt("tinhTP",tinhTP);
                bundle.putInt("soNguoiO",soNguoiO);
                intent.putExtra("data", bundle);

                startActivityForResult(intent, REQUEST_CODE);


                //show it
                //bottomSheetDialogFragment.show(getChildFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });
        btnSapXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new MaterialDialog.Builder(getContext())
                        .title(R.string.tieude2)
                        .items(R.array.sapxeps)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                //Toast.makeText(getContext(), String.valueOf(R.array.sapxeps), Toast.LENGTH_SHORT).show();
                                if (moDanhSach) {
                                    listFragment.filterData();
                                } else {
                                    mapFragment.loadData();
                                }
                                return true;
                            }
                        })
                        .positiveText(R.string.tieude3)
                        .show();*/



                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Sắp xếp")
                        .setSingleChoiceItems(sapXep, chonSapXep, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tempChon = which;
                                //Toast.makeText(getContext(),String.valueOf(which),Toast.LENGTH_SHORT).show();
                                //utility.toast(" "+charSequence);
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                chonSapXep = tempChon;
                                if (moDanhSach) {
                                    listFragment.filterData();
                                } else {
                                    mapFragment.loadData();
                                }
                            }
                        });
                builder.create().show();
            }
        });


        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (moDanhSach) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.weight = 1.5f;
                    btnMap.setLayoutParams(params);
                    btnBoLoc.setLayoutParams(params);
                    btnSapXep.setVisibility(View.GONE);
                    moDanhSach = false;
                    txtCheDoXem.setText("Danh sách");
                    imgBanDo.setImageResource(R.drawable.list);

                    if (isFragmentMap) {
                        fragmentManager1.beginTransaction().hide(listFragment).show(mapFragment).commit();
                        if (changeFilter) {
                            mapFragment.loadData();
                            changeFilter = false;
                        }
                    } else {
                        isFragmentMap = true;

//                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                        transaction.replace(R.id.framDanhSach, new MapFragment());
//                        transaction.commit();

                        fragmentManager1.beginTransaction().add(R.id.framDanhSach, mapFragment).commit();
                        fragmentManager1.beginTransaction().hide(listFragment).show(mapFragment).commit();

                    }


                } else {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.weight = 1f;
                    btnMap.setLayoutParams(params);
                    btnBoLoc.setLayoutParams(params);
                    btnSapXep.setVisibility(View.VISIBLE);
                    moDanhSach = true;
                    txtCheDoXem.setText("Bản đồ");
                    imgBanDo.setImageResource(R.drawable.map);

//                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                    transaction.replace(R.id.framDanhSach, new ListFragment());
//                    transaction.commit();

                    fragmentManager1.beginTransaction().hide(mapFragment).show(listFragment).commit();
                    if (changeFilter) {
                        listFragment.filterData();
                        changeFilter = false;
                    }
                }

            }
        });


        /////

//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.framDanhSach, new ListFragment());
//        transaction.commit();


        ////
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            Bundle bundle = data.getBundleExtra("data");
            tenTP = bundle.getString("tenTP");
            soNguoiO = bundle.getInt("soNguoiO");
            sex = bundle.getBoolean("sex");
            maxSlider = bundle.getInt("maxPrice");
            minSlider = bundle.getInt("minPrice");
            selectedFacilitiess = bundle.getBooleanArray("arrFacilities");
            changeFilter = bundle.getBoolean("changeFilter");
            minArea = bundle.getInt("minArea");
            maxArea = bundle.getInt("maxArea");
            tinhTP = bundle.getInt("tinhTP");
            lstChonQuanHuyen = bundle.getIntegerArrayList("lstChonQuanHuyen");
            timNhaNguyenCan = bundle.getBoolean("timNhaNguyenCan");
            timPhongTro = bundle.getBoolean("timPhongTro");
            timTimOGhep = bundle.getBoolean("timTimOGhep");


            if (moDanhSach) {
                listFragment.filterData();

//                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                transaction.replace(R.id.framDanhSach, new ListFragment());
//                transaction.commit();
            } else {
                mapFragment.loadData();
//                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                transaction.replace(R.id.framDanhSach, new MapFragment());
//                transaction.commit();
            }
        }
    }
}
