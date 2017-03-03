package com.simple.server.domain.log;

import com.simple.server.config.EndpointType;

public abstract class ALogRec implements ILogRec {
	
	protected EndpointType senderId;
	
	@Override
	public EndpointType getSenderId() {
		return senderId;
	}

	public void setSenderId(EndpointType senderId) {
		this.senderId = senderId;
	}
}
