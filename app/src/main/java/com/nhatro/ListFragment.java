package com.nhatro;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nhatro.adapter.CustomListViewAdapter;
import com.nhatro.model.PhongTro;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    ListView lstDanhSach;
    ArrayList<PhongTro> data;
    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        data = new ArrayList<>();

        data.add(new PhongTro(1,"Phòng Trọ Sạch Sẽ","Số 1 Võ Văn Ngân, Quận Thủ Đức, TPHCM",1200000,30,10,3,"Nam"));
        data.add(new PhongTro(2,"Phòng 2 Sạch Sẽ","Số 2 Võ Văn Ngân, Quận Thủ Đức, TPHCM",1300000,30,10,3,"Nam"));
        data.add(new PhongTro(3,"Phòng 3 Sạch Sẽ","Số 3 Võ Văn Ngân, Quận Thủ Đức, TPHCM",1400000,30,10,3,"Nam"));
        data.add(new PhongTro(4,"78/12 Làng Tăng Phú, Phường Tăng Nhơn Phú A, Quận 9 Thành Phố Hồ Chí Minh, TPHCM","78/12 Làng Tăng Phú, Phường Tăng Nhơn Phú A, Quận 9 Thành Phố Hồ Chí Minh, TPHCM",1520000,30,10,3,"Nam"));
        data.add(new PhongTro(5,"Phòng 5 Sạch Sẽ","Số 5 Võ Văn Ngân, Quận Thủ Đức, TPHCM",1600000,30,10,3,"Nữ"));
        data.add(new PhongTro(6,"Phòng Trọ Sạch Sẽ","Số 1 Võ Văn Ngân, Quận Thủ Đức, TPHCM",1200000,30,10,3,"Nam"));

        lstDanhSach = (ListView) v.findViewById(R.id.lstDanhSachTin);
        final CustomListViewAdapter adapter = new CustomListViewAdapter(getContext(),data);
        lstDanhSach.setAdapter(adapter);


        lstDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Details.class);

                Bundle bundle = new Bundle();
                int idItem = data.get(i).getId();
                bundle.putInt("iditem",idItem);
                intent.putExtra("iditem",bundle);
                getActivity().startActivity(intent);

            }
        });
        return v;
    }

}
