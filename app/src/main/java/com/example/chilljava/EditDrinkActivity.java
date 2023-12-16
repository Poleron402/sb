package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class EditDrinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_drink);
    }
    private void intentFactory(Class destination){
        Intent intent = new Intent(EditDrinkActivity.this, destination);
        startActivity(intent);
        finish();
    }
}