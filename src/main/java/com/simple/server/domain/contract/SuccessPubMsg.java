package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.EndpointType;

@JsonAutoDetect
@JsonDeserialize(as = SuccessMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessMsg extends AContract{
	
	private Integer id;
	private String subscriberId;
	private String storeClass;	
	private String subscriberHandler;
	private String subscriberStoreClass;	
	
	@Override
	public String getClazz() {
		return this.getClass().getName();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public EndpointType getSubscriberId() {
		return EndpointType.valueOf(subscriberId);
	}
	public void setSubscriberId(EndpointType subscriberId) {
		this.subscriberId = subscriberId.toValue();
	}
	public String getStoreClass() {
		return storeClass;
	}
	public void setStoreClass(String storeClass) {
		this.storeClass = storeClass;
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
