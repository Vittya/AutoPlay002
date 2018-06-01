package com.example.harsanyiv.autoplay002;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Szoroz extends LinearLayout{


    public Szoroz(Context context) {
        super(context);
        init(context);
    }

    public Szoroz(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Szoroz(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.szorzo, this);

        setOrientation(LinearLayout.HORIZONTAL);


    }


}
