package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.EndpointType;
import com.simple.server.domain.IRec;
import com.simple.server.domain.log.LogEventSetting;

@JsonAutoDetect
@JsonDeserialize(as = SubRouting.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubRouting extends AContract{
	
	@JsonProperty("id")
	int id;
	@JsonProperty("event_id")
	String eventId;;
	@JsonProperty("subscriber_id")
	String subscriberId;
	@JsonProperty("subscriber_handler")
	String subscriberHandler;	
	@JsonProperty("subscriber_store_class")
	String subscriberStoreClass;	

		
	@Override
	public String getClazz() {
		return SubRouting.class.getName();
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
	public String getSubscriberStoreClass() {
		return subscriberStoreClass;
	}
	public void setSubscriberStoreClass(String subscriberStoreClass) {
		this.subscriberStoreClass = subscriberStoreClass;
	}		
	@Override
	public void copyFrom(IRec rec) throws Exception{
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
