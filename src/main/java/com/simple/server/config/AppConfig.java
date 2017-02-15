package com.simple.server.config;

import java.util.concurrent.LinkedBlockingQueue;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.simple.server.dao.log.ILogDao;
import com.simple.server.dao.nav.INavDao;
import com.simple.server.dao.nav.NavDaoImpl;
import com.simple.server.domain.contract.IContract;
import com.simple.server.factory.ContractRecFactory;
import com.simple.server.factory.PhaserRunner;
import com.simple.server.factory.QueueFactory;
import com.simple.server.factory.ServiceFactory;
import com.simple.server.mediators.Mediator;
import com.simple.server.service.IService;

@Service("appConfig")
@Scope("singleton")
public class AppConfig {
	
	private LinkedBlockingQueue<String> queueDirty;
	private LinkedBlockingQueue<IContract> queueRead;
	private LinkedBlockingQueue<IContract> queueWrite;    
	    	
	private Mediator mediator = new Mediator();   
	
	@Autowired
	private JdbcTemplate navJdbcTemplate;
			
	@Autowired
	private JdbcTemplate logJdbcTemplate;

	@Autowired
	private SessionFactory logSessionFactory;
		
	@Autowired
	private SessionFactory navSessionFactory;
	
	@Autowired
	private ContractRecFactory contractRecFactory;
	
	@Autowired
	private ServiceFactory serviceFactory;
	
	@Autowired
	private QueueFactory queueFactory;
	
	@Autowired
	private ILogDao logDao;	
	
	@Autowired
	private NavDaoImpl navDao;
	
	@Autowired
	private IService navService;
	
	@Autowired
	private IService logService;
	
	@Autowired
	private IService btxService;
	
	@Autowired
	private IService oneService;
	
	@Autowired
	private PhaserRunner phaserRunner;
	
	public JdbcTemplate getNavJdbcTemplate() {
		return navJdbcTemplate;
	}

	public JdbcTemplate getLogJdbcTemplate() {
		return logJdbcTemplate;
	}

	public SessionFactory getLogSessionFactory() {
		return logSessionFactory;
	}

	public SessionFactory getNavSessionFactory() {
		return navSessionFactory;
	}

	public ILogDao getLogDao() {
		return logDao;
	}

	public INavDao getNavDao() {
		return navDao;
	}

	public ContractRecFactory getContractRecFactory() {
		return contractRecFactory;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public IService getNavService() {
		return navService;
	}

	public IService getLogService() {
		return logService;
	}

	public IService getBtxService() {
		return btxService;
	}

	public IService getOneService() {
		return oneService;
	}

	public Mediator getMediator() {
		return mediator;
	}

	public LinkedBlockingQueue<String> getQueueDirty() {
		return queueDirty;
	}

	public LinkedBlockingQueue<IContract> getQueueRead() {
		return queueRead;
	}

	public LinkedBlockingQueue<IContract> getQueueWrite() {
		return queueWrite;
	}

	public QueueFactory getQueueFactory() {
		return queueFactory;
	}

	public PhaserRunner getPhaserRunner() {
		return phaserRunner;
	}

	public void initQueueDirty(int size){
		this.queueDirty = new LinkedBlockingQueue<>(size);
	}
	
	public void initRead(int size){
		this.queueRead = new LinkedBlockingQueue<>(size);
	}
	
	public void initWrite(int size){
		this.queueWrite = new LinkedBlockingQueue<>(size);
	}
}
