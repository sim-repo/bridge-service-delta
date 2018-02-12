package com.simple.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.simple.server.config.AppConfig;
import com.simple.server.config.MiscType;
import com.simple.server.domain.IRec;
import com.simple.server.domain.contract.IContract;
import com.simple.server.util.ObjectConverter;

public abstract class AService implements IService {
	@Autowired
	AppConfig appConfig;

	@Override
	public AppConfig getAppConfig() throws Exception {
		return appConfig;
	}

	@Override
	public Session getSession(String endpointId) throws Exception{		
		SessionFactory sf = appConfig.getSessionFactoryByEndpointId(endpointId);		
		if (sf == null) throw new Exception(String.format("class AService, method: getSession(..) - can't find SessionFactory by endpointId %s", endpointId));
		return sf.getCurrentSession();
	}
	
	@Override
	public JdbcTemplate getJdbcTemplate(String endpointId) {
		return appConfig.getJdbcTemplateByKey(endpointId);
	}
	
	
	@Override
	public void insert(String endpointId, IContract msg) throws Exception {
		if (msg.getIsDirectInsert())
			insertAsIs(endpointId, msg);
		else {
			IRec rec = getAppConfig().getContractRecFactory().newRec(msg);			
			getDao().insert(getSession(endpointId), rec);
		}
	}
	

	@Override
	public void insert(String endpointId, String sql) throws Exception {
		getDao().insert(getSession(endpointId), sql);
	}

	@Override
	public void insert(String endpointId, List<IContract> list) throws Exception {
		List<IRec> recs = getAppConfig().getContractRecFactory().newRecList(list);
		getDao().insert(getSession(endpointId), recs);
	}

	@Override
	public void insertAsIs(String endpointId, IContract msg) throws Exception {
		getDao().insertAsIs(getSession(endpointId), msg);
	}

	@Override
	public void insertAsIs(String endpointId, List<IContract> list) throws Exception {
		getDao().insertAsIs(getSession(endpointId), list);
	}

	@Override
	public void deleteAsIs(String endpointId, IContract msg) throws Exception {
		getDao().deleteAsIs(getSession(endpointId), msg);
	}

	@Override
	public String readFlatJson(String endpointId, String sql) throws Exception {
		String res = getDao().readFlatJsonArray(getJdbcTemplate(endpointId), sql);
		return res;
	}

	@Override
	public String readComlexJson(String endpointId, String sql) throws Exception {
		String res = getDao().readComplexJsonArray(getJdbcTemplate(endpointId), sql);
		return res;
	}

	@Override
	public String getFlatJsonFirstObj(String endpointId, String sql) throws Exception {
		String res = getDao().getFlatJsonFirstObj(getJdbcTemplate(endpointId), sql);
		return res;
	}

	@Override
	public String readFlatXml(String endpointId, String sql) throws Exception {
		String res = getDao().readFlatXml(getJdbcTemplate(endpointId), sql);
		return res;
	}

	@Override
	public List<IContract> readAll(String endpointId, IContract msg) throws Exception {
		IRec rec = getAppConfig().getContractRecFactory().newRec(msg);
		List<IRec> list = getDao().readAll(getSession(endpointId), rec);
		if (list.size() == 0)
			return null;
		List<IContract> res = new ArrayList<IContract>();
		for (IRec element : list) {
			IContract contract = getAppConfig().getContractRecFactory().newContract(element);
			res.add(contract);
		}
		return res;
	}
	
	@Override
	public List<IRec> readAll(String endpointId, IRec rec) throws Exception {
		List<IRec> res = getDao().readAll(getSession(endpointId),  rec);
		return res;
	}
	
	@Override
	public List<IRec> readAll(String endpointId, Class clazz) throws Exception {
		List<IRec> res = getDao().readAll(getSession(endpointId),  clazz);
		return res;
	}

	@Override
	public <T extends IContract> List<T> readbyHQL(String endpointId, String sql, Class<T> clazz, Map<String, String> params)
			throws Exception {
		List<T> res = getDao().<T>readbyHQL(getSession(endpointId), clazz, sql, params);
		return res;
	}

	@Override
	public synchronized <T extends IContract> List<T> readbyCriteria(String endpointId, Class<T> clazz, Map<String, Object> params,
			int topNum, Map<String, MiscType> orders) throws Exception {
		List<T> res = getDao().<T>readbyCriteria(getSession(endpointId), clazz, params, topNum, orders);
		return res;
	}

	@Override
	public List<IContract> read(String endpointId, IContract msg) throws Exception {
		IRec rec = getAppConfig().getContractRecFactory().newRec(msg);
		List<IRec> list = getDao().read(getJdbcTemplate(endpointId), getSession(endpointId), rec);
		if (list.size() == 0)
			return null;
		List<IContract> res = new ArrayList<IContract>();
		for (IRec element : list) {
			IContract contract = getAppConfig().getContractRecFactory().newContract(element);
			res.add(contract);
		}
		return res;
	}

	@Override
	public <T extends IContract> List<T> read(String endpointId, String sql, Class<T> clazz) throws Exception {
		String json = getDao().readFlatJsonArray(getJdbcTemplate(endpointId), sql);
		T t = clazz.newInstance();
		List<T> res = ObjectConverter.jsonToObjects(json, clazz);
		return res;
	}

	@Override
	public List<Map<String, Object>> getListMap(String endpointId, String sql) throws Exception {
		return getDao().getListMap(getJdbcTemplate(endpointId), sql);
	}

	@Override
	public void validate(String endpointId) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
