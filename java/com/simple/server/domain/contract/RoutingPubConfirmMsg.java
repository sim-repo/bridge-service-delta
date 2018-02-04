package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonAutoDetect
@JsonDeserialize(as = RoutingPubConfirmMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutingPubConfirmMsg extends AContract {

	
	private static final long serialVersionUID = 1L;
	protected int id;
	private Boolean useAuth; 
	
	@Override
	public String getClazz() {
		return this.getClass().getName();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getUseAuth() {
		return useAuth;
	}

	public void setUseAuth(Boolean useAuth) {
		this.useAuth = useAuth;
	}	
	
	
}
