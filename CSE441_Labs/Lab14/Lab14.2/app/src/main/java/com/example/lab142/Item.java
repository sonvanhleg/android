package com.example.lab142;

public class Item {
    private String maso,tieude;
    private int thich;

    public Item(String maso, String tieude, int thich) {
        this.maso = maso;
        this.thich = thich;
        this.tieude = tieude;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public int getThich() {
        return thich;
    }

    public void setThich(int thich) {
        this.thich = thich;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }
}
