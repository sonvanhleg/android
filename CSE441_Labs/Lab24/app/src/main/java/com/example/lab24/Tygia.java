package com.example.lab24;

import android.graphics.Bitmap;

public class Tygia {
    private String type;
    private String imageurl;
    private Bitmap bitmap;
    private String muatienmat;
    private String muack;
    private String bantienmat;
    private String banck;
    public Tygia(){}

    public Tygia(String banck, String bantienmat, Bitmap bitmap, String imageurl, String muack, String muatienmat, String type) {
        this.banck = banck;
        this.bantienmat = bantienmat;
        this.bitmap = bitmap;
        this.imageurl = imageurl;
        this.muack = muack;
        this.muatienmat = muatienmat;
        this.type = type;
    }

    public String getBanck() {
        return banck;
    }

    public void setBanck(String banck) {
        this.banck = banck;
    }

    public String getBantienmat() {
        return bantienmat;
    }

    public void setBantienmat(String bantienmat) {
        this.bantienmat = bantienmat;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getMuack() {
        return muack;
    }

    public void setMuack(String muack) {
        this.muack = muack;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMuatienmat() {
        return muatienmat;
    }

    public void setMuatienmat(String muatienmat) {
        this.muatienmat = muatienmat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
