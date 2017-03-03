package com.simple.server.dao.one;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.dao.ADao;
import com.simple.server.domain.IRec;
import com.simple.server.domain.nav.INavRec;

@Service("oneDao")
public class OneDaoImpl extends ADao implements IOneDao{
	@Autowired
	AppConfig appConfig;
	
	@Override
	public Session currentSession() throws Exception {
		return appConfig.getOneSessionFactory().getCurrentSession();	
	}

	@Override
	public JdbcTemplate currentJDBCTemplate() throws Exception {
		return appConfig.getOneJdbcTemplate();
	}

	@Override
	public List<IRec> readbyPK(IRec rec) throws Exception {
		if(rec==null)
			throw new Exception("argument must be not null");
		if(!(rec instanceof INavRec))
			throw new Exception("argument must be instance of INavRec");
		
		INavRec navRec = (INavRec)rec;		
		List<IRec> list = currentSession().createCriteria(rec.getClass())
			    .add( Restrictions.like("JUUID", navRec.getJuuid()) )			    
			    .list();
		return list;
	}	
}
