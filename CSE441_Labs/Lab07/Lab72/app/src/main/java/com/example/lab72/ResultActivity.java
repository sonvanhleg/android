package com.example.lab72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    TextView txtResult;
    Button btnBack;
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
        txtResult = (TextView) findViewById(R.id.txtReult);
        btnBack = (Button) findViewById(R.id.btnBack);
        Intent myinten = getIntent();
        Bundle mybundle = myinten.getBundleExtra("mypackage");
        int a = mybundle.getInt("soa");
        int b = mybundle.getInt("sob");
        String result = "";
        if(a == 0 && b==0){
            result = "pt vô số nghiệm";
        }
        else if(a==0 && b !=0){
            result = "pt vô nghiệm";
        }
        else {
            result = "Nghiệm pt là: "+(-1.0)*b/a;
        }
        txtResult.setText(result);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}