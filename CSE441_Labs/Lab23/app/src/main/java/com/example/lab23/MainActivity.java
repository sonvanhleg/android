package com.example.lab23;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
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
    static ListView lv1;
    ArrayList<List> mylist;
    MyArrayAdapter myadapter;
    String nodeName;
    String title ="";
    String link = "";
    String description = "";
    String des = "";
    String urlStr = "";
    Bitmap mIcon_val = null;
    String URL= "https://vnexpress.net/rss/tin-moi-nhat.rss";
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
        lv1 = (ListView) findViewById(R.id.lv1);
        mylist = new ArrayList<List>();
        myadapter = new MyArrayAdapter(MainActivity.this,R.layout.layout_listview,mylist);
        lv1.setAdapter(myadapter);
        LoadExampleTask task = new LoadExampleTask();
        task.execute();
    }
    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<List>>
    {
        @Override
        protected ArrayList<List> doInBackground(Void... voids)
        {
            mylist = new ArrayList<List>();
            try {

                XmlPullParserFactory fc=XmlPullParserFactory.newInstance();
                XmlPullParser parser= fc.newPullParser();

                XMLParser myparser = new XMLParser();
                String xml = myparser.getXmlFromUrl(URL);

                parser.setInput(new StringReader(xml));

                int eventType=-1;
                while(eventType!=XmlPullParser.END_DOCUMENT)
                {
                    eventType=parser.next();
                    switch(eventType)
                    {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            nodeName=parser.getName();
                            if(nodeName.equals("title")){
                                title=parser.nextText();
                            }
                            else if(nodeName.equals("link")){
                                link = parser.nextText();
                            }
                            else if(nodeName.equals("description")){
                                description=parser.nextText();
                                try {
                                    urlStr =
                                            description.substring((description.indexOf("src=") + 5),
                                                    (description.indexOf("></a") - 2));
                                }
                                catch (Exception e1)
                                {
                                    e1.printStackTrace();
                                }
                                des=
                                        description.substring(description.indexOf("</br>")+5);
                                try {
                                    URL newurl = new URL(urlStr);
                                    mIcon_val =
                                            BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            nodeName=parser.getName();
                            if(nodeName.equals("item")){
                                mylist.add(new List(mIcon_val, title, des,link));
                            }
                            break;
                    }
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mylist;
        }
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            myadapter.clear();
        }
        @Override
        protected void onPostExecute(ArrayList<List> result) {

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