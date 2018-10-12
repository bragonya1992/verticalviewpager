package com.example.sample.sliding;

import android.view.View;
import android.view.ViewGroup;

public class DisplacementManager {
    private ViewGroup parentView;
    private View childView;
    private float limitFadeIn;
    private double limitUp;
    private double limitDown;
    private TranslationAnimator translationAnimator;
    public DisplacementManager(ViewGroup viewGroup, View childView){
        this.parentView = viewGroup;
        this.childView = childView;
        limitFadeIn = parentView.getLayoutParams().height / 4;
        limitUp = parentView.getLayoutParams().height * 0.50;
        limitDown = parentView.getLayoutParams().height*0.90;
        translationAnimator = new TranslationAnimator();
    }

    public void displacementView(View v, int distanceY){
        float elementYPosition = v.getY();
        if(distanceY >= limitUp && distanceY <= limitDown) {
            v.setY(distanceY);
        }
    }

    public void releaseView(float distance){
        ViewGroup.MarginLayoutParams pagerParams = (ViewGroup.MarginLayoutParams) childView.getLayoutParams();
        int from = pagerParams.topMargin;
        int to;
        if(distance < limitFadeIn){
            // fade up
            to =(int) limitUp;
        }else{
            // fade down
            to =(int) limitDown;
        }
        translationAnimator.translateView(from,to,childView);
    }
}
