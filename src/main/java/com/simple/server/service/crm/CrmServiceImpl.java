package com.simple.server.service.crm;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.dao.IDao;
import com.simple.server.service.AService;


@Service("crmService")
@Scope("singleton")
public class CrmServiceImpl  extends AService implements ICrmService{

	@Override
	public IDao getDao() throws Exception {
		return getAppConfig().getCrmDao();
	}
}
