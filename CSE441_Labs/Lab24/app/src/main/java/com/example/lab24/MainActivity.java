package com.example.lab24;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvTigia;
    TextView txtdate;
    ArrayList<Tygia> dstygia;
    MyArrayAdapter myadapter;
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
        lvTigia = (ListView) findViewById(R.id.lv);
        txtdate = (TextView) findViewById(R.id.txtdate);
        getdate();
        dstygia = new ArrayList<Tygia>();
        myadapter = new MyArrayAdapter(MainActivity.this,R.layout.item,dstygia);
        lvTigia.setAdapter(myadapter);
        TyGiaTask task = new TyGiaTask();
        task.execute();
    }
    public void getdate(){
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm nay: "+simpleDate.format(currentDate));
    }
    class TyGiaTask extends AsyncTask<Void, Void, ArrayList<Tygia>>{

        @Override
        protected ArrayList<Tygia> doInBackground(Void... voids) {
            ArrayList<Tygia> ds = new ArrayList<Tygia>();
            try {
                // Đây là link Server
                URL url = new URL("https://dongabank.com.vn/exchange/export");
                // Mở connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Thiết lập method là GET để lấy dữ liệu
                connection.setRequestMethod("GET");

                // Thiết lập thuộc tính header cho request
                connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible )");
                connection.setRequestProperty("Accept", "*/*");

                // Lấy dữ liệu từ server trả về
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                String line = br.readLine();
                StringBuilder builder = new StringBuilder();
                while (line != null) {
                    builder.append(line);
                    line = br.readLine();
                }

                String json = builder.toString();
                // Bỏ hai ngoặc tròn trong dữ liệu trả về
                json = json.replace("(", "").replace(")", "");

                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("items");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    Tygia tiGia = new Tygia();

                    tiGia.setType(item.getString("type"));

                    if (item.has("imageurl")) {
                        tiGia.setImageurl(item.getString("imageurl"));
                        url = new URL(tiGia.getImageurl());
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible )");
                        connection.setRequestProperty("Accept", "*/*");

                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                        tiGia.setBitmap(bitmap);
                    }

                    if (item.has("muatienmat")) {
                        tiGia.setMuatienmat(item.getString("muatienmat"));
                    }
                    if (item.has("muack")) {
                        tiGia.setMuack(item.getString("muack"));
                    }
                    if (item.has("bantienmat")) {
                        tiGia.setBantienmat(item.getString("bantienmat"));
                    }
                    if (item.has("banck")) {
                        tiGia.setBanck(item.getString("banck"));
                    }

                    ds.add(tiGia);
                }

                Log.d("JSON_DONGA", json);

            } catch (Exception ex) {
                Log.e("Lỗi", ex.toString());
            }

            return ds;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Tygia> result) {
            super.onPostExecute(result);
            myadapter.clear();
            myadapter.addAll(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}