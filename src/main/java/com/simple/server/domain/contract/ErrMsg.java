package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.EndpointType;
import com.simple.server.config.ErrorType;


@JsonAutoDetect
@JsonDeserialize(as = ErrMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrMsg extends AContract{
	
	private Integer id;
	private ErrorType errorId;
	private String details;
	private String subscriberId;
	private String subscriberHandler;
	private String storeClass;	
	
	@Override
	public String getClazz() {
		return this.getClass().getName();
	}	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ErrorType getErrorId() {
		return errorId;
	}
	public void setErrorId(ErrorType errorId) {
		this.errorId = errorId;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getStoreClass() {
		return storeClass;
	}
	public void setStoreClass(String storeClass) {
		this.storeClass = storeClass;
	}
	public EndpointType getSubscriberId() {
		return EndpointType.fromValue(this.subscriberId);
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
	@Override
	public void copyFrom(IContract _msg) throws Exception {
		if(_msg == null)
			return;	
	}
	
}
