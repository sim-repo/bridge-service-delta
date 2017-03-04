package com.simple.server.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.simple.server.config.AppConfig;
import com.simple.server.dao.IDao;
import com.simple.server.domain.contract.IContract;

public interface IService {
	AppConfig getAppConfig() throws Exception;
	IDao getDao() throws Exception;
	void insert(List<IContract> list) throws Exception;
	void insert(IContract msg) throws Exception;	
	String readFlatJson(String sql) throws Exception;
	String readFlatXml(String sql) throws Exception;
	List<IContract> read(IContract msg) throws Exception;
	<T extends IContract> List<T> read(String sql, Class<T> clazz) throws Exception;
	@Transactional
	<T extends IContract> List<T> readbyHQL(String sql, Class<T> clazz, Map<String,String> params) throws Exception;
	@Transactional
	<T extends IContract> List<T> readbyCriteria(Class<T> clazz, Map<String,String> params) throws Exception;
	
	List<IContract> readAll(IContract msg) throws Exception;
}
