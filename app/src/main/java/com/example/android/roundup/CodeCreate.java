package com.example.vikasperaka.roundupv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CodeCreate extends AppCompatActivity {

    private Button join;
    private TextView codetxtView;
    private EditText number;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_create);
        join = (Button) findViewById(R.id.join);
        codetxtView = (TextView) findViewById(R.id.codeDisplay);
        number = (EditText) findViewById(R.id.phone);
        code = getIntent().getExtras().get("code").toString().trim();
        codetxtView.setText(code);  //shows user the code for the created event
        RoundUpCalendar r = (RoundUpCalendar)getIntent().getSerializableExtra("cal");

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //maybe add sharing capabilities
                //sendTextMessageReminder();
                Intent i = new Intent(getApplicationContext(), JoinEvent.class);

                startActivity(i);
            }
        });
    }

    public void sendTextMessageReminder() {
        String phoneNumber = number.getText().toString();
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(phoneNumber, null, code, null, null);
    }

}
