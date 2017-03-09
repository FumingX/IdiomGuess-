package com.example.idiomguess2;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class OnlinePlatformActivity extends Activity implements OnTouchListener, OnGestureListener{

	private Button leftBt, rightBt, listOnTimeI, listOnNumI, listOfOwn, listOfLine, enterGame, enterGameC, personal, myFriend, systset, systmarket, help, logoff;
	private ViewFlipper viewFlipper;
	private GestureDetector detector;
	private int screenWidth, screenHeight;
	private ImageView integralBook, challengeBook, fightLogo, fightLogoC, bestPlay, bestPlayC;
	private LinearLayout integralTabLayout, challengeTabLayout, menuLayout, 
			rankingListTLayout, rankingListNLayout, myFriendListLayout, 
			onlinePlayerListLayout, inviteOperateLayout;
	private ArrayList<HashMap<String,String>> integralTList, integralNList, challengeMFList, challengeNPList;
	private TextView listNoT, listNoN, listNickT, listNickN, listScoreT, listScoreN, inviteNickMF, inviteNickNP, inviteLevelMF, inviteLevelNP;

	// 左右滑动时手指按下的X坐标
	private float touchDownX;
	// 左右滑动时手指松开的X坐标
	private float touchUpX;
	
	private boolean integralListChange = false;
	private boolean challengeListChange = false;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlineplatform);
		
///////////////////////////操纵部分/////////////////////////////////////////
		detector = new GestureDetector(this);
		
		screenWidth  = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）  
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）  
		
		viewFlipper = (ViewFlipper) findViewById(R.id.modelFlipper); 
        viewFlipper.setOnTouchListener(this); 
        
        leftBt = (Button)findViewById(R.id.toleft);
        rightBt = (Button)findViewById(R.id.toright);
        RelativeLayout.LayoutParams leftRl = new RelativeLayout.LayoutParams(screenHeight/10,screenHeight/10);
        leftRl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftRl.addRule(RelativeLayout.CENTER_VERTICAL);
        leftRl.leftMargin = screenHeight/10;
        leftBt.setLayoutParams(leftRl);
        
        RelativeLayout.LayoutParams rightRl = new RelativeLayout.LayoutParams(screenHeight/10,screenHeight/10);
        rightRl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightRl.addRule(RelativeLayout.CENTER_VERTICAL);
        rightRl.rightMargin = screenHeight/10;
        rightBt.setLayoutParams(rightRl);
        
        leftBt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 从右往左，看后一个View 
				// 设置View切换的动画 
                // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right 
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(OnlinePlatformActivity.this, 
                        R.anim.slide_in_right)); 
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(OnlinePlatformActivity.this, 
                        R.anim.slide_out_left)); 
                // 显示前一个View 
                viewFlipper.showNext(); 
                
                leftBt.setVisibility(View.VISIBLE);
        		rightBt.setVisibility(View.VISIBLE);

        		leftBt.startAnimation(AnimationUtils.loadAnimation(OnlinePlatformActivity.this, R.anim.fade_in));
        		rightBt.startAnimation(AnimationUtils.loadAnimation(OnlinePlatformActivity.this, R.anim.fade_in));
        		
        		new Thread() {
        			public void run() {
        				try {
        					Thread.sleep(1500);
        					mHandler.sendEmptyMessage(0);
        				} catch (Exception e) {
        					;
        				}
        			}
        		}.start();
			}
		});

		rightBt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 设置View切换的动画
				viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
						OnlinePlatformActivity.this, R.anim.slide_in_left));
				viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
						OnlinePlatformActivity.this, R.anim.slide_out_right));
				// 显示下一个View
				viewFlipper.showPrevious();

				leftBt.setVisibility(View.VISIBLE);
				rightBt.setVisibility(View.VISIBLE);

				leftBt.startAnimation(AnimationUtils.loadAnimation(
						OnlinePlatformActivity.this, R.anim.fade_in));
				rightBt.startAnimation(AnimationUtils.loadAnimation(
						OnlinePlatformActivity.this, R.anim.fade_in));

				new Thread() {
					public void run() {
						try {
							Thread.sleep(1500);
							mHandler.sendEmptyMessage(0);
						} catch (Exception e) {
							;
						}
					}
				}.start();
			}
		});

		menuLayout = (LinearLayout) findViewById(R.id.menuLayout);
		menuLayout.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, screenHeight * 37 / 203));

		personal = (Button) findViewById(R.id.personal);
		personal.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/8, screenHeight * 37 / 406));
		myFriend = (Button) findViewById(R.id.myfriend);
		myFriend.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/8, screenHeight * 37 / 406));
		systset = (Button) findViewById(R.id.systset);
		systset.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/8, screenHeight * 37 / 406));
		systmarket = (Button) findViewById(R.id.systmarket);
		systmarket.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/8, screenHeight * 37 / 406));
		help = (Button) findViewById(R.id.help);
		help.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/8, screenHeight * 37 / 406));
		logoff = (Button) findViewById(R.id.logoff);
		logoff.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/8, screenHeight * 37 / 406));
///////////////////////////////////////////////////////////////////////////////////////////////
		
//////////////////////////////////////积分模式////////////////////////////////////////////////////
		
		rankingListTLayout = (LinearLayout)findViewById(R.id.rankingListTLayout);
		rankingListNLayout = (LinearLayout)findViewById(R.id.rankingListNLayout);
		rankingListNLayout.setVisibility(View.GONE);
		
		integralBook = (ImageView)findViewById(R.id.integralBook);
		RelativeLayout.LayoutParams integralRl = new RelativeLayout.LayoutParams(
				screenWidth/2,screenHeight*9/20);
		integralRl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		integralRl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		integralRl.leftMargin = screenWidth/20;
		integralRl.bottomMargin = screenHeight/30;
		integralBook.setLayoutParams(integralRl);
		
		integralTabLayout = (LinearLayout)findViewById(R.id.integralTabLayout);
		RelativeLayout.LayoutParams integralTabRl = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,screenHeight/10);
		integralTabRl.addRule(RelativeLayout.ABOVE,R.id.integralBook);
		integralTabRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.integralBook);
		integralTabRl.addRule(RelativeLayout.ALIGN_RIGHT,R.id.integralBook);
		integralTabLayout.setLayoutParams(integralTabRl);
		
		fightLogo = (ImageView)findViewById(R.id.fightLogo);
		RelativeLayout.LayoutParams fightLogoRl = new RelativeLayout.LayoutParams(
				screenWidth*4/9,screenHeight/4);
		fightLogoRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		fightLogoRl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		fightLogoRl.topMargin = screenHeight/4;
		fightLogoRl.rightMargin = screenWidth/24;
		fightLogo.setLayoutParams(fightLogoRl);
		
		bestPlay = (ImageView)findViewById(R.id.bestPlay);
		RelativeLayout.LayoutParams bestPlayRl = new RelativeLayout.LayoutParams(
				screenHeight/7,screenHeight/7);
		bestPlayRl.addRule(RelativeLayout.BELOW,R.id.fightLogo);
		bestPlayRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.fightLogo);
		bestPlayRl.leftMargin = screenWidth/9;
		bestPlay.setLayoutParams(bestPlayRl);
		
		enterGame = (Button)findViewById(R.id.enterGame);
		RelativeLayout.LayoutParams enterGameRl = new RelativeLayout.LayoutParams(
				screenWidth/3,screenHeight/8);
		enterGameRl.addRule(RelativeLayout.BELOW,R.id.bestPlay);
		enterGameRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.fightLogo);
		enterGameRl.addRule(RelativeLayout.ALIGN_RIGHT,R.id.fightLogo);
		enterGameRl.leftMargin = screenHeight/8;
		enterGameRl.rightMargin = screenHeight/8;
		enterGameRl.topMargin = screenHeight/40;
		enterGameRl.bottomMargin = screenHeight/20;
		enterGame.setLayoutParams(enterGameRl);
		
		listOnTimeI = (Button)findViewById(R.id.listOnTimeI);
		listOnTimeI.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(integralListChange){
					listOnTimeI.setBackgroundResource(R.drawable.zhengfen);
					listOnNumI.setBackgroundResource(R.drawable.yanji1);
					rankingListTLayout.setVisibility(View.VISIBLE);
					rankingListNLayout.setVisibility(View.GONE);
					integralListChange = false;
				}
			}
		});
		
		listOnNumI = (Button)findViewById(R.id.listOnNumI);
		listOnNumI.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!integralListChange){
					listOnNumI.setBackgroundResource(R.drawable.yanji);
					listOnTimeI.setBackgroundResource(R.drawable.zhengfen1);
					rankingListNLayout.setVisibility(View.VISIBLE);
					rankingListTLayout.setVisibility(View.GONE);
					integralListChange = true;
				}
			}
		});
		
		enterGame = (Button)findViewById(R.id.enterGame);
		enterGame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!integralListChange){
					startActivity(new Intent(OnlinePlatformActivity.this,OnlineTimeGameActivity.class));
				}else{
					startActivity(new Intent(OnlinePlatformActivity.this,OnlineCountGameActivity.class));
				}
			}
		});
		
///////////////////////////////////////////////////////////////////////////////////////////////
		
///////////////////////////////////////邀战模式//////////////////////////////////////////////////

		myFriendListLayout = (LinearLayout)findViewById(R.id.myFriendListLayout);
		onlinePlayerListLayout = (LinearLayout)findViewById(R.id.onlinePlayerListLayout);
		onlinePlayerListLayout.setVisibility(View.GONE);
		
		challengeBook = (ImageView)findViewById(R.id.challengeBook);
		RelativeLayout.LayoutParams challengeRl = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, screenHeight/2);
		challengeRl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//		challengeRl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//		challengeRl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//		challengeRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		challengeRl.leftMargin = screenWidth/20;
		challengeRl.rightMargin = screenWidth/20;
		challengeRl.bottomMargin = screenHeight/30;
		challengeBook.setLayoutParams(challengeRl);
		
		challengeTabLayout = (LinearLayout)findViewById(R.id.challengeTabLayout);
		RelativeLayout.LayoutParams challengeTabRl = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,screenHeight/10);
		challengeTabRl.addRule(RelativeLayout.ABOVE,R.id.challengeBook);
		challengeTabRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.challengeBook);
		challengeTabRl.addRule(RelativeLayout.ALIGN_RIGHT,R.id.challengeBook);
		challengeTabLayout.setLayoutParams(challengeTabRl);

		listOfOwn = (Button)findViewById(R.id.listOfOwn);
		listOfOwn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(challengeListChange){
					listOfOwn.setBackgroundResource(R.drawable.onlinefri);
					listOfLine.setBackgroundResource(R.drawable.onlineother1);
					myFriendListLayout.setVisibility(View.VISIBLE);
					onlinePlayerListLayout.setVisibility(View.GONE);
					challengeListChange = false;
				}
			}
		});
		
		listOfLine = (Button)findViewById(R.id.listOfLine);
		listOfLine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!challengeListChange){
					listOfLine.setBackgroundResource(R.drawable.onlineother);
					listOfOwn.setBackgroundResource(R.drawable.onlinefri1);
					rankingListNLayout.setVisibility(View.VISIBLE);
					onlinePlayerListLayout.setVisibility(View.GONE);
					challengeListChange = true;
				}
			}
		});
		
//////////////////////////////////////////////////////////////////////////////////////////////
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		this.detector.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) { 
            // 取得左右滑动时手指按下的X坐标 
            touchDownX = event.getX(); 
            return true; 
        } else if (event.getAction() == MotionEvent.ACTION_UP) { 
            // 取得左右滑动时手指松开的X坐标 
            touchUpX = event.getX(); 
            // 从左往右，看前一个View 
            if (touchUpX - touchDownX > 100) { 
                // 设置View切换的动画 
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, 
                        R.anim.slide_in_left)); 
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, 
                        R.anim.slide_out_right)); 
                // 显示下一个View 
                viewFlipper.showPrevious(); 
                // 从右往左，看后一个View 
            } else if (touchDownX - touchUpX > 100) { 
                // 设置View切换的动画 
                // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right 
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, 
                        R.anim.slide_in_right)); 
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, 
                        R.anim.slide_out_left)); 
                // 显示前一个View 
                viewFlipper.showNext(); 
            } 
            return true; 
        } 
        return false;
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			leftBt.startAnimation(AnimationUtils.loadAnimation(
					OnlinePlatformActivity.this, R.anim.fade_out));
			rightBt.startAnimation(AnimationUtils.loadAnimation(
					OnlinePlatformActivity.this, R.anim.fade_out));

			leftBt.setVisibility(View.GONE);
			rightBt.setVisibility(View.GONE);
		}
	};

	@Override
	public boolean onDown(MotionEvent e) {
		leftBt.setVisibility(View.VISIBLE);
		rightBt.setVisibility(View.VISIBLE);

		leftBt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
		rightBt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
		
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1500);
					mHandler.sendEmptyMessage(0);
				} catch (Exception e) {
					;
				}
			}
		}.start();
		
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {	
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}
	
	public class IntegralTAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public IntegralTAdapter(Context c) {
			this.inflater = LayoutInflater.from(c);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return integralTList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View myView=inflater.inflate(R.layout.items4, null);  
			
			listNoT = (TextView)myView.findViewById(R.id.listNo);
			listNoT.setTextAppearance(OnlinePlatformActivity.this, R.style.textSize);
			
			listNickT = (TextView)myView.findViewById(R.id.listNick);
			listNickT.setTextAppearance(OnlinePlatformActivity.this, R.style.textSize);
			
			listScoreT = (TextView)myView.findViewById(R.id.listScore);
			listScoreT.setTextAppearance(OnlinePlatformActivity.this, R.style.textSize);
			
			
			return myView;
		}

	}
	
	public class IntegralNAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public IntegralNAdapter(Context c) {
			this.inflater = LayoutInflater.from(c);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return integralNList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View myView=inflater.inflate(R.layout.items4, null);  
			
			listNoN = (TextView)myView.findViewById(R.id.listNo);
			listNoN.setTextAppearance(OnlinePlatformActivity.this, R.style.textSize);
			
			listNickN = (TextView)myView.findViewById(R.id.listNick);
			listNickN.setTextAppearance(OnlinePlatformActivity.this, R.style.textSize);
			
			listScoreN = (TextView)myView.findViewById(R.id.listScore);
			listScoreN.setTextAppearance(OnlinePlatformActivity.this, R.style.textSize);
			
			
			return myView;
		}

	}
	
	public class ChallengeMFAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private Button inviteToPlay;

		public ChallengeMFAdapter(Context c) {
			this.inflater = LayoutInflater.from(c);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return challengeMFList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View myView=inflater.inflate(R.layout.items5, null);  
			
			inviteNickMF = (TextView)myView.findViewById(R.id.inviteNick);
			inviteNickMF.setTextAppearance(OnlinePlatformActivity.this, R.style.textSize);
			inviteNickMF.setLayoutParams(new LinearLayout.LayoutParams(
					screenWidth/2,ViewGroup.LayoutParams.WRAP_CONTENT));
			
			inviteOperateLayout = (LinearLayout)myView.findViewById(R.id.inviteOperateLayout);
			inviteOperateLayout.setLayoutParams(new LinearLayout.LayoutParams(
					screenWidth/2,ViewGroup.LayoutParams.WRAP_CONTENT));
			
			inviteToPlay = (Button)myView.findViewById(R.id.inviteToPlay);
			inviteToPlay.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				}
			});
			
			return myView;
		}
	}
	
	public class ChallengeNPAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private Button inviteToPlay;

		public ChallengeNPAdapter(Context c) {
			this.inflater = LayoutInflater.from(c);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return challengeNPList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View myView=inflater.inflate(R.layout.items5, null);  
			
			inviteNickNP = (TextView)myView.findViewById(R.id.inviteNick);
			inviteNickNP.setTextAppearance(OnlinePlatformActivity.this, R.style.textSize);
			inviteNickNP.setLayoutParams(new LinearLayout.LayoutParams(
					screenWidth/2,ViewGroup.LayoutParams.WRAP_CONTENT));
			
			inviteOperateLayout = (LinearLayout)myView.findViewById(R.id.inviteOperateLayout);
			inviteOperateLayout.setLayoutParams(new LinearLayout.LayoutParams(
					screenWidth/2,ViewGroup.LayoutParams.WRAP_CONTENT));
			
			inviteToPlay = (Button)myView.findViewById(R.id.inviteToPlay);
			inviteToPlay.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				}
			});
			return myView;
		}
	}
}
