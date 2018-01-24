package com.simple.server.dao.log;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.simple.server.dao.ADao;
import com.simple.server.domain.IRec;


@Repository("logDao") 
public class LogDaoImpl extends ADao implements ILogDao{
	
	@Override
	public List<IRec> readbyPK(Session session, IRec rec) throws Exception {
		IRec r = (IRec) session.load(rec.getClass(), rec);		
		return Arrays.asList(r);
	}	

}
