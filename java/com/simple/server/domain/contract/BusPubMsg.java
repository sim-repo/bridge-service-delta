package com.simple.server.domain.contract;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.config.AppConfig;
import com.simple.server.domain.IRec;
import com.simple.server.domain.log.LogPub;

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

}
