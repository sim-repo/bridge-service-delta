package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonAutoDetect
@JsonDeserialize(as = SubErrRouting.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubErrRouting extends AContract{

	@JsonProperty("id")
	int id;
	Boolean enabled;
	
	@Override
	public String getClazz() {
		return SubErrRouting.class.getName();
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
