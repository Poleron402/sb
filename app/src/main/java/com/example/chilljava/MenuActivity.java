package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private Button homeButton;
    List<Menu> allItems = new ArrayList<>();
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
        baseItems();
        showItems();

    }
    private void baseItems(){
        wireUpDB();
        if(mChillJavaDAO.getAllItems().size() == 0) {
            Menu newItem = new Menu("Dark Roast", 0, true, 1.50);
            Menu newItem1 = new Menu("Medium Roast", 0, true, 1.50);
            Menu newItem2 = new Menu("Latte", 2, true, 1.50);
            Menu newItem3 = new Menu("Cappuccino", 2, true, 1.50);
            mChillJavaDAO.insert(
                    newItem, newItem1, newItem2, newItem3
            );
        }

    }
    private void showItems(){
        allItems = mChillJavaDAO.getAllItems();

        setContentView(R.layout.activity_menu);
        LinearLayout linearLayout = findViewById(R.id.myLayoutOuter);

        for (int i = 0; i < allItems.size(); i++) {
            String imageRsc = allItems.get(i).getItemName().replace(" ", "_").toLowerCase();
            int resourceId = getResources().getIdentifier(imageRsc, "drawable", getPackageName());
            ImageView coffeeImage = new ImageView(this);
            coffeeImage.setImageResource(resourceId);
            coffeeImage.setLayoutParams(new LinearLayout.LayoutParams(480, 480));

            TextView textView = new TextView(this);
            textView.setText(allItems.get(i).getItemName());
            textView.setTextColor(getColor(R.color.milk));
            textView.setTextSize(30);
            textView.setGravity(Gravity.CENTER);

            TextView textView1 = new TextView(this);
            textView1.setWidth(200);
            textView1.setTextColor(getColor(R.color.milk));
            textView1.setTextSize(20);
            textView1.setGravity(Gravity.CENTER);

            if(allItems.get(i).getNumShots()>0){
                textView1.setText("Caffeine: "+allItems.get(i).getNumShots()+" shots of espresso");
            }else{
                textView1.setText("Caffeine: the default does not get shots of espresso");
            }
            TextView textView2 = new TextView(this);
            textView2.setWidth(200);
            textView2.setTextColor(getColor(R.color.milk));
            textView2.setTextSize(20);
            textView2.setGravity(Gravity.CENTER);

            if(allItems.get(i).isCanBeIced() == true){
                textView2.setText("This drink can be crafted iced");
            }else{
                textView2.setText("This drink cannot be iced");
            }


            // Add coffeeImage and textView to a new inner LinearLayout
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setGravity(Gravity.CENTER);
            itemLayout.addView(coffeeImage);
            itemLayout.addView(textView);
            itemLayout.addView(textView1);
            itemLayout.addView(textView2);
            linearLayout.addView(itemLayout);
        }
    }

    private void intentFactory(){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();
    }
}