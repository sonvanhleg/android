package com.example.lab133;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int IdLayout;
    ArrayList<Phone> mylist;

    public MyArrayAdapter( Activity context, int idLayout, ArrayList<Phone> mylist) {
        super(context, idLayout,mylist);
        this.context = context;
        IdLayout = idLayout;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myflater = context.getLayoutInflater();
        convertView = myflater.inflate(IdLayout,null);
        Phone myphone = mylist.get(position);
        ImageView imgphone = convertView.findViewById(R.id.imgphone);
        imgphone.setImageResource(myphone.getImage());
        TextView txtphone = convertView.findViewById(R.id.txtphone);
        txtphone.setText(myphone.getName());
        return convertView;
    }
}
