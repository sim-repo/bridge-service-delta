package com.simple.server.dao.crm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.simple.server.dao.ADao;
import com.simple.server.domain.IRec;
import com.simple.server.domain.crm.ICrmRec;


@Service("crmDao")
public class CrmDaoImpl extends ADao implements ICrmDao{

	@Override
	public Session currentSession() throws Exception {
		return appConfig.getCrmSessionFactory().getCurrentSession();	
	}

	@Override
	public JdbcTemplate currentJDBCTemplate() throws Exception {
		return appConfig.getCrmJdbcTemplate();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<IRec> readbyPK(IRec rec) throws Exception {
		if(rec==null)
			throw new IllegalArgumentException("CrmDaoImpl readbyPK: param cannot be null");
		if(!(rec instanceof ICrmRec))
			throw new IllegalArgumentException("CrmDaoImpl readbyPK: param must be instance of ICrmRec");
		
		ICrmRec crmRec = (ICrmRec)rec;		
		List<IRec> list = currentSession().createCriteria(rec.getClass())
			    .add( Restrictions.like("JUUID", crmRec.getJuuid()) )			    
			    .list();
		return list;
	}

}
