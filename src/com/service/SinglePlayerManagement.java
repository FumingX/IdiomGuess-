package com.service;

import java.util.ArrayList;
import android.content.Context;
import com.dao.Dao;
import com.util.Barrier;
import com.util.Idiom;
import com.util.Place;
import com.util.Transform;


public class SinglePlayerManagement {
	// 9个成语打乱的的字符数组
	public char[][] chars = new char[6][6];
	// 9个成语
	public String[] idioms = new String[9];
	// 对应某个成语是否被消掉
	private boolean[] isOk = new boolean[9];
	// 用于数据库连接
	private Dao dao;
	// 当前游戏关号
	private int barrierId = 1;
	// 当前朝代
	private String dynastic;
	// 用于表示是否是人物关
	private boolean isPerson = false;
	public String getDynastic() {
		return dynastic;
	}

	public boolean isPerson() {
		return isPerson;
	}

	// 用于表示此关卡是该朝代关的第几个
	private int order = 1;
	// 当前分数
	public int myGrade = 0;
	// 连击次数
	public int clickNum = 0;
	// 表示上一次点击的时间；
	private int lastTime;

	public SinglePlayerManagement(Context context) {
		dao = new Dao(context);

		init();
	}

	// 全部初始化
	private void init() {
		clickNum = 0;
		myGrade = 0;
		for (int i = 0; i < 9; i++) {
			isOk[i] = false;
		}
		lastTime = 180000;
	}

	// 随机生成9个成语（根据朝代）OK
	private void getIdiomsByDynastic(String dynastic) {
		for (int i = 0; i < 9; i++)
			isOk[i] = false;
		ArrayList<Idiom> mIdioms = dao.getIdiomsByDynastic(dynastic);
		int count = 0;
		for (int i = 9 * (order - 1); i < 9 * order; i++) {
			Idiom aIdiom = mIdioms.get(count);
			idioms[count] = aIdiom.getIdiom();// 获得成语
			count++;
		}
	}

	// 随机生成9个成语（根据朝代获得某个人物的成语）OK
	private void getIdiomsByPerson(String dynastic) {
		for (int i = 0; i < 9; i++)
			isOk[i] = false;
		ArrayList<Idiom> mIdioms = dao.getIdiomsByPerson(dynastic);
		int count = 0;
		for (int i = 9 * (order - 1); i < 9 * order; i++) {
			Idiom aIdiom = mIdioms.get(count);
			idioms[count] = aIdiom.getIdiom();// 获得成语
			count++;
		}
	}

	// 36个字符数组中生成打乱的字符（朝代关）OK
	private void getCharsByDynastic(String dynastic) {
		// 用于对应成语的中的字和数组中的字符的位置关系
		int[] relation = new int[36];
		this.getIdiomsByDynastic(dynastic);// 获得9个成语

		for (int i = 0; i < 36; i++) {
			relation[i] = i;
		}

		for (int i = 0; i < 72; i++) {// 将数组打乱
			int num1 = (int) (Math.random() * 36);
			int num2 = (int) (Math.random() * 36);
			int t;
			t = relation[num1];
			relation[num1] = relation[num2];
			relation[num2] = t;
		}

		for (int i = 0; i < 36; i++) {// 赋值
			int x1 = i / 4;
			int y1 = i % 4;
			int x2 = relation[i] / 6;
			int y2 = relation[i] % 6;
			chars[x2][y2] = idioms[x1].charAt(y1);
		}
	}

	// 36个字符数组中生成打乱的字符（人物关）OK
	private void getCharsByPerson(String dynastic) {
		this.getIdiomsByPerson(dynastic);
		int[] a = new int[36];
		for (int i = 0; i < 36; i++) {
			a[i] = i;
		}

		for (int i = 0; i < 72; i++) {// 将数组打乱
			int num1 = (int) (Math.random() * 36);
			int num2 = (int) (Math.random() * 36);
			int t;
			t = a[num1];
			a[num1] = a[num2];
			a[num2] = t;
		}

		for (int i = 0; i < 36; i++) {// 赋值
			int x1 = i / 4;
			int y1 = i % 4;
			int x2 = a[i] / 6;
			int y2 = a[i] % 6;
			chars[x2][y2] = idioms[x1].charAt(y1);
		}
	}

	// 获取该朝代人物的名字（人物关)OK
	public String getPersonName(String dynastic) {
		ArrayList<Idiom> mIdioms = dao.getIdiomsByPerson(dynastic);
		Idiom idiom = mIdioms.get(0);
		return idiom.getPerson();
	}

	// 获得道具1数量(提示两个字）OK
	public int getProperty1Num() {
		return dao.getProperty1Num();
	}

	// 获得道具2数量（直接消去）OK
	public int getProperty2Num() {
		return dao.getProperty2Num();
	}

	// 获得用户积累星星数OK
	public int getTotalStarNum() {
		return dao.getRestStarNum();
	}

	// 寻找某一个字符的位置
	private Place findCharPlace(char c) {
		Place place = new Place();
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++) {
				if (chars[i][j] == c) {
					place.setX(i);
					place.setY(j);
					return place;
				}
			}
		return place;
	}

	// 使用一个道具1，返回一个位置list
	public ArrayList<Place> useProperty1() {
		int num = dao.getProperty1Num();
		ArrayList<Place> places = new ArrayList<Place>();
		if (num > 0) {
			for (int i = 0; i < 9; i++) {
				if (!isOk[i]) {
					places.add(this.findCharPlace(idioms[i].charAt(0)));
					places.add(this.findCharPlace(idioms[i].charAt(1)));
					// 道具数减一
					num--;
					dao.setProperty1Num(num);
					break;
				}
			}
		}
		return places;
	}

	// 使用一个道具2，返回一个位置list
	public ArrayList<Place> useProperty2() {
		int num = dao.getProperty2Num();
		ArrayList<Place> places = new ArrayList<Place>();
		if (num > 0) {
			for (int i = 0; i < 9; i++) {
				if (!isOk[i]) {
					isOk[i] = true;
					places.add(this.findCharPlace(idioms[i].charAt(0)));
					places.add(this.findCharPlace(idioms[i].charAt(1)));
					places.add(this.findCharPlace(idioms[i].charAt(2)));
					places.add(this.findCharPlace(idioms[i].charAt(3)));

					// 道具数减一
					num--;
					dao.setProperty2Num(num);
					break;
				}
			}
		}
		return places;
	}

	// 道具1数量加1
	public boolean buyProperty1() {
		int totalStarNum = dao.getRestStarNum();
		if (totalStarNum >= 3) {// 如果总星星数大于3，则可以购买道具
			int num = dao.getProperty1Num();
			num++;
			dao.setProperty1Num(num);
			totalStarNum -= 3;
			dao.setRestStarNum(totalStarNum);
			return true;
		} else {
			return false;
		}

	}
	
	public void addProperty2(){
		int num = dao.getProperty2Num();
		num++;
		dao.setProperty2Num(num);
	}

	// 道具2数量加1
	public boolean buyProperty2() {
		int totalStarNum = dao.getRestStarNum();
		if (totalStarNum >= 5) {// 如果总星星数大于5，则可以购买道具
			int num = dao.getProperty2Num();
			num++;
			dao.setProperty2Num(num);
			totalStarNum -= 5;
			dao.setRestStarNum(totalStarNum);
			return true;
		} else {
			return false;
		}
	}

	// 获得所有关卡信息
	public ArrayList<Barrier> getBarriers() {
		return dao.getBarrier();
	}

	// 评分
	private int grade(int time) {
		if (time <= 180) {
			return (int) (180 - time) * 20 + myGrade;
		} else {
			return myGrade;
		}
	}

	// 评星星
	private int gradeStar(int grade) {
		if (grade >= 2400) {
			return 3;
		} else if (grade >= 1600) {
			return 2;
		} else if (grade >= 700) {
			return 1;
		} else {
			return 0;
		}
	}

	// 开始游戏OK
	public void startGame(int barrierId) {
		// 初始化赋值
		this.init();
		Transform transform = new Transform();
		this.barrierId = barrierId;
		this.dynastic = transform.barrierToDynasty(barrierId);
		this.order = transform.order;
		this.isPerson = transform.isPerson;
		for (int i = 0; i < 9; i++) {
			isOk[i] = false;
		}
		// 获得字符数组
		if (isPerson) {
			this.getCharsByPerson(this.dynastic);
		} else {
			this.getCharsByDynastic(this.dynastic);
		}

	}

	// 重新开始OK
	public void restartGame() {
		// 全部初始化
		this.init();
		// 获得字符数组
		if (isPerson) {
			this.getCharsByPerson(this.dynastic);
		} else {
			this.getCharsByDynastic(this.dynastic);
		}
	}

	// 判断是否能够消去
	public boolean judge(int n1, int n2, int n3, int n4, int restTime) {
		char a4, a1, a2, a3;
		a1 = chars[n1 / 6][n1 % 6];
		a2 = chars[n2 / 6][n2 % 6];
		a3 = chars[n3 / 6][n3 % 6];
		a4 = chars[n4 / 6][n4 % 6];

		String str = String.valueOf(a1) + a2 + a3 + a4;

		for (int i = 0; i < 9; i++) {
			if (idioms[i].equals(str)) {
				isOk[i] = true;
				chars[n1 / 6][n1 % 6] = ' ';
				chars[n2 / 6][n2 % 6] = ' ';
				chars[n3 / 6][n3 % 6] = ' ';
				chars[n4 / 6][n4 % 6] = ' ';
				// 计算分数
				myGrade += 100;
				// 如果有连击则分数增加
				if ((lastTime - restTime) < 5) {
					myGrade = myGrade + clickNum * 100;
					clickNum++;
				} else {
					clickNum = 1;
				}

				lastTime = restTime;
				return true;
			}
		}

		// 不能消去,分数减小
		myGrade -= 50;
		clickNum = 0;
		lastTime = restTime;
		return false;
	}

	// 判断是否完成
	public boolean isFinish() {
		for (int i = 0; i < 9; i++) {
			if (isOk[i] == false)
				return false;
		}
		return true;
	}

	// 通过本关
	public void finishGame(int restTime) {
		int  starNum;
		// long time = timeThread.getTotalTime();
		myGrade = this.grade(restTime);// 平分
		starNum = this.gradeStar(myGrade);// 评星星
		int totalStarNum = dao.getRestStarNum() + starNum;// 对应总星星数增加
		dao.setRestStarNum(totalStarNum);// 对应总星星数增加
		Barrier barrier = dao.getBarrierById(barrierId);
		// 更新关卡数据
		if (barrier.getGrage() < myGrade)
			barrier.setGrage(myGrade);
		if (barrier.getStarNum() < starNum)
			barrier.setStarNum(starNum);
		barrier.setPass(true);
		dao.setBarrierById(barrier);
	}

	// 获得成绩
	public int getMyGrade() {
		return myGrade;
	}

	// 找的没有通过，但是可以游戏的关卡号
	public int findBarrierIdCanPlay() {
		int id = 1;
		ArrayList<Barrier> barriers = this.getBarriers();
		for (int i = 1; i < barriers.size(); i++) {
			if (barriers.get(i - 1).isPass() && !barriers.get(i).isPass()) {
				id = i;
				break;
			}
		}
		return id;
	}

}
