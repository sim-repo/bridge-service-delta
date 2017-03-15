package com.simple.server.service.btx;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.dao.IDao;
import com.simple.server.service.AService;

@Service("btxService")
@Scope("singleton")
public class BtxServiceImpl extends AService implements IBtxService{

	@Override
	public IDao getDao() throws Exception {
		return getAppConfig().getBtxDao();
	}
}
