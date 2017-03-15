package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.domain.IRec;


@JsonAutoDetect
@JsonDeserialize(as = RoutingPubConfirmMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutingPubConfirmMsg extends AContract {

	
	private static final long serialVersionUID = 1L;
	protected int id;
	String publisherHandler;
	String publisherStoreClass;
	
	@Override
	public String getClazz() {
		return this.getClass().getName();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	public String getPublisherHandler() {
		return publisherHandler;
	}
	public void setPublisherHandler(String publisherHandler) {
		this.publisherHandler = publisherHandler;
	}	
	public String getPublisherStoreClass() {
		return publisherStoreClass;
	}
	public void setPublisherStoreClass(String publisherStoreClass) {
		this.publisherStoreClass = publisherStoreClass;
	}
	
}
