package com.example.demo_contrain;

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
    //Khai bao thuoc tinh
    EditText edtA,edtB,edtResult;
    Button btnSum;
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
        //Tham chieu den cac phan tu
        edtA =(EditText) findViewById(R.id.edtA);
        edtB =(EditText) findViewById(R.id.edtB);
        edtResult =(EditText) findViewById(R.id.edtResult);
        btnSum =(Button) findViewById(R.id.btnSum);
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xu ly su kien
                float a = Float.parseFloat(edtA.getText().toString());
                float b = Float.parseFloat(edtB.getText().toString());
                float c = a+b;
                edtResult.setText(c+"");
            }
        });
    }
}