package com.leejordan.minecraftboardgame;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class SwipeListener implements GestureDetector.OnGestureListener {

    private static final int MIN_Y_SWIPE_DISTANCE= 100;
    private static final int MIN_X_SWIPE_DISTANCE = 100;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private SwipeActions actions;

    public SwipeListener(SwipeActions a){
        actions = a;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        float changeInX = e2.getX() - e1.getX();
//        float changeInY = e2.getY() - e1.getY();
//
//        if (Math.abs(changeInX) > MIN_X_SWIPE_DISTANCE) {
//
//            if (changeInX< 0) {
//               actions.onSwipeLeft();
//            } else {
//               actions.onSwipeRight();
//            }
//            return true;
//        }
//        if (Math.abs(changeInY) > MIN_Y_SWIPE_DISTANCE) {
//
//            // Now Check Which Side Swipe Happened
//
//            if (changeInY < 0) {
//                actions.onSwipeUp();
//            } else {
//                actions.onSwipeDown();
//            }
//        }
//        return false;

        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        actions.onSwipeRight();
                    } else {
                        actions.onSwipeLeft();
                    }
                    result = true;
                }
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    actions.onSwipeDown();
                } else {
                    actions.onSwipeUp();
                }
                result = true;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
