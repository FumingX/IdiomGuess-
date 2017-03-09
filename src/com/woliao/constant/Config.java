package com.woliao.constant;

import java.util.Collection;

public interface Config {
	public static final int REQUEST_LOGIN = 1000;
	public static final int REQUEST_REGISTER = 1001;
	public static final int REQUEST_EXIT = 1002;
	public static final int REQUEST_QUICK_LOGIN = 1003;
	public static final int REQUEST_GET_PROP = 1004;
	public static final int REQUEST_MODIFY_PROP = 1005;
	public static final int REQUEST_ADD_FRIEND = 1006;
	public static final int REQUEST_GET_FRIEND = 1007;
	public static final int REQUEST_GET_USERS_ONLINE = 1008;
	public static final int REQUEST_ADD_SCORES = 1009;
	public static final int REQUEST_GET_SCORES = 1010;
	public static final int REQUEST_GET_CHENGYU = 1011;
	public static final int REQUEST_SEND_INVITE = 1012;
	public static final int REQUEST_INVITE_RESULT = 1013;
	public static final int REQUEST_EXIT_GAME = 1014;
	public static final int REQUEST_ADD_PLAYERSCORE = 1015;
	public static final int REQUEST_PK_RESULT = 1016;
	public static final int REQUEST_PK_PROCESS = 1017;
	public static final int REQUEST_TIME_SCORE = 1018;
	public static final int REQUEST_COUNT_SCORE = 1019;
	public static final int REQUEST_GAME = 1020;
	public static final int REQUEST_SEARCH_FRIEND = 1022;
	public static final int REQUEST_BASIC_INFO = 1023;
	public static final int REQUEST_DELETE_FRIEND = 1024;
	public static final int REQUEST_GET_NOTIFY = 1025;
	public static final int ACCEPT_ADD_FRIEND = 1026;
	public static final int REFUSE_ADD_FRIEND = 1027;
	public static final int REQUEST_GET_COUNT_RANKINGLIST = 2029;
	public static final int REQUEST_GET_TIME_RANKINGLIST = 2030;

	public static final int SUCCESS = 2000;
	public static final int FAIl = 2001;
	public static final int ONLINE = 2002;
	public static final int QUICK_FAIl = 2003;
	public static final int REGISTER_FAIl = 2004;
	public static final int REGISTER_SUCCESS = 2005;
	public static final int SEARCH_FRIEND = 2006;
	public static final int REQUEST_ADD_FRIEND_SUCCESS = 2007;
	public static final int REQUEST_ADD_FRIEND_FAIL = 2008;
	public static final int REQUEST_DELETE_FRIEND_SUCCESS = 2009;
	public static final int REQUEST_DELETE_FRIEND_FAIL = 2010;
	public static final int ACCEPT_ADD_FRIEND_SUCCESS = 2011;
	public static final int ACCEPT_ADD_FRIEND_Fail = 2012;
	public static final int REFUSE_FRIEND_SUCCESS = 2013;
	public static final int REQUEST_SET_TIPNUM = 2014;
	public static final int REQUEST_SET_REMOVENUM = 2015;
	public static final int REQUEST_BUY_PROP = 2026;
	public static final String KEY = "";
	public static final String NAME = "root";
	public static final int REQUEST_GET_OPPONENTCOUNT = 1030;//得到对手的剩余字数
	public static final int COUNT_RESULT = 1031;
	
	public static final int USER_STATE_ONLINE = 3000;
	public static final int USER_STATE_NON_ONLINE = 3001;

	public static final String RESULT = "result";
	public static final String REQUEST_TYPE = "requestType";

	public static final String IP = "10.3.2.44";//"192.168.253.2"; 10.3.2.59 192.168.253.2
												// 169.254.178.136
	
	

}
