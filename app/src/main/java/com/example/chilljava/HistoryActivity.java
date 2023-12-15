package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.Menu;
import com.example.chilljava.db.Orders;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private Button homeBtn;
    private Button menuBtn;
    private ChillJavaDAO mChillJavaDAO;
    private int userId;
    List<Orders> orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        homeBtn = findViewById(R.id.homebutton);
        menuBtn = findViewById(R.id.menubutton);
        wireUpDB();
        homeBtn.setOnClickListener(view -> intentFactory(MainActivity.class));
        menuBtn.setOnClickListener(view -> intentFactory(MenuActivity.class));
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);
        orders = mChillJavaDAO.getOrderByCustId(userId);
        if (orders != null && orders.size() > 0) {
            Toast.makeText(this, "You have orders", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You haven't made any orders", Toast.LENGTH_SHORT).show();
        }
        showHistory();
    }
    private void showHistory(){
        LinearLayout mainLayout = findViewById(R.id.myLayoutOuter);
        for(int i =0; i<orders.size(); i++){
            TextView textView = new TextView(this);
            textView.setTextSize(20);
            textView.setText("Order ID: "+orders.get(i).getOrderId());
            TextView textView1 = new TextView(this);
            textView1.setTextSize(20);
            String drinks = orders.get(i).getItemIds();
            StringBuilder drinksnames = new StringBuilder();
            for (int j =0; j<drinks.length(); j++){
                drinksnames.append(mChillJavaDAO.getItemById(Character.getNumericValue(drinks.charAt(j)))).append("\n");
            }
            textView1.setText("Drinks Ordered: "+drinksnames);
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setGravity(Gravity.CENTER);
            itemLayout.addView(textView);
            itemLayout.addView(textView1);
            mainLayout.addView(itemLayout);
        }
    }

    private void intentFactory(Class destination){
        Intent intent = new Intent(HistoryActivity.this, destination);
        intent.putExtra("userId", userId);
        startActivity(intent);
        finish();
    }

    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();
    }
}