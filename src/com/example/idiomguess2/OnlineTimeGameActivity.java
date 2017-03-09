package com.example.idiomguess2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.woliao.constant.Config;

public class OnlineTimeGameActivity extends Activity {

	private Button[] block;
	private static final String fontPath = "fonts/font.ttf";
	private boolean[] blockCheck;
	private LinearLayout gameBlock, blockPool, textPool, assistPool, assist,
			prompt, delete, show, operate;
	private ImageView smallLogo;
	private TextView blockText1, blockText2, blockText3, blockText4;
	private Typeface tf;
	private String chengyus[];
	String Chengyu[];
	String Chengyus_random[];
	private TextView[] textblockview;
	private TextView GradeShow;
	private int[] textblock;
	public Socket socket;
	public PrintWriter out;
	public BufferedReader in;
	private Chronometer time;
	int requestType;// 璇锋眰绫诲瀷
	private static boolean flag = true;
	private long startTime, lastTime;
	private long totalTime = 0;
	private boolean isRun = true;
	private int clickCount, Grade;
	private TextView LeftCount, daoju1, daoju2;
	// /////////////////////////////////////////////////////////////
	private int timejudge = 1;
	private Button pause, restart, returns;
	private int Count = 0;// 记录最大连击数
	private ImageView image;
	private ProgressBar processbar;
	private TextView grade1, grade2, statu2, statu3, pauseMessage;
	private TranslateAnimation translateAnimation;
	private LinearLayout GradeLayout;
	private RelativeLayout gradeRlLayout, onlineTimeOperateLayout, gamepauseLayout, whetherExitLayout, pauseLayout;
	private Button goBack, yesExit, noBack;
	private Button buttonTip,buttonEliminate;
	private int screenWidth, screenHeight;
	private Animation scale;
	private int mProgressStatus = 0;
	int addgrade = 0;
	boolean jump = true;
	double rate = 0;
	private Message message;
	public JSONObject me;
	int startstatu = 0;
	int a = 0, b = 0;
	boolean buttonclick = true;
	private boolean buttonclick_2=true;
	// ////////////////////////////////////////////////////////////////

	private Handler handler_grade = new Handler() {
		@Override
		public synchronized void handleMessage(Message msg) {
			// super.handleMessage(msg);
			int temp = msg.what;
			switch (temp) {
			case 1:
				gradeRlLayout.setVisibility(View.VISIBLE);

				translateAnimation = new TranslateAnimation(0, 0,
						-screenHeight, 0);
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
								gradeRlLayout.clearAnimation();
							}
						});
				gradeRlLayout.startAnimation(translateAnimation);

				break;
			case 2:
				int grade = msg.arg1;
				grade1.setVisibility(View.VISIBLE);
				grade1.setText("最终成绩  ：  " + grade);
				break;
			case 3:
				int coin = msg.arg1;
				grade2.setVisibility(View.VISIBLE);
				grade2.setText("金 币  ：  " + coin);
				break;
			case 4:
				statu2.setVisibility(View.VISIBLE);
				statu2.setText("等级:" + new InternetData().getRankString(a));
				processbar.setVisibility(View.VISIBLE);
				a = msg.arg1;
				b = msg.arg2;
				addgrade = b - a;
				startstatu = (a - new InternetData().getRankGradebyGrade(a))
						/ (new InternetData().getHigherRankGradebyGrade(a) - new InternetData()
								.getRankGradebyGrade(a)) * 100;
				rate = (b - new InternetData().getRankGradebyGrade(a))
						/ (new InternetData().getHigherRankGradebyGrade(a) - new InternetData()
								.getRankGradebyGrade(a)) * 100;
				if (b < 0)
					rate = 0;
				mProgressStatus = a;

				processbar.setProgress(startstatu);
				processbar.setSecondaryProgress(0);
				// 创建一个线程
				new Thread(new Runnable() {
					@Override
					public void run() {
						while (jump) {
							mProgressStatus = doWork(); // 获取耗时操作完成的百分比

							Message m = new Message();
							if (addgrade >= 0) {
								if (mProgressStatus < b) {
									m.what = 7;
									handler_grade.sendMessage(m); // 发送信息
								} else {
									jump = false;
									mProgressStatus = b;
									m.what = 8;
									handler_grade.sendMessage(m); // 发送消息
									break;
								}
							} else {
								if (mProgressStatus > b) {
									m.what = 7;
									handler_grade.sendMessage(m); // 发送信息
								} else {
									jump = false;
									mProgressStatus = b;
									m.what = 8;
									handler_grade.sendMessage(m); // 发送消息
									break;
								}
							}
						}

					}

					// 模拟一个耗时操作
					private int doWork() {
						if (addgrade >= 0) {
							mProgressStatus++; // 改变完成进度
						} else {
							mProgressStatus--;
						}
						try {
							Thread.sleep(200); // 线程休眠
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return mProgressStatus; // 返回新的进度
					}
				}).start();
				break;
			case 5:
				image.setVisibility(View.VISIBLE);
				image.startAnimation(scale);
				break;
			case 6:
				a = msg.arg1;
				b = msg.arg2;
				statu2.setText("等级:" + new InternetData().getRankString(a));
				rate = (b - new InternetData().getRankGradebyGrade(a))
						/ (new InternetData().getHigherRankGradebyGrade(a) - new InternetData()
								.getRankGradebyGrade(a)) * 100;
				mProgressStatus = a;
				processbar.setProgress(0);
				processbar.setSecondaryProgress(0);
				jump = true;
				// 创建一个线程
				new Thread(new Runnable() {
					@Override
					public void run() {
						while (jump) {
							mProgressStatus = doWork(); // 获取耗时操作完成的百分比

							Message m = new Message();
							if (mProgressStatus < b) {
								m.what = 7;
								handler_grade.sendMessage(m); // 发送信息
							} else {
								jump = false;
								mProgressStatus = b;
								m.what = 8;
								handler_grade.sendMessage(m); // 发送消息
								break;
							}
						}

					}

					// 模拟一个耗时操作
					private int doWork() {
						if (addgrade >= 0) {
							mProgressStatus++; // 改变完成进度
						} else {
							mProgressStatus--;
						}
						try {
							Thread.sleep(200); // 线程休眠
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return mProgressStatus; // 返回新的进度
					}
				}).start();
				break;
			case 7:
				rate = (mProgressStatus - new InternetData()
						.getRankGradebyGrade(a))
						/ (new InternetData().getHigherRankGradebyGrade(a) - new InternetData()
								.getRankGradebyGrade(a)) * 100;
				statu3.setText(rate + "%");
				startstatu = (mProgressStatus - new InternetData()
						.getRankGradebyGrade(mProgressStatus))
						/ (new InternetData()
								.getHigherRankGradebyGrade(mProgressStatus) - new InternetData()
								.getRankGradebyGrade(mProgressStatus)) * 100;
				processbar.setProgress(startstatu);
				if (InternetData.rank == 9) {
					processbar.setVisibility(View.GONE);
					statu3.setText("(最高等级)");
				}
				break;
			case 8:
				startstatu = (mProgressStatus - new InternetData()
						.getRankGradebyGrade(mProgressStatus))
						/ (new InternetData()
								.getHigherRankGradebyGrade(mProgressStatus) - new InternetData()
								.getRankGradebyGrade(mProgressStatus)) * 100;
				processbar.setProgress(startstatu);
				// statu1.setVisibility(View.VISIBLE);
				rate = (b - new InternetData().getRankGradebyGrade(a))
						/ (new InternetData().getHigherRankGradebyGrade(a) - new InternetData()
								.getRankGradebyGrade(a)) * 100;
				statu3.setVisibility(View.VISIBLE);
				statu3.setText(rate + " %");
				if (InternetData.rank == 9) {
					processbar.setVisibility(View.GONE);
					statu3.setText("(最高等级)");
				}
				// if(addgrade>=0){
				// statu1.setText("  +"+addgrade);
				// }else{
				// statu1.setText("  "+addgrade);
				// }
				break;
			case 9:
				a = msg.arg1;
				b = msg.arg2;
				statu2.setText("等级:" + new InternetData().getRankString(a));
				rate = (b - new InternetData().getRankGradebyGrade(a))
						/ (new InternetData().getHigherRankGradebyGrade(a) - new InternetData()
								.getRankGradebyGrade(a)) * 100;
				if (b < 0)
					rate = 0;
				mProgressStatus = a;
				processbar.setProgress(0);
				processbar.setSecondaryProgress(0);
				jump = true;
				// 创建一个线程
				new Thread(new Runnable() {
					@Override
					public void run() {
						while (jump) {
							mProgressStatus = doWork(); // 获取耗时操作完成的百分比

							Message m = new Message();
							if (mProgressStatus > b) {
								m.what = 7;
								handler_grade.sendMessage(m); // 发送信息
							} else {
								jump = false;
								mProgressStatus = b;
								m.what = 8;
								handler_grade.sendMessage(m); // 发送消息
								break;
							}
						}

					}

					// 模拟一个耗时操作
					private int doWork() {
						mProgressStatus--;

						try {
							Thread.sleep(200); // 线程休眠
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return mProgressStatus; // 返回新的进度
					}
				}).start();
				break;
			default:
				break;
			}

			return;
		}
	};

	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlinetimegame);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）

		textblockview = new TextView[4];
		textblockview[0] = (TextView) findViewById(R.id.onlinetime_blockText1);
		textblockview[1] = (TextView) findViewById(R.id.onlinetime_blockText2);
		textblockview[2] = (TextView) findViewById(R.id.onlinetime_blockText3);
		textblockview[3] = (TextView) findViewById(R.id.onlinetime_blockText4);
		textblockview[0].setTextColor(Color.BLUE);
		textblockview[1].setTextColor(Color.BLUE);
		textblockview[2].setTextColor(Color.BLUE);
		textblockview[3].setTextColor(Color.BLUE);
		textblockview[0].setTypeface(tf);
		textblockview[1].setTypeface(tf);
		textblockview[2].setTypeface(tf);
		textblockview[3].setTypeface(tf);
		
		

		daoju1 = (TextView) findViewById(R.id.onlinetime_promptText);
		daoju1.setText(InternetData.tip+" ");
		daoju2 = (TextView) findViewById(R.id.onlinetime_deleteText);
		daoju2.setText(InternetData.remove+" ");
		LeftCount = (TextView) findViewById(R.id.onlinetime_speakText);

		GradeShow = (TextView) findViewById(R.id.onlinetime_score);
		GradeShow.setText("0");
		GradeShow.setTextColor(Color.BLUE);
		time = (Chronometer) findViewById(R.id.onlinetime_time);

		Grade = 0;
		clickCount = 1;
		blockCheck = new boolean[36];
		textblock = new int[4];
		Chengyu = new String[9];
		block = new Button[36];

		// 图片缩放
		image = (ImageView) findViewById(R.id.onlinetime_imageView1);
		image.setVisibility(View.INVISIBLE);
		scale = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
		RelativeLayout.LayoutParams imageLy = new RelativeLayout.LayoutParams(
				screenHeight / 10, screenHeight / 10);
		imageLy.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		imageLy.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		imageLy.topMargin = screenHeight / 10;
		imageLy.rightMargin = screenHeight / 10;
		image.setLayoutParams(imageLy);

		processbar = (ProgressBar) findViewById(R.id.onlinetime_progressbar_updown);
		processbar.setVisibility(View.INVISIBLE);
		processbar.setLayoutParams(new RelativeLayout.LayoutParams(
				screenWidth / 4, ViewGroup.LayoutParams.WRAP_CONTENT));
		grade1 = (TextView) findViewById(R.id.onlinetime_textView1);
		grade1.setVisibility(View.INVISIBLE);
		grade1.setTypeface(tf);
		grade1.setTextColor(Color.BLUE);

		grade2 = (TextView) findViewById(R.id.onlinetime_textView2);
		grade2.setVisibility(View.INVISIBLE);
		grade2.setTypeface(tf);
		grade2.setTextColor(Color.BLUE);
		//
		// statu1 = (TextView)findViewById(R.id.textView4);
		// statu1.setVisibility(View.INVISIBLE);
		// statu1.setTypeface(tf);
		// statu1.setTextColor(Color.BLUE);

		statu2 = (TextView) findViewById(R.id.onlinetime_textView5);
		statu2.setVisibility(View.INVISIBLE);
		statu2.setTypeface(tf);
		statu2.setTextColor(Color.BLUE);

		statu3 = (TextView) findViewById(R.id.onlinetime_textView6);
		statu3.setVisibility(View.INVISIBLE);
		statu3.setTextColor(Color.BLACK);

		goBack = (Button) findViewById(R.id.onlinetime_button1);
		RelativeLayout.LayoutParams goBackLp = new RelativeLayout.LayoutParams(
				screenWidth / 6, screenHeight / 10);
		goBackLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		goBackLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		goBack.setLayoutParams(goBackLp);

		goBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				translateAnimation = new TranslateAnimation(0, 0, 0,
						-screenHeight);
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
								gradeRlLayout.clearAnimation();
								gradeRlLayout.setVisibility(View.GONE);
							}
						});
				gradeRlLayout.startAnimation(translateAnimation);
				
				Intent intent = new Intent(OnlineTimeGameActivity.this,OnlinePlatformActivity.class);
				startActivity(intent);
			}
		});

		gradeRlLayout = (RelativeLayout) findViewById(R.id.onlinetime_gradeRlLayout);
		gradeRlLayout.setVisibility(View.GONE);
		RelativeLayout.LayoutParams graderllp = new RelativeLayout.LayoutParams(
				screenWidth / 2, screenHeight / 2);
		graderllp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		graderllp.addRule(RelativeLayout.CENTER_VERTICAL);
		gradeRlLayout.setLayoutParams(graderllp);

		GradeLayout = (LinearLayout) findViewById(R.id.onlinetime_gradelayout);
		RelativeLayout.LayoutParams gradelp = new RelativeLayout.LayoutParams(
				screenWidth / 3, screenHeight / 3);
		gradelp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		gradelp.addRule(RelativeLayout.CENTER_VERTICAL);
		GradeLayout.setLayoutParams(gradelp);
		// //////////////////////////////////////////////////////////////
		for (int i = 0; i < 4; i++) {
			textblock[i] = -1;
		}
		blockCheck = new boolean[36];

		smallLogo = (ImageView) findViewById(R.id.onlinetime_smallLogo);
		LinearLayout.LayoutParams logo = new LinearLayout.LayoutParams(
				screenWidth / 10, screenHeight / 10);
		smallLogo.setLayoutParams(logo);

		show = (LinearLayout) findViewById(R.id.onlinetime_show);
		show.setPadding(screenWidth / 20, screenWidth / 40, 0, 0);
		LinearLayout.LayoutParams showLy = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, screenHeight * 2 / 7);
		show.setLayoutParams(showLy);
		
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
		
		whetherExitLayout = (RelativeLayout) findViewById(R.id.whetherExitLayout);
		whetherExitLayout.setVisibility(View.GONE);
		
		onlineTimeOperateLayout = (RelativeLayout)findViewById(R.id.onlinetimeoperate_layout);
		LinearLayout.LayoutParams onlineTimeOperateRl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		onlineTimeOperateRl.bottomMargin = screenHeight/15;
		onlineTimeOperateLayout.setLayoutParams(onlineTimeOperateRl);

		operate = (LinearLayout) findViewById(R.id.onlinetime_operate);
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
		LeftCount.setLayoutParams(speakTextLy);

		gameBlock = (LinearLayout) findViewById(R.id.onlinetime_gameBlock);
		LinearLayout.LayoutParams gmbk = new LinearLayout.LayoutParams(
				screenHeight * 4 / 5, ViewGroup.LayoutParams.MATCH_PARENT);
		gmbk.topMargin = screenHeight / 20;
		gmbk.rightMargin = screenHeight / 15;
		gameBlock.setLayoutParams(gmbk);

		assistPool = (LinearLayout) findViewById(R.id.onlinetime_assistPool);
		LinearLayout.LayoutParams astplPl = new LinearLayout.LayoutParams(
				screenHeight / 4, ViewGroup.LayoutParams.MATCH_PARENT);
		assistPool.setPadding(screenHeight / 30, 0, screenHeight / 30, 0);
		assistPool.setLayoutParams(astplPl);

		assist = (LinearLayout) findViewById(R.id.onlinetime_assist);
		LinearLayout.LayoutParams astPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		assist.setLayoutParams(astPl);

		prompt = (LinearLayout) findViewById(R.id.onlinetime_prompt);
		LinearLayout.LayoutParams promptPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 4);
		promptPl.bottomMargin = screenHeight / 25;
		prompt.setLayoutParams(promptPl);

		delete = (LinearLayout) findViewById(R.id.onlinetime_delete);
		LinearLayout.LayoutParams deletePl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 4);
		deletePl.topMargin = screenHeight / 25;
		delete.setLayoutParams(deletePl);
		// assist.setPadding(screenHeight / 30, 0, screenHeight / 30, 0);

		blockPool = (LinearLayout) findViewById(R.id.onlinetime_blockPool);
		LinearLayout.LayoutParams blkPl = new LinearLayout.LayoutParams(
				screenHeight * 4 / 5, screenHeight * 4 / 5);
		blockPool.setLayoutParams(blkPl);

		textPool = (LinearLayout) findViewById(R.id.onlinetime_textPool);
		LinearLayout.LayoutParams textPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		textPl.bottomMargin = screenHeight / 30;
		textPool.setLayoutParams(textPl);

		blockText1 = (TextView) findViewById(R.id.onlinetime_blockText1);
		blockText1.setTextColor(getResources().getColor(R.drawable.porcelain));
		blockText1.setTextAppearance(this, R.style.blockSize);
		blockText1.setTypeface(tf);
		blockText1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			}
		});

		blockText2 = (TextView) findViewById(R.id.onlinetime_blockText2);
		blockText2.setTextColor(getResources().getColor(R.drawable.porcelain));
		blockText2.setTextAppearance(this, R.style.blockSize);
		blockText2.setTypeface(tf);
		blockText2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			}
		});

		blockText3 = (TextView) findViewById(R.id.onlinetime_blockText3);
		blockText3.setTextColor(getResources().getColor(R.drawable.porcelain));
		blockText3.setTextAppearance(this, R.style.blockSize);
		blockText3.setTypeface(tf);
		blockText3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			}
		});

		blockText4 = (TextView) findViewById(R.id.onlinetime_blockText4);
		blockText4.setTextColor(getResources().getColor(R.drawable.porcelain));
		blockText4.setTextAppearance(this, R.style.blockSize);
		blockText4.setTypeface(tf);
		blockText4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			}
		});

		block[0] = (Button) findViewById(R.id.onlinetime_block1);
		block[0].setTextAppearance(this, R.style.blockTextSize);
		block[0].setTextColor(Color.WHITE);
		block[0].setTypeface(tf);

		block[1] = (Button) findViewById(R.id.onlinetime_block2);
		block[1].setTextAppearance(this, R.style.blockTextSize);
		block[1].setTextColor(Color.WHITE);
		block[1].setTypeface(tf);

		block[2] = (Button) findViewById(R.id.onlinetime_block3);
		block[2].setTextAppearance(this, R.style.blockTextSize);
		block[2].setTextColor(Color.WHITE);
		block[2].setTypeface(tf);

		block[3] = (Button) findViewById(R.id.onlinetime_block4);
		block[3].setTextAppearance(this, R.style.blockTextSize);
		block[3].setTextColor(Color.WHITE);
		block[3].setTypeface(tf);

		block[4] = (Button) findViewById(R.id.onlinetime_block5);
		block[4].setTextAppearance(this, R.style.blockTextSize);
		block[4].setTextColor(Color.WHITE);
		block[4].setTypeface(tf);

		block[5] = (Button) findViewById(R.id.onlinetime_block6);
		block[5].setTextAppearance(this, R.style.blockTextSize);
		block[5].setTextColor(Color.WHITE);
		block[5].setTypeface(tf);

		block[6] = (Button) findViewById(R.id.onlinetime_block7);
		block[6].setTextAppearance(this, R.style.blockTextSize);
		block[6].setTextColor(Color.WHITE);
		block[6].setTypeface(tf);

		block[7] = (Button) findViewById(R.id.onlinetime_block8);
		block[7].setTextAppearance(this, R.style.blockTextSize);
		block[7].setTextColor(Color.WHITE);
		block[7].setTypeface(tf);

		block[8] = (Button) findViewById(R.id.onlinetime_block9);
		block[8].setTextAppearance(this, R.style.blockTextSize);
		block[8].setTextColor(Color.WHITE);
		block[8].setTypeface(tf);

		block[9] = (Button) findViewById(R.id.onlinetime_block10);
		block[9].setTextAppearance(this, R.style.blockTextSize);
		block[9].setTextColor(Color.WHITE);
		block[9].setTypeface(tf);

		block[10] = (Button) findViewById(R.id.onlinetime_block11);
		block[10].setTextAppearance(this, R.style.blockTextSize);
		block[10].setTextColor(Color.WHITE);
		block[10].setTypeface(tf);

		block[11] = (Button) findViewById(R.id.onlinetime_block12);
		block[11].setTextAppearance(this, R.style.blockTextSize);
		block[11].setTextColor(Color.WHITE);
		block[11].setTypeface(tf);

		block[12] = (Button) findViewById(R.id.onlinetime_block13);
		block[12].setTextAppearance(this, R.style.blockTextSize);
		block[12].setTextColor(Color.WHITE);
		block[12].setTypeface(tf);

		block[13] = (Button) findViewById(R.id.onlinetime_block14);
		block[13].setTextAppearance(this, R.style.blockTextSize);
		block[13].setTextColor(Color.WHITE);
		block[13].setTypeface(tf);

		block[14] = (Button) findViewById(R.id.onlinetime_block15);
		block[14].setTextAppearance(this, R.style.blockTextSize);
		block[14].setTextColor(Color.WHITE);
		block[14].setTypeface(tf);

		block[15] = (Button) findViewById(R.id.onlinetime_block16);
		block[15].setTextAppearance(this, R.style.blockTextSize);
		block[15].setTextColor(Color.WHITE);
		block[15].setTypeface(tf);

		block[16] = (Button) findViewById(R.id.onlinetime_block17);
		block[16].setTextAppearance(this, R.style.blockTextSize);
		block[16].setTextColor(Color.WHITE);
		block[16].setTypeface(tf);

		block[17] = (Button) findViewById(R.id.onlinetime_block18);
		block[17].setTextAppearance(this, R.style.blockTextSize);
		block[17].setTextColor(Color.WHITE);
		block[17].setTypeface(tf);

		block[18] = (Button) findViewById(R.id.onlinetime_block19);
		block[18].setTextAppearance(this, R.style.blockTextSize);
		block[18].setTextColor(Color.WHITE);
		block[18].setTypeface(tf);

		block[19] = (Button) findViewById(R.id.onlinetime_block20);
		block[19].setTextAppearance(this, R.style.blockTextSize);
		block[19].setTextColor(Color.WHITE);
		block[19].setTypeface(tf);

		block[20] = (Button) findViewById(R.id.onlinetime_block21);
		block[20].setTextAppearance(this, R.style.blockTextSize);
		block[20].setTextColor(Color.WHITE);
		block[20].setTypeface(tf);

		block[21] = (Button) findViewById(R.id.onlinetime_block22);
		block[21].setTextAppearance(this, R.style.blockTextSize);
		block[21].setTextColor(Color.WHITE);
		block[21].setTypeface(tf);

		block[22] = (Button) findViewById(R.id.onlinetime_block23);
		block[22].setTextAppearance(this, R.style.blockTextSize);
		block[22].setTextColor(Color.WHITE);
		block[22].setTypeface(tf);

		block[23] = (Button) findViewById(R.id.onlinetime_block24);
		block[23].setTextAppearance(this, R.style.blockTextSize);
		block[23].setTextColor(Color.WHITE);
		block[23].setTypeface(tf);

		block[24] = (Button) findViewById(R.id.onlinetime_block25);
		block[24].setTextAppearance(this, R.style.blockTextSize);
		block[24].setTextColor(Color.WHITE);
		block[24].setTypeface(tf);

		block[25] = (Button) findViewById(R.id.onlinetime_block26);
		block[25].setTextAppearance(this, R.style.blockTextSize);
		block[25].setTextColor(Color.WHITE);
		block[25].setTypeface(tf);

		block[26] = (Button) findViewById(R.id.onlinetime_block27);
		block[26].setTextAppearance(this, R.style.blockTextSize);
		block[26].setTextColor(Color.WHITE);
		block[26].setTypeface(tf);

		block[27] = (Button) findViewById(R.id.onlinetime_block28);
		block[27].setTextAppearance(this, R.style.blockTextSize);
		block[27].setTextColor(Color.WHITE);
		block[27].setTypeface(tf);

		block[28] = (Button) findViewById(R.id.onlinetime_block29);
		block[28].setTextAppearance(this, R.style.blockTextSize);
		block[28].setTextColor(Color.WHITE);
		block[28].setTypeface(tf);

		block[29] = (Button) findViewById(R.id.onlinetime_block30);
		block[29].setTextAppearance(this, R.style.blockTextSize);
		block[29].setTextColor(Color.WHITE);
		block[29].setTypeface(tf);

		block[30] = (Button) findViewById(R.id.onlinetime_block31);
		block[30].setTextAppearance(this, R.style.blockTextSize);
		block[30].setTextColor(Color.WHITE);
		block[30].setTypeface(tf);

		block[31] = (Button) findViewById(R.id.onlinetime_block32);
		block[31].setTextAppearance(this, R.style.blockTextSize);
		block[31].setTextColor(Color.WHITE);
		block[31].setTypeface(tf);

		block[32] = (Button) findViewById(R.id.onlinetime_block33);
		block[32].setTextAppearance(this, R.style.blockTextSize);
		block[32].setTextColor(Color.WHITE);
		block[32].setTypeface(tf);

		block[33] = (Button) findViewById(R.id.onlinetime_block34);
		block[33].setTextAppearance(this, R.style.blockTextSize);
		block[33].setTextColor(Color.WHITE);
		block[33].setTypeface(tf);

		block[34] = (Button) findViewById(R.id.onlinetime_block35);
		block[34].setTextAppearance(this, R.style.blockTextSize);
		block[34].setTextColor(Color.WHITE);
		block[34].setTypeface(tf);

		block[35] = (Button) findViewById(R.id.onlinetime_block36);
		block[35].setTextAppearance(this, R.style.blockTextSize);
		block[35].setTextColor(Color.WHITE);
		block[35].setTypeface(tf);
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("requestType", Config.REQUEST_GET_CHENGYU);
			jObject.put("num", 9);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			socket = new Socket(Config.IP, 9999);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "UTF-8"));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream(), "UTF-8")), true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println(jObject.toString());

		boolean flag = true;
		while (flag) {
			try {
				jObject = new JSONObject(in.readLine());
				flag = false;
				JSONArray chengyu = (JSONArray) jObject.get("chengyus");
				chengyus = new String[36];
				for (int i = 0; i < 9; i++) {
					String str = (String) chengyu.get(i);
					Chengyu[i] = str;
					for (int j = 0; j < 4; j++) {
						chengyus[i * 4 + j] = str.substring(j, j + 1);
					}
				}

				Chengyus_random = new String[36];
				for (int i = 0; i < 36; i++) {
					Chengyus_random[i] = "";
				}
				for (int i = 0; i < 9; i++) {
					String str = (String) chengyu.get(i);
					for (int j = 0; j < 4; j++) {
						Random ran = new Random(24);
						int temp = 0;
						while (true) {
							temp = ran.nextInt(36);
							if (Chengyus_random[temp].equals("")) {
								Chengyus_random[temp] = str.substring(j, j + 1);
								break;
							}
						}
					}
				}
				int number = 0;
				for (int i = 0; i < 36; i++) {
					block[i].setText(Chengyus_random[number++]);
				}
			} catch (IOException e) {
			} catch (JSONException e) {
			}
		}
		try {
			if (socket != null) {
				socket.close();
			}
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			socket = null;
			in = null;
			out = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		buttonTip = (Button)this.findViewById(R.id.onlinetime_prop1);
		buttonTip.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				for(int i=0;i<9;i++){
					if(!Chengyu[i].equals("")){
						//找到数据库中成语的解释
						try {
							//道具数减一
							socket = new Socket(Config.IP, 9999);
							in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
							out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
							me = new JSONObject();
							me.put(Config.REQUEST_TYPE,Config.REQUEST_SET_TIPNUM);
							me.put("setTipNum", InternetData.tip-1);
							me.put("username", InternetData.username);
							out.println(me.toString());
							
							me = new JSONObject(in.readLine());
							if(me.getInt("isTipSuccess") >= 0){
								if(InternetData.tip > 0){
									//me.put(Config.REQUEST_TYPE, Config.REQUEST_GET_CHENGYUEXPLAIN);
									//me.put("explainName",Chengyu[i]);
									//out.println(me.toString());
									//me = new JSONObject(in.readLine());
									//String explain = me.getString("explain");
									//jin1.setText(""+explain);
									
									//显示
									for(int j=0;j<2;j++){
										for(int x=0;x<36;x++){
											if(Chengyu[i].substring(j, j+1).equals(Chengyus_random[x])){
												block[x].setTextColor(getResources().getColor(
														R.drawable.porcelain));
												block[x].setBackgroundResource(R.drawable.blk2);
												break;
											}
										}
									}
									InternetData.tip --;
									buttonTip.setText(""+InternetData.tip);
								}
							}else{
								me.put("setTipNum", InternetData.tip+1);
								Toast.makeText(OnlineTimeGameActivity.this,"您没有道具",Toast.LENGTH_SHORT).show();
							}	
						} catch (JSONException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						break;
					}
				}
				judgeGame();
				InternetData.countChengyu = InternetData.countChengyu + 4;
			}
		});
		buttonEliminate = (Button)this.findViewById(R.id.onlinetime_prop2);
		buttonEliminate.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				for(int i=0;i<9;i++){
					if(!Chengyu[i].equals("")){
						//道具数减一
						try {
								socket = new Socket(Config.IP, 9999);
							in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
							out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
							me = new JSONObject();
							me.put(Config.REQUEST_TYPE,Config.REQUEST_SET_REMOVENUM);
							me.put("setRemoveNum", InternetData.remove-1);
							me.put("username", InternetData.username);
							out.println(me.toString());
							
							me = new JSONObject(in.readLine());
							System.out.println(me.toString());
							if(me.getInt("isRemoveSuccess") >= 0){
								if(InternetData.remove > 0){
									//针对每个字寻找匹配
									for(int j=0;j<4;j++){
										for(int x=0;x<36;x++){
											if(Chengyu[i].substring(j, j+1).equals(Chengyus_random[x])){
												block[x].setText("");
												block[x].setBackgroundResource(R.drawable.blk2);
												break;
											}
										}
									}
									InternetData.remove --;
									buttonEliminate.setText(""+InternetData.remove);
								}
							}else{
								me.put("setRemoveNum", InternetData.remove+1);
								Toast.makeText(OnlineTimeGameActivity.this,"您没有道具",Toast.LENGTH_SHORT).show();
							}
						} catch (JSONException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}	
						break;
					}			
				}
				//EliminateText.setText(""+InternetData.remove);
				judgeGame();
				InternetData.countChengyu = InternetData.countChengyu + 4;
			}
			
		});

		block[0].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[0].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[0]) {
					block[0].setBackgroundResource(R.drawable.blk);
					block[0].setTextColor(Color.WHITE);
					blockCheck[0] = false;
					cancleBlock(0);
				} else if (buttonclick) {
					block[0].setBackgroundResource(R.drawable.blk2);
					block[0].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[0] = true;
					showBlock(0);
					judgeChengyu();
				}
			}
		});

		block[1].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[1].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[1]) {
					block[1].setBackgroundResource(R.drawable.blk);
					block[1].setTextColor(Color.WHITE);
					blockCheck[1] = false;
					cancleBlock(1);
				} else if (buttonclick) {
					block[1].setBackgroundResource(R.drawable.blk2);
					block[1].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[1] = true;
					showBlock(1);
					judgeChengyu();
				}
			}
		});

		block[2].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[2].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[2]) {
					block[2].setBackgroundResource(R.drawable.blk);
					block[2].setTextColor(Color.WHITE);
					blockCheck[2] = false;
					cancleBlock(2);
				} else if (buttonclick) {
					block[2].setBackgroundResource(R.drawable.blk2);
					block[2].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[2] = true;
					showBlock(2);
					judgeChengyu();
				}
			}
		});

		block[3].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[3].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[3]) {
					block[3].setBackgroundResource(R.drawable.blk);
					block[3].setTextColor(Color.WHITE);
					blockCheck[3] = false;
					cancleBlock(3);
				} else if (buttonclick) {
					block[3].setBackgroundResource(R.drawable.blk2);
					block[3].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[3] = true;
					showBlock(3);
					judgeChengyu();
				}
			}
		});

		block[4].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[4].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[4]) {
					block[4].setBackgroundResource(R.drawable.blk);
					block[4].setTextColor(Color.WHITE);
					blockCheck[4] = false;
					cancleBlock(4);
				} else if (buttonclick) {
					block[4].setBackgroundResource(R.drawable.blk2);
					block[4].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[4] = true;
					showBlock(4);
					judgeChengyu();
				}
			}
		});

		block[5].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[5].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[5]) {
					block[5].setBackgroundResource(R.drawable.blk);
					block[5].setTextColor(Color.WHITE);
					blockCheck[5] = false;
					cancleBlock(5);
				} else if (buttonclick) {
					block[5].setBackgroundResource(R.drawable.blk2);
					block[5].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[5] = true;
					showBlock(5);
					judgeChengyu();
				}
			}
		});

		block[6].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[6].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[6]) {
					block[6].setBackgroundResource(R.drawable.blk);
					block[6].setTextColor(Color.WHITE);
					blockCheck[6] = false;
					cancleBlock(6);
				} else if (buttonclick) {
					block[6].setBackgroundResource(R.drawable.blk2);
					block[6].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[6] = true;
					showBlock(6);
					judgeChengyu();
				}
			}
		});

		block[7].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[7].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[7]) {
					block[7].setBackgroundResource(R.drawable.blk);
					block[7].setTextColor(Color.WHITE);
					blockCheck[7] = false;
					cancleBlock(7);
				} else if (buttonclick) {
					block[7].setBackgroundResource(R.drawable.blk2);
					block[7].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[7] = true;
					showBlock(7);
					judgeChengyu();
				}
			}
		});

		block[8].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[8].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[8]) {
					block[8].setBackgroundResource(R.drawable.blk);
					block[8].setTextColor(Color.WHITE);
					blockCheck[8] = false;
					cancleBlock(8);
				} else if (buttonclick) {
					block[8].setBackgroundResource(R.drawable.blk2);
					block[8].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[8] = true;
					showBlock(8);
					judgeChengyu();
				}
			}
		});

		block[9].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[9].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[9]) {
					block[9].setBackgroundResource(R.drawable.blk);
					block[9].setTextColor(Color.WHITE);
					blockCheck[9] = false;
					cancleBlock(9);
				} else if (buttonclick) {
					block[9].setBackgroundResource(R.drawable.blk2);
					block[9].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[9] = true;
					showBlock(9);
					judgeChengyu();
				}
			}
		});

		block[10].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[10].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[10]) {
					block[10].setBackgroundResource(R.drawable.blk);
					block[10].setTextColor(Color.WHITE);
					blockCheck[10] = false;
					cancleBlock(10);
				} else if (buttonclick) {
					block[10].setBackgroundResource(R.drawable.blk2);
					block[10].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[10] = true;
					showBlock(10);
					judgeChengyu();
				}
			}
		});

		block[11].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[11].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[11]) {
					block[11].setBackgroundResource(R.drawable.blk);
					block[11].setTextColor(Color.WHITE);
					blockCheck[11] = false;
					cancleBlock(11);
				} else if (buttonclick) {
					block[11].setBackgroundResource(R.drawable.blk2);
					block[11].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[11] = true;
					showBlock(11);
					judgeChengyu();
				}
			}
		});

		block[12].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[12].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[12]) {
					block[12].setBackgroundResource(R.drawable.blk);
					block[12].setTextColor(Color.WHITE);
					blockCheck[12] = false;
					cancleBlock(12);
				} else if (buttonclick) {
					block[12].setBackgroundResource(R.drawable.blk2);
					block[12].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[12] = true;
					showBlock(12);
					judgeChengyu();
				}
			}
		});

		block[13].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[13].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[13]) {
					block[13].setBackgroundResource(R.drawable.blk);
					block[13].setTextColor(Color.WHITE);
					blockCheck[13] = false;
					cancleBlock(13);
				} else if (buttonclick) {
					block[13].setBackgroundResource(R.drawable.blk2);
					block[13].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[13] = true;
					showBlock(13);
					judgeChengyu();
				}
			}
		});

		block[14].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[14].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[14]) {
					block[14].setBackgroundResource(R.drawable.blk);
					block[14].setTextColor(Color.WHITE);
					blockCheck[14] = false;
					cancleBlock(14);
				} else if (buttonclick) {
					block[14].setBackgroundResource(R.drawable.blk2);
					block[14].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[14] = true;
					showBlock(14);
					judgeChengyu();
				}
			}
		});

		block[15].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[15].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[15]) {
					block[15].setBackgroundResource(R.drawable.blk);
					block[15].setTextColor(Color.WHITE);
					blockCheck[15] = false;
					cancleBlock(15);
				} else if (buttonclick) {
					block[15].setBackgroundResource(R.drawable.blk2);
					block[15].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[15] = true;
					showBlock(15);
					judgeChengyu();
				}
			}
		});

		block[16].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[16].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[16]) {
					block[16].setBackgroundResource(R.drawable.blk);
					block[16].setTextColor(Color.WHITE);
					blockCheck[16] = false;
					cancleBlock(16);
				} else if (buttonclick) {
					block[16].setBackgroundResource(R.drawable.blk2);
					block[16].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[16] = true;
					showBlock(16);
					judgeChengyu();
				}
			}
		});

		block[17].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[17].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[17]) {
					block[17].setBackgroundResource(R.drawable.blk);
					block[17].setTextColor(Color.WHITE);
					blockCheck[17] = false;
					cancleBlock(17);
				} else if (buttonclick) {
					block[17].setBackgroundResource(R.drawable.blk2);
					block[17].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[17] = true;
					showBlock(17);
					judgeChengyu();
				}
			}
		});

		block[18].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[18].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[18]) {
					block[18].setBackgroundResource(R.drawable.blk);
					block[18].setTextColor(Color.WHITE);
					blockCheck[18] = false;
					cancleBlock(18);
				} else if (buttonclick) {
					block[18].setBackgroundResource(R.drawable.blk2);
					block[18].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[18] = true;
					showBlock(18);
					judgeChengyu();
				}
			}
		});

		block[19].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[19].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[19]) {
					block[19].setBackgroundResource(R.drawable.blk);
					block[19].setTextColor(Color.WHITE);
					blockCheck[19] = false;
					cancleBlock(19);
				} else if (buttonclick) {
					block[19].setBackgroundResource(R.drawable.blk2);
					block[19].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[19] = true;
					showBlock(19);
					judgeChengyu();
				}
			}
		});

		block[20].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[20].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[20]) {
					block[20].setBackgroundResource(R.drawable.blk);
					block[20].setTextColor(Color.WHITE);
					blockCheck[20] = false;
					cancleBlock(20);
				} else if (buttonclick) {
					block[20].setBackgroundResource(R.drawable.blk2);
					block[20].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[20] = true;
					showBlock(20);
					judgeChengyu();
				}
			}
		});

		block[21].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[21].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[21]) {
					block[21].setBackgroundResource(R.drawable.blk);
					block[21].setTextColor(Color.WHITE);
					blockCheck[21] = false;
					cancleBlock(21);
				} else if (buttonclick) {
					block[21].setBackgroundResource(R.drawable.blk2);
					block[21].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[21] = true;
					showBlock(21);
					judgeChengyu();
				}
			}
		});

		block[22].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[22].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[22]) {
					block[22].setBackgroundResource(R.drawable.blk);
					block[22].setTextColor(Color.WHITE);
					blockCheck[22] = false;
					cancleBlock(22);
				} else if (buttonclick) {
					block[22].setBackgroundResource(R.drawable.blk2);
					block[22].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[22] = true;
					showBlock(22);
					judgeChengyu();
				}
			}
		});

		block[23].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[23].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[23]) {
					block[23].setBackgroundResource(R.drawable.blk);
					block[23].setTextColor(Color.WHITE);
					blockCheck[23] = false;
					cancleBlock(23);
				} else if (buttonclick) {
					block[23].setBackgroundResource(R.drawable.blk2);
					block[23].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[23] = true;
					showBlock(23);
					judgeChengyu();
				}
			}
		});

		block[24].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[24].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[24]) {
					block[24].setBackgroundResource(R.drawable.blk);
					block[24].setTextColor(Color.WHITE);
					blockCheck[24] = false;
					cancleBlock(24);
				} else if (buttonclick) {
					block[24].setBackgroundResource(R.drawable.blk2);
					block[24].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[24] = true;
					showBlock(24);
					judgeChengyu();
				}
			}
		});

		block[25].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[25].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[25]) {
					block[25].setBackgroundResource(R.drawable.blk);
					block[25].setTextColor(Color.WHITE);
					blockCheck[25] = false;
					cancleBlock(25);
				} else if (buttonclick) {
					block[25].setBackgroundResource(R.drawable.blk2);
					block[25].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[25] = true;
					showBlock(25);
					judgeChengyu();
				}
			}
		});

		block[26].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[26].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[26]) {
					block[26].setBackgroundResource(R.drawable.blk);
					block[26].setTextColor(Color.WHITE);
					blockCheck[26] = false;
					cancleBlock(26);
				} else if (buttonclick) {
					block[26].setBackgroundResource(R.drawable.blk2);
					block[26].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[26] = true;
					showBlock(26);
					judgeChengyu();
				}
			}
		});

		block[27].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[27].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[27]) {
					block[27].setBackgroundResource(R.drawable.blk);
					block[27].setTextColor(Color.WHITE);
					blockCheck[27] = false;
					cancleBlock(27);
				} else if (buttonclick) {
					block[27].setBackgroundResource(R.drawable.blk2);
					block[27].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[27] = true;
					showBlock(27);
					judgeChengyu();
				}
			}
		});

		block[28].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[28].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[28]) {
					block[28].setBackgroundResource(R.drawable.blk);
					block[28].setTextColor(Color.WHITE);
					blockCheck[28] = false;
					cancleBlock(28);
				} else if (buttonclick) {
					block[28].setBackgroundResource(R.drawable.blk2);
					block[28].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[28] = true;
					showBlock(28);
					judgeChengyu();
				}
			}
		});

		block[29].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[29].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[29]) {
					block[29].setBackgroundResource(R.drawable.blk);
					block[29].setTextColor(Color.WHITE);
					blockCheck[29] = false;
					cancleBlock(29);
				} else if (buttonclick) {
					block[29].setBackgroundResource(R.drawable.blk2);
					block[29].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[29] = true;
					showBlock(29);
					judgeChengyu();
				}
			}
		});

		block[30].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[30].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[30]) {
					block[30].setBackgroundResource(R.drawable.blk);
					block[30].setTextColor(Color.WHITE);
					blockCheck[30] = false;
					cancleBlock(30);
				} else if (buttonclick) {
					block[30].setBackgroundResource(R.drawable.blk2);
					block[30].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[30] = true;
					showBlock(30);
					judgeChengyu();
				}
			}
		});

		block[31].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[31].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[31]) {
					block[31].setBackgroundResource(R.drawable.blk);
					block[31].setTextColor(Color.WHITE);
					blockCheck[31] = false;
					cancleBlock(31);
				} else if (buttonclick) {
					block[31].setBackgroundResource(R.drawable.blk2);
					block[31].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[31] = true;
					showBlock(31);
					judgeChengyu();
				}
			}
		});

		block[32].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[32].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[32]) {
					block[32].setBackgroundResource(R.drawable.blk);
					block[32].setTextColor(Color.WHITE);
					blockCheck[32] = false;
					cancleBlock(32);
				} else if (buttonclick) {
					block[32].setBackgroundResource(R.drawable.blk2);
					block[32].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[32] = true;
					showBlock(32);
					judgeChengyu();
				}
			}
		});

		block[33].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[33].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[33]) {
					block[33].setBackgroundResource(R.drawable.blk);
					block[33].setTextColor(Color.WHITE);
					blockCheck[33] = false;
					cancleBlock(33);
				} else if (buttonclick) {
					block[33].setBackgroundResource(R.drawable.blk2);
					block[33].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[33] = true;
					showBlock(33);
					judgeChengyu();
				}
			}
		});

		block[34].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[34].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[34]) {
					block[34].setBackgroundResource(R.drawable.blk);
					block[34].setTextColor(Color.WHITE);
					blockCheck[34] = false;
					cancleBlock(34);
				} else if (buttonclick) {
					block[34].setBackgroundResource(R.drawable.blk2);
					block[34].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[34] = true;
					showBlock(34);
					judgeChengyu();
				}
			}
		});

		block[35].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (buttonclick && block[35].getText().toString().equals("")) {
				} else if (buttonclick && blockCheck[35]) {
					block[35].setBackgroundResource(R.drawable.blk);
					block[35].setTextColor(Color.WHITE);
					blockCheck[35] = false;
					cancleBlock(35);
				} else if (buttonclick) {
					block[35].setBackgroundResource(R.drawable.blk2);
					block[35].setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[35] = true;
					showBlock(35);
					judgeChengyu();
				}
			}
		});

		pause = (Button) findViewById(R.id.onlinetime_pause);
		pause.setOnClickListener(new OnClickListener() {
			private long temp;
			@Override
			public void onClick(View v) {
				if(buttonclick_2){
				if (timejudge == 1) {
					gamepauseLayout.setVisibility(View.VISIBLE);
					Animation myAnimation = AnimationUtils.loadAnimation(
							OnlineTimeGameActivity.this, R.anim.fade_in);
					gamepauseLayout.startAnimation(myAnimation);
					time.stop();
					timejudge = 2; 
					buttonclick=false;
					temp = Long.parseLong(time.getText().toString().split(":")[1]) * 1000 +
			                Long.parseLong(time.getText().toString().split(":")[0]) * 60000;

					pause.setBackgroundResource(R.drawable.play);

					//////////////////////////////////////////////////////////
					
					//卷轴盖住
					/////////////////////////////////////////////////////////
				} else {
					
					Animation myAnimation = AnimationUtils.loadAnimation(
							OnlineTimeGameActivity.this, R.anim.fade_out);
					gamepauseLayout.startAnimation(myAnimation);
					gamepauseLayout.setVisibility(View.GONE);
					
					time.setBase(SystemClock.elapsedRealtime()-temp);//从上次停止时间开始计时
					time.start();
					timejudge = 1;
					buttonclick=true;
					pause.setBackgroundResource(R.drawable.pause);

					//////////////////////////////////////////////////////////
					
					//卷轴移走
					/////////////////////////////////////////////////////////
				}
				}
			}
		});

		// 重新开始
		restart = (Button) findViewById(R.id.onlinetime_restart);
		restart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(buttonclick){
					for (int i = 0; i < 36; i++) {
						blockCheck[i] = false;
					}
					JSONObject jObject = new JSONObject();
					try {
						jObject.put("requestType", Config.REQUEST_GET_CHENGYU);
						jObject.put("num", 9);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					try {
						socket = new Socket(Config.IP, 9999);
						in = new BufferedReader(new InputStreamReader(
								socket.getInputStream(), "UTF-8"));
						out = new PrintWriter(new BufferedWriter(
								new OutputStreamWriter(socket.getOutputStream(),
										"UTF-8")), true);
					} catch (Exception e) {
						e.printStackTrace();
					}

					out.println(jObject.toString());

					boolean flag1 = true;
					while (flag1) {
						try {
							jObject = new JSONObject(in.readLine());
							flag1 = false;
							JSONArray chengyu = (JSONArray) jObject.get("chengyus");
							chengyus = new String[36];
							for (int i = 0; i < 9; i++) {
								String str = (String) chengyu.get(i);
								Chengyu[i] = str;
								for (int j = 0; j < 4; j++) {
									chengyus[i * 4 + j] = str.substring(j, j + 1);
								}
							}

							Chengyus_random = new String[36];
							for (int i = 0; i < 36; i++) {
								Chengyus_random[i] = "";
							}
							for (int i = 0; i < 9; i++) {
								String str = (String) chengyu.get(i);
								for (int j = 0; j < 4; j++) {
									Random ran = new Random(24);
									int temp = 0;
									while (true) {
										temp = ran.nextInt(36);
										if (Chengyus_random[temp].equals("")) {
											Chengyus_random[temp] = str.substring(j,
													j + 1);
											break;
										}
									}
								}
							}
							int number = 0;
							for (int i = 0; i < 36; i++) {
								block[i].setText(Chengyus_random[number++]);
								block[i].setBackgroundResource(R.drawable.blk);
								block[i].setTextColor(Color.WHITE);
							}
						} catch (IOException e) {
						} catch (JSONException e) {
						}
					}
					try {
						if (socket != null) {
							socket.close();
						}
						if (in != null) {
							in.close();
						}
						if (out != null) {
							out.close();
						}
						socket = null;
						in = null;
						out = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				

				LeftCount.setText(" 连击数："+0);
				GradeShow.setText(""+0);
				Grade=0;
				clickCount=0;
				lastTime = System.currentTimeMillis();
				pause.setBackgroundResource(R.drawable.pause);
				timejudge=1;
	      		////////////////////////////////////////////////////
	      		
	      		//卷轴
	      		//////////////////////////////////////////////////////
				time.stop();
				time.setBase(0);
				time.setFormat("%s");
				time.setTextColor(Color.BLUE);
				lastTime = Long.MIN_VALUE;
				totalTime = 180;
				time.setText("180");
				time.start();
				
			}
		});

		returns = (Button) findViewById(R.id.onlinetime_returns);
		returns.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				if(buttonclick_2){
//				Intent intent = new Intent(OnlineTimeGameActivity.this,OnlinePlatformActivity.class);
//				startActivity(intent);
//				}
				whetherExitLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(
						OnlineTimeGameActivity.this, R.anim.fade_in);
				whetherExitLayout.startAnimation(myAnimation);
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
						OnlineTimeGameActivity.this, R.anim.fade_out);
				whetherExitLayout.startAnimation(myAnimation);
				whetherExitLayout.setVisibility(View.GONE);
			}
		});


  		
  		////////////////////////////////////////////////////
  		
  		//卷轴
  		//////////////////////////////////////////////////////
		LeftCount.setText(" 连击数："+0);
		GradeShow.setText(""+0);
		time.setBase(0);
		time.setFormat("%s");
		time.setTextColor(Color.BLUE);
		lastTime = Long.MIN_VALUE;
		totalTime = 180;
		time.setText("180");
		time.start();
		time.setOnChronometerTickListener(new OnChronometerTickListener() {

			@Override
			public void onChronometerTick(Chronometer chronometer) {
				// TODO Auto-generated method stub
				if (totalTime <= 0) {

					time.setText("0");
					time.stop();
					buttonclick = false;
					buttonclick_2=false;
					judgeGame();
				} else {
					totalTime--;
					time.setText("" + totalTime);
				}

			}
		});

	}

	public synchronized Handler getHandler() {

		return this.handler_grade;

	}

	public void showBlock(int num) {
		for (int i = 0; i < 4; i++) {
			if (textblock[i] == -1) {
				textblock[i] = num;
				textblockview[i].setText(Chengyus_random[num]);
				break;
			}
		}
	}

	public void cancleBlock(int num) {
		for (int i = 0; i < 4; i++) {
			if (textblock[i] == num) {
				textblock[i] = -1;
				textblockview[i].setText("");
				break;
			}
		}
	}

	public void judgeChengyu() {
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			if (textblock[i] == -1) {
				flag = false;
				break;
			}
		}

		if (flag) {
			String chengyu = null;
			chengyu = Chengyus_random[textblock[0]]
					+ Chengyus_random[textblock[1]]
					+ Chengyus_random[textblock[2]]
					+ Chengyus_random[textblock[3]];

			boolean judge = false;
			for (int i = 0; i < 9; i++) {
				if (chengyu.equals(Chengyu[i])) {
					judge = true;
					break;
				}
			}

			if (judge) {
				for (int i = 0; i < 4; i++) {
					block[textblock[i]].setText("");
					textblock[i] = -1;
					textblockview[i].setText("");
				}

				if (System.currentTimeMillis() - lastTime <= 5000) {
					clickCount++;
					if (Count < clickCount) {
						Count = clickCount;
					}
				} else {
					clickCount = 1;
				}
				
				LeftCount.setText("连击数："+clickCount);
				Grade += clickCount * 100;
				GradeShow.setText("" + Grade);
				lastTime = System.currentTimeMillis();

				updatechengyu();
			} else {
				for (int i = 0; i < 4; i++) {
					block[textblock[i]].setBackgroundResource(R.drawable.blk);
					block[textblock[i]].setTextColor(Color.WHITE);
					blockCheck[textblock[i]] = false;
					textblock[i] = -1;
					textblockview[i].setText("");
				}
				Grade -= 50;
				clickCount = 1;
				GradeShow.setText("" + Grade);
				lastTime = System.currentTimeMillis();
			}
		}

	}

	public void updatechengyu() {
		boolean flag = true;
		for (int i = 0; i < 36; i++) {
			if (!block[i].getText().toString().equals("")) {
				flag = false;
			}
		}

		if (flag) {
			for (int i = 0; i < 36; i++) {
				blockCheck[i] = false;
			}
			JSONObject jObject = new JSONObject();
			try {
				jObject.put("requestType", Config.REQUEST_GET_CHENGYU);
				jObject.put("num", 9);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			try {
				socket = new Socket(Config.IP, 9999);
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream(), "UTF-8"));
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream(),
								"UTF-8")), true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			out.println(jObject.toString());

			boolean flag1 = true;
			while (flag1) {
				try {
					jObject = new JSONObject(in.readLine());
					flag1 = false;
					JSONArray chengyu = (JSONArray) jObject.get("chengyus");
					chengyus = new String[36];
					for (int i = 0; i < 9; i++) {
						String str = (String) chengyu.get(i);
						Chengyu[i] = str;
						for (int j = 0; j < 4; j++) {
							chengyus[i * 4 + j] = str.substring(j, j + 1);
						}
					}

					Chengyus_random = new String[36];
					for (int i = 0; i < 36; i++) {
						Chengyus_random[i] = "";
					}
					for (int i = 0; i < 9; i++) {
						String str = (String) chengyu.get(i);
						for (int j = 0; j < 4; j++) {
							Random ran = new Random(24);
							int temp = 0;
							while (true) {
								temp = ran.nextInt(36);
								if (Chengyus_random[temp].equals("")) {
									Chengyus_random[temp] = str.substring(j,
											j + 1);
									break;
								}
							}
						}
					}
					int number = 0;
					for (int i = 0; i < 36; i++) {
						block[i].setText(Chengyus_random[number++]);
						block[i].setBackgroundResource(R.drawable.blk);
						block[i].setTextAppearance(this, R.style.blockTextSize);
						block[i].setTextColor(Color.WHITE);
					}
				} catch (IOException e) {
				} catch (JSONException e) {
				}
			}
			try {
				if (socket != null) {
					socket.close();
				}
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				socket = null;
				in = null;
				out = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void judgeGame() {

		if (true) {

			message = Message.obtain();
			message.what = 1;
			handler_grade.sendMessage(message);

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 显示游戏成绩
					message = Message.obtain();
					message.what = 2;
					message.arg1 = Grade;
					handler_grade.sendMessage(message);

					int t = Grade;
					int other = 0;
					if (Count >= 2)
						other = 100;
					for (int i = 3; i <= Count; i++) {
						other = other * 2;
					}
					Grade += other;
					boolean tt = true;
					while (tt) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						message = Message.obtain();

						if (t > Grade) {
							t = Grade;
							message.what = 2;
							message.arg1 = t;
							handler_grade.sendMessage(message);
							tt = false;
							break;
						}
						t += 30;
						message.what = 2;
						message.arg1 = t;
						handler_grade.sendMessage(message);
					}

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 显示金币数
					message = Message.obtain();
					message.what = 3;
					message.arg1 = InternetData.coin;
					handler_grade.sendMessage(message);
					int addcoin;
					if (Grade > 0) {
						addcoin = Grade / 100;
					} else {
						addcoin = 0;
					}
					t = InternetData.coin;
					InternetData.coin += addcoin;
					tt = true;
					while (tt) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						message = Message.obtain();

						if (t > InternetData.coin) {
							t = InternetData.coin;
							message.what = 3;
							message.arg1 = t;
							handler_grade.sendMessage(message);
							tt = false;
							break;
						}
						t += 30;
						message.what = 3;
						message.arg1 = t;
						handler_grade.sendMessage(message);
					}

					// 显示等级
					message = Message.obtain();
					message.what = 4;
					message.arg1 = InternetData.rank_grade;
					int addrank = Grade / 200;
					InternetData.rank_grade += addrank;
					// 没有升级
					if (InternetData.rank_grade <= new InternetData()
							.getRankGrade(InternetData.rank)
							&& InternetData.rank_grade > new InternetData()
									.getRankGrade(InternetData.rank - 1)) {
						message.arg2 = InternetData.rank_grade;
						handler_grade.sendMessage(message);
					} else if (InternetData.rank_grade <= new InternetData()
							.getRankGrade(InternetData.rank - 1)) {// 降级
						message.arg2 = new InternetData()
								.getRankGrade(InternetData.rank - 1) + 1;
						handler_grade.sendMessage(message);
						if (InternetData.rank > 0)
							InternetData.rank--;
						while (InternetData.rank >= 0) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							message = Message.obtain();
							message.what = 9;
							int temp = new InternetData()
									.getRankGrade(InternetData.rank) - 1;
							message.arg1 = temp;
							if (InternetData.rank_grade > new InternetData()
									.getRankGrade(InternetData.rank - 1)) {
								message.arg2 = InternetData.rank_grade;
								handler_grade.sendMessage(message);
								break;
							} else {
								if (InternetData.rank > 0) {
									message.arg2 = new InternetData()
											.getRankGrade(InternetData.rank - 1) + 1;
									handler_grade.sendMessage(message);
									InternetData.rank--;
								} else {
									message.arg2 = new InternetData()
											.getRankGrade(InternetData.rank - 1) + 1;
									handler_grade.sendMessage(message);
									InternetData.rank = 0;
								}
							}
						}
					} else {// 升级
						message.arg2 = new InternetData()
								.getRankGrade(InternetData.rank);
						handler_grade.sendMessage(message);
						if (InternetData.rank < 9)
							InternetData.rank++;
						while (true) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							message = Message.obtain();
							message.what = 6;
							int temp = new InternetData()
									.getRankGrade(InternetData.rank - 1) + 1;
							message.arg1 = temp;
							if (InternetData.rank_grade <= new InternetData()
									.getRankGrade(InternetData.rank)) {
								message.arg2 = InternetData.rank_grade;
								handler_grade.sendMessage(message);
								break;
							} else {
								message.arg2 = new InternetData()
										.getRankGrade(InternetData.rank);
								handler_grade.sendMessage(message);
								if (InternetData.rank < 9)
									InternetData.rank++;
							}
						}
					}
					if (Grade > InternetData.timescore) {
						message = Message.obtain();
						message.what = 5;
						handler_grade.sendMessage(message);
					}
					return;
				}
			}).start();

			JSONObject jObject = new JSONObject();
			try {
				jObject.put("requestType", Config.REQUEST_GAME);
				jObject.put("username", InternetData.username);
				jObject.put("rank", InternetData.rank);
				jObject.put("rank_grade", InternetData.rank_grade);
				jObject.put("coin", InternetData.coin);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			try {
				socket = new Socket(Config.IP, 9999);
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream(), "UTF-8"));
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream(),
								"UTF-8")), true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			out.println(jObject.toString());

			if (Grade > InternetData.timescore) {
				InternetData.timescore = Grade;
				InternetData.time_time = System.currentTimeMillis();
				try {
					jObject.put("requestType", Config.REQUEST_TIME_SCORE);
					jObject.put("username", InternetData.username);
					jObject.put("totalgrade", Grade);
					jObject.put("time", InternetData.time_time);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				out.println(jObject.toString());

				try {
					if (socket != null) {
						socket.close();
					}
					if (in != null) {
						in.close();
					}
					if (out != null) {
						out.close();
					}
					socket = null;
					in = null;
					out = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
