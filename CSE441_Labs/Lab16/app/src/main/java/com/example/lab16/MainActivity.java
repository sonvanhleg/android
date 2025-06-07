package com.example.lab16;

import android.content.SharedPreferences;
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
    EditText edta,edtb,edtkq;
    Button btncong,btnclear;
    TextView txtlichsu;
    String lichsu = "";

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myprefs = getSharedPreferences("mysave",MODE_PRIVATE);
        SharedPreferences.Editor myedit = myprefs.edit();
        myedit.putString("ls",lichsu);
        myedit.commit();
    }

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
        edtkq = (EditText) findViewById(R.id.edtkq);
        btncong = (Button) findViewById(R.id.btntong);
        btnclear = (Button) findViewById(R.id.btnclear);
        txtlichsu = (TextView) findViewById(R.id.txtlichsu);
        SharedPreferences myprefs = getSharedPreferences("mysave",MODE_PRIVATE);
        lichsu = myprefs.getString("ls","");
        txtlichsu.setText(lichsu);
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int kq = a+b;
                edtkq.setText(kq+"");
                lichsu +=a+" + "+b+" = "+kq;
                txtlichsu.setText(lichsu);
                lichsu +="\n";
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichsu = "";
                txtlichsu.setText(lichsu);
            }
        });
    }
}