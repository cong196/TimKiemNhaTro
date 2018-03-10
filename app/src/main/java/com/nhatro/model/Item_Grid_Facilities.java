package com.nhatro.model;

/**
 * Created by CongHoang on 3/1/2018.
 */

public class Item_Grid_Facilities {
    String name;
    int img;
    boolean selected;

    public Item_Grid_Facilities(String name, int img, boolean selected) {
        this.name = name;
        this.img = img;
        this.selected = selected;
    }

    public boolean isSelected() {

        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Item_Grid_Facilities(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
