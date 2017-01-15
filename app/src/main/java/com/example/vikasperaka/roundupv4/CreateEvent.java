package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateEvent extends AppCompatActivity {

    private Button next;
    private EditText nameTxt;
    private EditText descriptionTxt;
    private ArrayList<ArrayList<Integer>> test;

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
                /*Intent i = new Intent(getApplicationContext(), CreateCalendar.class);
                //Gets user inputed name and description of event
                i.putExtra("name", nameTxt.getText().toString().trim());
                i.putExtra("description", descriptionTxt.getText().toString().trim());
                startActivity(i);*/

                EditText name = (EditText) findViewById(R.id.eventName);
                String inputName = name.getText().toString();
                EditText description = (EditText) findViewById(R.id.eventDescription);
                String inputDesc = description.getText().toString();
                if((inputName.length() == 0) && (inputDesc.length() == 0)){
                    Toast.makeText(getApplicationContext(), R.string.error_name_and_description, Toast.LENGTH_SHORT).show();
                }
                else if(inputName.length() == 0){
                    Toast.makeText(getApplicationContext(), R.string.error_name, Toast.LENGTH_SHORT).show();
                }
                else if(inputDesc.length() == 0){
                    Toast.makeText(getApplicationContext(), R.string.error_description, Toast.LENGTH_SHORT).show();
                }
                RoundUpCalendar make = new RoundUpCalendar(inputName, inputDesc, test);
                final String stri = inputDesc;
                if((inputName.length() != 0) && (inputDesc.length() != 0)) {
                    Intent nextIntent = new Intent(getApplicationContext(), CreateCalendar.class);
                    nextIntent.putExtra("description", stri);
                    nextIntent.putExtra("RoundUp", make);
                    nextIntent.putExtra("name", inputName);
                    nextIntent.putExtra("description", inputDesc);
                    startActivity(nextIntent);
                }
            }
        });
    }
}
