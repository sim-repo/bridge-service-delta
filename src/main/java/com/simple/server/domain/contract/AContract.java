package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.config.OperationType;

@SuppressWarnings("serial")
public abstract class AContract implements IContract {

	private static final long serialVersionUID = 1L;
	
	
	protected String clazz;
	protected String juuid;
	protected String eventId;
	protected EndpointType senderId;
	protected EndpointType endPointId;
	protected OperationType operationType;
	protected String serviceIdFrom;
	protected String serviceIdTo;
	protected String serviceRoleFrom;
	protected String serviceRoleTo;	
	protected String serviceOutDatetime;	
	protected String serviceInDatetime;	
	protected String requestInDatetime;	
	protected String messageHeaderValue;
	protected String messageBodyValue;	
	protected String logDatetime;	
	protected String loggerId;	
	protected String methodHandler;	
	protected String responseURI;	
	protected ContentType responseContentType  = ContentType.JsonPlainText; 	
	protected String responseContractClass;

	
	@Override
	public String getJuuid() {
		return juuid;
	}

	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public EndpointType getSenderId() {
		return senderId;
	}

	public void setSenderId(EndpointType senderId) {
		this.senderId = senderId;
	}

	public EndpointType getEndPointId() {
		return endPointId;
	}

	public void setEndPointId(EndpointType endPointId) {
		this.endPointId = endPointId;
	}

	public String getServiceIdFrom() {
		return serviceIdFrom;
	}

	public void setServiceIdFrom(String serviceIdFrom) {
		this.serviceIdFrom = serviceIdFrom;
	}

	public String getServiceOutDatetime() {
		return serviceOutDatetime;
	}

	public void setServiceOutDatetime(String serviceOutDatetime) {
		this.serviceOutDatetime = serviceOutDatetime;
	}

	public String getServiceIdTo() {
		return serviceIdTo;
	}

	public void setServiceIdTo(String serviceIdTo) {
		this.serviceIdTo = serviceIdTo;
	}

	public String getServiceInDatetime() {
		return serviceInDatetime;
	}

	public void setServiceInDatetime(String serviceInDatetime) {
		this.serviceInDatetime = serviceInDatetime;
	}

	public String getMessageHeaderValue() {
		return messageHeaderValue;
	}

	public void setMessageHeaderValue(String messageHeaderValue) {
		this.messageHeaderValue = messageHeaderValue;
	}

	public String getMessageBodyValue() {
		return messageBodyValue;
	}

	public void setMessageBodyValue(String messageBodyValue) {
		this.messageBodyValue = messageBodyValue;
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

	public String getResponseURI() {
		return responseURI;
	}

	@Override
	public void setResponseURI(String responseURI) {
		this.responseURI = responseURI;
	}

	public String getResponseContractClass() {
		return responseContractClass;
	}

	public void setResponseContractClass(String responseContractClass) {
		this.responseContractClass = responseContractClass;
	}

	public ContentType getResponseContentType() {
		return responseContentType;
	}

	public void setResponseContentType(ContentType responseContentType) {
		this.responseContentType = responseContentType;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getRequestInDatetime() {
		return requestInDatetime;
	}

	public void setRequestInDatetime(String requestInDatetime) {
		this.requestInDatetime = requestInDatetime;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	
}
