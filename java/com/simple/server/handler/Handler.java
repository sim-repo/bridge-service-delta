package com.simple.server.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simple.server.config.AppConfig;



@Component("busMsgHandler")
public class Handler {
	@Autowired
	private AppConfig appConfig;
	
	protected AppConfig getAppConfig(){
		return appConfig;
	}	
	
	public void handleJsonMsg(String json) throws Exception {						
		try{
			getAppConfig().getQueueDirty().put(json);
		}catch(Exception ex){
			ex.printStackTrace();
			getAppConfig().getLogger().warn("This is warn : " +ex.getMessage());
		}
	}	
}
