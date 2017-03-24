package com.simple.server.http;

import com.simple.server.domain.contract.IContract;

public interface IHttp {
	void sendHttp(IContract msg) throws Exception;
}
