package com.example.avdhutkadam.locs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class myList extends AppCompatActivity {

    ListView lv_locations;
    static int itemPosition;
    static TextView tv;
    public static String itemValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my_list);
            lv_locations = (ListView) findViewById(R.id.listView_locations);

            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listview, R.id.textView6, inputActivity.location_array);
            lv_locations.setAdapter(adapter);

            lv_locations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    itemPosition = position;
                    itemValue = (String) lv_locations.getItemAtPosition(position).toString();
                    Toast.makeText(getApplicationContext(), "Position-" + itemPosition + "\nValue-" + itemValue, Toast.LENGTH_LONG).show();
                    Intent int4=new Intent(view.getContext(),currentLocationActivity.class);
                    startActivity(int4);
                }
            });
        } catch (Exception e) {
        }
    }
}
