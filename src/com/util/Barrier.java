package com.util;

public class Barrier {
	@Override
	public String toString() {
		return "Barrier [id=" + id + ", starNum=" + starNum + ", grage="
				+ grage + ", isPass=" + isPass + "]";
	}

	int id;// 关号
	int starNum;// 星星数
	int grage;// 最高分
	boolean isPass;//是否过关

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
