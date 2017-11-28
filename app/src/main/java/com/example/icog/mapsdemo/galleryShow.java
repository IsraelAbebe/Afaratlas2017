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
    Integer[] images = {R.drawable.picture, R.drawable.picture1, R.drawable.picture2, R.drawable.picture3,
            R.drawable.picture4, R.drawable.picture5, R.drawable.picture6, R.drawable.picture7, R.drawable.picture8,
            R.drawable.picture9, R.drawable.picture10, R.drawable.picture11, R.drawable.picture12, R.drawable.picture13,
            R.drawable.picture14, R.drawable.picture15, R.drawable.picture16, R.drawable.picture17, R.drawable.picture18,
            R.drawable.picture19, R.drawable.picture20, R.drawable.picture21, R.drawable.picture22, R.drawable.picture23,
            R.drawable.picture24, R.drawable.picture25, R.drawable.picture26, R.drawable.picture27, R.drawable.picture28};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_show);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);
        buttonNext = (Button) findViewById(R.id.NextButton);
        buttonprev = (Button) findViewById(R.id.prevButton);

//        imageSwitcher.setImageResource(R.drawable.picture);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.picture);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;

            }
        });

        Animation in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);


        buttonprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    i--;
                    imageSwitcher.setImageResource(images[i]);
                }

            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i < images.length - 1) {
                    i++;
                    imageSwitcher.setImageResource(images[i]);
                }
            }
        });


    }
}
