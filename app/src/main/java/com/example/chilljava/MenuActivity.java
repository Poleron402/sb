package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.Menu;

public class MenuActivity extends AppCompatActivity {
    private Button homeButton;
    private ChillJavaDAO mChillJavaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        homeButton = findViewById(R.id.homebutton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentFactory();
            }
        });

    }
    private void baseItems(){
        wireUpDB();
        if(mChillJavaDAO.getAllItems().size() < 0) {
            Menu newItem = new Menu("Dark Roast", 0, true, 1.50);
            Menu newItem1 = new Menu("Medium Roast", 0, true, 1.50);
            Menu newItem2 = new Menu("Latte", 2, true, 1.50);
            Menu newItem3 = new Menu("Cappuccino", 0, true, 1.50);
            mChillJavaDAO.insert(
                    newItem, newItem1, newItem2, newItem3
            );
        }

    }
    private void intentFactory(){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }


    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();
    }
}