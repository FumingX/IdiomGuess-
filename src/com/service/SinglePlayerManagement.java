package com.service;

import java.util.ArrayList;
import android.content.Context;
import com.dao.Dao;
import com.util.Barrier;
import com.util.Idiom;
import com.util.Place;
import com.util.Transform;


public class SinglePlayerManagement {
	// 9��������ҵĵ��ַ�����
	public char[][] chars = new char[6][6];
	// 9������
	public String[] idioms = new String[9];
	// ��Ӧĳ�������Ƿ�����
	private boolean[] isOk = new boolean[9];
	// �������ݿ�����
	private Dao dao;
	// ��ǰ��Ϸ�غ�
	private int barrierId = 1;
	// ��ǰ����
	private String dynastic;
	// ���ڱ�ʾ�Ƿ��������
	private boolean isPerson = false;
	public String getDynastic() {
		return dynastic;
	}

	public boolean isPerson() {
		return isPerson;
	}

	// ���ڱ�ʾ�˹ؿ��Ǹó����صĵڼ���
	private int order = 1;
	// ��ǰ����
	public int myGrade = 0;
	// ��������
	public int clickNum = 0;
	// ��ʾ��һ�ε����ʱ�䣻
	private int lastTime;

	public SinglePlayerManagement(Context context) {
		dao = new Dao(context);

		init();
	}

	// ȫ����ʼ��
	private void init() {
		clickNum = 0;
		myGrade = 0;
		for (int i = 0; i < 9; i++) {
			isOk[i] = false;
		}
		lastTime = 180000;
	}

	// �������9��������ݳ�����OK
	private void getIdiomsByDynastic(String dynastic) {
		for (int i = 0; i < 9; i++)
			isOk[i] = false;
		ArrayList<Idiom> mIdioms = dao.getIdiomsByDynastic(dynastic);
		int count = 0;
		for (int i = 9 * (order - 1); i < 9 * order; i++) {
			Idiom aIdiom = mIdioms.get(count);
			idioms[count] = aIdiom.getIdiom();// ��ó���
			count++;
		}
	}

	// �������9��������ݳ������ĳ������ĳ��OK
	private void getIdiomsByPerson(String dynastic) {
		for (int i = 0; i < 9; i++)
			isOk[i] = false;
		ArrayList<Idiom> mIdioms = dao.getIdiomsByPerson(dynastic);
		int count = 0;
		for (int i = 9 * (order - 1); i < 9 * order; i++) {
			Idiom aIdiom = mIdioms.get(count);
			idioms[count] = aIdiom.getIdiom();// ��ó���
			count++;
		}
	}

	// 36���ַ����������ɴ��ҵ��ַ��������أ�OK
	private void getCharsByDynastic(String dynastic) {
		// ���ڶ�Ӧ������е��ֺ������е��ַ���λ�ù�ϵ
		int[] relation = new int[36];
		this.getIdiomsByDynastic(dynastic);// ���9������

		for (int i = 0; i < 36; i++) {
			relation[i] = i;
		}

		for (int i = 0; i < 72; i++) {// ���������
			int num1 = (int) (Math.random() * 36);
			int num2 = (int) (Math.random() * 36);
			int t;
			t = relation[num1];
			relation[num1] = relation[num2];
			relation[num2] = t;
		}

		for (int i = 0; i < 36; i++) {// ��ֵ
			int x1 = i / 4;
			int y1 = i % 4;
			int x2 = relation[i] / 6;
			int y2 = relation[i] % 6;
			chars[x2][y2] = idioms[x1].charAt(y1);
		}
	}

	// 36���ַ����������ɴ��ҵ��ַ�������أ�OK
	private void getCharsByPerson(String dynastic) {
		this.getIdiomsByPerson(dynastic);
		int[] a = new int[36];
		for (int i = 0; i < 36; i++) {
			a[i] = i;
		}

		for (int i = 0; i < 72; i++) {// ���������
			int num1 = (int) (Math.random() * 36);
			int num2 = (int) (Math.random() * 36);
			int t;
			t = a[num1];
			a[num1] = a[num2];
			a[num2] = t;
		}

		for (int i = 0; i < 36; i++) {// ��ֵ
			int x1 = i / 4;
			int y1 = i % 4;
			int x2 = a[i] / 6;
			int y2 = a[i] % 6;
			chars[x2][y2] = idioms[x1].charAt(y1);
		}
	}

	// ��ȡ�ó�����������֣������)OK
	public String getPersonName(String dynastic) {
		ArrayList<Idiom> mIdioms = dao.getIdiomsByPerson(dynastic);
		Idiom idiom = mIdioms.get(0);
		return idiom.getPerson();
	}

	// ��õ���1����(��ʾ�����֣�OK
	public int getProperty1Num() {
		return dao.getProperty1Num();
	}

	// ��õ���2������ֱ����ȥ��OK
	public int getProperty2Num() {
		return dao.getProperty2Num();
	}

	// ����û�����������OK
	public int getTotalStarNum() {
		return dao.getRestStarNum();
	}

	// Ѱ��ĳһ���ַ���λ��
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

	// ʹ��һ������1������һ��λ��list
	public ArrayList<Place> useProperty1() {
		int num = dao.getProperty1Num();
		ArrayList<Place> places = new ArrayList<Place>();
		if (num > 0) {
			for (int i = 0; i < 9; i++) {
				if (!isOk[i]) {
					places.add(this.findCharPlace(idioms[i].charAt(0)));
					places.add(this.findCharPlace(idioms[i].charAt(1)));
					// ��������һ
					num--;
					dao.setProperty1Num(num);
					break;
				}
			}
		}
		return places;
	}

	// ʹ��һ������2������һ��λ��list
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

					// ��������һ
					num--;
					dao.setProperty2Num(num);
					break;
				}
			}
		}
		return places;
	}

	// ����1������1
	public boolean buyProperty1() {
		int totalStarNum = dao.getRestStarNum();
		if (totalStarNum >= 3) {// ���������������3������Թ������
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

	// ����2������1
	public boolean buyProperty2() {
		int totalStarNum = dao.getRestStarNum();
		if (totalStarNum >= 5) {// ���������������5������Թ������
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

	// ������йؿ���Ϣ
	public ArrayList<Barrier> getBarriers() {
		return dao.getBarrier();
	}

	// ����
	private int grade(int time) {
		if (time <= 180) {
			return (int) (180 - time) * 20 + myGrade;
		} else {
			return myGrade;
		}
	}

	// ������
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

	// ��ʼ��ϷOK
	public void startGame(int barrierId) {
		// ��ʼ����ֵ
		this.init();
		Transform transform = new Transform();
		this.barrierId = barrierId;
		this.dynastic = transform.barrierToDynasty(barrierId);
		this.order = transform.order;
		this.isPerson = transform.isPerson;
		for (int i = 0; i < 9; i++) {
			isOk[i] = false;
		}
		// ����ַ�����
		if (isPerson) {
			this.getCharsByPerson(this.dynastic);
		} else {
			this.getCharsByDynastic(this.dynastic);
		}

	}

	// ���¿�ʼOK
	public void restartGame() {
		// ȫ����ʼ��
		this.init();
		// ����ַ�����
		if (isPerson) {
			this.getCharsByPerson(this.dynastic);
		} else {
			this.getCharsByDynastic(this.dynastic);
		}
	}

	// �ж��Ƿ��ܹ���ȥ
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
				// �������
				myGrade += 100;
				// ������������������
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

		// ������ȥ,������С
		myGrade -= 50;
		clickNum = 0;
		lastTime = restTime;
		return false;
	}

	// �ж��Ƿ����
	public boolean isFinish() {
		for (int i = 0; i < 9; i++) {
			if (isOk[i] == false)
				return false;
		}
		return true;
	}

	// ͨ������
	public void finishGame(int restTime) {
		int  starNum;
		// long time = timeThread.getTotalTime();
		myGrade = this.grade(restTime);// ƽ��
		starNum = this.gradeStar(myGrade);// ������
		int totalStarNum = dao.getRestStarNum() + starNum;// ��Ӧ������������
		dao.setRestStarNum(totalStarNum);// ��Ӧ������������
		Barrier barrier = dao.getBarrierById(barrierId);
		// ���¹ؿ�����
		if (barrier.getGrage() < myGrade)
			barrier.setGrage(myGrade);
		if (barrier.getStarNum() < starNum)
			barrier.setStarNum(starNum);
		barrier.setPass(true);
		dao.setBarrierById(barrier);
	}

	// ��óɼ�
	public int getMyGrade() {
		return myGrade;
	}

	// �ҵ�û��ͨ�������ǿ�����Ϸ�Ĺؿ���
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
