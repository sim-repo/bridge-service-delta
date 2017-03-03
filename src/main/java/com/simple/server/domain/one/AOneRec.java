package com.simple.server.domain.one;

import com.simple.server.config.EndpointType;

public abstract class AOneRec implements IOneRec{
	
	protected EndpointType senderId;
	
	@Override
	public EndpointType getSenderId() {
		return senderId;
	}

	public void setSendeId(EndpointType senderId) {
		this.senderId = senderId;
	}
}
