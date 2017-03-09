package com.example.idiomguess2;

import java.io.*;
import java.net.Socket;

import org.json.*;

import com.woliao.constant.Config;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.text.InputType;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.animation.*;
import android.widget.*;
import android.widget.ImageView.ScaleType;


public class LoginActivity extends Activity {
	
	private ImageView logoin,logoon;
	private RelativeLayout buttonrlinLayout, buttonrlonLayout, goldLayout;
	private GameView surf;
	private LinearLayout loginLayout, logonLayout, viewLayout, buttoninLayout ,
						buttononLayout , editinLayout, editonLayout, verticalLayout, 
						firstGoodLayout, secondGoodLayout, moneyLayout;
	private EditText userEt, pwdEt, newuserEt, newpwdEt, reppwdEt, nicknameEt;
	private Button tryBt, registerBt, loginBt, exitBt, quickLoginBt, registerinBt, gobackBt, localMarket, closeMarket;
	private TranslateAnimation translateAnimation;
	private long exitTime;
	private int screenWidth, screenHeight;
	////////////////////////////////////
	public Socket socket;
	public PrintWriter out;
	public BufferedReader in;
	JSONObject message;//用来存放接收的信息	
	int requestType;//请求类型
	private static boolean flag=true;
	/////////////////////////////////////////////////////
	
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		screenWidth  = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）  
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）  

		surf = (GameView)findViewById(R.id.surf);
		surf.setVisibility(View.GONE);
		
		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		surf.setLayoutParams(lp2);
		
		loginLayout = (LinearLayout)findViewById(R.id.loginBack);
		loginLayout.setGravity(Gravity.CENTER);
		loginLayout.setOrientation(LinearLayout.VERTICAL);
		loginLayout.setVisibility(View.GONE);
		loginLayout.setBackgroundResource(R.color.backtransparent);
		
		RelativeLayout.LayoutParams loginlyLp = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		loginlyLp.addRule(RelativeLayout.CENTER_VERTICAL);
		loginlyLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		loginLayout.setLayoutParams(loginlyLp);
		//layout.addView(loginLayout, loginlyLp);
		
		logoin = new ImageView(this);
		logoin.setImageResource(R.drawable.logo5);
		LinearLayout.LayoutParams logoinLp = new LinearLayout.LayoutParams((int)(screenWidth/3.5),(int)(screenHeight/3.5));
		loginLayout.addView(logoin, logoinLp);

		viewLayout = new LinearLayout(this);
		viewLayout.setGravity(Gravity.CENTER);
		viewLayout.setBackgroundResource(R.drawable.background_style);
		viewLayout.setPadding(0, (int)(screenWidth/48.0), 0, (int)(screenWidth/60.0));
		LinearLayout.LayoutParams viewlyLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		loginLayout.addView(viewLayout, viewlyLp);
		
		editinLayout = new LinearLayout(this);
		editinLayout.setGravity(Gravity.CENTER);
		editinLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams editinlyLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		viewLayout.addView(editinLayout, editinlyLp);		
		
		userEt = new EditText(this);
		userEt.setSingleLine(true);
		userEt.setHint("用户名");
		userEt.setInputType(InputType.TYPE_CLASS_TEXT);
		userEt.setTextAppearance(this, R.style.textSize);
		LinearLayout.LayoutParams useretLp = new LinearLayout.LayoutParams(
				(int)(screenWidth/2.0),
				(int)(screenHeight/10.0));
		useretLp.leftMargin = (int)(screenWidth/24.0);
		useretLp.rightMargin = (int)(screenWidth/60.0);

		pwdEt = new EditText(this);
		pwdEt.setSingleLine(true);
		pwdEt.setHint("密码");
		pwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
		pwdEt.setTextAppearance(this, R.style.textSize);
		LinearLayout.LayoutParams pwdetLp = new LinearLayout.LayoutParams(
				(int)(screenWidth/2.0),
				(int)(screenHeight/10.0));
		pwdetLp.leftMargin = (int)(screenWidth/24.0);
		pwdetLp.rightMargin = (int)(screenWidth/60.0);
		
		editinLayout.addView(userEt, useretLp);
		editinLayout.addView(pwdEt, pwdetLp);
		
		loginBt = new Button(this);
		loginBt.setBackgroundResource(R.drawable.loginbt_selector);
		LinearLayout.LayoutParams loginbtLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		viewLayout.addView(loginBt, loginbtLp);
		
		localMarket = new Button(this);
		localMarket.setBackgroundResource(R.drawable.market);
		LinearLayout.LayoutParams localMarketbtLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		viewLayout.addView(localMarket, localMarketbtLp);
		
		buttonrlinLayout = new RelativeLayout(this);
		LinearLayout.LayoutParams buttonrlLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		loginLayout.addView(buttonrlinLayout, buttonrlLp);
		
		buttoninLayout = new LinearLayout(this);
		buttoninLayout.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams buttoninlyLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		buttonrlinLayout.addView(buttoninLayout, buttoninlyLp);
		
		registerBt = new Button(this);
		registerBt.setBackgroundResource(R.drawable.registerbt_selector);
		LinearLayout.LayoutParams registerbtLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		registerbtLp.weight = 1.0f;
		
		verticalLayout = new LinearLayout(this);
		verticalLayout.setGravity(Gravity.CENTER);
		verticalLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams verticallyLp = new LinearLayout.LayoutParams(
				(int)(screenWidth/2.4),
				ViewGroup.LayoutParams.WRAP_CONTENT);
		
		exitBt = new Button(this);
		exitBt.setBackgroundResource(R.drawable.exitbt_selector);
		LinearLayout.LayoutParams exitbtLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		exitbtLp.weight = 1.0f;
		
		buttoninLayout.addView(registerBt, registerbtLp);
		buttoninLayout.addView(verticalLayout, verticallyLp);
		buttoninLayout.addView(exitBt, exitbtLp);
		
		
		tryBt = new Button(this);
		tryBt.setBackgroundResource(R.drawable.practicebt_selector);
		LinearLayout.LayoutParams trybtLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		trybtLp.weight = 1.0f;
		
		quickLoginBt = new Button(this);
		quickLoginBt.setBackgroundResource(R.drawable.quickloginbt_selector);
		LinearLayout.LayoutParams quickLoginbtLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		quickLoginbtLp.weight = 1.0f;
		
		verticalLayout.addView(tryBt, trybtLp);
		verticalLayout.addView(quickLoginBt, quickLoginbtLp);
		
		
		logonLayout = (LinearLayout)findViewById(R.id.logonBack);
		logonLayout.setGravity(Gravity.CENTER);
		logonLayout.setOrientation(LinearLayout.VERTICAL);
		logonLayout.setVisibility(View.GONE);
		logonLayout.setBackgroundResource(R.color.backtransparent);
		
		RelativeLayout.LayoutParams logonlyLp = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		logonlyLp.addRule(RelativeLayout.CENTER_VERTICAL);
		logonlyLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		logonLayout.setLayoutParams(logonlyLp);
		
		logoon = new ImageView(this);
		logoon.setImageResource(R.drawable.logo5);
		LinearLayout.LayoutParams logoonLp = new LinearLayout.LayoutParams((int)(screenWidth/3.5),(int)(screenHeight/3.5));
		logonLayout.addView(logoon, logoonLp);
		
		editonLayout = new LinearLayout(this);
		editonLayout.setGravity(Gravity.CENTER);
		editonLayout.setOrientation(LinearLayout.VERTICAL);
		editonLayout.setBackgroundResource(R.drawable.background_style);
		editonLayout.setPadding((int)(screenWidth/24.0), (int)(screenWidth/48.0),(int)(screenWidth/24.0), (int)(screenWidth/60.0));
		LinearLayout.LayoutParams editonlyLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		logonLayout.addView(editonLayout, editonlyLp);
		
		newuserEt = new EditText(this);
		newuserEt.setSingleLine(true);
		newuserEt.setHint("用户名");
		newuserEt.setInputType(InputType.TYPE_CLASS_TEXT);
		newuserEt.setTextAppearance(this, R.style.textSize);
		LinearLayout.LayoutParams newuseretLp = new LinearLayout.LayoutParams(
				(int)(screenWidth/1.5),
				(int)(screenHeight/10.0));
		//newuseretLp.leftMargin = (int)(screenWidth/24.0);
		//newuseretLp.rightMargin = (int)(screenWidth/24.0);
		
		nicknameEt = new EditText(this);
		nicknameEt.setSingleLine(true);
		nicknameEt.setHint("昵称");
		nicknameEt.setInputType(InputType.TYPE_CLASS_TEXT);
		nicknameEt.setTextAppearance(this, R.style.textSize);
		LinearLayout.LayoutParams nicketLp = new LinearLayout.LayoutParams(
				(int)(screenWidth/1.5),
				(int)(screenHeight/10.0));

		newpwdEt = new EditText(this);
		newpwdEt.setSingleLine(true);
		newpwdEt.setHint("密码");
		newpwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
		newpwdEt.setTextAppearance(this, R.style.textSize);
		LinearLayout.LayoutParams newpwdetLp = new LinearLayout.LayoutParams(
				(int)(screenWidth/1.5),
				(int)(screenHeight/10.0));
		
		reppwdEt = new EditText(this);
		reppwdEt.setSingleLine(true);
		reppwdEt.setHint("确认密码");
		reppwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
		reppwdEt.setTextAppearance(this, R.style.textSize);
		LinearLayout.LayoutParams reppwdetLp = new LinearLayout.LayoutParams(
				(int)(screenWidth/1.5),
				(int)(screenHeight/10.0));
		//newpwdetLp.leftMargin = (int)(screenWidth/24.0);
		//newpwdetLp.rightMargin = (int)(screenWidth/60.0);
		
		editonLayout.addView(newuserEt, newuseretLp);
		editonLayout.addView(newpwdEt, newpwdetLp);
		editonLayout.addView(reppwdEt, reppwdetLp);
		editonLayout.addView(nicknameEt, nicketLp);
		
		buttonrlonLayout = new RelativeLayout(this);
		buttonrlonLayout.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams buttonrlonLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		logonLayout.addView(buttonrlonLayout, buttonrlonLp);
		
		buttononLayout = new LinearLayout(this);
		buttononLayout.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams buttononlyLp = new LinearLayout.LayoutParams(
				(int)(screenWidth/2.4),
				ViewGroup.LayoutParams.WRAP_CONTENT);
		buttonrlonLayout.addView(buttononLayout, buttononlyLp);
		
		registerinBt = new Button(this);
		registerinBt.setBackgroundResource(R.drawable.registerbt_selector);
		LinearLayout.LayoutParams registerinbtLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		registerinbtLp.weight = 1.0f;
		
		gobackBt = new Button(this);
		gobackBt.setBackgroundResource(R.drawable.gobackbt_selector);
		LinearLayout.LayoutParams gobackbtLp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		gobackbtLp.weight = 1.0f;
		
		buttononLayout.addView(registerinBt, registerinbtLp);
		buttononLayout.addView(gobackBt, gobackbtLp);
		
		goldLayout = (RelativeLayout)findViewById(R.id.goldLayout);
		firstGoodLayout = (LinearLayout)findViewById(R.id.firstGoodLayout);
        secondGoodLayout = (LinearLayout)findViewById(R.id.secondGoodLayout);
        moneyLayout = (LinearLayout)findViewById(R.id.moneyLayout);
		
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
        
        RelativeLayout.LayoutParams moneyLy = new RelativeLayout.LayoutParams(
        		ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        moneyLy.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.goods);
        moneyLy.addRule(RelativeLayout.CENTER_HORIZONTAL);
        moneyLy.bottomMargin = screenHeight/6;
        moneyLayout.setLayoutParams(moneyLy);
        
        closeMarket = (Button)findViewById(R.id.closeMarket);
        RelativeLayout.LayoutParams closeMarketRl = new RelativeLayout.LayoutParams(
        		screenHeight/10,screenHeight/10);
        closeMarketRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.goods);
        closeMarketRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        closeMarketRl.topMargin = screenHeight/10;
        closeMarketRl.leftMargin = screenHeight/20;
        closeMarket.setLayoutParams(closeMarketRl);
		
		surf.setVisibility(View.VISIBLE);
		//logonLayout.setVisibility(View.VISIBLE);
		loginLayout.setVisibility(View.VISIBLE);
		
		translateAnimation = new TranslateAnimation(
				0, 0, -screenHeight, 0);
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
						loginLayout.clearAnimation();
					}
				});
		loginLayout.startAnimation(translateAnimation);
		
		//////////////////////////////////////////////////////////////////////////
		//我加的部分
		
		localMarket.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goldLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(LoginActivity.this,R.anim.fade_in);
				goldLayout.startAnimation(myAnimation);
			}
		});
		
		closeMarket.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation myAnimation = AnimationUtils.loadAnimation(LoginActivity.this,R.anim.fade_out);
				goldLayout.startAnimation(myAnimation);
				goldLayout.setVisibility(View.GONE);
			}
		});
		
		tryBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,StoryActivity.class));
			}
		});

		registerBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				///////////////////////////////////////////////////////////////////////////////////////////
				
				//LoginActivity.this.startActivity(new Intent(LoginActivity.this,StoryActivity.class));
				translateAnimation = new TranslateAnimation(
						0, 0, 0, screenHeight);
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
								loginLayout.clearAnimation();
								loginLayout.setVisibility(View.GONE);
							}
						});
				loginLayout.startAnimation(translateAnimation);
				
				logonLayout.setVisibility(View.VISIBLE);
				
				translateAnimation = new TranslateAnimation(
						0, 0, -screenHeight, 0);
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
								loginLayout.clearAnimation();
							}
						});
				logonLayout.startAnimation(translateAnimation);
//				
//				//跳转到注册界面
//				//Intent intent = new Intent(MainActivity.this,Register.class);
//				//startActivity(intent);
			}		
		});
		
		registerinBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		gobackBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				translateAnimation = new TranslateAnimation(
						0, 0, 0,-screenHeight);
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
								logonLayout.clearAnimation();
								logonLayout.setVisibility(View.GONE);
							}
						});
				logonLayout.startAnimation(translateAnimation);
				
				loginLayout.setVisibility(View.VISIBLE);
				
				translateAnimation = new TranslateAnimation(
						0, 0, screenHeight, 0);
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
								loginLayout.clearAnimation();
							}
						});
				loginLayout.startAnimation(translateAnimation);
			}
		});
		
		exitBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
				System.exit(0);
			}
		});

		quickLoginBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					socket = new Socket("10.3.2.59", 9999);
					in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				JSONObject jObject = new JSONObject();
				try {
					jObject.put(Config.REQUEST_TYPE, Config.REQUEST_QUICK_LOGIN);
				} catch (JSONException e) {
					e.printStackTrace();
				}						

				out.println(jObject.toString());

				while (flag) {
					try {
						receiveMessage();
					} catch (Exception e) {
						//flag=false;
						break; 
					}
				}

				try {
					if(socket != null){
						socket.close();
					}
					if(in != null){
						in.close();
					}
					if(out != null){
						out.close();
					}
					socket = null;
					in = null;
					out = null;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}		
		});
		
		loginBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(LoginActivity.this,MainActivity.class));
//				InternetData.username = userEt.getText().toString();
//				InternetData.password = pwdEt.getText().toString();
//
//				try {
//					socket = new Socket("10.3.2.59", 9999);
//					in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
//					out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				JSONObject jObject = new JSONObject();
//				try {
//					//登录
//					jObject.put("requestType", Config.REQUEST_LOGIN);
//					jObject.put("username", InternetData.username);
//					jObject.put("password", InternetData.password );
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}						
//
//				out.println(jObject.toString());
//
//				while (flag) {
//					try {
//						receiveMessage();
//					} catch (Exception e) {
//						//flag=false;
//						break; 
//					}
//				}
//
//				try {
//					if(socket != null){
//						socket.close();
//					}
//					if(in != null){
//						in.close();
//					}
//					if(out != null){
//						out.close();
//					}
//					socket = null;
//					in = null;
//					out = null;
//				} catch (Exception e) {
//					e.printStackTrace();
//				}

			}		
		});
		////////////////////////////////////////////////////////////////////////////
	}
	
	public void onPause(){
		super.onPause();
		surf.onPause();
	}
	
	public void onResume(){
		super.onResume();
		surf.onResume();
	}
	
	public void onStop(){
		super.onStop();
		surf.onPause();
	}
	
	public void onRestart(){
		super.onRestart();
		surf.onResume();
	}
	
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
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	//接收信息
	public void receiveMessage() throws JSONException, IOException{
		//将从输入流中读到的信息封装成JSONObject对象

		message = new JSONObject(in.readLine());

		try {
			requestType = message.getInt(Config.RESULT);
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		switch (requestType) {
		case Config.SUCCESS: 
			flag=false;
			Intent intent = new Intent(LoginActivity.this,GameActivity.class);
			startActivity(intent);
			break;
		case Config.FAIl: 
			flag=false;
			Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
			break;
		case Config.ONLINE: 
			flag=false;
			Toast.makeText(LoginActivity.this,"用户在线，无需登录",Toast.LENGTH_SHORT).show();
			break;
		case Config.QUICK_FAIl: 
			flag=false;
			Toast.makeText(LoginActivity.this,"快速登录失败",Toast.LENGTH_SHORT).show();
			break;
		case Config.REQUEST_QUICK_LOGIN: 
			InternetData.username = message.getString("username");
			InternetData.password = message.getString("password");
			flag=false;
			new AlertDialog.Builder(LoginActivity.this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("快速登录")
			.setMessage("系统为您分配的用户名、密码分别为：\n用户名 ："+InternetData.username+"\n密  码 ："+InternetData.password)
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(LoginActivity.this,GameActivity.class);
					startActivity(intent);
				}
			})
			.show();
			break;
		default:
			break;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////
}

