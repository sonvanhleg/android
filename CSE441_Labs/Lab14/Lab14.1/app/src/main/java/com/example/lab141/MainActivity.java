package com.example.lab141;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edta,edtb;
    Button btnadd;
    ListView lv1;
    ArrayList<String> list;
    ArrayAdapter<String> myarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addControl();
        addEvent();
    }
    private void addEvent(){
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xulycong();
            }
            private void Xulycong(){
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                String c = a+ " + "+b+" = "+(a+b);
                list.add(c);
                myarray.notifyDataSetChanged();
                edta.setText("");
                edtb.setText("");
            }
        });
    }
    private void addControl(){
        edta = (EditText) findViewById(R.id.edta);
        edtb = (EditText) findViewById(R.id.edtb);
        btnadd = (Button) findViewById(R.id.btnadd);
        lv1 = (ListView) findViewById(R.id.lv1);
        list = new ArrayList<String>();
        myarray = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,list);
        lv1.setAdapter(myarray);
        TabHost tab = (TabHost) findViewById(R.id.tabhost);
        tab.setup();
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.cong));
        tab.addTab(tab1);
        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.lichsu));
        tab.addTab(tab2);
    }
}