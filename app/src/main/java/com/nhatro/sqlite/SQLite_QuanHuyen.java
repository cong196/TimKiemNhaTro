package com.nhatro.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.nhatro.model.QuanHuyen;

import java.util.ArrayList;

/**
 * Created by CongHoang on 3/17/2018.
 */

public class SQLite_QuanHuyen extends SQLiteDataController {
    public SQLite_QuanHuyen(Context con) {
        super(con);
    }

    public ArrayList<QuanHuyen> getDSQH(int id) {
        ArrayList<QuanHuyen> lstQH = new ArrayList<>();
        // mo ket noi
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from QuanHuyen where idtp =" + id, null);
            QuanHuyen qh;
            while (cs.moveToNext()) {
                qh = new QuanHuyen(cs.getInt(0), cs.getInt(2),cs.getString(1));
                lstQH.add(qh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return lstQH;
    }


}