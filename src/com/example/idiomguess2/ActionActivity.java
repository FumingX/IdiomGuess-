package com.example.idiomguess2;


import android.annotation.SuppressLint;
import android.app.*;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.ImageView.ScaleType;


public class ActionActivity extends Activity {
	
	private ImageView back, mark;
	private RelativeLayout layout;
	private GameView surf;
	private LinearLayout loginLayout;
	private final static float hRate = 5.3f;
	private final static float back_W = 716;
	private final static float mark_W = 60;
	private final static float mark_H = 60;
	private boolean animMark = true;
	private TranslateAnimation translateAnimation;
	private long exitTime;
	
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		layout = (RelativeLayout) findViewById(R.id.actionLayout);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////

		back = new ImageView(this);
		back.setId(1);
		back.setAdjustViewBounds(true);
		back.setScaleType(ScaleType.FIT_CENTER);
		back.setImageResource(R.drawable.logo4);
		
		int screenWidth  = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）  
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）  

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		lp.setMargins(screenWidth / 8, screenHeight / 8, screenWidth / 8,
				screenHeight / 8);

		layout.addView(back, lp);
		
		Animation myAnimation = AnimationUtils.loadAnimation(this,R.anim.alpha_animation);
		back.setAnimation(myAnimation);
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			if (animMark) {
				animMark = false;
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Message message = new Message();
						message.what = 0;
						myHandler.sendMessage(message);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						startActivity(new Intent(ActionActivity.this, LoginActivity.class));
						finish();
					}
				}).start();
			}
		}
	}
	
	private Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {

				mark = new ImageView(ActionActivity.this);
				mark.setImageResource(R.drawable.mark);
				mark.setAdjustViewBounds(true);
				mark.setScaleType(ScaleType.FIT_CENTER);
				float xRate = back.getWidth()/back_W;
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)(xRate*mark_W), (int)(xRate*mark_H));
				lp.addRule(RelativeLayout.ALIGN_LEFT, 1);
				lp.addRule(RelativeLayout.ALIGN_BOTTOM, 1);
				lp.setMargins((int) (back.getWidth()/hRate)-(int)(xRate*mark_W)/2, 0, 0,
						back.getHeight()/2-(int)(xRate*mark_H));
				layout.addView(mark, lp);

				Animation myAnimation = AnimationUtils.loadAnimation(
						ActionActivity.this, R.anim.scale_animation);
				mark.startAnimation(myAnimation);
			}
		}
	};
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
        return super.onKeyDown(keyCode, event);
    }
	//////////////////////////////////////////////////////////////////////////////////////
}
