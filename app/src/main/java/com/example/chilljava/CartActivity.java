package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.Menu;
import com.example.chilljava.db.Orders;
import com.example.chilljava.db.User;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private Button homeBtn;
    private Button menuBtn;
    List<Menu> selectedItems;
    private ChillJavaDAO mChillJavaDAO;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        homeBtn = findViewById(R.id.homebutton);
        menuBtn = findViewById(R.id.menubutton);
        selectedItems = (List<Menu>) getIntent().getSerializableExtra("SelectedItems");
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);
//        Toast.makeText(this, String.valueOf(userId), Toast.LENGTH_SHORT).show();
        wireUpDB();
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentFactory(MainActivity.class);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentFactory(MenuActivity.class);
            }
        });
        if (selectedItems != null && !selectedItems.isEmpty()) {
            showSelected(selectedItems);
            Toast.makeText(this, "There are items here!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Nothing to see here", Toast.LENGTH_SHORT).show();
        }
    }
    private void showSelected(List<Menu> selectedItems) {
        LinearLayout mainLayout = findViewById(R.id.myLayoutOuter);
        Toast.makeText(this, "There are items in your cart", Toast.LENGTH_SHORT).show();
        double total = 0;
        for (int i = 0; i < selectedItems.size(); i++) {
            String imageRsc = selectedItems.get(i).getItemName().replace(" ", "_").toLowerCase();
            int resourceId = getResources().getIdentifier(imageRsc, "drawable", getPackageName());
            ImageView coffeeImage = new ImageView(this);
            coffeeImage.setImageResource(resourceId);
            coffeeImage.setLayoutParams(new LinearLayout.LayoutParams(480, 480));

            TextView textView = new TextView(this);
            textView.setText(selectedItems.get(i).getItemName()+", $"+String.format("%.2f", selectedItems.get(i).getPrice()));
            textView.setTextColor(getColor(R.color.milk));
            textView.setTextSize(30);
            textView.setGravity(Gravity.CENTER);

            TextView textView1 = new TextView(this);
            textView1.setWidth(200);
            textView1.setTextColor(getColor(R.color.milk));
            textView1.setTextSize(20);
            textView1.setGravity(Gravity.CENTER);

            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setGravity(Gravity.CENTER);
            itemLayout.addView(coffeeImage);
            itemLayout.addView(textView);
            itemLayout.addView(textView1);
            mainLayout.addView(itemLayout);
            total += selectedItems.get(i).getPrice();
        }
        TextView priceView = new TextView(this);
        priceView.setText("Total: $" + String.format("%.2f", total));
        priceView.setTextSize(43);
        priceView.setTextColor(getColor(R.color.milk));
        mainLayout.addView(priceView);
        checkout(userId, total, mainLayout);

    }
    private void checkout(int userId, double ttl, LinearLayout mainLayout){
//        LinearLayout mainLayout = findViewById(R.id.myLayoutOuter);
        Button button = new Button(this);
        button.setText("Checkout for $"+ String.format("%.2f", ttl));
        button.setTextSize(34);
        StringBuilder items = new StringBuilder();
        mainLayout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<selectedItems.size(); i++){
                     items.append(selectedItems.get(i).getItemId());
                     Orders order = new Orders(userId, items.toString());
                     mChillJavaDAO.insert(order);
                }
                mainLayout.removeAllViews();
            }
        });

    }
    private void intentFactory(Class destination){
        Intent intent = new Intent(CartActivity.this, destination);
        intent.putExtra("userId", userId);
        startActivity(intent);
        finish();
    }
    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();

    }
}