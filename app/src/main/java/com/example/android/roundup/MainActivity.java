package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.vikasperaka.roundupv4.R.id.results;

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
                startActivity(new Intent(getApplicationContext(), CreateEvent.class));
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), JoinEvent.class));
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Results.class));
            }
        });
    }
}
