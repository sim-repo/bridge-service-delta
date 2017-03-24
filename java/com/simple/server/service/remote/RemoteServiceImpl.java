package com.simple.server.service.remote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.simple.server.config.AppConfig;
import com.simple.server.config.EndpointType;
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
		service.insert(msg);
	}

	@Override
	public void insert(List<IContract> list) throws Exception {
		for(IContract msg : list){ 
			insert(msg);
		}
	}

	@Override
	public String getFlatJson(String sql, EndpointType endpoint) throws Exception {
		IService service = getAppConfig().getServiceFactory().getService(endpoint);
		String res = service.readFlatJson(sql);
		return res;
	}

	@Override
	public String getFlatXml(String sql, EndpointType endpoint) throws Exception {
		IService service = getAppConfig().getServiceFactory().getService(endpoint);
		String res = service.readFlatXml(sql);
		return res;
	}

	@Override
	public List<IContract> getMsg(IContract msg) throws Exception {
		IService service = getAppConfig().getServiceFactory().getService(msg.getEndPointId());
		List<IContract> res = service.read(msg);
		return res;
	}

	@Override
	public List<IContract> getAllMsg(IContract msg) throws Exception {
		IService service = getAppConfig().getServiceFactory().getService(msg.getEndPointId());
		List<IContract> res = service.readAll(msg);
		return res;
	}
	
	
	
}
