package com.example.notepad_version_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

public class NotePadEditText extends EditText{
	Paint mPaint;
	Rect mRect;
	public NotePadEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	    mPaint.setColor(0x80FF0000); 
	}

	public NotePadEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	/*	mPaint = new Paint();
	    mPaint.setStyle(Paint.Style.STROKE);
	    mPaint.setStrokeWidth(3);
	    mPaint.setColor(0x80FF0000);*/
		mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	    mPaint.setColor(0x80FF0000); 
	}

	public NotePadEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	
	    //this Contructure required when you are using this view in xml 
		mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	    mPaint.setColor(0x80FF0000); 
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {

	/*    Log.d("NotePadEditView", "Calling onDraw()"); // These log messages are displaying
	       canvas.drawLine(0, 0, 50, 50, mPaint); // just some random stuff so we know when we are done. (Note: these are not displaying - what's up with that???)
	       canvas.drawText("Hello, World", 30, 30, mPaint);
	       super.onDraw(canvas); */
		
		//int count = getLineCount();

        int height = getHeight();
        
        int line_height = getLineHeight();
        
        int count = height / line_height;

        if (getLineCount() > count)
            count = getLineCount();//for long text with scrolling

        Rect r = mRect;
        Paint paint = mPaint;
        int baseline = getLineBounds(0, r);//first line

        for (int i = 0; i < count; i++) {

            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
            baseline += getLineHeight();//next line
        }

        super.onDraw(canvas);
    }

	}
