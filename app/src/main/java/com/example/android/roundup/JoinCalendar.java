package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JoinCalendar extends AppCompatActivity {

    private Button next;
    private TextView nametxt;
    private TextView descriptiontxt;
    private TextView codetxt;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_calendar);

        next = (Button)findViewById(R.id.nextButton2);
        codetxt = (TextView)findViewById(R.id.calCode);
        nametxt = (TextView)findViewById(R.id.calName);
        descriptiontxt = (TextView)findViewById(R.id.calDescription);
        final String code = getIntent().getExtras().get("code").toString(); //get code
        //String userName = getIntent().getExtras().get("name").toString();
       // String description = getIntent().getExtras().get("description").toString();

        DatabaseReference event = root.child(code);
        codetxt.setText(code);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("", "");
        event.updateChildren(map1); //trick to call value event listener

        //update accordingly to user clicks
        final ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> inner = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            inner.add(0);
        }
        list.add(inner);

        event.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                 //set the user name and description texts to verify if correct
                    DataSnapshot d = (DataSnapshot)i.next();
                    if (d.getKey().equals("name")){
                        nametxt.setText(d.getValue().toString().trim());
                    }
                    if (d.getKey().equals("description")){
                        descriptiontxt.setText(d.getValue().toString().trim());
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), JoinUser.class);
                i.putExtra("code", code);
                i.putExtra("userCal", list);
                startActivity(i);
            }
        });
    }
}
