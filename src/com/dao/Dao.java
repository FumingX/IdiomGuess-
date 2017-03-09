package com.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.util.*;


public class Dao{

	private DBAdapter dbAdalper;
	//idiom表
    static final String KEY_ID = "ID";  
    static final String KEY_IDIOM = "idiom";
    static final String KEY_PARAPHARE = "paraphare";
    static final String KEY_DYNASTY = "dynasty";
    static final String KEY_NAME = "name";  
    static final String KEY_IS_APPARE = "is_appare";  
    static final String TAG = "DBAdapter";  
    
    //关卡表
    static final String POINT = "point"; 
    static final String IS_PASS = "is_pass"; 
    static final String SCORE = "score"; 
    static final String GRADE = "grade"; 
    
    //道具表
    static final String PROP1 = "prop1_num"; 
    static final String PROP2 = "prop2_num";
    static final String STARNUM = "star_num";
    
    //数据库信息
    static final String DATABASE_NAME = "idiom";  
    static final int DATABASE_VERSION = 1;
    
    
	public Dao(Context context) {
		copyResToSdcard copyResToSd = new copyResToSdcard();
		copyResToSd.copy(context);
		dbAdalper = new DBAdapter(context);
	}
	//获得所有关卡状态(OK)
	public ArrayList<Idiom> getIdiomsByDynastic(String dynastic) {
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		Cursor c = db.query(false, "my_idiom", new String[]{KEY_ID, KEY_IDIOM,KEY_PARAPHARE,KEY_DYNASTY,KEY_NAME,KEY_IS_APPARE}, KEY_DYNASTY + "="+"\'"+dynastic+"\'"+" and "+KEY_NAME+" is null", null, null, null, null, null);
		Idiom idiom = null;
		ArrayList<Idiom> list = new ArrayList<Idiom>();
		while (c.moveToNext()) {
		  idiom = new Idiom();
		  idiom.setId(c.getInt(0));
      	  idiom.setIdiom(c.getString(1));
      	  idiom.setExplain(c.getString(2));
      	  idiom.setExplain(c.getString(3));
      	  idiom.setPerson(c.getString(4));
      	  if (c.getInt(5) == 1) {
				idiom.setUsed(true);
			}else {
				idiom.setUsed(false);
			}
          list.add(idiom);
		}
		return list;
	}
	//查询特定朝代的人物成语
	public ArrayList<Idiom> getIdiomsByPerson(String dynastic) {
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		Cursor c = db.query(false, "my_idiom",new String[]{KEY_ID, KEY_IDIOM,KEY_PARAPHARE,KEY_DYNASTY,KEY_NAME,KEY_IS_APPARE}, KEY_DYNASTY + "="+"\'"+dynastic+"\'"+" and "+KEY_NAME+" is not null", null, null, null, null, null);
		Idiom idiom = null;
		ArrayList<Idiom> list = new ArrayList<Idiom>();
		while (c.moveToNext()) {
		  idiom = new Idiom();
		  idiom.setId(c.getInt(0));
      	  idiom.setIdiom(c.getString(1));
      	  idiom.setExplain(c.getString(2));
      	  idiom.setExplain(c.getString(3));
      	  idiom.setPerson(c.getString(4));
      	  if (c.getInt(5) == 1) {
				idiom.setUsed(true);
			}else {
				idiom.setUsed(false);
			}
          list.add(idiom);
		}
		return list;

	}

	//从数据库中取出某个朝代的需要猜测的成语(get)
//	public static ArrayList<Speculation> getIdiomBySpeculation(String dynastic) {
//		return null;
//	}
	
	//更新数据库
	public void updateIdiom(Idiom idiom){
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		ContentValues cv = new ContentValues();
    	cv.put("is_appare", 1);
    	String[] args = {String.valueOf(idiom.getIdiom())};
    	db.update("my_idiom", cv, "idiom=?",args);
		
	}
	
	
	//更新猜测的成语数据库
//	public static void updateSpeculation(Speculation speculation){
//		
//	}


	//获得所有关卡状态
	public ArrayList<Barrier> getBarrier() {
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		Cursor c = db.query(false, "checkpoint", new String[]{POINT, IS_PASS,SCORE,GRADE}, null, null, null, null, null, null);
		Barrier barrier = null;
		ArrayList<Barrier> list = new ArrayList<Barrier>();
		while (c.moveToNext()) {
			barrier = new Barrier();
			barrier.setId(c.getInt(0));
	      	if (c.getInt(1)==1) {
				barrier.setPass(true);
			}else {
				barrier.setPass(false);
			}
	      	barrier.setGrage(c.getInt(2));
	      	barrier.setStarNum(c.getInt(3));
	      	list.add(barrier);
			}
			return list;
		
	}
	
	//获得某个关卡的信息
	public Barrier getBarrierById(int barrierId) {
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		Cursor c = db.query(false, "checkpoint", new String[]{POINT, IS_PASS,SCORE,GRADE}, POINT + "=" + barrierId, null, null, null, null, null);
		Barrier barrier = null;
		while (c.moveToNext()) {
		barrier = new Barrier();
		barrier.setId(c.getInt(0));
	    if (c.getInt(1)==1) {
			barrier.setPass(true);
		}else {
			barrier.setPass(false);
		}
	    barrier.setGrage(c.getInt(2));
	    barrier.setStarNum(c.getInt(3));
		}
		return barrier;
	}
	
	//设置关卡状态
	public void setBarrierById(Barrier barrier) {
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		if (barrier.isPass()) {
			cv.put("is_pass", 1);
		}else {
			cv.put("is_pass", 0);
		}
    	cv.put("score", barrier.getGrage());
    	cv.put("grade", barrier.getStarNum());
    	String[] args = {String.valueOf(barrier.getId())};
    	db.update("checkpoint", cv, "point=?",args);
	}

	// 从数据库中取出道具1的数量
	public int getProperty1Num() {
		int num = -1;
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		Cursor c = db.query(true, "prop", new String[]{PROP1, PROP2,STARNUM}, null, null, null, null, null, null);
		while (c.moveToNext()) {
		num = c.getInt(0);
		}
		return num;

	}

	// 从数据库中取出道具2的数量
	public int getProperty2Num() {
		int num = -1;
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		Cursor c = db.query(true, "prop", new String[]{PROP1, PROP2,STARNUM}, null, null, null, null, null, null);
		while (c.moveToNext()) {
			num = c.getInt(1);
			}
		return num;

	}
	
	// 设置数据库中道具1的数量
	public void setProperty1Num(int num) {
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		ContentValues cv = new ContentValues();
    	cv.put("prop1_num", num);
    	db.update("prop", cv, null,null);
	}

	// 设置数据库中道具2的数量
	public void setProperty2Num(int num) {
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		ContentValues cv = new ContentValues();
    	cv.put("prop2_num", num);
    	db.update("prop", cv, null,null);
	}
	
	//获得剩余星星数
	public int getRestStarNum(){
		int num = -1;
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		Cursor c = db.query(true, "prop", new String[]{PROP1, PROP2,STARNUM}, null, null, null, null, null, null);
		while (c.moveToNext()) {
			num = c.getInt(2);
			}
		return num;
		
	}
	
	//设置剩余星星数
	public void setRestStarNum(int num){
		SQLiteDatabase db = dbAdalper.getWritableDatabase();
		ContentValues cv = new ContentValues();
    	cv.put("star_num", num);
    	db.update("prop", cv, null,null);
	}


}
