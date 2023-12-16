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
import com.example.chilljava.db.Menu;
import com.google.android.material.snackbar.Snackbar;

public class AddDrinkActivity extends AppCompatActivity {
    private EditText drinkName;
    private EditText shots;
    private EditText iced;
    private EditText price;
    private Button add;
    private Button home;
    private String dname;
    private int dshots;
    private String diced;
    private double dprice;
    private ChillJavaDAO mChillJavaDAO;
    private Boolean boolIced;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);
        drinkName = findViewById(R.id.name);
        shots = findViewById(R.id.shots);
        iced = findViewById(R.id.ice);
        price = findViewById(R.id.price);
        add = findViewById(R.id.addDrink);
        home = findViewById(R.id.goHome);
        wireUpDB();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dname = drinkName.getText().toString();
                dshots = Integer.parseInt(shots.getText().toString());
                diced = iced.getText().toString();
                dprice = Double.parseDouble(price.getText().toString());
                Menu aName = mChillJavaDAO.getAnItemByName(dname);

                if(!dname.isEmpty() && dshots>=0 &&
                        (diced.toUpperCase().equals("Y")||diced.toUpperCase().equals("N"))
                        && dprice>0) {
                    if (diced.toUpperCase().equals("Y")) {
                        boolIced = true;
                    } else {
                        boolIced = false;
                    }
                }
                if(aName == null){
                    Menu newItem = new Menu(dname, dshots, boolIced, dprice);
                    mChillJavaDAO.insert(newItem);
                    Snackbar.make(view, "Item successfully added", Snackbar.LENGTH_SHORT).show();
                    add.setEnabled(false);
                    add.setText("✅");
                }else{
                    Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentFactory(AdminActivity.class);
            }
        });

    }

    private void intentFactory(Class destination){
        Intent intent = new Intent(AddDrinkActivity.this, destination);
        startActivity(intent);
        finish();
    }
    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();
    }
}