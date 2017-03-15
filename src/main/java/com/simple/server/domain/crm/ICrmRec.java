package com.simple.server.domain.crm;

import com.simple.server.config.EndpointType;
import com.simple.server.domain.IRec;

public interface ICrmRec extends IRec{
	@Override
	default EndpointType getEndpoint(){
		return EndpointType.CRM;
	}
}
