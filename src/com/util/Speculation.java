package com.util;

public class Speculation {
	private int id;
	private String idiom;
	private String explain;//�������
	private String dynastic;
	private String describe;//����
	private boolean isUsed;//�Ƿ�ʹ�ù�

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdiom() {
		return idiom;
	}

	public void setIdiom(String idiom) {
		this.idiom = idiom;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getDynastic() {
		return dynastic;
	}

	public void setDynastic(String dynastic) {
		this.dynastic = dynastic;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
