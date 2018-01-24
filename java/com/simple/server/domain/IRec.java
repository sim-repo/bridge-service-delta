package com.simple.server.domain;

import java.io.Serializable;

import com.simple.server.config.ContentType;
import com.simple.server.domain.contract.IContract;

public interface IRec extends Serializable{
	void copyFrom(IContract msg) throws Exception;
	void format() throws Exception;
	void setResponseContentType(ContentType ct);
	String getEndpoint();	
	String getSenderId();	
	String getEventId();
	void setEventId(String eventId);
	public String getJuuid();
}
