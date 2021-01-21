package com.leejordan.minecraftboardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton steve, alex;
    Button startGame;
    boolean clicked = false;
    static int playerType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        steve = findViewById(R.id.steve);
        alex = findViewById(R.id.alex);
        startGame = findViewById(R.id.playGame);

        steve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
                playerType = 1;
                toast("You have selected Steve as your player.");
//                Toast.makeText(MainActivity.this, "You have selected Steve as your player.", Toast.LENGTH_SHORT).show();
            }
        });

        alex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
                playerType = 2;
                toast("You have selected Alex as your player.");
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == false){
                    toast("You haven't selected a player yet.");
                }
                else{
                    Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(gameIntent);
                    finish();
                }
            }
        });
    }

    public void toast(String t){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.toastText);
        text.setText(t);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 110);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}