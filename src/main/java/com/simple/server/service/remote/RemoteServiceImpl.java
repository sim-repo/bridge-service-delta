package com.simple.server.service.remote;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.IContract;
import com.simple.server.service.IService;

public class RemoteServiceImpl implements IRemoteService{
	
	@Autowired
	AppConfig appConfig;

	@Override
	public AppConfig getAppConfig() throws Exception {
		return appConfig;
	}

	@Override
	public void insert(IContract msg) throws Exception {
		IService service = getAppConfig().getServiceFactory().getService(msg.getEndPointId());
		service.insert(msg.getEndPointId(), msg);
	}

	@Override
	public void insert(List<IContract> list) throws Exception {
		for(IContract msg : list){ 
			insert(msg);
		}
	}

	@Override
	public void insert(String sql, String endpoint) throws Exception {
		IService service = getAppConfig().getServiceFactory().getService(endpoint);
		service.insert(endpoint, sql);		
	}
	
	@Override
	public void delete(IContract msg) throws Exception {
		IService service = getAppConfig().getServiceFactory().getService(msg.getEndPointId());
		service.deleteAsIs(msg.getEndPointId(), msg);
	}
	
	@Override
	public String getFlatJson(String sql, String endpoint) throws Exception {			
		String res = "";
		try {
			IService service = getAppConfig().getServiceFactory().getService(endpoint);
			res = service.readFlatJson(endpoint, sql);
		}
		catch (Exception e) {
			res = e.getMessage();
		}		
		return res;
	}

	@Override
	public String getFlatJsonFirstObj(String sql, String endpoint) throws Exception {
		String res = "";
		try {
			IService service = getAppConfig().getServiceFactory().getService(endpoint);		
			res = service.getFlatJsonFirstObj(endpoint, sql);
		} 
		catch (Exception e) {
			res = e.getMessage();
		}
		return res;
	}

	@Override
	public String getComplexJson(String sql, String endpoint) throws Exception {
		String res = "";
		try {
			IService service = getAppConfig().getServiceFactory().getService(endpoint);
			res = service.readComlexJson(endpoint, sql);
		}
		catch (Exception e) {
			res = e.getMessage();
		}
		return res;
	}
	
	@Override
	public String getFlatXml(String sql, String endpoint) throws Exception {
		String res = "";
		try {
			IService service = getAppConfig().getServiceFactory().getService(endpoint);
			res = service.readFlatXml(endpoint, sql);
		}
		catch (Exception e) {
			res = e.getMessage();
		}
		return res;
	}

	@Override
	public List<IContract> getMsg(IContract msg) throws Exception {
		IService service = getAppConfig().getServiceFactory().getService(msg.getEndPointId());
		List<IContract> res = service.read(msg.getEndPointId(), msg);
		return res;
	}

	@Override
	public List<IContract> getAllMsg(IContract msg) throws Exception {		
		IService service = getAppConfig().getServiceFactory().getService(msg.getEndPointId());
		List<IContract> res = service.readAll(msg.getEndPointId(), msg);
		return res;
	}

	@Override
	public List<Map<String, Object>> getListMap(String sql, String endpoint) throws Exception {
		IService service = getAppConfig().getServiceFactory().getService(endpoint);
		return service.getListMap(endpoint, sql);		
	}

	
}
