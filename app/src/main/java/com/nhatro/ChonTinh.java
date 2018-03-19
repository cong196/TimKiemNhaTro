package com.nhatro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhatro.adapter.List_Tinh_TP_Adapter;
import com.nhatro.model.TinhTP;
import com.nhatro.sqlite.SQLiteDataController;
import com.nhatro.sqlite.SQLite_TinhTP;

import java.io.IOException;
import java.util.ArrayList;

public class ChonTinh extends AppCompatActivity {

    ArrayList<TinhTP> data;
    List_Tinh_TP_Adapter list_tinh_tp_adapter;
    ListView lstTP;

    int tinhTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_tinh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        tinhTP = bundle.getInt("tinhTP");


        lstTP = (ListView) findViewById(R.id.lstTP);
        //getSupportActionBar().hide();
        getSupportActionBar().setTitle("Chọn Tỉnh/Thành Phố");
        data = new ArrayList<>();

        //createDB();

        getListTP();

        /*SQLiteDataController sql = new SQLiteDataController(this);
        sql.deleteDatabase();*/


        //data.get(indexSelected).setSelect(true);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == tinhTP) {
                data.get(i).setSelect(true);
                break;
            }
        }
        //data.indexOf()

        list_tinh_tp_adapter = new List_Tinh_TP_Adapter(getApplicationContext(), R.layout.item_list_tinh_tp, data);
        list_tinh_tp_adapter.notifyDataSetChanged();

        lstTP.setAdapter(list_tinh_tp_adapter);

        lstTP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //data.get(tinhTP).setSelect(false);

                //data.get(i).setSelect(false);
                for (int i1 = 0; i1 < data.size(); i1++) {
                    if (data.get(i1).getId() == tinhTP) {
                        data.get(i1).setSelect(false);
                        break;
                    }
                }

                tinhTP = data.get(i).getId();

                data.get(i).setSelect(!data.get(i).isSelect());
                list_tinh_tp_adapter.notifyDataSetChanged();

                Intent intent1 = getIntent();
                Bundle bundle = new Bundle();
                bundle.putInt("tinhTP", tinhTP);
                bundle.putString("tenTP", data.get(i).getTen());
                intent1.putExtra("datas", bundle);

                setResult(21, intent1); // phương thức này sẽ trả kết quả cho Activity Filter
                finish(); // Đóng Activity hiện tại
            }
        });

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

    private void getListTP() {
        SQLite_TinhTP sqLite_tinhTP = new SQLite_TinhTP(getApplicationContext());
        data = new ArrayList<TinhTP>();
        data = sqLite_tinhTP.getDSTP();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
