package com.simple.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.simple.server.config.ContentType;
import com.simple.server.domain.IRec;
import com.simple.server.domain.UniRequest;
import com.simple.server.domain.UniResult;
import com.simple.server.domain.contract.IContract;
import com.simple.server.util.ObjectConverter;

public abstract class ADao implements IDao{

	@Override
	public void insert(List<IRec> list) throws Exception {
		int count=0;
		for(IRec rec: list){
			try{							
				currentSession().saveOrUpdate(rec);	
			}catch(SQLException e){
				e.printStackTrace();
			}			
			if (++count % 50 == 0 ) {
				currentSession().flush();
				currentSession().clear();
			}
		}	
	}
	
	@Override
	public void insert(IRec rec) throws Exception {
		currentSession().saveOrUpdate(rec);	
	}

	@Override
	public String readFlatJsonArray(String sql) throws Exception {
		List<Map<String,Object>> list =  currentJDBCTemplate().queryForList(sql);			
		return ObjectConverter.listMapToJson(list);
	}

	
	@Override
	public String readFlatXml(String sql) throws Exception {
		List<Map<String,Object>> list =  currentJDBCTemplate().queryForList(sql);
		StringBuilder result = new StringBuilder();		
				
		for(Map<String, Object> map: list){		
			for(Map.Entry<String, Object> pair: map.entrySet()){				
				result.append(pair.getValue());
			}		
		}			
		JSONObject jObject = XML.toJSONObject(result.toString());
	    ObjectMapper mapper = new ObjectMapper();	   
	    Object json = mapper.readValue(jObject.toString(), Object.class);
	    String output = mapper.writeValueAsString(json);
		return output;
	}
	
	
	
	protected abstract List<IRec> readbyPK(IRec rec) throws Exception;	
		
	@Override
	public List<IRec> readAll(IRec rec) throws Exception{
		Criteria criteria = currentSession().createCriteria(rec.getClass());
		return criteria.list();
	}
	
	@Override
	public List<IRec> read(IRec rec) throws Exception{
		List<IRec> res = new ArrayList<IRec> ();
		if(rec instanceof UniRequest){
			UniRequest r = (UniRequest)rec;
			UniResult u = new UniResult();
			u.setResponseContentType(r.getResponseContentType());
			u.setResponseURI(r.getResponseURI());
			if(ContentType.JsonPlainText.equals(r.getResponseContentType())){				 
				u.setResult(readFlatJsonArray(r.getQuery()));				
			}
			else if(ContentType.XmlPlainText.equals(r.getResponseContentType())){				
				u.setResult(readFlatXml(r.getQuery()));				
			}
			res.add(u);			
		}
		else{
			res.addAll(readbyPK(rec));
		}
		return res;
	}

	@Override
	@Transactional()
	public <T extends IContract> List<T> readbyHQL(Class<T> clazz, String query, Map<String,String> params) throws Exception {		
		Query q = currentSession().createQuery(query);
		for(Map.Entry<String,String> pair: params.entrySet()){						
			q.setParameter(pair.getKey(), pair.getValue());
		}				
		List<T> res = q.list();
		return res;
	}
	

}
