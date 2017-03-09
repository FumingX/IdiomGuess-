package com.example.idiomguess2;

import android.app.Service;
import android.content.Context;  
import android.content.Intent;
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;
import android.graphics.Canvas;  
import android.graphics.Color;    
import android.graphics.PorterDuff;  
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.SurfaceHolder;  
import android.view.SurfaceView;  
import android.view.SurfaceHolder.Callback;  

  

public class MySurfaceView extends SurfaceView implements Callback, Runnable {  

    private static final String TAG = "WakeLock";
	private Context mContext;  
    private SurfaceHolder surfaceHolder;  
    private Canvas mCanvas;  
    private int[] images;
    private boolean mPauseFlag ;

	public MySurfaceView(Context context) {
		super(context);
		this.mContext = context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
//		images = new int[] { R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
//				R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
//				R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
//				R.drawable.pic10, R.drawable.pic11, R.drawable.pic12,
//				R.drawable.pic13, R.drawable.pic14, R.drawable.pic15,
//				R.drawable.pic16, R.drawable.pic17, R.drawable.pic18,
//				R.drawable.pic19, R.drawable.pic20, R.drawable.pic21,
//				R.drawable.pic22, R.drawable.pic23, R.drawable.pic24,
//				R.drawable.pic25, R.drawable.pic26, R.drawable.pic27,
//				R.drawable.pic28, R.drawable.pic29, R.drawable.pic30,
//				R.drawable.pic31, R.drawable.pic32, R.drawable.pic33,
//				R.drawable.pic34, R.drawable.pic35, R.drawable.pic36,
//				R.drawable.pic37, R.drawable.pic38, R.drawable.pic39,
//				R.drawable.pic40, R.drawable.pic41, R.drawable.pic42,
//				R.drawable.pic43, R.drawable.pic44, R.drawable.pic45,
//				R.drawable.pic46, R.drawable.pic47, R.drawable.pic48,
//				R.drawable.pic49, R.drawable.pic50, R.drawable.pic51,
//				R.drawable.pic52, R.drawable.pic53, R.drawable.pic54,
//				R.drawable.pic55, R.drawable.pic56, R.drawable.pic57,
//				R.drawable.pic58, R.drawable.pic59, R.drawable.pic60,
//				R.drawable.pic61, R.drawable.pic62, R.drawable.pic63,
//				R.drawable.pic64, R.drawable.pic65, R.drawable.pic66,
//				R.drawable.pic67, R.drawable.pic68, R.drawable.pic69,
//				R.drawable.pic70, R.drawable.pic71, R.drawable.pic72,
//				R.drawable.pic73, R.drawable.pic74, R.drawable.pic75,
//				R.drawable.pic76, R.drawable.pic77, R.drawable.pic78,
//				R.drawable.pic79, R.drawable.pic80, R.drawable.pic81,
//				R.drawable.pic82, R.drawable.pic83, R.drawable.pic84,
//				R.drawable.pic85, R.drawable.pic86, R.drawable.pic87,
//				R.drawable.pic88, R.drawable.pic89, R.drawable.pic90,
//				R.drawable.pic91, R.drawable.pic92, R.drawable.pic93,
//				R.drawable.pic94, R.drawable.pic95, R.drawable.pic96,
//				R.drawable.pic97, R.drawable.pic98, R.drawable.pic99,
//				R.drawable.pic100, R.drawable.pic101, R.drawable.pic102,
//				R.drawable.pic103, R.drawable.pic104, R.drawable.pic105,
//				R.drawable.pic106, R.drawable.pic107, R.drawable.pic108,
//				R.drawable.pic109, R.drawable.pic110, R.drawable.pic111,
//				R.drawable.pic112, R.drawable.pic113, R.drawable.pic114,
//				R.drawable.pic115, R.drawable.pic116, R.drawable.pic117,
//				R.drawable.pic118, R.drawable.pic119, R.drawable.pic120,
//				R.drawable.pic121, R.drawable.pic122, R.drawable.pic123,
//				R.drawable.pic124, R.drawable.pic125 };
	}

    @Override  
    public void run() {
    	int item = 0;
        while (true) {
        	pauseThread();
        	if(item == 125)
        		item = 0;
            //synchronized (surfaceHolder) {  
        	mCanvas = surfaceHolder.lockCanvas();
    		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), images[0]);
    		Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), mCanvas.getHeight(), false);
    		mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);// ÇåÆÁÄ».
    		mCanvas.drawBitmap(bitmap2, item, 0, null);// »æÖÆµ±Ç°ÆÁÄ»±³¾°  
            surfaceHolder.unlockCanvasAndPost(mCanvas);       
            //}  
            item++;
        }  
    }  

    @Override  
    public void surfaceCreated(SurfaceHolder holder) {  
        new Thread(this).start();
        mPauseFlag = false;
    	//mContext.startService(new Intent(mContext,BackService.class));
    }  

    @Override  
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {  
    }  

    @Override  
    public void surfaceDestroyed(SurfaceHolder holder) {  

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


    
//    public static class BackService extends Service {
//
//    	private Thread thread;
//    	@Override
//    	public IBinder onBind(Intent intent) {
//    		return null;
//    	}
//    	
//    	@Override
//    	public void onCreate(){
//    		super.onCreate();
//    		thread = new Thread(new Runnable(){
//				@Override
//				public void run() {
//					int item = 0;
//			        while (true) {  
//			        	if(item == 125)
//			        		item = 0;
//			            //synchronized (surfaceHolder) {  
//			            	mCanvas = surfaceHolder.lockCanvas();
//			        		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), images[item]);
//			        		Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, mCanvas.getWidth(), mCanvas.getHeight(), false);
//			        		mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);// ÇåÆÁÄ».
//			        		mCanvas.drawBitmap(bitmap2, 0, 0, null);// »æÖÆµ±Ç°ÆÁÄ»±³¾°  
//			                surfaceHolder.unlockCanvasAndPost(mCanvas);    
//			            //}  
//			            item++;
//			        }  
//				}
//    		});
//    	}
//    	
//    	@Override
//    	public void onStart(Intent intent, int startId){
//    		super.onStart(intent, startId);
//    		thread.start();
//    	}
//    }
}  
