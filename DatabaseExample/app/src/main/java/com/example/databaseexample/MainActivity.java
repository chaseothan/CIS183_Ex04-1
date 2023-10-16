package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    EditText et_j_fname;
    EditText et_j_lname;
    EditText et_j_uname;
    Button btn_j_addUser;
    ListView lv_j_users;



    Intent updateIntent;

    ArrayList<User> userList;

    DatabaseHelper dbHelper;

    ArrayList<String> usernames;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_j_fname = findViewById(R.id.et_v_fname);
        et_j_lname = findViewById(R.id.et_v_lname);
        et_j_uname = findViewById(R.id.et_v_uname);
        btn_j_addUser = findViewById(R.id.btn_v_addUser);
        lv_j_users = findViewById(R.id.lv_v_users);

        userList = new ArrayList<User>();
        //make an instance of the databaseHelper and pass it this
        dbHelper = new DatabaseHelper(this);
        //call the initializeDB() function to fill the records into our table
        dbHelper.initializeDB();
        //test to make sure the records were inserted
        //we should see 4 when we run this
        Log.d("Number of records: ", dbHelper.numberOfRowsInTable() + "");
        userList = dbHelper.getAllRows();

        //for testing purposes only
        //displayUsers();

        //get all of the usernames from our table
        usernames = dbHelper.getAllUsernames();

        //Remember that this is a simple menu.  Meaning that you can only display one string per cell
        //If you want to display multiple things in a cell you need to make a custom adapter and custom cell.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usernames);

        //tell the listview to use the adapter
        lv_j_users.setAdapter(adapter);

        updateIntent = new Intent(MainActivity.this, Update.class);

        addNewUserButtonEvent();
        deleteUserEvent();
        updateUser();
    }

    public void addNewUserButtonEvent() {
        btn_j_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button Press", "add user!!");

                String u = et_j_uname.getText().toString();
                String f = et_j_fname.getText().toString();
                String l = et_j_lname.getText().toString();

                User user = new User(u, f, l);

                //adding the user to the database and the arraylist
                addNewUser(user);

                //add the username to the username arraylist
                usernames.add(u);

                //this line is easily forgotten.  You need this line so the listview will reflect the new
                //user based off the new username added to the usernames arraylist.
                adapter.notifyDataSetChanged();

                //this is for testing only
                //displayUsers();



                //clear textboxes
                et_j_uname.setText("");
                et_j_fname.setText("");
                et_j_lname.setText("");

                //add to database
                //save to an array list
                //display in listview
            }
        });
    }

    public void addNewUser(User u)
    {
        //add user to the arraylist
        userList.add(u);
        //add user to the database.
        dbHelper.addNewUser(u);
    }
    public void displayUsers()
    {
        for(int i = 0; i < userList.size(); i++)
        {
            Log.d("User: ", userList.get(i).getFname());

        }
    }

    public void deleteUserEvent()
    {
        lv_j_users.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                //call the delete function in our dbHelpter and pass it username
                dbHelper.deleteUser(usernames.get(i));
                //remove the user from our userlist
                userList.remove(i);
                //remove the suer from our usernames
                usernames.remove(i);
                //update the listview to see the changes
                adapter.notifyDataSetChanged();

                return false;
            }
        });
    }
    public void updateUser()
    {
        lv_j_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                updateIntent.putExtra("User",userList.get(i));
                Log.d("--info--", userList.get(i).getFname());
                Log.d("user", usernames.get(i));
                startActivity(updateIntent);
            }
        });

    }

}

