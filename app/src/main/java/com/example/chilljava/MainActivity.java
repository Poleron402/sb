package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int userId = -1;
    private User user;
    private TextView mUserDisplay;
    private Button madminButton;
    private Button mLogoutButton;
    private Button menuButton;
    private static final String USER_ID_KEY = "com.example.chilljava.userIdKey";
    private static final String PREFENCES_KEY = "com.example.chilljava.PREFENCES_KEY";
    private ChillJavaDAO mChillJavaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        madminButton = findViewById(R.id.adminButton);
        mUserDisplay = findViewById(R.id.nameDisplay);
        mLogoutButton = findViewById(R.id.logoutButton);
        menuButton = findViewById(R.id.menuButton);
        wireUpDB();
        SharedPreferences preferences = getSharedPreferences(PREFENCES_KEY, MODE_PRIVATE);
        userId = preferences.getInt(USER_ID_KEY, -1);


        if (userId != -1){
            user = mChillJavaDAO.getUserById(userId);
            Toast.makeText(this, "User ID: " + userId+" "+user.getIsadmin(), Toast.LENGTH_SHORT).show();
            mUserDisplay.setText(user.getUsername());
            if(user.getIsadmin().equals("true")){
                madminButton.setVisibility(View.VISIBLE);
            }else{
                madminButton.setVisibility(View.GONE);
            }
        }else{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra(USER_ID_KEY, userId);
            startActivity(intent);
            finish();
        }
        List<User> users = mChillJavaDAO.getAllUser();
        if(users.size() == 0){
            User defaultUser = new User("testuser1", "testuser1", 0, "false");
            mChillJavaDAO.insert(defaultUser);
            User admin = new User("admin2", "admin2", 10, "true");
            mChillJavaDAO.insert(admin);
        }
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences(PREFENCES_KEY,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                finish();
                userId = -1;
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();


    }
}