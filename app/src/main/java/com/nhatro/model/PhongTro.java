package com.nhatro.model;

/**
 * Created by CongHoang on 2/11/2018.
 */

public class PhongTro {
    private int id;
    private String tieude;
    private String diachi;
    private float gia;
    private float dientich;
    private float chieudai;
    private float chieurong;
    private String gioitinh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public float getGia() {
        return gia;
    }



    public void setGia(float gia) {

        this.gia = gia;
    }

    public float getDientich() {
        return dientich;
    }

    public void setDientich(float dientich) {
        this.dientich = dientich;
    }

    public float getChieudai() {
        return chieudai;
    }

    public void setChieudai(float chieudai) {
        this.chieudai = chieudai;
    }

    public float getChieurong() {
        return chieurong;
    }

    public void setChieurong(float chieurong) {
        this.chieurong = chieurong;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public PhongTro(int id, String tieude, String diachi, float gia, float dientich, float chieudai, float chieurong, String gioitinh) {
        this.id = id;
        this.tieude = tieude;
        this.diachi = diachi;
        this.gia = gia;
        this.dientich = dientich;
        this.chieudai = chieudai;
        this.chieurong = chieurong;
        this.gioitinh = gioitinh;
    }
}
