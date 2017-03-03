package com.simple.server.domain.nav;

import com.simple.server.config.EndpointType;
import com.simple.server.domain.log.ILogRec;

public abstract class ANavRec implements INavRec{
	
	protected EndpointType senderId;
	
	@Override
	public EndpointType getSenderId() {
		return senderId;
	}

	public void setSendeId(EndpointType senderId) {
		this.senderId = senderId;
	}
}
