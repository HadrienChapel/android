package com.example.aymeric.multiplecurrencyconverter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.content.res.Resources;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private TextView view;
    private Double usd;
    private Double aed;
    private Double eur;
    private Double bhd;
    private Double gbp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.editText);
        view = (TextView) findViewById(R.id.textView3);
        getJson();
    }

    public double convertEUR2BHD(double m){
        return (m / bhd);
    }

    public double convertEUR2AED(double m){
        return (m / aed);
    }
    public double convertEUR2GBP(double m){
        return (m / gbp);
    }
    public double convertEUR2USD(double m){
        return (m / usd);
    }

    public void getJson()
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://free.currencyconverterapi.com/api/v3/convert?q=USD_EUR,BHD_EUR,AED_EUR,GBP_EUR,EUR_EUR&compact=y";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 5 characters of the response string.
                        System.out.println("JSON" + response);
                        ParseJson(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void ParseJson(String json)
    {
        try {
            JSONObject jObject = new JSONObject(json);
            usd = jObject.getJSONObject("USD_EUR").getDouble("val");
            aed = jObject.getJSONObject("AED_EUR").getDouble("val");
            gbp = jObject.getJSONObject("GBP_EUR").getDouble("val");
            bhd = jObject.getJSONObject("BHD_EUR").getDouble("val");
            eur = jObject.getJSONObject("EUR_EUR").getDouble("val");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void convert(View v)
    {
        switch (v.getId()) {
            case R.id.button:
                RadioButton bhd = (RadioButton) findViewById(R.id.radioButton2);
                RadioButton usd = (RadioButton) findViewById(R.id.radioButton);
                RadioButton gbp = (RadioButton) findViewById(R.id.radioButton3);
                RadioButton aed = (RadioButton) findViewById(R.id.radioButton4);
                if (text.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a value number", Toast.LENGTH_LONG).show();
                    return;
                }
                float iV = Float.parseFloat(text.getText().toString());
                if (bhd.isChecked()) {
                    view.setText(String.valueOf(convertEUR2BHD(iV)));
                    bhd.setChecked(false);
                    usd.setChecked(false);
                    gbp.setChecked(false);
                    aed.setChecked(false);
                } else if (usd.isChecked()) {
                    view.setText(String.valueOf(convertEUR2USD(iV)));
                    bhd.setChecked(false);
                    usd.setChecked(false);
                    gbp.setChecked(false);
                    aed.setChecked(false);                }
                else if (gbp.isChecked()){
                    view.setText(String.valueOf(convertEUR2GBP(iV)));
                    bhd.setChecked(false);
                    usd.setChecked(false);
                    gbp.setChecked(false);
                    aed.setChecked(false);                }
                else if (aed.isChecked()){
                    view.setText(String.valueOf(convertEUR2AED(iV)));
                    bhd.setChecked(false);
                    usd.setChecked(false);
                    gbp.setChecked(false);
                    aed.setChecked(false);                }
                break;
        }
    }

}
