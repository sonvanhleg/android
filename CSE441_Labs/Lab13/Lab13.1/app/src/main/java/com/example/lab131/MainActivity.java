package com.example.lab131;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView selection;
    AutoCompleteTextView singleComplete;
    MultiAutoCompleteTextView multiComplete;
    String arr[]={"Hà Nội","Huế","Sài Gòn","Hà Giang","Hội An","Kiên Giang","Lâm Đồng","Lâm Khánh"};
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
        selection = (TextView) findViewById(R.id.txtselection);
        singleComplete = (AutoCompleteTextView) findViewById(R.id.editauto);
        ArrayAdapter myadapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        );
        singleComplete.setAdapter(myadapter);
        multiComplete = (MultiAutoCompleteTextView) findViewById(R.id.multi);
        multiComplete.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        ));
        multiComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        singleComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                selection.setText(singleComplete.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}