package com.leejordan.minecraftboardgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DrawView extends View {
    Paint paint = new Paint();

    Player player;
    Bitmap cobblestone, chest, creeper, zombie, minigame, endPortal, enderDragon, diamondOre, steveFace, alexFace;
    Space space1, space2, space3, space4, space5, space6, space7, space8, space9, space10, space11, space12, space13, space14, space15, space16, space17, space18, space19, space20, space21, space22, space23, space24, space25, space26, space27, space28, space29, space30, space31, space32, space33, space34, space35, space36;

    //constants
    private static final String COBBLESTONE = "cobblestone";
    private static final String CHEST = "chest";
    private static final String ZOMBIE = "zombie";
    private static final String CREEPER = "creeper";
    private static final String MINIGAME = "minigame";
    private static final String ENDER_DRAGON = "ender_dragon";
    private static final String END_PORTAL = "end_portal";
    private static final String DIAMOND_ORE = "diamond_ore";

    //prizes

    public static HashMap<Integer, Space> idToSpace;
    private HashMap<String, String> displayWeapons;

    private int count;
    static int moves;
    static boolean myTurn;
    static boolean moving;

    static boolean weaponChosen;
    static String weaponChoice;
    static boolean readyForWeapon;

    static GameActivity game;
    static int enderPearlPrize;
    static String swordPrize;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        float space_length = getWidth() * 0.117f;
        float player_length = getWidth() * 0.095f;
        float player_gap = getWidth() * 0.011f;

        weaponChosen = false;
        readyForWeapon = false;
        weaponChoice = "";

        displayWeapons = new HashMap<>();
        displayWeapons.put("WOODEN_SWORD", "Wooden Sword");
        displayWeapons.put("IRON_SWORD", "Iron Sword");
        displayWeapons.put("GOLD_SWORD", "Gold Sword");
        displayWeapons.put("DIAMOND_SWORD", "Diamond Sword");

        count = 0;
        myTurn = false;
        moving = false;
        game = (GameActivity) getContext();

        //Bitmaps
        cobblestone = BitmapFactory.decodeResource(getResources(), R.drawable.cobblestonespace);
        chest = BitmapFactory.decodeResource(getResources(), R.drawable.chest);
        creeper = BitmapFactory.decodeResource(getResources(), R.drawable.creeper);
        zombie = BitmapFactory.decodeResource(getResources(), R.drawable.zombie);
        minigame = BitmapFactory.decodeResource(getResources(), R.drawable.minigame_space);
        enderDragon = BitmapFactory.decodeResource(getResources(), R.drawable.enderdragon_face);
        endPortal = BitmapFactory.decodeResource(getResources(), R.drawable.endportal);
        diamondOre = BitmapFactory.decodeResource(getResources(), R.drawable.diamond_ore);
        steveFace =  BitmapFactory.decodeResource(getResources(), R.drawable.steve_icon);
        alexFace =  BitmapFactory.decodeResource(getResources(), R.drawable.alex_icon);

        //Spaces

        //Space 1 - Cobblestone
        space1 = new Space(getWidth() * 0.14f,  getHeight() - space_length, space_length , 0, COBBLESTONE, 1);
        space1.setBitmap(cobblestone);

        //Space 2 - Cobblestone
        space2 = new Space(getWidth() * 0.14f,  getHeight() - (2*space_length), space_length , 0, COBBLESTONE, 2);
        space2.setBitmap(cobblestone);

        //Space3 - Zombie
        space3 = new Space(getWidth() * 0.14f,  getHeight() - (3*space_length), space_length , -1, ZOMBIE, 3);
        space3.setBitmap(zombie);

        //Space4 - Chest
        space4 = new Space(getWidth() * 0.14f + space_length,  getHeight() - (3*space_length), space_length , 0, CHEST, 4);
        space4.setBitmap(chest);

        //Space5 - Cobblestone
        space5 = new Space(getWidth() * 0.14f + (2*space_length),  getHeight() - (3*space_length), space_length , 0, COBBLESTONE, 5);
        space5.setBitmap(cobblestone);

        //Space6 - Minigame
        space6 = new Space(getWidth() * 0.14f + (3*space_length),  getHeight() - (3*space_length), space_length , 0, MINIGAME, 6);
        space6.setBitmap(minigame);

        //Space7 - Cobblestone
        space7 = new Space(getWidth() * 0.14f + (4*space_length),  getHeight() - (3*space_length), space_length , 0, COBBLESTONE, 7);
        space7.setBitmap(cobblestone);

        //Space8 - Cobblestone
        space8 = new Space(getWidth() * 0.14f + (5*space_length),  getHeight() - (3*space_length), space_length , 0, COBBLESTONE, 8);
        space8.setBitmap(cobblestone);

        //Space 9 - Zombie
        space9 = new Space(getWidth() * 0.14f + (5*space_length),  getHeight() - (4*space_length), space_length , -1, ZOMBIE, 9);
        space9.setBitmap(zombie);

        //Space 10 - Diamond Ore
        space10 = new Space(getWidth() * 0.14f + (5*space_length),  getHeight() - (5*space_length), space_length , 3, DIAMOND_ORE, 10);
        space10.setBitmap(diamondOre);

        //Space 11 - Cobblestone
        space11 = new Space(getWidth() * 0.14f + (5*space_length),  getHeight() - (6*space_length), space_length , 0, COBBLESTONE, 11);
        space11.setBitmap(cobblestone);

        //Space 12 - Cobblestone
        space12 = new Space(getWidth() * 0.14f + (4*space_length),  getHeight() - (6*space_length), space_length , 0, COBBLESTONE, 12);

        //Space 13 - Minigame
        space13 = new Space(getWidth() * 0.14f + (3*space_length),  getHeight() - (6*space_length), space_length , 0, MINIGAME, 13);

        //Space 14 - Cobblestone
        space14 = new Space(getWidth() * 0.14f + (3*space_length),  getHeight() - (5*space_length), space_length , 0, COBBLESTONE, 14);

        //Space15 - Diamond Ore
        space15 = new Space(getWidth() * 0.14f + (2*space_length),  getHeight() - (5*space_length), space_length , 3, DIAMOND_ORE, 15);

        //Space16 - Creeper
        space16 = new Space(getWidth() * 0.14f + (space_length),  getHeight() - (5*space_length), space_length , -3, CREEPER, 16);

        //Space17 - Cobblestone
        space17 = new Space(getWidth() * 0.14f ,  getHeight() - (5*space_length), space_length , 0, COBBLESTONE, 17);

        //Space18 - Chest
        space18 = new Space(getWidth() * 0.14f ,  getHeight() - (6*space_length), space_length , 0, CHEST, 18);

        //Space19 - Cobblestone
        space19 = new Space(getWidth() * 0.14f ,  getHeight() - (7*space_length), space_length , 0, COBBLESTONE, 19);

        //Space20 - Minigame
        space20 = new Space(getWidth() * 0.14f ,  getHeight() - (8*space_length), space_length , 0, MINIGAME, 20);

        //Space21 - Zombie
        space21 = new Space(getWidth() * 0.14f + (space_length),  getHeight() - (8*space_length), space_length , -1, ZOMBIE, 21);

        //Space22 - Cobblestone
        space22 = new Space(getWidth() * 0.14f + (2*space_length),  getHeight() - (8*space_length), space_length , 0, COBBLESTONE, 22);

        //Space23 - Chest
        space23 = new Space(getWidth() * 0.14f + (3*space_length),  getHeight() - (8*space_length), space_length , 0, CHEST, 23);

        //Space24 - Minigame
        space24 = new Space(getWidth() * 0.14f + (4*space_length),  getHeight() - (8*space_length), space_length , 0, MINIGAME, 24);

        //Space25 - Creeper
        space25 = new Space(getWidth() * 0.14f + (5*space_length),  getHeight() - (8*space_length), space_length , -3, CREEPER, 25);

        //Space26 - Cobblestone
        space26 = new Space(getWidth() * 0.14f + (5*space_length),  getHeight() - (9*space_length), space_length , 0, COBBLESTONE, 26);

        //Space27 - Chest
        space27 = new Space(getWidth() * 0.14f + (5*space_length),  getHeight() - (10*space_length), space_length , 0, CHEST, 27);

        //Space28 - Diamond Ore
        space28 = new Space(getWidth() * 0.14f + (5*space_length),  getHeight() - (11*space_length), space_length , 3, DIAMOND_ORE, 28);

        //Space29 - Cobblestone
        space29 = new Space(getWidth() * 0.14f + (4*space_length),  getHeight() - (11*space_length), space_length , 0, COBBLESTONE, 29);

        //Space30 - Cobblestone
        space30 = new Space(getWidth() * 0.14f + (3*space_length),  getHeight() - (11*space_length), space_length , 0, COBBLESTONE, 30);

        //Space31 - Minigame
        space31 = new Space(getWidth() * 0.14f + (3*space_length),  getHeight() - (10*space_length), space_length , 0, MINIGAME, 31);

        //Space32 - Cobblestone
        space32 = new Space(getWidth() * 0.14f + (2*space_length),  getHeight() - (10*space_length), space_length , 0, COBBLESTONE, 32);

        //Space33 - Creeper
        space33 = new Space(getWidth() * 0.14f + (space_length),  getHeight() - (10*space_length), space_length , -3, CREEPER, 33);

        //Space34 - Cobblestone
        space34 = new Space(getWidth() * 0.14f,  getHeight() - (10*space_length), space_length , 0, COBBLESTONE, 34);

        //Space35 - End Portal
        space35 = new Space(getWidth() * 0.14f,  getHeight() - (11*space_length), space_length , 0, END_PORTAL, 35);

        //Space36 - Ender Dragon
        space36 = new Space(getWidth() * 0.14f,  getHeight() - (12*space_length), space_length , 0, ENDER_DRAGON, 36);

        idToSpace = new HashMap<>();
        idToSpace.put(1, space1);
        idToSpace.put(2, space2);
        idToSpace.put(3, space3);
        idToSpace.put(4, space4);
        idToSpace.put(5, space5);
        idToSpace.put(6, space6);
        idToSpace.put(7, space7);
        idToSpace.put(8, space8);
        idToSpace.put(9, space9);
        idToSpace.put(10, space10);
        idToSpace.put(11, space11);
        idToSpace.put(12, space12);
        idToSpace.put(13, space13);
        idToSpace.put(14, space14);
        idToSpace.put(15, space15);
        idToSpace.put(16, space16);
        idToSpace.put(17, space17);
        idToSpace.put(18, space18);
        idToSpace.put(19, space19);
        idToSpace.put(20, space20);
        idToSpace.put(21, space21);
        idToSpace.put(22, space22);
        idToSpace.put(23, space23);
        idToSpace.put(24, space24);
        idToSpace.put(25, space25);
        idToSpace.put(26, space26);
        idToSpace.put(27, space27);
        idToSpace.put(28, space28);
        idToSpace.put(29, space29);
        idToSpace.put(30, space30);
        idToSpace.put(31, space31);
        idToSpace.put(32, space32);
        idToSpace.put(33, space33);
        idToSpace.put(34, space34);
        idToSpace.put(35, space35);
        idToSpace.put(36, space36);



        space12.setBitmap(cobblestone);
        space13.setBitmap(minigame);
        space14.setBitmap(cobblestone);

        space15.setBitmap(diamondOre);
        space16.setBitmap(creeper);
        space17.setBitmap(cobblestone);
        space18.setBitmap(chest);
        space19.setBitmap(cobblestone);
        space20.setBitmap(minigame);
        space21.setBitmap(zombie);
        space22.setBitmap(cobblestone);
        space23.setBitmap(chest);
        space24.setBitmap(minigame);
        space25.setBitmap(creeper);
        space26.setBitmap(cobblestone);
        space27.setBitmap(chest);
        space28.setBitmap(diamondOre);
        space29.setBitmap(cobblestone);
        space30.setBitmap(cobblestone);
        space31.setBitmap(minigame);
        space32.setBitmap(cobblestone);
        space33.setBitmap(creeper);
        space34.setBitmap(cobblestone);
        space35.setBitmap(endPortal);
        space36.setBitmap(enderDragon);

        //Player
        if(MainActivity.playerType == 1){
            player = new Player(space1, player_gap, player_length, steveFace, 1, 1);
        }
        else{
            player = new Player(space1, player_gap, player_length, alexFace, 2, 1);
        }


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawAllSpaces(canvas);

        player.draw(canvas);

        if(weaponChosen == true){
            game.toast("You chose: " + displayWeapons.get(weaponChoice));
            weaponChosen = false;
            new CountDownTimer(3500, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    player.moveTo(36);

                    new CountDownTimer(1000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            game.endToast();
                            new CountDownTimer(4000, 500) {
                                @Override
                                public void onTick(long millisUntilFinished) {

                                }

                                @Override
                                public void onFinish() {
                                    game.switchToDragon();
                                }
                            }.start();
                        }
                    }.start();
                }
            }.start();
        }
        invalidate();

    }

    private void drawAllSpaces(Canvas canvas) {
        space1.draw(canvas);
        space2.draw(canvas);
        space3.draw(canvas);
        space4.draw(canvas);
        space5.draw(canvas);
        space6.draw(canvas);
        space7.draw(canvas);
        space8.draw(canvas);
        space9.draw(canvas);
        space10.draw(canvas);
        space11.draw(canvas);
        space12.draw(canvas);
        space13.draw(canvas);
        space14.draw(canvas);
        space15.draw(canvas);
        space16.draw(canvas);
        space17.draw(canvas);
        space18.draw(canvas);
        space19.draw(canvas);
        space20.draw(canvas);
        space21.draw(canvas);
        space22.draw(canvas);
        space23.draw(canvas);
        space24.draw(canvas);
        space25.draw(canvas);
        space26.draw(canvas);
        space27.draw(canvas);
        space28.draw(canvas);
        space29.draw(canvas);
        space30.draw(canvas);
        space31.draw(canvas);
        space32.draw(canvas);
        space33.draw(canvas);
        space34.draw(canvas);
        space35.draw(canvas);
        space36.draw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if(space36.contains(event.getX(), event.getY())){
//                DrawViewInventory.addItem("ENDER_PEARL", 12);
//                DrawViewInventory.addItem("WOODEN_SWORD", 1);
//               DrawViewInventory.addItem("IRON_SWORD", 1);
//                player.moveTo(35);
//                enderPortalSpaceAction(space35);

            }
            Log.i("TEST", "count: " + count);
            Log.i("TEST", "moves: " + moves);
            if(myTurn == true && moving == false){
                if (count < moves) {
                    game.moveToast(moves - count);
                    player.moveTo(player.getCurrentSpaceID() + 1);
                    if(player.getCurrentSpaceID() == 35){
                        moving = true;
                        enderPortalSpaceAction(space35);
                    }
                    count++;
                    if (count == moves){
                        myTurn = false;
                        count = 0;

                        String type = idToSpace.get(player.getCurrentSpaceID()).getType();

                        if (type.equals(ZOMBIE) || type.equals(DIAMOND_ORE) || type.equals(CREEPER)) {
                            spaceAction(idToSpace.get(player.getCurrentSpaceID()));
                        }
                        if (type.equals(CHEST)){
                            moving = true;
                            chestSpaceAction(idToSpace.get(player.getCurrentSpaceID()));
                        }
                        if (type.equals(MINIGAME)){
                            moving = true;
                            minigameSpaceAction(idToSpace.get(player.getCurrentSpaceID()));
                        }
                        if (type.equals(END_PORTAL)){
                            moving = true;
                            enderPortalSpaceAction(idToSpace.get(player.getCurrentSpaceID()));
                        }

                        //see if it lands on minigame or chest space

                    }
                }
                else{
                    count = 0;
                    myTurn = false;
                }
            }
        }
        return false;
    }

    public void spaceAction(final Space space){
        moving = true;
        new CountDownTimer(1200, 500){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (space.getType().equals(ZOMBIE)){
                    game.zombieToast();
                }
                else if (space.getType().equals(CREEPER)){
                    game.creeperToast();
                }
                else if (space.getType().equals(DIAMOND_ORE)){
                    game.diamondOreToast();
                }
                if (space.getType().equals(ZOMBIE) || space.getType().equals(CREEPER)|| space.getType().equals(DIAMOND_ORE)){
                    new CountDownTimer(3500, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            moveSpaces(space.getSpaceChange());


                            //see if it landed on a certain space
                            String type = idToSpace.get(player.getCurrentSpaceID()).getType();
                            if(type.equals(CHEST)){
                                chestSpaceAction(idToSpace.get(player.getCurrentSpaceID()));
                            }
                            else if(type.equals(MINIGAME)){
                                minigameSpaceAction(idToSpace.get(player.getCurrentSpaceID()));
                            }
                            else
                            {
                                moving = false;
                            }

//                            if(type.equals(MINIGAME)){
//
//                            }
//                            else if(type.equals(CHEST)){
//
//                            }
//                            else if(type.equals(END_PORTAL)){
//
//                            }
//                            else if(type.equals(ENDER_DRAGON)){
//
//                            }
//                            else{
//                                moving = false;
//                            }
                        }
                    }.start();

                }
            }
        }.start();

    }

    public void moveSpaces(final int i){
        int moves = Math.abs(i);
        for(int k = 0; k < moves;k++){
            if (i > 0){
                player.moveTo(player.getCurrentSpaceID() + 1);
            }
            else if (i < 0){
                player.moveTo(player.getCurrentSpaceID() - 1);
            }
        }

    }

    public void chestSpaceAction(final Space s){
        if (s.isVisited()){
            game.visitedToast();
            new CountDownTimer(3000, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    moving = false;
                }
            }.start();
        }
        else{
            new CountDownTimer(1200, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    if(s.getId() == 4){
                        game.chestToast(2, 1, "WOODEN_SWORD");
                        new CountDownTimer(1500, 500) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                DrawViewInventory.addItem("ENDER_PEARL", 2);
                                DrawViewInventory.addItem("WOODEN_SWORD", 1);
                            }
                        }.start();
                    }
                    else if(s.getId() == 18){
                        game.chestToast(3, 1, "IRON_SWORD");
                        new CountDownTimer(1500, 500) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                DrawViewInventory.addItem("ENDER_PEARL", 3);
                                DrawViewInventory.addItem("IRON_SWORD", 1);
                            }
                        }.start();
                    }
                    else if(s.getId() == 23){
                        game.chestToast(4, 0, "NONE");
                        new CountDownTimer(1500, 500) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                DrawViewInventory.addItem("ENDER_PEARL", 4);
                            }
                        }.start();
                    }
                    else if(s.getId() == 27){
                        game.chestToast(3, 1, "IRON_SWORD");
                        new CountDownTimer(1500, 500) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                DrawViewInventory.addItem("ENDER_PEARL", 3);
                                DrawViewInventory.addItem("IRON_SWORD", 1);
                            }
                        }.start();
                    }

                    new CountDownTimer(3500, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            moving = false;
                            s.setVisited(true);
                        }
                    }.start();


                }
            }.start();
        }


    }

    public void minigameSpaceAction(final Space s){
        //end with moving == false
        new CountDownTimer(1000, 500){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                //decide minigame and do a toast
                final int gameID = (int)(Math.random() * 3) + 1;
                switch (gameID){
                    case 1:
                        game.minigameToast(1);
                        break;
                    case 2:
                        game.minigameToast(2);
                        break;
                    case 3:
                        game.minigameToast(3);
                        break;
                }

                int spaceID = s.getId();

                switch (spaceID){
                    case 6:
                        enderPearlPrize = 3;
                        swordPrize = "WOODEN_SWORD";
                        break;
                    case 13:
                        enderPearlPrize = 2;
                        swordPrize = "GOLD_SWORD";
                        break;
                    case 20:
                        enderPearlPrize = 3;
                        swordPrize = "GOLD_SWORD";
                        break;
                    case 24:
                        enderPearlPrize = 2;
                        swordPrize = "IRON_SWORD";
                        break;
                    case 31:
                        enderPearlPrize = 4;
                        swordPrize = "DIAMOND_SWORD";
                        break;
                }

                new CountDownTimer(3000, 500){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        //open Activity based on id
                        if(gameID == 1)
                            game.switchToGame1();
                        else if(gameID == 2)
                            game.switchToGame2();
                        else if(gameID == 3)
                            game.switchToGame3();
                    }
                }.start();

                //after toast, open up minigame fragment
                //wait till fragment ends/game ends (probably use boolean)
                //check results
                //give out rewards according to the space ID
                //end with moving == false

            }
        }.start();

    }

    public void enderPortalSpaceAction(final Space s){
        new CountDownTimer(2000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (DrawViewInventory.enderPearls() >= 12){
                    game.portalToast("You have all the Ender Pearls you need to travel to the End.");
                    new CountDownTimer(3500, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            game.portalToast("However, before you travel to fight the Ender Dragon...");
                            new CountDownTimer(2500, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {

                                }

                                @Override
                                public void onFinish() {
                                    game.portalToast("You must bring a weapon with you. ");
                                    new CountDownTimer(2500, 1000){

                                        @Override
                                        public void onTick(long millisUntilFinished) {

                                        }

                                        @Override
                                        public void onFinish() {
                                            if (DrawViewInventory.hasSword()){
                                                game.portalToast("In your inventory, tap the weapon of your choice in order to advance.");
                                                new CountDownTimer(3500, 1000) {
                                                    @Override
                                                    public void onTick(long millisUntilFinished) {

                                                    }

                                                    @Override
                                                    public void onFinish() {
                                                        game.portalToast("Choose wisely.");
                                                        readyForWeapon = true;
                                                    }
                                                }.start();
                                            }
                                            else{
                                                game.portalToast("However, you do not have a weapon. You will have to use your fists.");
                                                new CountDownTimer(3500, 1000) {
                                                    @Override
                                                    public void onTick(long millisUntilFinished) {

                                                    }

                                                    @Override
                                                    public void onFinish() {
                                                        game.portalToast("This will be difficult, but good luck anyway.");
                                                        new CountDownTimer(3500, 1000) {
                                                            @Override
                                                            public void onTick(long millisUntilFinished) {

                                                            }

                                                            @Override
                                                            public void onFinish() {
                                                                weaponChoice = "NONE";
                                                                game.endToast();
                                                                new CountDownTimer(3500, 500) {
                                                                    @Override
                                                                    public void onTick(long millisUntilFinished) {

                                                                    }

                                                                    @Override
                                                                    public void onFinish() {
                                                                        game.switchToDragon();
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
                else{
                    game.portalToast("Unfortunately, you do not have all the Ender Pearls you need to advance.");
                    new CountDownTimer(4000, 500){

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            game.switchToLose();
                        }
                    }.start();
                }
            }
        }.start();
    }

    public static void miningGameEnd(int winOrLose){
       if (winOrLose == 0){
            game.toast("You can continue playing. Roll the dice.");
           moving = false;
       }
       else{
           game.prizeToast(enderPearlPrize, swordPrize);
           new CountDownTimer(1500, 500) {
               @Override
               public void onTick(long millisUntilFinished) {

               }

               @Override
               public void onFinish() {
                   DrawViewInventory.addItem("ENDER_PEARL", enderPearlPrize);
                   DrawViewInventory.addItem(swordPrize, 1);
                   moving = false;
               }
           }.start();
       }
    }


    public static void diamondGameEnd(int winOrLose){
        if (winOrLose == 0){
            game.diamondLoseToast();
            moving = false;
        }
        else{
            game.prizeToast(enderPearlPrize, swordPrize);
            new CountDownTimer(1500, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    DrawViewInventory.addItem("ENDER_PEARL", enderPearlPrize);
                    DrawViewInventory.addItem(swordPrize, 1);
                    moving = false;
                }
            }.start();
        }
    }
    public static void endermanGameEnd(int winOrLose){
        if (winOrLose == 0){
            moving = false;
            new CountDownTimer(1000, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    game.toast("You can continue playing. Roll the dice.");
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
                    game.prizeToast(enderPearlPrize, swordPrize);
                    new CountDownTimer(1500, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            DrawViewInventory.addItem("ENDER_PEARL", enderPearlPrize);
                            DrawViewInventory.addItem(swordPrize, 1);
                            moving = false;
                        }
                    }.start();
                }
            }.start();
        }
    }

}
