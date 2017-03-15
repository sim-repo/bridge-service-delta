package com.simple.server.service.oktell;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.dao.IDao;
import com.simple.server.service.AService;

@Service("oktellService")
@Scope("singleton")
public class OktellServiceImpl extends AService implements IOktellService{

	@Override
	public IDao getDao() throws Exception {
		return getAppConfig().getOktellDao();
	}

}
