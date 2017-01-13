package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.roundupv4.R;

import static com.example.android.roundupv4.R.id.results;

public class MainActivity extends AppCompatActivity {

    private Button create;
    private Button join;
    private Button result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = (Button)findViewById(R.id.createRoundUp);
        join = (Button)findViewById(R.id.joinRoundUp);
        result = (Button)findViewById(results);

        //options to create, join, and see results
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.vikasperaka.roundupv4.CreateEvent.class));
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.vikasperaka.roundupv4.JoinEvent.class));
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.vikasperaka.roundupv4.Results.class));
            }
        });
    }
}