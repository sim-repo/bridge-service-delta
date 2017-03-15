package com.simple.server.service.log;

import org.springframework.messaging.MessageChannel;
import com.simple.server.domain.contract.IContract;
import com.simple.server.service.IService;

public interface ILogService extends IService{	
	void sendAsIs(MessageChannel msgChannel, IContract msg) throws Exception;	
}
