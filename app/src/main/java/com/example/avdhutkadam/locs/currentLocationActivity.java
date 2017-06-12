package com.example.avdhutkadam.locs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;

import static android.location.LocationManager.GPS_PROVIDER;

public class currentLocationActivity extends AppCompatActivity implements LocationListener{
    NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this);

    private static final int NOTIFY_ME_ID=1337;
    private final int INTERVAL=20000;
    public static boolean fiveSecFlag=true;
    public static Handler handler=new Handler();
    protected LocationManager locationManager;
    TextView txtLatLong,tv_list;
    ListView lv_locationTask;
    ImageButton b;
    double diff;
    Button button;
    static currentLocationActivity activityObject;
    public static double latti_current,longi_current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_current_location);


            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            locationManager.requestLocationUpdates(GPS_PROVIDER, 0, 0, this);
        } catch (Exception er) {
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        try {
            activityObject=this;

            txtLatLong = (TextView) findViewById(R.id.txt_currentlocation);
            latti_current = location.getLatitude();
            longi_current = location.getLongitude();
            txtLatLong.setTextColor(Color.BLUE);
            txtLatLong.setText("Latitude:" + latti_current + ", \nLongitude:" + longi_current);

            b = (ImageButton) findViewById(R.id.button_setAlarm);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "Alarm has been set", Toast.LENGTH_SHORT).show();

                    afterFiveSec();

                }
            });

        } catch (Exception ex) {
        }
    }

    public static currentLocationActivity getActivity()
    {
        return activityObject;
    }

    private double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double dist = earthRadius * c;

        Toast.makeText(getApplicationContext(),"Distance is:"+dist,Toast.LENGTH_SHORT).show();
        diff=dist;

        return dist; // output distance, in MILES

    }


    public void giveNotification() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {0, 2000, 2000};
            final NotificationManager mgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder.setAutoCancel(true);
            mBuilder.setSmallIcon(R.drawable.my_ic_launcher);
            mBuilder.setContentTitle("Location Alarm");
            mBuilder.setContentText("You reached at " + inputActivity.location_array.get(myList.itemPosition));
            mBuilder.setSound(notification);
            mBuilder.setVibrate(pattern);

            Intent intent=new Intent(this,NotificationView.class);
            TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
            stackBuilder.addParentStack(NotificationView.class);
            stackBuilder.addNextIntent(intent);
            PendingIntent pendingIntent=stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendingIntent);


            @SuppressWarnings("deprecation")
            Notification note = new Notification(R.drawable.mybigic_launcher_web, "You reached at your destination", System.currentTimeMillis());

            mgr.notify(NOTIFY_ME_ID, mBuilder.build());


        } catch (Exception el) {
        }
    }

    public void afterFiveSec() {
        try {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (distance(inputActivity.final_lattitudeArray.get(myList.itemPosition), inputActivity.final_longitdeArray.get(myList.itemPosition), latti_current, longi_current) < 0.2) {
                            giveNotification();
                        }
                        handler.postDelayed(this, INTERVAL);
                    } catch (Exception s) {
                    }
                }
            }, 1000);
        } catch (Exception e1) {
        }
    }

    public static void stopDelay(){
        try {
            handler.removeCallbacksAndMessages(null);
        }
        catch (Exception m){}

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }
}