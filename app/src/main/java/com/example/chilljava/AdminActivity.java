package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This is a pretty simple admin activity. When an admin clicks on the button that is only accessible to them
 * it comes here, where the admin has a choice to add a drink, delete, or edit price.
 * I decided to only implement price update because if the drink changes, it might just be a different drink
 */
public class AdminActivity extends AppCompatActivity {
    private Button addingButton;
    private Button deletingButton;
    private Button editingButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        addingButton = findViewById(R.id.addbutton);
        deletingButton = findViewById(R.id.deletebutton);
        editingButton = findViewById(R.id.editbutton);
        backButton = findViewById(R.id.back);
        addingButton.setOnClickListener(view -> {
            IntentFactory(AddDrinkActivity.class);
        });
        backButton.setOnClickListener(view -> {
            IntentFactory(MainActivity.class);
        });
        deletingButton.setOnClickListener(view-> {
                IntentFactory(DeleteDrinkActivity.class);
        });
        editingButton.setOnClickListener(view -> {
            IntentFactory(EditDrinkActivity.class);
        });
    }

    private void IntentFactory(Class whereTo){
        Intent intent = new Intent(AdminActivity.this, whereTo);
        startActivity(intent);
    }
}