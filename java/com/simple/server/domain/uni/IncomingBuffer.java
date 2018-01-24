package com.simple.server.domain.uni;

import com.simple.server.config.ContentType;
import com.simple.server.domain.ARec;
import com.simple.server.domain.IRec;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.IncomingBufferMsg;
import com.simple.server.domain.contract.UniMsg;
import com.simple.server.util.DateConvertHelper;
import com.simple.server.util.ObjectConverter;

public class IncomingBuffer extends ARec implements IRec{

	public int id;
	private String creationDatetime =DateConvertHelper.getCurDate(); 
	private String juuid;
	private String body;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreationDatetime() {
		return creationDatetime;
	}

	public void setCreationDatetime(String creationDatetime) {
		this.creationDatetime = creationDatetime;
	}

	public String getJuuid() {
		return juuid;
	}

	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public void copyFrom(IContract msg) throws Exception {
		if(msg instanceof UniMsg){
			UniMsg u = (UniMsg)msg;
			this.setEventId(u.getEventId());
			this.setJuuid(u.getJuuid());
			this.setSenderId(u.getSenderId());
			this.setBody(u.getBody());
			return;
		}	
		
		if(msg instanceof IncomingBufferMsg){
			IncomingBufferMsg u = (IncomingBufferMsg)msg;
			this.setEventId(u.getEventId());
			this.setJuuid(u.getJuuid());
			this.setSenderId(u.getSenderId());
			this.setBody(u.getMessageBodyValue());		
			return;
		}					
		throw new IllegalArgumentException(String.format("IncomingBuffer, copyFrom: wrong passed param: %s", msg.getClass().getName()));
	}

	
	

	@Override
	public void format() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResponseContentType(ContentType ct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEndpoint() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
