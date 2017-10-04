package com.simple.server.config;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.MessageChannel;
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
import com.simple.server.service.sender.Sender;

@Service("appConfig")
@Scope("singleton")
public class AppConfig {
	
	public final static String ACC = "jservice";
	public final static String PSW = "j123Service";
	public final static String DOMEN = "SIMPLE";
	public final static String WORKSTATION = "MSK10WEBSVC2";
	
	public final static String DATEFORMAT = "dd.MM.yyyy HH:mm:ss";
	public final static String SERVICE_ID = "bridge"; 
	public final static String ROLE_ID = "BRIDGE-SERVICE"; 
	public final static String LOG_HEADER_NAME = "clazz";
	
	@Autowired
    private MessageChannel channelBusLog;	
	@Autowired
	private MessageChannel channelSrvLog;	
	@Autowired
	private MessageChannel channelMonRep;
	
	
	private LinkedBlockingQueue<String> queueDirty;
	private LinkedBlockingQueue<IContract> queueRead;
	private LinkedBlockingQueue<IContract> queueWrite;    
	private LinkedBlockingQueue<IContract> queuePub;      	
	private LinkedBlockingQueue<IContract> queueSub;
	private LinkedBlockingQueue<IContract> queueLog;
	private LinkedBlockingQueue<IContract> queueMon;
	
	private static final Logger logger = LogManager.getLogger(AppConfig.class);
	
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
	private PhaserRunner phaserRunner;
		
	@Autowired 
	private Sender sender;
		
	public Sender getSender() {
		return sender;
	}

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

	public Mediator getMediator() {
		return mediator;
	}
	
	public MessageChannel getChannelBusLog() {
		return channelBusLog;
	}
	
	public MessageChannel getChannelSrvLog() {
		return channelSrvLog;
	}
	
	public MessageChannel getChannelMonRep() {
		return channelMonRep;
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
	
	public LinkedBlockingQueue<IContract> getQueuePub() {
		return queuePub;
	}		

	public LinkedBlockingQueue<IContract> getQueueSub() {
		return queueSub;
	}	

	public LinkedBlockingQueue<IContract> getQueueLog() {
		return queueLog;
	}		

	public LinkedBlockingQueue<IContract> getQueueMon() {
		return queueMon;
	}

	public QueueFactory getQueueFactory() {
		return queueFactory;
	}

	public PhaserRunner getPhaserRunner() {
		return phaserRunner;
	}

	public static Logger getLogger() {
		return logger;
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
	
	public void initPub(int size){
		this.queuePub = new LinkedBlockingQueue<>(size);
	}
	
	public void initSub(int size){
		this.queueSub = new LinkedBlockingQueue<>(size);
	}
	
	public void initLog(int size){
		this.queueLog = new LinkedBlockingQueue<>(size);
	}
	
	public void initMon(int size){
		this.queueMon= new LinkedBlockingQueue<>(size);
	}
}