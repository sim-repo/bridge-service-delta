package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.EndpointType;

@JsonAutoDetect
@JsonDeserialize(as = PubErrRouting.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PubErrRouting extends AContract{

	@JsonProperty("id")
	int id;
	@JsonProperty("event_id")
	String eventId;;
	@JsonProperty("publisher_id")
	String publisherId;
	@JsonProperty("publisher_handler")
	String publisherHandler;
	@JsonProperty("publisher_store_class")
	String publisherStoreClass;
	
	@Override
	public String getClazz() {
		return PubErrRouting.class.getName();
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
	public EndpointType getPublisherId() {
		return EndpointType.valueOf(publisherId);
	}
	public void setPublisherId(EndpointType publisherId) {
		this.publisherId = publisherId.toString();
	}
	public String getPublisherHandler() {
		return publisherHandler;
	}
	public void setPublisherHandler(String publisherHandler) {
		this.publisherHandler = publisherHandler;
	}
	public String getPublisherStoreClass() {
		return publisherStoreClass;
	}
	public void setPublisherStoreClass(String publisherStoreClass) {
		this.publisherStoreClass = publisherStoreClass;
	}

}
