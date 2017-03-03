package com.simple.server.domain.contract;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
	void copyFrom(IRec rec);
	
	String getJuuid();
	String getEventId();
	EndpointType getEndPointId();
	OperationType getOperationType();
	void setResponseURI(String responseURI);
}
