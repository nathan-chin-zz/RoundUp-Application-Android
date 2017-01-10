package com.example.android.roundup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nathan on 1/9/2017.
 */

public class CalendarScreen extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);

        Intent intent = getIntent();
        String description = intent.getStringExtra("description");
        TextView message = (TextView) findViewById(R.id.write);
        message.setText(description);
        RoundUpCalendar make = (RoundUpCalendar)intent.getSerializableExtra("RoundUp");
        TextView unique = (TextView) findViewById(R.id.code);
        unique.setText(make.getUniqueCode());
    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        Toast.makeText(this, R.string.no_back, Toast.LENGTH_SHORT).show();
    }

    public void setDate(){

    }


}
