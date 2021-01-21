package com.leejordan.minecraftboardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WinActivity extends AppCompatActivity {

    ImageView playerIcon;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        playerIcon = findViewById(R.id.playerWin);
        playAgain = findViewById(R.id.playAgainWin);

        if (MainActivity.playerType == 1){
            playerIcon.setImageResource(R.drawable.steve_happy);
        }
        else if(MainActivity.playerType == 2){
            playerIcon.setImageResource(R.drawable.alex_happy);
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(WinActivity.this, MainActivity.class);
                startActivity(gameIntent);
                finish();
            }
        });

    }
}