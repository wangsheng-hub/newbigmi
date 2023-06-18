package com.example.xingzuoweb.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
    public static String getJSONFromNet(String path){
        String json = "";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            int hasRead = 0;
            byte[] buf = new byte[1024];
            while (true){
                hasRead = is.read(buf);
                if (hasRead==-1){
                    break;
                }
                baos.write(buf,0,hasRead);
            }
            is.close();
            json = baos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
