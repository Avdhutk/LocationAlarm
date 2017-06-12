package com.example.avdhutkadam.locs;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationView extends AppCompatActivity {
    Button b_clrNotification,b_stopNotify;
    TextView tv_setAnotherAlarm;
    TextView tv_putReachedLocation, tv_putReachedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_notification_view);

            final NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(1337);
            b_clrNotification = (Button) findViewById(R.id.button_clrNotification);



            //vibrator2.cancel();

            tv_putReachedLocation = (TextView) findViewById(R.id.textView_putReachedLocation);
            tv_putReachedTask = (TextView) findViewById(R.id.textView_putReachedTask);
            b_stopNotify = (Button) findViewById(R.id.button_stopNotification);
            tv_setAnotherAlarm = (TextView) findViewById(R.id.textView_setAnotherAlarm);

            tv_putReachedLocation.setText(myList.itemValue);
            tv_putReachedTask.setText(inputActivity.task_array.get(myList.itemPosition));
            b_clrNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        inputActivity.location_array.remove(myList.itemPosition);
                        inputActivity.task_array.remove(myList.itemPosition);
                        inputActivity.final_lattitudeArray.remove(myList.itemPosition);
                        inputActivity.final_longitdeArray.remove(myList.itemPosition);
                    } catch (Exception e) {
                    }
                }
            });
            tv_setAnotherAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent int5 = new Intent(v.getContext(), myList.class);
                    startActivity(int5);

                }
            });

            b_stopNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    currentLocationActivity.stopDelay();
                    notificationManager.cancel(1337);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        } catch (Exception ex) {
        }
    }

   /* protected void onResume() {
        try {
            super.onResume();
        } catch (Exception e) {
        }
    }
    public void stopNotification()
    {
        currentLocationActivity.getActivity().finish();
    }*/

}