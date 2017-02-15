package com.simple.server.service.log;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.EndpointType;
import com.simple.server.dao.IDao;
import com.simple.server.domain.IRec;
import com.simple.server.domain.contract.IContract;
import com.simple.server.service.AService;

@Service("logService")
@Scope("singleton")
public class LogServiceImpl extends AService implements ILogService{

	@Override
	public IDao getDao() throws Exception {
		return getAppConfig().getLogDao();
	}

	@Override
	public List<IContract> read(IContract msg) throws Exception {
		
		IRec rec = getAppConfig().getContractRecFactory().newRec(msg); 
		List<IRec> list = getDao().read(rec);
		//TODO
		List<IContract> res = null;
		return res;
	}
	
}
