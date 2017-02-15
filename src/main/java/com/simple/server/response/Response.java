package com.simple.server.response;

import com.simple.server.domain.contract.IContract;

public interface Response {
	void reply(IContract msg) throws Exception;
}
