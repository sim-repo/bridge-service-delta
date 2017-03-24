package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonAutoDetect
@JsonDeserialize(as = ConfirmMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmMsg extends AContract{
	
	private Integer id;	
	private String storeClass;		
	
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
	public String getStoreClass() {
		return storeClass;
	}
	public void setStoreClass(String storeClass) {
		this.storeClass = storeClass;
	}

	@Override
	public void copyFrom(IContract _msg) throws Exception {
		if(_msg==null)
			throw new Exception("ConfirmMsg, copyFrom, _msg is null");
		this.setSubscriberId(_msg.getSenderId());
		
		if(_msg instanceof StatusMsg){
			StatusMsg status = (StatusMsg)_msg;
			this.setErrorId(status.getErrorId());
			this.setDetails(status.getDetails());
		}
		
		this.setSubscriberHandler(_msg.getMethodHandler());
		this.setSubscriberStoreClass(_msg.getResponseContractClass());
		
		super.copyFrom(_msg);
	}	
	
	
}


