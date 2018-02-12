package com.simple.server.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.simple.server.config.AppConfig;
import com.simple.server.config.MiscType;
import com.simple.server.dao.IDao;
import com.simple.server.domain.IRec;
import com.simple.server.domain.contract.IContract;

public interface IService {
	AppConfig getAppConfig() throws Exception;
	IDao getDao() throws Exception;
	@Transactional
	void insert(String endpointId, List<IContract> list) throws Exception;
	@Transactional
	void insert(String endpointId, IContract msg) throws Exception;
	@Transactional
	void insert(String endpointId, String sql) throws Exception;
	@Transactional
	void insertAsIs(String endpointId, IContract msg) throws Exception;
	@Transactional
	void insertAsIs(String endpointId, List<IContract> list) throws Exception;
	@Transactional
	String readFlatJson(String endpointId, String sql) throws Exception;
	@Transactional
	String readComlexJson(String endpointId, String sql) throws Exception;
	@Transactional
	String readFlatXml(String endpointId, String sql) throws Exception;
	@Transactional
	String getFlatJsonFirstObj(String endpointId, String sql) throws Exception;
	@Transactional
	List<IContract> read(String endpointId, IContract msg) throws Exception;
	@Transactional
	<T extends IContract> List<T> read(String endpointId, String sql, Class<T> clazz) throws Exception;
	@Transactional
	<T extends IContract> List<T> readbyHQL(String endpointId, String sql, Class<T> clazz, Map<String,String> params) throws Exception;
	@Transactional
	<T extends IContract> List<T> readbyCriteria(String endpointId, Class<T> clazz, Map<String,Object> params, int topNum, Map<String,MiscType> orders) throws Exception;
	@Transactional()
	List<IContract> readAll(String endpointId, IContract msg) throws Exception;
	@Transactional()
	List<IRec> readAll(String endpointId, IRec msg) throws Exception;
	@Transactional()
	List<IRec> readAll(String endpointId, Class clazz) throws Exception;
	@Transactional()
	void deleteAsIs(String endpointId, IContract msg) throws Exception;
	@Transactional()
	List<Map<String, Object>> getListMap(String endpointId, String sql) throws Exception;
	void validate(String endpointId) throws Exception;
	Session getSession(String endpointId) throws Exception;
	JdbcTemplate getJdbcTemplate(String endpointId);
}
