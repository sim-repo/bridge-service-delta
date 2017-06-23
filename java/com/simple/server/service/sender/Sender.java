package com.simple.server.service.sender;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.IContract;
import com.simple.server.util.ObjectConverter;

@Service("sender")
@Scope("singleton")
public class Sender {
	
	@Autowired
	private AppConfig appConfig;
	
	public void send(MessageChannel msgChannel, IContract msg) throws Exception {
		
		if (msgChannel==null || msg==null)
			return;
		
		msg.setServiceOutDatetime(new Date().toString());
		msg.setServiceIdFrom(appConfig.SERVICE_ID);		
		String json = ObjectConverter.objectToJson(msg);				
		msgChannel.send(MessageBuilder.withPayload( json ).setHeader(appConfig.LOG_HEADER_NAME, msg.getClass().getSimpleName()).build());
	}
}
