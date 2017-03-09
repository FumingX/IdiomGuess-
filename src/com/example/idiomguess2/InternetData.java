package com.example.idiomguess2;

import java.net.Socket;

import org.json.JSONObject;

public class InternetData {

	public static String username = null;
	public static String password = null;
	public static int coin = 0;
	public static int tip = 0;
	public static int remove = 0;
	public static int success = 0;
	public static int fail = 0;
	public static int rank = 0;
	public static int rank_grade = 0;
	public static String nickname = null;
	public static String Rank = null;
	public static int timescore = 0;
	public static int countscore = 0;
	public static long time_time = 0;
	public static long count_time = 0;
	public static int countChengyu = 0;
	public static Socket socket = null;
	public static String playername = null;
	public static int opponentCount = 0;

	public String getRankString() {
		String Rank = null;
		switch (InternetData.rank) {
		case 0:
			Rank = "ͯ��";
			break;
		case 1:
			Rank = "��ʿ";
			break;
		case 2:
			Rank = "��ʿ";
			break;
		case 3:
			Rank = "���";
			break;
		case 4:
			Rank = "����";
			break;
		case 5:
			Rank = "��ʿ";
			break;
		case 6:
			Rank = "̽��";
			break;
		case 7:
			Rank = "����";
			break;
		case 8:
			Rank = "״Ԫ";
			break;
		case 9:
			Rank = "����";
			break;
		}
		return Rank;
	}

	public String getRankString(int a) {
		String Rank = null;
		if (a <= 50) {
			Rank = "ͯ��";
		} else if (a <= 100) {
			Rank = "��ʿ";
		} else if (a <= 200) {
			Rank = "��ʿ";
		} else if (a <= 400) {
			Rank = "���";
		} else if (a <= 800) {
			Rank = "����";
		} else if (a <= 1600) {
			Rank = "��ʿ";
		} else if (a <= 3200) {
			Rank = "̽��";
		} else if (a <= 6400) {
			Rank = "����";
		} else if (a <= 12800) {
			Rank = "״Ԫ";
		} else {
			Rank = "����";
		}
		return Rank;
	}

	public int getRankGrade(int r) {
		int Rank = 0;
		switch (r) {
		case 0:
			Rank = 50;
			break;
		case 1:
			Rank = 100;
			break;
		case 3:
			Rank = 200;
			break;
		case 4:
			Rank = 400;
			break;
		case 5:
			Rank = 800;
			break;
		case 6:
			Rank = 1600;
			break;
		case 7:
			Rank = 3200;
			break;
		case 8:
			Rank = 6400;
			break;
		case 9:
			Rank = 1280;
			break;
		case 10:
			Rank = 2560;
			break;
		}
		return Rank;
	}

	public int getRankGradebyGrade(int r) {
		int grade = 0;
		if (r <= 50) {
			grade = 0;
		} else if (r <= 100) {
			grade = 50;
		} else if (r <= 200) {
			grade = 100;
		} else if (r <= 400) {
			grade = 200;
		} else if (r <= 800) {
			grade = 400;
		} else if (r <= 1600) {
			grade = 800;
		} else if (r <= 3200) {
			grade = 1600;
		} else if (r <= 6400) {
			grade = 3200;
		} else if (r <= 12800) {
			grade = 6400;
		}
		return grade;
	}

	public int getHigherRankGradebyGrade(int r) {
		int grade = 0;
		if (r <= 50) {
			grade = 50;
		} else if (r <= 100) {
			grade = 100;
		} else if (r <= 200) {
			grade = 200;
		} else if (r <= 400) {
			grade = 400;
		} else if (r <= 800) {
			grade = 800;
		} else if (r <= 1600) {
			grade = 1600;
		} else if (r <= 3200) {
			grade = 3200;
		} else if (r <= 6400) {
			grade = 6400;
		} else if (r <= 12800) {
			grade = 12800;
		}
		return grade;
	}
}
