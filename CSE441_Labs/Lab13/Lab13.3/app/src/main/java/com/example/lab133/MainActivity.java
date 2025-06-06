package com.example.lab133;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int image[] = {R.drawable.dienthoai,R.drawable.dienthoai,R.drawable.dienthoai,R.drawable.dienthoai,R.drawable.dienthoai,R.drawable.dienthoai};
    String name[] = {"Sam sung","Iphone","HTC","WindowPhone","LG","Sky"};
    ArrayList<Phone> mylist;
    MyArrayAdapter myadapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lv), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.lv1);
        mylist = new ArrayList<>();
        for(int i = 0;i < name.length; i++){
            mylist.add(new Phone(image[i],name[i]));
        }
        myadapter = new MyArrayAdapter(MainActivity.this,R.layout.layout_item,mylist);
        lv.setAdapter(myadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myitent = new Intent(MainActivity.this,SubActivity.class);
                myitent.putExtra("name",name[i]);
                startActivity(myitent);
            }
        });
    }
}