package com.simple.server.domain.nav;

import com.simple.server.config.ContentType;
import com.simple.server.domain.contract.CustMsg;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.SorderMsg;

public class NavCust extends ANavRec {
	protected int id;
	protected String outerCustomerId;	
	protected String customerId;	
	protected String customerName;
	protected String phoneNo;
	protected String phoneNo2;	
	protected String email;
	protected String address;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public void copyFrom(IContract msg) throws Exception {
		if(!(msg instanceof CustMsg))
			throw new Exception("msg must be instance of CustMsg");
		
		CustMsg cust = (CustMsg)msg;
			
		
		if (cust.getOuterCustomerId() != null)
			this.setOuterCustomerId(cust.getOuterCustomerId());
		
		if (cust.getCustomerId() != null)
			this.setCustomerId(cust.getCustomerId());
		
		if (cust.getCustomerName()!= null)
			this.setCustomerName(cust.getCustomerName());
		
		if (cust.getAddress() != null)
			this.setAddress(cust.getAddress());
		
		if (cust.getEmail() != null)
			this.setEmail(cust.getEmail());
		
		if (cust.getPhoneNo() != null)
			this.setPhoneNo(cust.getPhoneNo());
		
		if (cust.getPhoneNo2() != null)
			this.setPhoneNo2(cust.getPhoneNo2());		
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
	public String getJuuid() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
