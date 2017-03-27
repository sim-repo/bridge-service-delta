package com.simple.server.domain;

import com.simple.server.config.EndpointType;

public abstract class ARec implements IRec{
	
	protected String senderId;
	protected String juuid;
	protected String eventId;
	
	@Override
	public EndpointType getSenderId() {
		return EndpointType.fromValue(senderId);
	}

	public void setSenderId(EndpointType senderId) {
		this.senderId = senderId.toValue();
	}	
	
	@Override
	public String getJuuid(){
		return juuid;
	}
	@Override
	public String getEventId() {
		return eventId;
	}
	@Override
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
}
