package com.example.lab122;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapter;
    EditText edtwork,edthour,edtmi;
    Button btnwork;
    TextView txtdate;
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
        edthour = (EditText) findViewById(R.id.edthour);
        edtwork = (EditText) findViewById(R.id.edtwork);
        edtmi = (EditText) findViewById(R.id.edtmi);
        btnwork = (Button) findViewById(R.id.btnadd);
        lv = (ListView) findViewById(R.id.lv1);
        txtdate = (TextView) findViewById(R.id.txtdate);
        arraywork = new ArrayList<>();
        arrAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arraywork);
        lv.setAdapter(arrAdapter);
        Date currentDate = Calendar.getInstance().getTime();
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm nay: "+simpleDateFormat.format(currentDate));
        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtwork.getText().toString().equals("")||edthour.getText().toString().equals("")||edtmi.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all, information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            builder.show();
                        }
                    });
                }
                else{
                    String str = edtwork.getText().toString()+" - "+edthour.getText().toString()+": "+edtmi.getText().toString();
                    arraywork.add(str);
                    arrAdapter.notifyDataSetChanged();
                    edthour.setText("");
                    edtmi.setText("");
                    edtwork.setText("");
                }
            }
        });
    }
}