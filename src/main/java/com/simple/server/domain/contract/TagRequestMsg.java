package com.simple.server.domain.contract;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.domain.IRec;

@JsonAutoDetect
@JsonDeserialize(as = TagRequestMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagRequestMsg extends AContract {

	private static final long serialVersionUID = 1L;
	private String tag;
	private String sqlTemplate;
	private Map<String, String> params = new HashMap<String, String>();
	
	
	@Override
	public String getClazz() {
		return TagRequestMsg.class.getName();
	}
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getSqlTemplate() {
		return sqlTemplate;
	}
	public void setSqlTemplate(String sqlTemplate) {
		this.sqlTemplate = sqlTemplate;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	@Override
	public void copyFrom(IRec rec) {
		
	}		
}
