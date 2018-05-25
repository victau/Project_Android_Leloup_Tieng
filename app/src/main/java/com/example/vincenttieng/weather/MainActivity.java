package com.example.vincenttieng.weather;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView titre_item;
    TextView max_temp_item;
    TextView min_temp_item;
    TextView location_item;
    ImageView image_item;
    ImageView Web;
    ImageView web2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        titre_item = (TextView)findViewById(R.id.titre_tv);
        max_temp_item = (TextView)findViewById(R.id.temp_max_tv);
        min_temp_item = (TextView)findViewById(R.id.temp_min_tv);
        location_item = (TextView)findViewById(R.id.ville_tv);
        image_item = (ImageView)findViewById(R.id.Icon_iv);
        Web = (ImageView)findViewById(R.id.Web);
        new TestRequest().execute();
        Web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.parisinfo.com/decouvrir-paris/balades-a-paris");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });
        web2= (ImageView)findViewById(R.id.pasbeau);
        web2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://quefaire.paris.fr/36/que-faire-a-paris-quand-il-pleut");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });
    }

  /*  private class Request5jour extends AsyncTask<Void,Void,ClimatElement[]>{


        @Override
        protected ClimatElement[] doInBackground(Void... voids) {

            String urlString = "http://api.openweathermap.org/data/2.5/forecast?q=London,us&appid=d82353bb4ef1c1ab8c741a3d9877ad3f";

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(urlString)
                    .build();

            return new ClimatElement[0];
        }
    }*/



    private class TestRequest extends AsyncTask <Void,Void,ClimatElement>{
        float temp_max;
        float temp_min;
        int temp_maxInt;
        int temp_minInt;
        @Override
            protected ClimatElement doInBackground(Void... params) {
                String urlTest = "http://api.openweathermap.org/data/2.5/weather?q=Paris&APPID=21c28e3675f2918f90e632ef85442b77";

                OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                        .url(urlTest)
                        .build();

                ClimatElement climatElement = null;

                try {
                    Response response = client.newCall(request).execute();
                    String bodyReponse = response.body().string();
                    climatElement = parseJSON(bodyReponse);
                    temp_max = (int)Float.valueOf(climatElement.getMaxTemp()).floatValue();
                    temp_min = (int)Float.valueOf(climatElement.getMinTemp()).floatValue();
                     temp_minInt = (int) temp_min / 32;
                     temp_maxInt = (int) temp_max / 32;
                    Log.i("Reponse",response.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return climatElement;
            }

        @Override
        protected void onPostExecute(ClimatElement climatElement) {
            super.onPostExecute(climatElement);
          String titreItem = climatElement.getNomDuJour() + " " +
                     climatElement.getJour() + " " + climatElement.getNomDuMois() ;
            if (temp_minInt < 0){
                image_item.setImageResource(R.drawable.froid);
            }
            if (temp_maxInt >= 15){
                image_item.setImageResource(R.drawable.soleil);
            }
            if ((temp_minInt > 0) && (temp_maxInt < 15)){
                image_item.setImageResource(R.drawable.nuageux);
            }

            titre_item.setText(titreItem);
            max_temp_item.setText("Temp max : " + temp_maxInt + " °C");
            min_temp_item.setText("Temp min : " + temp_minInt + " °C");
            location_item.setText( climatElement.getLocation());

        }
    }

    private ClimatElement parseJSON(String bodyReponse) throws JSONException {
        JSONObject mainJSON = new JSONObject(bodyReponse);
        String ville = mainJSON.get("name").toString();
        JSONObject sys = mainJSON.getJSONObject("sys");
        String pays = sys.get("country").toString();
        String location = ville + ", " + pays;

        JSONObject main = mainJSON.getJSONObject("main");
        String minTemp = main.get("temp_min").toString();
        String maxTemp = main.get("temp_max").toString();
        String dt = mainJSON.get("dt").toString();
        int timeStamp = Integer.valueOf(dt);
        Calendar mydate = Calendar.getInstance();
        mydate.setTimeInMillis((long)timeStamp*1000);
        String resultat = mydate.get(Calendar.DAY_OF_MONTH)+"."+mydate.get(Calendar.MONTH)+"."+mydate.get(Calendar.YEAR);

        String nomDuMois = Formattage.getMois(mydate.get(Calendar.MONTH));
        String nomDuJour = Formattage.getJour(mydate.get(Calendar.DAY_OF_WEEK));

        ClimatElement climatElement = new ClimatElement(ville, pays,
                location, minTemp, maxTemp, timeStamp, nomDuMois,
                nomDuJour, mydate.get(Calendar.DAY_OF_MONTH),
                mydate.get(Calendar.MONTH), mydate.get(Calendar.YEAR) );
        return climatElement;

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Home :
                Toast.makeText(this, R.string.accueil,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.Quit :
                System.exit(0);
                return true;
            case R.id.notif :
                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                Notification notification = builder.setSmallIcon(R.drawable.soleil)
                        .setContentTitle("WeatherApp")
                        .setContentText("Obtenez votre météo en temps et en heure")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build();
                manager.notify(0,notification);
                return true;
            case R.id.download :
                Toast.makeText(this, "Test",Toast.LENGTH_LONG).show();
                Intent intentdownload = new Intent(this,Download.class);
                startActivity(intentdownload);
            break;



        }
        return super.onOptionsItemSelected(item);
}}

