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

public class SigninActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private EditText mPassword2;
    private Button mButton;
    private ChillJavaDAO mChillJavaDAO;
    private Button rerouteBtn;
    private String un, pw, pw2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mUsername = findViewById(R.id.usernamesi);
        mPassword = findViewById(R.id.passwordsi);
        mPassword2 = findViewById(R.id.repeatpassword);
        mButton = findViewById(R.id.signinbtn);
        rerouteBtn = findViewById(R.id.logintransfer);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                functionality();
            }
        });
        rerouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentFactory();
            }
        });
    }
    private void functionality(){
        wireUpDB();
        un = mUsername.getText().toString();
        pw = mPassword.getText().toString();
        pw2 = mPassword2.getText().toString();

        if (pw.equals(pw2)){
            User newUser = new User(un, pw, 0, "false");
            mChillJavaDAO.insert(newUser);
            intentFactory();
        }else{
            Toast.makeText(this, "The passwords do not match" , Toast.LENGTH_SHORT).show();
        }

    }

    private void intentFactory(){
        Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();
    }
}