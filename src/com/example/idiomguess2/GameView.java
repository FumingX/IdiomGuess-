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
    private boolean flag;// �̱߳�ʶ  
    private boolean mPauseFlag;
    private Bitmap bitmap_bg;// ����ͼ  
    private float mSurfaceWidth, mSurfaceHeight;// ��Ļ���  
    private int mBitposX;// ͼƬ��λ��  
    private Canvas mCanvas;
    private Thread thread;  
    // �����ƶ�״̬  
    private enum State {  
        LEFT, RINGHT  
    }
    // Ĭ��Ϊ����  
    private State state = State.LEFT;  
    private final int BITMAP_STEP = 1;// ���������ƶ�����.  

    

    protected void onDraw() {  
        drawBG();  
        updateBG();  
    }  

	public void updateBG() {
		/** ͼƬ����Ч�� **/
		switch (state) {

		case LEFT:
			mBitposX -= BITMAP_STEP;// ��������
			break;
		case RINGHT:
			mBitposX += BITMAP_STEP;// ��������
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
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);// ����Ļ.  
        mCanvas.drawBitmap(bitmap_bg, mBitposX, 0, null);// ���Ƶ�ǰ��Ļ����  
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
