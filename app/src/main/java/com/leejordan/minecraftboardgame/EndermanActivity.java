package com.leejordan.minecraftboardgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.zip.CheckedOutputStream;

public class EndermanActivity extends AppCompatActivity {

    private ImageView enderman;
    private ImageView header;
    private ImageView instructions;
    private Button startGame;
    private TextView timer;
    private ImageView movesPanel;
    private TextView currentMoveText;
    private ImageView currentMoveIcon;

    private int secondsLeft = 5;
    private int count = 0;
    private final int BREAK = 1000;

    private boolean gameContinues = true;

    private final int SWIPE_THRESHOLD = 100;
    private final int SWIPE_VELOCITY_THRESHOLD = 100;
    private final String SWIPE_UP = "SWIPE UP";
    private final String SWIPE_LEFT = "SWIPE LEFT";
    private final String SWIPE_DOWN = "SWIPE DOWN";
    private final String SWIPE_RIGHT = "SWIPE RIGHT";
    private final String VOLUME_UP = "VOLUME UP";
    private final String VOLUME_DOWN = "VOLUME DOWN";
    private final String SHAKE = "SHAKE";

    private final int SWIPE_UP_IMAGE = R.drawable.swipe_up_icon;
    private final int SWIPE_DOWN_IMAGE = R.drawable.swipe_down_icon;
    private final int SWIPE_LEFT_IMAGE = R.drawable.swipe_left_icon;
    private final int SWIPE_RIGHT_IMAGE = R.drawable.swipe_right_icon;
    private final int VOLUME_UP_IMAGE = R.drawable.volume_up_icon;
    private final int VOLUME_DOWN_IMAGE = R.drawable.volume_down_icon;
    private final int SHAKE_IMAGE = R.drawable.shake_icon;

    private final String swiped_up = "swiped up";
    private final String swiped_down = "swiped down";
    private final String swiped_left = "swiped left";
    private final String swiped_right = "swiped right";
    private final String shaked = "shook the phone";
    private final String increased_volume = "increased the volume";
    private final String decreased_volume = "decreased the volume";
    private final String swiping_up = "swiping up";
    private final String swiping_down = "swiping down";
    private final String swiping_left = "swiping left";
    private final String swiping_right = "swiping right";
    private final String shaking = "shaking the phone";
    private final String increasing_volume = "increasing the volume";
    private final String decreasing_volume = "decreasing the volume";

    boolean playable;
    boolean swipeUp = false;
    boolean swipeDown = false;
    boolean swipeLeft = false;
    boolean swipeRight = false;
    boolean shake = false;
    boolean volumeUp = false;
    boolean volumeDown = false;

    private String moveMade;
    private String currentMove;

    private String[] moves = {SWIPE_UP, SWIPE_DOWN, SWIPE_LEFT, SWIPE_RIGHT, VOLUME_UP, VOLUME_DOWN, SHAKE};
    private boolean[] moveBooleans = {swipeUp, swipeDown, swipeLeft, swipeRight, volumeUp, volumeDown, shake};
    ArrayList<Integer> gameMoves = new ArrayList<>();
    private final int[] moveTimes = {5, 5, 4, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2};
    private int[] moveImages = {SWIPE_UP_IMAGE, SWIPE_DOWN_IMAGE, SWIPE_LEFT_IMAGE, SWIPE_RIGHT_IMAGE, VOLUME_UP_IMAGE, VOLUME_DOWN_IMAGE, SHAKE_IMAGE};
    private HashMap<String, String> moveToImproperMessage;
    private HashMap<String, String> moveToProperMessage;

    private SwipeListener swipeDetector;
    private GestureDetectorCompat gestureDetectorCompat;

    private SensorManager shakeDetector;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enderman);

        moveToProperMessage = new HashMap<>();
        moveToImproperMessage = new HashMap<>();

        playable = false;

        for(int i = 0; i < 15; i++){
            if (i == 0){
                int moveID = (int)(Math.random() * 7);
                gameMoves.add(moveID);
            }
            else{
                int id = (int)(Math.random() * 7) ;
                boolean equalToLast = true;
                while(equalToLast){
                    id = (int)(Math.random() * 7);
                    if (id != gameMoves.get(i-1)){
                        equalToLast = false;
                    }
                }
                gameMoves.add(id);
            }
        }

        Log.i("ENDERMAN MOVES", gameMoves.toString());

        moveToImproperMessage.put(SWIPE_UP, swiped_up);
        moveToImproperMessage.put(SWIPE_DOWN, swiped_down);
        moveToImproperMessage.put(SWIPE_LEFT, swiped_left);
        moveToImproperMessage.put(SWIPE_RIGHT, swiped_right);
        moveToImproperMessage.put(VOLUME_UP, increased_volume);
        moveToImproperMessage.put(VOLUME_DOWN, decreased_volume);
        moveToImproperMessage.put(SHAKE, shaked);

        moveToProperMessage.put(SWIPE_UP, swiping_up);
        moveToProperMessage.put(SWIPE_DOWN, swiping_down);
        moveToProperMessage.put(SWIPE_LEFT, swiping_left);
        moveToProperMessage.put(SWIPE_RIGHT, swiping_right);
        moveToProperMessage.put(VOLUME_UP, increasing_volume);
        moveToProperMessage.put(VOLUME_DOWN, decreasing_volume);
        moveToProperMessage.put(SHAKE, shaking);



        shakeDetector = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(shakeDetector).registerListener(mSensorListener, shakeDetector.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        enderman = findViewById(R.id.enderman);
        header = findViewById(R.id.enderman_logo);
        instructions = findViewById(R.id.enderman_instructions);
        startGame = findViewById(R.id.startEndermanGame);
        timer = findViewById(R.id.endermanCountdown);
        movesPanel = findViewById(R.id.movesBG);
        currentMoveIcon = findViewById(R.id.move_icon);
        currentMoveText = findViewById(R.id.move_text);

        enderman.setVisibility(View.GONE);
        timer.setVisibility(View.GONE);
        movesPanel.setVisibility(View.GONE);
        currentMoveText.setVisibility(View.GONE);
        currentMoveIcon.setVisibility(View.GONE);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                header.setImageResource(R.drawable.frame_blank);
                instructions.setVisibility(View.GONE);
                startGame.setVisibility(View.GONE);
                enderman.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                movesPanel.setVisibility(View.VISIBLE);
                currentMoveText.setVisibility(View.VISIBLE);
                currentMoveIcon.setVisibility(View.VISIBLE);
                currentMoveText.setText("");
                currentMoveIcon.setImageResource(R.drawable.enderman_head);

                new CountDownTimer(1000, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        announcementToast("Get ready! Watch the timer and the move bar!");
                        new CountDownTimer(3500, 500) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                playable = true;

                                moveMade = "";
                                currentMove = moves[gameMoves.get(count)];
                                secondsLeft = moveTimes[count];
                                timer.setText(moveTimes[count] + "s");
                                currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                currentMoveText.setText(moves[gameMoves.get(count)]);

                                new CountDownTimer(moveTimes[count] * 1000, 100) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        secondsLeft = (int) (millisUntilFinished / 1000);
                                        timer.setText(secondsLeft + "s");
                                        if(moveMade.length() > 0){
                                            cancel();
                                            onFinish();
                                        }
                                    }

                                    @Override
                                    public void onFinish() {
                                        playable = false;
                                        checkWin();
                                        if (gameContinues){

                                            new CountDownTimer(BREAK, 100) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {

                                                }

                                                @Override
                                                public void onFinish() {
                                                    playable = true;
                                                    count++; //1
                                                    moveMade = "";
                                                    currentMove = moves[gameMoves.get(count)];
                                                    secondsLeft = moveTimes[count];
                                                    timer.setText(moveTimes[count] + "s");
                                                    currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                    currentMoveText.setText(moves[gameMoves.get(count)]);

                                                    new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                        @Override
                                                        public void onTick(long millisUntilFinished) {
                                                            Log.i("ENDERMAN TIME", "-- " + moveMade);
                                                            secondsLeft = (int) (millisUntilFinished / 1000);
                                                            timer.setText(secondsLeft + "s");
                                                            if(moveMade.length() > 0){
                                                                cancel();
                                                                onFinish();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFinish() {
                                                            playable = false;
                                                            Log.i("ENDERMAN TIME", "-- " + moveMade);
                                                            Log.i("ENDERMAN TIME", "** " + currentMove);
                                                            checkWin();
                                                            if (gameContinues){

                                                                new CountDownTimer(BREAK, 100) {
                                                                    @Override
                                                                    public void onTick(long millisUntilFinished) {

                                                                    }

                                                                    @Override
                                                                    public void onFinish() {
                                                                        playable = true;
                                                                        count++; //2
                                                                        moveMade = "";
                                                                        currentMove = moves[gameMoves.get(count)];
                                                                        secondsLeft = moveTimes[count];
                                                                        timer.setText(moveTimes[count] + "s");
                                                                        currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                        currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                        new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                            @Override
                                                                            public void onTick(long millisUntilFinished) {
                                                                                secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                timer.setText(secondsLeft + "s");
                                                                                if(moveMade.length() > 0){
                                                                                    cancel();
                                                                                    onFinish();
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onFinish() {
                                                                                playable = false;
                                                                                checkWin();
                                                                                if (gameContinues){

                                                                                    new CountDownTimer(BREAK, 100) {
                                                                                        @Override
                                                                                        public void onTick(long millisUntilFinished) {

                                                                                        }

                                                                                        @Override
                                                                                        public void onFinish() {
                                                                                            playable = true;
                                                                                            count++; //3
                                                                                            moveMade = "";
                                                                                            currentMove = moves[gameMoves.get(count)];
                                                                                            secondsLeft = moveTimes[count];
                                                                                            timer.setText(moveTimes[count] + "s");
                                                                                            currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                            currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                            new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                @Override
                                                                                                public void onTick(long millisUntilFinished) {
                                                                                                    secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                    timer.setText(secondsLeft + "s");
                                                                                                    if(moveMade.length() > 0){
                                                                                                        cancel();
                                                                                                        onFinish();
                                                                                                    }
                                                                                                }

                                                                                                @Override
                                                                                                public void onFinish() {
                                                                                                    playable = false;
                                                                                                    checkWin();
                                                                                                    if (gameContinues){

                                                                                                        new CountDownTimer(BREAK, 100) {
                                                                                                            @Override
                                                                                                            public void onTick(long millisUntilFinished) {

                                                                                                            }

                                                                                                            @Override
                                                                                                            public void onFinish() {
                                                                                                                playable = true;
                                                                                                                count++; //4
                                                                                                                moveMade = "";
                                                                                                                currentMove = moves[gameMoves.get(count)];
                                                                                                                secondsLeft = moveTimes[count];
                                                                                                                timer.setText(moveTimes[count] + "s");
                                                                                                                currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                    @Override
                                                                                                                    public void onTick(long millisUntilFinished) {
                                                                                                                        secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                        timer.setText(secondsLeft + "s");
                                                                                                                        if(moveMade.length() > 0){
                                                                                                                            cancel();
                                                                                                                            onFinish();
                                                                                                                        }
                                                                                                                    }

                                                                                                                    @Override
                                                                                                                    public void onFinish() {
                                                                                                                        playable = false;
                                                                                                                        checkWin();
                                                                                                                        if (gameContinues){

                                                                                                                            new CountDownTimer(BREAK, 100) {
                                                                                                                                @Override
                                                                                                                                public void onTick(long millisUntilFinished) {

                                                                                                                                }

                                                                                                                                @Override
                                                                                                                                public void onFinish() {
                                                                                                                                    playable = true;
                                                                                                                                    count++; //5
                                                                                                                                    moveMade = "";
                                                                                                                                    currentMove = moves[gameMoves.get(count)];
                                                                                                                                    secondsLeft = moveTimes[count];
                                                                                                                                    timer.setText(moveTimes[count] + "s");
                                                                                                                                    currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                    currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                    new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                        @Override
                                                                                                                                        public void onTick(long millisUntilFinished) {
                                                                                                                                            secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                            timer.setText(secondsLeft + "s");
                                                                                                                                            if(moveMade.length() > 0){
                                                                                                                                                cancel();
                                                                                                                                                onFinish();
                                                                                                                                            }
                                                                                                                                        }

                                                                                                                                        @Override
                                                                                                                                        public void onFinish() {
                                                                                                                                            playable = false;
                                                                                                                                            checkWin();
                                                                                                                                            if (gameContinues){

                                                                                                                                                new CountDownTimer(BREAK, 100) {
                                                                                                                                                    @Override
                                                                                                                                                    public void onTick(long millisUntilFinished) {

                                                                                                                                                    }

                                                                                                                                                    @Override
                                                                                                                                                    public void onFinish() {
                                                                                                                                                        playable = true;
                                                                                                                                                        count++; //6
                                                                                                                                                        moveMade = "";
                                                                                                                                                        currentMove = moves[gameMoves.get(count)];
                                                                                                                                                        secondsLeft = moveTimes[count];
                                                                                                                                                        timer.setText(moveTimes[count] + "s");
                                                                                                                                                        currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                                        currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                                        new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                                            @Override
                                                                                                                                                            public void onTick(long millisUntilFinished) {
                                                                                                                                                                secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                                                timer.setText(secondsLeft + "s");
                                                                                                                                                                if(moveMade.length() > 0){
                                                                                                                                                                    cancel();
                                                                                                                                                                    onFinish();
                                                                                                                                                                }
                                                                                                                                                            }

                                                                                                                                                            @Override
                                                                                                                                                            public void onFinish() {
                                                                                                                                                                playable = false;
                                                                                                                                                                checkWin();
                                                                                                                                                                if (gameContinues){

                                                                                                                                                                    new CountDownTimer(BREAK, 100) {
                                                                                                                                                                        @Override
                                                                                                                                                                        public void onTick(long millisUntilFinished) {

                                                                                                                                                                        }

                                                                                                                                                                        @Override
                                                                                                                                                                        public void onFinish() {
                                                                                                                                                                            playable = true;
                                                                                                                                                                            count++; //7
                                                                                                                                                                            moveMade = "";
                                                                                                                                                                            currentMove = moves[gameMoves.get(count)];
                                                                                                                                                                            secondsLeft = moveTimes[count];
                                                                                                                                                                            timer.setText(moveTimes[count] + "s");
                                                                                                                                                                            currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                                                            currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                                                            new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onTick(long millisUntilFinished) {
                                                                                                                                                                                    secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                                                                    timer.setText(secondsLeft + "s");
                                                                                                                                                                                    if(moveMade.length() > 0){
                                                                                                                                                                                        cancel();
                                                                                                                                                                                        onFinish();
                                                                                                                                                                                    }
                                                                                                                                                                                }

                                                                                                                                                                                @Override
                                                                                                                                                                                public void onFinish() {
                                                                                                                                                                                    playable = false;
                                                                                                                                                                                    checkWin();
                                                                                                                                                                                    if (gameContinues){

                                                                                                                                                                                        new CountDownTimer(BREAK, 100) {
                                                                                                                                                                                            @Override
                                                                                                                                                                                            public void onTick(long millisUntilFinished) {

                                                                                                                                                                                            }

                                                                                                                                                                                            @Override
                                                                                                                                                                                            public void onFinish() {
                                                                                                                                                                                                playable = true;
                                                                                                                                                                                                count++; //8
                                                                                                                                                                                                moveMade = "";
                                                                                                                                                                                                currentMove = moves[gameMoves.get(count)];
                                                                                                                                                                                                secondsLeft = moveTimes[count];
                                                                                                                                                                                                timer.setText(moveTimes[count] + "s");
                                                                                                                                                                                                currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                                                                                currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                                                                                new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                                                                                    @Override
                                                                                                                                                                                                    public void onTick(long millisUntilFinished) {
                                                                                                                                                                                                        secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                                                                                        timer.setText(secondsLeft + "s");
                                                                                                                                                                                                        if(moveMade.length() > 0){
                                                                                                                                                                                                            cancel();
                                                                                                                                                                                                            onFinish();
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }

                                                                                                                                                                                                    @Override
                                                                                                                                                                                                    public void onFinish() {
                                                                                                                                                                                                        playable = false;
                                                                                                                                                                                                        checkWin();
                                                                                                                                                                                                        if (gameContinues){

                                                                                                                                                                                                            new CountDownTimer(BREAK, 100) {
                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                public void onTick(long millisUntilFinished) {

                                                                                                                                                                                                                }

                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                public void onFinish() {
                                                                                                                                                                                                                    playable = true;
                                                                                                                                                                                                                    count++; //9
                                                                                                                                                                                                                    moveMade = "";
                                                                                                                                                                                                                    currentMove = moves[gameMoves.get(count)];
                                                                                                                                                                                                                    secondsLeft = moveTimes[count];
                                                                                                                                                                                                                    timer.setText(moveTimes[count] + "s");
                                                                                                                                                                                                                    currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                                                                                                    currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                                                                                                    new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                        public void onTick(long millisUntilFinished) {
                                                                                                                                                                                                                            secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                                                                                                            timer.setText(secondsLeft + "s");
                                                                                                                                                                                                                            if(moveMade.length() > 0){
                                                                                                                                                                                                                                cancel();
                                                                                                                                                                                                                                onFinish();
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }

                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                        public void onFinish() {
                                                                                                                                                                                                                            playable = false;
                                                                                                                                                                                                                            checkWin();
                                                                                                                                                                                                                            if (gameContinues){

                                                                                                                                                                                                                                new CountDownTimer(BREAK, 100) {
                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                    public void onTick(long millisUntilFinished) {

                                                                                                                                                                                                                                    }

                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                    public void onFinish() {
                                                                                                                                                                                                                                        playable = true;
                                                                                                                                                                                                                                        count++; //10
                                                                                                                                                                                                                                        moveMade = "";
                                                                                                                                                                                                                                        currentMove = moves[gameMoves.get(count)];
                                                                                                                                                                                                                                        secondsLeft = moveTimes[count];
                                                                                                                                                                                                                                        timer.setText(moveTimes[count] + "s");
                                                                                                                                                                                                                                        currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                                                                                                                        currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                                                                                                                        new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                                            public void onTick(long millisUntilFinished) {
                                                                                                                                                                                                                                                secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                                                                                                                                timer.setText(secondsLeft + "s");
                                                                                                                                                                                                                                                if(moveMade.length() > 0){
                                                                                                                                                                                                                                                    cancel();
                                                                                                                                                                                                                                                    onFinish();
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }

                                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                                            public void onFinish() {
                                                                                                                                                                                                                                                playable = false;
                                                                                                                                                                                                                                                checkWin();
                                                                                                                                                                                                                                                if (gameContinues){

                                                                                                                                                                                                                                                    new CountDownTimer(BREAK, 100) {
                                                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                                                        public void onTick(long millisUntilFinished) {

                                                                                                                                                                                                                                                        }

                                                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                                                        public void onFinish() {
                                                                                                                                                                                                                                                            playable = true;
                                                                                                                                                                                                                                                            count++; //11
                                                                                                                                                                                                                                                            moveMade = "";
                                                                                                                                                                                                                                                            currentMove = moves[gameMoves.get(count)];
                                                                                                                                                                                                                                                            secondsLeft = moveTimes[count];
                                                                                                                                                                                                                                                            timer.setText(moveTimes[count] + "s");
                                                                                                                                                                                                                                                            currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                                                                                                                                            currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                                                                                                                                            new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                                                public void onTick(long millisUntilFinished) {
                                                                                                                                                                                                                                                                    secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                                                                                                                                                    timer.setText(secondsLeft + "s");
                                                                                                                                                                                                                                                                    if(moveMade.length() > 0){
                                                                                                                                                                                                                                                                        cancel();
                                                                                                                                                                                                                                                                        onFinish();
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                                                public void onFinish() {
                                                                                                                                                                                                                                                                    playable = false;
                                                                                                                                                                                                                                                                    checkWin();
                                                                                                                                                                                                                                                                    if (gameContinues){

                                                                                                                                                                                                                                                                        new CountDownTimer(BREAK, 100) {
                                                                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                                                                            public void onTick(long millisUntilFinished) {

                                                                                                                                                                                                                                                                            }

                                                                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                                                                            public void onFinish() {
                                                                                                                                                                                                                                                                                playable = true;
                                                                                                                                                                                                                                                                                count++; //12
                                                                                                                                                                                                                                                                                moveMade = "";
                                                                                                                                                                                                                                                                                currentMove = moves[gameMoves.get(count)];
                                                                                                                                                                                                                                                                                secondsLeft = moveTimes[count];
                                                                                                                                                                                                                                                                                timer.setText(moveTimes[count] + "s");
                                                                                                                                                                                                                                                                                currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                                                                                                                                                                currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                                                                                                                                                                new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                                                                    public void onTick(long millisUntilFinished) {
                                                                                                                                                                                                                                                                                        secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                                                                                                                                                                        timer.setText(secondsLeft + "s");
                                                                                                                                                                                                                                                                                        if(moveMade.length() > 0){
                                                                                                                                                                                                                                                                                            cancel();
                                                                                                                                                                                                                                                                                            onFinish();
                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                    }

                                                                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                                                                    public void onFinish() {
                                                                                                                                                                                                                                                                                        playable = false;
                                                                                                                                                                                                                                                                                        checkWin();
                                                                                                                                                                                                                                                                                        if (gameContinues){

                                                                                                                                                                                                                                                                                            new CountDownTimer(BREAK, 100) {
                                                                                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                                                                                public void onTick(long millisUntilFinished) {

                                                                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                                                                                public void onFinish() {
                                                                                                                                                                                                                                                                                                    playable = true;
                                                                                                                                                                                                                                                                                                    count++; //13
                                                                                                                                                                                                                                                                                                    moveMade = "";
                                                                                                                                                                                                                                                                                                    currentMove = moves[gameMoves.get(count)];
                                                                                                                                                                                                                                                                                                    secondsLeft = moveTimes[count];
                                                                                                                                                                                                                                                                                                    timer.setText(moveTimes[count] + "s");
                                                                                                                                                                                                                                                                                                    currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                                                                                                                                                                                    currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                                                                                                                                                                                    new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                                                                                                        public void onTick(long millisUntilFinished) {
                                                                                                                                                                                                                                                                                                            secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                                                                                                                                                                                            timer.setText(secondsLeft + "s");
                                                                                                                                                                                                                                                                                                            if(moveMade.length() > 0){
                                                                                                                                                                                                                                                                                                                cancel();
                                                                                                                                                                                                                                                                                                                onFinish();
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }

                                                                                                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                                                                                                        public void onFinish() {
                                                                                                                                                                                                                                                                                                            playable = false;
                                                                                                                                                                                                                                                                                                            checkWin();
                                                                                                                                                                                                                                                                                                            if (gameContinues){

                                                                                                                                                                                                                                                                                                                new CountDownTimer(BREAK, 100) {
                                                                                                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                                                                                                    public void onTick(long millisUntilFinished) {

                                                                                                                                                                                                                                                                                                                    }

                                                                                                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                                                                                                    public void onFinish() {
                                                                                                                                                                                                                                                                                                                        playable = true;
                                                                                                                                                                                                                                                                                                                        count++; //14
                                                                                                                                                                                                                                                                                                                        moveMade = "";
                                                                                                                                                                                                                                                                                                                        currentMove = moves[gameMoves.get(count)];
                                                                                                                                                                                                                                                                                                                        secondsLeft = moveTimes[count];
                                                                                                                                                                                                                                                                                                                        timer.setText(moveTimes[count] + "s");
                                                                                                                                                                                                                                                                                                                        currentMoveIcon.setImageResource(moveImages[gameMoves.get(count)]);
                                                                                                                                                                                                                                                                                                                        currentMoveText.setText(moves[gameMoves.get(count)]);

                                                                                                                                                                                                                                                                                                                        new CountDownTimer(moveTimes[count] * 1000, 100) {
                                                                                                                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                                                                                                                            public void onTick(long millisUntilFinished) {
                                                                                                                                                                                                                                                                                                                                secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                                                                                                                                                                                                                timer.setText(secondsLeft + "s");
                                                                                                                                                                                                                                                                                                                                if(moveMade.length() > 0){
                                                                                                                                                                                                                                                                                                                                    cancel();
                                                                                                                                                                                                                                                                                                                                    onFinish();
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                            }

                                                                                                                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                                                                                                                            public void onFinish() {
                                                                                                                                                                                                                                                                                                                                playable = false;
                                                                                                                                                                                                                                                                                                                                checkWin();
                                                                                                                                                                                                                                                                                                                                if (gameContinues){

                                                                                                                                                                                                                                                                                                                                    new CountDownTimer(1500, 100) {
                                                                                                                                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                                                                                                                                        public void onTick(long millisUntilFinished) {

                                                                                                                                                                                                                                                                                                                                        }

                                                                                                                                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                                                                                                                                        public void onFinish() {
                                                                                                                                                                                                                                                                                                                                            enderman.setVisibility(View.GONE);
                                                                                                                                                                                                                                                                                                                                            winToast();
                                                                                                                                                                                                                                                                                                                                            new CountDownTimer(4000, 500) {
                                                                                                                                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                                                                                                                                public void onTick(long millisUntilFinished) {

                                                                                                                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                                                                                                                                public void onFinish() {
                                                                                                                                                                                                                                                                                                                                                    finish();
                                                                                                                                                                                                                                                                                                                                                    DrawView.endermanGameEnd(1);
                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                            }.start();
                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                    }.start();
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                        }.start();

                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                }.start();
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                    }.start();

                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }.start();
                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }.start();

                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                        }.start();
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                            }.start();

                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                    }.start();
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }.start();

                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }.start();
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }.start();

                                                                                                                                                                                                                }
                                                                                                                                                                                                            }.start();
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }.start();

                                                                                                                                                                                            }
                                                                                                                                                                                        }.start();
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }.start();

                                                                                                                                                                        }
                                                                                                                                                                    }.start();
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }.start();

                                                                                                                                                    }
                                                                                                                                                }.start();
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }.start();

                                                                                                                                }
                                                                                                                            }.start();
                                                                                                                        }
                                                                                                                    }
                                                                                                                }.start();

                                                                                                            }
                                                                                                        }.start();
                                                                                                    }
                                                                                                }
                                                                                            }.start();
                                                                                        }
                                                                                    }.start();
                                                                                }
                                                                            }
                                                                        }.start();
                                                                    }
                                                                }.start();
                                                            }
                                                        }
                                                    }.start();

                                                }
                                            }.start();
                                        }
                                    }
                                }.start();




                            }
                        }.start();
                    }
                }.start();
            }

        });

        swipeDetector = new SwipeListener(new SwipeActions() {
            @Override
            public void onSwipeUp() {
                if (playable){
                    moveMade = SWIPE_UP;
                    if (currentMove.equals(SWIPE_UP)) {
                        endermanHit();
                        moveToast("Successful hit!");
                    }
                }
            }

            @Override
            public void onSwipeDown() {
                if (playable){
                    moveMade = SWIPE_DOWN;
                    if (currentMove.equals(SWIPE_DOWN)) {
                        endermanHit();
                        moveToast("Successful hit!");
                    }
                }
            }

            @Override
            public void onSwipeLeft() {
                if (playable){
                    moveMade = SWIPE_LEFT;
                    if (currentMove.equals(SWIPE_LEFT)) {
                        endermanHit();
                        moveToast("Successful hit!");
                    }
                }
            }

            @Override
            public void onSwipeRight() {
                if (playable){
                    moveMade = SWIPE_RIGHT;
                    if (currentMove.equals(SWIPE_RIGHT)) {
                        endermanHit();
                        moveToast("Successful hit!");
                    }
                }
            }
        });

        gestureDetectorCompat = new GestureDetectorCompat(getApplicationContext(), swipeDetector);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            onVolumeDown();
        }
        else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            onVolumeUp();
        }
        return true;
    }

    public void winToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.enderman_toast,
                (ViewGroup) findViewById(R.id.enderman_toastLayout));

        TextView winOrLose = (TextView) layout.findViewById(R.id.enderman_label);

        TextView reward = (TextView) layout.findViewById(R.id.enderman_toastText);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void loseToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.enderman_toast,
                (ViewGroup) findViewById(R.id.enderman_toastLayout));

        TextView winOrLose = (TextView) layout.findViewById(R.id.enderman_label);
        winOrLose.setText("You lost! :(");
        TextView reward = (TextView) layout.findViewById(R.id.enderman_toastText);
        reward.setText("You won't receive a reward.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                onShake();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        shakeDetector.registerListener(mSensorListener, shakeDetector.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        shakeDetector.unregisterListener(mSensorListener);
        super.onPause();
    }

    public void onShake(){
        if (playable){
            moveMade = SHAKE;
            if (currentMove.equals(SHAKE)){
                endermanHit();
                moveToast("Successful hit!");
            }
        }
    }

    public void onVolumeUp(){
        if (playable){
            moveMade = VOLUME_UP;
            if (currentMove.equals(VOLUME_UP) ){
                endermanHit();
                moveToast("Successful hit!");
            }
        }
    }

    public void onVolumeDown(){
        if (playable){
            moveMade = VOLUME_DOWN;
            if (currentMove.equals(VOLUME_DOWN) ){
                endermanHit();
                moveToast("Successful hit!");
            }
        }
    }

    public void moveToast(String t){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.enderman_attack_toast,
                (ViewGroup) findViewById(R.id.enderman_attack_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.enderman_attack_toastText);
        text.setText(t);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 250);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void announcementToast(String t){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.enderman_attack_toast,
                (ViewGroup) findViewById(R.id.enderman_attack_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.enderman_attack_toastText);
        text.setText(t);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void endermanHit(){
        final Animation shakeEnderman;
        shakeEnderman = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_enderman);
        enderman.setImageResource(R.drawable.enderman_hurt);
        enderman.startAnimation(shakeEnderman);
        new CountDownTimer(400, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                enderman.setImageResource(R.drawable.enderman);
            }
        }.start();
    }

    public void checkWin(){
        if(moveMade.length() != 0 && !moveMade.equals(currentMove)){
            playable = false;
            gameContinues = false;
            announcementToast("Oh no! You " + moveToImproperMessage.get(moveMade) + " instead of " + moveToProperMessage.get(currentMove) + ".");
            new CountDownTimer(3500, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    loseToast();
                    new CountDownTimer(3000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            finish();
                                                                DrawView.endermanGameEnd(0);
                        }
                    }.start();
                }
            }.start();
        }
        else if (moveMade.length() == 0){
            playable = false;
            gameContinues = false;
            announcementToast("Oh no! You ran out of time.");
            new CountDownTimer(3500, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    loseToast();
                    new CountDownTimer(3000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            finish();
                            DrawView.endermanGameEnd(0);
                        }
                    }.start();
                }
            }.start();
        }

    }

}