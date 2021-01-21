package com.leejordan.minecraftboardgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DrawViewInventory extends View {

    Bitmap hotbar;
    static Inventory inventory;
    static HashMap<Integer, Slot> iDToSlot;
    static HashMap<String, Bitmap> itemToBitmap;
    Dice dice;
    Bitmap die;
    Slot slot2, slot3, slot4, slot5, slot6;
    Bitmap enderPearl1, enderPearl2, enderPearl3, enderPearl4, enderPearl5, enderPearl6, enderPearl7, enderPearl8, enderPearl9, enderPearl10, enderPearl11, enderPearl12, enderPearl13, enderPearl14, enderPearl15, enderPearl16, enderPearl17, enderPearl18, enderPearl19, enderPearl20, enderPearl21, enderPearl22, enderPearl23, woodSword1, woodSword2, ironSword1, ironSword2, goldSword1, goldSword2, diamondSword1, diamondSword2;

    public DrawViewInventory(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        hotbar = BitmapFactory.decodeResource(getResources(), R.drawable.inventory);
        die = BitmapFactory.decodeResource(getResources(), R.drawable.dice);
        setBitmaps();

        float inventoryHeight = getWidth() * 0.145f;
        float inventoryTop = getHeight() - inventoryHeight;

        inventory = new Inventory(0, inventoryTop, getWidth(), inventoryTop + inventoryHeight);
        inventory.setBitmap(hotbar);

        float diceTop = inventoryTop + (getWidth() * 0.02f);
        float item_length = getWidth() * 0.1f;
        dice = new Dice(getWidth()*0.02f, diceTop, item_length);
        dice.setBitmap(die);

        float slotTop = inventoryTop + (getWidth() * 0.025f);
        float slot2Left = getWidth() * 0.16f;
        float slot3Left = getWidth() * 0.305f;
        float slot4Left = getWidth() * 0.445f;
        float slot5Left = getWidth() * 0.588f;
        float slot6Left = getWidth() * 0.733f;
        slot2 = new Slot(slot2Left, slotTop, item_length, 2);
        slot3 = new Slot(slot3Left, slotTop, item_length, 3);
        slot4 = new Slot(slot4Left, slotTop, item_length, 4);
        slot5 = new Slot(slot5Left, slotTop, item_length, 5);
        slot6 = new Slot(slot6Left, slotTop, item_length, 6);

        iDToSlot = new HashMap<>();
        iDToSlot.put(2, slot2);
        iDToSlot.put(3, slot3);
        iDToSlot.put(4, slot4);
        iDToSlot.put(5, slot5);
        iDToSlot.put(6, slot6);

        itemToBitmap = new HashMap<>();
        itemToBitmap.put("ENDER_PEARL1", enderPearl1);
        itemToBitmap.put("ENDER_PEARL2", enderPearl2);
        itemToBitmap.put("ENDER_PEARL3", enderPearl3);
        itemToBitmap.put("ENDER_PEARL4", enderPearl4);
        itemToBitmap.put("ENDER_PEARL5", enderPearl5);
        itemToBitmap.put("ENDER_PEARL6", enderPearl6);
        itemToBitmap.put("ENDER_PEARL7", enderPearl7);
        itemToBitmap.put("ENDER_PEARL8", enderPearl8);
        itemToBitmap.put("ENDER_PEARL9", enderPearl9);
        itemToBitmap.put("ENDER_PEARL10", enderPearl10);
        itemToBitmap.put("ENDER_PEARL11", enderPearl11);
        itemToBitmap.put("ENDER_PEARL12", enderPearl12);
        itemToBitmap.put("ENDER_PEARL13", enderPearl13);
        itemToBitmap.put("ENDER_PEARL14", enderPearl14);
        itemToBitmap.put("ENDER_PEARL15", enderPearl15);
        itemToBitmap.put("ENDER_PEARL16", enderPearl16);
        itemToBitmap.put("ENDER_PEARL17", enderPearl17);
        itemToBitmap.put("ENDER_PEARL18", enderPearl18);
        itemToBitmap.put("ENDER_PEARL19", enderPearl19);
        itemToBitmap.put("ENDER_PEARL20", enderPearl20);
        itemToBitmap.put("ENDER_PEARL21", enderPearl21);
        itemToBitmap.put("ENDER_PEARL22", enderPearl22);
        itemToBitmap.put("ENDER_PEARL23", enderPearl23);
        itemToBitmap.put("WOODEN_SWORD", woodSword1);
        itemToBitmap.put("IRON_SWORD", ironSword1);
        itemToBitmap.put("GOLD_SWORD", goldSword1);
        itemToBitmap.put("DIAMOND_SWORD", diamondSword1);


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        inventory.draw(canvas);
        dice.draw(canvas);
        slot2.draw(canvas);
        slot3.draw(canvas);
        slot4.draw(canvas);
        slot5.draw(canvas);
        slot6.draw(canvas);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            Log.i("DICE", ""+ DiceFragment.on);
            if(dice.contains(event.getX(), event.getY())){
                Log.i("DICE", ""+ DrawView.moving);
                if (!DiceFragment.on && DrawView.myTurn == false && DrawView.moving == false){
                    GameActivity.roll();
                }
            }
            else if(slot3.contains(event.getX(), event.getY())){
                if(DrawView.readyForWeapon == true && inventory.getHotbar().size() >= 3){
                    DrawView.weaponChosen = true;
                    DrawView.readyForWeapon = false;
                    DrawView.weaponChoice = inventory.getHotbar().get(2);

                }
            }
            else if(slot4.contains(event.getX(), event.getY())){
                if(DrawView.readyForWeapon == true && inventory.getHotbar().size() >= 4){
                    DrawView.weaponChosen = true;
                    DrawView.readyForWeapon = false;
                    DrawView.weaponChoice = inventory.getHotbar().get(3);
                }
            }
            else if(slot5.contains(event.getX(), event.getY())){
                if(DrawView.readyForWeapon == true && inventory.getHotbar().size() >= 5){
                    DrawView.weaponChosen = true;
                    DrawView.readyForWeapon = false;
                    DrawView.weaponChoice = inventory.getHotbar().get(4);
                }
            }
            else if(slot6.contains(event.getX(), event.getY())){
                if(DrawView.readyForWeapon == true && inventory.getHotbar().size() >= 6){
                    DrawView.weaponChosen = true;
                    DrawView.readyForWeapon = false;
                    DrawView.weaponChoice = inventory.getHotbar().get(5);
                }
            }
        }
        return false;
    }

    public void setBitmaps(){
        enderPearl1 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl1);
        enderPearl2 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl2);
        enderPearl3 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl3);
        enderPearl4 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl4);
        enderPearl5 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl5);
        enderPearl6 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl6);
        enderPearl7 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl7);
        enderPearl8 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl8);
        enderPearl9 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl9);
        enderPearl10 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl10);
        enderPearl11 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl11);
        enderPearl12 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl12);
        enderPearl13 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl13);
        enderPearl14 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl14);
        enderPearl15 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl15);
        enderPearl16 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl16);
        enderPearl17 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl17);
        enderPearl18 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl18);
        enderPearl19 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl19);
        enderPearl20 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl20);
        enderPearl21 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl21);
        enderPearl22 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl22);
        enderPearl23 = BitmapFactory.decodeResource(getResources(), R.drawable.ender_pearl23);

        woodSword1 = BitmapFactory.decodeResource(getResources(), R.drawable.wood_sword);
        ironSword1 = BitmapFactory.decodeResource(getResources(), R.drawable.iron_sword);
        goldSword1 = BitmapFactory.decodeResource(getResources(), R.drawable.gold_sword);
        diamondSword1 = BitmapFactory.decodeResource(getResources(), R.drawable.diamond_sword);



    }

    public static void addItem(String type, int count){
        inventory.add(type, count);
//        Log.i("INVENTORY", inventory.getHotbar().toString());
//        Log.i("INVENTORY", type + inventory.getItems().get(type));
        if (type.equals("ENDER_PEARL")){
            iDToSlot.get(inventory.getHotbar().indexOf(type) + 1).setBitmap(itemToBitmap.get(""+ type + inventory.getItems().get(type)));
        }
        else{
            iDToSlot.get(inventory.getHotbar().indexOf(type) + 1).setBitmap(itemToBitmap.get(""+ type));
        }


    }

    public static int enderPearls(){
        if (inventory.getHotbar().contains("ENDER_PEARL")){
            return inventory.getItems().get("ENDER_PEARL");
        }
        else{
            return 0;
        }
    }

    public static boolean hasSword(){
        if (inventory.getHotbar().contains("WOODEN_SWORD") || inventory.getHotbar().contains("IRON_SWORD") || inventory.getHotbar().contains("GOLD_SWORD") || inventory.getHotbar().contains("DIAMOND_SWORD")){
            return true;
        }
        return false;
    }


}
