package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.Menu;
import com.google.android.material.snackbar.Snackbar;
import android.os.AsyncTask;

public class EditDrinkActivity extends AppCompatActivity {
    private Button goBack;
    private Button edit;
    private EditText name;
    private EditText price;
    private String ename;
    private String eprice;
    private ChillJavaDAO mChillJavaDAO;
    private ChillJavaDB mChillJavaDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drink);
        goBack = findViewById(R.id.goHome);
        edit = findViewById(R.id.editDrink);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        wireUpDB();
        edit.setOnClickListener(view->{
            ename = name.getText().toString();
            new EditDrinkTask().execute(ename);
            edit.setEnabled(false);
            Snackbar.make(view, "Edited the item", Snackbar.LENGTH_SHORT).show();
            edit.setText("✅");
        });
        goBack.setOnClickListener(view -> {
            intentFactory(AdminActivity.class);
        });

    }

    private class EditDrinkTask extends AsyncTask<String, Void, Menu> {
        @Override
        protected Menu doInBackground(String... params) {
            String input = params[0];
            return mChillJavaDAO.getAnItemByName(input);
        }

        @Override
        protected void onPostExecute(Menu menu) {
            if (menu != null) {
                // Display UI for editing or use a dialog to get updated information
                // For simplicity, let's assume you have an edit method in your DAO
                eprice = price.getText().toString();
                menu.setPrice(Double.parseDouble(eprice));  // Set other updated properties
                new UpdateMenuTask().execute(menu);
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Item not found", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private class UpdateMenuTask extends AsyncTask<Menu, Void, Void> {
        @Override
        protected Void doInBackground(Menu... params) {
            Menu updatedMenu = params[0];
            mChillJavaDAO.update(updatedMenu);
            return null;
        }
    }
    private void intentFactory(Class destination){
        Intent intent = new Intent(EditDrinkActivity.this, destination);
        startActivity(intent);
        finish();
    }

    private void wireUpDB(){
        mChillJavaDB = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .build();

        // Create a Data Access Object (DAO) instance from the database
        mChillJavaDAO = mChillJavaDB.getChillJavaDAO();
    }
}