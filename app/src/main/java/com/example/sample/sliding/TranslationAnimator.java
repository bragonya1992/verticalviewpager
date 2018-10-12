package com.example.sample.sliding;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class TranslationAnimator {

    public void translateView(int fromDeltaY,final int toDeltaY, final View view){
        TranslateAnimation anim = new TranslateAnimation(0, 0, fromDeltaY, toDeltaY);
        anim.setDuration(1000);

        anim.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                ViewGroup.MarginLayoutParams pagerParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                pagerParams.topMargin = toDeltaY;
                view.requestLayout();
            }
        });

        view.startAnimation(anim);
    }
}
