package com.simple.server.domain.contract;

import com.simple.server.config.ContentType;
import com.simple.server.util.ObjectConverter;

public class UniMinMsg {
	
	protected String clazz;
	protected String juuid;
	protected String eventId;
	protected String body;
	protected String url;
	protected String datetime;
	protected String contentType;
	
	public String getClazz() {
		return this.getClass().getName();
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getJuuid() {
		return juuid;
	}
	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public ContentType getContentType() {
		return ContentType.valueOf(contentType);
	}
	public void setContentType(ContentType contentType) {
		this.contentType = contentType.toValue();
	}
	public void copyFrom(IContract msg){
		if(msg instanceof UniMsg){
			UniMsg uniMsg = (UniMsg)msg;
			this.setBody(uniMsg.getBody());
			this.setClazz(uniMsg.getBody());
			this.setEventId(uniMsg.getEventId());
			this.setJuuid(uniMsg.getJuuid());
			this.setUrl(uniMsg.getResponseURI());
			
		}
	}
	
	public void bodyTransform(ContentType contentType) throws Exception{
		
		boolean isJson = false;		
		isJson = ObjectConverter.isValidJSON(body);			
		
		switch(contentType){		
		 	case XmlPlainText:
		 	case ApplicationXml: 
		 		if(isJson){
		 			this.setBody(ObjectConverter.jsonToXml(body,true));
		 		}
		 		break;
		 	default: 
		 		if(!isJson){		 			
		 			this.setBody(ObjectConverter.xmlToJson(body));
		 		}		 		
		 		break;
		}
	}
	
}
