package com.example.lab18;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    public static String DATABASE_NAME="arirang.sqlite";
    EditText edtim;
    ListView lv1,lv2,lv3;
    ArrayList<Item> list1,list2,list3;
    myarrayAdapter myarray1, myarray2,myarray3;
    TabHost tab;
    ImageButton btnxoa;
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
        processCopy();
        database = openOrCreateDatabase("arirang.sqlite",MODE_PRIVATE,null);
        addControl();
        addTim();
        addEvent();
    }

    private void addEvent() {
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase("t2")){
                    addDanhSach();
                }
                if(tabId.equalsIgnoreCase("t3")){
                    addYeuthich();
                }
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtim.setText("");
            }
        });
    }

    private void addYeuthich() {
        myarray3.clear();
        Cursor c = database.rawQuery("SELECT * FROM ArirangSongList WHERE YEUTHICH = 1",null);
        c.moveToFirst();
        while(c.isAfterLast()==false){
            list3.add(new Item(c.getString(1),c.getInt(6),c.getString(2)));
            c.moveToNext();
        }
        c.close();
        myarray3.notifyDataSetChanged();
    }

    private void addDanhSach() {
        myarray2.clear();
        Cursor c = database.rawQuery("SELECT * FROM ArirangSongList",null);
        c.moveToNext();
        while (c.isAfterLast()==false){
            list2.add(new Item(c.getString(1),c.getInt(6),c.getString(2)));
            c.moveToNext();
        }
        c.close();
        myarray2.notifyDataSetChanged();
    }

    private void addTim() {
        edtim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getdata();
            }

            private void getdata() {
                String dulieunhap = edtim.getText().toString();
                myarray1.clear();
                if(!edtim.getText().toString().equals("")){
                    Cursor c = database.rawQuery("SELECT * FROM ArirangSongList where TENBH1 LIKE '"+"%"+dulieunhap+"%"+"' OR MABH LIKE '"+"%"+dulieunhap+"%"+"'",null);
                    c.moveToNext();
                    while(c.isAfterLast() == false){
                        list1.add(new Item(c.getString(1),c.getInt(6),c.getString(2)));
                        c.moveToNext();
                    }
                    c.close();
                }
                myarray1.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addControl() {
        btnxoa = (ImageButton) findViewById(R.id.btnxoa);
        tab = (TabHost) findViewById(R.id.tabhost);
        tab.setup();
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.search));
        tab.addTab(tab1);
        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.list));
        tab.addTab(tab2);
        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("",getResources().getDrawable(R.drawable.favourite));
        tab.addTab(tab3);
        edtim = (EditText) findViewById(R.id.edtim);
        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        lv3 = (ListView) findViewById(R.id.lv3);
        list1 = new ArrayList<Item>();
        list2 = new ArrayList<Item>();
        list3 = new ArrayList<Item>();
        myarray1 = new myarrayAdapter(MainActivity.this,R.layout.listitem,list1);
        myarray2 = new myarrayAdapter(MainActivity.this,R.layout.listitem,list2);
        myarray3 = new myarrayAdapter(MainActivity.this,R.layout.listitem,list3);
        lv1.setAdapter(myarray1);
        lv2.setAdapter(myarray2);
        lv3.setAdapter(myarray3);
    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists()){
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this,"Copying sucess from Assets folder",Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath(){
        return getApplication().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
    public  void CopyDataBaseFromAsset(){
        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}