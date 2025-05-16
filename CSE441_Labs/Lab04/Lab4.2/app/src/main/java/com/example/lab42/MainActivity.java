package com.example.lab42;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnBMI;
    EditText edtName,edtHeight,edtWeight,edtBMT,edtDiagnosis;
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
        btnBMI =(Button) findViewById(R.id.btnBMI);
        edtName = (EditText) findViewById(R.id.edtName);
        edtHeight = (EditText) findViewById(R.id.edtHeight);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        edtBMT = (EditText) findViewById(R.id.edtBMI);
        edtDiagnosis = (EditText) findViewById(R.id.edtdiagnosis);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double H = Double.parseDouble(edtHeight.getText()+"");
                double W = Double.parseDouble(edtWeight.getText()+"");
                double BMI = W/Math.pow(H,2);
                String Diagnosis = "";
                if(BMI < 18){
                    Diagnosis = "Bạn gầy";
                }
                else if(BMI <= 24.9){
                    Diagnosis = "Bạn bình thường";
                }
                else if(BMI <= 29.9){
                    Diagnosis = "Bạn béo phì độ 1";
                }
                else if(BMI < 34.9){
                    Diagnosis = "Bạn béo phì độ 2";
                }
                else{
                    Diagnosis = "Bạn béo phì độ 3";
                }
                DecimalFormat dcf = new DecimalFormat("#.0");
                edtBMT.setText(dcf.format(BMI));
                edtDiagnosis.setText(Diagnosis);
            }
        });
    }
}