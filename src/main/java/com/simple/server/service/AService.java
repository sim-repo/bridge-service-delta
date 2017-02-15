package com.simple.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.IRec;
import com.simple.server.domain.contract.IContract;

public abstract class AService implements IService{
	@Autowired
	AppConfig appConfig;
	
	@Override
	public AppConfig getAppConfig() throws Exception {
		return appConfig;
	}

	@Override
	@Transactional()
	public void insert(IContract msg) throws Exception {
		IRec rec = getAppConfig().getContractRecFactory().newRec(msg);				
		getDao().insert(rec);
	}

	@Override
	@Transactional()
	public void insert(List<IContract> list) throws Exception {		
		List<IRec> recs = getAppConfig().getContractRecFactory().newRecList(list);
		getDao().insert(recs);
	}

	@Override
	public String readFlatJson(String sql) throws Exception {
		String res = getDao().readFlatJson(sql);
		return res;
	}

	@Override
	public String readFlatXml(String sql) throws Exception {
		String res = getDao().readFlatXml(sql);
		return res;
	}
	
	@Override
	public List<IContract> readAll(IContract msg) throws Exception{
		
		IRec rec = getAppConfig().getContractRecFactory().newRec(msg);
		List<IRec> list = getDao().readAll(rec);
		if(list.size()==0)
			return null;
		
		List<IContract> res = new ArrayList<IContract>();
	
		for(IRec element: list){			
			IContract contract = getAppConfig().getContractRecFactory().newContract(element);	
			res.add(contract);
		}	
		return res;
	}
}

