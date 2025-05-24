package com.example.lab72;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edta,edtb;
    Button btnReult;
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
        edta = (EditText) findViewById(R.id.edta);
        edtb = (EditText) findViewById(R.id.edtb);
        btnReult = (Button) findViewById(R.id.btnReult);
        btnReult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this,ResultActivity.class);
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edta.getText().toString());
                //Dong goi du lieu
                Bundle mybundle = new Bundle();
                mybundle.putInt("soa",a);
                mybundle.putInt("sob",b);
                myintent.putExtra("mypackage",mybundle);
                startActivity(myintent);
            }
        });
    }
}