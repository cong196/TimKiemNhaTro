package com.nhatro;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elyeproj.loaderviewlibrary.LoaderImageView;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.nhatro.adapter.CustomListViewAdapter;
import com.nhatro.model.PhongTro;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    ListView lstDanhSach;
    ArrayList<PhongTro> data;
    CustomListViewAdapter adapter;
    View footerview;
    boolean isLoading = false; // chưa load DL
    boolean isnext = true; // Còn dữ liệu để load tiếp
    LinearLayout layoutList;
    ProgressBar loadingData;

    ConstraintLayout layoutLoading;

    mHadler mHadlerr;
    HandlerFilter handlerFilter;
    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        footerview = inflater.inflate(R.layout.footer_listivew, null);
        loadingData = v.findViewById(R.id.loadingData);
        layoutList = v.findViewById(R.id.layoutList);


        layoutLoading = v.findViewById(R.id.layoutLoadingList);

        mHadlerr = new mHadler();
        handlerFilter = new HandlerFilter();

        data = new ArrayList<>();

        data.add(new PhongTro(1, "Phòng Trọ Sạch Sẽ", "Số 1 Võ Văn Ngân, Quận Thủ Đức, TPHCM", 1200000, 30, 10, 3, "Nam"));
        data.add(new PhongTro(2, "Phòng 2 Sạch Sẽ", "Số 2 Võ Văn Ngân, Quận Thủ Đức, TPHCM", 1300000, 30, 10, 3, "Nam"));
        data.add(new PhongTro(3, "Phòng 3 Sạch Sẽ", "Số 3 Võ Văn Ngân, Quận Thủ Đức, TPHCM", 1400000, 30, 10, 3, "Nam"));
        data.add(new PhongTro(4, "78/12 Làng Tăng Phú, Phường Tăng Nhơn Phú A, Quận 9 Thành Phố Hồ Chí Minh, TPHCM", "78/12 Làng Tăng Phú, Phường Tăng Nhơn Phú A, Quận 9 Thành Phố Hồ Chí Minh, TPHCM", 1520000, 30, 10, 3, "Nam"));
        data.add(new PhongTro(5, "Phòng 5 Sạch Sẽ", "Số 5 Võ Văn Ngân, Quận Thủ Đức, TPHCM", 1600000, 30, 10, 3, "Nữ"));
        data.add(new PhongTro(6, "Phòng Trọ Sạch Sẽ", "Số 1 Võ Văn Ngân, Quận Thủ Đức, TPHCM", 1200000, 30, 10, 3, "Nam"));

        lstDanhSach = (ListView) v.findViewById(R.id.lstDanhSachTin);
        adapter = new CustomListViewAdapter(getContext(), data);
        adapter.notifyDataSetChanged();

        lstDanhSach.setAdapter(adapter);

        lstDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Details.class);

                Bundle bundle = new Bundle();
                int idItem = data.get(i).getId();
                bundle.putInt("iditem", idItem);
                intent.putExtra("iditem", bundle);
                getActivity().startActivity(intent);


            }
        });

        lstDanhSach.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (absListView.getLastVisiblePosition() == i2 - 1 && isLoading == false && isnext) {
                    //data.add(new PhongTro(9,"1111","Số 1 Võ Văn Ngân, Quận Thủ Đức, TPHCM",1200000,30,10,3,"Nam"));
                    //adapter.notifyDataSetChanged();
                    isLoading = true;
                    Thread thread = new ThreadData();
                    thread.start();
                    //lstDanhSach.addFooterView(footerview);
                }
            }
        });
        //Toast.makeText(getContext(),"ĐANG LOAD DL",Toast.LENGTH_SHORT).show();
        return v;
    }


    public void filterData() {
        Toast.makeText(getContext(),"Đang Lọc DL",Toast.LENGTH_SHORT).show();
        isnext = true;
        Thread thread = new ThreadFilter();
        thread.start();
    }
    public ArrayList<PhongTro> getData() {
        ArrayList<PhongTro> datas = new ArrayList<>();
        if(datas.size() == 0) {
            isnext = false;
        }
        datas.add(new PhongTro(8, "Thêm 1", "78/12 Làng Tăng Phú, Phường Tăng Nhơn Phú A, Quận 9 Thành Phố Hồ Chí Minh, TPHCM", 1520000, 30, 10, 3, "Nam"));

        /*datas.add(new PhongTro(9, "Thêm 2", "Số 5 Võ Văn Ngân, Quận Thủ Đức, TPHCM", 1600000, 30, 10, 3, "Nữ"));
        datas.add(new PhongTro(10, "Thêm 3", "Số 1 Võ Văn Ngân, Quận Thủ Đức, TPHCM", 1200000, 30, 10, 3, "Nam"));

        datas.add(new PhongTro(12, "Thêm 4", "78/12 Làng Tăng Phú, Phường Tăng Nhơn Phú A, Quận 9 Thành Phố Hồ Chí Minh, TPHCM", 1520000, 30, 10, 3, "Nam"));
        datas.add(new PhongTro(11, "Thêm 5", "Số 5 Võ Văn Ngân, Quận Thủ Đức, TPHCM", 1600000, 30, 10, 3, "Nữ"));
        datas.add(new PhongTro(13, "Thêm 6", "Số 1 Võ Văn Ngân, Quận Thủ Đức, TPHCM", 1200000, 30, 10, 3, "Nam"));
*/
        return datas;
    }

    public class mHadler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0) {
                //lstDanhSach.addFooterView(footerview);
                loadingData.setVisibility(View.VISIBLE);
            } else {
                //data.addAll(getData());
                data.clear();
                data.addAll(getData());
                adapter.notifyDataSetChanged();
                isLoading = false;
                loadingData.setVisibility(View.GONE);
            }
        }

    }

    public class ThreadData extends Thread {
        @Override
        public void run() {
            mHadlerr.sendEmptyMessage(0);
            ArrayList<PhongTro> mangData = new ArrayList<>();
            mangData = getData();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message = mHadlerr.obtainMessage(1, mangData);
            mHadlerr.sendMessage(message);

        }
    }

    public class HandlerFilter extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0) {
                data.clear();
                adapter.notifyDataSetChanged();
                layoutList.setVisibility(View.GONE);
                layoutLoading.setVisibility(View.VISIBLE);
            } else {
                data.addAll(getData());
                adapter.notifyDataSetChanged();
                layoutList.setVisibility(View.VISIBLE);
                layoutLoading.setVisibility(View.GONE);
            }
        }

    }

    public class ThreadFilter extends Thread {
        @Override
        public void run() {
            handlerFilter.sendEmptyMessage(0);
            ArrayList<PhongTro> mangData = new ArrayList<>();
            mangData = getData();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message = handlerFilter.obtainMessage(1, mangData);
            handlerFilter.sendMessage(message);
        }
    }

}
