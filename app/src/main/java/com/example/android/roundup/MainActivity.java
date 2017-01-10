package com.example.android.roundup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button description = (Button) findViewById(R.id.next);
        description.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.name);
                String inputName = name.getText().toString();
                EditText description = (EditText) findViewById(R.id.input);
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
                RoundUpCalendar make = new RoundUpCalendar(inputName, inputDesc);
                final String stri = inputDesc;
                if((inputName.length() != 0) && (inputDesc.length() != 0)) {
                    Intent nextIntent = new Intent(MainActivity.this, CalendarScreen.class);
                    nextIntent.putExtra("description", stri);
                    nextIntent.putExtra("RoundUp", make);
                    startActivity(nextIntent);
                }
            }
        });
    }
}
