package com.example.mypaintapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleColourView extends View {

    private Paint colourPaint;
    private OnColourClickListener listener=null;

    private int[] colours = {Color.RED, Color.BLACK,Color.GRAY,Color.WHITE,Color.YELLOW,
            Color.BLUE,Color.GREEN,Color.MAGENTA,Color.CYAN };


    public CircleColourView(Context context) {
        super(context);
        init();
    }

    public CircleColourView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleColourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CircleColourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        colourPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width= getWidth()/colours.length;

        for (int i = 0; i<colours.length; i++){
            colourPaint.setColor(colours[i]);
            canvas.drawCircle((i+0.5f)*width, getHeight()/2, width/2,colourPaint);
        }


    }
    public interface OnColourClickListener{
        public void changeColour(int colour);
    }
    public void setOnColourClickListener(OnColourClickListener listener){
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (listener!=null && event.getAction()==MotionEvent.ACTION_DOWN){
            float x= event.getX();

            //get the width

            int width = getWidth()/colours.length;

            //get the colour position

            int selectedColourPositionInArray = (int) x/width;

            listener.changeColour(colours[selectedColourPositionInArray]);
        }



        return true;
    }
}
