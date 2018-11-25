package com.simple.server.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.simple.server.config.MiscType;
import com.simple.server.domain.IRec;
import com.simple.server.domain.contract.IContract;

public interface IDao {
	
	
	void insert(Session sf, List<IRec> list) throws Exception;
	void insert(Session sf,IRec rec) throws Exception;
	void insertAsIs(Session sf,IContract msg) throws Exception;
	void insertAsIs(Session sf,List<IContract> list) throws Exception;	
	void insert(Session sf,String sql) throws Exception;
	
	
	
	String readFlatJsonArray(JdbcTemplate currentJDBCTemplate,String sql) throws Exception;
	String readComplexJsonArray(JdbcTemplate currentJDBCTemplate,String sql) throws Exception;
	String getFlatJsonFirstObj(JdbcTemplate currentJDBCTemplate,String sql) throws Exception;
	String readFlatXml(JdbcTemplate currentJDBCTemplate,String sql) throws Exception;
	List<Map<String, Object>> getListMap(JdbcTemplate currentJDBCTemplate,String sql) throws Exception;
	List<IRec> read(JdbcTemplate currentJDBCTemplate, Session session, IRec rec) throws Exception;	
	<T extends IContract> List<T> readbyHQL(Session sf,Class<T> clazz, String query, Map<String,String> params) throws Exception;
	<T extends IContract> List<T> readbyCriteria(Session sf,Class<T> clazz, Map<String,Object> params, int topNum, Map<String,MiscType> orders) throws Exception;
	
	List<IRec> readAll(Session sf,IRec rec) throws Exception;
	List<IRec> readAll(Session sf,Class clazz) throws Exception;
	void deleteAsIs(Session sf,IContract msg) throws Exception;
}
