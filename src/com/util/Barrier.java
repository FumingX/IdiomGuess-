package com.util;

public class Barrier {
	@Override
	public String toString() {
		return "Barrier [id=" + id + ", starNum=" + starNum + ", grage="
				+ grage + ", isPass=" + isPass + "]";
	}

	int id;// �غ�
	int starNum;// ������
	int grage;// ��߷�
	boolean isPass;//�Ƿ����

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStarNum() {
		return starNum;
	}

	public void setStarNum(int starNum) {
		this.starNum = starNum;
	}

	public int getGrage() {
		return grage;
	}

	public void setGrage(int grage) {
		this.grage = grage;
	}

}
