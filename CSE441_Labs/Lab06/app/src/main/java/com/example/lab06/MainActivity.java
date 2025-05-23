package com.example.lab06;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtname,edtcmnd,edtbs;
    CheckBox chkdb,chkds,chkdc;
    Button btnsend;
    RadioGroup group;
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
        edtname = (EditText) findViewById(R.id.edtname);
        edtcmnd = (EditText) findViewById(R.id.edtcmnd);
        edtbs = (EditText) findViewById(R.id.edtbs);
        chkdb = (CheckBox) findViewById(R.id.chkdb);
        chkds = (CheckBox) findViewById(R.id.chkds);
        chkdc = (CheckBox) findViewById(R.id.chkdc);
        btnsend = (Button) findViewById(R.id.btnsend);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowInformation();
            }
        });
    }
    public void doShowInformation(){
        String name = edtname.getText().toString();
        name = name.trim();
        if(name.length() < 3){
            edtname.requestFocus();
            edtname.selectAll();
            Toast.makeText(this,"Tên phải > 3 ký tự",Toast.LENGTH_LONG).show();
            return;
        }
        String cmnd = edtcmnd.getText().toString();
        cmnd = cmnd.trim();
        if(cmnd.length() != 9){
            edtcmnd.requestFocus();
            edtcmnd.selectAll();
            Toast.makeText(this,"CMND phải đủ 9 ký tự",Toast.LENGTH_LONG).show();
            return;
        }
        String bang = "";
        group = (RadioGroup) findViewById(R.id.idgroup);
        int id = group.getCheckedRadioButtonId();
        if(id == -1){
            Toast.makeText(this,"Phải chọn bằng cấp",Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = findViewById(id);
        bang = rad.getText()+"";
        String sothich = "";
        if(chkdb.isChecked()){
            sothich+=chkdb.getText()+"\n";
        }
        if(chkds.isChecked()){
            sothich+=chkds.getText()+"\n";
        }
        if (chkdc.isChecked()){
            sothich+=chkdc.getText()+"\n";
        }
        String bosung = edtbs.getText()+"";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        String msg = name+"\n";
        msg+=cmnd+"\n";
        msg+=bang+"\n";
        msg+=sothich;
        msg+="---------------\n";
        msg+="Thông tin bổ sung: \n";
        msg+=bosung+"\n";
        msg+="---------------\n";
        builder.setMessage(msg);
        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Question");
        b.setMessage("Are you sure you want to exit?");
        b.setIcon(R.drawable.ic_launcher_background);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.create().show();
    }
}