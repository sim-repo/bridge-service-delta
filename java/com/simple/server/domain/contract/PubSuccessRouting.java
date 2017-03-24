package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.EndpointType;

@JsonAutoDetect
@JsonDeserialize(as = PubSuccessRouting.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PubSuccessRouting extends AContract{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	protected int id;

	protected String bodyContentType;
	
	@Override
	public String getClazz() {
		return PubSuccessRouting.class.getName();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public EndpointType getBodyContentType() {
		return EndpointType.fromValue(bodyContentType);
	}
	
	public void setBodyContentType(EndpointType contentType) {
		this.bodyContentType = contentType.toValue();
	}
		
}
