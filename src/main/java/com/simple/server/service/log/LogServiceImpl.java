package com.simple.server.service.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simple.server.config.EndpointType;
import com.simple.server.dao.IDao;
import com.simple.server.domain.IRec;
import com.simple.server.domain.contract.IContract;
import com.simple.server.service.AService;
import com.simple.server.util.ObjectConverter;

@Service("logService")
@Scope("singleton")
public class LogServiceImpl extends AService implements ILogService{

	@Override
	public IDao getDao() throws Exception {
		return getAppConfig().getLogDao();
	}

	@Override
	@Transactional()
	public List<IContract> read(IContract msg) throws Exception {
		
		IRec rec = getAppConfig().getContractRecFactory().newRec(msg); 
		List<IRec> list = getDao().read(rec);
		
		if(list.size()==0)
			return null;
		
		List<IContract> res = new ArrayList<IContract>();
		
		for(IRec element: list){			
			IContract contract = getAppConfig().getContractRecFactory().newContract(element);	
			res.add(contract);
		}	
		return res;
	}
	
	@Override
	public <T extends IContract> List<T> read(String sql, Class<T> clazz) throws Exception {
				
		String json = getDao().readFlatJsonArray(sql);		
		T t = clazz.newInstance();				
		List<T> res = ObjectConverter.JsonToObjects(json,clazz);			
		return res;
	}
	
	@Override
	public <T extends IContract> List<T> readbyHQL(String sql, Class<T> clazz, Map<String,String> params) throws Exception {		
		List<T> res = getDao().<T>readbyHQL(clazz, sql, params);
		return res;
	}
	
}
