package com.tetraverge.greetingcardname.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tetraverge.greetingcardname.OnSwipeTouchListener;
import com.tetraverge.greetingcardname.R;
import com.tetraverge.greetingcardname.imageanimationutils.DisplayNextView;
import com.tetraverge.greetingcardname.imageanimationutils.Flip3dAnimation;

public class CardViewActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "CardViewActivity";
    ConstraintLayout cardviewlayout;
    ImageView imageView1, imageView2;
    TextView title;
    boolean isFirstImage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        cardviewlayout = findViewById(R.id.cardviewLayout);
        imageView1 = findViewById(R.id.firstImageView);
        imageView2 = findViewById(R.id.secondImageView);
        title = findViewById(R.id.title);

        title.setText(R.string.app_banner_title);
        title.setSelected(true);

        imageView2.setVisibility(View.GONE);
        Picasso.with(this)
                .load("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")
                .placeholder(R.drawable.ic_placeholder_image)
                .into(imageView1, new Callback(){

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

        Picasso.with(this)
                .load("https://homepages.cae.wisc.edu/~ece533/images/boat.png")
                .placeholder(R.drawable.ic_placeholder_image)
                .into(imageView2, new Callback(){

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });




        cardviewlayout.setOnTouchListener(new OnSwipeTouchListener(CardViewActivity.this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if (isFirstImage) {
                    applyRotation(0, -90);
                    isFirstImage = !isFirstImage;
                }

            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                if(!isFirstImage){
                    applyRotation(0, 90);
                    isFirstImage = !isFirstImage;
                }
            }


        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (isFirstImage) {
                    applyRotation(0, -90);
                    isFirstImage = !isFirstImage;
                } else {
                    applyRotation(0, 90);
                    isFirstImage = !isFirstImage;
                }
            }
        });
    }


    private void applyRotation(float start, float end) {

// Find the center of image

        final float centerX = imageView1.getWidth() / 2.0f;

        final float centerY = imageView1.getHeight() / 2.0f;


// Create a new 3D rotation with the supplied parameter

// The animation listener is used to trigger the next animation

        final Flip3dAnimation rotation =

                new Flip3dAnimation(start, end, centerX, centerY);

        rotation.setDuration(500);

        rotation.setFillAfter(true);

        rotation.setInterpolator(new AccelerateInterpolator());

        rotation.setAnimationListener(new DisplayNextView(isFirstImage, imageView1, imageView2));


        if (isFirstImage) {

            imageView1.startAnimation(rotation);

        } else {

            imageView2.startAnimation(rotation);

        }


    }



}
