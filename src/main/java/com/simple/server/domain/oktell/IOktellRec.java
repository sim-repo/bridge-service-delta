package com.simple.server.domain.oktell;

import com.simple.server.config.EndpointType;
import com.simple.server.domain.IRec;

public interface IOktellRec extends IRec{
	@Override
	default EndpointType getEndpoint(){
		return EndpointType.OKTELL;
	}
}



