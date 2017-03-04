package com.simple.server.service.btx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.dao.IDao;
import com.simple.server.domain.contract.IContract;
import com.simple.server.service.AService;
import com.simple.server.util.ObjectConverter;

@Service("btxService")
@Scope("singleton")
public class BtxServiceImpl extends AService implements IBtxService{

	@Override
	public IDao getDao() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IContract> read(IContract msg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public <T extends IContract> List<T> read(String sql, Class<T> clazz) throws Exception {
				
		String json = getDao().readFlatJsonArray(sql);		
		T t = clazz.newInstance();				
		T t2 = (T)ObjectConverter.JsonToObject(json,t);
		List<T> res = new ArrayList();
		res.add(t2);				
		return res;
	}

	
	@Override
	public <T extends IContract> List<T> readbyHQL(String sql, Class<T> clazz, Map<String,String> params) throws Exception {		
		List<T> res = getDao().<T>readbyHQL(clazz, sql, params);
		return res;
	}
	
	@Override
	public <T extends IContract> List<T> readbyCriteria(Class<T> clazz, Map<String, String> params) throws Exception {
		List<T> res = getDao().<T>readbyCriteria(clazz,params);
		return res;
	}
}
