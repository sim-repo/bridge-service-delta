package com.simple.server.domain.log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.config.EventType;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.RoutingPubConfirmMsg;
import com.simple.server.domain.contract.BusPubMsg;


@JsonAutoDetect
@JsonDeserialize(as = LogPubConfirmation.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogPubConfirmation extends ALogRec{

	protected int id;		
	protected String publisherHandler;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getPublisherHandler() {
		return publisherHandler;
	}
	public void setPublisherHandler(String publisherHandler) {
		this.publisherHandler = publisherHandler;
	}

	@Override
	public void copyFrom(IContract msg) throws Exception {
		
		if(!(msg instanceof RoutingPubConfirmMsg))
			throw new Exception("msg must be instance of PubConfirmationMsg");
		
		RoutingPubConfirmMsg pubmsg = (RoutingPubConfirmMsg)msg;
					
		if(pubmsg.getSenderId()!= null)
			this.setSenderId(pubmsg.getSenderId());
		
		if(pubmsg.getEventId()!= null)
			this.setEventId(pubmsg.getEventId());
		
		if(pubmsg.getPublisherHandler()!= null)
			this.setPublisherHandler(pubmsg.getPublisherHandler());
	}

	@Override
	public void format() throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void setResponseContentType(ContentType ct) {
		// TODO Auto-generated method stub		
	}

}
