package com.simple.server.service.btx;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.dao.IDao;
import com.simple.server.domain.contract.IContract;
import com.simple.server.service.AService;

@Service("btxService")
@Scope("singleton")
public class BtxServiceImpl extends AService implements IBtxService{

	@Override
	public IDao getDao() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IContract> read(IContract msg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
