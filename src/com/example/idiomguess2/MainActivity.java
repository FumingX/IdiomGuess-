package com.example.idiomguess2;

import java.util.HashMap;

import com.util.MyApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private LinearLayout firstGoodLayout, secondGoodLayout, moneyLayout, menuLayout, menu;
	private RelativeLayout goldLayout, systemsetLayout, methodInfoLayout, helpLayout;
	private int screenWidth, screenHeight;
	private Button closeMarket, closeSet, goodOneBuy, goodTwoBuy, musicSet, 
			soundEffectSet, enterMethod, localBreak, onlineFight, personal, 
			myFriend, systset, systmarket, help, logoff;
	private TextView musicText, musicOn, musicOff, soundEffectText, 
			soundEffectOn, soundEffectOff, methodInfo;
	private boolean musicMark = true;
	private boolean soundEffectMark = true;
	private TranslateAnimation translateAnimation;
	private boolean haveAudio = true, haveBackMusic = true;// 判断是否有音效
	private SoundPool soundpool; // 声明一个SoundPool对象
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>(); // 创建一个HashMap对象
	// private MediaPlayer mPlayer;
	private MyApp myApp;
	private boolean animMark = true;
	private boolean modelMark = false;
	
	
	private LinearLayout detailLayout, userNameL, userPwdL, winRateL,
			nowScoreL, confirmLayout;
	private RelativeLayout personalDataLayout;
	private TextView userName, userNameV, userPwd, userPwdV, winRate, winRateV,
			nowScore, nowScoreV, helpMessage;
	private Button closeData, changePwd, confirm, unconfirm, closeHelp;
	private EditText nowPwd, newPwd, newPwdRe;
	private String[] modelHelp = new String[] {
			"模式介绍：\n过关斩将：单机闯关模式，在规定时间内找到9个成语即可进入下一关，共108关，每消除1个得100分，连击及时间剩余可加分，按照最终分数获取星星金币。",
			"模式介绍：\n征战四方：网络对战模式，分为一决高下与勇攀高峰两个子模式。\n（1）一决高下：进入模式后先选择对手，可从在线好友中选取，也可从大厅在线玩家中选取，在规定时间内消除个数最多者为胜，当版面中9个成语消除完毕后，系统会自动补充。\n（2）勇攀高峰：分争分夺秒与眼疾手快两个子模式，争分夺秒即在规定时间内，每消除一个得100分，连击有分数奖励；眼疾手快即消除9个成语，连击及剩余时间会有分数奖励。最终将最高比分传上排行榜，与好友竞争。" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	myApp = (MyApp) getApplicationContext();
		haveAudio = myApp.isHaveAudio();
		haveBackMusic = myApp.isHaveBackMusic();
		soundpool = new SoundPool(3, AudioManager.STREAM_SYSTEM, 0); // 创建一个SoundPool对象，该对象可以容纳5个音频流
		// 将要播放的音频流保存到HashMap对象中
		soundmap.put(2, soundpool.load(this, R.raw.xiaoqu_audio, 1));
		soundmap.put(3, soundpool.load(this, R.raw.idiom_btn_audio, 1));
		soundmap.put(4, soundpool.load(this, R.raw.other_btn_audio, 1));

		if (myApp.duizhanPlayer == null) {
			myApp.duizhanPlayer = MediaPlayer.create(this, R.raw.duizhan_music);
		} else {
            myApp.duizhanPlayer.pause();
		}
		if (myApp.chuangguanPlayer == null) {
			myApp.chuangguanPlayer = MediaPlayer.create(this,
					R.raw.chuangguan_music);
		} else {
			myApp.chuangguanPlayer.pause();
		}
		if (myApp.startPlayer == null) {
			myApp.startPlayer = MediaPlayer.create(this, R.raw.start_music);
			if (haveBackMusic) {
				myApp.startPlayer.start();
				myApp.startPlayer.setLooping(true);
			}
		} else {
			if (haveBackMusic) {
				if (!myApp.startPlayer.isPlaying()) {
					myApp.startPlayer.start();
					myApp.startPlayer.setLooping(true);
				}
			}
		}
		if (myApp.shuafenPlayer == null) {
			myApp.shuafenPlayer = MediaPlayer.create(this, R.raw.shuafen_music);
		} else {
			myApp.shuafenPlayer.pause();
		}
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）  
		
        firstGoodLayout = (LinearLayout)findViewById(R.id.firstGoodLayout);
        secondGoodLayout = (LinearLayout)findViewById(R.id.secondGoodLayout);
        methodInfoLayout = (RelativeLayout)findViewById(R.id.methodInfoLayout);
        
        RelativeLayout.LayoutParams methodInfoLy = new RelativeLayout.LayoutParams(
        		screenWidth*11/20,ViewGroup.LayoutParams.WRAP_CONTENT);
        methodInfoLy.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        methodInfoLy.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        methodInfoLy.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        methodInfoLy.rightMargin = screenWidth/20;
        methodInfoLy.topMargin = screenHeight*21/100;
        methodInfoLy.bottomMargin = screenHeight/20;
        methodInfoLayout.setLayoutParams(methodInfoLy);
        
        methodInfo = (TextView)findViewById(R.id.methodInfo);
        methodInfo.setTextAppearance(this, R.style.textSize);
        methodInfo.setMovementMethod(new ScrollingMovementMethod());
        methodInfo.setText(modelHelp[0]);
        
        enterMethod = (Button)findViewById(R.id.enterMethod);
        RelativeLayout.LayoutParams enterMethodLy = new RelativeLayout.LayoutParams(
        		screenWidth*11/75,screenHeight*12/175);
        enterMethodLy.addRule(RelativeLayout.CENTER_HORIZONTAL);
        enterMethodLy.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        enterMethodLy.bottomMargin = screenHeight*12/175;
        enterMethod.setLayoutParams(enterMethodLy);
        
        localBreak = (Button)findViewById(R.id.localBreak);
        RelativeLayout.LayoutParams localBreakLy = new RelativeLayout.LayoutParams(
        		screenWidth/3,screenHeight/4);
        localBreakLy.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        localBreakLy.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        localBreakLy.leftMargin = screenWidth/30;
        localBreakLy.topMargin = screenHeight/4;
        localBreak.setLayoutParams(localBreakLy);
        
        onlineFight = (Button)findViewById(R.id.onlineFight);
        onlineFight.setVisibility(View.GONE);
        RelativeLayout.LayoutParams onlineFightLy = new RelativeLayout.LayoutParams(
        		screenWidth/3,screenHeight/4);
        onlineFightLy.addRule(RelativeLayout.ALIGN_LEFT,R.id.localBreak);
        onlineFightLy.addRule(RelativeLayout.ALIGN_RIGHT,R.id.localBreak);
        onlineFightLy.addRule(RelativeLayout.BELOW,R.id.localBreak);
        onlineFight.setLayoutParams(onlineFightLy);
        
        RelativeLayout.LayoutParams firstLy = new RelativeLayout.LayoutParams(
        		screenWidth/5,screenHeight/2);
        firstLy.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        firstLy.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        firstLy.leftMargin = screenWidth/5;
        firstLy.topMargin = screenHeight/5;
        firstGoodLayout.setLayoutParams(firstLy);
        
        RelativeLayout.LayoutParams secondLy = new RelativeLayout.LayoutParams(
        		screenWidth/5,screenHeight/2);
        secondGoodLayout.setLayoutParams(secondLy);
        secondLy.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        secondLy.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        secondLy.rightMargin = screenWidth/5;
        secondLy.topMargin = screenHeight/5;
        secondGoodLayout.setLayoutParams(secondLy);
        
        moneyLayout = (LinearLayout)findViewById(R.id.moneyLayout);
        RelativeLayout.LayoutParams moneyLy = new RelativeLayout.LayoutParams(
        		ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        moneyLy.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.goods);
        moneyLy.addRule(RelativeLayout.CENTER_HORIZONTAL);
        moneyLy.bottomMargin = screenHeight/6;
        moneyLayout.setLayoutParams(moneyLy);
        
        goldLayout = (RelativeLayout)findViewById(R.id.goldLayout);
        systemsetLayout = (RelativeLayout)findViewById(R.id.systemsetLayout);
        personalDataLayout = (RelativeLayout)findViewById(R.id.personalDataLayout);
        helpLayout = (RelativeLayout)findViewById(R.id.helpLayout);
        
        closeData = (Button)findViewById(R.id.closeData);
        RelativeLayout.LayoutParams closeDataRl = new RelativeLayout.LayoutParams(
        		screenHeight/10,screenHeight/10);
        closeDataRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.personalData);
        closeDataRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        closeDataRl.topMargin = screenHeight/10;
        closeDataRl.leftMargin = screenHeight/20;
        closeData.setLayoutParams(closeDataRl);
        
        closeMarket = (Button)findViewById(R.id.closeMarket);
        RelativeLayout.LayoutParams closeMarketRl = new RelativeLayout.LayoutParams(
        		screenHeight/10,screenHeight/10);
        closeMarketRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.goods);
        closeMarketRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        closeMarketRl.topMargin = screenHeight/10;
        closeMarketRl.leftMargin = screenHeight/20;
        closeMarket.setLayoutParams(closeMarketRl);
        
        closeHelp = (Button)findViewById(R.id.closeHelp);
        RelativeLayout.LayoutParams closeHelptRl = new RelativeLayout.LayoutParams(
        		screenHeight/10,screenHeight/10);
        closeHelptRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.goods);
        closeHelptRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        closeHelptRl.topMargin = screenHeight/10;
        closeHelptRl.leftMargin = screenHeight/20;
        closeHelp.setLayoutParams(closeHelptRl);
        
        goodOneBuy = (Button)findViewById(R.id.goodOneBuy);
        goodTwoBuy = (Button)findViewById(R.id.goodTwoBuy);
        
        musicText = (TextView)findViewById(R.id.musicText);
        musicText.setLayoutParams(new LinearLayout.LayoutParams(
        		screenWidth/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        musicOn = (TextView)findViewById(R.id.musicOn);
        musicOn.setLayoutParams(new LinearLayout.LayoutParams(
        		screenWidth/20, ViewGroup.LayoutParams.WRAP_CONTENT));
        musicOff = (TextView)findViewById(R.id.musicOff);
        musicOff.setLayoutParams(new LinearLayout.LayoutParams(
        		screenWidth/20, ViewGroup.LayoutParams.WRAP_CONTENT));
        musicSet = (Button)findViewById(R.id.musicSet);
        musicSet.setLayoutParams(new LinearLayout.LayoutParams(
        		screenWidth/5, ViewGroup.LayoutParams.WRAP_CONTENT));
        
        soundEffectText = (TextView)findViewById(R.id.soundEffectText);
        soundEffectText.setLayoutParams(new LinearLayout.LayoutParams(
        		screenWidth/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        soundEffectOn = (TextView)findViewById(R.id.soundEffectOn);
        soundEffectOn.setLayoutParams(new LinearLayout.LayoutParams(
        		screenWidth/20, ViewGroup.LayoutParams.WRAP_CONTENT));
        soundEffectOff = (TextView)findViewById(R.id.soundEffectOff);
        soundEffectOff.setLayoutParams(new LinearLayout.LayoutParams(
        		screenWidth/20, ViewGroup.LayoutParams.WRAP_CONTENT));
        soundEffectSet = (Button)findViewById(R.id.soundEffectSet);
        soundEffectSet.setLayoutParams(new LinearLayout.LayoutParams(
        		screenWidth/5, ViewGroup.LayoutParams.WRAP_CONTENT));
        
        closeSet = (Button)findViewById(R.id.closeSet);
        RelativeLayout.LayoutParams closeSetRl = new RelativeLayout.LayoutParams(
        		screenHeight/10,screenHeight/10);
        closeSetRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.goods);
        closeSetRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        closeSetRl.topMargin = screenHeight/10;
        closeSetRl.leftMargin = screenHeight/20;
        closeSet.setLayoutParams(closeSetRl);

        
        menuLayout = (LinearLayout) findViewById(R.id.menuMainLayout);
		menuLayout.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, screenHeight * 37 / 203));
		
		menu = (LinearLayout) findViewById(R.id.menuLayout);
        
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
		
		
		detailLayout = (LinearLayout)findViewById(R.id.detailLayout);
		RelativeLayout.LayoutParams detailRl = new RelativeLayout.LayoutParams(
				screenWidth/2, screenHeight*2/3);
		detailRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		detailRl.addRule(RelativeLayout.CENTER_VERTICAL);
		detailLayout.setLayoutParams(detailRl);
		
		userName = (TextView)findViewById(R.id.userName);
		userName.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		userNameV = (TextView)findViewById(R.id.userNameV);
		userNameV.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		userNameL = (LinearLayout)findViewById(R.id.userNameL);
		userNameL.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		userPwd = (TextView)findViewById(R.id.userPwd);
		userPwd.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		userPwdV = (TextView)findViewById(R.id.userPwdV);
		userPwdV.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		userPwdL = (LinearLayout)findViewById(R.id.userPwdL);
		userPwdL.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		winRate = (TextView)findViewById(R.id.winRate);
		winRate.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		winRateV = (TextView)findViewById(R.id.winRateV);
		winRateV.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		winRateL = (LinearLayout)findViewById(R.id.winRateL);
		winRateL.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		nowScore = (TextView)findViewById(R.id.nowScore);
		nowScore.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		nowScoreV = (TextView)findViewById(R.id.nowScoreV);
		nowScoreV.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		nowScoreL = (LinearLayout)findViewById(R.id.nowScoreL);
		nowScoreL.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth/6,ViewGroup.LayoutParams.WRAP_CONTENT));
		
		changePwd = (Button)findViewById(R.id.changePwd);
		LinearLayout.LayoutParams changePwdRl = new LinearLayout.LayoutParams(
				screenWidth/10, ViewGroup.LayoutParams.WRAP_CONTENT);
		changePwd.setLayoutParams(changePwdRl);

		confirmLayout = (LinearLayout)findViewById(R.id.confirmLayout);
		confirmLayout.setVisibility(View.GONE);
		RelativeLayout.LayoutParams confirmly = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		confirmly.setMargins(10, 10, 10, 10);
		confirmly.addRule(RelativeLayout.CENTER_VERTICAL);
		confirmly.addRule(RelativeLayout.CENTER_HORIZONTAL);
		confirmLayout.setLayoutParams(confirmly);
		
		
		nowPwd = (EditText)findViewById(R.id.nowPwd);
		newPwd = (EditText)findViewById(R.id.newPwd);
		newPwdRe = (EditText)findViewById(R.id.newPwdRe);
		
		nowPwd.setTextAppearance(this, R.style.newPwdSize);
		newPwd.setTextAppearance(this, R.style.newPwdSize);
		newPwdRe.setTextAppearance(this, R.style.newPwdSize);
		
		helpMessage = (TextView)findViewById(R.id.helpMessage);
		helpMessage.setMovementMethod(new ScrollingMovementMethod());
		helpMessage.setTextAppearance(this, R.style.textSize);
		
		confirm = (Button)findViewById(R.id.confirm);
		unconfirm = (Button)findViewById(R.id.unconfirm);
		
		animation();
		
		personal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				personalDataLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
				personalDataLayout.startAnimation(myAnimation);
			}
		});
		
		myFriend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,FriendActivity.class));
			}
		});
		
		systset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				systemsetLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
				systemsetLayout.startAnimation(myAnimation);
			}
		});
		
		systmarket.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goldLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
				goldLayout.startAnimation(myAnimation);
			}
		});
		
		help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				helpLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
				helpLayout.startAnimation(myAnimation);
			}
		});
		
		logoff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		enterMethod.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!modelMark){
					startActivity(new Intent(MainActivity.this,StoryActivity.class));
				}else{
					startActivity(new Intent(MainActivity.this,OnlinePlatformActivity.class));
				}
			}
		});
		
		localBreak.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(modelMark){
					localBreak.setBackgroundResource(R.drawable.guoguan1);
					onlineFight.setBackgroundResource(R.drawable.zhengzhan);
					methodInfo.setText(modelHelp[0]);
					modelMark = false;
				}
			}
		});
		
		onlineFight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!modelMark){
					onlineFight.setBackgroundResource(R.drawable.zhengzhan1);
					localBreak.setBackgroundResource(R.drawable.guoguan);
					methodInfo.setText(modelHelp[1]);
					modelMark= true;
				}
			}
		});
        
        closeMarket.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Animation myAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_out);
				goldLayout.startAnimation(myAnimation);
				goldLayout.setVisibility(View.GONE);
			}
		});
        
        closeSet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Animation myAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_out);
				systemsetLayout.startAnimation(myAnimation);
				systemsetLayout.setVisibility(View.GONE);
			}
		});
        
        closeData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Animation myAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_out);
				personalDataLayout.startAnimation(myAnimation);
				personalDataLayout.setVisibility(View.GONE);
			}
		});
        
        closeHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Animation myAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_out);
				helpLayout.startAnimation(myAnimation);
				helpLayout.setVisibility(View.GONE);
			}
		});
        
        goodOneBuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
			}
		});
        
        goodTwoBuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
			}
		});
        
        musicSet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(musicMark){
					musicSet.setBackgroundResource(R.drawable.offbtn);
					myApp.stopPlayMusic();
					myApp.setHaveBackMusic(false);
					musicMark = false;
				}else{
					musicSet.setBackgroundResource(R.drawable.onbtn);
					myApp.setHaveBackMusic(true);
					myApp.startPlayer.start();
					musicMark = true;
				}
			}
		});
        
        soundEffectSet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(soundEffectMark){
					soundEffectSet.setBackgroundResource(R.drawable.offbtn);
					soundEffectMark = false;
					myApp.setHaveAudio(false);
				}else{
					soundEffectSet.setBackgroundResource(R.drawable.onbtn);
					soundEffectMark = true;
					myApp.setHaveAudio(true);
				}
			}
		});
        
        changePwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				confirmLayout.setVisibility(View.VISIBLE);
			}
		});
        
        confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			}
		});
        
        unconfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				confirmLayout.setVisibility(View.GONE);
			}
		});
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
						myHandler.sendEmptyMessage(0);
					}
				}).start();
			}
		}
	}
	
	private Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			onlineFight.setVisibility(View.VISIBLE);
			
			translateAnimation = new TranslateAnimation(
					-screenWidth, 0, 0, 0);
			translateAnimation.setDuration(500);

			translateAnimation
					.setAnimationListener(new Animation.AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
						}

						@Override
						public void onAnimationRepeat(Animation animation) {
						}

						@Override
						public void onAnimationEnd(Animation animation) {
							onlineFight.clearAnimation();
						}
					});
			onlineFight.startAnimation(translateAnimation);
		}
	};
	
	private void animation(){
		translateAnimation = new TranslateAnimation(
				0, 0, screenHeight,0);
		translateAnimation.setDuration(500);

		translateAnimation
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						menu.clearAnimation();
					}
				});
		menu.startAnimation(translateAnimation);
		
		translateAnimation = new TranslateAnimation(
				screenWidth, 0, 0, 0);
		translateAnimation.setDuration(500);

		translateAnimation
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						methodInfoLayout.clearAnimation();
					}
				});
		methodInfoLayout.startAnimation(translateAnimation);
		
		translateAnimation = new TranslateAnimation(
				-screenWidth, 0, 0, 0);
		translateAnimation.setDuration(500);

		translateAnimation
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						localBreak.clearAnimation();
					}
				});
		localBreak.startAnimation(translateAnimation);
	}
}
