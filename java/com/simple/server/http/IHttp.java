package com.simple.server.http;

import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.UniMinMsg;

public interface IHttp {
	void sendHttp(IContract msg) throws Exception;
	void sendHttp(UniMinMsg msg) throws Exception;
}
