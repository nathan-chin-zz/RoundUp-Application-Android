package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nathan on 1/9/2017.
 */

public class CalendarScreen extends AppCompatActivity{

    int numDays = 0;
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
        //unique.setText(make.getUniqueCode());

        ArrayList<Dates> dates = new ArrayList<>();
        dates.add(new Dates(1));
        dates.add(new Dates(2));
        dates.add(new Dates(3));
        dates.add(new Dates(4));
        dates.add(new Dates(5));
        dates.add(new Dates(6));
        dates.add(new Dates(7));
        dates.add(new Dates(8));
        dates.add(new Dates(9));
        dates.add(new Dates(10));
        dates.add(new Dates(11));
        dates.add(new Dates(12));
        dates.add(new Dates(13));
        dates.add(new Dates(14));
        dates.add(new Dates(15));
        dates.add(new Dates(16));
        dates.add(new Dates(17));
        dates.add(new Dates(18));
        dates.add(new Dates(19));
        dates.add(new Dates(20));
        dates.add(new Dates(21));
        dates.add(new Dates(22));
        dates.add(new Dates(23));
        dates.add(new Dates(24));
        dates.add(new Dates(25));
        dates.add(new Dates(26));
        dates.add(new Dates(27));
        dates.add(new Dates(28));
        dates.add(new Dates(29));
        dates.add(new Dates(30));
        dates.add(new Dates(31));
        final ArrayList<Dates> copy = dates;

        final ArrayList<Integer> selected = new ArrayList<>();

        final CalendarAdapter adapter = new CalendarAdapter(this, dates);
        GridView gridView = (GridView) findViewById(R.id.calendar);
        gridView.setAdapter(adapter);

        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                numDays = 0;
                selected.clear();
                TextView num = (TextView) findViewById(R.id.num_selected_days);
                TextView sel = (TextView) findViewById(R.id.selected_days);
                for(int k = 0; k < copy.size(); k++){
                    if(copy.get(k).isClicked() == true){
                        numDays = numDays + 1;
                        selected.add(copy.get(k).getDay());
                        //numDays = adapter.getNumDays();
                    }
                }
                num.setText("" + numDays);
                sel.setText("" + selected);
            }
        });


    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        Toast.makeText(this, R.string.no_back, Toast.LENGTH_SHORT).show();
    }





}
