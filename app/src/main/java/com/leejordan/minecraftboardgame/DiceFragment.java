package com.leejordan.minecraftboardgame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiceFragment extends Fragment {

    View view;
    ImageView diceImage;
    static int roll;
    static boolean rolled;
    static boolean on;
    private int count;

    public DiceFragment() {
        // Required empty public constructor
    }

    public static DiceFragment newInstance(){
        DiceFragment frag = new DiceFragment();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dice, container, false);
        count = 0;
        rolled = false;
        on = true;
        diceImage = (ImageView) view.findViewById(R.id.rollingDice);
        diceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    count++;

                    if (count == 1){
                        rollDice();
                        rolled = true;

                        new CountDownTimer(1500, 500){

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                GameActivity.removeRoll();
                                rolled = false;
                                on = false;

                                LayoutInflater inflater2 = getLayoutInflater();
                                View layout = inflater2.inflate(R.layout.toast,
                                        (ViewGroup) view.findViewById(R.id.toastLayout));

                                TextView text = (TextView) layout.findViewById(R.id.toastText);
                                if(roll > 1){
                                    text.setText("Move up " + roll + " spaces.");
                                }
                                else {
                                    text.setText("Move up " + roll + " space.");
                                }

                                DrawView.moves = roll;
                                DrawView.myTurn = true;

                                Toast toast = new Toast(getContext());
                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setView(layout);
                                toast.show();


                            }
                        }.start();
                    }
                }
        });

        return view;

    }

    private void rollDice() {
        int value = (int)(Math.random() * 5) + 1;
        roll = value;
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        diceImage.startAnimation(anim);
        switch (value){
            case 1:
                diceImage.setImageResource(R.drawable.dice1);
                break;
            case 2:
                diceImage.setImageResource(R.drawable.dice2);
                break;
            case 3:
                diceImage.setImageResource(R.drawable.dice3);
                break;
            case 4:
                diceImage.setImageResource(R.drawable.dice4);
                break;
            case 5:
                diceImage.setImageResource(R.drawable.dice5);
                break;

        }

    }




}