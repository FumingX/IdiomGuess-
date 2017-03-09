package com.example.idiomguess2;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dao.Dao;
import com.service.SinglePlayerManagement;
import com.util.Barrier;
import com.util.MyApp;
import com.util.Place;

public class LocalGameActivity extends Activity {

	private static final String fontPath = "fonts/font.ttf";
	private String[] promptString = new String[6];
	private boolean[] blockCheck;
	private LinearLayout gameBlock, blockPool, textPool, assistPool, assist,
			prompt, delete, show, operate, levelMessageLayout,
			levelOperateLayout, failmessageLayout, failoperLinearLayout;
	private RelativeLayout showResultLayout, failgameLayout, gamepauseLayout,
			pauseLayout, localOperateLayout, whetherExitLayout;
	private TextView clockView, scoreView, prompNum, deleteNum, speakText;
	private Typeface tf;
	private TextView[] textblockview;
	private Button[] block;
	private CountDownTimer timer;// 用于倒计时
	private SinglePlayerManagement spmnt;
	private int[] textblock = new int[4];
	private boolean isPause = false;
	private Button pauseplay, restart, returns, prop1Btn, prop2Btn, winReplay,
			winBackUp, failReplay, failBackUp, yesExit, noBack;
	private long restTime = 180000;
	private String promptStr;
	private Intent intent;
	private int screenWidth, screenHeight;
	private TextView pauseMessage, scoreMessage, highestMessage, failMessage;
	private RatingBar starBar;
	private boolean haveAudio = true, haveBackMusic = true;// 判断是否有音效
	private SoundPool soundpool; // 声明一个SoundPool对象
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>(); // 创建一个HashMap对象
	// private MediaPlayer mPlayer;
	private MyApp myApp;

	private void findBtnAndSetBtn() {
		block[0] = (Button) findViewById(R.id.localBlock1);
		block[0].setTextAppearance(this, R.style.blockTextSize);
		block[0].setTextColor(Color.WHITE);
		block[0].setTypeface(tf);

		block[1] = (Button) findViewById(R.id.localBlock2);
		block[1].setTextAppearance(this, R.style.blockTextSize);
		block[1].setTextColor(Color.WHITE);
		block[1].setTypeface(tf);

		block[2] = (Button) findViewById(R.id.localBlock3);
		block[2].setTextAppearance(this, R.style.blockTextSize);
		block[2].setTextColor(Color.WHITE);
		block[2].setTypeface(tf);

		block[3] = (Button) findViewById(R.id.localBlock4);
		block[3].setTextAppearance(this, R.style.blockTextSize);
		block[3].setTextColor(Color.WHITE);
		block[3].setTypeface(tf);

		block[4] = (Button) findViewById(R.id.localBlock5);
		block[4].setTextAppearance(this, R.style.blockTextSize);
		block[4].setTextColor(Color.WHITE);
		block[4].setTypeface(tf);

		block[5] = (Button) findViewById(R.id.localBlock6);
		block[5].setTextAppearance(this, R.style.blockTextSize);
		block[5].setTextColor(Color.WHITE);
		block[5].setTypeface(tf);

		block[6] = (Button) findViewById(R.id.localBlock7);
		block[6].setTextAppearance(this, R.style.blockTextSize);
		block[6].setTextColor(Color.WHITE);
		block[6].setTypeface(tf);

		block[7] = (Button) findViewById(R.id.localBlock8);
		block[7].setTextAppearance(this, R.style.blockTextSize);
		block[7].setTextColor(Color.WHITE);
		block[7].setTypeface(tf);

		block[8] = (Button) findViewById(R.id.localBlock9);
		block[8].setTextAppearance(this, R.style.blockTextSize);
		block[8].setTextColor(Color.WHITE);
		block[8].setTypeface(tf);

		block[9] = (Button) findViewById(R.id.localBlock10);
		block[9].setTextAppearance(this, R.style.blockTextSize);
		block[9].setTextColor(Color.WHITE);
		block[9].setTypeface(tf);

		block[10] = (Button) findViewById(R.id.localBlock11);
		block[10].setTextAppearance(this, R.style.blockTextSize);
		block[10].setTextColor(Color.WHITE);
		block[10].setTypeface(tf);

		block[11] = (Button) findViewById(R.id.localBlock12);
		block[11].setTextAppearance(this, R.style.blockTextSize);
		block[11].setTextColor(Color.WHITE);
		block[11].setTypeface(tf);

		block[12] = (Button) findViewById(R.id.localBlock13);
		block[12].setTextAppearance(this, R.style.blockTextSize);
		block[12].setTextColor(Color.WHITE);
		block[12].setTypeface(tf);

		block[13] = (Button) findViewById(R.id.localBlock14);
		block[13].setTextAppearance(this, R.style.blockTextSize);
		block[13].setTextColor(Color.WHITE);
		block[13].setTypeface(tf);

		block[14] = (Button) findViewById(R.id.localBlock15);
		block[14].setTextAppearance(this, R.style.blockTextSize);
		block[14].setTextColor(Color.WHITE);
		block[14].setTypeface(tf);

		block[15] = (Button) findViewById(R.id.localBlock16);
		block[15].setTextAppearance(this, R.style.blockTextSize);
		block[15].setTextColor(Color.WHITE);
		block[15].setTypeface(tf);

		block[16] = (Button) findViewById(R.id.localBlock17);
		block[16].setTextAppearance(this, R.style.blockTextSize);
		block[16].setTextColor(Color.WHITE);
		block[16].setTypeface(tf);

		block[17] = (Button) findViewById(R.id.localBlock18);
		block[17].setTextAppearance(this, R.style.blockTextSize);
		block[17].setTextColor(Color.WHITE);
		block[17].setTypeface(tf);

		block[18] = (Button) findViewById(R.id.localBlock19);
		block[18].setTextAppearance(this, R.style.blockTextSize);
		block[18].setTextColor(Color.WHITE);
		block[18].setTypeface(tf);

		block[19] = (Button) findViewById(R.id.localBlock20);
		block[19].setTextAppearance(this, R.style.blockTextSize);
		block[19].setTextColor(Color.WHITE);
		block[19].setTypeface(tf);

		block[20] = (Button) findViewById(R.id.localBlock21);
		block[20].setTextAppearance(this, R.style.blockTextSize);
		block[20].setTextColor(Color.WHITE);
		block[20].setTypeface(tf);

		block[21] = (Button) findViewById(R.id.localBlock22);
		block[21].setTextAppearance(this, R.style.blockTextSize);
		block[21].setTextColor(Color.WHITE);
		block[21].setTypeface(tf);

		block[22] = (Button) findViewById(R.id.localBlock23);
		block[22].setTextAppearance(this, R.style.blockTextSize);
		block[22].setTextColor(Color.WHITE);
		block[22].setTypeface(tf);

		block[23] = (Button) findViewById(R.id.localBlock24);
		block[23].setTextAppearance(this, R.style.blockTextSize);
		block[23].setTextColor(Color.WHITE);
		block[23].setTypeface(tf);

		block[24] = (Button) findViewById(R.id.localBlock25);
		block[24].setTextAppearance(this, R.style.blockTextSize);
		block[24].setTextColor(Color.WHITE);
		block[24].setTypeface(tf);

		block[25] = (Button) findViewById(R.id.localBlock26);
		block[25].setTextAppearance(this, R.style.blockTextSize);
		block[25].setTextColor(Color.WHITE);
		block[25].setTypeface(tf);

		block[26] = (Button) findViewById(R.id.localBlock27);
		block[26].setTextAppearance(this, R.style.blockTextSize);
		block[26].setTextColor(Color.WHITE);
		block[26].setTypeface(tf);

		block[27] = (Button) findViewById(R.id.localBlock28);
		block[27].setTextAppearance(this, R.style.blockTextSize);
		block[27].setTextColor(Color.WHITE);
		block[27].setTypeface(tf);

		block[28] = (Button) findViewById(R.id.localBlock29);
		block[28].setTextAppearance(this, R.style.blockTextSize);
		block[28].setTextColor(Color.WHITE);
		block[28].setTypeface(tf);

		block[29] = (Button) findViewById(R.id.localBlock30);
		block[29].setTextAppearance(this, R.style.blockTextSize);
		block[29].setTextColor(Color.WHITE);
		block[29].setTypeface(tf);

		block[30] = (Button) findViewById(R.id.localBlock31);
		block[30].setTextAppearance(this, R.style.blockTextSize);
		block[30].setTextColor(Color.WHITE);
		block[30].setTypeface(tf);

		block[31] = (Button) findViewById(R.id.localBlock32);
		block[31].setTextAppearance(this, R.style.blockTextSize);
		block[31].setTextColor(Color.WHITE);
		block[31].setTypeface(tf);

		block[32] = (Button) findViewById(R.id.localBlock33);
		block[32].setTextAppearance(this, R.style.blockTextSize);
		block[32].setTextColor(Color.WHITE);
		block[32].setTypeface(tf);

		block[33] = (Button) findViewById(R.id.localBlock34);
		block[33].setTextAppearance(this, R.style.blockTextSize);
		block[33].setTextColor(Color.WHITE);
		block[33].setTypeface(tf);

		block[34] = (Button) findViewById(R.id.localBlock35);
		block[34].setTextAppearance(this, R.style.blockTextSize);
		block[34].setTextColor(Color.WHITE);
		block[34].setTypeface(tf);

		block[35] = (Button) findViewById(R.id.localBlock36);
		block[35].setTextAppearance(this, R.style.blockTextSize);
		block[35].setTextColor(Color.WHITE);
		block[35].setTypeface(tf);

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.localgame);
		spmnt = new SinglePlayerManagement(getBaseContext());// 管理类
		intent = this.getIntent();// 获得intent
		int barrierId = intent.getIntExtra("barrierId", 0);
		spmnt.startGame(barrierId);// 这里的关卡是需要变的
		promptString[0] = "连击越多分数越高哦！";
		promptString[1] = "听说人长得帅分数高呢！";
		promptString[2] = "不同的关卡模式不同哦！";
		promptString[3] = "你知道这些成语是什么朝代的吗？";
		promptString[4] = "头发长见识短吗？";
		promptString[5] = "你能通过吗";

		String myDynasty = spmnt.getDynastic();
		boolean isRenwu = spmnt.isPerson();
		if (isRenwu) {
			String peopleName = spmnt.getPersonName(myDynasty);
			promptStr = "您现在穿越到" + myDynasty + "朝代了哦.您当前角色是" + peopleName
					+ "，来找找描写你的成语吧！";
		} else {
			promptStr = "您现在穿越到" + myDynasty + "朝代了哦,快找找这个朝代的成语吧。";
		}

		// 初始化
		tf = Typeface.createFromAsset(getAssets(), fontPath);

		textblockview = new TextView[4];
		textblockview[0] = (TextView) findViewById(R.id.localBlockText1);
		textblockview[1] = (TextView) findViewById(R.id.localBlockText2);
		textblockview[2] = (TextView) findViewById(R.id.localBlockText3);
		textblockview[3] = (TextView) findViewById(R.id.localBlockText4);
		textblockview[0].setTextColor(Color.BLACK);
		textblockview[1].setTextColor(Color.BLACK);
		textblockview[2].setTextColor(Color.BLACK);
		textblockview[3].setTextColor(Color.BLACK);
		textblockview[0].setTypeface(tf);
		textblockview[1].setTypeface(tf);
		textblockview[2].setTypeface(tf);
		textblockview[3].setTypeface(tf);
		textblock = new int[4];
		for (int i = 0; i < 4; i++) {
			textblock[i] = -1;
		}
		clockView = (TextView) findViewById(R.id.localBlock);
		scoreView = (TextView) findViewById(R.id.localScore);
		speakText = (TextView) findViewById(R.id.localSpeakText);

		blockCheck = new boolean[36];
		block = new Button[36];

		blockCheck = new boolean[36];

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）

		show = (LinearLayout) findViewById(R.id.localShow);
		show.setPadding(screenWidth / 20, screenWidth / 40, 0, 0);
		LinearLayout.LayoutParams showLy = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, screenHeight * 2 / 7);
		show.setLayoutParams(showLy);

		localOperateLayout = (RelativeLayout)findViewById(R.id.localOperateLayout);
		LinearLayout.LayoutParams localOperateRl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		localOperateRl.bottomMargin = screenHeight/15;
		localOperateLayout.setLayoutParams(localOperateRl);
		
		operate = (LinearLayout) findViewById(R.id.operate);
		// show.setPadding(screenWidth/20, screenWidth/40, 0, 0);
		RelativeLayout.LayoutParams operateLy = new RelativeLayout.LayoutParams(
				screenHeight / 4, screenHeight / 3);
		operateLy.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		operateLy.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		operateLy.bottomMargin = 10;
		operate.setLayoutParams(operateLy);
		
		RelativeLayout.LayoutParams speakTextLy = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, screenHeight*7/24);
		speakTextLy.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		speakTextLy.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		speakTextLy.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		speakTextLy.leftMargin = screenWidth/24;
		speakText.setLayoutParams(speakTextLy);

		gameBlock = (LinearLayout) findViewById(R.id.localGameBlock);
		LinearLayout.LayoutParams gmbk = new LinearLayout.LayoutParams(
				screenHeight * 4 / 5, ViewGroup.LayoutParams.MATCH_PARENT);
		gmbk.topMargin = screenHeight / 20;
		gmbk.rightMargin = screenHeight / 15;
		gameBlock.setLayoutParams(gmbk);

		assistPool = (LinearLayout) findViewById(R.id.localAssistPool);
		LinearLayout.LayoutParams astplPl = new LinearLayout.LayoutParams(
				screenHeight / 4, ViewGroup.LayoutParams.MATCH_PARENT);
		assistPool.setPadding(screenHeight / 30, 0, screenHeight / 30, 0);
		assistPool.setLayoutParams(astplPl);

		assist = (LinearLayout) findViewById(R.id.localAssist);
		LinearLayout.LayoutParams astPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		assist.setLayoutParams(astPl);

		prompt = (LinearLayout) findViewById(R.id.localPrompt);
		LinearLayout.LayoutParams promptPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 4);
		promptPl.bottomMargin = screenHeight / 25;
		prompt.setLayoutParams(promptPl);

		delete = (LinearLayout) findViewById(R.id.localDelete);
		LinearLayout.LayoutParams deletePl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 4);
		deletePl.topMargin = screenHeight / 25;
		delete.setLayoutParams(deletePl);
		// assist.setPadding(screenHeight / 30, 0, screenHeight / 30, 0);

		blockPool = (LinearLayout) findViewById(R.id.localBlockPool);
		LinearLayout.LayoutParams blkPl = new LinearLayout.LayoutParams(
				screenHeight * 4 / 5, screenHeight * 4 / 5);
		blockPool.setLayoutParams(blkPl);

		textPool = (LinearLayout) findViewById(R.id.localTextPool);
		LinearLayout.LayoutParams textPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		textPl.bottomMargin = screenHeight / 30;
		textPool.setLayoutParams(textPl);

		showResultLayout = (RelativeLayout) findViewById(R.id.showResultLayout);
		showResultLayout.setVisibility(View.GONE);
		
		whetherExitLayout = (RelativeLayout) findViewById(R.id.whetherExitLayout);
		whetherExitLayout.setVisibility(View.GONE);

		levelMessageLayout = (LinearLayout) findViewById(R.id.levelMessageLayout);
		RelativeLayout.LayoutParams levelMesRl = new RelativeLayout.LayoutParams(
				screenWidth * 2 / 5, screenHeight * 3 / 7);
		levelMesRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		levelMesRl.addRule(RelativeLayout.ALIGN_TOP, R.id.resultBack);
		levelMesRl.topMargin = screenHeight / 4;
		levelMessageLayout.setLayoutParams(levelMesRl);

		levelOperateLayout = (LinearLayout) findViewById(R.id.levelOperateLayout);
		RelativeLayout.LayoutParams levelOpeRl = new RelativeLayout.LayoutParams(
				screenWidth * 3 / 10, screenWidth * 3 / 50);
		levelOpeRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		levelOpeRl.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.resultBack);
		levelOpeRl.bottomMargin = screenHeight / 5;
		levelOperateLayout.setLayoutParams(levelOpeRl);

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
			if (haveBackMusic) {
				myApp.duizhanPlayer.start();
				myApp.duizhanPlayer.setLooping(true);
			}
		} else {
			if (haveBackMusic) {
				if (!myApp.duizhanPlayer.isPlaying()) {
					myApp.duizhanPlayer.start();
					myApp.duizhanPlayer.setLooping(true);
				}
			}
		}
		if (myApp.chuangguanPlayer == null) {
			myApp.chuangguanPlayer = MediaPlayer.create(this,
					R.raw.chuangguan_music);
		} else {
			myApp.chuangguanPlayer.pause();
		}
		if (myApp.startPlayer == null) {
			myApp.startPlayer = MediaPlayer.create(this, R.raw.start_music);
		} else {
			myApp.startPlayer.pause();
		}
		if (myApp.shuafenPlayer == null) {
			myApp.shuafenPlayer = MediaPlayer.create(this, R.raw.shuafen_music);
		} else {
			myApp.shuafenPlayer.pause();
		}

		winReplay = (Button) findViewById(R.id.winReplay);
		winReplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					Animation myAnimation = AnimationUtils.loadAnimation(
							LocalGameActivity.this, R.anim.fade_out);
					showResultLayout.startAnimation(myAnimation);
					showResultLayout.setVisibility(View.GONE);
					timer.cancel();
					restTime = 180000;
					timer = new CountDownTimer(restTime, 1000) {
						@Override
						public void onTick(long millisUntilFinished) {
							clockView.setText(String
									.valueOf(millisUntilFinished / 1000) + "s");
							restTime = millisUntilFinished;
							if ((restTime / 1000) % 15 == 0) {
								int speakNum = (int) (Math.random() * 6);
								speakText.setText(promptString[speakNum]);
							}
						}

						@Override
						public void onFinish() {
							clockView.setText("0s");
							failgameLayout.setVisibility(View.VISIBLE);
							Animation myAnimation = AnimationUtils
									.loadAnimation(LocalGameActivity.this,
											R.anim.message_scale_animation);
							failgameLayout.startAnimation(myAnimation);
						}
					};
					timer.start();

					for (int i = 0; i < 4; i++) {// 把已经点中的取消
						if (textblock[i] >= 0) {
							block[textblock[i]]
									.setBackgroundResource(R.drawable.blk);
							block[textblock[i]].setTextColor(Color.WHITE);
							blockCheck[textblock[i]] = false;

							cancleBlock(textblock[i]);
						}
					}

					for (int i = 0; i < 36; i++) {
						block[i].setBackgroundResource(R.drawable.blk);
						block[i].setTextColor(Color.WHITE);
						blockCheck[i] = false;
						cancleBlock(i);
					}
					spmnt.restartGame();
					scoreView.setText(String.valueOf(spmnt.myGrade) + "分");

					show();
					speakText.setText(promptStr);
				}
			}
		});

		winBackUp = (Button) findViewById(R.id.winBackUp);
		winBackUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					Animation myAnimation = AnimationUtils.loadAnimation(
							LocalGameActivity.this, R.anim.fade_out);
					showResultLayout.startAnimation(myAnimation);
					showResultLayout.setVisibility(View.GONE);
					Intent intent1 = new Intent(LocalGameActivity.this,
							StoryActivity.class);
					startActivity(intent1);
				}
			}
		});
		
		yesExit = (Button) findViewById(R.id.yesExit);
		yesExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		noBack = (Button) findViewById(R.id.noBack);
		noBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation myAnimation = AnimationUtils.loadAnimation(
						LocalGameActivity.this, R.anim.fade_out);
				whetherExitLayout.startAnimation(myAnimation);
				whetherExitLayout.setVisibility(View.GONE);
			}
		});

		failgameLayout = (RelativeLayout) findViewById(R.id.failgameLayout);
		failgameLayout.setVisibility(View.GONE);

		failmessageLayout = (LinearLayout) findViewById(R.id.failmessageLayout);
		RelativeLayout.LayoutParams failmessRl = new RelativeLayout.LayoutParams(
				screenWidth * 2 / 5, screenHeight * 3 / 7);
		failmessRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		failmessRl.addRule(RelativeLayout.ALIGN_TOP, R.id.failgame);
		failmessRl.topMargin = screenHeight / 4;
		failmessageLayout.setLayoutParams(failmessRl);

		failoperLinearLayout = (LinearLayout) findViewById(R.id.failoperateLayout);
		RelativeLayout.LayoutParams failopeRl = new RelativeLayout.LayoutParams(
				screenWidth * 3 / 10, screenWidth * 3 / 50);
		failopeRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		failopeRl.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.failgame);
		failopeRl.bottomMargin = screenHeight / 5;
		failoperLinearLayout.setLayoutParams(failopeRl);

		failMessage = (TextView) findViewById(R.id.failmessage);
		failMessage.setText("好可惜呀，时间没了。爸爸说，失败是成功的妈妈。输了不要气馁哦！");

		failReplay = (Button) findViewById(R.id.failreplay);
		failReplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					Animation myAnimation = AnimationUtils.loadAnimation(
							LocalGameActivity.this, R.anim.fade_out);
					failgameLayout.startAnimation(myAnimation);
					failgameLayout.setVisibility(View.GONE);
					timer.cancel();
					restTime = 180000;
					timer = new CountDownTimer(restTime, 1000) {
						@Override
						public void onTick(long millisUntilFinished) {
							clockView.setText(String
									.valueOf(millisUntilFinished / 1000) + "s");
							restTime = millisUntilFinished;
							if ((restTime / 1000) % 15 == 0) {
								int speakNum = (int) (Math.random() * 6);
								speakText.setText(promptString[speakNum]);
							}
						}

						@Override
						public void onFinish() {
							clockView.setText("0s");
							failgameLayout.setVisibility(View.VISIBLE);
							Animation myAnimation = AnimationUtils
									.loadAnimation(LocalGameActivity.this,
											R.anim.message_scale_animation);
							failgameLayout.startAnimation(myAnimation);
						}
					};
					timer.start();

					for (int i = 0; i < 4; i++) {// 把已经点中的取消
						if (textblock[i] >= 0) {
							block[textblock[i]]
									.setBackgroundResource(R.drawable.blk);
							block[textblock[i]].setTextColor(Color.WHITE);
							blockCheck[textblock[i]] = false;

							cancleBlock(textblock[i]);
						}
					}

					for (int i = 0; i < 36; i++) {
						block[i].setBackgroundResource(R.drawable.blk);
						block[i].setTextColor(Color.WHITE);
						blockCheck[i] = false;
						cancleBlock(i);
					}
					spmnt.restartGame();
					scoreView.setText(String.valueOf(spmnt.myGrade) + "分");

					show();
					speakText.setText(promptStr);
				}
			}
		});

		failBackUp = (Button) findViewById(R.id.failbackUp);
		failBackUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					Animation myAnimation = AnimationUtils.loadAnimation(
							LocalGameActivity.this, R.anim.fade_out);
					failgameLayout.startAnimation(myAnimation);
					failgameLayout.setVisibility(View.GONE);
					Intent intent1 = new Intent(LocalGameActivity.this,
							StoryActivity.class);
					startActivity(intent1);
				}
			}
		});

		gamepauseLayout = (RelativeLayout) findViewById(R.id.gamepauseLayout);
		gamepauseLayout.setVisibility(View.GONE);
		RelativeLayout.LayoutParams gamePauRl = new RelativeLayout.LayoutParams(
				screenHeight * 5 / 6, screenHeight * 5 / 6);
		gamePauRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		gamePauRl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		gamePauRl.topMargin = screenHeight / 18;
		gamePauRl.rightMargin = screenHeight / 20;
		gamepauseLayout.setLayoutParams(gamePauRl);

		pauseLayout = (RelativeLayout) findViewById(R.id.pauseLayout);
		RelativeLayout.LayoutParams pauseRl = new RelativeLayout.LayoutParams(
				screenHeight * 2 / 5, screenHeight * 2 / 5);
		pauseRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
		pauseRl.addRule(RelativeLayout.CENTER_VERTICAL);
		pauseLayout.setLayoutParams(pauseRl);

		pauseMessage = (TextView) findViewById(R.id.pauseMessage);
		RelativeLayout.LayoutParams pauseMessRl = new RelativeLayout.LayoutParams(
				screenHeight / 3, screenHeight * 4 / 25);
		pauseMessRl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		pauseMessRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		pauseMessage.setLayoutParams(pauseMessRl);
		pauseMessage.setText("如厕时间不许偷看哦！");

		pauseplay = (Button) findViewById(R.id.localPause);
		restart = (Button) findViewById(R.id.localRestart);
		returns = (Button) findViewById(R.id.localReturns);
		prop1Btn = (Button) findViewById(R.id.localProp1);// 提示两个字符
		prop2Btn = (Button) findViewById(R.id.localProp2);// 直接消去
		prompNum = (TextView) findViewById(R.id.localPromptText);
		deleteNum = (TextView) findViewById(R.id.localDeleteText);
		this.findBtnAndSetBtn();

		speakText.setText(promptStr);
		show();

		pauseplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (isPause) {
					Animation myAnimation = AnimationUtils.loadAnimation(
							LocalGameActivity.this, R.anim.fade_out);
					gamepauseLayout.startAnimation(myAnimation);
					gamepauseLayout.setVisibility(View.GONE);

					pauseplay.setBackgroundResource(R.drawable.pause);
					timer = new CountDownTimer(restTime, 1000) {
						@Override
						public void onTick(long millisUntilFinished) {
							clockView.setText(String
									.valueOf(millisUntilFinished / 1000) + "s");
							restTime = millisUntilFinished;
							if ((restTime / 1000) % 15 == 0) {
								int speakNum = (int) (Math.random() * 6);
								speakText.setText(promptString[speakNum]);
							}
						}

						@Override
						public void onFinish() {
							clockView.setText("0s");
							failgameLayout.setVisibility(View.VISIBLE);
							Animation myAnimation = AnimationUtils
									.loadAnimation(LocalGameActivity.this,
											R.anim.message_scale_animation);
							failgameLayout.startAnimation(myAnimation);
						}
					};

					timer.start();
					isPause = false;
				} else {
					gamepauseLayout.setVisibility(View.VISIBLE);
					Animation myAnimation = AnimationUtils.loadAnimation(
							LocalGameActivity.this, R.anim.fade_in);
					gamepauseLayout.startAnimation(myAnimation);

					pauseplay.setBackgroundResource(R.drawable.play);
					timer.cancel();
					isPause = true;
				}
			}
		});

		restart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					timer.cancel();
					restTime = 180000;
					timer = new CountDownTimer(restTime, 1000) {
						@Override
						public void onTick(long millisUntilFinished) {
							clockView.setText(String
									.valueOf(millisUntilFinished / 1000) + "s");
							restTime = millisUntilFinished;
							if ((restTime / 1000) % 15 == 0) {
								int speakNum = (int) (Math.random() * 6);
								speakText.setText(promptString[speakNum]);
							}
						}

						@Override
						public void onFinish() {
							clockView.setText("0s");
							failgameLayout.setVisibility(View.VISIBLE);
							Animation myAnimation = AnimationUtils
									.loadAnimation(LocalGameActivity.this,
											R.anim.message_scale_animation);
							failgameLayout.startAnimation(myAnimation);
						}
					};
					timer.start();

					for (int i = 0; i < 4; i++) {// 把已经点中的取消
						if (textblock[i] >= 0) {
							block[textblock[i]]
									.setBackgroundResource(R.drawable.blk);
							block[textblock[i]].setTextColor(Color.WHITE);
							blockCheck[textblock[i]] = false;

							cancleBlock(textblock[i]);
						}
					}

					for (int i = 0; i < 36; i++) {
						block[i].setBackgroundResource(R.drawable.blk);
						block[i].setTextColor(Color.WHITE);
						blockCheck[i] = false;
						cancleBlock(i);
					}
					spmnt.restartGame();
					scoreView.setText(String.valueOf(spmnt.myGrade) + "分");

					show();
					speakText.setText(promptStr);
				}
			}
		});

		// 返回键响应事件
		returns.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				whetherExitLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(
						LocalGameActivity.this, R.anim.fade_in);
				whetherExitLayout.startAnimation(myAnimation);
//				if (!isPause) {
//					if (haveAudio) {
//						soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
//					}
//					Intent intent1 = new Intent(LocalGameActivity.this,
//							StoryActivity.class);
//					startActivity(intent1);
//				}
			}
		});

		// 显示道具数量

		int num1 = spmnt.getProperty1Num();
		int num2 = spmnt.getProperty2Num();
		prompNum.setText(String.valueOf(num1));
		deleteNum.setText(String.valueOf(num2));

		prop1Btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					ArrayList<Place> places = spmnt.useProperty1();
					if (places.size() > 0) {
						int p1 = places.get(0).getX() * 6
								+ places.get(0).getY();
						int p2 = places.get(1).getX() * 6
								+ places.get(1).getY();

						for (int i = 0; i < 4; i++) {// 把已经点中的取消
							if (textblock[i] >= 0) {
								block[textblock[i]]
										.setBackgroundResource(R.drawable.blk);
								block[textblock[i]].setTextColor(Color.WHITE);
								blockCheck[textblock[i]] = false;

								cancleBlock(textblock[i]);
							}
						}
						// 显示提示
						block[p1].setBackgroundResource(R.drawable.blk2);
						block[p1].setTextColor(Color.BLACK);
						blockCheck[p1] = true;
						showBlock(p1);

						block[p2].setBackgroundResource(R.drawable.blk2);
						block[p2].setTextColor(Color.BLACK);
						blockCheck[p2] = true;
						showBlock(p2);
						// 更新道具数量
						int num1 = spmnt.getProperty1Num();
						prompNum.setText(String.valueOf(num1));

					}
				}
			}
		});

		// 消去按钮
		prop2Btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					ArrayList<Place> places = spmnt.useProperty2();
					// 如果能够消去
					if (places.size() > 0) {
						int[] p = new int[4];
						p[0] = places.get(0).getX() * 6 + places.get(0).getY();
						p[1] = places.get(1).getX() * 6 + places.get(1).getY();
						p[2] = places.get(2).getX() * 6 + places.get(2).getY();
						p[3] = places.get(3).getX() * 6 + places.get(3).getY();
						// 把已经点中的取消
						for (int i = 0; i < 4; i++) {
							if (textblock[i] >= 0) {
								block[textblock[i]]
										.setBackgroundResource(R.drawable.blk);
								block[textblock[i]].setTextColor(Color.WHITE);
								blockCheck[textblock[i]] = false;

								cancleBlock(textblock[i]);
							}
						}

						for (int i = 0; i < 4; i++) {
							block[p[i]].setBackgroundResource(R.drawable.blk2);
							block[p[i]].setTextColor(Color.BLACK);
							blockCheck[p[i]] = true;
							showBlock(p[i]);
							judgeIdiom();
						}

						// 更新道具数量
						int num2 = spmnt.getProperty2Num();
						deleteNum.setText(String.valueOf(num2));
					}
				}
			}
		});

		scoreView.setText(spmnt.myGrade + "分");
		timer = new CountDownTimer(restTime, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				clockView.setText(String.valueOf(millisUntilFinished / 1000)
						+ "s");
				restTime = millisUntilFinished;
				if ((restTime / 1000) % 15 == 0) {
					int speakNum = (int) (Math.random() * 6);
					speakText.setText(promptString[speakNum]);
				}
			}

			@Override
			public void onFinish() {
				clockView.setText("0s");
				failgameLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(
						LocalGameActivity.this, R.anim.message_scale_animation);
				failgameLayout.startAnimation(myAnimation);
			}
		};
		timer.start();

		block[0].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[0].getText().toString().equals("")) {
					} else if (blockCheck[0]) {
						block[0].setBackgroundResource(R.drawable.blk);
						block[0].setTextColor(Color.WHITE);
						blockCheck[0] = false;
						cancleBlock(0);
					} else {
						block[0].setBackgroundResource(R.drawable.blk2);
						block[0].setTextColor(Color.BLACK);
						blockCheck[0] = true;
						showBlock(0);
						judgeIdiom();
					}
				}
			}
		});

		block[1].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[1].getText().toString().equals("")) {
					} else if (blockCheck[1]) {
						block[1].setBackgroundResource(R.drawable.blk);
						block[1].setTextColor(Color.WHITE);
						blockCheck[1] = false;
						cancleBlock(1);
					} else {
						block[1].setBackgroundResource(R.drawable.blk2);
						block[1].setTextColor(Color.BLACK);
						blockCheck[1] = true;
						showBlock(1);
						judgeIdiom();
					}
				}
			}
		});

		block[2].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[2].getText().toString().equals("")) {
					} else if (blockCheck[2]) {
						block[2].setBackgroundResource(R.drawable.blk);
						block[2].setTextColor(Color.WHITE);
						blockCheck[2] = false;
						cancleBlock(2);
					} else {
						block[2].setBackgroundResource(R.drawable.blk2);
						block[2].setTextColor(Color.BLACK);
						blockCheck[2] = true;
						showBlock(2);
						judgeIdiom();
					}
				}
			}
		});

		block[3].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[3].getText().toString().equals("")) {
					} else if (blockCheck[3]) {
						block[3].setBackgroundResource(R.drawable.blk);
						block[3].setTextColor(Color.WHITE);
						blockCheck[3] = false;
						cancleBlock(3);
					} else {
						block[3].setBackgroundResource(R.drawable.blk2);
						block[3].setTextColor(Color.BLACK);
						blockCheck[3] = true;
						showBlock(3);
						judgeIdiom();
					}
				}
			}
		});

		block[4].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[4].getText().toString().equals("")) {
					} else if (blockCheck[4]) {
						block[4].setBackgroundResource(R.drawable.blk);
						block[4].setTextColor(Color.WHITE);
						blockCheck[4] = false;
						cancleBlock(4);
					} else {
						block[4].setBackgroundResource(R.drawable.blk2);
						block[4].setTextColor(Color.BLACK);
						blockCheck[4] = true;
						showBlock(4);
						judgeIdiom();
					}
				}
			}
		});

		block[5].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[5].getText().toString().equals("")) {
					} else if (blockCheck[5]) {
						block[5].setBackgroundResource(R.drawable.blk);
						block[5].setTextColor(Color.WHITE);
						blockCheck[5] = false;
						cancleBlock(5);
					} else {
						block[5].setBackgroundResource(R.drawable.blk2);
						block[5].setTextColor(Color.BLACK);
						blockCheck[5] = true;
						showBlock(5);
						judgeIdiom();
					}
				}
			}
		});

		block[6].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[6].getText().toString().equals("")) {
					} else if (blockCheck[6]) {
						block[6].setBackgroundResource(R.drawable.blk);
						block[6].setTextColor(Color.WHITE);
						blockCheck[6] = false;
						cancleBlock(6);
					} else {
						block[6].setBackgroundResource(R.drawable.blk2);
						block[6].setTextColor(Color.BLACK);
						blockCheck[6] = true;
						showBlock(6);
						judgeIdiom();
					}
				}
			}
		});

		block[7].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[7].getText().toString().equals("")) {
					} else if (blockCheck[7]) {
						block[7].setBackgroundResource(R.drawable.blk);
						block[7].setTextColor(Color.WHITE);
						blockCheck[7] = false;
						cancleBlock(7);
					} else {
						block[7].setBackgroundResource(R.drawable.blk2);
						block[7].setTextColor(Color.BLACK);
						blockCheck[7] = true;
						showBlock(7);
						judgeIdiom();
					}
				}
			}
		});

		block[8].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[8].getText().toString().equals("")) {
					} else if (blockCheck[8]) {
						block[8].setBackgroundResource(R.drawable.blk);
						block[8].setTextColor(Color.WHITE);
						blockCheck[8] = false;
						cancleBlock(8);
					} else {
						block[8].setBackgroundResource(R.drawable.blk2);
						block[8].setTextColor(Color.BLACK);
						blockCheck[8] = true;
						showBlock(8);
						judgeIdiom();
					}
				}
			}
		});

		block[9].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[9].getText().toString().equals("")) {
					} else if (blockCheck[9]) {
						block[9].setBackgroundResource(R.drawable.blk);
						block[9].setTextColor(Color.WHITE);
						blockCheck[9] = false;
						cancleBlock(9);
					} else {
						block[9].setBackgroundResource(R.drawable.blk2);
						block[9].setTextColor(Color.BLACK);
						blockCheck[9] = true;
						showBlock(9);
						judgeIdiom();
					}
				}
			}
		});

		block[10].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[10].getText().toString().equals("")) {
					} else if (blockCheck[10]) {
						block[10].setBackgroundResource(R.drawable.blk);
						block[10].setTextColor(Color.WHITE);
						blockCheck[10] = false;
						cancleBlock(10);
					} else {
						block[10].setBackgroundResource(R.drawable.blk2);
						block[10].setTextColor(Color.BLACK);
						blockCheck[10] = true;
						showBlock(10);
						judgeIdiom();
					}
				}
			}
		});

		block[11].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[11].getText().toString().equals("")) {
					} else if (blockCheck[11]) {
						block[11].setBackgroundResource(R.drawable.blk);
						block[11].setTextColor(Color.WHITE);
						blockCheck[11] = false;
						cancleBlock(11);
					} else {
						block[11].setBackgroundResource(R.drawable.blk2);
						block[11].setTextColor(Color.BLACK);
						blockCheck[11] = true;
						showBlock(11);
						judgeIdiom();
					}
				}
			}
		});

		block[12].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[12].getText().toString().equals("")) {
					} else if (blockCheck[12]) {
						block[12].setBackgroundResource(R.drawable.blk);
						block[12].setTextColor(Color.WHITE);
						blockCheck[12] = false;
						cancleBlock(12);
					} else {
						block[12].setBackgroundResource(R.drawable.blk2);
						block[12].setTextColor(Color.BLACK);
						blockCheck[12] = true;
						showBlock(12);
						judgeIdiom();
					}
				}
			}
		});

		block[13].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[13].getText().toString().equals("")) {
					} else if (blockCheck[13]) {
						block[13].setBackgroundResource(R.drawable.blk);
						block[13].setTextColor(Color.WHITE);
						blockCheck[13] = false;
						cancleBlock(13);
					} else {
						block[13].setBackgroundResource(R.drawable.blk2);
						block[13].setTextColor(Color.BLACK);
						blockCheck[13] = true;
						showBlock(13);
						judgeIdiom();
					}
				}
			}
		});

		block[14].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[14].getText().toString().equals("")) {
					} else if (blockCheck[14]) {
						block[14].setBackgroundResource(R.drawable.blk);
						block[14].setTextColor(Color.WHITE);
						blockCheck[14] = false;
						cancleBlock(14);
					} else {
						block[14].setBackgroundResource(R.drawable.blk2);
						block[14].setTextColor(Color.BLACK);
						blockCheck[14] = true;
						showBlock(14);
						judgeIdiom();
					}
				}
			}
		});

		block[15].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[15].getText().toString().equals("")) {
					} else if (blockCheck[15]) {
						block[15].setBackgroundResource(R.drawable.blk);
						block[15].setTextColor(Color.WHITE);
						blockCheck[15] = false;
						cancleBlock(15);
					} else {
						block[15].setBackgroundResource(R.drawable.blk2);
						block[15].setTextColor(Color.BLACK);
						blockCheck[15] = true;
						showBlock(15);
						judgeIdiom();
					}
				}
			}
		});

		block[16].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[16].getText().toString().equals("")) {
					} else if (blockCheck[16]) {
						block[16].setBackgroundResource(R.drawable.blk);
						block[16].setTextColor(Color.WHITE);
						blockCheck[16] = false;
						cancleBlock(16);
					} else {
						block[16].setBackgroundResource(R.drawable.blk2);
						block[16].setTextColor(Color.BLACK);
						blockCheck[16] = true;
						showBlock(16);
						judgeIdiom();
					}
				}
			}
		});

		block[17].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[17].getText().toString().equals("")) {
					} else if (blockCheck[17]) {
						block[17].setBackgroundResource(R.drawable.blk);
						block[17].setTextColor(Color.WHITE);
						blockCheck[17] = false;
						cancleBlock(17);
					} else {
						block[17].setBackgroundResource(R.drawable.blk2);
						block[17].setTextColor(Color.BLACK);
						blockCheck[17] = true;
						showBlock(17);
						judgeIdiom();
					}
				}
			}
		});

		block[18].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[18].getText().toString().equals("")) {
					} else if (blockCheck[18]) {
						block[18].setBackgroundResource(R.drawable.blk);
						block[18].setTextColor(Color.WHITE);
						blockCheck[18] = false;
						cancleBlock(18);
					} else {
						block[18].setBackgroundResource(R.drawable.blk2);
						block[18].setTextColor(Color.BLACK);
						blockCheck[18] = true;
						showBlock(18);
						judgeIdiom();
					}
				}
			}
		});

		block[19].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (block[19].getText().toString().equals("")) {
					} else if (blockCheck[19]) {
						if (haveAudio) {
							soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
						}
						block[19].setBackgroundResource(R.drawable.blk);
						block[19].setTextColor(Color.WHITE);
						blockCheck[19] = false;
						cancleBlock(19);
					} else {
						block[19].setBackgroundResource(R.drawable.blk2);
						block[19].setTextColor(Color.BLACK);
						blockCheck[19] = true;
						showBlock(19);
						judgeIdiom();
					}
				}
			}
		});

		block[20].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[20].getText().toString().equals("")) {
					} else if (blockCheck[20]) {
						block[20].setBackgroundResource(R.drawable.blk);
						block[20].setTextColor(Color.WHITE);
						blockCheck[20] = false;
						cancleBlock(20);
					} else {
						block[20].setBackgroundResource(R.drawable.blk2);
						block[20].setTextColor(Color.BLACK);
						blockCheck[20] = true;
						showBlock(20);
						judgeIdiom();
					}
				}
			}
		});

		block[21].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[21].getText().toString().equals("")) {
					} else if (blockCheck[21]) {
						block[21].setBackgroundResource(R.drawable.blk);
						block[21].setTextColor(Color.WHITE);
						blockCheck[21] = false;
						cancleBlock(21);
					} else {
						block[21].setBackgroundResource(R.drawable.blk2);
						block[21].setTextColor(Color.BLACK);
						blockCheck[21] = true;
						showBlock(21);
						judgeIdiom();
					}
				}
			}
		});

		block[22].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[22].getText().toString().equals("")) {
					} else if (blockCheck[22]) {
						block[22].setBackgroundResource(R.drawable.blk);
						block[22].setTextColor(Color.WHITE);
						blockCheck[22] = false;
						cancleBlock(22);
					} else {
						block[22].setBackgroundResource(R.drawable.blk2);
						block[22].setTextColor(Color.BLACK);
						blockCheck[22] = true;
						showBlock(22);
						judgeIdiom();
					}
				}
			}
		});

		block[23].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[23].getText().toString().equals("")) {
					} else if (blockCheck[23]) {
						block[23].setBackgroundResource(R.drawable.blk);
						block[23].setTextColor(Color.WHITE);
						blockCheck[23] = false;
						cancleBlock(23);
					} else {
						block[23].setBackgroundResource(R.drawable.blk2);
						block[23].setTextColor(Color.BLACK);
						blockCheck[23] = true;
						showBlock(23);
						judgeIdiom();
					}
				}
			}
		});

		block[24].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[24].getText().toString().equals("")) {
					} else if (blockCheck[24]) {
						block[24].setBackgroundResource(R.drawable.blk);
						block[24].setTextColor(Color.WHITE);
						blockCheck[24] = false;
						cancleBlock(24);
					} else {
						block[24].setBackgroundResource(R.drawable.blk2);
						block[24].setTextColor(Color.BLACK);
						blockCheck[24] = true;
						showBlock(24);
						judgeIdiom();
					}
				}
			}
		});

		block[25].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[25].getText().toString().equals("")) {
					} else if (blockCheck[25]) {
						block[25].setBackgroundResource(R.drawable.blk);
						block[25].setTextColor(Color.WHITE);
						blockCheck[25] = false;
						cancleBlock(25);
					} else {
						block[25].setBackgroundResource(R.drawable.blk2);
						block[25].setTextColor(Color.BLACK);
						blockCheck[25] = true;
						showBlock(25);
						judgeIdiom();
					}
				}
			}
		});

		block[26].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[26].getText().toString().equals("")) {
					} else if (blockCheck[26]) {
						block[26].setBackgroundResource(R.drawable.blk);
						block[26].setTextColor(Color.WHITE);
						blockCheck[26] = false;
						cancleBlock(26);
					} else {
						block[26].setBackgroundResource(R.drawable.blk2);
						block[26].setTextColor(Color.BLACK);
						blockCheck[26] = true;
						showBlock(26);
						judgeIdiom();
					}
				}
			}
		});

		block[27].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[27].getText().toString().equals("")) {
					} else if (blockCheck[27]) {
						block[27].setBackgroundResource(R.drawable.blk);
						block[27].setTextColor(Color.WHITE);
						blockCheck[27] = false;
						cancleBlock(27);
					} else {
						block[27].setBackgroundResource(R.drawable.blk2);
						block[27].setTextColor(Color.BLACK);
						blockCheck[27] = true;
						showBlock(27);
						judgeIdiom();
					}
				}
			}
		});

		block[28].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[28].getText().toString().equals("")) {
					} else if (blockCheck[28]) {
						block[28].setBackgroundResource(R.drawable.blk);
						block[28].setTextColor(Color.WHITE);
						blockCheck[28] = false;
						cancleBlock(28);
					} else {
						block[28].setBackgroundResource(R.drawable.blk2);
						block[28].setTextColor(Color.BLACK);
						blockCheck[28] = true;
						showBlock(28);
						judgeIdiom();
					}
				}
			}
		});

		block[29].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[29].getText().toString().equals("")) {
					} else if (blockCheck[29]) {
						block[29].setBackgroundResource(R.drawable.blk);
						block[29].setTextColor(Color.WHITE);
						blockCheck[29] = false;
						cancleBlock(29);
					} else {
						block[29].setBackgroundResource(R.drawable.blk2);
						block[29].setTextColor(Color.BLACK);
						blockCheck[29] = true;
						showBlock(29);
						judgeIdiom();
					}
				}
			}
		});

		block[30].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[30].getText().toString().equals("")) {
					} else if (blockCheck[30]) {
						block[30].setBackgroundResource(R.drawable.blk);
						block[30].setTextColor(Color.WHITE);
						blockCheck[30] = false;
						cancleBlock(30);
					} else {
						block[30].setBackgroundResource(R.drawable.blk2);
						block[30].setTextColor(Color.BLACK);
						blockCheck[30] = true;
						showBlock(30);
						judgeIdiom();
					}
				}
			}
		});

		block[31].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[31].getText().toString().equals("")) {
					} else if (blockCheck[31]) {
						block[31].setBackgroundResource(R.drawable.blk);
						block[31].setTextColor(Color.WHITE);
						blockCheck[31] = false;
						cancleBlock(31);
					} else {
						block[31].setBackgroundResource(R.drawable.blk2);
						block[31].setTextColor(Color.BLACK);
						blockCheck[31] = true;
						showBlock(31);
						judgeIdiom();
					}
				}
			}
		});

		block[32].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[32].getText().toString().equals("")) {
					} else if (blockCheck[32]) {
						block[32].setBackgroundResource(R.drawable.blk);
						block[32].setTextColor(Color.WHITE);
						blockCheck[32] = false;
						cancleBlock(32);
					} else {
						block[32].setBackgroundResource(R.drawable.blk2);
						block[32].setTextColor(Color.BLACK);
						blockCheck[32] = true;
						showBlock(32);
						judgeIdiom();
					}
				}
			}
		});

		block[33].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[33].getText().toString().equals("")) {
					} else if (blockCheck[33]) {
						block[33].setBackgroundResource(R.drawable.blk);
						block[33].setTextColor(Color.WHITE);
						blockCheck[33] = false;
						cancleBlock(33);
					} else {
						block[33].setBackgroundResource(R.drawable.blk2);
						block[33].setTextColor(Color.BLACK);
						blockCheck[33] = true;
						showBlock(33);
						judgeIdiom();
					}
				}
			}
		});

		block[34].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[34].getText().toString().equals("")) {
					} else if (blockCheck[34]) {
						block[34].setBackgroundResource(R.drawable.blk);
						block[34].setTextColor(Color.WHITE);
						blockCheck[34] = false;
						cancleBlock(34);
					} else {
						block[34].setBackgroundResource(R.drawable.blk2);
						block[34].setTextColor(Color.BLACK);
						blockCheck[34] = true;
						showBlock(34);
						judgeIdiom();
					}
				}
			}
		});

		block[35].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isPause) {
					if (haveAudio) {
						soundpool.play(soundmap.get(3), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					if (block[35].getText().toString().equals("")) {
					} else if (blockCheck[35]) {
						block[35].setBackgroundResource(R.drawable.blk);
						block[35].setTextColor(Color.WHITE);
						blockCheck[35] = false;
						cancleBlock(35);
					} else {
						block[35].setBackgroundResource(R.drawable.blk2);
						block[35].setTextColor(Color.BLACK);
						blockCheck[35] = true;
						showBlock(35);
						judgeIdiom();
					}
				}
			}
		});
	}

	private void show() {
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++) {
				block[i * 6 + j].setText(String.valueOf(spmnt.chars[i][j]));
				if (spmnt.chars[i][j] == ' ') {
					block[i * 6 + j].setBackgroundColor(Color.WHITE);
					block[i * 6 + j].setText("");
				}
			}
	}

	private void showBlock(int num) {
		for (int i = 0; i < 4; i++) {
			if (textblock[i] == -1) {
				textblock[i] = num;
				textblockview[i].setText(block[num].getText());
				break;
			}
		}
	}

	private void cancleBlock(int num) {
		for (int i = 0; i < 4; i++) {
			if (textblock[i] == num) {
				textblock[i] = -1;
				textblockview[i].setText("");
				break;
			}
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent1 = new Intent(LocalGameActivity.this, StoryActivity.class);
		startActivity(intent1);
		super.onBackPressed();
	}

	// 判断是否选出四字成语以及成语是否正确
	public void judgeIdiom() {
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if (textblock[i] == -1) {
				flag = false;
				break;
			}
		}
		// 如果已经选中了四个字
		if (flag) {
			boolean judge = spmnt.judge(textblock[0], textblock[1],
					textblock[2], textblock[3], (int) (restTime / 1000));

			// 四字成语正确
			if (judge) {
				if (haveAudio) {
					soundpool.play(soundmap.get(2), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				for (int i = 0; i < 4; i++) {
					block[textblock[i]].setText("");
					textblock[i] = -1;
					textblockview[i].setText("");
					scoreView.setText(spmnt.myGrade + "分");
				}
				// 显示连击
				if (spmnt.clickNum > 1) {
					if (spmnt.clickNum == 3 || spmnt.clickNum == 6) {// 3连击奖励
						spmnt.addProperty2();

						ArrayList<Place> places = spmnt.useProperty2();
						// 如果能够消去
						if (places.size() > 0) {
							int[] p = new int[4];
							p[0] = places.get(0).getX() * 6
									+ places.get(0).getY();
							p[1] = places.get(1).getX() * 6
									+ places.get(1).getY();
							p[2] = places.get(2).getX() * 6
									+ places.get(2).getY();
							p[3] = places.get(3).getX() * 6
									+ places.get(3).getY();
							// 把已经点中的取消
							for (int i = 0; i < 4; i++) {
								if (textblock[i] >= 0) {
									block[textblock[i]]
											.setBackgroundResource(R.drawable.blk);
									block[textblock[i]]
											.setTextColor(Color.WHITE);
									blockCheck[textblock[i]] = false;

									cancleBlock(textblock[i]);
								}
							}

							// 消去
							for (int i = 0; i < 4; i++) {
								block[p[i]]
										.setBackgroundResource(R.drawable.blk2);
								block[p[i]].setTextColor(Color.BLACK);
								blockCheck[p[i]] = true;
								showBlock(p[i]);
								judgeIdiom();
							}

						}

						Toast.makeText(LocalGameActivity.this, "3连击奖励",
								Toast.LENGTH_SHORT).show();
					}
				}
				// 判断是否成功
				if (spmnt.isFinish()) {
					timer.cancel();
					spmnt.finishGame((int) (restTime / 1000));
					scoreView.setText(String.valueOf(spmnt.myGrade));
					clockView.setText("0");

					// 表示成功

					// 弹矿显示结果
					Barrier barrier = new Dao(getBaseContext())
							.getBarrierById(intent.getIntExtra("barrierId", 0));
					int starNum = barrier.getStarNum();
					int HighestScore = barrier.getGrage();
					scoreMessage = (TextView) findViewById(R.id.thisScoreValue);
					highestMessage = (TextView) findViewById(R.id.highestScoreValue);
					starBar = (RatingBar) findViewById(R.id.levelStar);
					scoreMessage.setText(spmnt.myGrade + "分");
					highestMessage.setText(HighestScore + "分");
					showResultLayout.setVisibility(View.VISIBLE);
					starBar.setRating(starNum);
					Animation myAnimation = AnimationUtils.loadAnimation(
							LocalGameActivity.this,
							R.anim.message_scale_animation);
					showResultLayout.startAnimation(myAnimation);
				}
			} else {// 成语错误
				for (int i = 0; i < 4; i++) {
					block[textblock[i]].setBackgroundResource(R.drawable.blk);
					block[textblock[i]].setTextColor(Color.WHITE);
					blockCheck[textblock[i]] = false;
					textblock[i] = -1;
					textblockview[i].setText("");
					scoreView.setText(spmnt.myGrade + "分");
				}
			}
		}

	}

}
