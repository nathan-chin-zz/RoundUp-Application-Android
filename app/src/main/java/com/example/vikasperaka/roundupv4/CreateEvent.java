package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEvent extends AppCompatActivity {

    private Button next;
    private EditText nameTxt;
    private EditText descriptionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        next = (Button)findViewById(R.id.nextButton3);
        nameTxt = (EditText)findViewById(R.id.eventName);
        descriptionTxt = (EditText)findViewById(R.id.eventDescription);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateCalendar.class);
                //Gets user inputed name and description of event
                i.putExtra("name", nameTxt.getText().toString().trim());
                i.putExtra("description", descriptionTxt.getText().toString().trim());
                startActivity(i);
            }
        });
    }
}
