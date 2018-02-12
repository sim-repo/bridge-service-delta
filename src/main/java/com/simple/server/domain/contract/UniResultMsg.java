package com.simple.server.domain.contract;

import com.simple.server.domain.IRec;
import com.simple.server.domain.UniResult;

public class UniResultMsg extends AContract{
	
	private String result;

	@Override
	public void copyFrom(IRec rec) {
		if(rec instanceof UniResult){
			UniResult r = (UniResult)rec;
			this.setResult(r.getResult());
			this.setResponseContentType(r.getResponseContentType());
			this.setResponseURI(r.getResponseURI());
			
		}		
	}
	
	@Override
	public String getClazz() {
		return UniResultMsg.class.getName();
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
