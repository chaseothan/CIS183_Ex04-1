package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity
{
    Button btn_back;
    Button btn_update;
    EditText et_fname;
    EditText et_lname;
    Intent mainActivity;
    User userPassed;

    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btn_back = (Button) findViewById(R.id.btn_v_back);
        btn_update = (Button) findViewById(R.id.btn_v_update);
        et_fname = (EditText) findViewById(R.id.et_v_firstname);
        et_lname = (EditText) findViewById(R.id.et_v_lastname);


        mainActivity = new Intent(Update.this, MainActivity.class);

        Intent cameFrom = getIntent();

        userPassed = (User) cameFrom.getSerializableExtra("User");

        et_fname.setText(userPassed.getFname());
        et_lname.setText(userPassed.getLname());

        dbHelper = new DatabaseHelper(this);

        updateButtonEvent();
        backButtonEvent();
    }

    public void updateButtonEvent()
    {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //change the information about the user that was passed.
                userPassed.setFname(et_fname.getText().toString());
                userPassed.setLname(et_lname.getText().toString());

                //pass new updated user to update
                dbHelper.updateUser(userPassed);
                //start main activity
                startActivity(mainActivity);
            }
        });
    }

    public void backButtonEvent()
    {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mainActivity);
            }
        });
    }
}