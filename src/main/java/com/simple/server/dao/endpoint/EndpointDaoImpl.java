package com.simple.server.dao.endpoint;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.simple.server.dao.ADao;
import com.simple.server.domain.IRec;

@Service("endpointDao")
public class EndpointDaoImpl extends ADao implements IEndpointDao{
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IRec> readbyPK(Session session, IRec rec) throws Exception {
		if(rec==null)
			throw new IllegalArgumentException("EndpointDaoImpl readbyPK: param cannot be null");
						
		List<IRec> list = session.createCriteria(rec.getClass())
			    .add( Restrictions.like("JUUID", rec.getJuuid()) )			    
			    .list();
		return list;
	}	
	
}
