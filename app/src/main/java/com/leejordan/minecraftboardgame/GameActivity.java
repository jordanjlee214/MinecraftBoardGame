package com.leejordan.minecraftboardgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class GameActivity extends AppCompatActivity {

    ImageView instructionsPart1, instructionsPart2;
    Button startGame;
    DrawView boardGame;
    int buttonClicks = 0;
    static FragmentManager fragmentManager;
    static InventoryFragment inventoryFrag;
    static DiceFragment diceFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Fragment Setup
        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.container) != null){
            if (savedInstanceState != null){
                return;
            }
            inventoryFrag = new InventoryFragment();
            diceFrag = new DiceFragment();
        }

        //Views
        instructionsPart1 = findViewById(R.id.instructions1);
        instructionsPart2 = findViewById(R.id.instructions2);
        startGame = findViewById(R.id.next);
        boardGame = findViewById(R.id.drawing);

        instructionsPart2.setVisibility(View.GONE);
        boardGame.setVisibility(View.GONE);
//        DiceFragment.on = true;

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicks++;
                if (buttonClicks == 1){
                    instructionsPart1.setVisibility(View.GONE);
                    instructionsPart2.setVisibility(View.VISIBLE);
                }
                else if(buttonClicks == 2){
                    fragmentManager.beginTransaction().add(R.id.container, inventoryFrag).commit();


                    instructionsPart2.setVisibility(View.GONE);
                    boardGame.setVisibility(View.VISIBLE);
                    startGame.setVisibility(View.GONE);

                    toast("Tap the dice in your inventory to roll.");

                    new CountDownTimer(3000, 500){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            toast("After you roll, tap anywhere on the screen to move spaces.");

                        }
                    }.start();
//                    DiceFragment.on = false;
                }
            }
        });

    }

    public static void roll(){
        fragmentManager.beginTransaction().add(R.id.container, diceFrag).commit();
    }

    public static void removeRoll(){
        fragmentManager.beginTransaction().remove(diceFrag).commit();

    }

    public void toast(String t){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.toastText);
        text.setText(t);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void moveToast(int i){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.move_toast,
                (ViewGroup) findViewById(R.id.move_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.move_toastText);
        text.setText("" + i);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void diamondOreToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.ore_toast,
                (ViewGroup) findViewById(R.id.ore_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.ore_toastText);
        text.setText("You found diamond ore! Move forward 3 spaces.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void creeperToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.creeper_toast,
                (ViewGroup) findViewById(R.id.creeper_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.creeper_toastText);
        text.setText("A Creeper exploded! Move back 3 spaces.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void zombieToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.zombie_toast,
                (ViewGroup) findViewById(R.id.zombie_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.zombie_toastText);
        text.setText("You got hit by a Zombie! Move back 1 space.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void visitedToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.visited_toast,
                (ViewGroup) findViewById(R.id.visited_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.visited_toastText);
        text.setText("You already looted this chest.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void chestToast(int ender_pearls, int swords, String swordType){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.chest_toast,
                (ViewGroup) findViewById(R.id.chest_toastLayout));

        ImageView item1 = (ImageView) layout.findViewById(R.id.chest_item1);
        ImageView item2 = (ImageView) layout.findViewById(R.id.chest_item2);

        switch (ender_pearls) {
            case 1:
                item1.setImageResource(R.drawable.ender_pearl1);
                break;
            case 2:
                item1.setImageResource(R.drawable.ender_pearl2);
                break;
            case 3:
                item1.setImageResource(R.drawable.ender_pearl3);
                break;
            case 4:
                item1.setImageResource(R.drawable.ender_pearl4);
                break;
        }

        if (swords == 0){
            item2.setVisibility(View.GONE);
        }
        else{
            switch (swordType){
                case "WOODEN_SWORD":
                    item2.setImageResource(R.drawable.wood_sword);
                    break;
                case "IRON_SWORD":
                    item2.setImageResource(R.drawable.iron_sword);
                    break;
                case "GOLD_SWORD":
                    item2.setImageResource(R.drawable.gold_sword);
                    break;
                case "DIAMOND_SWORD":
                    item2.setImageResource(R.drawable.diamond_sword);
                    break;
            }

            }


        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void minigameToast(int gameType){

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.minigame_toast,
                (ViewGroup) findViewById(R.id.minigame_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.minigame_toastText);

        String game = "";
        switch(gameType){
            case 1:
                game = "Game: Speed Mine Challenge";
                break;
            case 2:
                game = "Game: Diamond Hunting";
                break;
            case 3:
                game = "Game: Enderman Brawl";
                break;
        }

        text.setText(game);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void switchToGame1(){
        Intent gameIntent = new Intent(getApplicationContext(), MineActivity.class);
        gameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(gameIntent);
    }

    public void switchToGame2(){
        Intent gameIntent = new Intent(getApplicationContext(), DiamondActivity.class);
        gameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(gameIntent);
    }

    public void switchToGame3(){
        Intent gameIntent = new Intent(getApplicationContext(), EndermanActivity.class);
        gameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(gameIntent);
    }


    public void switchToDragon(){
        Intent gameIntent = new Intent(getApplicationContext(), DragonActivity.class);
        gameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(gameIntent);
        finish();
    }
    public void switchToLose(){
        Intent gameIntent = new Intent(getApplicationContext(), LoseActivity.class);
        gameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(gameIntent);
        finish();
    }

    public void prizeToast(int ender_pearls, String swordType){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.prize_toast,
                (ViewGroup) findViewById(R.id.prize_toastLayout));

        ImageView item1 = (ImageView) layout.findViewById(R.id.prize_item1);
        ImageView item2 = (ImageView) layout.findViewById(R.id.prize_item2);

        switch (ender_pearls) {
            case 1:
                item1.setImageResource(R.drawable.ender_pearl1);
                break;
            case 2:
                item1.setImageResource(R.drawable.ender_pearl2);
                break;
            case 3:
                item1.setImageResource(R.drawable.ender_pearl3);
                break;
            case 4:
                item1.setImageResource(R.drawable.ender_pearl4);
                break;
        }


            switch (swordType){
                case "WOODEN_SWORD":
                    item2.setImageResource(R.drawable.wood_sword);
                    break;
                case "IRON_SWORD":
                    item2.setImageResource(R.drawable.iron_sword);
                    break;
                case "GOLD_SWORD":
                    item2.setImageResource(R.drawable.gold_sword);
                    break;
                case "DIAMOND_SWORD":
                    item2.setImageResource(R.drawable.diamond_sword);
                    break;
            }



        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


    public void diamondLoseToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.diamond_toast,
                (ViewGroup) findViewById(R.id.diamond_toastLayout));

        TextView winOrLose = (TextView) layout.findViewById(R.id.diamond_label);
        winOrLose.setText("Times up!");
        TextView reward = (TextView) layout.findViewById(R.id.diamond_toastText);
        reward.setText("You lost. Continue the game.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void portalToast(String t){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.portal_toast,
                (ViewGroup) findViewById(R.id.portal_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.portal_toastText);
        text.setText(t);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }



    public void endToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.end_toast,
                (ViewGroup) findViewById(R.id.end_toastLayout));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}

