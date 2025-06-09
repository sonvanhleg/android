package com.example.lab23;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<List> {
    private Activity context;
    private ArrayList<List> arr;
    private int layoutID;
    ListView lv1;

    public MyArrayAdapter(Activity context, int layoutID, ArrayList<List> arr) {
        super(context, layoutID, arr);
        this.context = context;
        this.layoutID = layoutID;
        this.arr = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        final List lst = arr.get(position);
        ImageView imgitem = convertView.findViewById(R.id.imageView);
        imgitem.setImageBitmap(lst.getImg());
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        txtTitle.setText(lst.getTitle());
        TextView txtInfo = convertView.findViewById(R.id.txtinfo);
        txtInfo.setText(lst.getInfo());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(lst.getLink()));
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
