package com.example.lab73;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    EditText edtna,edtnb;
    Button btnTotal,btnSignal;
    Intent myintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtna = (EditText) findViewById(R.id.edtna);
        edtnb = (EditText) findViewById(R.id.edtnb);
        btnTotal = (Button) findViewById(R.id.btnTotal);
        btnSignal = (Button) findViewById(R.id.btnSignal);
        myintent = getIntent();
        int a = myintent.getIntExtra("soa",0);
        int b = myintent.getIntExtra("sob",0);
        edtna.setText(a+"");
        edtnb.setText(b+"");
        btnTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = a+b;
                myintent.putExtra("total",sum);
                setResult(33,myintent);
                finish();
            }
        });
        btnSignal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sub = a-b;
                myintent.putExtra("signal",sub);
                setResult(34,myintent);
                finish();
            }
        });
    }
}