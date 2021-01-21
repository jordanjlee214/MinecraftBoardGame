package com.leejordan.minecraftboardgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DrawViewMine extends View {

    private static final int BLOCKS = 10;
    static int currentBlock;
    boolean playable;
    int blocksMined;
    private int count;

    Bitmap stone1, stone2, stone3, stone4, stone5, stone6, stone7, stone8, stone9, dirt1, dirt2, dirt3, dirt4, dirt5, dirt6, dirt7, dirt8, dirt9, redstone1, redstone2, redstone3, redstone4, redstone5, redstone6, redstone7, redstone8, redstone9, gold1, gold2, gold3, gold4, gold5, gold6, gold7, gold8, gold9, diamond1, diamond2, diamond3, diamond4, diamond5, diamond6, diamond7, diamond8, diamond9, iron1, iron2, iron3, iron4, iron5, iron6, iron7, iron8, iron9, emerald1, emerald2, emerald3, emerald4, emerald5, emerald6, emerald7, emerald8, emerald9;
    Block[] blocks = new Block[BLOCKS];

//    Bitmap stone1 = BitmapFactory.decodeResource(getResources(), R.drawable.stone1);
//    Bitmap stone2 = BitmapFactory.decodeResource(getResources(), R.drawable.stone2);
//    Bitmap stone3 = BitmapFactory.decodeResource(getResources(), R.drawable.stone3);
//    Bitmap stone4 = BitmapFactory.decodeResource(getResources(), R.drawable.stone4);
//    Bitmap stone5 = BitmapFactory.decodeResource(getResources(), R.drawable.stone5);
//    Bitmap stone6 = BitmapFactory.decodeResource(getResources(), R.drawable.stone6);
//    Bitmap stone7 = BitmapFactory.decodeResource(getResources(), R.drawable.stone7);
//    Bitmap stone8 = BitmapFactory.decodeResource(getResources(), R.drawable.stone8);
//    Bitmap stone9 = BitmapFactory.decodeResource(getResources(), R.drawable.stone9);
//    Bitmap dirt1 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt1);
//    Bitmap dirt2 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt2);
//    Bitmap dirt3 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt3);
//    Bitmap dirt4 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt4);
//    Bitmap dirt5 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt5);
//    Bitmap dirt6 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt6);
//    Bitmap dirt7 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt7);
//    Bitmap dirt8 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt8);
//    Bitmap dirt9 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt9);
//    Bitmap redstone1 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone1);
//    Bitmap redstone2 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone2);
//    Bitmap redstone3 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone3);
//    Bitmap redstone4 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone4);
//    Bitmap redstone5 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone5);
//    Bitmap redstone6 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone6);
//    Bitmap redstone7 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone7);
//    Bitmap redstone8 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone8);
//    Bitmap redstone9 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone9);
//    Bitmap gold1 = BitmapFactory.decodeResource(getResources(), R.drawable.gold1);
//    Bitmap gold2 = BitmapFactory.decodeResource(getResources(), R.drawable.gold2);
//    Bitmap gold3 = BitmapFactory.decodeResource(getResources(), R.drawable.gold3);
//    Bitmap gold4 = BitmapFactory.decodeResource(getResources(), R.drawable.gold4);
//    Bitmap gold5 = BitmapFactory.decodeResource(getResources(), R.drawable.gold5);
//    Bitmap gold6 = BitmapFactory.decodeResource(getResources(), R.drawable.gold6);
//    Bitmap gold7 = BitmapFactory.decodeResource(getResources(), R.drawable.gold7);
//    Bitmap gold8 = BitmapFactory.decodeResource(getResources(), R.drawable.gold8);
//    Bitmap gold9 = BitmapFactory.decodeResource(getResources(), R.drawable.gold9);
//    Bitmap diamond1 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond1);
//    Bitmap diamond2 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond2);
//    Bitmap diamond3 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond3);
//    Bitmap diamond4 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond4);
//    Bitmap diamond5 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond5);
//    Bitmap diamond6 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond6);
//    Bitmap diamond7 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond7);
//    Bitmap diamond8 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond8);
//    Bitmap diamond9 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond9);
//    Bitmap iron1 = BitmapFactory.decodeResource(getResources(), R.drawable.iron1);
//    Bitmap iron2 = BitmapFactory.decodeResource(getResources(), R.drawable.iron2);
//    Bitmap iron3 = BitmapFactory.decodeResource(getResources(), R.drawable.iron3);
//    Bitmap iron4 = BitmapFactory.decodeResource(getResources(), R.drawable.iron4);
//    Bitmap iron5 = BitmapFactory.decodeResource(getResources(), R.drawable.iron5);
//    Bitmap iron6 = BitmapFactory.decodeResource(getResources(), R.drawable.iron6);
//    Bitmap iron7 = BitmapFactory.decodeResource(getResources(), R.drawable.iron7);
//    Bitmap iron8 = BitmapFactory.decodeResource(getResources(), R.drawable.iron8);
//    Bitmap iron9 = BitmapFactory.decodeResource(getResources(), R.drawable.iron9);
//    Bitmap emerald1 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald1);
//    Bitmap emerald2 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald2);
//    Bitmap emerald3 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald3);
//    Bitmap emerald4 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald4);
//    Bitmap emerald5 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald5);
//    Bitmap emerald6 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald6);
//    Bitmap emerald7 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald7);
//    Bitmap emerald8 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald8);
//    Bitmap emerald9 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald9);
//
    ArrayList<Bitmap> stone = new ArrayList<>();
    ArrayList<Bitmap> dirt = new ArrayList<>();
    ArrayList<Bitmap> redstone = new ArrayList<>();
    ArrayList<Bitmap> gold = new ArrayList<>();
    ArrayList<Bitmap> diamond = new ArrayList<>();
    ArrayList<Bitmap> iron = new ArrayList<>();
    ArrayList<Bitmap> emerald = new ArrayList<>();
//
    HashMap<Integer, ArrayList<Bitmap>> idToBitmaps;

    public DrawViewMine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        playable = false;
        currentBlock = 0;
        idToBitmaps = new HashMap<>();
        blocksMined = 0;
        count = 0;


        setAllBitmaps();

        idToBitmaps.put(1, stone);
        idToBitmaps.put(2, dirt);
        idToBitmaps.put(3, redstone);
        idToBitmaps.put(4, gold);
        idToBitmaps.put(5, diamond);
        idToBitmaps.put(6, iron);
        idToBitmaps.put(7, emerald);

        float blockTop = getWidth() * 0.71f;
        float blockLeft = getWidth() * 0.25f;
        float blockLength = getWidth() * 0.5f;

        for(int i = 0; i < BLOCKS; i++){
            int block_id = (int)(Math.random() * 7) + 1;
            blocks[i] = new Block(blockLeft, blockTop, blockLength, block_id);
            blocks[i].setBitmapStages(idToBitmaps.get(block_id));
            blocks[i].setBitmap(idToBitmaps.get(block_id).get(0));
        }


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//
        blocks[currentBlock].draw(canvas);

        invalidate();
    }

    public void setAllBitmaps() {
        stone1 = BitmapFactory.decodeResource(getResources(), R.drawable.stone1);
        stone2 = BitmapFactory.decodeResource(getResources(), R.drawable.stone2);
        stone3 = BitmapFactory.decodeResource(getResources(), R.drawable.stone3);
        stone4 = BitmapFactory.decodeResource(getResources(), R.drawable.stone4);
        stone5 = BitmapFactory.decodeResource(getResources(), R.drawable.stone5);
        stone6 = BitmapFactory.decodeResource(getResources(), R.drawable.stone6);
        stone7 = BitmapFactory.decodeResource(getResources(), R.drawable.stone7);
        stone8 = BitmapFactory.decodeResource(getResources(), R.drawable.stone8);
        stone9 = BitmapFactory.decodeResource(getResources(), R.drawable.stone9);

        dirt1 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt1);
        dirt2 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt2);
        dirt3 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt3);
        dirt4 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt4);
        dirt5 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt5);
        dirt6 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt6);
        dirt7 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt7);
        dirt8 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt8);
        dirt9 = BitmapFactory.decodeResource(getResources(), R.drawable.dirt9);

        redstone1 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone1);
        redstone2 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone2);
        redstone3 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone3);
        redstone4 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone4);
        redstone5 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone5);
        redstone6 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone6);
        redstone7 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone7);
        redstone8 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone8);
        redstone9 = BitmapFactory.decodeResource(getResources(), R.drawable.redstone9);

        gold1 = BitmapFactory.decodeResource(getResources(), R.drawable.gold1);
        gold2 = BitmapFactory.decodeResource(getResources(), R.drawable.gold2);
        gold3 = BitmapFactory.decodeResource(getResources(), R.drawable.gold3);
        gold4 = BitmapFactory.decodeResource(getResources(), R.drawable.gold4);
        gold5 = BitmapFactory.decodeResource(getResources(), R.drawable.gold5);
        gold6 = BitmapFactory.decodeResource(getResources(), R.drawable.gold6);
        gold7 = BitmapFactory.decodeResource(getResources(), R.drawable.gold7);
        gold8 = BitmapFactory.decodeResource(getResources(), R.drawable.gold8);
        gold9 = BitmapFactory.decodeResource(getResources(), R.drawable.gold9);

        diamond1 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond1);
        diamond2 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond2);
        diamond3 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond3);
        diamond4 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond4);
        diamond5 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond5);
        diamond6 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond6);
        diamond7 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond7);
        diamond8 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond8);
        diamond9 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond9);

        iron1 = BitmapFactory.decodeResource(getResources(), R.drawable.iron1);
        iron2 = BitmapFactory.decodeResource(getResources(), R.drawable.iron2);
        iron3 = BitmapFactory.decodeResource(getResources(), R.drawable.iron3);
        iron4 = BitmapFactory.decodeResource(getResources(), R.drawable.iron4);
        iron5 = BitmapFactory.decodeResource(getResources(), R.drawable.iron5);
        iron6 = BitmapFactory.decodeResource(getResources(), R.drawable.iron6);
        iron7 = BitmapFactory.decodeResource(getResources(), R.drawable.iron7);
        iron8 = BitmapFactory.decodeResource(getResources(), R.drawable.iron8);
        iron9 = BitmapFactory.decodeResource(getResources(), R.drawable.iron9);

        emerald1 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald1);
        emerald2 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald2);
        emerald3 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald3);
        emerald4 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald4);
        emerald5 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald5);
        emerald6 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald6);
        emerald7 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald7);
        emerald8 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald8);
        emerald9 = BitmapFactory.decodeResource(getResources(), R.drawable.emerald9);

        stone.add(stone1);
        stone.add(stone2);
        stone.add(stone3);
        stone.add(stone4);
        stone.add(stone5);
        stone.add(stone6);
        stone.add(stone7);
        stone.add(stone8);
        stone.add(stone9);


        dirt.add(dirt1);
        dirt.add(dirt2);
        dirt.add(dirt3);
        dirt.add(dirt4);
        dirt.add(dirt5);
        dirt.add(dirt6);
        dirt.add(dirt7);
        dirt.add(dirt8);
        dirt.add(dirt9);

        redstone.add(redstone1);
        redstone.add(redstone2);
        redstone.add(redstone3);
        redstone.add(redstone4);
        redstone.add(redstone5);
        redstone.add(redstone6);
        redstone.add(redstone7);
        redstone.add(redstone8);
        redstone.add(redstone9);

        gold.add(gold1);
        gold.add(gold2);
        gold.add(gold3);
        gold.add(gold4);
        gold.add(gold5);
        gold.add(gold6);
        gold.add(gold7);
        gold.add(gold8);
        gold.add(gold9);

        diamond.add(diamond1);
        diamond.add(diamond2);
        diamond.add(diamond3);
        diamond.add(diamond4);
        diamond.add(diamond5);
        diamond.add(diamond6);
        diamond.add(diamond7);
        diamond.add(diamond8);
        diamond.add(diamond9);

        iron.add(iron1);
        iron.add(iron2);
        iron.add(iron3);
        iron.add(iron4);
        iron.add(iron5);
        iron.add(iron6);
        iron.add(iron7);
        iron.add(iron8);
        iron.add(iron9);

        emerald.add(emerald1);
        emerald.add(emerald2);
        emerald.add(emerald3);
        emerald.add(emerald4);
        emerald.add(emerald5);
        emerald.add(emerald6);
        emerald.add(emerald7);
        emerald.add(emerald8);
        emerald.add(emerald9);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(blocks[currentBlock].contains(event.getX(), event.getY())){
                if (playable){
                    int currentStage = blocks[currentBlock].getStage();
                    blocks[currentBlock].setStage(currentStage + 1);
                    if (blocks[currentBlock].getStage() >= 9){
                        blocks[currentBlock].setMined(true);
                        blocksMined += 1;
                        currentBlock += 1;

                        if (blocksMined == 10){
                            //GAME IS DONE! YOU WIN!
                            playable = false;
                            currentBlock = 9;

                        }

                    }
                    else{
                        Bitmap nextStage = blocks[currentBlock].getBitmapStages().get(currentStage +1);
                        blocks[currentBlock].setBitmap(nextStage);
                    }

                }
            }
        }
        return false;
    }

    public int getBlocksMined() {
        return blocksMined;
    }
}
