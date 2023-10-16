package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {

    EditText et_j_firstname;
    EditText et_j_lastname;
    Button btn_j_back;
    Button btn_j_update;

    Intent mainActvity;
     User userpassed;

     DatabaseHelper dbHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);



        et_j_firstname = findViewById(R.id.et_update_firstName);
        et_j_lastname = findViewById(R.id.et_update_lastName);
        btn_j_back = findViewById(R.id.btn_update_v_back);
        btn_j_update = findViewById(R.id.btn_v_update_updateuserInfo);

        mainActvity = new Intent (Update.this, MainActivity.class);

        Intent cameFrom = getIntent();

        dbHelper = new DatabaseHelper(this);

        userpassed = (User) cameFrom.getSerializableExtra("User");

        et_j_firstname.setText(userpassed.getFname());
        et_j_lastname.setText(userpassed.getLname());


        updateButtonEvent();
        backButtonEvent();
    }


    public void updateButtonEvent()
    {

        btn_j_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change info bout user passed

                userpassed.setFname(et_j_firstname.getText().toString());
                userpassed.setLname(et_j_lastname.getText().toString());

                //update user
                dbHelper.updateUser(userpassed);
                //start activity
                startActivity(mainActvity);


            }
        });
    }
    public void backButtonEvent()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}