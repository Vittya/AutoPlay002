package com.example.harsanyiv.autoplay002;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;


import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GombView extends RelativeLayout  {


    /** fling behavior threshold */
    private static final int FLING_THRESHOLD = 180;
    /** callback return value content : Left to Right */
    public static final int LEFELE = 1;
    /** callback return value content : Right to Left */
    public static final int FELFELE = -1;

    private GestureDetector mGestureDetector;
    private OnSwipeListener mOnSwipeListener;

    /**
     * OnSwipeListener interface
     */
    public interface OnSwipeListener {
        public abstract void onSwipe(View view, int direction);
    }

    /**
     * Set swipe listener
     *
     * @param listener
     */
    public void setOnSwipeListener(final OnSwipeListener listener) {
        mOnSwipeListener = listener;
    }


    public GombView(Context context) {
        super(context);
        init(context);
    }

    public GombView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GombView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }



    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.gomb_view, this);

        mGestureDetector = new GestureDetector(context,new MySimpleOnGestureListener());
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mGestureDetector.onTouchEvent(ev);
    }


    private class MySimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            Toast.makeText(getContext(),"Sima spin",Toast.LENGTH_SHORT).show();

            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float sX = e1.getX();
            float sY = e1.getY();
            float eX = e2.getX();
            float eY = e2.getY();
            float deltaX = eX - sX;
            float deltaY = eY - sY;


            if (Math.abs(deltaY) > Math.abs(deltaX) && Math.abs(deltaY) > FLING_THRESHOLD) {
                // lefelé
                if (e1.getY() < e2.getY()) {
                    Toast.makeText(getContext(),"lefelé",Toast.LENGTH_SHORT).show();
                    if (mOnSwipeListener != null) {
                        mOnSwipeListener.onSwipe(GombView.this, LEFELE);
                        return true;
                    }
                }
                // Felfelé
                if (e1.getY() > e2.getY()) {
                    Toast.makeText(getContext(),"felfelé",Toast.LENGTH_SHORT).show();
                    if (mOnSwipeListener != null) {
                        mOnSwipeListener.onSwipe(GombView.this, FELFELE);
                        return true;
                    }
                }
            }
            return false;
        }
    }


}
