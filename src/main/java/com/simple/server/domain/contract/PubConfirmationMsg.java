package com.simple.server.domain.contract;

import com.simple.server.domain.IRec;

public class PubConfirmationMsg extends AContract {

	
	String publisherHandler;
	
	
	public String getPublisherHandler() {
		return publisherHandler;
	}

	public void setPublisherHandler(String publisherHandler) {
		this.publisherHandler = publisherHandler;
	}

	@Override
	public String getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void copyFrom(IRec rec) {
		// TODO Auto-generated method stub
		
	}
	
	

}
