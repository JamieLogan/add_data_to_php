package com.jamie.sql;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;



/**
 * Created by jaimelogan on 18/03/2014.
 */
public class PHP_query extends AsyncTask<String,Void,String> {
    String QResult="nul";

    public PHP_query(){
    }

    protected void  onPreExecute(){

    }

    protected String doInBackground(String... arg0){
        try{
            String link = "http://cnut.eng.gla.ac.uk/dsp5/sensorid.php?sensor="+ arg0[0];
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = httpclient.execute(request);
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line="";
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            return sb.toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    protected void onPostExecute(String result){
        int leg, x, field, record, bit_delay;
        //char sample;
       String[][] db = new String[1024][6];
        String buffer;
        buffer="";
        field=0;
        record=0;
        bit_delay=1;
        boolean en=false;
        leg = result.length();
        for (x=0; x<leg; x++){
            if (result.charAt(x)=='\"'){
               en=!en;
                if(en==false){
                    field++;
                    if(field==6){
                        record++;
                    }
                }
               bit_delay=1;
            }
            if(en==true & bit_delay==0){
                buffer.valueOf(result.charAt(x));
                db[record][field].concat(buffer);
            }
            bit_delay-=1;
        }

        Global.result = result;

    }

}
