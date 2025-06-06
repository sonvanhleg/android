package com.example.lab134;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {
    private Bundle extra;
    TextView txtname2;
    ImageView img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtname2 = (TextView) findViewById(R.id.textView2);
        img2 = (ImageView) findViewById(R.id.imageView2);
        extra = getIntent().getExtras();
        int position = extra.getInt("Title");
        txtname2.setText(MainActivity.arrayName[position]);
        img2.setImageResource(MainActivity.imageName[position]);
    }
}