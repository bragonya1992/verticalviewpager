package com.example.sample.draggin;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.sample.R;
import com.prabhat1707.verticalpager.VerticalViewPager;

public class DragginLayout extends RelativeLayout {
    private int mDraggingState = 0;
    private VerticalViewPager verticalPager;
    private ViewGroup parent;
    private ViewDragHelper mDragHelper;
    private int mDraggingBorder;
    private int mVerticalRange;


    public class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public void onViewDragStateChanged(int state) {
            if (state == mDraggingState) { // no change
                return;
            }
            if ((mDraggingState == ViewDragHelper.STATE_DRAGGING || mDraggingState == ViewDragHelper.STATE_SETTLING) &&
                    state == ViewDragHelper.STATE_IDLE) {
                // the view stopped from moving.

                if (mDraggingBorder == 0) {
                    onStopDraggingToClosed();
                }
            }
            if (state == ViewDragHelper.STATE_DRAGGING) {
                onStartDragging();
            }
            mDraggingState = state;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mDraggingBorder = top;
        }

        public int getViewVerticalDragRange(View child) {
            return mVerticalRange;
        }

        @Override
        public boolean tryCaptureView(View view, int i) {
            return view == parent;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int topBound = getPaddingTop();
            final int bottomBound = mVerticalRange;
            return Math.min(Math.max(top, topBound), bottomBound);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            final float rangeToCheck = mVerticalRange;
            if (mDraggingBorder == 0) {
                return;
            }
            if (mDraggingBorder == rangeToCheck) {
                return;
            }
            final int settleDestY = mDraggingBorder > rangeToCheck / 2 ? mVerticalRange : mVerticalRange/2;

            if(mDragHelper.settleCapturedViewAt(0, settleDestY)) {
                ViewCompat.postInvalidateOnAnimation(DragginLayout.this);
            }
        }
    }

    public DragginLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        verticalPager = findViewById(R.id.viewPager);
        parent = findViewById(R.id.parent);
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        super.onFinishInflate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mVerticalRange = (int) (h * 0.84);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void onStopDraggingToClosed() {
        // To be implemented
    }

    private void onStartDragging() {

    }

    private boolean isQueenTarget(MotionEvent event) {
        int[] queenLocation = new int[2];
        verticalPager.getLocationOnScreen(queenLocation);
        int upperLimit = queenLocation[1] + verticalPager.getMeasuredHeight();
        int lowerLimit = queenLocation[1];
        int y = (int) event.getRawY();
        return (y > lowerLimit && y < upperLimit);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            mDragHelper.processTouchEvent(event);
            return true;
    }

    @Override
    public void computeScroll() { // needed for automatic settling.
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean isMoving() {
        return (mDraggingState == ViewDragHelper.STATE_DRAGGING ||
                mDraggingState == ViewDragHelper.STATE_SETTLING);
    }
}
