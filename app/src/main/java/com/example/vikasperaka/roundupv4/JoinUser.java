package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JoinUser extends AppCompatActivity {

    private Button send;
    private EditText nameTxt;
    private String name = "";
    DatabaseReference root;
    Long n;
    ArrayList<ArrayList<Long>> test1 = new ArrayList<ArrayList<Long>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_user);

        send = (Button)findViewById(R.id.sendResults);
        nameTxt = (EditText)findViewById(R.id.joinName);
        final String code = getIntent().getExtras().get("code").toString().trim();
        root = FirebaseDatabase.getInstance().getReference().getRoot();
        final DatabaseReference subRoot = root.child(code);
        final DatabaseReference child = subRoot.child("Enter Name");    //change because currently default setting

        subRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    //set the user name and description texts to verify if correct
                    DataSnapshot d = (DataSnapshot)i.next();
                    if (d.getKey().equals("orgCal")) {
                        test1 = (ArrayList<ArrayList<Long>>) d.getValue();
                        ArrayList<Long> in = test1.get(0);
                        n = in.get(0);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameTxt.getText().toString().trim();
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put(name, "");
                subRoot.updateChildren(map1);

                DatabaseReference user = subRoot.child(name);
                //manipulate user database
                int rand = (int)(Math.random() * 5 + 1);
                ArrayList<ArrayList<Long>> test = (ArrayList<ArrayList<Long>>) getIntent().getExtras().get("userCal");
                ArrayList<Long> inner = new ArrayList<Long>();
                inner.add((long)rand);
                inner.add((long)0);
                inner.add((long)0);
                test.set(0,inner);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("userCal", test);
                user.updateChildren(map2);
                //user.push().setValue(test); //updating user data accordingly
                // user.getKey();


                Intent i = new Intent(getApplicationContext(), Confirmation.class);
                i.putExtra("val", n);
                i.putExtra("name", name);
                i.putExtra("code", code);
                i.putExtra("list", test1);
                startActivity(i);
            }
        });
    }
}
