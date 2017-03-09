package com.example.idiomguess2;

import android.content.Context;  
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;
import android.graphics.Canvas;  
import android.graphics.Color;   
import android.graphics.PorterDuff;   
import android.util.AttributeSet;
import android.view.SurfaceHolder;  
import android.view.SurfaceView;  
import android.view.SurfaceHolder.Callback;  

  

public class GameView extends SurfaceView implements Callback, Runnable {  

	public GameView(Context context) {  
        super(context);  
        flag = true;
        mPauseFlag = false;  
        this.mContext = context;  
        setFocusable(true);  
        setFocusableInTouchMode(true);  
        surfaceHolder = getHolder();  
        surfaceHolder.addCallback(this);  
    }  
	
    public GameView(Context context, AttributeSet attrs) {
		super(context, attrs); 
        flag = true;
        mPauseFlag = false;  
        this.mContext = context;  
        setFocusable(true);  
        setFocusableInTouchMode(true);  
        surfaceHolder = getHolder();  
        surfaceHolder.addCallback(this);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		flag = true;
        mPauseFlag = false;  
        this.mContext = context;  
        setFocusable(true);  
        setFocusableInTouchMode(true);  
        surfaceHolder = getHolder();  
        surfaceHolder.addCallback(this);
	}

	private Context mContext;  
    private SurfaceHolder surfaceHolder;  
    private boolean flag;// 线程标识  
    private boolean mPauseFlag;
    private Bitmap bitmap_bg;// 背景图  
    private float mSurfaceWidth, mSurfaceHeight;// 屏幕宽高  
    private int mBitposX;// 图片的位置  
    private Canvas mCanvas;
    private Thread thread;  
    // 背景移动状态  
    private enum State {  
        LEFT, RINGHT  
    }
    // 默认为向左  
    private State state = State.LEFT;  
    private final int BITMAP_STEP = 1;// 背景画布移动步伐.  

    

    protected void onDraw() {  
        drawBG();  
        updateBG();  
    }  

	public void updateBG() {
		/** 图片滚动效果 **/
		switch (state) {

		case LEFT:
			mBitposX -= BITMAP_STEP;// 画布左移
			break;
		case RINGHT:
			mBitposX += BITMAP_STEP;// 画布右移
			break;
		default:
			break;
		}
		if (mBitposX <= -mSurfaceWidth) {
			state = State.RINGHT;
		}
		if (mBitposX >= 0) {
			state = State.LEFT;
		}
	}
	
    public void drawBG() {  
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);// 清屏幕.  
        mCanvas.drawBitmap(bitmap_bg, mBitposX, 0, null);// 绘制当前屏幕背景  
    }  

	@Override
	public void run() {
		while (flag) {
			// synchronized (surfaceHolder) {
			pauseThread();
			mCanvas = surfaceHolder.lockCanvas();
			onDraw();
			surfaceHolder.unlockCanvasAndPost(mCanvas);
			// }
		}
	}

    @Override  
    public void surfaceCreated(SurfaceHolder holder) { 
    	mSurfaceWidth = getWidth();
        mSurfaceHeight = getHeight();  
        
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.background);
        float rate = mSurfaceHeight/bitmap.getHeight();
		Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*rate), (int)mSurfaceHeight, false);
        bitmap_bg = bitmap2;
        thread = new Thread(this);  
        thread.start();
    }  

    @Override  
    public void surfaceChanged(SurfaceHolder holder, int format, int width,  
            int height) {
    }  

    @Override  
    public void surfaceDestroyed(SurfaceHolder holder) {  
    	flag = false;  
    }  
    
    public void onPause(){
		synchronized (surfaceHolder) {
			mPauseFlag = true;
		}
	}

	public void onResume(){
		synchronized (surfaceHolder) {
			mPauseFlag = false;
			surfaceHolder.notifyAll();
		}
	}
	
	private void pauseThread(){
		synchronized (surfaceHolder) {
			if (mPauseFlag){
				try {
					surfaceHolder.wait();
				} catch (Exception e) {
				}
			}
		}
	}
}
