package com.example.lab15;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtmalop,edttenlop,edtsiso;
    Button btninsert,btnupdate,btndelete,btnquery;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
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
        edtmalop =(EditText) findViewById(R.id.edtmalop);
        edttenlop = (EditText) findViewById(R.id.edttenlop);
        edtsiso = (EditText) findViewById(R.id.edtsiso);
        btndelete = (Button) findViewById(R.id.btndelete);
        btninsert = (Button) findViewById(R.id.btninsert);
        btnquery = (Button) findViewById(R.id.btnquery);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        lv = (ListView) findViewById(R.id.lv1);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mylist);
        lv.setAdapter(myadapter);
        mydatabase = openOrCreateDatabase("qlsinhvien.db",MODE_PRIVATE,null);
        try {
            String sql = "CREATE TABLE tbllop(malop TEXT primary key,tenlop TEXT,siso INTEGER)";
            mydatabase.execSQL(sql);
        }catch (Exception e){
            Log.e("Error","Table đã tồn tại");
        }
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtmalop.getText().toString();
                String tenlop = edttenlop.getText().toString();
                int siso = Integer.parseInt(edtsiso.getText().toString());
                ContentValues myvalue = new ContentValues();
                myvalue.put("malop",malop);
                myvalue.put("tenlop",tenlop);
                myvalue.put("siso",siso);
                String msg = "";
                if(mydatabase.insert("tbllop",null,myvalue) == -1){
                    msg = "Fail to insert Record!";
                }
                else{
                    msg = "Insert record Sucessfully";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String malop = edtmalop.getText().toString();
                 int n = mydatabase.delete("tbllop","malop = ?",new String[]{malop});
                 String msg = "";
                 if(n == 0){
                     msg = "No record to delete";
                 }
                 else{
                     msg = n+" record is deleted";
                 }
                 Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siso = Integer.parseInt(edtsiso.getText().toString());
                String malop = edtmalop.getText().toString();
                ContentValues myvalue  = new ContentValues();
                myvalue.put("siso",siso);
                int n = mydatabase.update("tbllop",myvalue,"malop = ?",new String[]{malop});
                String msg = "";
                if(n == 0){
                    msg = "No recod to Update";
                }
                else{
                    msg = n+" record is updated";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist.clear();
                Cursor c = mydatabase.query("tbllop",null,null,null,null,null,null);
                c.moveToNext();
                String data = "";
                while(c.isAfterLast() == false){
                    data = c.getString(0)+" - "+c.getString(1)+" - "+c.getString(2);
                    c.moveToNext();
                    mylist.add(data);
                }
                c.close();
                myadapter.notifyDataSetChanged();
            }
        });
    }
}