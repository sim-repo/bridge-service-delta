package com.simple.server.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import com.simple.server.domain.IRec;

public interface IDao {
	
	Session currentSession() throws Exception;	
	JdbcTemplate currentJDBCTemplate() throws Exception;
	
	void insert(List<IRec> list) throws Exception;
	void insert(IRec rec) throws Exception;
	
	String readFlatJson(String sql) throws Exception;
	String readFlatXml(String sql) throws Exception;
	List<IRec> read(IRec rec) throws Exception;	
	List<IRec> readAll(IRec rec) throws Exception;
}
