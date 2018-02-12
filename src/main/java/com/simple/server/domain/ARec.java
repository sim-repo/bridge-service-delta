package com.simple.server.domain;


public abstract class ARec implements IRec{
	
	protected String senderId;
	protected String juuid;
	protected String eventId;
	
	@Override
	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
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
