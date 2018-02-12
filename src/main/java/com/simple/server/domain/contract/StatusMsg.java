package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.domain.IRec;


@JsonAutoDetect
@JsonDeserialize(as = StatusMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusMsg extends AContract{
	         
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String message;
	
	
	@Override
	public String getClazz() {
		return this.getClass().getName();
	}	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
