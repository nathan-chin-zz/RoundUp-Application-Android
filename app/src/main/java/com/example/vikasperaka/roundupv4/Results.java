package com.example.vikasperaka.roundupv4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Results extends AppCompatActivity {

    private EditText resultCode;
    private TextView resultText;
    private Button send;
    private int r;
    private int c;
    private int e;
    Long max = 0L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultCode = (EditText)findViewById(R.id.resultCode);
        resultText = (TextView)findViewById(R.id.resulttxt);
        send = (Button)findViewById(R.id.sendCode);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().getRoot().
                        child(resultCode.getText().toString().trim());
                Map<String, Object> m = new HashMap<String, Object>();
                ref.updateChildren(m);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator i = dataSnapshot.getChildren().iterator();
                        while(i.hasNext()){
                            //set the user name and description texts to verify if correct
                            DataSnapshot d = (DataSnapshot)i.next();
                            if (d.getKey().equals("orgCal")) {
                                ArrayList<ArrayList<Long>> test = (ArrayList<ArrayList<Long>>) d.getValue();
                                int length = test.size();
                                int width = test.get(0).size();

                                resultText.setText(" ");
                                for (int row = 0; row < length; row++){
                                    for (int col = 0; col < width; col++){
                                        if (test.get(row).get(col) > max){
                                            max = test.get(row).get(col);
                                            Log.d("max", max + "");
                                            r = row;
                                            c = col;
                                            resultText.setText(max + " " + r + " " + c);
                                        }
                                    }
                                }

                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
