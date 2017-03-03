package com.simple.server.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import com.simple.server.domain.IRec;
import com.simple.server.domain.contract.IContract;

public interface IDao {
	
	Session currentSession() throws Exception;	
	JdbcTemplate currentJDBCTemplate() throws Exception;
	
	void insert(List<IRec> list) throws Exception;
	void insert(IRec rec) throws Exception;
	
	String readFlatJsonArray(String sql) throws Exception;
	String readFlatXml(String sql) throws Exception;
	List<IRec> read(IRec rec) throws Exception;	
	<T extends IContract> List<T> readbyHQL(Class<T> clazz, String query, Map<String,String> params) throws Exception;
	List<IRec> readAll(IRec rec) throws Exception;
}
