package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
    }
}
