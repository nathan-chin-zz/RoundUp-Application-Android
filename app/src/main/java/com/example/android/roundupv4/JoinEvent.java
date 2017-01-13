package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinEvent extends AppCompatActivity {

    private Button next;
    private EditText codeTxt;
    private String codeEntered;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);

        next = (Button)findViewById(R.id.nextButton1);
        codeTxt = (EditText)findViewById(R.id.joinCode);

        //Check if code entered is valid code

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), JoinCalendar.class);
                codeEntered = codeTxt.getText().toString().trim();
                i.putExtra("code", codeEntered);

                startActivity(i);
            }
        });
    }
}
