package com.simple.server.dao.btx;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.simple.server.dao.ADao;
import com.simple.server.domain.IRec;
import com.simple.server.domain.btx.IBtxRec;


@Service("btxDao")
public class BtxDaoImpl extends ADao implements IBtxDao{

	@Override
	public Session currentSession() throws Exception {
		return appConfig.getBtxSessionFactory().getCurrentSession();	
	}

	@Override
	public JdbcTemplate currentJDBCTemplate() throws Exception {
		return appConfig.getBtxJdbcTemplate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<IRec> readbyPK(IRec rec) throws Exception {
		if(rec==null)
			throw new IllegalArgumentException("BtxDaoImpl readbyPK: param cannot be null");
		if(!(rec instanceof IBtxRec))
			throw new IllegalArgumentException("BtxDaoImpl readbyPK: param must be instance of IBtxRec");
		
		IBtxRec btxRec = (IBtxRec)rec;		
		List<IRec> list = currentSession().createCriteria(rec.getClass())
			    .add( Restrictions.like("JUUID", btxRec.getJuuid()) )			    
			    .list();
		return list;
	}

}
