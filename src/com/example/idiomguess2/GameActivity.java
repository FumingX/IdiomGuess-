package com.example.idiomguess2;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity {
	
	private Button block1, block2, block3, block4, block5, block6, block7,
			block8, block9, block10, block11, block12, block13, block14,
			block15, block16, block17, block18, block19, block20, block21,
			block22, block23, block24, block25, block26, block27, block28,
			block29, block30, block31, block32, block33, block34, block35,
			block36;
	private static final String fontPath = "fonts/font.ttf";
	private boolean[] blockCheck;
	private LinearLayout gameBlock, blockPool, textPool, assistPool, assist, prompt, delete, show, operate;
	private ImageView smallLogo;
	private TextView blockText1, blockText2, blockText3, blockText4;
	private Typeface tf;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.localgame);
	
		tf = Typeface.createFromAsset(getAssets(), fontPath);
		blockCheck = new boolean[36];
		
		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）  
		
		show = (LinearLayout) findViewById(R.id.localShow);
		show.setPadding(screenWidth/20, screenWidth/40, 0, 0);
		LinearLayout.LayoutParams showLy = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, screenHeight*2/7);
		show.setLayoutParams(showLy);
		
		operate = (LinearLayout) findViewById(R.id.operate);
		//show.setPadding(screenWidth/20, screenWidth/40, 0, 0);
		LinearLayout.LayoutParams operateLy = new LinearLayout.LayoutParams(
				screenHeight/4, screenHeight/3);
		operateLy.bottomMargin = 10;
		operate.setLayoutParams(operateLy);
		
		gameBlock = (LinearLayout) findViewById(R.id.localGameBlock);
		LinearLayout.LayoutParams gmbk = new LinearLayout.LayoutParams(
				screenHeight * 4 / 5, ViewGroup.LayoutParams.MATCH_PARENT);
		gmbk.topMargin = screenHeight / 20;
		gmbk.rightMargin = screenHeight / 15;
		gameBlock.setLayoutParams(gmbk);
		
		assistPool = (LinearLayout) findViewById(R.id.localAssistPool);
		LinearLayout.LayoutParams astplPl = new LinearLayout.LayoutParams(
				screenHeight/4,
				ViewGroup.LayoutParams.MATCH_PARENT);
		assistPool.setPadding(screenHeight / 30, 0, screenHeight / 30, 0);
		assistPool.setLayoutParams(astplPl);
		
		assist = (LinearLayout) findViewById(R.id.localAssist);
		LinearLayout.LayoutParams astPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		assist.setLayoutParams(astPl);
		
		prompt = (LinearLayout) findViewById(R.id.localPrompt);
		LinearLayout.LayoutParams promptPl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				screenHeight/4);
		promptPl.bottomMargin = screenHeight/25;
		prompt.setLayoutParams(promptPl);
		
		delete = (LinearLayout) findViewById(R.id.localDelete);
		LinearLayout.LayoutParams deletePl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				screenHeight/4);
		deletePl.topMargin = screenHeight/25;
		delete.setLayoutParams(deletePl);
		//assist.setPadding(screenHeight / 30, 0, screenHeight / 30, 0);
		

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
		
		blockText1 = (TextView)findViewById(R.id.localBlockText1);
		blockText1.setTextColor(getResources().getColor(
				R.drawable.porcelain));
		blockText1.setTextAppearance(this, R.style.blockSize);
		blockText1.setTypeface(tf);
		blockText1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				blockText1.setText("美");
			}
		});
		
		blockText2 = (TextView)findViewById(R.id.localBlockText2);
		blockText2.setTextColor(getResources().getColor(
				R.drawable.porcelain));
		blockText2.setTextAppearance(this, R.style.blockSize);
		blockText2.setTypeface(tf);
		blockText2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				blockText2.setText("");
			}
		});
		
		blockText3 = (TextView)findViewById(R.id.localBlockText3);
		blockText3.setTextColor(getResources().getColor(
				R.drawable.porcelain));
		blockText3.setTextAppearance(this, R.style.blockSize);
		blockText3.setTypeface(tf);
		blockText3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				blockText3.setText("");
			}
		});
		
		blockText4 = (TextView)findViewById(R.id.localBlockText4);
		blockText4.setTextColor(getResources().getColor(
				R.drawable.porcelain));
		blockText4.setTextAppearance(this, R.style.blockSize);
		blockText4.setTypeface(tf);
		blockText4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				blockText4.setText("");
			}
		});
		
		block1 = (Button) findViewById(R.id.localBlock1);
		block1.setTextAppearance(this, R.style.blockTextSize);
		block1.setTextColor(Color.WHITE);
		block1.setTypeface(tf);
		
		block2 = (Button)findViewById(R.id.localBlock2);
		block2.setTextAppearance(this, R.style.blockTextSize);
		block2.setTextColor(Color.WHITE);
		block2.setTypeface(tf);
		
		block3 = (Button)findViewById(R.id.localBlock3);
		block3.setTextAppearance(this, R.style.blockTextSize);
		block3.setTextColor(Color.WHITE);
		block3.setTypeface(tf);
		
		block4 = (Button)findViewById(R.id.localBlock4);
		block4.setTextAppearance(this, R.style.blockTextSize);
		block4.setTextColor(Color.WHITE);
		block4.setTypeface(tf);
		
		block5 = (Button)findViewById(R.id.localBlock5);
		block5.setTextAppearance(this, R.style.blockTextSize);
		block5.setTextColor(Color.WHITE);
		block5.setTypeface(tf);
		
		block6 = (Button)findViewById(R.id.localBlock6);
		block6.setTextAppearance(this, R.style.blockTextSize);
		block6.setTextColor(Color.WHITE);
		block6.setTypeface(tf);
		
		block7 = (Button)findViewById(R.id.localBlock7);
		block7.setTextAppearance(this, R.style.blockTextSize);
		block7.setTextColor(Color.WHITE);
		block7.setTypeface(tf);
		
		block8 = (Button)findViewById(R.id.localBlock8);
		block8.setTextAppearance(this, R.style.blockTextSize);
		block8.setTextColor(Color.WHITE);
		block8.setTypeface(tf);
		
		block9 = (Button)findViewById(R.id.localBlock9);
		block9.setTextAppearance(this, R.style.blockTextSize);
		block9.setTextColor(Color.WHITE);
		block9.setTypeface(tf);
		
		block10 = (Button)findViewById(R.id.localBlock10);
		block10.setTextAppearance(this, R.style.blockTextSize);
		block10.setTextColor(Color.WHITE);
		block10.setTypeface(tf);
		
		block11 = (Button)findViewById(R.id.localBlock11);
		block11.setTextAppearance(this, R.style.blockTextSize);
		block11.setTextColor(Color.WHITE);
		block11.setTypeface(tf);
		
		block12 = (Button)findViewById(R.id.localBlock12);
		block12.setTextAppearance(this, R.style.blockTextSize);
		block12.setTextColor(Color.WHITE);
		block12.setTypeface(tf);
		
		block13 = (Button)findViewById(R.id.localBlock13);
		block13.setTextAppearance(this, R.style.blockTextSize);
		block13.setTextColor(Color.WHITE);
		block13.setTypeface(tf);
		
		block14 = (Button)findViewById(R.id.localBlock14);
		block14.setTextAppearance(this, R.style.blockTextSize);
		block14.setTextColor(Color.WHITE);
		block14.setTypeface(tf);
		
		block15 = (Button)findViewById(R.id.localBlock15);
		block15.setTextAppearance(this, R.style.blockTextSize);
		block15.setTextColor(Color.WHITE);
		block15.setTypeface(tf);
		
		block16 = (Button)findViewById(R.id.localBlock16);
		block16.setTextAppearance(this, R.style.blockTextSize);
		block16.setTextColor(Color.WHITE);
		block16.setTypeface(tf);
		
		block17 = (Button)findViewById(R.id.localBlock17);
		block17.setTextAppearance(this, R.style.blockTextSize);
		block17.setTextColor(Color.WHITE);
		block17.setTypeface(tf);
		
		block18 = (Button)findViewById(R.id.localBlock18);
		block18.setTextAppearance(this, R.style.blockTextSize);
		block18.setTextColor(Color.WHITE);
		block18.setTypeface(tf);
		
		block19 = (Button)findViewById(R.id.localBlock19);
		block19.setTextAppearance(this, R.style.blockTextSize);
		block19.setTextColor(Color.WHITE);
		block19.setTypeface(tf);
		
		block20 = (Button)findViewById(R.id.localBlock20);
		block20.setTextAppearance(this, R.style.blockTextSize);
		block20.setTextColor(Color.WHITE);
		block20.setTypeface(tf);
		
		block21 = (Button)findViewById(R.id.localBlock21);
		block21.setTextAppearance(this, R.style.blockTextSize);
		block21.setTextColor(Color.WHITE);
		block21.setTypeface(tf);
		
		block22 = (Button)findViewById(R.id.localBlock22);
		block22.setTextAppearance(this, R.style.blockTextSize);
		block22.setTextColor(Color.WHITE);
		block22.setTypeface(tf);
		
		block23 = (Button)findViewById(R.id.localBlock23);
		block23.setTextAppearance(this, R.style.blockTextSize);
		block23.setTextColor(Color.WHITE);
		block23.setTypeface(tf);
		
		block24 = (Button)findViewById(R.id.localBlock24);
		block24.setTextAppearance(this, R.style.blockTextSize);
		block24.setTextColor(Color.WHITE);
		block24.setTypeface(tf);
		
		block25 = (Button)findViewById(R.id.localBlock25);
		block25.setTextAppearance(this, R.style.blockTextSize);
		block25.setTextColor(Color.WHITE);
		block25.setTypeface(tf);
		
		block26 = (Button)findViewById(R.id.localBlock26);
		block26.setTextAppearance(this, R.style.blockTextSize);
		block26.setTextColor(Color.WHITE);
		block26.setTypeface(tf);
		
		block27 = (Button)findViewById(R.id.localBlock27);
		block27.setTextAppearance(this, R.style.blockTextSize);
		block27.setTextColor(Color.WHITE);
		block27.setTypeface(tf);
		
		block28 = (Button)findViewById(R.id.localBlock28);
		block28.setTextAppearance(this, R.style.blockTextSize);
		block28.setTextColor(Color.WHITE);
		block28.setTypeface(tf);
		
		block29 = (Button)findViewById(R.id.localBlock29);
		block29.setTextAppearance(this, R.style.blockTextSize);
		block29.setTextColor(Color.WHITE);
		block29.setTypeface(tf);
		
		block30 = (Button)findViewById(R.id.localBlock30);
		block30.setTextAppearance(this, R.style.blockTextSize);
		block30.setTextColor(Color.WHITE);
		block30.setTypeface(tf);
		
		block31 = (Button)findViewById(R.id.localBlock31);
		block31.setTextAppearance(this, R.style.blockTextSize);
		block31.setTextColor(Color.WHITE);
		block31.setTypeface(tf);
		
		block32 = (Button)findViewById(R.id.localBlock32);
		block32.setTextAppearance(this, R.style.blockTextSize);
		block32.setTextColor(Color.WHITE);
		block32.setTypeface(tf);
		
		block33 = (Button)findViewById(R.id.localBlock33);
		block33.setTextAppearance(this, R.style.blockTextSize);
		block33.setTextColor(Color.WHITE);
		block33.setTypeface(tf);

		block34 = (Button) findViewById(R.id.localBlock34);
		block34.setTextAppearance(this, R.style.blockTextSize);
		block34.setTextColor(Color.WHITE);
		block34.setTypeface(tf);

		block35 = (Button) findViewById(R.id.localBlock35);
		block35.setTextAppearance(this, R.style.blockTextSize);
		block35.setTextColor(Color.WHITE);
		block35.setTypeface(tf);

		block36 = (Button) findViewById(R.id.localBlock36);
		block36.setTextAppearance(this, R.style.blockTextSize);
		block36.setTextColor(Color.WHITE);
		block36.setTypeface(tf);

		block1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[0]) {
					block1.setBackgroundResource(R.drawable.blk);
					block1.setTextColor(Color.WHITE);
					blockCheck[0] = false;
				} else {
					block1.setBackgroundResource(R.drawable.blk2);
					block1.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[0] = true;
				}
			}
		});

		block2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[1]) {
					block2.setBackgroundResource(R.drawable.blk);
					block2.setTextColor(Color.WHITE);
					blockCheck[1] = false;
				} else {
					block2.setBackgroundResource(R.drawable.blk2);
					block2.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[1] = true;
				}
			}
		});

		block3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[2]) {
					block3.setBackgroundResource(R.drawable.blk);
					block3.setTextColor(Color.WHITE);
					blockCheck[2] = false;
				} else {
					block3.setBackgroundResource(R.drawable.blk2);
					block3.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[2] = true;
				}
			}
		});
		
		block4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[3]) {
					block4.setBackgroundResource(R.drawable.blk);
					block4.setTextColor(Color.WHITE);
					blockCheck[3] = false;
				} else {
					block4.setBackgroundResource(R.drawable.blk2);
					block4.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[3] = true;
				}
			}
		});

		block5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[4]) {
					block5.setBackgroundResource(R.drawable.blk);
					block5.setTextColor(Color.WHITE);
					blockCheck[4] = false;
				} else {
					block5.setBackgroundResource(R.drawable.blk2);
					block5.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[4] = true;
				}
			}
		});

		block6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[5]) {
					block6.setBackgroundResource(R.drawable.blk);
					block6.setTextColor(Color.WHITE);
					blockCheck[5] = false;
				} else {
					block6.setBackgroundResource(R.drawable.blk2);
					block6.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[5] = true;
				}
			}
		});

		block7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[6]) {
					block7.setBackgroundResource(R.drawable.blk);
					block7.setTextColor(Color.WHITE);
					blockCheck[6] = false;
				} else {
					block7.setBackgroundResource(R.drawable.blk2);
					block7.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[6] = true;
				}
			}
		});

		block8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[7]) {
					block8.setBackgroundResource(R.drawable.blk);
					block8.setTextColor(Color.WHITE);
					blockCheck[7] = false;
				} else {
					block8.setBackgroundResource(R.drawable.blk2);
					block8.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[7] = true;
				}
			}
		});

		block9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[8]) {
					block9.setBackgroundResource(R.drawable.blk);
					block9.setTextColor(Color.WHITE);
					blockCheck[8] = false;
				} else {
					block9.setBackgroundResource(R.drawable.blk2);
					block9.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[8] = true;
				}
			}
		});

		block10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[9]) {
					block10.setBackgroundResource(R.drawable.blk);
					block10.setTextColor(Color.WHITE);
					blockCheck[9] = false;
				} else {
					block10.setBackgroundResource(R.drawable.blk2);
					block10.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[9] = true;
				}
			}
		});

		block11.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[10]) {
					block11.setBackgroundResource(R.drawable.blk);
					block11.setTextColor(Color.WHITE);
					blockCheck[10] = false;
				} else {
					block11.setBackgroundResource(R.drawable.blk2);
					block11.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[10] = true;
				}
			}
		});

		block12.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[11]) {
					block12.setBackgroundResource(R.drawable.blk);
					block12.setTextColor(Color.WHITE);
					blockCheck[11] = false;
				} else {
					block12.setBackgroundResource(R.drawable.blk2);
					block12.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[11] = true;
				}
			}
		});
		
		block13.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[12]) {
					block13.setBackgroundResource(R.drawable.blk);
					block13.setTextColor(Color.WHITE);
					blockCheck[12] = false;
				} else {
					block13.setBackgroundResource(R.drawable.blk2);
					block13.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[12] = true;
				}
			}
		});

		block14.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[13]) {
					block14.setBackgroundResource(R.drawable.blk);
					block14.setTextColor(Color.WHITE);
					blockCheck[13] = false;
				} else {
					block14.setBackgroundResource(R.drawable.blk2);
					block14.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[13] = true;
				}
			}
		});

		block15.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[14]) {
					block15.setBackgroundResource(R.drawable.blk);
					block15.setTextColor(Color.WHITE);
					blockCheck[14] = false;
				} else {
					block15.setBackgroundResource(R.drawable.blk2);
					block15.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[14] = true;
				}
			}
		});
		
		block16.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[15]) {
					block16.setBackgroundResource(R.drawable.blk);
					block16.setTextColor(Color.WHITE);
					blockCheck[15] = false;
				} else {
					block16.setBackgroundResource(R.drawable.blk2);
					block16.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[15] = true;
				}
			}
		});

		block17.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[16]) {
					block17.setBackgroundResource(R.drawable.blk);
					block17.setTextColor(Color.WHITE);
					blockCheck[16] = false;
				} else {
					block17.setBackgroundResource(R.drawable.blk2);
					block17.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[16] = true;
				}
			}
		});

		block18.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[17]) {
					block18.setBackgroundResource(R.drawable.blk);
					block18.setTextColor(Color.WHITE);
					blockCheck[17] = false;
				} else {
					block18.setBackgroundResource(R.drawable.blk2);
					block18.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[17] = true;
				}
			}
		});

		block19.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[18]) {
					block19.setBackgroundResource(R.drawable.blk);
					block19.setTextColor(Color.WHITE);
					blockCheck[18] = false;
				} else {
					block19.setBackgroundResource(R.drawable.blk2);
					block19.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[18] = true;
				}
			}
		});

		block20.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[19]) {
					block20.setBackgroundResource(R.drawable.blk);
					block20.setTextColor(Color.WHITE);
					blockCheck[19] = false;
				} else {
					block20.setBackgroundResource(R.drawable.blk2);
					block20.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[19] = true;
				}
			}
		});

		block21.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[20]) {
					block21.setBackgroundResource(R.drawable.blk);
					block21.setTextColor(Color.WHITE);
					blockCheck[20] = false;
				} else {
					block21.setBackgroundResource(R.drawable.blk2);
					block21.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[20] = true;
				}
			}
		});

		block22.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[21]) {
					block22.setBackgroundResource(R.drawable.blk);
					block22.setTextColor(Color.WHITE);
					blockCheck[21] = false;
				} else {
					block22.setBackgroundResource(R.drawable.blk2);
					block22.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[21] = true;
				}
			}
		});

		block23.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[22]) {
					block23.setBackgroundResource(R.drawable.blk);
					block23.setTextColor(Color.WHITE);
					blockCheck[22] = false;
				} else {
					block23.setBackgroundResource(R.drawable.blk2);
					block23.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[22] = true;
				}
			}
		});

		block24.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[23]) {
					block24.setBackgroundResource(R.drawable.blk);
					block24.setTextColor(Color.WHITE);
					blockCheck[23] = false;
				} else {
					block24.setBackgroundResource(R.drawable.blk2);
					block24.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[23] = true;
				}
			}
		});
		
		block25.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[24]) {
					block25.setBackgroundResource(R.drawable.blk);
					block25.setTextColor(Color.WHITE);
					blockCheck[24] = false;
				} else {
					block25.setBackgroundResource(R.drawable.blk2);
					block25.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[24] = true;
				}
			}
		});

		block26.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[25]) {
					block26.setBackgroundResource(R.drawable.blk);
					block26.setTextColor(Color.WHITE);
					blockCheck[25] = false;
				} else {
					block26.setBackgroundResource(R.drawable.blk2);
					block26.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[25] = true;
				}
			}
		});

		block27.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[26]) {
					block27.setBackgroundResource(R.drawable.blk);
					block27.setTextColor(Color.WHITE);
					blockCheck[26] = false;
				} else {
					block27.setBackgroundResource(R.drawable.blk2);
					block27.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[26] = true;
				}
			}
		});
		
		block28.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[27]) {
					block28.setBackgroundResource(R.drawable.blk);
					block28.setTextColor(Color.WHITE);
					blockCheck[27] = false;
				} else {
					block28.setBackgroundResource(R.drawable.blk2);
					block28.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[27] = true;
				}
			}
		});

		block29.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[28]) {
					block29.setBackgroundResource(R.drawable.blk);
					block29.setTextColor(Color.WHITE);
					blockCheck[28] = false;
				} else {
					block29.setBackgroundResource(R.drawable.blk2);
					block29.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[28] = true;
				}
			}
		});

		block30.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[29]) {
					block30.setBackgroundResource(R.drawable.blk);
					block30.setTextColor(Color.WHITE);
					blockCheck[29] = false;
				} else {
					block30.setBackgroundResource(R.drawable.blk2);
					block30.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[29] = true;
				}
			}
		});

		block31.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[30]) {
					block31.setBackgroundResource(R.drawable.blk);
					block31.setTextColor(Color.WHITE);
					blockCheck[30] = false;
				} else {
					block31.setBackgroundResource(R.drawable.blk2);
					block31.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[30] = true;
				}
			}
		});

		block32.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[31]) {
					block32.setBackgroundResource(R.drawable.blk);
					block32.setTextColor(Color.WHITE);
					blockCheck[31] = false;
				} else {
					block32.setBackgroundResource(R.drawable.blk2);
					block32.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[31] = true;
				}
			}
		});

		block33.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[32]) {
					block33.setBackgroundResource(R.drawable.blk);
					block33.setTextColor(Color.WHITE);
					blockCheck[32] = false;
				} else {
					block33.setBackgroundResource(R.drawable.blk2);
					block33.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[32] = true;
				}
			}
		});

		block34.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[33]) {
					block34.setBackgroundResource(R.drawable.blk);
					block34.setTextColor(Color.WHITE);
					blockCheck[33] = false;
				} else {
					block34.setBackgroundResource(R.drawable.blk2);
					block34.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[33] = true;
				}
			}
		});

		block35.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[34]) {
					block35.setBackgroundResource(R.drawable.blk);
					block35.setTextColor(Color.WHITE);
					blockCheck[34] = false;
				} else {
					block35.setBackgroundResource(R.drawable.blk2);
					block35.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[34] = true;
				}
			}
		});

		block36.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (blockCheck[35]) {
					block36.setBackgroundResource(R.drawable.blk);
					block36.setTextColor(Color.WHITE);
					blockCheck[35] = false;
				} else {
					block36.setBackgroundResource(R.drawable.blk2);
					block36.setTextColor(getResources().getColor(
							R.drawable.porcelain));
					blockCheck[35] = true;
				}
			}
		});
	}
}
