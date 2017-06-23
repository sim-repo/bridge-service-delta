package com.simple.server.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simple.server.config.AppConfig;
import com.simple.server.config.EndpointType;
import com.simple.server.service.IService;


@Component("serviceFactory")
public class ServiceFactory {
	
	@Autowired
	AppConfig appConfig;
	
	public IService getService(EndpointType endpoint) throws Exception{
		
		if(endpoint == null)
			throw new Exception("[bridge-service].[getService]: argument 'endpoint' is null!");
		
		IService service = null;
		if(EndpointType.LOG.equals(endpoint))
			service =appConfig.getLogService();
		else if(EndpointType.NAV.equals(endpoint))
			service =appConfig.getNavService();		
		return service;
	}
}
