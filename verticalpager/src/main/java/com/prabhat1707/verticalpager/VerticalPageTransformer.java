package com.prabhat1707.verticalpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by prabhat on 5/2/18.
 */

public class VerticalPageTransformer implements ViewPager.PageTransformer {

    public void transformPage(View page, float position) {
        //int pageWidth = view.getWidth();


            // This page is way off screen to the right
            float yPosition = position * page.getHeight();
            page.setTranslationY(yPosition);
            page.setTranslationX(page.getWidth() * -position);
            page.setAlpha(1);

    }

}
