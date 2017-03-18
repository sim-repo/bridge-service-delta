package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonAutoDetect
@JsonDeserialize(as = ErrDefMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrDefMsg extends AContract{
	
	private Integer id;
	
	@Override
	public String getClazz() {
		return this.getClass().getName();
	}	
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
		
}
