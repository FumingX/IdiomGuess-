package com.example.idiomguess2;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.idiomguess2.PullListView.OnRefreshListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FriendActivity extends Activity {

private PullListView pullListView;
	
	//private List<String> dataList, dataList2;
	private FriendAdapter adapter;
	private SearchAdapter adapter2;
	private MessageAdapter adapter3;
	private LinearLayout upblankLayout, titleLayout, downblankLayout, splitLayout, opeLayout, findUserLayout;
	private RelativeLayout operateLayout, searchLayout, detailLayout, messageLayout;
	private int screenWidth, screenHeight;
	private ArrayList<HashMap<String,String>> myList;
	private TextView userNick, userLevel, nickname, gamelevel, nickFind, messageSend;
	private Button goreturn, query, message, closeSearch, find, closeDetail, closeMessage;
	private ListView search, messageList;
	private EditText findUser;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend);
        
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）  
		
        pullListView = (PullListView) this.findViewById(R.id.friendList);
        
        searchLayout = (RelativeLayout)findViewById(R.id.searchLayout);
        searchLayout.setVisibility(View.GONE);
        
        detailLayout = (RelativeLayout)findViewById(R.id.detailLayout);
        detailLayout.setVisibility(View.GONE);
        
        messageLayout = (RelativeLayout)findViewById(R.id.messageLayout);
        messageLayout.setVisibility(View.GONE);
        
        upblankLayout = (LinearLayout)findViewById(R.id.upblankLayout);
        upblankLayout.setLayoutParams(new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT,screenHeight*2/11));
        titleLayout = (LinearLayout)findViewById(R.id.titleLayout);
        titleLayout.setLayoutParams(new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT,screenHeight/10));
        
        nickname = (TextView)findViewById(R.id.nickname);
        nickname.setTextAppearance(FriendActivity.this, R.style.textSize);
        gamelevel = (TextView)findViewById(R.id.gamelevel);
        gamelevel.setTextAppearance(FriendActivity.this, R.style.textSize);
        
        downblankLayout = (LinearLayout)findViewById(R.id.downblankLayout);
        downblankLayout.setLayoutParams(new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT,screenHeight/6));
        splitLayout = (LinearLayout)findViewById(R.id.splitLayout);
        splitLayout.setLayoutParams(new LinearLayout.LayoutParams(
        		screenWidth/10,ViewGroup.LayoutParams.WRAP_CONTENT));
        operateLayout = (RelativeLayout)findViewById(R.id.operateLayout);
        operateLayout.setLayoutParams(new LinearLayout.LayoutParams(
        		ViewGroup.LayoutParams.MATCH_PARENT,screenHeight/6));
        
        goreturn = (Button)findViewById(R.id.goreturn);
        RelativeLayout.LayoutParams goreRl = new RelativeLayout.LayoutParams(
        		screenHeight/10,screenHeight/10);
        goreRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
        goreRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        goreturn.setLayoutParams(goreRl);
        
        search = (ListView)findViewById(R.id.search);
        RelativeLayout.LayoutParams searchRl = new RelativeLayout.LayoutParams(
        		screenWidth*2/5,screenHeight*3/7);
        searchRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
        searchRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        searchRl.topMargin = screenHeight/5;
        search.setLayoutParams(searchRl);
        
        messageList = (ListView)findViewById(R.id.messageList);
        RelativeLayout.LayoutParams messageRl = new RelativeLayout.LayoutParams(
        		screenWidth*2/5,screenHeight*4/7);
        messageRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
        messageRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        messageRl.topMargin = screenHeight/5;
        messageList.setLayoutParams(messageRl);
        
        closeSearch = (Button)findViewById(R.id.closeSearch);
        RelativeLayout.LayoutParams closeSearchRl = new RelativeLayout.LayoutParams(
        		screenHeight/10,screenHeight/10);
        closeSearchRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.searchResult);
        closeSearchRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        closeSearchRl.topMargin = screenHeight/10;
        closeSearchRl.leftMargin = screenHeight/20;
        closeSearch.setLayoutParams(closeSearchRl);
        
        closeDetail = (Button)findViewById(R.id.closeDetail);
        RelativeLayout.LayoutParams closeDetailRl = new RelativeLayout.LayoutParams(
        		screenHeight/10,screenHeight/10);
        closeDetailRl.addRule(RelativeLayout.ALIGN_LEFT,R.id.detailMessage);
        closeDetailRl.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        closeDetailRl.topMargin = screenHeight/10;
        closeDetailRl.leftMargin = screenHeight/20;
        closeDetail.setLayoutParams(closeDetailRl);
        
        findUserLayout = (LinearLayout)findViewById(R.id.findUserLayout);
        RelativeLayout.LayoutParams findUserRl = new RelativeLayout.LayoutParams(
        		screenWidth*2/5,ViewGroup.LayoutParams.WRAP_CONTENT);
        findUserRl.addRule(RelativeLayout.BELOW,R.id.search);
        findUserRl.addRule(RelativeLayout.CENTER_HORIZONTAL);
        findUserRl.topMargin = screenHeight/20;
        findUserLayout.setLayoutParams(findUserRl);
        
        findUser = (EditText)findViewById(R.id.findUser);
        LinearLayout.LayoutParams findUserEtRl = new LinearLayout.LayoutParams(
        		screenWidth*12/35,ViewGroup.LayoutParams.WRAP_CONTENT);
        findUser.setLayoutParams(findUserEtRl);
        
        query = (Button)findViewById(R.id.findBt);
        query.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				searchLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(FriendActivity.this,R.anim.fade_in);
				searchLayout.startAnimation(myAnimation);
				
				
			}
		});
        
        message = (Button)findViewById(R.id.messageBt);
        message.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				messageLayout.setVisibility(View.VISIBLE);
				Animation myAnimation = AnimationUtils.loadAnimation(FriendActivity.this,R.anim.fade_in);
				messageLayout.startAnimation(myAnimation);
				messageList.setAdapter(adapter3);
			}
		});
        
        closeSearch = (Button)findViewById(R.id.closeSearch);
        closeSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//dataList2.clear();
				//adapter2.notifyDataSetChanged();
				Animation myAnimation = AnimationUtils.loadAnimation(FriendActivity.this,R.anim.fade_out);
				searchLayout.startAnimation(myAnimation);
				searchLayout.setVisibility(View.GONE);
			}
		});
        
        closeDetail = (Button)findViewById(R.id.closeDetail);
        closeDetail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation myAnimation = AnimationUtils.loadAnimation(FriendActivity.this,R.anim.fade_out);
				detailLayout.startAnimation(myAnimation);
				detailLayout.setVisibility(View.GONE);
			}
		});
        
        closeMessage = (Button)findViewById(R.id.closeMessage);
        closeMessage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation myAnimation = AnimationUtils.loadAnimation(FriendActivity.this,R.anim.fade_out);
				messageLayout.startAnimation(myAnimation);
				messageLayout.setVisibility(View.GONE);
				
				
			}
		});
         
        find = (Button)findViewById(R.id.find);
        find.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				search.setAdapter(adapter2);
			}
		});
        
        myList=new ArrayList<HashMap<String,String>>();
        for(int i=0;i<15;i++){
        	HashMap<String,String> map=new HashMap<String,String>();
        	map.put("button", "btn:"+i);
        	myList.add(map);
        }
        
        adapter=new FriendAdapter(this); 
        adapter2 = new SearchAdapter(this);
        adapter3 = new MessageAdapter(this);
        
        pullListView.setAdapter(adapter);
        
        
//        dataList = new ArrayList<String>();
//		for (int i = 0; i < 1; i++) {
//			dataList.add("Item data "+i);
//		}
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
//		pullListView.setAdapter(adapter);
//		
		//下拉刷新监听器
		pullListView.setonRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Thread.sleep(2000);
//							dataList.clear();
//							for (int i = 0; i < 5; i++) {
//								dataList.add("Item data "+i);
//							}
							myHandler.sendEmptyMessage(0);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
    }
    
    private Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pullListView.onRefreshComplete();
		}
    };
    
    public class FriendAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private Button  query, delete;

		public FriendAdapter(Context c) {
			this.inflater = LayoutInflater.from(c);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return myList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View myView=inflater.inflate(R.layout.items, null);  
			
			userNick = (TextView)myView.findViewById(R.id.userNick);
			userNick.setTextAppearance(FriendActivity.this, R.style.textSize);
			userNick.setLayoutParams(new LinearLayout.LayoutParams(
					screenWidth/3,ViewGroup.LayoutParams.WRAP_CONTENT));
			
			userLevel = (TextView)myView.findViewById(R.id.userLevel);
			userLevel.setTextAppearance(FriendActivity.this, R.style.textSize);
			userLevel.setLayoutParams(new LinearLayout.LayoutParams(
					screenWidth/3,ViewGroup.LayoutParams.WRAP_CONTENT));
			
			opeLayout = (LinearLayout)myView.findViewById(R.id.opeLayout);
			opeLayout.setLayoutParams(new LinearLayout.LayoutParams(
					screenWidth/3,ViewGroup.LayoutParams.WRAP_CONTENT));
			
			query = (Button)myView.findViewById(R.id.readFri);
			query.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					detailLayout.setVisibility(View.VISIBLE);
					Animation myAnimation = AnimationUtils.loadAnimation(FriendActivity.this,R.anim.fade_in);
					detailLayout.startAnimation(myAnimation);
				}
			});
			
			delete = (Button) myView.findViewById(R.id.deleteFri);
			delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					AlertDialog alertDialog = new AlertDialog.Builder(
							FriendActivity.this).create();
					alertDialog.setTitle("游戏提示：");
					alertDialog.setMessage("确定要删除该好友？");

					alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
							"取消", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO 自动生成的方法存根

								}
							});
					alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
							"确定", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO 自动生成的方法存根

								}
							});
					alertDialog.show();
				}
			});
			return myView;
		}

	}
    
    public class SearchAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private Button  addFriend;

		public SearchAdapter(Context c) {
			this.inflater = LayoutInflater.from(c);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return myList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View myView=inflater.inflate(R.layout.items2, null);  
			
			nickFind = (TextView)myView.findViewById(R.id.nickFind);
			nickFind.setTextAppearance(FriendActivity.this, R.style.textSize);
			
			addFriend = (Button)myView.findViewById(R.id.addFriend);
			addFriend.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					
				}
			});
			
			return myView;
		}

	}
    
    public class MessageAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private Button  accept, cancle;

		public MessageAdapter(Context c) {
			this.inflater = LayoutInflater.from(c);
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return myList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View myView=inflater.inflate(R.layout.items3, null);  
			
			messageSend = (TextView)myView.findViewById(R.id.messageSend);
			messageSend.setTextAppearance(FriendActivity.this, R.style.textSize);
			
			accept = (Button)myView.findViewById(R.id.accept);
			accept.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
				
				}
			});
			
			cancle = (Button)myView.findViewById(R.id.cancle);
			cancle.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					Toast.makeText(FriendActivity.this, "删除行数行数" + position, Toast.LENGTH_LONG).show();
				}
			});
			return myView;
		}

	}
}
