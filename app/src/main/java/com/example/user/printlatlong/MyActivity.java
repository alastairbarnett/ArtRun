package com.example.user.printlatlong;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MyActivity extends Activity {
    final String[] artNamesArray={"A is for Alexander B is for Bunyip C is for Canberra","A Short Walk","ACT Bushfire Memorial","ACT Memorial","Ainslie's Sheep","Alfred Deakin, A Life in Three Phases","Angel Wings","Aquila","Ark in the Ark and Beyond","Bogong Moths","bush pack (nil tenure)","Casuarina Pods","Centricity","Chalchiuhtlicue","Choice of Passage","Circuitry","Civic Carousel","Civic Memory Quilt","Commemoration","Crossing","Culture Fragment","Cumulus Aeronauticus","Cushion","Dancers on a Lakefront","Decollete","Dinornis Maximus","DNA","Dream Lens for the Future","Dreaming","Droplet","Ebb and Flow","Egle Queen of Serpents","Eternity","Ethos","Father and Son","Fenix 2","Fireline","Folding Ground Across the In Between","Fractal Weave","Fused Glass","Gather","Gathering Place","Gravity Circle","Gungahlin Drive Extension Artworks","Habitat","Harmonies","Here and Now","Honey Eater Rising","House Proud","Icarus","Illumicube","Kambah Firestorm Storytree Memorial","Lady With Flowers","Laserwrap","Life boat/Thuyen Cuu Roi","Life Cycle","Living Space","Longitude","Mal Meninga","Microscopia","Moai","Mohandas Karamchand Gandhi","Moth Ascending the Capital","Narrabundah: A Site Marker","Nest III","New Blood (Gateway Artwork)","On the Road Again","On the Staircase","Owl","Oyster","Patria es Humanidad (Our Country is Humanity)","Poet's Corner","Prime Minister John Curtin and Treasurer Ben Chifley, ca 1945","Private Poetry","Rain Pools","Reclamation: Culture, Spirit and Place","Red and Blue","Relic","Resilience","Resting Place of the Dragonfly","Running Lights","Sculpture No 23 (The Parcel)","Sculptured Form","Seqvanae","Sir Robert Menzies","Sky Shard Above the In Between","Soccer Players","Statue of Confucius","Stepping Out","Sweet Justice","The Ability to Imagine","The Big Little Man","The Canberra Times Fountain","The Fourth Pillar","The Glebe","The Goongarline","The Master's Voice","The Meeting Place","The Other Side of Midnight","The View From Here","The World Peace Flame Monument","Thespis","Toku","Touching Lightly","Tree of Knowledge","Twilight","Two Legged Marsupial","Two to Tango","Untitled (Andrew Townsend and Suzie Bleach, 1997)","Untitled (Andrew Townsend and Suzie Bleach, 2000)","Untitled (Bert Flugelman, 1979)","Untitled (Frank Hinder, 1963)","Untitled (G Duncan, B and F Waterman, 1985)","Untitled (Gerald and Margo Lewers, 1965)","Untitled (Jean-Pierre Rives, 2008)","Untitled (Keiko Amenomori-Schmeisser, 1999)","Untitled (Paul Piesley, 2001)","Untitled (S Maritti, 1967)","Vessel of (Horti)cultural Plenty","We Are Fishes","wide brown land","Wind Sculpture","Winds of Light","Windstone - A Trail of a Cloud","Woden Flood Memorial","Young Eagle"};
    final Double[] latitudeData = {-35.184821999999997,-35.343608000000003,-35.324330000000003,-35.280729999999998,-35.280535,-35.314557999999998,-35.41536,-35.28443,-35.235509999999998,-35.292465,-35.279271999999999,-35.282288999999999,-35.280636000000001,-35.277344999999997,-35.282107000000003,-35.278272999999999,-35.279558999999999,-35.279515000000004,-35.248493000000003,-35.285792000000001,-35.343637999999999,-35.233393,-35.278660000000002,-35.236072,-35.415635000000002,-35.334125,-35.299142000000003,-35.279375000000002,-35.280619999999999,-35.341839999999998,-35.277577000000001,-35.282012999999999,-35.279902,-35.281272999999999,-35.278320000000001,-35.277700000000003,-35.281162999999999,-35.276980000000002,-35.281336000000003,-35.280450000000002,-35.321151999999998,-35.406804000000001,-35.280301999999999,-35.224587,-35.262284999999999,-35.208300000000001,-35.283037,-35.232202000000001,-35.280729999999998,-35.279415999999998,-35.279510000000002,-35.372399999999999,-35.201095000000002,-35.277940000000001,-35.281213999999999,-35.279111999999998,-35.280709000000002,-35.277025000000002,-35.249364999999997,-35.345402,-35.281564000000003,-35.282412000000001,-35.391987,-35.332791999999998,-35.292243999999997,-35.222104999999999,-35.339449999999999,-35.279440000000001,-35.247638999999999,-35.280349000000001,-35.276946000000002,-35.278660000000002,-35.278396999999998,-35.278379999999999,-35.304478000000003,-35.278823000000003,-35.279111999999998,-35.279029999999999,-35.282314999999997,-35.290380999999996,-35.237031999999999,-35.278405999999997,-35.344617,-35.278104999999996,-35.288795999999998,-35.276980000000002,-35.217469999999999,-35.251204999999999,-35.333182999999998,-35.278863999999999,-35.345531000000001,-35.279581999999998,-35.280535,-35.280624000000003,-35.280239000000002,-35.186428999999997,-35.281398000000003,-35.371414000000001,-35.280472000000003,-35.410038999999998,-35.281106999999999,-35.281520999999998,-35.298124000000001,-35.311919000000003,-35.342357999999997,-35.278413,-35.280349000000001,-35.280546000000001,-35.323383,-35.264797000000002,-35.237912000000001,-35.279975999999998,-35.27711,-35.280354000000003,-35.281675,-35.278464,-35.27854,-35.281261999999998,-35.276918000000002,-35.410319000000001,-35.285756999999997,-35.281632000000002,-35.238003999999997,-35.281999999999996,-35.335048,-35.278218000000003};
    final Double[] longitudeData = {149.13259400000001,149.05157800000001,149.026768,149.131722,149.13251,149.108014,149.07267400000001,149.13487699999999,149.068299,149.121208,149.13188299999999,149.133792,149.13101399999999,149.12558799999999,149.13220799999999,149.13041899999999,149.13190900000001,149.132058,149.10146700000001,149.13461899999999,149.08525,149.040155,149.13181900000001,149.06793999999999,149.07134099999999,149.08642499999999,149.11027799999999,149.12908899999999,149.12676300000001,149.08390399999999,149.131733,149.136415,149.131044,149.13116199999999,149.13173599999999,149.12570400000001,149.13099800000001,149.128049,149.13054199999999,149.13146499999999,149.13340700000001,149.07226299999999,149.12820500000001,149.119148,149.14522199999999,149.04875899999999,149.12569400000001,149.15649500000001,149.13097099999999,149.13234499999999,149.13530600000001,149.06229300000001,149.14931799999999,149.12732399999999,149.130529,149.133149,149.132825,149.13025300000001,149.10171500000001,149.10075900000001,149.13118600000001,149.13605699999999,149.06947099999999,149.15405999999999,149.06473700000001,149.01924,149.07648499999999,149.13227000000001,149.06763900000001,149.12790799999999,149.126025,149.13181900000001,149.131981,149.13194799999999,149.15231399999999,149.13160999999999,149.127419,149.123661,149.13385099999999,149.137473,149.072746,149.127656,149.085837,149.127656,149.131486,149.128049,149.00236000000001,149.136627,149.09376700000001,149.126734,149.101585,149.13167999999999,149.13251,149.12820099999999,149.13597100000001,149.134705,149.13283799999999,149.17204899999999,149.13271,149.067307,149.13531699999999,149.13009600000001,149.12164000000001,149.14398600000001,149.10874000000001,149.13797700000001,149.12790799999999,149.125666,149.078971,149.11224200000001,149.067961,149.132464,149.12780900000001,149.126983,149.12512000000001,149.131641,149.130617,149.131744,149.12639799999999,149.06970999999999,149.07490300000001,149.13342299999999,149.071979,149.13569200000001,149.08500900000001,149.13261600000001};
    final Integer[] pathJumps = {52, 61, 40, 95, 80, 104, 46, 114, 44, 8, 7, 66, 86, 36, 33, 77, 70, 41, 91, 72, 25, 83, 106, 42, 20, 118, 73, 54, 48, 59, 17, 115, 98, 55, 124, 14, 81, 107, 123, 120, 96, 111, 28, 74, 1, 31, 57, 64, 13, 27, 79, 22, 34, 15, 117, 89, 37, 53, 71, 112, 93, 69, 50, 108, 45, 122, 101, 94, 87, 90, 60, 23, 39, 92, 99, 121, 125, 5, 65, 19, 43, 10, 3, 30, 88, 49, 35, 51, 100, 21, 24, 82, 84, 103, 116, 110, 119, 2, 105, 6, 68, 67, 76, 38, 29, 56, 62, 26, 47, 113, 63, 9, 16, 102, 109, 97, 85, 12, 18, 78, 11, 32, 0, 58, 80, 75};

    int currentFocus = -1;
    int score = 0;
    int time = 0;
    boolean firstRun = true;

    void showPic(){
        Context context = this;
        Intent intent = new Intent(context, com.example.user.printlatlong.ImageActivity.class);
        intent.putExtra("pastFocusID", Integer.toString(currentFocus));
        startActivity(intent);
    }
    void refreshTime(){
        Integer totalSecs = time;
        Integer hours = totalSecs / 3600;
        Integer minutes = (totalSecs % 3600) / 60;
        Integer seconds = totalSecs % 60;

        String timeString = Integer.toString(hours) + ":" + Integer.toString(minutes/10)+Integer.toString(minutes%10) + ":" + Integer.toString(seconds/10)+Integer.toString(seconds%10);
        TextView textView = (TextView)findViewById(R.id.scoreText);
        textView.setText("Score: "+Integer.toString(score)+"/126, Total Time: "+timeString);
    }
    private Handler handler = new Handler();
    void tickTimer(){
        handler.post(new Runnable() {
            public void run() {
                time++;
                refreshTime();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        if (firstRun){
            firstRun=false;
            String title="Welcome";
            String message="Use you navigation skills to find the next checkpoint (red) and increase your score!";
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(message)
                    .setCancelable(false)
                    .setTitle(title)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            int delay = 0; // delay for 0 sec.
                            int period = 1000; // repeat every sec.

                            Timer timer = new Timer();
                            timer.scheduleAtFixedRate(new TimerTask()
                            {
                                public void run()
                                {
                                    tickTimer();
                                }
                            }, delay, period);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        ImageView image = (ImageView)findViewById(R.id.artPhotoView);
        image.setLongClickable(true);
        image.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent ev) {
                if (ev.getAction() == MotionEvent.ACTION_DOWN)showPic();
                return true;
            }
        });
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void messageBox(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public void makeUseOfNewLocation(Location location){
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        double error = 0.015;
        boolean first = false;
        //messageBox("Title",Double.toString(latitude)+","+Double.toString(longitude));

        if (currentFocus==-1){
            double minDist = 1000000000.0;
            int minID = -1;
            for(int i=0; i<=125;i++){
                double currentDist = distance(latitude,longitude,latitudeData[i],longitudeData[i]);
                if (currentDist < minDist){
                    minDist=currentDist;
                    minID = i;
                }
            }
            currentFocus=minID;
            //currentFocus=randInt(0,125);
            //messageBox("Title",Integer.toString(minID));
            first=true;
        }
        if(distance(latitude,longitude,latitudeData[currentFocus],longitudeData[currentFocus])<error || first){
            if(!first){
                Context context = this;
                Intent intent = new Intent(context, com.example.user.printlatlong.InfoActivity.class);
                intent.putExtra("pastFocusID", Integer.toString(currentFocus));
                startActivity(intent);
                currentFocus = pathJumps[currentFocus];
                if (score==125) messageBox("Congratulations","You have visited every artwork!");
                if (score<=126) score++;
            }

            TextView textView = (TextView)findViewById(R.id.artTitleText);
            textView.setText(artNamesArray[currentFocus]);

            //textView = (TextView)findViewById(R.id.scoreText);
            //textView.setText("Score: "+Integer.toString(score)+"/126");
            refreshTime();

            GoogleMap mMap;
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            mMap.clear();
            MarkerOptions newMarker = new MarkerOptions()
                    .position(new LatLng(latitudeData[currentFocus], longitudeData[currentFocus]))
                    .title("Next Checkpoint");
            mMap.addMarker(newMarker);

            float height = (float)15.5;
            CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(newMarker.getPosition(),height);
            mMap.animateCamera(cu);

            MarkerOptions newMarker2 = new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title("Starting Position")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            mMap.addMarker(newMarker2);


            Context context = this;
            ImageView imageView = (ImageView)findViewById(R.id.artPhotoView);
            Bitmap newImage = BitmapFactory.decodeResource(context.getResources(), getResources().getIdentifier("drawable/imageid"+ Integer.toString(currentFocus), null, getPackageName()));
            newImage = getRoundedCornerBitmap(newImage,50);
            imageView.setImageBitmap(newImage);
            //imageView.setImageDrawable(getResources().getDrawable( getResources().getIdentifier("drawable/imageid"+ Integer.toString(currentFocus), null, getPackageName())));

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
