package com.simple.server.domain.contract;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.simple.server.domain.IRec;
import com.simple.server.domain.nav.NavCust;
import com.simple.server.domain.one.OneCust;

@JsonAutoDetect
@JsonDeserialize(as = CustMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustMsg extends AContract{
	
	private static final long serialVersionUID = 1L;
	protected String outerCustomerId;	
	protected String customerId;	
	protected String customerName;
	protected String phoneNo;
	protected String phoneNo2;	
	protected String email;
	protected String address;
	
	@Override
	public String getClazz() {
		return this.getClass().getName();
	}

	public String getOuterCustomerId() {
		return outerCustomerId;
	}

	public void setOuterCustomerId(String outerCustomerId) {
		this.outerCustomerId = outerCustomerId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPhoneNo2() {
		return phoneNo2;
	}

	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public void copyFrom(IRec rec) {
		if (rec == null)
			return;
		if (rec instanceof NavCust) {
			NavCust nc = (NavCust) rec;
			this.setOuterCustomerId(nc.getOuterCustomerId());
			this.setAddress(nc.getAddress());
			this.setCustomerId(nc.getCustomerId());
			this.setCustomerName(nc.getCustomerName());
			this.setPhoneNo(nc.getPhoneNo());
			this.setPhoneNo2(nc.getPhoneNo2());
			this.setEmail(nc.getEmail());
			return;
		}	
		
		if (rec instanceof OneCust) {
			OneCust nc = (OneCust) rec;
			this.setOuterCustomerId(nc.getOuterCustomerId());
			this.setAddress(nc.getAddress());
			this.setCustomerId(nc.getCustomerId());
			this.setCustomerName(nc.getCustomerName());
			this.setPhoneNo(nc.getPhoneNo());
			this.setPhoneNo2(nc.getPhoneNo2());
			this.setEmail(nc.getEmail());
			return;
		}		
	}	
	
}
