package com.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBAdapter extends SQLiteOpenHelper {  
	
	//idiom��
    static final String KEY_ID = "ID";  
    static final String KEY_IDIOM = "idiom";
    static final String KEY_PARAPHARE = "paraphare";
    static final String KEY_DYNASTY = "dynasty";
    static final String KEY_NAME = "name";  
    static final String KEY_IS_APPARE = "is_appare";  
    static final String TAG = "DBAdapter";  
    
    //�ؿ���
    static final String POINT = "point"; 
    static final String IS_PASS = "is_pass"; 
    static final String SCORE = "score"; 
    static final String GRADE = "grade"; 
    
    //���߱�
    static final String PROP1 = "prop1_num"; 
    static final String PROP2 = "prop2_num";
    static final String STARNUM = "star_num";
    
    //���ݿ���Ϣ
    static final String DATABASE_NAME = "idiom";  
    static final int DATABASE_VERSION = 1;  
 
	
	public DBAdapter(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO �Զ����ɵĹ��캯�����
	}
	public DBAdapter(Context context) {
		super(context, "idiom", null, 1);
	}

	

	@Override
	public void onCreate(SQLiteDatabase db) {
//		db.execSQL("create table IF NOT EXISTS my_idiom(id integer primary key autoincrement,idiom text not null, paraphare text,dynasty text not null, name text, is_appare integer not null);");
//    	db.execSQL("create table IF NOT EXISTS checkpoint(id integer not null, is_pass integer not null, score integer,grade integer);");
//    	db.execSQL("create table IF NOT EXISTS guess_idiom(id integer primary key autoincrement,idiom text not null,paraphare text,discribe text not null,dynasty text,is_appare integer not null);");
//    	db.execSQL("create table IF NOT EXISTS prop(prop1_num integer, prop2_num integer, star_num integer);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}


}
