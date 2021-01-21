package com.leejordan.minecraftboardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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

public class DiamondActivity extends AppCompatActivity {

    private ImageView main;
    private Button startGame;
    private TextView timer, diamondCount;
    private ImageView diamond1A, diamond1B, diamond1C, diamond2A, diamond2B, diamond2C, diamond3A, diamond3B, diamond3C;
    boolean game1, game2, game3;
    boolean clicked1A, clicked1B, clicked1C, clicked2A, clicked2B, clicked2C, clicked3A, clicked3B, clicked3C;
    private static final int BLOCKS = 3;
    private static final int TIME = 5;
    private static final int ROUND3_TIME = 10;
    private int secondsLeft = TIME;
    private int blocksFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diamond);

        game1 = false;
        game2 = false;
        game3 = false;
        clicked1A = false;
        clicked1B= false;
        clicked1C= false;
        clicked2A= false;
        clicked2B= false;
        clicked2C= false;
        clicked3A= false;
        clicked3B= false;
clicked3C= false;

        blocksFound = 0;

        main = findViewById(R.id.mainWindow);
        startGame = findViewById(R.id.startDiamondGame);
        timer = findViewById(R.id.diamondCountdown);
        diamondCount = findViewById(R.id.diamondCount);

        diamond1A = findViewById(R.id.diamond1A);
        diamond1B = findViewById(R.id.diamond1B);
        diamond1C = findViewById(R.id.diamond1C);
        diamond2A = findViewById(R.id.diamond2A);
        diamond2B = findViewById(R.id.diamond2B);
        diamond2C = findViewById(R.id.diamond2C);
        diamond3A = findViewById(R.id.diamond3A);
        diamond3B = findViewById(R.id.diamond3B);
        diamond3C = findViewById(R.id.diamond3C_chest);

        timer.setVisibility(View.GONE);
        diamondCount.setVisibility(View.GONE);
        diamond1A.setVisibility(View.GONE);
        diamond1B.setVisibility(View.GONE);
        diamond1C.setVisibility(View.GONE);
        diamond2A.setVisibility(View.GONE);
        diamond2B.setVisibility(View.GONE);
        diamond2C.setVisibility(View.GONE);
        diamond3A.setVisibility(View.GONE);
        diamond3B.setVisibility(View.GONE);
        diamond3C.setVisibility(View.GONE);

        diamond1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game1 && !clicked1A) {
                    blocksFound += 1;
                    clicked1A = true;
                }
            }
        });

        diamond1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game1 && !clicked1B) {
                    blocksFound += 1;
                    clicked1B = true;
                }
            }
        });

        diamond1C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game1 && !clicked1C) {
                    blocksFound += 1;
                    clicked1C = true;
                }
            }
        });

        diamond2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game2 && !clicked2A) {
                    blocksFound += 1;
                    clicked2A = true;
                }
            }
        });

        diamond2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game2 && !clicked2B) {
                    blocksFound += 1;
                    clicked2B = true;
                }
            }
        });

        diamond2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game2 && !clicked2C) {
                    blocksFound += 1;
                    clicked2C = true;
                }
            }
        });

        diamond3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game3 && !clicked3A) {
                    blocksFound += 1;
                    clicked3A = true;
                }
            }
        });

        diamond3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game3 && !clicked3B) {
                    blocksFound += 1;
                    clicked3B = true;
                }
            }
        });

        diamond3C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game3 && !clicked3C) {
                    blocksFound += 1;
                    clicked3C = true;
                    chestToast();
                }
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setImageResource(R.drawable.mine1);
                startGame.setVisibility(View.GONE);
                timer.setVisibility(View.VISIBLE);
                diamondCount.setVisibility(View.VISIBLE);
                diamond1A.setVisibility(View.VISIBLE);
                diamond1B.setVisibility(View.VISIBLE);
                diamond1C.setVisibility(View.VISIBLE);


                new CountDownTimer(1000, 500) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        game1 = true;
                        new CountDownTimer(TIME * 1000, 100) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                secondsLeft = (int) (millisUntilFinished / 1000);
                                timer.setText(secondsLeft + "s");

                                diamondCount.setText("" + blocksFound);

                                if (blocksFound >= BLOCKS) {
                                    cancel();
                                    onFinish();
                                }
                            }

                            @Override
                            public void onFinish() {
                                game1 = false;

                                new CountDownTimer(1000, 500) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        if (blocksFound < BLOCKS) {
                                            finish();
                                            DrawView.diamondGameEnd(0);
                                        } else {
                                            blocksFound = 0;
                                            announcementToast();
                                            new CountDownTimer(3500, 500) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {

                                                }

                                                @Override
                                                public void onFinish() {
                                                    main.setImageResource(R.drawable.mine2);
                                                    diamond1A.setVisibility(View.GONE);
                                                    diamond1B.setVisibility(View.GONE);
                                                    diamond1C.setVisibility(View.GONE);
                                                    diamond2A.setVisibility(View.VISIBLE);
                                                    diamond2B.setVisibility(View.VISIBLE);
                                                    diamond2C.setVisibility(View.VISIBLE);
                                                    secondsLeft = TIME;
                                                    timer.setText(TIME + "s");
                                                    diamondCount.setText("" + blocksFound);
                                                    new CountDownTimer(1000, 500) {
                                                        @Override
                                                        public void onTick(long millisUntilFinished) {

                                                        }

                                                        @Override
                                                        public void onFinish() {
                                                            game2 = true;
                                                            new CountDownTimer(TIME * 1000, 100) {
                                                                @Override
                                                                public void onTick(long millisUntilFinished) {
                                                                    secondsLeft = (int) (millisUntilFinished / 1000);
                                                                    timer.setText(secondsLeft + "s");

                                                                    diamondCount.setText("" + blocksFound);

                                                                    if (blocksFound >= BLOCKS) {
                                                                        cancel();
                                                                        onFinish();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onFinish() {
                                                                    game2 = false;

                                                                    new CountDownTimer(1000, 500) {
                                                                        @Override
                                                                        public void onTick(long millisUntilFinished) {

                                                                        }

                                                                        @Override
                                                                        public void onFinish() {
                                                                            if (blocksFound < BLOCKS) {
                                                                                finish();
                                                                                DrawView.diamondGameEnd(0);
                                                                            } else {
                                                                                blocksFound = 0;
                                                                                announcementToast();
                                                                                new CountDownTimer(3500, 500) {
                                                                                    @Override
                                                                                    public void onTick(long millisUntilFinished) {

                                                                                    }

                                                                                    @Override
                                                                                    public void onFinish() {
                                                                                        announcementToast("You've probably gotten used to this by now.");
                                                                                        new CountDownTimer(2500, 500) {
                                                                                            @Override
                                                                                            public void onTick(long millisUntilFinished) {

                                                                                            }

                                                                                            @Override
                                                                                            public void onFinish() {
                                                                                                announcementToast("For this last one, one diamond won't be in its usual location.");
                                                                                                new CountDownTimer(2500, 500) {
                                                                                                    @Override
                                                                                                    public void onTick(long millisUntilFinished) {

                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onFinish() {
                                                                                                        announcementToast("So, think intuitively. Where else could you find a diamond?");
                                                                                                        new CountDownTimer(2500, 500) {
                                                                                                            @Override
                                                                                                            public void onTick(long millisUntilFinished) {

                                                                                                            }

                                                                                                            @Override
                                                                                                            public void onFinish() {
                                                                                                                announcementToast("For the last round, you have 10 seconds instead. Good luck!");
                                                                                                                new CountDownTimer(1000, 500) {
                                                                                                                    @Override
                                                                                                                    public void onTick(long millisUntilFinished) {

                                                                                                                    }

                                                                                                                    @Override
                                                                                                                    public void onFinish() {
                                                                                                                        main.setImageResource(R.drawable.mine3);
                                                                                                                        diamond2A.setVisibility(View.GONE);
                                                                                                                        diamond2B.setVisibility(View.GONE);
                                                                                                                        diamond2C.setVisibility(View.GONE);
                                                                                                                        diamond3A.setVisibility(View.VISIBLE);
                                                                                                                        diamond3B.setVisibility(View.VISIBLE);
                                                                                                                        diamond3C.setVisibility(View.VISIBLE);
                                                                                                                        secondsLeft = TIME;
                                                                                                                        timer.setText(ROUND3_TIME + "s");
                                                                                                                        diamondCount.setText("" + blocksFound);
                                                                                                                        new CountDownTimer(1000, 500) {
                                                                                                                            @Override
                                                                                                                            public void onTick(long millisUntilFinished) {

                                                                                                                            }

                                                                                                                            @Override
                                                                                                                            public void onFinish() {
                                                                                                                                game3 = true;
                                                                                                                                new CountDownTimer(ROUND3_TIME * 1000, 100) {
                                                                                                                                    @Override
                                                                                                                                    public void onTick(long millisUntilFinished) {
                                                                                                                                        secondsLeft = (int) (millisUntilFinished / 1000);
                                                                                                                                        timer.setText(secondsLeft + "s");

                                                                                                                                        diamondCount.setText("" + blocksFound);

                                                                                                                                        if (blocksFound >= BLOCKS) {
                                                                                                                                            cancel();
                                                                                                                                            onFinish();
                                                                                                                                        }
                                                                                                                                    }

                                                                                                                                    @Override
                                                                                                                                    public void onFinish() {
                                                                                                                                        game3 = false;
                                                                                                                                        new CountDownTimer(2500, 500) {
                                                                                                                                            @Override
                                                                                                                                            public void onTick(long millisUntilFinished) {

                                                                                                                                            }

                                                                                                                                            @Override
                                                                                                                                            public void onFinish() {
                                                                                                                                                if (blocksFound < BLOCKS) {
                                                                                                                                                    finish();
                                                                                                                                                    DrawView.diamondGameEnd(0);
                                                                                                                                                } else {
                                                                                                                                                    winToast();
                                                                                                                                                    new CountDownTimer(2000, 500) {
                                                                                                                                                        @Override
                                                                                                                                                        public void onTick(long millisUntilFinished) {

                                                                                                                                                        }

                                                                                                                                                        @Override
                                                                                                                                                        public void onFinish() {
                                                                                                                                                            finish();
                                                                                                                                                            DrawView.miningGameEnd(1);
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
                                                                                                                }.start();
                                                                                                            }
                                                                                                        }.start();
                                                                                                    }
                                                                                                }.start();
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

    }

    public void diamondOreToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.ore_toast,
                (ViewGroup) findViewById(R.id.ore_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.ore_toastText);
        text.setText("You found a diamond!");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 600);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void chestToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.visited_toast,
                (ViewGroup) findViewById(R.id.visited_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.visited_toastText);
        text.setTextColor(Color.CYAN);
        text.setText("You found a diamond in the chest!");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void announcementToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.ore_toast,
                (ViewGroup) findViewById(R.id.ore_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.ore_toastText);
        text.setText("You got all 3 diamonds! Let's move on.");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void announcementToast(String s) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.ore_toast,
                (ViewGroup) findViewById(R.id.ore_toastLayout));

        TextView text = (TextView) layout.findViewById(R.id.ore_toastText);
        text.setText(s);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void winToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.diamond_toast,
                (ViewGroup) findViewById(R.id.diamond_toastLayout));

        TextView winOrLose = (TextView) layout.findViewById(R.id.diamond_label);

        TextView reward = (TextView) layout.findViewById(R.id.diamond_toastText);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}