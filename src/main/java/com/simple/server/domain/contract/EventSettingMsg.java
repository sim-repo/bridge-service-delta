package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.domain.IRec;
import com.simple.server.domain.log.LogEventSetting;

@JsonAutoDetect
@JsonDeserialize(as = EventSettingMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventSettingMsg extends AContract{
	
	@JsonProperty("id")
	int id;
	@JsonProperty("event_id")
	String eventId;;
	@JsonProperty("subscriber_id")
	String subscriberId;
	@JsonProperty("subscriber_handler")
	String subscriberHandler;	
	
	@Override
	public String getClazz() {
		return EventSettingMsg.class.getName();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getSubscriberHandler() {
		return subscriberHandler;
	}
	public void setSubscriberHandler(String subscriberHandler) {
		this.subscriberHandler = subscriberHandler;
	}
	@Override
	public void copyFrom(IRec rec) {
		if (rec == null)
			return;
		if (rec instanceof LogEventSetting) {
			LogEventSetting les = (LogEventSetting) rec;
			this.setEventId(les.getEventId());
			this.setSubscriberId(les.getSubscriberId());
			this.setSubscriberHandler(les.getSubscriberHandler());
			
		}
		
	}
	
	
	
}
