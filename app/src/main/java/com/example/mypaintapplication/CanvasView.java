package com.example.mypaintapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView extends View {

    private Paint penPaint;
    private Path penPath;
    private Bitmap mainBitmap;
    private Canvas mainCanvas;
    private Paint mainBitmapPaint;


    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        penPaint = new Paint();
        penPath = new Path();

        penPaint.setStyle(Paint.Style.STROKE);
        penPaint.setStrokeWidth(8.0f);

        //performance
        penPaint.setAntiAlias(true);
        mainBitmapPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //initialise
        mainBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mainCanvas = new Canvas(mainBitmap);
    }

    private float LastX;
    private float LastY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw
        canvas.drawPath(penPath,penPaint);
        canvas.drawBitmap(mainBitmap,0,0,mainBitmapPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x=event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                penPath.moveTo(x,y);
                LastX =x;
                LastY =y;
                break;
            case MotionEvent.ACTION_MOVE:
                penPath.quadTo(LastX,LastY,(LastX+x)/2, (LastY+y)/2);
                LastY = y;
                LastX = x;
                break;
            case MotionEvent.ACTION_UP:
                penPath.lineTo(x,y);
                mainCanvas.drawPath(penPath,penPaint);
                penPath.reset();
                break;
        }

        invalidate();
        return true;
    }
    public void setBackgroundColour(int colour){
        mainCanvas.drawColor(colour);
        postInvalidate();

    }
    public void setCanvasTextColour(int colour){
        penPaint.setColor(colour);
        postInvalidate();
    }
    public void clearCanvas(){
        mainCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        postInvalidate();
    }
    public Bitmap getMainBitmap(){
        return mainBitmap;
    }


}
