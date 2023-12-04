package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int userId = -1;
    private User user;
    private Button madminButton;
    private static final String USER_ID_KEY = "com.example.chilljava.userIdKey";
    private ChillJavaDAO mChillJavaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        madminButton = findViewById(R.id.adminButton);
        wireUpDB();

        userId = getIntent().getIntExtra("user_id", userId);
        user = mChillJavaDAO.getUserById(userId);

        if (userId != -1){
            Toast.makeText(this, "User ID: " + userId+" "+user.getIsadmin(), Toast.LENGTH_SHORT).show();

            if(user.getIsadmin().equals("true")){
                madminButton.setVisibility(View.VISIBLE);
            }else{
                madminButton.setVisibility(View.GONE);
            }
        }else{
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra(USER_ID_KEY, userId);
            startActivity(intent);
            Toast.makeText(this, "User ID not provided", Toast.LENGTH_SHORT).show();
            finish();
        }
        List<User> users = mChillJavaDAO.getAllUser();
        if(users.size() == 0){
            User defaultUser = new User("testuser1", "testuser1", 0, "false");
            mChillJavaDAO.insert(defaultUser);
            User admin = new User("admin2", "admin2", 10, "true");
            mChillJavaDAO.insert(admin);
        }

    }

    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();


    }
}