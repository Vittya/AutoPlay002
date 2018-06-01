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
import android.widget.Toast;

import com.xw.repo.BubbleSeekBar;


public class MainActivity extends Activity  {

    ImageButton i,spin,bal,jobb;
    Button a,b,c,d;
    ImageView img1;
    CardView cv,cv2;
    TextView tv,szorzTV,betTV;
    int[] szorzTmb = {1,2,4,6,8};
    boolean száználNyomtál_e=false;
    FlingAnimation flingY;
    int index=1;


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

        final Intent i = new Intent(this,TtksBrlng.class);
        Button b =findViewById(R.id.ttksgmb);

        gombos();

    }

    private Runnable xxx = new Runnable() {
        @Override
        public void run() {
            száználNyomtál_e=true;


        cv.setVisibility(View.GONE);
        spin.setImageResource(R.drawable.ic_stop);

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
                száználNyomtál_e=false;
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
        cv2 = findViewById(R.id.cv2);
        cv2.setVisibility(View.GONE);
        a = findViewById(R.id.btn0);
        b = findViewById(R.id.btn1);
        c = findViewById(R.id.btn2);
        d = findViewById(R.id.btn3);
        bal = findViewById(R.id.bal);
        jobb = findViewById(R.id.jobb);
        szorzTV = findViewById(R.id.multiTV);
        betTV = findViewById(R.id.betTV);


        bal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index!=0)
                   index--;

                szorzTV.setText(String.valueOf(szorzTmb[index]));
                bet();

            }
        });

        jobb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(index!=szorzTmb.length-1)
              index++;
                szorzTV.setText(String.valueOf(szorzTmb[index]));
                bet();

            }
        });



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

                //ide kellhet egy zászló
                if(cv.getVisibility()==View.VISIBLE) {
                    cv.setVisibility(View.GONE);
                    számláló = számláló<=0 ? 20:számláló;

                    handler.postDelayed(xxx,100);

                }else if(cv.getVisibility()==View.GONE&&száználNyomtál_e) {
                    visi(cv);
                    száználNyomtál_e = false;
                }
                    else  {
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
                    .setMinValue(-100) // legalább sebesség
                    .setMaxValue(100)  // legfeljebb sebesség
                    .setFriction(0.01f)
                    .setMinimumVisibleChange(0.9f)
                    .addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                        @Override
                        public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {

                            //findViewById(R.id.cv).setVisibility(View.GONE);
                    //v(value);

                        System.out.println("value"+value);

                            if(value==100) {

                                flingY.setMinValue(0).start();
                                    if(!száználNyomtál_e){
                                        spin.setImageResource(R.drawable.ic_auto);

                                        findViewById(R.id.cv).setVisibility(View.VISIBLE);

                                    }


                            }
                            else if(value==0) {
                                visi(0);
                                visiFentről(0);
                            }

                            else if(value==-100) {
                                flingY.setMaxValue(0).start();
                                cv2.setVisibility(View.VISIBLE);

                            }



                        }
                    })

                    .start();

            return true;
        }


    };


    private void bet(){
        a.setText(String.valueOf(0.1f*szorzTmb[index]));
        b.setText(String.valueOf(0.2f*szorzTmb[index]));
        c.setText(String.valueOf(0.5f*szorzTmb[index]));
        d.setText(String.valueOf(szorzTmb[index]));
    }

    public void visi(View view) {

        findViewById(R.id.cv).setVisibility(View.GONE);
        flingY.setStartVelocity(-1800).start();
        spin.setImageResource(R.drawable.ic_spin);
        tv.setText("");

    }
    public void visiFentről(View view) {

        findViewById(R.id.cv2).setVisibility(View.GONE);
        flingY.setStartVelocity(1800).start();
        betTV.setText("Bet: ");

    }

    public void visiFentről(int v) {

        findViewById(R.id.cv2).setVisibility(View.GONE);


    }
    public void visi(int i) {

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

    }


    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


    }

}
