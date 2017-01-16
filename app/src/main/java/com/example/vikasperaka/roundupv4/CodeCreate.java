package com.example.vikasperaka.roundupv4;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        code = getIntent().getExtras().get("code").toString().trim();
        codetxtView.setText(code);  //shows user the code for the created event
        RoundUpCalendar r = (RoundUpCalendar) getIntent().getSerializableExtra("cal");

        codetxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Code", code);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(CodeCreate.this, "Code has been copied", Toast.LENGTH_SHORT).show();
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder message = new AlertDialog.Builder(CodeCreate.this);
                message.setMessage("Once the RoundUp has been created, the settings CANNOT be changed")
                        .setCancelable(false)
                        .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), JoinEvent.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Hold on", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = message.create();
                alert.setTitle("WARNING");
                alert.show();
            }
        });
    }

        /*join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //maybe add sharing capabilities
                //sendTextMessageReminder();
                AlertDialog .Builder warning = new AlertDialog.Builder(this);
                warning.setMessage("I hope you have copied/memorized THIS CODE --> " + code + "\n If not you want might " +
                        "want to now").create();

                Intent i = new Intent(getApplicationContext(), JoinEvent.class);

                startActivity(i);
            }
        });*/

    public void sendTextMessageReminder() {
        String phoneNumber = number.getText().toString();
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(phoneNumber, null, code, null, null);
    }
}
