package com.simple.server.service.endpoint;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.simple.server.dao.IDao;
import com.simple.server.service.AService;


@Service("endpointService")
@Scope("singleton")
public class EndpointServiceImpl extends AService implements IEndpointService{

	@Override
	public IDao getDao() throws Exception {	
		return getAppConfig().getEndpointDao();
	}
	
	
	/*
	@Override
	public List<IContract> read(String endpointId , IContract msg) throws Exception {					
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
		T t2 = (T)ObjectConverter.jsonToObject(json,t);
		List<T> res = new ArrayList();
		res.add(t2);				
		return res;
	}
	*/
}
