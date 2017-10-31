package com.example.icog.mapsdemo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class galleryShow extends AppCompatActivity {

    Button buttonprev, buttonNext;
    ImageSwitcher imageSwitcher;
    Integer[] images = {R.drawable.pic1,R.drawable.about};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_show);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);
        buttonNext = (Button) findViewById(R.id.NextButton);
        buttonprev = (Button) findViewById(R.id.prevButton);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;

            }
        });

        Animation in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);


        buttonprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    i--;
                    imageSwitcher.setImageResource(images[i]);
                }

            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<images.length-1){
                    i++;
                    imageSwitcher.setImageResource(images[i]);
                }
            }
        });


    }
}
