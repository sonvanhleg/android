package com.example.lab22;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnparse;
    ListView lv1;
    ArrayAdapter<String> myadapter;
    ArrayList<String> mylist;
    String URL = "http://api.androidhive.info/pizza/?format=xml";
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
        btnparse = (Button) findViewById(R.id.btnparse);
        lv1 = (ListView) findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,mylist);
        lv1.setAdapter(myadapter);
        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadExampleTask task = new LoadExampleTask();
                task.execute();
            }
        });
    }
    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> list = new ArrayList<String>();
            try {
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser parser = fc.newPullParser();
                XMLParser myparser = new XMLParser();
                String xml = myparser.getXMLFromUrl(URL);
                parser.setInput(new StringReader(xml));
                int eventType = -1;
                String nodeName;
                String datashow = "";
                while (eventType!=XmlPullParser.END_DOCUMENT){
                    eventType = parser.next();
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("id")){
                                datashow+=parser.nextText()+"-";
                            } else if (nodeName.equals("name")) {
                                datashow+=parser.nextText();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            nodeName = parser.getName();
                            if(nodeName.equals("item")){
                                list.add(datashow);
                                datashow = "";
                            }
                            break;
                    }
                }
            }catch (XmlPullParserException e1){
                e1.printStackTrace();
            }
            catch (FileNotFoundException e2){
                e2.printStackTrace();
            }
            catch (IOException e3){
                e3.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<String> Result) {
            super.onPostExecute(Result);
            myadapter.clear();
            myadapter.addAll(Result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}