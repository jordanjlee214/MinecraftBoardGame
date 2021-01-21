package com.leejordan.minecraftboardgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory extends RectF {
    private Bitmap bitmap;
    private int itemCount;
    private int slots;
    private HashMap<String, Integer> items;
    private ArrayList<String> hotbar;

    public Inventory(float left, float top, float right, float bottom){
        super(left, top, right, bottom);
        slots = 7;
        itemCount = 1;
        items = new HashMap<>();
        hotbar = new ArrayList<>();
        hotbar.add(0, "DICE");
        items.put("DICE", 1);
        items.put("ENDER_PEARL", 0);
        items.put("WOODEN_SWORD", 0);
        items.put("IRON_SWORD", 0);
        items.put("GOLD_SWORD", 0);
        items.put("DIAMOND_SWORD", 0);

    }

    public void draw(Canvas canvas){
        if (bitmap == null){
            Paint p = new Paint();
            p.setColor(Color.RED);
            canvas.drawCircle(500, 500, 30, p);
        }
        else{
            canvas.drawBitmap(bitmap, null, this,null );
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    public ArrayList<String> getHotbar() {
        return hotbar;
    }

    public void setHotbar(ArrayList<String> hotbar) {
        this.hotbar = hotbar;
    }

    public void add(String s, int count)
    {
        itemCount+=count;
        if (hotbar.contains(s)){
            int previousCount = items.get(s);
            items.put(s, previousCount + count);
        }
        else{
            hotbar.add(s);
            items.put(s, count);
        }
    }

}
