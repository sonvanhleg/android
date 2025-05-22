package com.example.lab52;

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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btncontinue, btnsolve, btnexit;
    EditText edta, edtb, edtc;
    TextView txtresult;

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
        btncontinue = (Button) findViewById(R.id.btncontinue);
        btnsolve = (Button) findViewById(R.id.btnsolve);
        btnexit = (Button) findViewById(R.id.btnexit);
        edta = (EditText) findViewById(R.id.edta);
        edtb = (EditText) findViewById(R.id.edtb);
        edtc = (EditText) findViewById(R.id.edtc);
        txtresult = (TextView) findViewById(R.id.txtresult);
        btnsolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText()+"");
                int b = Integer.parseInt(edtb.getText()+"");
                int c = Integer.parseInt(edtc.getText()+"");
                String result = "";
                DecimalFormat dcf = new DecimalFormat("0.00");
                if(a==0){
                    if(b==0){
                        if(c==0){
                            result = "pt vô số nghiệm";
                        }
                        else{
                            result = "pt vô nghiệm";
                        }
                    }
                    else{
                        result = "pt có 1 nghiệm , x = "+dcf.format(-c/b);
                    }
                }
                else{
                    double delta = b*b-4*a*c;
                    if(delta < 0){
                        result = "pt vô nghiệm";
                    }
                    else if(delta == 0){
                        result = "pt có nghiệm kép x1=x2= "+dcf.format(-b/(2*a));
                    }
                    else{
                        result = "pt có 2 nghiệm: x1 = "+dcf.format((-b+Math.sqrt(delta))/(2*a))
                                +"; x2= "+dcf.format((-b-Math.sqrt(delta))/(2*a));
                    }
                }
                txtresult.setText(result);
            }

        });
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edta.setText("");
                edtb.setText("");
                edtc.setText("");
                edta.requestFocus();
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}