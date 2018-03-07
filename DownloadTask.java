package com.example.vinaysharma.n;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vinay sharma on 28-01-2018.
 */

public class DownloadTask extends AsyncTask<String, Void, String> {



    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnecttion;

        try {

            url = new URL(urls[0]);
            urlConnecttion = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnecttion.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while (data != -1) {
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
