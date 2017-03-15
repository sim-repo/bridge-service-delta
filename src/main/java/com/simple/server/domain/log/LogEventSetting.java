package com.simple.server.domain.log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.EndpointType;

@JsonAutoDetect
@JsonDeserialize(as = LogEventSetting.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogEventSetting {
	int id;
	String eventId;;
	String subscriberId;
	String subscriberHandler;
	
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
	
	
}
