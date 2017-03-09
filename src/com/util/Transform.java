package com.util;

public class Transform {

	// 用于表示是否是人物关
	public boolean isPerson = false;
	// 用于表示此关卡是该朝代关的第几个
	public int order = 1;
	

	public String barrierToDynasty(int barrierId) {
		String dynasty = null;
		switch (barrierId) {
		case 1:
			dynasty = "神话";
			order = 1;
			break;
		case 13:
			dynasty = "神话";
			order = 2;
			break;
		case 10:
			dynasty = "网络";
			order = 1;
			break;
		case 2:
			dynasty = "炎黄";
			order = 1;
			break;
		case 3:
			dynasty = "夏";
			order = 1;
			break;
		case 101:
			dynasty = "商";
			order = 1;
			break;
		case 5:
			dynasty = "周";
			order = 1;
			break;
		case 14:
			dynasty = "周";
			order = 2;
			break;
		case 25:
			dynasty = "周";
			order = 3;
			break;
		case 37:
			dynasty = "周";
			order = 4;
			break;
		case 49:
			dynasty = "周";
			order = 5;
			break;
		case 61:
			dynasty = "周";
			order = 6;
			break;
		case 6:
			dynasty = "秦";
			order = 1;
			break;
		case 15:
			dynasty = "秦";
			order = 2;
			break;
		case 26:
			dynasty = "秦";
			order = 3;
			break;
		case 38:
			dynasty = "秦";
			order = 4;
			break;
		case 50:
			dynasty = "秦";
			order = 5;
			break;
		case 62:
			dynasty = "秦";
			order = 6;
			break;
		case 71:
			dynasty = "秦";
			isPerson = true;
			order = 7;
			break;
		case 7:
			dynasty = "汉";
			order = 1;
			break;
		case 16:
			dynasty = "汉";
			order = 2;
			break;
		case 27:
			dynasty = "汉";
			order = 3;
			break;
		case 39:
			dynasty = "汉";
			order = 4;
			break;
		case 51:
			dynasty = "汉";
			order = 5;
			break;
		case 63:
			dynasty = "汉";
			order = 6;
			break;
		case 73:
			dynasty = "汉";
			order = 7;
			break;
		case 85:
			dynasty = "汉";
			order = 8;
			break;
		case 95:
			dynasty = "汉";
			order = 9;
			break;
		case 97:
			dynasty = "汉";
			order = 10;
			break;
		case 4:
			dynasty = "汉";
			order = 11;
			break;
		case 56:
			dynasty = "汉";
			order = 12;
			break;
		case 42:
			dynasty = "汉";
			order = 13;
			break;
		case 29:
			dynasty = "汉";
			order = 14;
			break;
		case 19:
			dynasty = "汉";
			order = 15;
			break;
		case 72:
			dynasty = "汉";
			order = 16;
			isPerson = true;
			break;
		case 8:
			dynasty = "三国";
			order = 1;
			break;
		case 17:
			dynasty = "三国";
			order = 2;
			break;
		case 28:
			dynasty = "三国";
			order = 3;
			break;
		case 40:
			dynasty = "三国";
			order = 4;
			break;
		case 52:
			dynasty = "三国";
			order = 5;
			break;
		case 64:
			dynasty = "三国";
			order = 6;
			break;
		case 74:
			dynasty = "三国";
			order = 7;
			break;
		case 86:
			dynasty = "三国";
			order = 8;
			break;
		case 96:
			dynasty = "三国";
			order = 9;
			break;
		case 98:
			dynasty = "三国";
			order = 10;
			break;
		case 81:
			dynasty = "三国";
			order = 11;
			isPerson = true;
			break;

		case 9:
			dynasty = "晋";
			order = 1;
			break;
		case 18:
			dynasty = "晋";
			order = 2;
			break;
		case 102:
			dynasty = "晋";
			order = 3;
			break;
		case 41:
			dynasty = "晋";
			order = 4;
			break;
		case 53:
			dynasty = "晋";
			order = 5;
			break;
		case 65:
			dynasty = "晋";
			order = 6;
			break;
		case 75:
			dynasty = "晋";
			order = 7;
			break;
		case 87:
			dynasty = "晋";
			order = 8;
			break;
		case 69:
			dynasty = "网络";
			order = 2;
			break;
		case 93:
			dynasty = "南北朝";
			order = 1;
			break;
		case 11:
			dynasty = "隋";
			order = 1;
			break;
		case 103:
			dynasty = "隋";
			order = 2;
			break;
		case 30:
			dynasty = "隋";
			order = 3;
			break;
		case 106:
			dynasty = "隋";
			order = 4;
			break;
		case 54:
			dynasty = "隋";
			order = 5;
			break;
		case 66:
			dynasty = "隋";
			order = 6;
			break;
		case 76:
			dynasty = "隋";
			order = 7;
			break;

		case 12:
			dynasty = "唐";
			order = 1;
			break;
		case 20:
			dynasty = "唐";
			order = 2;
			break;
		case 31:
			dynasty = "唐";
			order = 3;
			break;
		case 43:
			dynasty = "唐";
			order = 4;
			break;
		case 55:
			dynasty = "唐";
			order = 5;
			break;
		case 67:
			dynasty = "唐";
			order = 6;
			break;
		case 77:
			dynasty = "唐";
			order = 7;
			break;
		case 88:
			dynasty = "唐";
			order = 8;
			break;
		case 99:
			dynasty = "唐";
			order = 9;
			break;
		case 46:
			dynasty = "唐";
			order = 10;
			break;
		case 60:
			dynasty = "唐";
			order = 11;
			break;
		case 107:
			dynasty = "唐";
			order = 12;
			break;
		case 82:
			dynasty = "唐";
			order = 13;
			isPerson = true;
			break;
		case 21:
			dynasty = "五代十国";
			order = 1;
			break;
		case 32:
			dynasty = "五代十国";
			order = 2;
			break;
		case 44:
			dynasty = "五代十国";
			order = 3;
			break;
		case 104:
			dynasty = "五代十国";
			order = 4;
			break;
		case 68:
			dynasty = "五代十国";
			order = 5;
			break;
		case 78:
			dynasty = "五代十国";
			order = 6;
			break;
		case 89:
			dynasty = "五代十国";
			order = 7;
			break;
		case 83:
			dynasty = "五代十国";
			order = 8;
			isPerson = true;
			break;
		case 22:
			dynasty = "宋";
			order = 1;
			break;
		case 33:
			dynasty = "宋";
			order = 2;
			break;
		case 45:
			dynasty = "宋";
			order = 3;
			break;
		case 57:
			dynasty = "宋";
			order = 4;
			break;
		case 94:
			dynasty = "宋";
			order = 5;
			break;
		case 79:
			dynasty = "宋";
			order = 6;
			break;
		case 84:
			dynasty = "宋";
			order = 7;
			isPerson = true;
			break;
		case 23:
			dynasty = "元";
			order = 1;
			break;
		case 34:
			dynasty = "元";
			order = 2;
			break;
		case 108:
			dynasty = "元";
			order = 3;
			break;
		case 58:
			dynasty = "元";
			order = 4;
			break;
		case 91:
			dynasty = "元";
			order = 5;
			isPerson = true;
			break;
		case 24:
			dynasty = "明";
			order = 1;
			break;
		case 35:
			dynasty = "明";
			order = 2;
			break;
		case 47:
			dynasty = "明";
			order = 3;
			break;
		case 59:
			dynasty = "明";
			order = 4;
			break;
		case 70:
			dynasty = "明";
			order = 5;
			break;
		case 105:
			dynasty = "明";
			order = 6;
			break;
		case 90:
			dynasty = "明";
			order = 7;
			break;
		case 100:
			dynasty = "明";
			order = 8;
			break;
		case 92:
			dynasty = "明";
			order = 9;
			isPerson = true;
			break;
		case 36:
			dynasty = "清";
			order = 1;
			break;
		case 48:
			dynasty = "清";
			order = 2;
			break;
		case 80:
			dynasty = "清";
			order = 3;
			break;
		}

		return dynasty;
	}
}
