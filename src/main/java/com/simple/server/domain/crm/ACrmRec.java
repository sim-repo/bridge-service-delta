package com.simple.server.domain.crm;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.ARec;

public abstract class ACrmRec extends ARec implements ICrmRec{
	
	private int id;
	private String creationDatetime = new SimpleDateFormat(AppConfig.DATEFORMAT).format(Calendar.getInstance().getTime()); 
	private String juuid;
	private String eventId;
	
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
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getCreationDatetime() {
		return creationDatetime;
	}		
}
