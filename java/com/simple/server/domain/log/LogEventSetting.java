package com.simple.server.domain.log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.config.EventType;
import com.simple.server.domain.contract.IContract;

@JsonAutoDetect
@JsonDeserialize(as = LogEventSetting.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogEventSetting extends ALogRec{
	int id;	
	String subscriberId;
	String subscriberHandler;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public EndpointType getSubscriberId() {
		return EndpointType.fromValue(subscriberId);
	}
	public void setSubscriberId(EndpointType subscriberId) {
		this.subscriberId = subscriberId.toString();
	}
	public String getSubscriberHandler() {
		return subscriberHandler;
	}
	public void setSubscriberHandler(String subscriberHandler) {
		this.subscriberHandler = subscriberHandler;
	}
	@Override
	public void copyFrom(IContract msg) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void format() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setResponseContentType(ContentType ct) {
		// TODO Auto-generated method stub
		
	}
	
	
}
