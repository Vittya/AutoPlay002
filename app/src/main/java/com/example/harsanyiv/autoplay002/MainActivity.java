package com.example.harsanyiv.autoplay002;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.v7.widget.CardView;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;


public class MainActivity extends Activity  {

    ImageButton i,spin;
    ImageView img1;
    CardView cv;
    TextView tv;
    boolean m=false;
    FlingAnimation flingY;

    int számláló=0;
    int iii=0;
    Animation anim,animB;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        img1=findViewById(R.id.img1);
        anim= AnimationUtils.loadAnimation(this,R.anim.spinnin);
        animB=AnimationUtils.loadAnimation(this,R.anim.nagy);
        if(savedInstanceState!=null)
            számláló=savedInstanceState.getInt("számláló");
        final Intent i = new Intent(this,TitkosBarlang.class);
        Button b =findViewById(R.id.ttksgmb);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(i);
            }
        });
        gombos();




    }

    private Runnable xxx = new Runnable() {
        @Override
        public void run() {
            if(számláló>0) {
                számláló--;
                tv.setText(String.valueOf(számláló));
                //img1.startAnimation(anim);
                if(getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE)
                    img1.setImageResource(R.drawable.lands_spun);
                else
                    img1.setImageResource(R.drawable.spin_wheels);
                handler.postDelayed(xxx,500);
            }else{
                handler.removeCallbacks(xxx);
                visi(cv);
                if(getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE)
                    img1.setImageResource(R.drawable.lands);
                else
                    img1.setImageResource(R.drawable.spin_wheels2);
            }
        }
    };

    private Runnable yyy = new Runnable() {
        @Override
        public void run() {
            if(iii>0){
                iii--;
            if(getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_LANDSCAPE)
                img1.setImageResource(R.drawable.lands_spun);
            else
                img1.setImageResource(R.drawable.spin_wheels);

            handler.postDelayed(yyy,100);

        } else{
                handler.removeCallbacks(yyy);
                if(getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE)
                    img1.setImageResource(R.drawable.lands);
                else
                    img1.setImageResource(R.drawable.spin_wheels2);

            }
        }
    };


    void gombos(){
        i = findViewById(R.id.x);
        cv = findViewById(R.id.cv);
        //cv.setBackgroundResource(R.drawable.ic_launcher_background);
        cv.setVisibility(View.GONE);
        spin = findViewById(R.id.spin);
        tv = findViewById(R.id.tv);
        if(számláló<=0)
            tv.setText("");

        BubbleSeekBar bsb = findViewById(R.id.sz);
        bsb.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                számláló=progress;
                tv.setText(String.valueOf(számláló));
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {


            }
        });
        final GestureDetector gestureDetector = new GestureDetector(getBaseContext(), mGestureListener);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m) {
                    számláló = 0;
                    m=false;
                }
                //ide kellhet egy zászló
                if(cv.getVisibility()==View.VISIBLE) {
                    cv.setVisibility(View.GONE);
                    spin.setImageResource(R.drawable.ic_stop);
                    m=true;
                    spin.setTag(R.drawable.ic_stop);
                    handler.postDelayed(xxx,100);
                }else if(spin.getTag()!=(Integer)R.drawable.ic_stop) {
                    spin.startAnimation(animB);
                    iii=2;
                   handler.post(yyy);

                }
            }
        });
        spin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return gestureDetector.onTouchEvent(event);
            }
        });







    }



    private GestureDetector.OnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {


        @Override
        public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {

            //fel le
            flingY = new FlingAnimation(spin, DynamicAnimation.TRANSLATION_Y);
            flingY.setStartVelocity(velocityY)
                    .setMinValue(0) // legalább sebesség
                    .setMaxValue(100)  // legfeljebb sebesség
                    .setFriction(0.01f)
                    .setMinimumVisibleChange(0.9f)
                    .addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                        @Override
                        public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                            //Toast.makeText(getBaseContext(),"value"+value+"veloc "+velocity,Toast.LENGTH_SHORT).show();
                            //findViewById(R.id.cv).setVisibility(View.GONE);
                            v(value);
                            if(value==100)
                                számláló = 20;

                            else visi(0,cv);

                            if(cv.getVisibility()==View.VISIBLE){
                                spin.setImageResource(R.drawable.ic_auto);
                            }

                        }
                    })

                    .start();

            return true;
        }


    };







    public void visi(View view) {

            findViewById(R.id.cv).setVisibility(View.GONE);
            flingY.setStartVelocity(-1800).start();
            tv.setText("");
            m=false;
    }
    public void visi(int i,View view) {

        findViewById(R.id.cv).setVisibility(View.GONE);
        számláló=i;
        tv.setText("");
        spin.setImageResource(R.drawable.ic_spin);
        spin.setTag(R.drawable.ic_spin);
    }

    public void v(float v) {
        if(v==100)
            findViewById(R.id.cv).setVisibility(View.VISIBLE);

    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putInt("számláló",számláló);
    }


    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        számláló = savedInstanceState.getInt("számláló");
    }

}
