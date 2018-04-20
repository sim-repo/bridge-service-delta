package com.simple.server.domain.contract;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.AppConfig;
import com.simple.server.domain.IRec;
import com.simple.server.domain.log.LogPub;
import com.simple.server.util.DateConvertHelper;

@JsonAutoDetect
@JsonDeserialize(as = BusPubMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusPubMsg extends AContract {
	
	private static final long serialVersionUID = 1L;
	protected int id;
	
	@Override
	public String getClazz() {		
		return this.getClass().getName();
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void copyFrom(IRec rec) throws Exception{
		if (rec == null)
			return;
		
		if (rec instanceof LogPub) {
			LogPub r = (LogPub) rec;
			BusPubMsg msg = new BusPubMsg();
			msg.setJuuid(r.getJuuid());
			msg.setEndPointId(r.getEndpoint());
			msg.setSenderId(r.getSenderId());
			msg.setEventId(r.getEventId());
			msg.setLogDatetime(r.getLogDatetime());
			msg.setLoggerId(r.getLoggerId());
			msg.setMessageHeaderValue(r.getMessageHeaderValue());
			msg.setMethodHandler(r.getMethodHandler());
			msg.setRequestInDatetime(r.getRequestInDatetime());
			msg.setServiceIdFrom(r.getServiceIdFrom());
			msg.setServiceIdTo(r.getServiceIdTo());
			msg.setServiceInDatetime(r.getServiceInDatetime());
			msg.setServiceOutDatetime(r.getServiceOutDatetime());
			msg.setServiceRoleFrom(r.getServiceIdFrom());
			msg.setServiceIdTo(r.getServiceIdTo());						
			return;
		}					
	}
	
	@Override
	public void copyFrom(IContract _msg) throws Exception{
		AContract msg = (AContract)_msg;
		
		this.setServiceOutDatetime(DateConvertHelper.getCurDate());		
		if(this.getJuuid() == null)
			this.setJuuid(msg.getJuuid());		
		
		if(this.getEndPointId() == null || this.getEndPointId().equals(""))
			this.setEndPointId(msg.getEndPointId());
		
		if(this.getSenderId() == null || this.getSenderId().equals(""))
			this.setSenderId(msg.getSenderId());
		
		if(this.getPublisherId() == null || this.getPublisherId().equals(""))
			this.setPublisherId(msg.getPublisherId());
		
		if(this.getSubscriberId() == null || this.getSubscriberId().equals(""))
			this.setSubscriberId(msg.getSubscriberId());
		
		if(this.getEventId() == null || this.getEventId().equals(""))
			this.setEventId(msg.getEventId());
		
		if(this.getResponseURI() == null)
			this.setResponseURI(msg.getResponseURI());
		
		if(this.getResponseContentType() == null)
			this.setResponseContentType(msg.getResponseContentType());
		
		if(this.getResponseContractClass() == null)
			this.setResponseContractClass(msg.getResponseContractClass());	
		
		if(this.getMethodHandler() == null)
			this.setMethodHandler(msg.getMethodHandler());
							
		
		if(this.getLogDatetime() == null){
			this.setLogDatetime(msg.getLogDatetime());
		}		
		this.setServiceRoleFrom(getAppConfig().ROLE_ID);
		if(getAppConfig() != null)
			this.setServiceIdFrom(getAppConfig().getServiceId());;			
		this.setMessageHeaderValue(this.getClass().getSimpleName());					
	}

}
