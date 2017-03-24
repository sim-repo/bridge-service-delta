package com.simple.server.domain.one;

import com.simple.server.config.EndpointType;
import com.simple.server.domain.IRec;

public interface IOneRec extends IRec{
	@Override
	default EndpointType getEndpoint(){
		return EndpointType.ONE;
	}
}
