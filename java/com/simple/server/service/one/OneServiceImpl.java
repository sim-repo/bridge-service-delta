package com.simple.server.service.one;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.dao.IDao;
import com.simple.server.service.AService;

@Service("oneService")
@Scope("singleton")
public class OneServiceImpl extends AService implements IOneService{

	@Override
	public IDao getDao() throws Exception {	
		return getAppConfig().getOneDao();
	}
}
