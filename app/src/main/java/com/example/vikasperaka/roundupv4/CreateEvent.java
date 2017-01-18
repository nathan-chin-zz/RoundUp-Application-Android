package com.example.vikasperaka.roundupv4;

// Import statements
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * The CreateEvent class has the code for the screen when the user is creating an event. The user inputs an event name and
 * description and then continues
 * @author created by Vikas Peraka but written by Nathan Chin
 * @date 1/09/2017
 */
public class CreateEvent extends AppCompatActivity {

    // Instance variables
    private Button next;
    private EditText nameTxt;
    private EditText descriptionTxt;
    private ArrayList<ArrayList<Integer>> test;

    /**
     * The onCreate method describes what happens when the class is called.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Assign Views in the xml file to variables in code
        next = (Button)findViewById(R.id.nextButton3);
        nameTxt = (EditText)findViewById(R.id.eventName);
        descriptionTxt = (EditText)findViewById(R.id.eventDescription);

        // Sets an onClickListener on the next button to perform actions when clicked
        next.setOnClickListener(new View.OnClickListener() {
            /**
             * The onClick method performs actions when clicked
             * @param view the View that is clicked
             */
            @Override
            public void onClick(View view) {
                /* Vikas's code that was commented out to replace with Nathan's
                Intent i = new Intent(getApplicationContext(), CreateCalendar.class);
                //Gets user inputed name and description of event
                i.putExtra("name", nameTxt.getText().toString().trim());
                i.putExtra("description", descriptionTxt.getText().toString().trim());
                startActivity(i);
                */

                // Grabbing the text input by the users and storing the data into String variables
                EditText name = (EditText) findViewById(R.id.eventName);
                String inputName = name.getText().toString();
                EditText description = (EditText) findViewById(R.id.eventDescription);
                String inputDesc = description.getText().toString();

                // If no name or description are input, a Toast message tells the user to write them
                if((inputName.length() == 0) && (inputDesc.length() == 0)){
                    Toast.makeText(getApplicationContext(), R.string.error_name_and_description, Toast.LENGTH_SHORT).show();
                }
                // Else if no name is input, a Toast message tells the user to write one
                else if(inputName.length() == 0){
                    Toast.makeText(getApplicationContext(), R.string.error_name, Toast.LENGTH_SHORT).show();
                }
                // Else if no description is input, a Toast message tells the user to write one
                else if(inputDesc.length() == 0){
                    Toast.makeText(getApplicationContext(), R.string.error_description, Toast.LENGTH_SHORT).show();
                }
                RoundUpCalendar make = new RoundUpCalendar(inputName, inputDesc, test);
                final String stri = inputDesc;

                // Send an intent only if there is text in both the name and description fields
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
