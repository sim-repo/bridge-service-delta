package com.simple.server.domain.nav;

import com.simple.server.config.EndpointType;
import com.simple.server.domain.IRec;

public interface INavRec extends IRec{
	public String getJuuid();
	
	@Override
	default EndpointType getEndpoint(){
		return EndpointType.NAV;
	}
	
}
