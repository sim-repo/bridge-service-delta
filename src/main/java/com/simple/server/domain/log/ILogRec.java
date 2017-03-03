package com.simple.server.domain.log;

import com.simple.server.config.EndpointType;
import com.simple.server.domain.IRec;

public interface ILogRec extends IRec{
	@Override
	default EndpointType getEndpoint(){
		return EndpointType.LOG;
	}
}
