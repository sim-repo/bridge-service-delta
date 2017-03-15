package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.EndpointType;


@JsonAutoDetect
@JsonDeserialize(as = SubErrRouting.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubErrRouting extends AContract{

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
		return SubErrRouting.class.getName();
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
		return EndpointType.valueOf(subscriberId);
	}
	public void setSubscriberId(EndpointType subscriberId) {
		this.subscriberId = subscriberId.toValue();
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
}
