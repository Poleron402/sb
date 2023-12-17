package com.example.chilljava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.chilljava.db.Menu;
import com.example.chilljava.db.ChillJavaDAO;
import com.example.chilljava.db.ChillJavaDB;
import com.example.chilljava.db.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int userId = -1;
    private User user;
    private TextView mUserDisplay;
    private Button madminButton;
    private Button mLogoutButton;
    private Button mHistoryButton;
    private Button menuButton;
    private Button mLuckyButton;
    private static final String USER_ID_KEY = "com.example.chilljava.userIdKey";
    private static final String PREFENCES_KEY = "com.example.chilljava.PREFENCES_KEY";
    private ChillJavaDAO mChillJavaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        madminButton = findViewById(R.id.adminButton);
        mUserDisplay = findViewById(R.id.nameDisplay);
        mLogoutButton = findViewById(R.id.logoutButton);
        menuButton = findViewById(R.id.menuButton);
        mHistoryButton = findViewById(R.id.historyButton);
        mLuckyButton = findViewById(R.id.luckyButton);
        wireUpDB();
        SharedPreferences preferences = getSharedPreferences(PREFENCES_KEY, MODE_PRIVATE);
        userId = preferences.getInt(USER_ID_KEY, -1);
        baseItems();
        if (userId != -1){
            user = mChillJavaDAO.getUserById(userId);
            Toast.makeText(this, "User ID: " + userId+" "+user.getIsadmin(), Toast.LENGTH_SHORT).show();
            mUserDisplay.setText(user.getUsername()+"\n⭐"+user.getStars());
            if(user.getIsadmin().equals("true")){
                madminButton.setVisibility(View.VISIBLE);
            }else{
                madminButton.setVisibility(View.GONE);
            }
        }else{
            IntentFactory(LoginActivity.class);
            finish();
        }
        List<User> users = mChillJavaDAO.getAllUser();
        if(users.size() == 0){
            User defaultUser = new User("testuser1", "testuser1", 0, "false");
            mChillJavaDAO.insert(defaultUser);
            User admin = new User("admin2", "admin2", 10, "true");
            mChillJavaDAO.insert(admin);
        }
        mLogoutButton.setOnClickListener(view -> {
            SharedPreferences preferences1 =getSharedPreferences(PREFENCES_KEY,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences1.edit();
            editor.clear();
            editor.apply();
            finish();
            userId = -1;
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        });
        menuButton.setOnClickListener(view->{
                IntentFactory(MenuActivity.class);
        });
        madminButton.setOnClickListener(view -> {
            IntentFactory(AdminActivity.class);
        });
        mHistoryButton.setOnClickListener(view->{
                IntentFactory(HistoryActivity.class);
        });
        mLuckyButton.setOnClickListener(view->{
            List<Menu> menu = mChillJavaDAO.getAllItems();
            Random random = new Random();
            Menu luckyItem = menu.get(random.nextInt(menu.size()));
            Snackbar.make(findViewById(android.R.id.content), "✨You should get "+luckyItem.getItemName()+"✨", Snackbar.LENGTH_SHORT).show();
        });

    }
    private void baseItems(){

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
    private void IntentFactory(Class destination){
        Intent intent = new Intent(MainActivity.this, destination);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
    private void wireUpDB(){
        mChillJavaDAO = Room.databaseBuilder(this, ChillJavaDB.class, ChillJavaDB.DB_NAME)
                .allowMainThreadQueries().build().getChillJavaDAO();
    }
}