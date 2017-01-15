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

        /*Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("M/d/yyyy");
        String strDate = "Current Date : " + mdformat.format(calendar.getTime());
        TextView view = (TextView) findViewById(R.id.date);
        view.setText(strDate);*/

        Month first = new Month();
        ArrayList<Dates> dates = first.getDays();
        Month second = new Month(1);
        ArrayList<Dates> dates2 = second.getDays();
        Month third = new Month(2);
        ArrayList<Dates> dates3 = third.getDays();

        for(int k = 0; k < dates2.size(); k++){
            dates.add(dates2.get(k));
        }
        for(int j = 0; j < dates3.size(); j++){
            dates.add(dates3.get(j));
        }


        final ArrayList<Dates> copy = dates;

        final CalendarAdapter adapter = new CalendarAdapter(this, dates);
        GridView gridView = (GridView) findViewById(R.id.calendar);
        gridView.setAdapter(adapter);

        final ArrayList<Integer> selected = new ArrayList<>();

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
