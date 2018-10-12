package com.example.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sample.sliding.DisplacementManager;
import com.example.sample.sliding.SlidingTouchListener;
import com.prabhat1707.verticalpager.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private VerticalViewPager viewPager;
    private View testField;
    private Context context;
    private List<String> stringList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        viewPager = findViewById(R.id.viewPager);
        testField = findViewById(R.id.test_field);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(0,0,0,40);

        viewPager.setOffscreenPageLimit(8);
        final DisplacementManager displacementManager = new DisplacementManager((ViewGroup) testField,viewPager);
        testField.setOnTouchListener(new SlidingTouchListener(this,viewPager){

            @Override
            public void onSlideDown(float displacement) {
                Log.d("gesture","displacement "+displacement+" posi y"+viewPager.getY());
                displacementManager.displacementView(viewPager,(int)displacement);
            }

            @Override
            public void onReleaseView(float displacement) {
                //displacementManager.releaseView(displacement);
            }

            @Override
            public void onClick() {
                Toast.makeText(context, "you touch me!", Toast.LENGTH_SHORT).show();
            }
        });
        addData();
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), context, stringList));
    }

    private void addData() {
        for (int i = 0; i < 8; i++) {
            stringList.add(String.valueOf(i));
        }
    }


}
