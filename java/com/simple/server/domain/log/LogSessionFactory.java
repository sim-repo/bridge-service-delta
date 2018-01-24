package com.simple.server.domain.log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.ContentType;
import com.simple.server.domain.contract.IContract;

@JsonAutoDetect
@JsonDeserialize(as = LogSessionFactory.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogSessionFactory extends ALogRec{

	private int id;
	private String endpointId;
	private String strSessionFactory;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndpointId() {
		return endpointId;
	}

	public void setEndpointId(String endpointId) {
		this.endpointId = endpointId;
	}

	public String getStrSessionFactory() {
		return strSessionFactory;
	}

	public void setStrSessionFactory(String strSessionFactory) {
		this.strSessionFactory = strSessionFactory;
	}

	@Override
	public void copyFrom(IContract msg) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void format() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResponseContentType(ContentType ct) {
		// TODO Auto-generated method stub
		
	}

}
