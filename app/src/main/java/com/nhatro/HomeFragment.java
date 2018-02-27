package com.nhatro;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    boolean moDanhSach = true;
    TextView txtCheDoXem;
    ImageView imgBanDo;
    int REQUEST_CODE = 1;

    //android.support.v4.app.FragmentManager fragmentManager = getChildFragmentManager();

    public HomeFragment() {
        // Required empty public constructor
    }

    LinearLayout btnSapXep, btnBoLoc, btnMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_home, container, false);


        btnSapXep = (LinearLayout) v.findViewById(R.id.btnSapXep);
        btnBoLoc = (LinearLayout) v.findViewById(R.id.btnBoLoc);
        btnMap = (LinearLayout) v.findViewById(R.id.btnMap);
        txtCheDoXem = (TextView) v.findViewById(R.id.txtCheDoXem);
        imgBanDo = (ImageView) v.findViewById(R.id.imgBanDo);
        btnBoLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Filter.class);
                startActivityForResult(intent,REQUEST_CODE);
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
                                Toast.makeText(getContext(),String.valueOf(R.array.sapxeps),Toast.LENGTH_SHORT).show();
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

                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.framDanhSach, new MapFragment());
                    transaction.commit();


                } else {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.weight = 1f;
                    btnMap.setLayoutParams(params);
                    btnBoLoc.setLayoutParams(params);
                    btnSapXep.setVisibility(View.VISIBLE);
                    moDanhSach = true;
                    txtCheDoXem.setText("Bản đồ");
                    imgBanDo.setImageResource(R.drawable.map);

                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.framDanhSach, new ListFragment());
                    transaction.commit();
                }

            }
        });


        /////


        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.framDanhSach, new ListFragment());
        transaction.commit();


        ////
        return v;
    }

}
