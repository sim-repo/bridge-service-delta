package com.simple.server.domain.log;

import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.domain.contract.IContract;

public class LogReply extends ALogRec{
	
	protected int id;
	
	protected String juuid;
	
	protected String responseURI;

	protected ContentType responseContentType;

	protected String responseContractClass;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getJuuid() {
		return juuid;
	}

	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}

	public String getResponseURI() {
		return responseURI;
	}

	public void setResponseURI(String responseURI) {
		this.responseURI = responseURI;
	}

	public ContentType getResponseContentType() {
		return responseContentType;
	}

	@Override
	public void setResponseContentType(ContentType responseContentType) {
		this.responseContentType = responseContentType;
	}

	public String getResponseContractClass() {
		return responseContractClass;
	}

	public void setResponseContractClass(String responseContractClass) {
		this.responseContractClass = responseContractClass;
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
	public EndpointType getEndpoint(){
		return EndpointType.LOG;
	}
	
	
}
