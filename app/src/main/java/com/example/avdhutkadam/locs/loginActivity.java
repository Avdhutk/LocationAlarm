package com.example.avdhutkadam.locs;

import android.content.Intent;
import android.graphics.Color;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    Button b1,b2,switch2;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        switch2 = (Button) findViewById(R.id.auth);
        tx1 = (TextView) findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);
        try {
            switch2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ed1.getText().toString().equals("user") && ed2.getText().toString().equals("user")) {
                        Intent int2 = new Intent(v.getContext(), inputActivity.class);
                        startActivity(int2);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                        tx1.setVisibility(View.VISIBLE);
                        tx1.setBackgroundColor(Color.RED);
                        counter--;
                        tx1.setText(Integer.toString(counter));

                        if (counter == 0) {
                            Toast.makeText(getApplicationContext(), "SORRY..You reached login limit", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                }
            });
        } catch (Exception exx) {
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

