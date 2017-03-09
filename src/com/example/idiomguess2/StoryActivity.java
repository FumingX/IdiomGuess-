package com.example.idiomguess2;

import java.util.ArrayList;
import java.util.HashMap;

import com.dao.Dao;
import com.util.Barrier;
import com.util.MyApp;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
//import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;
import android.widget.RelativeLayout;

@SuppressWarnings("deprecation")
public class StoryActivity extends Activity implements OnTouchListener,
		OnGestureListener {
	private GestureDetector detector;

	private Button[] levelBt = new Button[108];
	private boolean[] levelMark = new boolean[108];
	private int screenWidth, screenHeight;

	private Button leftBt, rightBt;

	private ViewFlipper viewFlipper;

	// 左右滑动时手指按下的X坐标
	private float touchDownX;
	// 左右滑动时手指松开的X坐标
	private float touchUpX;

	private Dao dao;
	private Intent intent;
	private boolean haveAudio = true, haveBackMusic = true;// 判断是否有音效
	private SoundPool soundpool; // 声明一个SoundPool对象
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>(); // 创建一个HashMap对象
	// private MediaPlayer mPlayer;
	private MyApp myApp;

	// 找到按钮对应id
	private void findBtnById() {
		levelBt[0] = (Button) findViewById(R.id.level1);
		levelBt[1] = (Button) findViewById(R.id.level2);
		levelBt[2] = (Button) findViewById(R.id.level3);
		levelBt[3] = (Button) findViewById(R.id.level4);
		levelBt[4] = (Button) findViewById(R.id.level5);
		levelBt[5] = (Button) findViewById(R.id.level6);
		levelBt[6] = (Button) findViewById(R.id.level7);
		levelBt[7] = (Button) findViewById(R.id.level8);
		levelBt[8] = (Button) findViewById(R.id.level9);
		levelBt[9] = (Button) findViewById(R.id.level10);
		levelBt[10] = (Button) findViewById(R.id.level11);
		levelBt[11] = (Button) findViewById(R.id.level12);
		levelBt[12] = (Button) findViewById(R.id.level13);
		levelBt[13] = (Button) findViewById(R.id.level14);
		levelBt[14] = (Button) findViewById(R.id.level15);
		levelBt[15] = (Button) findViewById(R.id.level16);
		levelBt[16] = (Button) findViewById(R.id.level17);
		levelBt[17] = (Button) findViewById(R.id.level18);
		levelBt[18] = (Button) findViewById(R.id.level19);
		levelBt[19] = (Button) findViewById(R.id.level20);
		levelBt[20] = (Button) findViewById(R.id.level21);
		levelBt[21] = (Button) findViewById(R.id.level22);
		levelBt[22] = (Button) findViewById(R.id.level23);
		levelBt[23] = (Button) findViewById(R.id.level24);
		levelBt[24] = (Button) findViewById(R.id.level25);
		levelBt[25] = (Button) findViewById(R.id.level26);
		levelBt[26] = (Button) findViewById(R.id.level27);
		levelBt[27] = (Button) findViewById(R.id.level28);
		levelBt[28] = (Button) findViewById(R.id.level29);
		levelBt[29] = (Button) findViewById(R.id.level30);
		levelBt[30] = (Button) findViewById(R.id.level31);
		levelBt[31] = (Button) findViewById(R.id.level32);
		levelBt[32] = (Button) findViewById(R.id.level33);
		levelBt[33] = (Button) findViewById(R.id.level34);
		levelBt[34] = (Button) findViewById(R.id.level35);
		levelBt[35] = (Button) findViewById(R.id.level36);
		levelBt[36] = (Button) findViewById(R.id.level37);
		levelBt[37] = (Button) findViewById(R.id.level38);
		levelBt[38] = (Button) findViewById(R.id.level39);
		levelBt[39] = (Button) findViewById(R.id.level40);
		levelBt[40] = (Button) findViewById(R.id.level41);
		levelBt[41] = (Button) findViewById(R.id.level42);
		levelBt[42] = (Button) findViewById(R.id.level43);
		levelBt[43] = (Button) findViewById(R.id.level44);
		levelBt[44] = (Button) findViewById(R.id.level45);
		levelBt[45] = (Button) findViewById(R.id.level46);
		levelBt[46] = (Button) findViewById(R.id.level47);
		levelBt[47] = (Button) findViewById(R.id.level48);
		levelBt[48] = (Button) findViewById(R.id.level49);
		levelBt[49] = (Button) findViewById(R.id.level50);
		levelBt[50] = (Button) findViewById(R.id.level51);
		levelBt[51] = (Button) findViewById(R.id.level52);
		levelBt[52] = (Button) findViewById(R.id.level53);
		levelBt[53] = (Button) findViewById(R.id.level54);
		levelBt[54] = (Button) findViewById(R.id.level55);
		levelBt[55] = (Button) findViewById(R.id.level56);
		levelBt[56] = (Button) findViewById(R.id.level57);
		levelBt[57] = (Button) findViewById(R.id.level58);
		levelBt[58] = (Button) findViewById(R.id.level59);
		levelBt[59] = (Button) findViewById(R.id.level60);
		levelBt[60] = (Button) findViewById(R.id.level61);
		levelBt[61] = (Button) findViewById(R.id.level62);
		levelBt[62] = (Button) findViewById(R.id.level63);
		levelBt[63] = (Button) findViewById(R.id.level64);
		levelBt[64] = (Button) findViewById(R.id.level65);
		levelBt[65] = (Button) findViewById(R.id.level66);
		levelBt[66] = (Button) findViewById(R.id.level67);
		levelBt[67] = (Button) findViewById(R.id.level68);
		levelBt[68] = (Button) findViewById(R.id.level69);
		levelBt[69] = (Button) findViewById(R.id.level70);
		levelBt[70] = (Button) findViewById(R.id.level71);
		levelBt[71] = (Button) findViewById(R.id.level72);
		levelBt[72] = (Button) findViewById(R.id.level73);
		levelBt[73] = (Button) findViewById(R.id.level74);
		levelBt[74] = (Button) findViewById(R.id.level75);
		levelBt[75] = (Button) findViewById(R.id.level76);
		levelBt[76] = (Button) findViewById(R.id.level77);
		levelBt[77] = (Button) findViewById(R.id.level78);
		levelBt[78] = (Button) findViewById(R.id.level79);
		levelBt[79] = (Button) findViewById(R.id.level80);
		levelBt[80] = (Button) findViewById(R.id.level81);
		levelBt[81] = (Button) findViewById(R.id.level82);
		levelBt[82] = (Button) findViewById(R.id.level83);
		levelBt[83] = (Button) findViewById(R.id.level84);
		levelBt[84] = (Button) findViewById(R.id.level85);
		levelBt[85] = (Button) findViewById(R.id.level86);
		levelBt[86] = (Button) findViewById(R.id.level87);
		levelBt[87] = (Button) findViewById(R.id.level88);
		levelBt[88] = (Button) findViewById(R.id.level89);
		levelBt[89] = (Button) findViewById(R.id.level90);
		levelBt[90] = (Button) findViewById(R.id.level91);
		levelBt[91] = (Button) findViewById(R.id.level92);
		levelBt[92] = (Button) findViewById(R.id.level93);
		levelBt[93] = (Button) findViewById(R.id.level94);
		levelBt[94] = (Button) findViewById(R.id.level95);
		levelBt[95] = (Button) findViewById(R.id.level96);
		levelBt[96] = (Button) findViewById(R.id.level97);
		levelBt[97] = (Button) findViewById(R.id.level98);
		levelBt[98] = (Button) findViewById(R.id.level99);
		levelBt[99] = (Button) findViewById(R.id.level100);
		levelBt[100] = (Button) findViewById(R.id.level101);
		levelBt[101] = (Button) findViewById(R.id.level102);
		levelBt[102] = (Button) findViewById(R.id.level103);
		levelBt[103] = (Button) findViewById(R.id.level104);
		levelBt[104] = (Button) findViewById(R.id.level105);
		levelBt[105] = (Button) findViewById(R.id.level106);
		levelBt[106] = (Button) findViewById(R.id.level107);
		levelBt[107] = (Button) findViewById(R.id.level108);
	}

	// 设置按钮图片
	private void setBtnBackgroud() {
		// 加载第一界面图片
		if (levelMark[0]) {
			if (getSpliter() == 0) {
				levelBt[0].setBackgroundResource(R.drawable.shenhua2);
			} else {
				levelBt[0].setBackgroundResource(R.drawable.shenhua1);
			}
		} else {
			levelBt[0].setBackgroundResource(R.drawable.shenhua3);
		}

		if (levelMark[1]) {
			if (getSpliter() == 1) {
				levelBt[1].setBackgroundResource(R.drawable.yanhuang2);
			} else {
				levelBt[1].setBackgroundResource(R.drawable.yanhuang1);
			}
		} else {
			levelBt[1].setBackgroundResource(R.drawable.yanhuang3);
		}

		if (levelMark[2]) {
			if (getSpliter() == 2) {
				levelBt[2].setBackgroundResource(R.drawable.xia2);
			} else {
				levelBt[2].setBackgroundResource(R.drawable.xia1);
			}
		} else {
			levelBt[2].setBackgroundResource(R.drawable.xia3);
		}

		if (levelMark[3]) {
			if (getSpliter() == 3) {
				levelBt[3].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[3].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[3].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[4]) {
			if (getSpliter() == 4) {
				levelBt[4].setBackgroundResource(R.drawable.zhou2);
			} else {
				levelBt[4].setBackgroundResource(R.drawable.zhou1);
			}
		} else {
			levelBt[4].setBackgroundResource(R.drawable.zhou3);
		}

		if (levelMark[5]) {
			if (getSpliter() == 5) {
				levelBt[5].setBackgroundResource(R.drawable.qin2);
			} else {
				levelBt[5].setBackgroundResource(R.drawable.qin1);
			}
		} else {
			levelBt[5].setBackgroundResource(R.drawable.qin3);
		}

		if (levelMark[6]) {
			if (getSpliter() == 6) {
				levelBt[6].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[6].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[6].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[7]) {
			if (getSpliter() == 7) {
				levelBt[7].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[7].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[7].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[8]) {
			if (getSpliter() == 8) {
				levelBt[8].setBackgroundResource(R.drawable.jin2);
			} else {
				levelBt[8].setBackgroundResource(R.drawable.jin1);
			}
		} else {
			levelBt[8].setBackgroundResource(R.drawable.jin3);
		}

		if (levelMark[9]) {
			if (getSpliter() == 9) {
				levelBt[9].setBackgroundResource(R.drawable.nanbeichao2);
			} else {
				levelBt[9].setBackgroundResource(R.drawable.nanbeichao1);
			}
		} else {
			levelBt[9].setBackgroundResource(R.drawable.nanbeichao3);
		}

		if (levelMark[10]) {
			if (getSpliter() == 10) {
				levelBt[10].setBackgroundResource(R.drawable.sui2);
			} else {
				levelBt[10].setBackgroundResource(R.drawable.sui1);
			}
		} else {
			levelBt[10].setBackgroundResource(R.drawable.sui3);
		}

		if (levelMark[11]) {
			if (getSpliter() == 11) {
				levelBt[11].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[11].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[11].setBackgroundResource(R.drawable.tang3);
		}

		// 加载第二界面图片
		if (levelMark[12]) {
			if (getSpliter() == 12) {
				levelBt[12].setBackgroundResource(R.drawable.shenhua2);
			} else {
				levelBt[12].setBackgroundResource(R.drawable.shenhua1);
			}
		} else {
			levelBt[12].setBackgroundResource(R.drawable.shenhua3);
		}

		if (levelMark[13]) {
			if (getSpliter() == 13) {
				levelBt[13].setBackgroundResource(R.drawable.zhou2);
			} else {
				levelBt[13].setBackgroundResource(R.drawable.zhou1);
			}
		} else {
			levelBt[13].setBackgroundResource(R.drawable.zhou3);
		}

		if (levelMark[14]) {
			if (getSpliter() == 14) {
				levelBt[14].setBackgroundResource(R.drawable.qin2);
			} else {
				levelBt[14].setBackgroundResource(R.drawable.qin1);
			}
		} else {
			levelBt[14].setBackgroundResource(R.drawable.qin3);
		}

		if (levelMark[15]) {
			if (getSpliter() == 15) {
				levelBt[15].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[15].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[15].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[16]) {
			if (getSpliter() == 16) {
				levelBt[16].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[16].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[16].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[17]) {
			if (getSpliter() == 17) {
				levelBt[17].setBackgroundResource(R.drawable.jin2);
			} else {
				levelBt[17].setBackgroundResource(R.drawable.jin1);
			}
		} else {
			levelBt[17].setBackgroundResource(R.drawable.jin3);
		}

		if (levelMark[18]) {
			if (getSpliter() == 18) {
				levelBt[18].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[18].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[18].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[19]) {
			if (getSpliter() == 19) {
				levelBt[19].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[19].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[19].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[20]) {
			if (getSpliter() == 20) {
				levelBt[20].setBackgroundResource(R.drawable.wudaishiguo2);
			} else {
				levelBt[20].setBackgroundResource(R.drawable.wudaishiguo1);
			}
		} else {
			levelBt[20].setBackgroundResource(R.drawable.wudaishiguo3);
		}

		if (levelMark[21]) {
			if (getSpliter() == 21) {
				levelBt[21].setBackgroundResource(R.drawable.song2);
			} else {
				levelBt[21].setBackgroundResource(R.drawable.song1);
			}
		} else {
			levelBt[21].setBackgroundResource(R.drawable.song3);
		}

		if (levelMark[22]) {
			if (getSpliter() == 22) {
				levelBt[22].setBackgroundResource(R.drawable.yuan2);
			} else {
				levelBt[22].setBackgroundResource(R.drawable.yuan1);
			}
		} else {
			levelBt[22].setBackgroundResource(R.drawable.yuan3);
		}

		if (levelMark[23]) {
			if (getSpliter() == 23) {
				levelBt[23].setBackgroundResource(R.drawable.ming2);
			} else {
				levelBt[23].setBackgroundResource(R.drawable.ming1);
			}
		} else {
			levelBt[23].setBackgroundResource(R.drawable.ming3);
		}

		// 加载第三界面图片
		if (levelMark[24]) {
			if (getSpliter() == 24) {
				levelBt[24].setBackgroundResource(R.drawable.zhou2);
			} else {
				levelBt[24].setBackgroundResource(R.drawable.zhou1);
			}
		} else {
			levelBt[24].setBackgroundResource(R.drawable.zhou3);
		}

		if (levelMark[25]) {
			if (getSpliter() == 25) {
				levelBt[25].setBackgroundResource(R.drawable.qin2);
			} else {
				levelBt[25].setBackgroundResource(R.drawable.qin1);
			}
		} else {
			levelBt[25].setBackgroundResource(R.drawable.qin3);
		}

		if (levelMark[26]) {
			if (getSpliter() == 26) {
				levelBt[26].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[26].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[26].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[27]) {
			if (getSpliter() == 27) {
				levelBt[27].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[27].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[27].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[28]) {
			if (getSpliter() == 28) {
				levelBt[28].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[28].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[28].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[29]) {
			if (getSpliter() == 29) {
				levelBt[29].setBackgroundResource(R.drawable.sui2);
			} else {
				levelBt[29].setBackgroundResource(R.drawable.sui1);
			}
		} else {
			levelBt[29].setBackgroundResource(R.drawable.sui3);
		}

		if (levelMark[30]) {
			if (getSpliter() == 30) {
				levelBt[30].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[30].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[30].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[31]) {
			if (getSpliter() == 31) {
				levelBt[31].setBackgroundResource(R.drawable.wudaishiguo2);
			} else {
				levelBt[31].setBackgroundResource(R.drawable.wudaishiguo1);
			}
		} else {
			levelBt[31].setBackgroundResource(R.drawable.wudaishiguo3);
		}

		if (levelMark[32]) {
			if (getSpliter() == 32) {
				levelBt[32].setBackgroundResource(R.drawable.song2);
			} else {
				levelBt[32].setBackgroundResource(R.drawable.song1);
			}
		} else {
			levelBt[32].setBackgroundResource(R.drawable.song3);
		}

		if (levelMark[33]) {
			if (getSpliter() == 33) {
				levelBt[33].setBackgroundResource(R.drawable.yuan2);
			} else {
				levelBt[33].setBackgroundResource(R.drawable.yuan1);
			}
		} else {
			levelBt[33].setBackgroundResource(R.drawable.yuan3);
		}

		if (levelMark[34]) {
			if (getSpliter() == 34) {
				levelBt[34].setBackgroundResource(R.drawable.ming2);
			} else {
				levelBt[34].setBackgroundResource(R.drawable.ming1);
			}
		} else {
			levelBt[34].setBackgroundResource(R.drawable.ming3);
		}

		if (levelMark[35]) {
			if (getSpliter() == 35) {
				levelBt[35].setBackgroundResource(R.drawable.qing2);
			} else {
				levelBt[35].setBackgroundResource(R.drawable.qing1);
			}
		} else {
			levelBt[35].setBackgroundResource(R.drawable.qing3);
		}

		// 加载第四界面图片
		if (levelMark[36]) {
			if (getSpliter() == 36) {
				levelBt[36].setBackgroundResource(R.drawable.zhou2);
			} else {
				levelBt[36].setBackgroundResource(R.drawable.zhou1);
			}
		} else {
			levelBt[36].setBackgroundResource(R.drawable.zhou3);
		}

		if (levelMark[37]) {
			if (getSpliter() == 37) {
				levelBt[37].setBackgroundResource(R.drawable.qin2);
			} else {
				levelBt[37].setBackgroundResource(R.drawable.qin1);
			}
		} else {
			levelBt[37].setBackgroundResource(R.drawable.qin3);
		}

		if (levelMark[38]) {
			if (getSpliter() == 38) {
				levelBt[38].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[38].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[38].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[39]) {
			if (getSpliter() == 39) {
				levelBt[39].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[39].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[39].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[40]) {
			if (getSpliter() == 40) {
				levelBt[40].setBackgroundResource(R.drawable.jin2);
			} else {
				levelBt[40].setBackgroundResource(R.drawable.jin1);
			}
		} else {
			levelBt[40].setBackgroundResource(R.drawable.jin3);
		}

		if (levelMark[41]) {
			if (getSpliter() == 41) {
				levelBt[41].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[41].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[41].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[42]) {
			if (getSpliter() == 42) {
				levelBt[42].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[42].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[42].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[43]) {
			if (getSpliter() == 43) {
				levelBt[43].setBackgroundResource(R.drawable.wudaishiguo2);
			} else {
				levelBt[43].setBackgroundResource(R.drawable.wudaishiguo1);
			}
		} else {
			levelBt[43].setBackgroundResource(R.drawable.wudaishiguo3);
		}

		if (levelMark[44]) {
			if (getSpliter() == 44) {
				levelBt[44].setBackgroundResource(R.drawable.song2);
			} else {
				levelBt[44].setBackgroundResource(R.drawable.song1);
			}
		} else {
			levelBt[44].setBackgroundResource(R.drawable.song3);
		}

		if (levelMark[45]) {
			if (getSpliter() == 45) {
				levelBt[45].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[45].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[45].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[46]) {
			if (getSpliter() == 46) {
				levelBt[46].setBackgroundResource(R.drawable.ming2);
			} else {
				levelBt[46].setBackgroundResource(R.drawable.ming1);
			}
		} else {
			levelBt[46].setBackgroundResource(R.drawable.ming3);
		}

		if (levelMark[47]) {
			if (getSpliter() == 47) {
				levelBt[47].setBackgroundResource(R.drawable.qing2);
			} else {
				levelBt[47].setBackgroundResource(R.drawable.qing1);
			}
		} else {
			levelBt[47].setBackgroundResource(R.drawable.qing3);
		}

		// 加载第五界面图片
		if (levelMark[48]) {
			if (getSpliter() == 48) {
				levelBt[48].setBackgroundResource(R.drawable.zhou2);
			} else {
				levelBt[48].setBackgroundResource(R.drawable.zhou1);
			}
		} else {
			levelBt[48].setBackgroundResource(R.drawable.zhou3);
		}

		if (levelMark[49]) {
			if (getSpliter() == 49) {
				levelBt[49].setBackgroundResource(R.drawable.qin2);
			} else {
				levelBt[49].setBackgroundResource(R.drawable.qin1);
			}
		} else {
			levelBt[49].setBackgroundResource(R.drawable.qin3);
		}

		if (levelMark[50]) {
			if (getSpliter() == 50) {
				levelBt[50].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[50].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[50].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[51]) {
			if (getSpliter() == 51) {
				levelBt[51].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[51].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[51].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[52]) {
			if (getSpliter() == 52) {
				levelBt[52].setBackgroundResource(R.drawable.jin2);
			} else {
				levelBt[52].setBackgroundResource(R.drawable.jin1);
			}
		} else {
			levelBt[52].setBackgroundResource(R.drawable.jin3);
		}

		if (levelMark[53]) {
			if (getSpliter() == 53) {
				levelBt[53].setBackgroundResource(R.drawable.sui2);
			} else {
				levelBt[53].setBackgroundResource(R.drawable.sui1);
			}
		} else {
			levelBt[53].setBackgroundResource(R.drawable.sui3);
		}

		if (levelMark[54]) {
			if (getSpliter() == 54) {
				levelBt[54].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[54].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[54].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[55]) {
			if (getSpliter() == 55) {
				levelBt[55].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[55].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[55].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[56]) {
			if (getSpliter() == 56) {
				levelBt[56].setBackgroundResource(R.drawable.song2);
			} else {
				levelBt[56].setBackgroundResource(R.drawable.song1);
			}
		} else {
			levelBt[56].setBackgroundResource(R.drawable.song3);
		}

		if (levelMark[57]) {
			if (getSpliter() == 57) {
				levelBt[57].setBackgroundResource(R.drawable.yuan2);
			} else {
				levelBt[57].setBackgroundResource(R.drawable.yuan1);
			}
		} else {
			levelBt[57].setBackgroundResource(R.drawable.yuan3);
		}

		if (levelMark[58]) {
			if (getSpliter() == 58) {
				levelBt[58].setBackgroundResource(R.drawable.ming2);
			} else {
				levelBt[58].setBackgroundResource(R.drawable.ming1);
			}
		} else {
			levelBt[58].setBackgroundResource(R.drawable.ming3);
		}

		if (levelMark[59]) {
			if (getSpliter() == 59) {
				levelBt[59].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[59].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[59].setBackgroundResource(R.drawable.tang3);
		}

		// 加载第六界面图片
		if (levelMark[60]) {
			if (getSpliter() == 60) {
				levelBt[60].setBackgroundResource(R.drawable.zhou2);
			} else {
				levelBt[60].setBackgroundResource(R.drawable.zhou1);
			}
		} else {
			levelBt[60].setBackgroundResource(R.drawable.zhou3);
		}

		if (levelMark[61]) {
			if (getSpliter() == 61) {
				levelBt[61].setBackgroundResource(R.drawable.qin2);
			} else {
				levelBt[61].setBackgroundResource(R.drawable.qin1);
			}
		} else {
			levelBt[61].setBackgroundResource(R.drawable.qin3);
		}

		if (levelMark[62]) {
			if (getSpliter() == 62) {
				levelBt[62].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[62].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[62].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[63]) {
			if (getSpliter() == 63) {
				levelBt[63].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[63].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[63].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[64]) {
			if (getSpliter() == 64) {
				levelBt[64].setBackgroundResource(R.drawable.jin2);
			} else {
				levelBt[64].setBackgroundResource(R.drawable.jin1);
			}
		} else {
			levelBt[64].setBackgroundResource(R.drawable.jin3);
		}

		if (levelMark[65]) {
			if (getSpliter() == 65) {
				levelBt[65].setBackgroundResource(R.drawable.sui2);
			} else {
				levelBt[65].setBackgroundResource(R.drawable.sui1);
			}
		} else {
			levelBt[65].setBackgroundResource(R.drawable.sui3);
		}

		if (levelMark[66]) {
			if (getSpliter() == 66) {
				levelBt[66].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[66].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[66].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[67]) {
			if (getSpliter() == 67) {
				levelBt[67].setBackgroundResource(R.drawable.wudaishiguo2);
			} else {
				levelBt[67].setBackgroundResource(R.drawable.wudaishiguo1);
			}
		} else {
			levelBt[67].setBackgroundResource(R.drawable.wudaishiguo3);
		}

		if (levelMark[68]) {
			if (getSpliter() == 68) {
				levelBt[68].setBackgroundResource(R.drawable.song2);
			} else {
				levelBt[68].setBackgroundResource(R.drawable.song1);
			}
		} else {
			levelBt[68].setBackgroundResource(R.drawable.song3);
		}

		if (levelMark[69]) {
			if (getSpliter() == 69) {
				levelBt[69].setBackgroundResource(R.drawable.ming2);
			} else {
				levelBt[69].setBackgroundResource(R.drawable.ming1);
			}
		} else {
			levelBt[69].setBackgroundResource(R.drawable.ming3);
		}

		if (levelMark[70]) {
			if (getSpliter() == 70) {
				levelBt[70].setBackgroundResource(R.drawable.qin2);
			} else {
				levelBt[70].setBackgroundResource(R.drawable.qin1);
			}
		} else {
			levelBt[70].setBackgroundResource(R.drawable.qin3);
		}

		if (levelMark[71]) {
			if (getSpliter() == 71) {
				levelBt[71].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[71].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[71].setBackgroundResource(R.drawable.han3);
		}

		// 加载第七界面图片
		if (levelMark[72]) {
			if (getSpliter() == 72) {
				levelBt[72].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[72].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[72].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[73]) {
			if (getSpliter() == 73) {
				levelBt[73].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[73].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[73].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[74]) {
			if (getSpliter() == 74) {
				levelBt[74].setBackgroundResource(R.drawable.jin2);
			} else {
				levelBt[74].setBackgroundResource(R.drawable.jin1);
			}
		} else {
			levelBt[74].setBackgroundResource(R.drawable.jin3);
		}

		if (levelMark[75]) {
			if (getSpliter() == 75) {
				levelBt[75].setBackgroundResource(R.drawable.sui2);
			} else {
				levelBt[75].setBackgroundResource(R.drawable.sui1);
			}
		} else {
			levelBt[75].setBackgroundResource(R.drawable.sui3);
		}

		if (levelMark[76]) {
			if (getSpliter() == 76) {
				levelBt[76].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[76].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[76].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[77]) {
			if (getSpliter() == 77) {
				levelBt[77].setBackgroundResource(R.drawable.wudaishiguo2);
			} else {
				levelBt[77].setBackgroundResource(R.drawable.wudaishiguo1);
			}
		} else {
			levelBt[77].setBackgroundResource(R.drawable.wudaishiguo3);
		}

		if (levelMark[78]) {
			if (getSpliter() == 78) {
				levelBt[78].setBackgroundResource(R.drawable.song2);
			} else {
				levelBt[78].setBackgroundResource(R.drawable.song1);
			}
		} else {
			levelBt[78].setBackgroundResource(R.drawable.song3);
		}

		if (levelMark[79]) {
			if (getSpliter() == 79) {
				levelBt[79].setBackgroundResource(R.drawable.qing2);
			} else {
				levelBt[79].setBackgroundResource(R.drawable.qing1);
			}
		} else {
			levelBt[79].setBackgroundResource(R.drawable.qing3);
		}

		if (levelMark[80]) {
			if (getSpliter() == 80) {
				levelBt[80].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[80].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[80].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[81]) {
			if (getSpliter() == 81) {
				levelBt[81].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[81].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[81].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[82]) {
			if (getSpliter() == 82) {
				levelBt[82].setBackgroundResource(R.drawable.wudaishiguo2);
			} else {
				levelBt[82].setBackgroundResource(R.drawable.wudaishiguo1);
			}
		} else {
			levelBt[82].setBackgroundResource(R.drawable.wudaishiguo3);
		}

		if (levelMark[83]) {
			if (getSpliter() == 83) {
				levelBt[83].setBackgroundResource(R.drawable.song2);
			} else {
				levelBt[83].setBackgroundResource(R.drawable.song1);
			}
		} else {
			levelBt[83].setBackgroundResource(R.drawable.song3);
		}

		// 加载第八界面图片
		if (levelMark[84]) {
			if (getSpliter() == 84) {
				levelBt[84].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[84].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[84].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[85]) {
			if (getSpliter() == 85) {
				levelBt[85].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[85].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[85].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[86]) {
			if (getSpliter() == 86) {
				levelBt[86].setBackgroundResource(R.drawable.jin2);
			} else {
				levelBt[86].setBackgroundResource(R.drawable.jin1);
			}
		} else {
			levelBt[86].setBackgroundResource(R.drawable.jin3);
		}

		if (levelMark[87]) {
			if (getSpliter() == 87) {
				levelBt[87].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[87].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[87].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[88]) {
			if (getSpliter() == 88) {
				levelBt[88].setBackgroundResource(R.drawable.wudaishiguo2);
			} else {
				levelBt[88].setBackgroundResource(R.drawable.wudaishiguo1);
			}
		} else {
			levelBt[88].setBackgroundResource(R.drawable.wudaishiguo3);
		}

		if (levelMark[89]) {
			if (getSpliter() == 89) {
				levelBt[89].setBackgroundResource(R.drawable.ming2);
			} else {
				levelBt[89].setBackgroundResource(R.drawable.ming1);
			}
		} else {
			levelBt[89].setBackgroundResource(R.drawable.ming3);
		}

		if (levelMark[90]) {
			if (getSpliter() == 90) {
				levelBt[90].setBackgroundResource(R.drawable.yuan2);
			} else {
				levelBt[90].setBackgroundResource(R.drawable.yuan1);
			}
		} else {
			levelBt[90].setBackgroundResource(R.drawable.yuan3);
		}

		if (levelMark[91]) {
			if (getSpliter() == 91) {
				levelBt[91].setBackgroundResource(R.drawable.ming2);
			} else {
				levelBt[91].setBackgroundResource(R.drawable.ming1);
			}
		} else {
			levelBt[91].setBackgroundResource(R.drawable.ming3);
		}

		if (levelMark[92]) {
			if (getSpliter() == 92) {
				levelBt[92].setBackgroundResource(R.drawable.nanbeichao2);
			} else {
				levelBt[92].setBackgroundResource(R.drawable.nanbeichao1);
			}
		} else {
			levelBt[92].setBackgroundResource(R.drawable.nanbeichao3);
		}

		if (levelMark[93]) {
			if (getSpliter() == 93) {
				levelBt[93].setBackgroundResource(R.drawable.song2);
			} else {
				levelBt[93].setBackgroundResource(R.drawable.song1);
			}
		} else {
			levelBt[93].setBackgroundResource(R.drawable.song3);
		}

		if (levelMark[94]) {
			if (getSpliter() == 94) {
				levelBt[94].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[94].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[94].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[95]) {
			if (getSpliter() == 95) {
				levelBt[95].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[95].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[95].setBackgroundResource(R.drawable.sanguo3);
		}

		// 第九界面
		if (levelMark[96]) {
			if (getSpliter() == 96) {
				levelBt[96].setBackgroundResource(R.drawable.han2);
			} else {
				levelBt[96].setBackgroundResource(R.drawable.han1);
			}
		} else {
			levelBt[96].setBackgroundResource(R.drawable.han3);
		}

		if (levelMark[97]) {
			if (getSpliter() == 97) {
				levelBt[97].setBackgroundResource(R.drawable.sanguo2);
			} else {
				levelBt[97].setBackgroundResource(R.drawable.sanguo1);
			}
		} else {
			levelBt[97].setBackgroundResource(R.drawable.sanguo3);
		}

		if (levelMark[98]) {
			if (getSpliter() == 98) {
				levelBt[98].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[98].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[98].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[99]) {
			if (getSpliter() == 99) {
				levelBt[99].setBackgroundResource(R.drawable.ming2);
			} else {
				levelBt[99].setBackgroundResource(R.drawable.ming1);
			}
		} else {
			levelBt[99].setBackgroundResource(R.drawable.ming3);
		}

		if (levelMark[100]) {
			if (getSpliter() == 100) {
				levelBt[100].setBackgroundResource(R.drawable.shang2);
			} else {
				levelBt[100].setBackgroundResource(R.drawable.shang1);
			}
		} else {
			levelBt[100].setBackgroundResource(R.drawable.shang3);
		}

		if (levelMark[101]) {
			if (getSpliter() == 101) {
				levelBt[101].setBackgroundResource(R.drawable.jin2);
			} else {
				levelBt[101].setBackgroundResource(R.drawable.jin1);
			}
		} else {
			levelBt[101].setBackgroundResource(R.drawable.jin3);
		}

		if (levelMark[102]) {
			if (getSpliter() == 102) {
				levelBt[102].setBackgroundResource(R.drawable.sui2);
			} else {
				levelBt[102].setBackgroundResource(R.drawable.sui1);
			}
		} else {
			levelBt[102].setBackgroundResource(R.drawable.sui3);
		}

		if (levelMark[103]) {
			if (getSpliter() == 103) {
				levelBt[103].setBackgroundResource(R.drawable.wudaishiguo2);
			} else {
				levelBt[103].setBackgroundResource(R.drawable.wudaishiguo1);
			}
		} else {
			levelBt[103].setBackgroundResource(R.drawable.wudaishiguo3);
		}

		if (levelMark[104]) {
			if (getSpliter() == 104) {
				levelBt[104].setBackgroundResource(R.drawable.ming2);
			} else {
				levelBt[104].setBackgroundResource(R.drawable.ming1);
			}
		} else {
			levelBt[104].setBackgroundResource(R.drawable.ming3);
		}

		if (levelMark[105]) {
			if (getSpliter() == 105) {
				levelBt[105].setBackgroundResource(R.drawable.sui2);
			} else {
				levelBt[105].setBackgroundResource(R.drawable.sui1);
			}
		} else {
			levelBt[105].setBackgroundResource(R.drawable.sui3);
		}

		if (levelMark[106]) {
			if (getSpliter() == 106) {
				levelBt[106].setBackgroundResource(R.drawable.tang2);
			} else {
				levelBt[106].setBackgroundResource(R.drawable.tang1);
			}
		} else {
			levelBt[106].setBackgroundResource(R.drawable.tang3);
		}

		if (levelMark[107]) {
			if (getSpliter() == 107) {
				levelBt[107].setBackgroundResource(R.drawable.yuan2);
			} else {
				levelBt[107].setBackgroundResource(R.drawable.yuan1);
			}
		} else {
			levelBt[107].setBackgroundResource(R.drawable.yuan3);
		}
	}

	// 设置布局
	private void setMyLayout() {
		RelativeLayout.LayoutParams levelBtlp = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp.leftMargin = screenWidth / 10;
		levelBtlp.topMargin = screenHeight / 10;
		levelBt[0].setLayoutParams(levelBtlp);

		RelativeLayout.LayoutParams levelBtlp1 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp1.leftMargin = screenWidth / 4;
		levelBtlp1.topMargin = screenHeight / 4;
		levelBt[1].setLayoutParams(levelBtlp1);

		RelativeLayout.LayoutParams levelBtlp2 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp2.leftMargin = screenWidth / 5;
		levelBtlp2.topMargin = screenHeight / 2;
		levelBt[2].setLayoutParams(levelBtlp2);

		RelativeLayout.LayoutParams levelBtlp3 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp3.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp3.leftMargin = screenWidth / 12;
		levelBtlp3.topMargin = screenHeight * 2 / 3;
		levelBt[3].setLayoutParams(levelBtlp3);

		RelativeLayout.LayoutParams levelBtlp4 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp4.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp4.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp4.leftMargin = screenWidth / 30;
		levelBtlp4.topMargin = screenHeight / 3;
		levelBt[4].setLayoutParams(levelBtlp4);

		RelativeLayout.LayoutParams levelBtlp5 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp5.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp5.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp5.leftMargin = screenWidth * 2 / 5;
		levelBtlp5.topMargin = screenHeight * 4 / 5;
		levelBt[5].setLayoutParams(levelBtlp5);

		RelativeLayout.LayoutParams levelBtlp6 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp6.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp6.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp6.leftMargin = screenWidth * 2 / 3;
		levelBtlp6.topMargin = screenHeight / 2;
		levelBt[6].setLayoutParams(levelBtlp6);

		RelativeLayout.LayoutParams levelBtlp7 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp7.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp7.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp7.leftMargin = screenWidth * 4 / 5;
		levelBtlp7.topMargin = screenHeight / 3;
		levelBt[7].setLayoutParams(levelBtlp7);

		RelativeLayout.LayoutParams levelBtlp8 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp8.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp8.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp8.leftMargin = screenWidth / 2;
		levelBtlp8.topMargin = screenHeight / 2;
		levelBt[8].setLayoutParams(levelBtlp8);

		RelativeLayout.LayoutParams levelBtlp9 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp9.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp9.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp9.leftMargin = screenWidth * 2 / 3;
		levelBtlp9.topMargin = screenHeight / 15;
		levelBt[9].setLayoutParams(levelBtlp9);

		RelativeLayout.LayoutParams levelBtlp10 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp10.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp10.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp10.leftMargin = screenWidth * 2 / 5;
		levelBtlp10.topMargin = screenHeight / 3;
		levelBt[10].setLayoutParams(levelBtlp10);

		RelativeLayout.LayoutParams levelBtlp11 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp11.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp11.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp11.leftMargin = screenWidth * 11 / 15;
		levelBtlp11.topMargin = screenHeight * 4 / 5;
		levelBt[11].setLayoutParams(levelBtlp11);

		// 第二界面图片布局
		RelativeLayout.LayoutParams levelBtlp12 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp12.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp12.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp12.leftMargin = screenWidth / 5;
		levelBtlp12.topMargin = screenHeight / 3;
		levelBt[12].setLayoutParams(levelBtlp12);

		RelativeLayout.LayoutParams levelBtlp13 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp13.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp13.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp13.leftMargin = screenWidth / 4;
		levelBtlp13.topMargin = screenHeight * 2 / 3;
		levelBt[13].setLayoutParams(levelBtlp13);

		RelativeLayout.LayoutParams levelBtlp14 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp14.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp14.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp14.leftMargin = screenWidth / 30;
		levelBtlp14.topMargin = screenHeight *  3 / 5;
		levelBt[14].setLayoutParams(levelBtlp14);

		RelativeLayout.LayoutParams levelBtlp15 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp15.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp15.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp15.leftMargin = screenWidth * 2 / 3;
		levelBtlp15.topMargin = screenHeight * 3 / 5;
		levelBt[15].setLayoutParams(levelBtlp15);

		RelativeLayout.LayoutParams levelBtlp16 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp16.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp16.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp16.leftMargin = screenWidth * 1 / 2;
		levelBtlp16.topMargin = screenHeight * 4 / 5;
		levelBt[16].setLayoutParams(levelBtlp16);

		RelativeLayout.LayoutParams levelBtlp17 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp17.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp17.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp17.leftMargin = screenWidth * 4 / 5;
		levelBtlp17.topMargin = screenHeight / 3;
		levelBt[17].setLayoutParams(levelBtlp17);

		RelativeLayout.LayoutParams levelBtlp18 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp18.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp18.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp18.leftMargin = screenWidth / 2;
		levelBtlp18.topMargin = screenHeight / 2;
		levelBt[18].setLayoutParams(levelBtlp18);

		RelativeLayout.LayoutParams levelBtlp19 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp19.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp19.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp19.leftMargin = screenWidth * 2 / 3;
		levelBtlp19.topMargin = screenHeight / 3;
		levelBt[19].setLayoutParams(levelBtlp19);

		RelativeLayout.LayoutParams levelBtlp20 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp20.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp20.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp20.leftMargin = screenWidth / 20;
		levelBtlp20.topMargin = screenHeight / 15;
		levelBt[20].setLayoutParams(levelBtlp20);

		RelativeLayout.LayoutParams levelBtlp21 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp21.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp21.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp21.leftMargin = screenWidth * 2 / 5;
		levelBtlp21.topMargin = screenHeight / 5;
		levelBt[21].setLayoutParams(levelBtlp21);

		RelativeLayout.LayoutParams levelBtlp22 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp22.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp22.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp22.leftMargin = screenWidth * 2 / 3;
		levelBtlp22.topMargin = screenHeight / 25;
		levelBt[22].setLayoutParams(levelBtlp22);

		RelativeLayout.LayoutParams levelBtlp23 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp23.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp23.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp23.leftMargin = screenWidth * 4 / 5;
		levelBtlp23.topMargin = screenHeight * 4 / 5;
		levelBt[23].setLayoutParams(levelBtlp23);

		// 第三界面图片布局(OK)
		RelativeLayout.LayoutParams levelBtlp24 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp24.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp24.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp24.leftMargin = screenWidth / 4;
		levelBtlp24.topMargin = screenHeight / 5;
		levelBt[24].setLayoutParams(levelBtlp24);

		RelativeLayout.LayoutParams levelBtlp25 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp25.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp25.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp25.leftMargin = screenWidth / 7;
		levelBtlp25.topMargin = screenHeight * 1 / 2;
		levelBt[25].setLayoutParams(levelBtlp25);

		RelativeLayout.LayoutParams levelBtlp26 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp26.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp26.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp26.leftMargin = screenWidth / 30;
		levelBtlp26.topMargin = screenHeight * 4 / 5;
		levelBt[26].setLayoutParams(levelBtlp26);

		RelativeLayout.LayoutParams levelBtlp27 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp27.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp27.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp27.leftMargin = screenWidth * 2 / 3;
		levelBtlp27.topMargin = screenHeight * 4 / 5;
		levelBt[27].setLayoutParams(levelBtlp27);

		RelativeLayout.LayoutParams levelBtlp28 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp28.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp28.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp28.leftMargin = screenWidth * 3 / 8;
		levelBtlp28.topMargin = screenHeight * 4 / 5;
		levelBt[28].setLayoutParams(levelBtlp28);

		RelativeLayout.LayoutParams levelBtlp29 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp29.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp29.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp29.leftMargin = screenWidth * 4 / 5;
		levelBtlp29.topMargin = screenHeight / 4;
		levelBt[29].setLayoutParams(levelBtlp29);

		RelativeLayout.LayoutParams levelBtlp30 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp30.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp30.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp30.leftMargin = screenWidth / 2;
		levelBtlp30.topMargin = screenHeight / 2;
		levelBt[30].setLayoutParams(levelBtlp30);

		RelativeLayout.LayoutParams levelBtlp31 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp31.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp31.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp31.leftMargin = screenWidth * 2 / 3;
		levelBtlp31.topMargin = screenHeight / 3;
		levelBt[31].setLayoutParams(levelBtlp31);

		RelativeLayout.LayoutParams levelBtlp32 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp32.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp32.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp32.leftMargin = screenWidth / 20;
		levelBtlp32.topMargin = screenHeight / 15;
		levelBt[32].setLayoutParams(levelBtlp32);

		RelativeLayout.LayoutParams levelBtlp33 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp33.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp33.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp33.leftMargin = screenWidth * 2 / 5;
		levelBtlp33.topMargin = screenHeight / 3;
		levelBt[33].setLayoutParams(levelBtlp33);

		RelativeLayout.LayoutParams levelBtlp34 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp34.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp34.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp34.leftMargin = screenWidth * 2 / 3;
		levelBtlp34.topMargin = screenHeight / 25;
		levelBt[34].setLayoutParams(levelBtlp34);

		RelativeLayout.LayoutParams levelBtlp35 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp35.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp35.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp35.leftMargin = screenWidth * 4 / 5;
		levelBtlp35.topMargin = screenHeight * 3 / 5;
		levelBt[35].setLayoutParams(levelBtlp35);

		// 第四界面图片布局
		RelativeLayout.LayoutParams levelBtlp36 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp36.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp36.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp36.leftMargin = screenWidth / 8;
		levelBtlp36.topMargin = screenHeight / 10;
		levelBt[36].setLayoutParams(levelBtlp36);

		RelativeLayout.LayoutParams levelBtlp37 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp37.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp37.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp37.leftMargin = screenWidth * 2 / 5;
		levelBtlp37.topMargin = screenHeight / 4;
		levelBt[37].setLayoutParams(levelBtlp37);

		RelativeLayout.LayoutParams levelBtlp38 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp38.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp38.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp38.leftMargin = screenWidth / 5;
		levelBtlp38.topMargin = screenHeight / 3;
		levelBt[38].setLayoutParams(levelBtlp38);

		RelativeLayout.LayoutParams levelBtlp39 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp39.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp39.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp39.leftMargin = screenWidth / 9;
		levelBtlp39.topMargin = screenHeight * 3 / 4;
		levelBt[39].setLayoutParams(levelBtlp39);

		RelativeLayout.LayoutParams levelBtlp40 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp40.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp40.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp40.leftMargin = screenWidth / 30;
		levelBtlp40.topMargin = screenHeight / 2;
		levelBt[40].setLayoutParams(levelBtlp40);

		RelativeLayout.LayoutParams levelBtlp41 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp41.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp41.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp41.leftMargin = screenWidth * 2 / 5;
		levelBtlp41.topMargin = screenHeight * 4 / 5;
		levelBt[41].setLayoutParams(levelBtlp41);

		RelativeLayout.LayoutParams levelBtlp42 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp42.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp42.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp42.leftMargin = screenWidth * 2 / 3;
		levelBtlp42.topMargin = screenHeight / 2;
		levelBt[42].setLayoutParams(levelBtlp42);

		RelativeLayout.LayoutParams levelBtlp43 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp43.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp43.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp43.leftMargin = screenWidth * 4 / 5;
		levelBtlp43.topMargin = screenHeight / 3;
		levelBt[43].setLayoutParams(levelBtlp43);

		RelativeLayout.LayoutParams levelBtlp44 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp44.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp44.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp44.leftMargin = screenWidth / 3;
		levelBtlp44.topMargin = screenHeight / 2;
		levelBt[44].setLayoutParams(levelBtlp44);

		RelativeLayout.LayoutParams levelBtlp45 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp45.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp45.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp45.leftMargin = screenWidth * 2 / 3;
		levelBtlp45.topMargin = screenHeight / 15;
		levelBt[45].setLayoutParams(levelBtlp45);

		RelativeLayout.LayoutParams levelBtlp46 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp46.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp46.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp46.leftMargin = screenWidth * 3 / 5;
		levelBtlp46.topMargin = screenHeight / 4;
		levelBt[46].setLayoutParams(levelBtlp46);

		RelativeLayout.LayoutParams levelBtlp47 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp47.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp47.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp47.leftMargin = screenWidth * 11 / 15;
		levelBtlp47.topMargin = screenHeight * 4 / 5;
		levelBt[47].setLayoutParams(levelBtlp47);

		// 第五界面布局
		RelativeLayout.LayoutParams levelBtlp48 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp48.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp48.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp48.leftMargin = screenWidth / 3;
		levelBtlp48.topMargin = screenHeight / 10;
		levelBt[48].setLayoutParams(levelBtlp48);

		RelativeLayout.LayoutParams levelBtlp49 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp49.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp49.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp49.leftMargin = screenWidth / 4;
		levelBtlp49.topMargin = screenHeight / 4;
		levelBt[49].setLayoutParams(levelBtlp49);

		RelativeLayout.LayoutParams levelBtlp50 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp50.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp50.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp50.leftMargin = screenWidth / 4;
		levelBtlp50.topMargin = screenHeight*2 / 3;
		levelBt[50].setLayoutParams(levelBtlp50);

		RelativeLayout.LayoutParams levelBtlp51 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp51.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp51.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp51.leftMargin = screenWidth / 12;
		levelBtlp51.topMargin = screenHeight * 2 / 3;
		levelBt[51].setLayoutParams(levelBtlp51);

		RelativeLayout.LayoutParams levelBtlp52 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp52.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp52.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp52.leftMargin = screenWidth / 30;
		levelBtlp52.topMargin = screenHeight / 3;
		levelBt[52].setLayoutParams(levelBtlp52);

		RelativeLayout.LayoutParams levelBtlp53 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp53.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp53.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp53.leftMargin = screenWidth * 2 / 5;
		levelBtlp53.topMargin = screenHeight * 4 / 5;
		levelBt[53].setLayoutParams(levelBtlp53);

		RelativeLayout.LayoutParams levelBtlp54 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp54.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp54.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp54.leftMargin = screenWidth * 2 / 3;
		levelBtlp54.topMargin = screenHeight / 2;
		levelBt[54].setLayoutParams(levelBtlp54);

		RelativeLayout.LayoutParams levelBtlp55 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp55.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp55.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp55.leftMargin = screenWidth * 4 / 5;
		levelBtlp55.topMargin = screenHeight / 3;
		levelBt[55].setLayoutParams(levelBtlp55);

		RelativeLayout.LayoutParams levelBtlp56 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp56.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp56.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp56.leftMargin = screenWidth / 2;
		levelBtlp56.topMargin = screenHeight / 2;
		levelBt[56].setLayoutParams(levelBtlp56);

		RelativeLayout.LayoutParams levelBtlp57 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp57.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp57.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp57.leftMargin = screenWidth * 2 / 3;
		levelBtlp57.topMargin = screenHeight / 15;
		levelBt[57].setLayoutParams(levelBtlp57);

		RelativeLayout.LayoutParams levelBtlp58 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp58.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp58.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp58.leftMargin = screenWidth * 3 / 5;
		levelBtlp58.topMargin = screenHeight / 3;
		levelBt[58].setLayoutParams(levelBtlp58);

		RelativeLayout.LayoutParams levelBtlp59 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp59.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp59.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp59.leftMargin = screenWidth * 11 / 15;
		levelBtlp59.topMargin = screenHeight * 4 / 5;
		levelBt[59].setLayoutParams(levelBtlp59);

		// 第六界面
		RelativeLayout.LayoutParams levelBtlp60 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp60.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp60.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp60.leftMargin = screenWidth *4 / 10;
		levelBtlp60.topMargin = screenHeight *2 / 5;
		levelBt[60].setLayoutParams(levelBtlp60);

		RelativeLayout.LayoutParams levelBtlp61 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp61.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp61.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp61.leftMargin = screenWidth / 4;
		levelBtlp61.topMargin = screenHeight / 4;
		levelBt[61].setLayoutParams(levelBtlp61);

		RelativeLayout.LayoutParams levelBtlp62 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp62.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp62.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp62.leftMargin = screenWidth / 5;
		levelBtlp62.topMargin = screenHeight / 2;
		levelBt[62].setLayoutParams(levelBtlp62);

		RelativeLayout.LayoutParams levelBtlp63 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp63.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp63.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp63.leftMargin = screenWidth / 12;
		levelBtlp63.topMargin = screenHeight * 3 / 4;
		levelBt[63].setLayoutParams(levelBtlp63);

		RelativeLayout.LayoutParams levelBtlp64 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp64.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp64.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp64.leftMargin = screenWidth / 30;
		levelBtlp64.topMargin = screenHeight / 3;
		levelBt[64].setLayoutParams(levelBtlp64);

		RelativeLayout.LayoutParams levelBtlp65 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp65.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp65.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp65.leftMargin = screenWidth * 2 / 5;
		levelBtlp65.topMargin = screenHeight * 4 / 5;
		levelBt[65].setLayoutParams(levelBtlp65);

		RelativeLayout.LayoutParams levelBtlp66 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp66.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp66.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp66.leftMargin = screenWidth * 2 / 3;
		levelBtlp66.topMargin = screenHeight / 2;
		levelBt[66].setLayoutParams(levelBtlp66);

		RelativeLayout.LayoutParams levelBtlp67 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp67.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp67.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp67.leftMargin = screenWidth * 4 / 5;
		levelBtlp67.topMargin = screenHeight / 3;
		levelBt[67].setLayoutParams(levelBtlp67);

		RelativeLayout.LayoutParams levelBtlp68 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp68.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp68.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp68.leftMargin = screenWidth / 2;
		levelBtlp68.topMargin = screenHeight *3/ 5;
		levelBt[68].setLayoutParams(levelBtlp68);

		RelativeLayout.LayoutParams levelBtlp69 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp69.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp69.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp69.leftMargin = screenWidth * 2 / 3;
		levelBtlp69.topMargin = screenHeight / 15;
		levelBt[69].setLayoutParams(levelBtlp69);

		RelativeLayout.LayoutParams levelBtlp70 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp70.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp70.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp70.leftMargin = screenWidth * 2 / 5;
		levelBtlp70.topMargin = screenHeight / 9;
		levelBt[70].setLayoutParams(levelBtlp70);

		RelativeLayout.LayoutParams levelBtlp71 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp71.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp71.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp71.leftMargin = screenWidth * 11 / 15;
		levelBtlp71.topMargin = screenHeight * 4 / 5;
		levelBt[71].setLayoutParams(levelBtlp71);

		// 第七界面
		RelativeLayout.LayoutParams levelBtlp72 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp72.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp72.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp72.leftMargin = screenWidth *3/ 10;
		levelBtlp72.topMargin = screenHeight / 10;
		levelBt[72].setLayoutParams(levelBtlp72);

		RelativeLayout.LayoutParams levelBtlp73 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp73.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp73.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp73.leftMargin = screenWidth / 4;
		levelBtlp73.topMargin = screenHeight / 2;
		levelBt[73].setLayoutParams(levelBtlp73);

		RelativeLayout.LayoutParams levelBtlp74 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp74.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp74.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp74.leftMargin = screenWidth *7/ 10;
		levelBtlp74.topMargin = screenHeight / 2;
		levelBt[74].setLayoutParams(levelBtlp74);

		RelativeLayout.LayoutParams levelBtlp75 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp75.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp75.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp75.leftMargin = screenWidth / 10;
		levelBtlp75.topMargin = screenHeight * 4 / 5;
		levelBt[75].setLayoutParams(levelBtlp75);

		RelativeLayout.LayoutParams levelBtlp76 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp76.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp76.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp76.leftMargin = screenWidth / 30;
		levelBtlp76.topMargin = screenHeight / 3;
		levelBt[76].setLayoutParams(levelBtlp76);

		RelativeLayout.LayoutParams levelBtlp77 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp77.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp77.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp77.leftMargin = screenWidth * 2 / 5;
		levelBtlp77.topMargin = screenHeight * 4 / 5;
		levelBt[77].setLayoutParams(levelBtlp77);

		RelativeLayout.LayoutParams levelBtlp78 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp78.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp78.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp78.leftMargin = screenWidth * 1 / 2;
		levelBtlp78.topMargin = screenHeight / 9;
		levelBt[78].setLayoutParams(levelBtlp78);

		RelativeLayout.LayoutParams levelBtlp79 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp79.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp79.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp79.leftMargin = screenWidth * 4 / 5;
		levelBtlp79.topMargin = screenHeight / 3;
		levelBt[79].setLayoutParams(levelBtlp79);

		RelativeLayout.LayoutParams levelBtlp80 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp80.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp80.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp80.leftMargin = screenWidth / 2;
		levelBtlp80.topMargin = screenHeight / 2;
		levelBt[80].setLayoutParams(levelBtlp80);

		RelativeLayout.LayoutParams levelBtlp81 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp81.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp81.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp81.leftMargin = screenWidth * 2 / 3;
		levelBtlp81.topMargin = screenHeight / 15;
		levelBt[81].setLayoutParams(levelBtlp81);

		RelativeLayout.LayoutParams levelBtlp82 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp82.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp82.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp82.leftMargin = screenWidth * 2 / 5;
		levelBtlp82.topMargin = screenHeight / 3;
		levelBt[82].setLayoutParams(levelBtlp82);

		RelativeLayout.LayoutParams levelBtlp83 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp83.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp83.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp83.leftMargin = screenWidth * 11 / 15;
		levelBtlp83.topMargin = screenHeight * 4 / 5;
		levelBt[83].setLayoutParams(levelBtlp83);

		// 第八界面
		RelativeLayout.LayoutParams levelBtlp84 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp84.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp84.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp84.leftMargin = screenWidth / 2;
		levelBtlp84.topMargin = screenHeight / 15;
		levelBt[84].setLayoutParams(levelBtlp84);

		RelativeLayout.LayoutParams levelBtlp85 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp85.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp85.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp85.leftMargin = screenWidth / 4;
		levelBtlp85.topMargin = screenHeight / 4;
		levelBt[85].setLayoutParams(levelBtlp85);

		RelativeLayout.LayoutParams levelBtlp86 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp86.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp86.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp86.leftMargin = screenWidth / 5;
		levelBtlp86.topMargin = screenHeight / 2;
		levelBt[86].setLayoutParams(levelBtlp86);

		RelativeLayout.LayoutParams levelBtlp87 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp87.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp87.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp87.leftMargin = screenWidth / 12;
		levelBtlp87.topMargin = screenHeight * 2 / 3;
		levelBt[87].setLayoutParams(levelBtlp87);

		RelativeLayout.LayoutParams levelBtlp88 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp88.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp88.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp88.leftMargin = screenWidth / 30;
		levelBtlp88.topMargin = screenHeight / 3;
		levelBt[88].setLayoutParams(levelBtlp88);

		RelativeLayout.LayoutParams levelBtlp89 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp89.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp89.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp89.leftMargin = screenWidth * 2 / 5;
		levelBtlp89.topMargin = screenHeight * 4 / 5;
		levelBt[89].setLayoutParams(levelBtlp89);

		RelativeLayout.LayoutParams levelBtlp90 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp90.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp90.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp90.leftMargin = screenWidth * 2 / 3;
		levelBtlp90.topMargin = screenHeight / 2;
		levelBt[90].setLayoutParams(levelBtlp90);

		RelativeLayout.LayoutParams levelBtlp91 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp91.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp91.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp91.leftMargin = screenWidth * 4 / 5;
		levelBtlp91.topMargin = screenHeight / 3;
		levelBt[91].setLayoutParams(levelBtlp91);

		RelativeLayout.LayoutParams levelBtlp92 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp92.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp92.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp92.leftMargin = screenWidth / 2;
		levelBtlp92.topMargin = screenHeight / 2;
		levelBt[92].setLayoutParams(levelBtlp92);

		RelativeLayout.LayoutParams levelBtlp93 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp93.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp93.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp93.leftMargin = screenWidth * 2 / 3;
		levelBtlp93.topMargin = screenHeight / 15;
		levelBt[93].setLayoutParams(levelBtlp93);

		RelativeLayout.LayoutParams levelBtlp94 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp94.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp94.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp94.leftMargin = screenWidth * 2 / 5;
		levelBtlp94.topMargin = screenHeight / 3;
		levelBt[94].setLayoutParams(levelBtlp94);

		RelativeLayout.LayoutParams levelBtlp95 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp95.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp95.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp95.leftMargin = screenWidth * 11 / 15;
		levelBtlp95.topMargin = screenHeight * 4 / 5;
		levelBt[95].setLayoutParams(levelBtlp95);

		// 第九界面
		RelativeLayout.LayoutParams levelBtlp96 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp96.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp96.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp96.leftMargin = screenWidth / 10;
		levelBtlp96.topMargin = screenHeight / 10;
		levelBt[96].setLayoutParams(levelBtlp96);

		RelativeLayout.LayoutParams levelBtlp97 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp97.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp97.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp97.leftMargin = screenWidth / 4;
		levelBtlp97.topMargin = screenHeight / 4;
		levelBt[97].setLayoutParams(levelBtlp97);

		RelativeLayout.LayoutParams levelBtlp98 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp98.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp98.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp98.leftMargin = screenWidth *17 / 20;
		levelBtlp98.topMargin = screenHeight *3 / 5;
		levelBt[98].setLayoutParams(levelBtlp98);

		RelativeLayout.LayoutParams levelBtlp99 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp99.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp99.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp99.leftMargin = screenWidth / 12;
		levelBtlp99.topMargin = screenHeight * 2 / 3;
		levelBt[99].setLayoutParams(levelBtlp99);

		RelativeLayout.LayoutParams levelBtlp100 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp100.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp100.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp100.leftMargin = screenWidth / 30;
		levelBtlp100.topMargin = screenHeight / 3;
		levelBt[100].setLayoutParams(levelBtlp100);

		RelativeLayout.LayoutParams levelBtlp101 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp101.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp101.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp101.leftMargin = screenWidth * 2 / 5;
		levelBtlp101.topMargin = screenHeight * 4 / 5;
		levelBt[101].setLayoutParams(levelBtlp101);

		RelativeLayout.LayoutParams levelBtlp102 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp102.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp102.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp102.leftMargin = screenWidth /4;
		levelBtlp102.topMargin = screenHeight *11/ 15;
		levelBt[102].setLayoutParams(levelBtlp102);

		RelativeLayout.LayoutParams levelBtlp103 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp103.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp103.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp103.leftMargin = screenWidth * 4 / 5;
		levelBtlp103.topMargin = screenHeight / 3;
		levelBt[103].setLayoutParams(levelBtlp103);

		RelativeLayout.LayoutParams levelBtlp104 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp104.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp104.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp104.leftMargin = screenWidth / 2;
		levelBtlp104.topMargin = screenHeight / 2;
		levelBt[104].setLayoutParams(levelBtlp104);

		RelativeLayout.LayoutParams levelBtlp105 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp105.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp105.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp105.leftMargin = screenWidth * 2 / 3;
		levelBtlp105.topMargin = screenHeight / 15;
		levelBt[105].setLayoutParams(levelBtlp105);

		RelativeLayout.LayoutParams levelBtlp106 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp106.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp106.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp106.leftMargin = screenWidth * 2 / 5;
		levelBtlp106.topMargin = screenHeight *1 / 5;
		levelBt[106].setLayoutParams(levelBtlp106);

		RelativeLayout.LayoutParams levelBtlp107 = new RelativeLayout.LayoutParams(
				screenWidth / 10, screenWidth / 10);
		levelBtlp107.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		levelBtlp107.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		levelBtlp107.leftMargin = screenWidth * 11 / 15;
		levelBtlp107.topMargin = screenHeight * 4 / 5;
		levelBt[107].setLayoutParams(levelBtlp107);
	}

	// 把数据库中的数据转换成数组
	private void transformToArray() {
		ArrayList<Barrier> barriers = dao.getBarrier();
		for (int i = 0; i < barriers.size(); i++) {
			levelMark[i] = barriers.get(i).isPass();
		}
		int id = 0;

		for (int i = 1; i < barriers.size(); i++) {
			if (barriers.get(i - 1).isPass() && !barriers.get(i).isPass()) {
				id = i;
				break;
			}
		}
		levelMark[id] = true;
	}

	private int getSpliter() {
		for (int i = 0; i < 108; i++) {
			if (!levelMark[i])
				return i - 1;
		}
		return -1;
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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myApp = (MyApp) getApplicationContext();
		haveAudio = myApp.isHaveAudio();
		haveBackMusic = myApp.isHaveBackMusic();
		setContentView(R.layout.storyline);
		soundpool = new SoundPool(3, AudioManager.STREAM_SYSTEM, 0); // 创建一个SoundPool对象，该对象可以容纳5个音频流
		// 将要播放的音频流保存到HashMap对象中
		soundmap.put(2, soundpool.load(this, R.raw.xiaoqu_audio, 1));
		soundmap.put(3, soundpool.load(this, R.raw.idiom_btn_audio, 1));
		soundmap.put(4, soundpool.load(this, R.raw.other_btn_audio, 1));
		soundpool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 0); // 创建一个SoundPool对象，该对象可以容纳5个音频流
		// 将要播放的音频流保存到HashMap对象中
		soundmap.put(4, soundpool.load(this, R.raw.other_btn_audio, 1));

		if (myApp.chuangguanPlayer == null) {
			myApp.chuangguanPlayer = MediaPlayer.create(this,
					R.raw.chuangguan_music);
			if (haveBackMusic) {
				myApp.chuangguanPlayer.start();
				myApp.chuangguanPlayer.setLooping(true);
			}
		} else {
			if (haveBackMusic) {
				if (!myApp.chuangguanPlayer.isPlaying()) {
					myApp.chuangguanPlayer.start();
					myApp.chuangguanPlayer.setLooping(true);
				}
			}
		}
		if (myApp.duizhanPlayer == null) {
			myApp.duizhanPlayer = MediaPlayer.create(this, R.raw.duizhan_music);
		} else {
			myApp.duizhanPlayer.pause();
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

		// 初始化
		dao = new Dao(getBaseContext());
		this.transformToArray();

		screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
		screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）

		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		viewFlipper.setOnTouchListener(this);

		leftBt = (Button) findViewById(R.id.left);
		rightBt = (Button) findViewById(R.id.right);
		RelativeLayout.LayoutParams leftRl = new RelativeLayout.LayoutParams(
				screenHeight / 10, screenHeight / 10);
		leftRl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		leftRl.addRule(RelativeLayout.CENTER_VERTICAL);
		leftRl.leftMargin = screenHeight / 10;
		leftBt.setLayoutParams(leftRl);

		RelativeLayout.LayoutParams rightRl = new RelativeLayout.LayoutParams(
				screenHeight / 10, screenHeight / 10);
		rightRl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rightRl.addRule(RelativeLayout.CENTER_VERTICAL);
		rightRl.rightMargin = screenHeight / 10;
		rightBt.setLayoutParams(rightRl);

		leftBt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				// 从右往左，看后一个View
				// 设置View切换的动画
				// 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right
				viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
						StoryActivity.this, R.anim.slide_in_right));
				viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
						StoryActivity.this, R.anim.slide_out_left));
				// 显示前一个View
				viewFlipper.showNext();

				leftBt.setVisibility(View.VISIBLE);
				rightBt.setVisibility(View.VISIBLE);

				leftBt.startAnimation(AnimationUtils.loadAnimation(
						StoryActivity.this, R.anim.fade_in));
				rightBt.startAnimation(AnimationUtils.loadAnimation(
						StoryActivity.this, R.anim.fade_in));

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
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				// 设置View切换的动画
				viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
						StoryActivity.this, R.anim.slide_in_left));
				viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
						StoryActivity.this, R.anim.slide_out_right));
				// 显示下一个View
				viewFlipper.showPrevious();

				leftBt.setVisibility(View.VISIBLE);
				rightBt.setVisibility(View.VISIBLE);

				leftBt.startAnimation(AnimationUtils.loadAnimation(
						StoryActivity.this, R.anim.fade_in));
				rightBt.startAnimation(AnimationUtils.loadAnimation(
						StoryActivity.this, R.anim.fade_in));

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

		detector = new GestureDetector(this);

		this.findBtnById();
		this.setMyLayout();

		this.setBtnBackgroud();

		// 关卡按钮响应事件
		// 第一界面
		levelBt[0].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[0]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 1);
					startActivity(intent);
				}
			}
		});

		levelBt[1].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[1]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 2);
					startActivity(intent);
				}
			}
		});

		levelBt[2].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[2]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);

					intent.putExtra("barrierId", 3);
					startActivity(intent);
				}
			}
		});

		levelBt[3].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[3]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 4);
					startActivity(intent);
				}
			}
		});

		levelBt[4].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[4]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 5);
					startActivity(intent);
				}
			}
		});

		levelBt[5].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (levelMark[5]) {
					if (haveAudio) {
						soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
					}
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 6);
					startActivity(intent);
				}
			}
		});

		levelBt[6].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[6]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 7);
					startActivity(intent);
				}
			}
		});

		levelBt[7].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[7]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 8);
					startActivity(intent);
				}
			}
		});

		levelBt[8].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[8]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 9);
					startActivity(intent);
				}
			}
		});

		levelBt[9].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[9]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 10);
					startActivity(intent);
				}
			}
		});

		levelBt[10].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[10]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 11);
					startActivity(intent);
				}
			}
		});

		levelBt[11].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[11]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 12);
					startActivity(intent);
				}
			}
		});

		// 第二界面
		levelBt[12].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[12]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 13);
					startActivity(intent);
				}
			}
		});

		levelBt[13].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[13]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 14);
					startActivity(intent);
				}
			}
		});

		levelBt[14].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[14]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 15);
					startActivity(intent);
				}
			}
		});

		levelBt[15].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[15]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 16);
					startActivity(intent);
				}
			}
		});

		levelBt[16].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[16]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 17);
					startActivity(intent);
				}
			}
		});

		levelBt[17].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[17]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 18);
					startActivity(intent);
				}
			}
		});

		levelBt[18].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[18]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 19);
					startActivity(intent);
				}
			}
		});

		levelBt[19].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[19]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 20);
					startActivity(intent);
				}
			}
		});

		levelBt[20].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[20]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 21);
					startActivity(intent);
				}
			}
		});

		levelBt[21].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[21]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 22);
					startActivity(intent);
				}
			}
		});

		levelBt[22].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[22]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 23);
					startActivity(intent);
				}
			}
		});

		levelBt[23].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[23]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 24);
					startActivity(intent);
				}
			}
		});

		// 第三界面
		levelBt[24].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[24]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 25);
					startActivity(intent);
				}

			}
		});

		levelBt[25].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[25]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 26);
					startActivity(intent);
				}
			}
		});

		levelBt[26].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[26]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 27);
					startActivity(intent);
				}
			}
		});

		levelBt[27].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[27]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 28);
					startActivity(intent);
				}
			}
		});

		levelBt[28].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[28]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 29);
					startActivity(intent);
				}
			}
		});

		levelBt[29].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[29]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 30);
					startActivity(intent);
				}
			}
		});

		levelBt[30].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[30]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 31);
					startActivity(intent);
				}
			}
		});

		levelBt[31].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[31]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 32);
					startActivity(intent);
				}
			}
		});

		levelBt[32].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[32]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 33);
					startActivity(intent);
				}
			}
		});

		levelBt[33].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[33]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 34);
					startActivity(intent);
				}
			}
		});

		levelBt[34].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[34]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 35);
					startActivity(intent);
				}
			}
		});

		levelBt[35].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[35]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 36);
					startActivity(intent);
				}
			}
		});

		// 第四界面
		levelBt[36].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[36]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 37);
					startActivity(intent);
				}

			}
		});

		levelBt[37].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[37]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 38);
					startActivity(intent);
				}
			}
		});

		levelBt[38].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[38]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 39);
					startActivity(intent);
				}
			}
		});

		levelBt[39].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[39]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 40);
					startActivity(intent);
				}
			}
		});

		levelBt[40].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1); // 播放指定的音频
				}
				if (levelMark[40]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 41);
					startActivity(intent);
				}
			}
		});

		levelBt[41].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[41]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 42);
					startActivity(intent);
				}
			}
		});

		levelBt[42].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[42]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 43);
					startActivity(intent);
				}
			}
		});

		levelBt[43].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[43]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 44);
					startActivity(intent);
				}
			}
		});

		levelBt[44].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[44]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 45);
					startActivity(intent);
				}
			}
		});

		levelBt[45].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[45]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 46);
					startActivity(intent);
				}
			}
		});

		levelBt[46].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[46]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 47);
					startActivity(intent);
				}
			}
		});

		levelBt[47].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[47]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 48);
					startActivity(intent);
				}
			}
		});

		// 第五界面
		levelBt[48].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[48]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 49);
					startActivity(intent);
				}

			}
		});

		levelBt[49].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[49]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 50);
					startActivity(intent);
				}
			}
		});

		levelBt[50].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[50]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 51);
					startActivity(intent);
				}
			}
		});

		levelBt[51].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[51]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 52);
					startActivity(intent);
				}
			}
		});

		levelBt[52].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[52]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 53);
					startActivity(intent);
				}
			}
		});

		levelBt[53].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[53]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 54);
					startActivity(intent);
				}
			}
		});

		levelBt[54].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[54]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 55);
					startActivity(intent);
				}
			}
		});

		levelBt[55].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[55]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 56);
					startActivity(intent);
				}
			}
		});

		levelBt[56].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[56]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 57);
					startActivity(intent);
				}
			}
		});

		levelBt[57].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[57]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 58);
					startActivity(intent);
				}
			}
		});

		levelBt[58].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[58]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 59);
					startActivity(intent);
				}
			}
		});

		levelBt[59].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[59]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 60);
					startActivity(intent);
				}
			}
		});

		// 第六界面
		levelBt[60].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[60]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 61);
					startActivity(intent);
				}

			}
		});

		levelBt[61].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[61]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 62);
					startActivity(intent);
				}
			}
		});

		levelBt[62].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[62]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 63);
					startActivity(intent);
				}
			}
		});

		levelBt[63].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[63]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 64);
					startActivity(intent);
				}
			}
		});

		levelBt[64].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[64]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 65);
					startActivity(intent);
				}
			}
		});

		levelBt[65].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[65]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 66);
					startActivity(intent);
				}
			}
		});

		levelBt[66].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[66]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 67);
					startActivity(intent);
				}
			}
		});

		levelBt[67].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[67]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 68);
					startActivity(intent);
				}
			}
		});

		levelBt[68].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[68]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 69);
					startActivity(intent);
				}
			}
		});

		levelBt[69].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[69]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 70);
					startActivity(intent);
				}
			}
		});

		levelBt[70].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[70]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 71);
					startActivity(intent);
				}
			}
		});

		levelBt[71].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[71]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 72);
					startActivity(intent);
				}
			}
		});

		// 第七界面
		levelBt[72].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[72]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 73);
					startActivity(intent);
				}

			}
		});

		levelBt[73].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[73]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 74);
					startActivity(intent);
				}
			}
		});

		levelBt[74].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[74]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 75);
					startActivity(intent);
				}
			}
		});

		levelBt[75].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[75]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 76);
					startActivity(intent);
				}
			}
		});

		levelBt[76].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[76]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 77);
					startActivity(intent);
				}
			}
		});

		levelBt[77].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[77]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 78);
					startActivity(intent);
				}
			}
		});

		levelBt[78].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[78]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 79);
					startActivity(intent);
				}
			}
		});

		levelBt[79].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[79]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 80);
					startActivity(intent);
				}
			}
		});

		levelBt[80].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[80]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 81);
					startActivity(intent);
				}
			}
		});

		levelBt[81].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[81]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 82);
					startActivity(intent);
				}
			}
		});

		levelBt[82].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[82]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 83);
					startActivity(intent);
				}
			}
		});

		levelBt[83].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[83]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 84);
					startActivity(intent);
				}
			}
		});

		// 第八界面
		levelBt[84].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[84]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 85);
					startActivity(intent);
				}
			}
		});

		levelBt[85].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[85]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 86);
					startActivity(intent);
				}
			}
		});

		levelBt[86].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[86]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 87);
					startActivity(intent);
				}
			}
		});

		levelBt[87].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[87]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 88);
					startActivity(intent);
				}
			}
		});

		levelBt[88].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[88]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 89);
					startActivity(intent);
				}
			}
		});

		levelBt[89].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[89]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 90);
					startActivity(intent);
				}
			}
		});

		levelBt[90].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[90]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 91);
					startActivity(intent);
				}
			}
		});

		levelBt[91].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[91]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 92);
					startActivity(intent);
				}
			}
		});

		levelBt[92].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[92]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 93);
					startActivity(intent);
				}
			}
		});

		levelBt[93].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[93]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 94);
					startActivity(intent);
				}
			}
		});

		levelBt[94].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[94]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 95);
					startActivity(intent);
				}
			}
		});

		levelBt[95].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[95]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 96);
					startActivity(intent);
				}
			}
		});

		// 第九界面
		levelBt[96].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[96]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 97);
					startActivity(intent);
				}

			}
		});

		levelBt[97].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[97]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 98);
					startActivity(intent);
				}
			}
		});

		levelBt[98].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[98]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 99);
					startActivity(intent);
				}
			}
		});

		levelBt[99].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[00]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 100);
					startActivity(intent);
				}
			}
		});

		levelBt[100].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[100]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 101);
					startActivity(intent);
				}
			}
		});

		levelBt[101].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[101]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 102);
					startActivity(intent);
				}
			}
		});

		levelBt[102].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[102]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 103);
					startActivity(intent);
				}
			}
		});

		levelBt[103].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[103]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 104);
					startActivity(intent);
				}
			}
		});

		levelBt[104].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[104]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 105);
					startActivity(intent);
				}
			}
		});

		levelBt[105].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[105]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 106);
					startActivity(intent);
				}
			}
		});

		levelBt[106].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[106]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 107);
					startActivity(intent);
				}
			}
		});

		levelBt[107].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (haveAudio) {
					soundpool.play(soundmap.get(4), 1, 1, 0, 0, 1);
				}
				if (levelMark[107]) {
					intent = new Intent(StoryActivity.this,
							LocalGameActivity.class);
					intent.putExtra("barrierId", 108);
					startActivity(intent);
				}
			}
		});

	}

	@Override
	public void onBackPressed() {
		Intent intent1 = new Intent(StoryActivity.this, MainActivity.class);
		startActivity(intent1);
		super.onBackPressed();
	}

	@Override
	public boolean onDown(MotionEvent e) {

		leftBt.setVisibility(View.VISIBLE);
		rightBt.setVisibility(View.VISIBLE);

		leftBt.startAnimation(AnimationUtils
				.loadAnimation(this, R.anim.fade_in));
		rightBt.startAnimation(AnimationUtils.loadAnimation(this,
				R.anim.fade_in));

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

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			leftBt.startAnimation(AnimationUtils.loadAnimation(
					StoryActivity.this, R.anim.fade_out));
			rightBt.startAnimation(AnimationUtils.loadAnimation(
					StoryActivity.this, R.anim.fade_out));

			leftBt.setVisibility(View.GONE);
			rightBt.setVisibility(View.GONE);
		}
	};

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
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}


}