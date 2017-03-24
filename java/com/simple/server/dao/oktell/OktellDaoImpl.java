package com.simple.server.dao.oktell;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.simple.server.dao.ADao;
import com.simple.server.domain.IRec;
import com.simple.server.domain.oktell.IOktellRec;


@Service("oktellDao")
public class OktellDaoImpl extends ADao implements IOktellDao{

	@Override
	public Session currentSession() throws Exception {
		return appConfig.getOktellSessionFactory().getCurrentSession();
	}

	@Override
	public JdbcTemplate currentJDBCTemplate() throws Exception {
		return appConfig.getOktellJdbcTemplate();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<IRec> readbyPK(IRec rec) throws Exception {
		if(rec==null)
			throw new IllegalArgumentException("OktellDaoImpl readbyPK: param cannot be null");
		if(!(rec instanceof IOktellRec))
			throw new IllegalArgumentException("OktellDaoImpl readbyPK: param must be instance of IBtxRec");
		
		IOktellRec oktellRec = (IOktellRec)rec;		
		List<IRec> list = currentSession().createCriteria(rec.getClass())
			    .add( Restrictions.like("JUUID", oktellRec.getJuuid()) )			    
			    .list();
		return list;
	}

}
