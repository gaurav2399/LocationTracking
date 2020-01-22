package com.gaurav.locationtracking.ServerHandler;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendDeviceDetails extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes("PostData=" + params[1]);
            wr.flush();
            wr.close();
            return "SEND";

        } catch (Exception e) {
            e.printStackTrace();
            return "NOT SEND";
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    @Override
    protected void onPostExecute(String msg) {
        super.onPostExecute(msg);
        Log.e("Send Device Details",msg);
    }
}

