package com.example.phonedialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PhoneDialerActivity extends AppCompatActivity {

    private Button dialbutton;

    private ImageButton callButton;

    private ImageButton contactsButton;

    private ImageButton hangButton;

    private ImageButton backspaceButton;

    private EditText textField;


    private ButtonListener listener = new ButtonListener();

    public class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.backspace_button:
                    if (textField.getText().toString().length() > 0) {
                        textField.setText(textField.getText().toString().substring(0, textField.getText().toString().length() - 1));
                    }
                    break;
                case R.id.hang_button:
                    finish();
                    break;
                case R.id.contacts_button:
                    String phoneNumber = textField.getText().toString();
                    if (phoneNumber.length() > 0) {
                        Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
                        intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                        startActivityForResult(intent, 2022);
                    } else {
                        Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
                    }
                case R.id.call_button:
                    if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                                PhoneDialerActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                1);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + textField.getText().toString()));
                        startActivity(intent);
                    }
                    break;
                default:
                    textField.setText(textField.getText().toString() + ((Button)view).getText().toString());
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        dialbutton = (Button) findViewById(R.id.number_0_button);
        dialbutton.setOnClickListener(listener);
        dialbutton = (Button) findViewById(R.id.number_1_button);
        dialbutton.setOnClickListener(listener);
        dialbutton = (Button) findViewById(R.id.number_2_button);
        dialbutton.setOnClickListener(listener);
        dialbutton = (Button) findViewById(R.id.number_3_button);
        dialbutton.setOnClickListener(listener);
        dialbutton = (Button) findViewById(R.id.number_4_button);
        dialbutton.setOnClickListener(listener);
        dialbutton = (Button) findViewById(R.id.number_5_button);
        dialbutton.setOnClickListener(listener);
        dialbutton = (Button) findViewById(R.id.number_6_button);
        dialbutton.setOnClickListener(listener);
        dialbutton = (Button) findViewById(R.id.number_7_button);
        dialbutton.setOnClickListener(listener);
        dialbutton = (Button) findViewById(R.id.number_8_button);
        dialbutton.setOnClickListener(listener);
        dialbutton = (Button) findViewById(R.id.number_9_button);
        dialbutton.setOnClickListener(listener);

        callButton = (ImageButton) findViewById(R.id.call_button);
        callButton.setOnClickListener(listener);


        contactsButton = (ImageButton) findViewById(R.id.contacts_button);
        contactsButton.setOnClickListener(listener);
        hangButton = (ImageButton) findViewById(R.id.hang_button);
        hangButton.setOnClickListener(listener);
        backspaceButton = (ImageButton) findViewById(R.id.backspace_button);
        backspaceButton.setOnClickListener(listener);
        textField = (EditText) findViewById(R.id.field_area);




    }
}