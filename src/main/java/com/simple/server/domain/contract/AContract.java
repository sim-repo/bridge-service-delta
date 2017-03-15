package com.simple.server.domain.contract;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.simple.server.config.AppConfig;
import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.config.OperationType;
import com.simple.server.domain.IRec;

@SuppressWarnings("serial")
public abstract class AContract implements IContract {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	AppConfig appConfig;
	
	protected String clazz;
	protected String juuid;
	protected String eventId;	
	protected String senderId;
	protected String endPointId;
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
	protected Boolean isDirectInsert; 
	
	@Override
	public AppConfig getAppConfig() throws Exception {
		return appConfig;
	}
	
	@Override
	public String getJuuid() {
		return juuid;
	}

	@Override
	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}
	
	@Override
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	@Override
	public EndpointType getSenderId() {
		return EndpointType.fromValue(senderId);
	}

	public void setSenderId(EndpointType senderId) {
		this.senderId = senderId.toValue();
	}

	@Override
	public EndpointType getEndPointId() {
		return EndpointType.fromValue(endPointId);
	}

	@Override
	public void setEndPointId(EndpointType endPointId) {
		this.endPointId = endPointId.toValue();
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

	@Override
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

	@Override
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

	@Override
	public String getResponseURI() {
		return responseURI;
	}

	@Override
	public void setResponseURI(String responseURI) {
		this.responseURI = responseURI;
	}
	
	@Override
	public String getResponseContractClass() {
		return responseContractClass;
	}

	public void setResponseContractClass(String responseContractClass) {
		this.responseContractClass = responseContractClass;
	}

	@Override
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

	@Override
	public OperationType getOperationType() {
		return operationType;
	}

	@Override
	public Boolean getIsDirectInsert() {
		return isDirectInsert;
	}
	
	@Override
	public void setIsDirectInsert(Boolean isDirectInsert) {
		this.isDirectInsert = new Boolean(isDirectInsert);
	}

	@Override
	public void copyFrom(IContract _msg) throws Exception{
		AContract msg = (AContract)_msg;
		
		this.setServiceOutDatetime(new SimpleDateFormat(AppConfig.DATEFORMAT).format(Calendar.getInstance().getTime()));		
		this.setJuuid(msg.getJuuid());
		this.setEndPointId(msg.getEndPointId());
		this.setSenderId(msg.getSenderId());
		this.setEventId(msg.getEventId());
		this.setResponseURI(msg.getResponseURI());
		this.setResponseContentType(msg.getResponseContentType());
		this.setResponseContractClass(msg.getResponseContractClass());				
		this.setMethodHandler(msg.getMethodHandler());
		this.setServiceRoleFrom(getAppConfig().ROLE_ID);
		this.setServiceIdFrom(getAppConfig().SERVICE_ID);			
		this.setMessageHeaderValue(this.getClass().getSimpleName());
		this.setMessageBodyValue(msg.toString());				
	}
	
	
	@Override
	public void copyFrom(IRec rec) throws Exception{
		// TODO Auto-generated method stub		
	}
}
