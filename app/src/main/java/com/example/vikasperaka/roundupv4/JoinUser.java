package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.roundupv4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinUser extends AppCompatActivity {

    private Button send;
    private EditText nameTxt;
    private String name = "";
    DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_user);

        send = (Button)findViewById(R.id.sendResults);
        nameTxt = (EditText)findViewById(R.id.joinName);
        final String code = getIntent().getExtras().get("code").toString().trim();
        root = FirebaseDatabase.getInstance().getReference().getRoot();
        final DatabaseReference subRoot = root.child(code);




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
                ArrayList<ArrayList<Integer>> test = (ArrayList<ArrayList<Integer>>) getIntent().getExtras().get("userCal");
                ArrayList<Integer> inner = new ArrayList<Integer>();
                inner.add(rand);
                inner.add(0);
                inner.add(0);
                test.set(0,inner);
                user.push().setValue(test); //updating user data accordingly

                //try to use transaction to count number of clicks
                subRoot.runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        return null;
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                    }
                });

                Intent i = new Intent(getApplicationContext(), Confirmation.class);
                i.putExtra("name", name);
                i.putExtra("code", code);
                startActivity(i);
            }
        });
    }
}
