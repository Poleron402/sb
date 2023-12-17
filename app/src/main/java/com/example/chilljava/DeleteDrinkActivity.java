package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.Menu;
import com.google.android.material.snackbar.Snackbar;

/**
 * This activity is used for editing the price of the drink - part of CRUD and the last activity that I implement
 */
public class DeleteDrinkActivity extends AppCompatActivity {
    private EditText drinkName;
    private Button delete;
    private Button home;
    private ChillJavaDAO mChillJavaDAO;
    private ChillJavaDB mChillJavaDB;
    private String input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_drink);
        drinkName = findViewById(R.id.name);
        delete = findViewById(R.id.deleteDrink);
        home = findViewById(R.id.goHome);
        wireUpDB();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = drinkName.getText().toString();
                new DeleteDrinkTask().execute(input);
                delete.setEnabled(false);
                Snackbar.make(view, "Deleted the item", Snackbar.LENGTH_SHORT).show();

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentFactory(AdminActivity.class);
            }
        });

    }




    /**This was given to me by chatgpt because I could not implement deleting to work while the
     * queries were running on the main thread. Worked with adding though!
     */

    private class DeleteDrinkTask extends AsyncTask<String, Void, Menu> {
        @Override
        protected Menu doInBackground(String... params) {
            String input = params[0];
            return mChillJavaDAO.getAnItemByName(input);
        }

        @Override
        protected void onPostExecute(Menu menu) {
            if (menu != null) {
                new DeleteMenuTask().execute(menu);
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Something went wrong", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private class DeleteMenuTask extends AsyncTask<Menu, Void, Void> {
        @Override
        protected Void doInBackground(Menu... params) {
            Menu menu = params[0];
            mChillJavaDAO.delete(menu);
            return null;
        }
    }

    private void intentFactory(Class destination){
        Intent intent = new Intent(DeleteDrinkActivity.this, destination);
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