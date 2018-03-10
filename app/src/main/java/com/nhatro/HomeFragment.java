package com.nhatro;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    boolean moDanhSach = true; // Đánh dấu mở danh sách hay ko
    TextView txtCheDoXem;
    ImageView imgBanDo;
    int REQUEST_CODE = 1;

    //android.support.v4.app.FragmentManager fragmentManager = getChildFragmentManager();
    private int minSlider = 0;
    private int maxSlider = 10000000;
    private boolean[] selectedFacilitiess = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private boolean sex = true;

    private boolean isFragmentMap = false; // Biến đánh dấu cho biết đã add fragmnent home chưa
    private boolean changeFilter = false;



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
        fragmentManager1 = getChildFragmentManager();
        fragmentManager1.beginTransaction().add(R.id.framDanhSach, listFragment).commit();

        btnSapXep = (LinearLayout) v.findViewById(R.id.btnSapXep);
        btnBoLoc = (LinearLayout) v.findViewById(R.id.btnBoLoc);
        btnMap = (LinearLayout) v.findViewById(R.id.btnMap);
        txtCheDoXem = (TextView) v.findViewById(R.id.txtCheDoXem);
        imgBanDo = (ImageView) v.findViewById(R.id.imgBanDo);
        btnBoLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Filter.class);

                Bundle bundle = new Bundle();

                bundle.putInt("maxPrice", maxSlider);
                bundle.putInt("minPrice", minSlider);
                bundle.putBoolean("sex", sex);
                bundle.putBooleanArray("arrFacilities", selectedFacilitiess);

                intent.putExtra("data", bundle);
                startActivityForResult(intent, REQUEST_CODE);


                //show it
                //bottomSheetDialogFragment.show(getChildFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });

        btnSapXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(getContext())
                        .title(R.string.tieude2)
                        .items(R.array.sapxeps)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Toast.makeText(getContext(), String.valueOf(R.array.sapxeps), Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        })
                        .positiveText(R.string.tieude3)
                        .show();
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

                    if(isFragmentMap) {
                        fragmentManager1.beginTransaction().hide(listFragment).show(mapFragment).commit();
                        if(changeFilter) {
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
                    if(changeFilter) {
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

            sex = bundle.getBoolean("sex");
            maxSlider = bundle.getInt("maxPrice");
            minSlider = bundle.getInt("minPrice");
            selectedFacilitiess = bundle.getBooleanArray("arrFacilities");
            changeFilter = bundle.getBoolean("changeFilter");
            if(moDanhSach){

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
