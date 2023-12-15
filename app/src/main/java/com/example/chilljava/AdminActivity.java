package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    private Button addingButton;
    private Button deletingButton;
    private Button editingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        addingButton = findViewById(R.id.addbutton);
        deletingButton = findViewById(R.id.deletebutton);
        editingButton = findViewById(R.id.editbutton);

    }
}