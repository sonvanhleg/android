package com.example.lab51;

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
    EditText edtyear;
    Button btnConvert;
    TextView txtyear;
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
        edtyear = (EditText) findViewById(R.id.edtyear);
        btnConvert = (Button) findViewById(R.id.btnConvert);
        txtyear = (TextView) findViewById(R.id.txtyear);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String can="",chi="";
                int calendaryear = Integer.parseInt(edtyear.getText().toString());
                switch (calendaryear%10){
                    case 0:can = "Canh";break;
                    case 1:can = "Tân";break;
                    case 2:can = "Nhâm";break;
                    case 3:can = "Quý";break;
                    case 4:can = "Giáp";break;
                    case 5:can = "Ất";break;
                    case 6:can = "Bính";break;
                    case 7:can = "Đinh";break;
                    case 8:can = "Mậu";break;
                    case 9:can = "Kỳ";break;
                }
                switch (calendaryear%12){
                    case 0:chi = "Thân";break;
                    case 1:chi = "Dậu";break;
                    case 2:chi = "Tuất";break;
                    case 3:chi = "Hợi";break;
                    case 4:chi = "Tí";break;
                    case 5:chi = "Sữu";break;
                    case 6:chi = "Dần";break;
                    case 7:chi = "Mẹo";break;
                    case 8:chi = "Thìn";break;
                    case 9:chi = "Tị";break;
                    case 10:chi = "Ngọ";break;
                    case 11:chi = "Mùi";break;
                }
                txtyear.setText(can + " " + chi);
            }
        });
    }

}