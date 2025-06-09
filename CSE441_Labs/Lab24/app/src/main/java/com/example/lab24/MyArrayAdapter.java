package com.example.lab24;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Tygia> {
    Activity context;
    int resource;
    List<Tygia> objects;

    public MyArrayAdapter(Activity context, int resource, List<Tygia> objects) {
        super(context, resource,objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);
        Tygia tygia = this.objects.get(position);
        ImageView imgHinh = (ImageView) item.findViewById(R.id.imghinh);
        TextView txtType = (TextView) item.findViewById(R.id.txtType);
        TextView txtmuatm = (TextView) item.findViewById(R.id.txtmuatm);
        TextView txtbantm = (TextView) item.findViewById(R.id.txtbantm);
        TextView txtmuack = (TextView) item.findViewById(R.id.txtmuack);
        TextView txtbanck = (TextView) item.findViewById(R.id.txtbanck);
        imgHinh.setImageBitmap(tygia.getBitmap());
        txtType.setText(tygia.getType());
        txtbantm.setText(tygia.getMuatienmat());
        txtbantm.setText(tygia.getBantienmat());
        txtmuack.setText(tygia.getMuack());
        txtbanck.setText(tygia.getBanck());
        return item;
    }
}
