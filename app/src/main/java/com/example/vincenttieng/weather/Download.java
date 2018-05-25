package com.example.vincenttieng.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Download extends AppCompatActivity {


    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button btn = (Button) findViewById(R.id.dl);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londres")));
                IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
                LocalBroadcastManager.getInstance(Download.this).registerReceiver(new BierUpdate(), intentFilter);
                GetWeatherServices.startActionWeather(Download.this);

            }
        });

        rv = (RecyclerView) findViewById(R.id.rv_beer);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new BeersAdapter(getWeatherFromFile()));
    }


    public static final String BIERS_UPDATE = "com.octip.cours.inf4042_11.BIERS_UPDATE";

    public class BierUpdate extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("tag", "OnReceive");
            BeersAdapter adapter = (BeersAdapter) rv.getAdapter();

            adapter.setNewBeer(getWeatherFromFile());


        }
    }

    public JSONArray getWeatherFromFile() {
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "weather.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            JSONArray test = new JSONArray(new String(buffer,"utf-8"));
            int length = test.length();

            Log.d("tag", "Longueur : " + Integer.toString(length));
            return test; // construction du tableau
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }
}