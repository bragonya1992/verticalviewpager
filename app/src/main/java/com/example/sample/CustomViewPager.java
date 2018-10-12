package com.example.sample;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.sample.sliding.SlidingTouchListener;

public class CustomViewPager extends ViewPager {

    private GestureDetector gestureDetector;
    private boolean isExpanded = true;
    public CustomViewPager(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context, new GestureViewPager());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    private final class GestureViewPager extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

}
