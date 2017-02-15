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
		System.out.println("bridge::::" + json);
		getAppConfig().getQueueDirty().put(json);
	}	
}
