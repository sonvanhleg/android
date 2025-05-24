package com.example.lab73;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edta,edtb,edtResult;
    Button btnResult;
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
        edta = (EditText) findViewById(R.id.edtA);
        edtb = (EditText) findViewById(R.id.edtB);
        edtResult = (EditText) findViewById(R.id.edtResult);
        btnResult = (Button) findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this,ResultActivity.class);
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                myintent.putExtra("soa",a);
                myintent.putExtra("sob",b);
                startActivityForResult(myintent,99);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == 33){
            int sum = data.getIntExtra("total",0);
            edtResult.setText(sum+"");
        }
        if(requestCode == 99 && resultCode == 34){
            int sub = data.getIntExtra("signal",0);
            edtResult.setText(sub+"");
        }
    }
}