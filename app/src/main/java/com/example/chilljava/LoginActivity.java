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

public class LoginActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private Button mButton;
    private User myUser;
    private ChillJavaDAO mChillJavaDAO;
    private String un;
    private String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        wireUpDB();
        setUpDisplay();
    }

    private void setUpDisplay(){
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mButton = findViewById(R.id.loginbtn);
        //storing values from display in strings

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                un = mUsername.getText().toString();
                pw = mPassword.getText().toString();
                myUser = mChillJavaDAO.getUserByUsername(un);
                if(un.equals("") || pw.equals("") || myUser == null || !myUser.getPassword().equals(pw)){
                    Toast.makeText(LoginActivity.this, "One or two required fields missing!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user_id", myUser.getUserId());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();
    }

}