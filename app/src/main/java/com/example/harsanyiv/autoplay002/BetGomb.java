package com.example.harsanyiv.autoplay002;

import android.content.Context;
import android.support.annotation.Nullable;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

public class BetGomb extends LinearLayout{
    public BetGomb(Context context) {
        super(context);
        init(context);
    }

    public BetGomb(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BetGomb(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context c){


        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bet_gombok,this);

    }
}
