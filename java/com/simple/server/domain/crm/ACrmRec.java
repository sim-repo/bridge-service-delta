package com.simple.server.domain.crm;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.ARec;
import com.simple.server.util.DateConvertHelper;

public abstract class ACrmRec extends ARec implements ICrmRec{
	
	private int id;
	private String creationDatetime = DateConvertHelper.getCurDate(); 
	private String juuid;

	
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
	public String getCreationDatetime() {
		return creationDatetime;
	}		
}
