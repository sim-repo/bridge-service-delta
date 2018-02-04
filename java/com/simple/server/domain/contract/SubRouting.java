package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.ContentType;
import com.simple.server.domain.IRec;
import com.simple.server.domain.log.LogEventSetting;

@JsonAutoDetect
@JsonDeserialize(as = SubRouting.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubRouting extends AContract{
	
	@JsonProperty("id")
	protected int id;
	protected String bodyContentType;
	protected Boolean useAuth;
	protected String bodyFldSeparator;	
	protected Boolean removeXmlAttributes;	
	protected Boolean useCharsetBase64;
	protected Boolean useXmlDeclaration;
	
	@Override
	public String getClazz() {
		return SubRouting.class.getName();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}		
	public ContentType getBodyContentType() {
		return ContentType.fromValue(bodyContentType);
	}
	public void setBodyContentType(ContentType bodyContentType) {				
		this.bodyContentType = bodyContentType.toValue();
	}		
	public Boolean getUseAuth() {
		return useAuth;
	}
	public void setUseAuth(Boolean useAuth) {
		this.useAuth = useAuth;
	}		
	public String getBodyFldSeparator() {
		return bodyFldSeparator;
	}
	public void setBodyFldSeparator(String bodyFldSeparator) {
		this.bodyFldSeparator = bodyFldSeparator;
	}			
	public Boolean getRemoveXmlAttributes() {
		return removeXmlAttributes;
	}
	public void setRemoveXmlAttributes(Boolean removeXmlAttributes) {
		this.removeXmlAttributes = removeXmlAttributes;
	}
	public Boolean getUseCharsetBase64() {
		return useCharsetBase64;
	}
	public void setUseCharsetBase64(Boolean useCharsetBase64) {
		this.useCharsetBase64 = useCharsetBase64;
	}
	public void setBodyContentType(String bodyContentType) {
		this.bodyContentType = bodyContentType;
	}	
	public Boolean getUseXmlDeclaration() {
		return useXmlDeclaration;
	}
	public void setUseXmlDeclaration(Boolean useXmlDeclaration) {
		this.useXmlDeclaration = useXmlDeclaration;
	}
	@Override
	public void copyFrom(IRec rec) throws Exception{
		if (rec == null)
			return;
		if (rec instanceof LogEventSetting) {
			LogEventSetting les = (LogEventSetting) rec;
			this.setEventId(les.getEventId());
			this.setSubscriberId(les.getSubscriberId());
			this.setSubscriberHandler(les.getSubscriberHandler());			
		}		
	}
}
