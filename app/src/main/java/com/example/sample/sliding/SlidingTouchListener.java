package com.example.sample.sliding;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SlidingTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private ViewPager  viewParams;
    private float initPosition;

    public SlidingTouchListener(Context context, ViewPager viewParams){
        gestureDetector = new GestureDetector(context, new SlidingGestureListener());
        this.viewParams = viewParams;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getActionMasked() == MotionEvent.ACTION_UP)
            onReleaseView(motionEvent.getY()-initPosition);
        boolean ges = gestureDetector.onTouchEvent(motionEvent);
        Log.d("underview",""+ges);
        return ges;
    }

    private final class SlidingGestureListener extends GestureDetector.SimpleOnGestureListener {
        private final String TAG = SlidingGestureListener.class.getSimpleName();
        private static final int SLIDE_THRESHOLD = 10;
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            try {
                float deltaY = e2.getY() - e1.getY();

                if (Math.abs(deltaY) > SLIDE_THRESHOLD) {
                    // the user made a sliding down gesture
                    Log.d("gesture","detalY "+deltaY+" initPos"+initPosition);
                    onSlideDown(initPosition+deltaY);
                    return true;

                }
            } catch (Exception exception) {
                Log.e(TAG, exception.getMessage());
            }

            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            initPosition = viewParams.getY();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            onClick();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }
    }

    public void onSlideDown(float displacement){

    }

    public void onReleaseView(float displacement){

    }

    public void onClick(){

    }
}
