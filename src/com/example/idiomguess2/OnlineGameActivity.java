package com.example.idiomguess2;

import java.util.ArrayList;

import com.service.SinglePlayerManagement;
import com.util.Place;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class OnlineGameActivity extends Activity {

	private static final String fontPath = "fonts/font.ttf";
	private static final String fontPath2 = "fonts/midi.ttf";

	private LinearLayout gameBlock, blockPool, textPool, assistPool, assist,
			prompt, delete, show, operate, headLayout, escapeLayout, rateLayout;
	private Typeface tf, tf2;
	private Button[] block;
	private Button escapeOnline;
	private TextView playerRate1, playerRate2, rate;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onlinegame);
		
		block = new Button[36];
		
		tf = Typeface.createFromAsset(getAssets(), fontPath);
		tf2 = Typeface.createFromAsset(getAssets(), fontPath2);

		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // ÆÁÄ»¸ß£¨ÏñËØ£¬Èç£º800p£©

		show = (LinearLayout) findViewById(R.id.onlineShow);
		show.setPadding(screenWidth / 40, screenWidth / 40, 0, 0);
		LinearLayout.LayoutParams showLy = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, screenHeight * 2 / 7);
		show.setLayoutParams(showLy);

//		operate = (LinearLayout) findViewById(R.id.onlineOperate);
//		// show.setPadding(screenWidth/20, screenWidth/40, 0, 0);
//		LinearLayout.LayoutParams operateLy = new LinearLayout.LayoutParams(
//				screenHeight / 4, screenHeight / 3);
//		operateLy.bottomMargin = 10;
//		operate.setLayoutParams(operateLy);

		gameBlock = (LinearLayout) findViewById(R.id.onlineGameBlock);
		LinearLayout.LayoutParams gmbk = new LinearLayout.LayoutParams(
				screenHeight * 4 / 5, ViewGroup.LayoutParams.MATCH_PARENT);
		gmbk.topMargin = screenHeight / 20;
		gmbk.rightMargin = screenHeight / 15;
		gameBlock.setLayoutParams(gmbk);

		assistPool = (LinearLayout) findViewById(R.id.onlineAssistPool);
		LinearLayout.LayoutParams astplPl = new LinearLayout.LayoutParams(
				screenHeight / 4, ViewGroup.LayoutParams.MATCH_PARENT);
		assistPool.setPadding(screenHeight / 30, 0, screenHeight / 30, 0);
		assistPool.setLayoutParams(astplPl);

		assist = (LinearLayout) findViewById(R.id.onlineAssist);
		LinearLayout.LayoutParams astPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		assist.setLayoutParams(astPl);

		prompt = (LinearLayout) findViewById(R.id.onlinePrompt);
		LinearLayout.LayoutParams promptPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 4);
		promptPl.bottomMargin = screenHeight / 25;
		prompt.setLayoutParams(promptPl);

		delete = (LinearLayout) findViewById(R.id.onlineDelete);
		LinearLayout.LayoutParams deletePl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 4);
		deletePl.topMargin = screenHeight / 25;
		delete.setLayoutParams(deletePl);
		// assist.setPadding(screenHeight / 30, 0, screenHeight / 30, 0);

		blockPool = (LinearLayout) findViewById(R.id.onlineBlockPool);
		LinearLayout.LayoutParams blkPl = new LinearLayout.LayoutParams(
				screenHeight * 4 / 5, screenHeight * 4 / 5);
		blockPool.setLayoutParams(blkPl);

		textPool = (LinearLayout) findViewById(R.id.onlineTextPool);
		LinearLayout.LayoutParams textPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		textPl.bottomMargin = screenHeight / 30;
		textPool.setLayoutParams(textPl);
		
		headLayout = (LinearLayout)findViewById(R.id.headerLayout);
		RelativeLayout.LayoutParams headRl = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		headLayout.setPadding(screenHeight/20, screenHeight/20, screenHeight/20, 0);
		headRl.topMargin = screenHeight/20;
		headLayout.setLayoutParams(headRl);

		rateLayout = (LinearLayout)findViewById(R.id.rateLayout);
		rateLayout.setPadding(screenHeight/20, 0, screenHeight/20, screenHeight/20);
		
		escapeLayout = (LinearLayout)findViewById(R.id.escapeLayout);
		escapeLayout.setPadding(screenHeight/20, 0, screenHeight/20, screenHeight/20);
		
		playerRate1 = (TextView)findViewById(R.id.playerRate1);
		playerRate1.setTextAppearance(this, R.style.rateSize);
		playerRate1.setTypeface(tf2);
		
		playerRate2 = (TextView)findViewById(R.id.playerRate2);
		playerRate2.setTextAppearance(this, R.style.rateSize);
		playerRate2.setTypeface(tf2);
		
		rate = (TextView)findViewById(R.id.rate);
		rate.setTextAppearance(this, R.style.rateSize);
		rate.setTypeface(tf2);

	}
}
