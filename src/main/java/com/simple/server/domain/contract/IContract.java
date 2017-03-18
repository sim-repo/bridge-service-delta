package com.simple.server.domain.contract;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.AppConfig;
import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.config.ErrorType;
import com.simple.server.config.EventType;
import com.simple.server.config.OperationType;
import com.simple.server.domain.IRec;
import com.simple.server.util.BusMsgJsonDeserializer;

@JsonPropertyOrder({ "clazz" })
@JsonInclude(Include.NON_EMPTY)
@JsonDeserialize(using = BusMsgJsonDeserializer.class)
public interface IContract extends Serializable {

	static final long serialVersionUID = 1L;
	
	@JsonGetter("clazz")
	String getClazz();
	void copyFrom(IRec rec) throws Exception;
	void copyFrom(IContract msg) throws Exception;
	
	AppConfig getAppConfig() throws Exception;
	
	String getJuuid();	
	void setJuuid(String juuid);
	EventType getEventId();
	void setEventId(EventType eventId);
	EndpointType getEndPointId();
	void setEndPointId(EndpointType endPointId);
	EndpointType getPublisherId();
	void setPublisherId(EndpointType publisherId);
	EndpointType getSubscriberId();
	void setSubscriberId(EndpointType subscriberId);
	EndpointType getSenderId();
	void setSenderId(EndpointType senderId);
	
	OperationType getOperationType();
	void setOperationType(OperationType operationType);
			
	Boolean getIsDirectInsert();
	void setIsDirectInsert(Boolean direct);
	String getResponseURI();
	void setResponseURI(String responseURI);
	ContentType getResponseContentType();
	void setResponseContentType(ContentType contentType);
	String getResponseContractClass();
	String getMethodHandler();
	
	void setMessageBodyValue(String body);
	void setLogDatetime(String logDatetime);
	
	void setErrorId(ErrorType errorId);
	ErrorType getErrorId();
	String getDetails();
	void setDetails(String details);
	
}
