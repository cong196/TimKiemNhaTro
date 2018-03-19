package com.nhatro.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.nhatro.model.TinhTP;

import java.util.ArrayList;

/**
 * Created by CongHoang on 3/17/2018.
 */

public class SQLite_TinhTP extends SQLiteDataController {
    public SQLite_TinhTP(Context con) {
        super(con);
    }

    public ArrayList<TinhTP> getDSTP() {
        ArrayList<TinhTP> lstTP = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from TinhThanh", null);
            TinhTP tp;
            while (cs.moveToNext()) {
                tp = new TinhTP(cs.getInt(0),cs.getString(1));
                lstTP.add(tp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return lstTP;
    }


}
