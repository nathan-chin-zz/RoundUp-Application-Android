package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateCalendar extends AppCompatActivity {

    private Button next;
    private DatabaseReference root;
    private String tempKey;
    private ArrayList<ArrayList<Integer>> test;

    int numDays = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_calendar);

        next = (Button)findViewById(R.id.nextButton4);
        root = FirebaseDatabase.getInstance().getReference();

        //Add calendar data here
        ArrayList<Integer> inner = new ArrayList<>();
        inner.add(0);
        inner.add(0);
        inner.add(0);
        test = new ArrayList<>();
        test.add(inner);


        final String name = getIntent().getExtras().get("name").toString();
        final String description = getIntent().getExtras().get("description").toString();
        final RoundUpCalendar roundUpCalendar =  new RoundUpCalendar(name, description, test);
        final String CODE = roundUpCalendar.getUniqueCode();

        //Adds unique event code to database
        Map<String, Object> map = new HashMap<String, Object>();
        tempKey = CODE;
        root.updateChildren(map);

        //figure out how to clear database to prevent data overload...
        //Adds event name, description, and original calendar to event base on given code
        DatabaseReference subRoot = root.child(tempKey);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", name);
        map2.put("description", description);
        map2.put("orgCal", test);
        subRoot.updateChildren(map2);

        setTitle("Event: " + name);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CodeCreate.class);
                i.putExtra("code", CODE);

                i.putExtra("cal", roundUpCalendar);
                //i.putExtra("name", name);
                //i.putExtra("description", description);
                startActivity(i);
            }
        });

        Intent intent = getIntent();
        String description_edit = intent.getStringExtra("description");
        TextView message = (TextView) findViewById(R.id.write);
        message.setText(description_edit);
        /*TextView unique = (TextView) findViewById(R.id.code);
        unique.setText(roundUpCalendar.getUniqueCode());*/


        Month first = new Month();
        ArrayList<Dates> dates = first.getDays();
        Month second = new Month(1);
        ArrayList<Dates> dates2 = second.getDays();
        Month third = new Month(2);
        ArrayList<Dates> dates3 = third.getDays();

        TextView months = (TextView) findViewById(R.id.display_months);
        months.setText(first.getMonthName() + " - " + second.getMonthName() + " - " + third.getMonthName());

        for(int k = 0; k < dates2.size(); k++){
            dates.add(dates2.get(k));
        }
        for(int j = 0; j < dates3.size(); j++){
            dates.add(dates3.get(j));
        }




        final ArrayList<Dates> copy = dates;

        final CalendarAdapter adapter = new CalendarAdapter(this, dates);
        final GridView gridView = (GridView) findViewById(R.id.calendar);
        gridView.setAdapter(adapter);



        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ArrayList<String> selected = new ArrayList<>();
                numDays = 0;
                selected.clear();
                TextView num = (TextView) findViewById(R.id.num_selected_days);
                TextView sel = (TextView) findViewById(R.id.selected_days);
                for(int k = 0; k < copy.size(); k++){
                    if(copy.get(k).isClicked() == true){
                        numDays = numDays + 1;
                        selected.add(copy.get(k).getDayAndMonth());
                        //numDays = adapter.getNumDays();
                    }
                }
                num.setText("" + numDays);
                if(selected.size() == 0){
                    sel.setText("none");
                }
                else{
                    sel.setText("" + selected);
                }
            }
        });
    }
}
