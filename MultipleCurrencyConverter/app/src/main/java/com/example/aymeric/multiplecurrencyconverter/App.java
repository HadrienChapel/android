package com.example.aymeric.multiplecurrencyconverter;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Aymeric on 16/04/2016.
 */
public class App extends Application {
    private static App mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(this);
    }

    public static App GetInstance(){return mInstance;}

    public RequestQueue getRequestQueue() {return mRequestQueue;}
}
