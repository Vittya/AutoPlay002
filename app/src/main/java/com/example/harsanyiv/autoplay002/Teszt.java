package com.example.harsanyiv.autoplay002;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.animation.DynamicAnimation;
import android.support.animation.DynamicAnimation.OnAnimationEndListener;
import android.support.animation.FlingAnimation;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Teszt extends RelativeLayout  {

    ImageButton spin;
    FlingAnimation flingY;
    Bundle bundle;

    public Teszt(Context context) {
        super(context);
        init(context);
    }

    public Teszt(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Teszt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.teszt, this);


    }

  public enum GombState{

        FNT(R.drawable.ic_spin),
        FNTb(R.drawable.ic_spin),
        KZT(R.drawable.ic_spin),
        LNT(R.drawable.ic_auto),
        LNTb(R.drawable.ic_stop);

      public int kép;

      GombState(int kép){
          this.kép=kép;
      }

    }

}
