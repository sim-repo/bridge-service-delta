package com.simple.server.domain;

import java.util.Map;

import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.domain.contract.SubRouting;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.TagRequestMsg;

public class UniRequest extends ARec implements IRec{
	
	private StringBuilder query;

	protected ContentType responseContentType = ContentType.JsonPlainText;
	
	protected String responseURI;
	
	protected String senderId;
	
	@Override
	public EndpointType getSenderId() {
		return EndpointType.fromValue(senderId);
	}

	public void setSenderId(EndpointType senderId) {
		this.senderId = senderId.toValue();
	}

	public String getQuery() {
		return query.toString();
	}

	public void setQuery(StringBuilder query) {
		this.query = query;
	}

	@Override
	public void copyFrom(IContract msg) throws Exception {
		if(msg instanceof TagRequestMsg){
			TagRequestMsg tag = (TagRequestMsg)msg;
					
			if(tag.getResponseContentType() != null)
				this.setResponseContentType(tag.getResponseContentType());		
		
			if(tag.getResponseURI() != null)
				this.setResponseURI(tag.getResponseURI());
			
			this.setQuery(new StringBuilder(tag.getSqlTemplate()+" "));
						
			
			for(Map.Entry<String,String> pair: tag.getParams().entrySet()){
				this.query.append(pair.getKey()+" = "+pair.getValue()+',');
			}			
			this.query.deleteCharAt(this.query.length()-1);
		}
		else if(msg instanceof SubRouting){
			SubRouting esm = (SubRouting)msg;
			if(esm.getEventId()==null)
				throw new Exception("Error has occured: bridge-serivce, UniRequest, copyFrom, EventSettingMsg");
			this.setQuery(new StringBuilder(String.format("SELECT * FROM jdb.`bus event settings` WHERE `event_id` LIKE '%s' ;",esm.getEventId())));			
		}				
	}

	@Override
	public void format() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EndpointType getEndpoint() { 
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
