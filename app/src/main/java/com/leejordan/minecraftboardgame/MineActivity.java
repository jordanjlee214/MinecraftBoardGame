package com.leejordan.minecraftboardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MineActivity extends AppCompatActivity {

    ImageView topBar;
    DrawViewMine game;
    Button startMineGame;
    TextView timerCountdown;
    TextView blockCount;
    private static final int BLOCKS = 10;
    private static final int TIME = 15;
    int secondsLeft = TIME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        topBar = findViewById(R.id.instructions);
        game = findViewById(R.id.speedMineGame);
        startMineGame = findViewById(R.id.startMiningGame);
        timerCountdown = findViewById(R.id.miningCountdown);
        blockCount = findViewById(R.id.blockCount);

        timerCountdown.setVisibility(View.GONE);
        blockCount.setVisibility(View.GONE);
//
        startMineGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMineGame.setVisibility(View.GONE);
                topBar.setImageResource(R.drawable.blank_instrux);
                timerCountdown.setVisibility(View.VISIBLE);
                blockCount.setVisibility(View.VISIBLE);

                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        game.playable = true;
                        new CountDownTimer(TIME * 1000, 100){

                            @Override
                            public void onTick(long millisUntilFinished) {
                                secondsLeft = (int) (millisUntilFinished / 1000);
                                timerCountdown.setText(secondsLeft + "s");

                                //get block count from DrawViewMine
                                blockCount.setText("" + game.getBlocksMined());

                                //if it reaches 10 blocks
                                if (game.getBlocksMined() >= BLOCKS){
                                    cancel();
                                    onFinish();
                                }
                            }

                            @Override
                            public void onFinish() {
                                game.playable = false;
                                if (game.getBlocksMined() >= BLOCKS){
                                    winToast();
                                }
                                else{
                                    loseToast();
                                }
                                new CountDownTimer(3500, 500) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        finish();
                                        if (game.getBlocksMined() >= BLOCKS){
                                            DrawView.miningGameEnd(1);
                                        }
                                        else{
                                            DrawView.miningGameEnd(0);
                                        }
                                    }
                                }.start();
                                //check if you won, and announce if you have won
                                //communicate with DrawView and GameActivity to show you won

                            }
                        }.start();
                    }
                }.start();



            }
        });


       //finish
    }

    public void winToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.mining_toast,
                (ViewGroup) findViewById(R.id.mining_toastLayout));

        TextView winOrLose = (TextView) layout.findViewById(R.id.mining_label);

        TextView reward = (TextView) layout.findViewById(R.id.mining_toastText);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void loseToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.mining_toast,
                (ViewGroup) findViewById(R.id.mining_toastLayout));

        TextView winOrLose = (TextView) layout.findViewById(R.id.mining_label);
        winOrLose.setText("You lost! :(");
        TextView reward = (TextView) layout.findViewById(R.id.mining_toastText);
        reward.setText("You won't receive a reward.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }


}