package com.simple.server.domain.btx;

import com.simple.server.config.EndpointType;
import com.simple.server.domain.IRec;

public interface IBtxRec extends IRec{	
	@Override
	default EndpointType getEndpoint(){
		return EndpointType.BTX;
	}
}
