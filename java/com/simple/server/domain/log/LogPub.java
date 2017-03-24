package com.simple.server.domain.log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.config.EventType;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.BusPubMsg;

@JsonAutoDetect
@JsonDeserialize(as = LogPub.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogPub extends ALogRec{
	
	protected int id;
	
	protected String juuid;
	
	protected String serviceIdFrom;

	protected String serviceIdTo;
	
	protected String serviceRoleFrom;

	protected String serviceRoleTo;
	
	protected String messageHeaderValue;
	
	protected String serviceOutDatetime;

	protected String serviceInDatetime;

	protected String requestInDatetime;
	
	protected String logDatetime;

	protected String loggerId;

	protected String methodHandler;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJuuid() {
		return juuid;
	}

	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}

	public String getServiceIdFrom() {
		return serviceIdFrom;
	}

	public void setServiceIdFrom(String serviceIdFrom) {
		this.serviceIdFrom = serviceIdFrom;
	}

	public String getServiceIdTo() {
		return serviceIdTo;
	}

	public void setServiceIdTo(String serviceIdTo) {
		this.serviceIdTo = serviceIdTo;
	}

	public String getServiceRoleFrom() {
		return serviceRoleFrom;
	}

	public void setServiceRoleFrom(String serviceRoleFrom) {
		this.serviceRoleFrom = serviceRoleFrom;
	}

	public String getServiceRoleTo() {
		return serviceRoleTo;
	}

	public void setServiceRoleTo(String serviceRoleTo) {
		this.serviceRoleTo = serviceRoleTo;
	}

	public String getMessageHeaderValue() {
		return messageHeaderValue;
	}

	public void setMessageHeaderValue(String messageHeaderValue) {
		this.messageHeaderValue = messageHeaderValue;
	}

	public String getServiceOutDatetime() {
		return serviceOutDatetime;
	}

	public void setServiceOutDatetime(String serviceOutDatetime) {
		this.serviceOutDatetime = serviceOutDatetime;
	}

	public String getServiceInDatetime() {
		return serviceInDatetime;
	}

	public void setServiceInDatetime(String serviceInDatetime) {
		this.serviceInDatetime = serviceInDatetime;
	}

	public String getRequestInDatetime() {
		return requestInDatetime;
	}

	public void setRequestInDatetime(String requestInDatetime) {
		this.requestInDatetime = requestInDatetime;
	}

	public String getLogDatetime() {
		return logDatetime;
	}

	public void setLogDatetime(String logDatetime) {
		this.logDatetime = logDatetime;
	}

	public String getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(String loggerId) {
		this.loggerId = loggerId;
	}

	public String getMethodHandler() {
		return methodHandler;
	}

	public void setMethodHandler(String methodHandler) {
		this.methodHandler = methodHandler;
	}

	@Override
	public void setResponseContentType(ContentType ct) {
	}

	
	@Override
	public void copyFrom(IContract msg) throws Exception {
		if(!(msg instanceof BusPubMsg))
			throw new Exception("msg must be instance of PubMsg");
		
		BusPubMsg pubmsg = (BusPubMsg)msg;
					
		if (pubmsg.getJuuid() != null)
			this.setJuuid(pubmsg.getJuuid());
		
		if (pubmsg.getServiceIdFrom() != null)
			this.setServiceIdFrom(pubmsg.getServiceIdFrom());
		
		if (pubmsg.getServiceIdTo()!= null)
			this.setServiceIdTo(pubmsg.getServiceIdTo());
		
		if (pubmsg.getServiceRoleFrom() != null)
			this.setServiceRoleFrom(pubmsg.getServiceRoleFrom());
		
		if (pubmsg.getServiceRoleTo() != null)
			this.setServiceRoleTo(pubmsg.getServiceRoleTo());
		
		if (pubmsg.getMessageHeaderValue()!= null)
			this.setMessageHeaderValue(pubmsg.getMessageHeaderValue());
				
		if (pubmsg.getEndPointId() != null)
			this.setSenderId(pubmsg.getSenderId());
				
		if (pubmsg.getServiceOutDatetime() != null)
			this.setServiceOutDatetime(pubmsg.getServiceOutDatetime());
		
		if (pubmsg.getServiceInDatetime() != null)
			this.setServiceInDatetime(pubmsg.getServiceInDatetime());
		
		if (pubmsg.getRequestInDatetime() != null)
			this.setRequestInDatetime(pubmsg.getRequestInDatetime());
		
		if (pubmsg.getLogDatetime() != null)
			this.setLogDatetime(pubmsg.getLogDatetime());
		
		if (pubmsg.getLoggerId() != null)
			this.setLoggerId(pubmsg.getLoggerId());
		
		if (pubmsg.getMethodHandler() != null)
			this.setMethodHandler(pubmsg.getMethodHandler());
		
		if (pubmsg.getEventId() != null)
			this.setEventId(pubmsg.getEventId());
	}

	@Override
	public void format() throws Exception {
	}

	
}
