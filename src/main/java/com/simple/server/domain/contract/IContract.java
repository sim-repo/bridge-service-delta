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
	
	String getJuuid();	
	String getEventId();
	EndpointType getEndPointId();
	OperationType getOperationType();
	Boolean getIsDirectInsert();	
	EndpointType getSenderId();
	String getResponseURI();
	ContentType getResponseContentType();
	String getResponseContractClass();
	String getMethodHandler();
	AppConfig getAppConfig() throws Exception;
	
	void setEndPointId(EndpointType endPointId);
	void setResponseURI(String responseURI);
	void setJuuid(String juuid);
	void setIsDirectInsert(Boolean direct);
	void setMessageBodyValue(String body);
}
