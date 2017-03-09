package com.example.idiomguess2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;
import android.widget.SpinnerAdapter;

public class GameGallery extends Gallery{

	@SuppressWarnings("deprecation")
	public GameGallery(Context context) {
		super(context);
	}

	public GameGallery(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public GameGallery(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
	}

//	@Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
//                    float distanceY) {
//            float x =0f;
//            if(e2.getX()<e1.getX()){
//                    x=-15f;
//            }else {
//                    x=15f;
//            }
//            boolean result = false;
//            if(Math.abs(distanceX)<10){
//                    result = super.onScroll(e1, e2, (-1)*distanceX, distanceY);
//            }else{
//                    result = super.onScroll(e1, e2, x, distanceY);
//            }
//            return result;
//    }

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) { 

        return e2.getX() > e1.getX(); 

    } 

 

    @Override 

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, 

            float velocityY) { 

        int keyCode; 

        if (isScrollingLeft(e1, e2)) {       

            keyCode = KeyEvent.KEYCODE_DPAD_LEFT; 

        } else { 

            keyCode = KeyEvent.KEYCODE_DPAD_RIGHT; 

        } 

        onKeyDown(keyCode, null); 

        return true; 

    } 
}
