package com.nhatro.model;

/**
 * Created by CongHoang on 2/25/2018.
 */

public class ItemOnMapView {
    private int id;
    private String title;
    private String address;
    private float price;
    private double lat;
    private double lng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public ItemOnMapView(int id, String title, String address, float price, double lat, double lng) {

        this.id = id;
        this.title = title;
        this.address = address;
        this.price = price;
        this.lat = lat;
        this.lng = lng;
    }
}
