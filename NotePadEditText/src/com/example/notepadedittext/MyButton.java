package com.example.notepadedittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.Button;

public class MyButton extends Button{
	Paint paint;
	RectF oval;
	public MyButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        paint.setStyle(Style.FILL);
        oval = new RectF(0, 0, 0, 0);
	}

	public MyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        paint.setStyle(Style.FILL);
        oval = new RectF(0, 0, 0, 0);
		
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        paint.setStyle(Style.FILL);
        oval = new RectF(0, 0, 0, 0);
	}
	
	 @Override
     protected void onDraw(Canvas canvas) {

            
            int width = getWidth();
            int height = getHeight();
            //canvas.drawCircle(width/2, height/2, height/3, paint);
            
            //canvas.drawrect(left,top,right,bottom)
            //canvas.drawRect(0, 0, width-10, height-10, paint);
            
            //canvas.drawText("Text",top,bottom)
            //canvas.drawLine(0, 0, width, 0, paint);
            oval.right = width;
            oval.bottom = height;
            //canvas.drawArc(oval, 90, 180, false, paint);
            //canvas.drawLine(0, height-10, width,height-10, paint);
            canvas.drawRoundRect(oval, 30, 40, paint);
            paint.setColor(Color.WHITE);
            canvas.drawText("Harsah", 0, height/2, paint);
            paint.setStyle(Style.STROKE);
            
            //paint.setColor(Color.BLUE);
            //canvas.drawArc(oval, 90, 180, false, paint);
            

      } 

}
