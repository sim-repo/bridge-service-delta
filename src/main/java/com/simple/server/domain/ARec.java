package com.simple.server.domain;

import com.simple.server.config.EndpointType;
import com.simple.server.config.EventType;

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
	public EventType getEventId() {
		return EventType.valueOf(eventId);
	}
	@Override
	public void setEventId(EventType eventId) {
		this.eventId = eventId.toString();
	}
}
