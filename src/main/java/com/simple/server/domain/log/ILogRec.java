package com.simple.server.domain.log;

import com.simple.server.domain.IRec;

public interface ILogRec extends IRec{
	@Override
	default String getEndpoint(){
		return "LOG";
	}
}
