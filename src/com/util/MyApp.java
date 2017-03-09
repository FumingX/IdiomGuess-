package com.util;

import android.R;
import android.app.Application;
import android.media.MediaPlayer;

public class MyApp extends Application {
    private boolean haveAudio = true,haveBackMusic= true;
    public MediaPlayer startPlayer = null,shuafenPlayer = null,chuangguanPlayer = null,duizhanPlayer = null;
	public boolean isHaveAudio() {
		return haveAudio;
	}
	public void setHaveAudio(boolean haveAudio) {
		this.haveAudio = haveAudio;
	}
	public boolean isHaveBackMusic() {
		return haveBackMusic;
	}
	public void setHaveBackMusic(boolean haveBackMusic) {
		this.haveBackMusic = haveBackMusic;
	}

	public void stopPlayMusic(){
	     if(startPlayer!=null){
	    	 startPlayer.pause();
	     }
	     if(shuafenPlayer!=null){
	    	 shuafenPlayer.pause();
	     }
	     if(duizhanPlayer!=null){
	    	 duizhanPlayer.pause();
	     }
	     if(chuangguanPlayer!=null){
	    	 chuangguanPlayer.pause();
	     }
		
	}
	
}
