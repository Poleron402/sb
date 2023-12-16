package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.Menu;
import com.google.android.material.snackbar.Snackbar;

public class DeleteDrinkActivity extends AppCompatActivity {
    private EditText drinkName;
    private Button delete;
    private Button home;
    private ChillJavaDAO mChillJavaDAO;
    private String input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_drink);
        drinkName = findViewById(R.id.name);
        delete = findViewById(R.id.deleteDrink);
        home = findViewById(R.id.goHome);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = drinkName.getText().toString();
                    Menu menu = mChillJavaDAO.getAnItemByName(input);
                    if(menu!=null) {
                        mChillJavaDAO.delete(menu);
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

//    private boolean isId(String input){
//        try{
//            Integer.parseInt(input);
//            return true;
//        }catch(NumberFormatException err){
//            return false;
//        }
//    }

    private void intentFactory(Class destination){
        Intent intent = new Intent(DeleteDrinkActivity.this, destination);
        startActivity(intent);
        finish();
    }
    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();
    }
}