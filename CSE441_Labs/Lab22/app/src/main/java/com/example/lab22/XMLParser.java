package com.example.lab22;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class XMLParser {
    public String getXMLFromUrl(String url){
        String xml = null;
        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (ClientProtocolExeption e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return xml;
    }
}
