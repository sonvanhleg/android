package com.example.lab04;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText edtCel,edtFar;
    Button btncf,btnfc,btnClear;
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
        edtCel =(EditText) findViewById(R.id.txtCel);
        edtFar = (EditText) findViewById(R.id.txtFar);
        btncf = (Button) findViewById(R.id.btnCel);
        btnfc = (Button) findViewById(R.id.btnFar);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dcf = new DecimalFormat("#.00");
                int Far = Integer.parseInt(edtFar.getText()+"");
                edtCel.setText(""+dcf.format((Far-32)/1.8));
            }
        });
        btncf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dcf = new DecimalFormat("#.00");
                int Cel = Integer.parseInt(edtCel.getText()+"");
                edtFar.setText(""+dcf.format(Cel*1.8+32));
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtFar.setText("");
                edtCel.setText("");
            }
        });
    }
}