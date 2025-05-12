package com.example.lab03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edt1,edt2,edt3;
    Button btnadd,btnminus,btnproduct,btnquotient;
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
        edt1 = findViewById(R.id.edta);
        edt2 = findViewById(R.id.edtb);
        edt3 = findViewById(R.id.edtc);
        btnadd = findViewById(R.id.btnadd);
        btnminus = findViewById(R.id.btnminus);
        btnproduct = findViewById(R.id.btnpoduct);
        btnquotient = findViewById(R.id.btnquotient);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                edt3.setText("a + b ="+(a+b));
            }
        });
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                edt3.setText("a - b ="+(a-b));
            }
        });
        btnproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                edt3.setText("a * b ="+(a*b));
            }
        });
        btnquotient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                if(b == 0){
                    edt3.setText("B phai khac 0");
                }
                else{
                edt3.setText("a / b ="+(a/b));}
            }
        });
    }
}