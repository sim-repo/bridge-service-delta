package com.simple.server.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.simple.server.config.EndpointType;
import com.simple.server.domain.IRec;
import com.simple.server.domain.UniRequest;
import com.simple.server.domain.UniResult;
import com.simple.server.domain.contract.CustMsg;
import com.simple.server.domain.contract.EventSettingMsg;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.SorderMsg;
import com.simple.server.domain.contract.TagRequestMsg;
import com.simple.server.domain.contract.UniResultMsg;
import com.simple.server.domain.nav.NavCust;
import com.simple.server.domain.nav.NavSorder;
import com.simple.server.domain.one.OneCust;

@Component("contractRecFactory")
public class ContractRecFactory {
	
	public IRec newRec(IContract msg) throws Exception{
		IRec res = null;
		
		if(msg instanceof TagRequestMsg){
			res = new UniRequest();
			res.copyFrom(msg);
		} 
		else{
			EndpointType endpoint = msg.getEndPointId();						
			if(EndpointType.LOG.equals(endpoint)){			
				if(msg instanceof EventSettingMsg){
					res = new UniRequest();
					res.copyFrom(msg);
					return res;
				}
			}
			else if(EndpointType.NAV.equals(endpoint)){
				
				if(msg instanceof SorderMsg){
					res = new NavSorder();
					res.copyFrom(msg);
					res.format();
					return res;
				}else										
					if(msg instanceof CustMsg){
						res = new NavCust();
						res.copyFrom(msg);
						res.format();
						return res;
					}
			}
			else if(EndpointType.BTX.equals(endpoint)){
				//TODO
			}
			else if(EndpointType.ONE.equals(endpoint)){
				if(msg instanceof CustMsg){
					res = new OneCust();
					res.copyFrom(msg);
					res.format();
					return res;
				}
			}		
		}
		return res;
	}
	
	
	public IContract newContract(IRec rec) throws Exception{		
		IContract res = null;
		
		if(rec instanceof UniResult){
			res = new UniResultMsg();
			res.copyFrom(rec);
		}
		else{
		
			EndpointType endpoint = rec.getEndpoint();		
			if(EndpointType.LOG.equals(endpoint)){			
				//TODO			
			}
			else if(EndpointType.NAV.equals(endpoint)){
				if(rec instanceof NavSorder){
					res = new SorderMsg();
					res.copyFrom(rec);			
					return res;
				}
				if(rec instanceof NavCust){
					res = new CustMsg();
					res.copyFrom(rec);			
					return res;
				}
			}
			else if(EndpointType.BTX.equals(endpoint)){
				//TODO
			}
			else if(EndpointType.ONE.equals(endpoint)){
				if(rec instanceof OneCust){
					res = new CustMsg();
					res.copyFrom(rec);			
					return res;
				}
			}	
		}
		return res;
	}
	
	
	public List<IRec> newRecList(List<IContract> list) throws Exception{
		List<IRec> res = new ArrayList<IRec>();
		for(IContract msg: list){
			IRec rec = newRec(msg);
			res.add(rec);
		}
		return res;
	}
	
}
