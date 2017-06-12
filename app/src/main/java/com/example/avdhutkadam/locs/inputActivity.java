package com.example.avdhutkadam.locs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class inputActivity extends AppCompatActivity {

    EditText et_getLocation,et_getTask;
    Button b_submit,b_done;
    ImageButton imgb_addMore;
    TextView tv_putLatLng;
    static String location = "";
    static String task = "";
    public static ArrayList<String> location_array=new ArrayList<String>();
    public static ArrayList<String> task_array=new ArrayList<String>();
    Geocoder geocoder = null;
    static double latti_final=0.0;
    static double longi_final=0.0;
    int flag;
    public static ArrayList<Double> final_longitdeArray=new ArrayList<>();
    public static ArrayList<Double> final_lattitudeArray=new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_input);
            et_getLocation = (EditText) findViewById(R.id.editText_location);
            et_getTask = (EditText) findViewById(R.id.editText_task);
            b_submit = (Button) findViewById(R.id.button_submit);
            b_done=(Button)findViewById(R.id.button_clrNotification);
            tv_putLatLng = (TextView) findViewById(R.id.textView_putLatLng);
            imgb_addMore=(ImageButton)findViewById(R.id.imageButton_addMore);

            b_done.setVisibility(View.INVISIBLE);
            imgb_addMore.setVisibility(View.INVISIBLE);
            flag=0;
            b_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if(et_getLocation.getText().toString()=="")// || et_getTask.getText().toString()=="")
                        {
                            Toast.makeText(getApplicationContext(), "Please enter all details", Toast.LENGTH_SHORT).show();
                        }

                        location = et_getLocation.getText().toString();
                        task = et_getTask.getText().toString();

                        {

                        getGeoCoordsFromAddress(getApplicationContext(), location);
                    }
                        if (latti_final != 0.0 && longi_final != 0.0) {
                            location_array.add(location);
                            task_array.add(task);
                            latti_final=0.0;
                            longi_final=0.0;
                            b_done.setVisibility(View.VISIBLE);
                            imgb_addMore.setVisibility(View.VISIBLE);
                            flag=1;
                        } else {
                            tv_putLatLng.setText("Sorry...Try Again");
                        }
                    }catch (Exception et) {
                    }
                }
            });


            imgb_addMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    et_getLocation.setText("");
                    et_getTask.setText("");
                    tv_putLatLng.setText("Your Lat/Lng");
                }
            });

            b_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag==1) {
                        Intent int3 = new Intent(v.getContext(), myList.class);
                        startActivity(int3);
                    } else {
                        tv_putLatLng.setText("Cannot fetch lat/long");
                    }
                }
            });
        } catch (Exception e) {
        }
    }
    public void getGeoCoordsFromAddress(Context c, String address) {
        try {
          List<Address> addresses;
            try {
                geocoder = new Geocoder(c);

                addresses = geocoder.getFromLocationName(address, 1);
                //latti = null;
                //longi = null;

                if (addresses != null) {
                    latti_final = addresses.get(0).getLatitude();
                    longi_final = addresses.get(0).getLongitude();
                }

                if (latti_final > 0) {
                    tv_putLatLng.setTextColor(Color.BLUE);
                    tv_putLatLng.setText("Lattitude:-" + latti_final + "\nLongitude:-" + longi_final);

                    final_lattitudeArray.add(latti_final);
                    final_longitdeArray.add(longi_final);
                } else {
                    tv_putLatLng.setText("Cannot get long/latt");
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        } catch (Exception ex) {
        }
    }
    /*public final boolean isInternetOn(){

        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        //noinspection deprecation
        if(connectivityManager.getNetworkInfo(0).getState()== NetworkInfo.State.DISCONNECTED|| connectivityManager.getNetworkInfo(1).getState()== NetworkInfo.State.DISCONNECTED){
            tv_putLatLng.setText("Not connected to internet\nTRY AGAIN");
            tv_putLatLng.setTextColor(Color.RED);
        }
        return false;
    }*/
}