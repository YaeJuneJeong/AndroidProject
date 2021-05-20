package com.example.validation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class OnOff extends AppCompatActivity {
    final private String modelNum = "check-010";
    private static String IP_ADDRESS = "jyj98071.cafe24.com";
    private static String TAG = "php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_off);
        Button modelval = findViewById(R.id.modelValidation);


        Intent intent = getIntent();
        final String userEmail = intent.getExtras().getString("userEmail");

        final TextView modelinput = findViewById(R.id.modelInput);
        final String userTechNum = modelinput.getText().toString();
        modelval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modelNum.equals(modelinput.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "등록성공", Toast.LENGTH_LONG).show();
                    InsertData insertData =new InsertData();
                    insertData.execute("http://" + IP_ADDRESS + "/dbinfo.php", userEmail,userTechNum);

                    Intent model = new Intent(OnOff.this, alarmActivity.class);
                    OnOff.this.startActivity(model);
                } else {
                    Toast.makeText(getApplicationContext(), "모델번호가 일치하지 않습니다", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(OnOff.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String userEmail = (String)params[1];
            String userTechNum = (String)params[2];

            String serverURL = (String)params[0];
            String postParameters = "userEmail=" + userEmail+ "&userTechNum" + userTechNum;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }

}

