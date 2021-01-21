package com.leejordan.minecraftboardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class DragonActivity extends AppCompatActivity {

    private DrawViewDragon game;
    private ImageView instructions, weapon, topBorder, weaponSlot, heartSlot, timeSlot;
    private TextView hearts, timer;
    private Button startGame;
    private final int TIME = 15;
    private final int HEARTS = 100;
    private int heartsLeft = HEARTS;
    private String theWeapon;
    private int secondsLeft = TIME;
    private int clicks;
    int dragonHearts;
    private HashMap<String, Integer> weaponToID;
    private HashMap<String, Integer> damage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragon);

        clicks = 0;

        theWeapon = DrawView.weaponChoice;

        weaponToID = new HashMap<>();
        weaponToID.put("NONE", R.drawable.fist);
        weaponToID.put("WOODEN_SWORD", R.drawable.wood_sword);
        weaponToID.put("GOLD_SWORD", R.drawable.gold_sword);
        weaponToID.put("IRON_SWORD", R.drawable.iron_sword);
        weaponToID.put("DIAMOND_SWORD", R.drawable.diamond_sword);

        damage = new HashMap<>();
        damage.put("NONE", 1);
        damage.put("WOODEN_SWORD", 2);
        damage.put("GOLD_SWORD", 3);
        damage.put("IRON_SWORD", 5);
        damage.put("DIAMOND_SWORD", 7);

        game = findViewById(R.id.drawingDragon);
        instructions = findViewById(R.id.bossBattleInstructions);
        weapon = findViewById(R.id.weapon);
        hearts = findViewById(R.id.dragonLives);
        timer = findViewById(R.id.dragonCountdown);
        topBorder = findViewById(R.id.topBorder);
        weaponSlot = findViewById(R.id.weaponSlot);
        heartSlot = findViewById(R.id.heartSlot);
        timeSlot = findViewById(R.id.timeSlot);
        startGame = findViewById(R.id.startDragonGame);

        game.setVisibility(View.GONE);
        weapon.setVisibility(View.GONE);
        hearts.setVisibility(View.GONE);
        timer.setVisibility(View.GONE);
        topBorder.setVisibility(View.GONE);
        weaponSlot.setVisibility(View.GONE);
        heartSlot.setVisibility(View.GONE);
        timeSlot.setVisibility(View.GONE);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructions.setVisibility(View.GONE);
                startGame.setVisibility(View.GONE);
                game.setVisibility(View.VISIBLE);
                weapon.setVisibility(View.VISIBLE);
                hearts.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                topBorder.setVisibility(View.VISIBLE);
                weaponSlot.setVisibility(View.VISIBLE);
                heartSlot.setVisibility(View.VISIBLE);
                timeSlot.setVisibility(View.VISIBLE);

                weapon.setImageResource(weaponToID.get(theWeapon));

                new CountDownTimer((TIME * 1000), 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        secondsLeft = (int) (millisUntilFinished / 1000);
                        if (secondsLeft <= 15  && secondsLeft >= 12 ){
                            game.setSpeed(25);
                        }
                        else if(secondsLeft <= 11  && secondsLeft >= 8 ){
                            game.setSpeed(35);
                        }
                        else if(secondsLeft <= 8  && secondsLeft >= 4 ){
                            game.setSpeed(40);
                        }
                        else if(secondsLeft <= 3  && secondsLeft >= 0 ){
                            game.setSpeed(45);
                        }
                        timer.setText(secondsLeft + "s");
                        clicks = game.getTaps();
                        Log.i("TAPS", "" + clicks) ;
                        heartsLeft = HEARTS - (clicks * damage.get(theWeapon));
                        Log.i("TAPS", "h" + heartsLeft) ;
                        if (heartsLeft >=0){
                            hearts.setText("" + heartsLeft);
                        }
                        else if (heartsLeft < 0) {
                            hearts.setText("" + 0);
                        }
                        if (heartsLeft <= 0){
                            cancel();
                            onFinish();
                        }
                    }

                    @Override
                    public void onFinish() {
                        DrawViewDragon.playable = false;
                        new CountDownTimer(1000, 500) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                if(heartsLeft <= 0){
                                    winToast();
                                    new CountDownTimer(3500, 500){

                                        @Override
                                        public void onTick(long millisUntilFinished) {

                                        }

                                        @Override
                                        public void onFinish() {
                                            switchToWin();
                                        }
                                    }.start();
                                }
                                else{
                                    new CountDownTimer(1000, 500) {
                                        @Override
                                        public void onTick(long millisUntilFinished) {

                                        }

                                        @Override
                                        public void onFinish() {
                                            switchToLose();
                                        }
                                    }.start();
                                }
                            }
                        }.start();

                    };
                }.start();
            }
        });

    }

    public void winToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dragon_toast,
                (ViewGroup) findViewById(R.id.dragon_toastLayout));

        TextView winOrLose = (TextView) layout.findViewById(R.id.dragon_label);
        winOrLose.setText("You won!");
        TextView reward = (TextView) layout.findViewById(R.id.dragon_toastText);
        reward.setText("The Ender Dragon is dead.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    public void switchToLose(){
        Intent gameIntent = new Intent(getApplicationContext(), LoseActivity.class);
        gameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(gameIntent);
        finish();
    }
    public void switchToWin(){
        Intent gameIntent = new Intent(getApplicationContext(), WinActivity.class);
        gameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(gameIntent);
        finish();
    }

}