package com.simple.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.simple.server.factory.TaskRunner;

@Component
public class PostInit implements ApplicationListener<ContextRefreshedEvent> {
	  
	@Autowired
	TaskRunner taskRunner;	  
	
	@Autowired
	AppConfig appConfig;	  
	  
	@Value("${queueSizeDirty.int.property :100}")
	private int queueSizeDirty;
	
	@Value("${queueSizeRead.int.property :100}")
	private int queueSizeRead;
	  
	@Value("${queueSizeWrite.int.property :100}")
	private int queueSizeWrite;
	  
  
	  
	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		
		  appConfig.initQueueDirty(queueSizeDirty);
		  appConfig.initRead(queueSizeRead);
		  appConfig.initWrite(queueSizeWrite);
		  
		  taskRunner.initProcessing();
	}
}