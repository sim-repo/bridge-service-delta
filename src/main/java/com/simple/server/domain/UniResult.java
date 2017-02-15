package com.simple.server.domain;

import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.domain.contract.IContract;

public class UniResult implements IRec{
	
	private String result;
	protected ContentType responseContentType = ContentType.JsonPlainText;
	protected String responseURI;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
	public EndpointType getEndpoint() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResponseContentType(ContentType responseContentType) {
		this.responseContentType = responseContentType;
	}

	public ContentType getResponseContentType() {
		return responseContentType;
	}

	public String getResponseURI() {
		return responseURI;
	}

	public void setResponseURI(String responseURI) {
		this.responseURI = responseURI;
	}
	
	
	

}
