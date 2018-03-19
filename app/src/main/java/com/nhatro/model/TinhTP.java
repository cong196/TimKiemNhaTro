package com.nhatro.model;

/**
 * Created by CongHoang on 3/17/2018.
 */

public class TinhTP {
    private int id;
    private String ten;
    private boolean select;

    public TinhTP(int id, String ten, boolean select) {
        this.id = id;
        this.ten = ten;
        this.select = select;
    }

    public TinhTP(int id, String ten) {
        this.id = id;
        this.ten = ten;
        this.select = false;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
