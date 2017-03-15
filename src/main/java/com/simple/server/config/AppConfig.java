package com.simple.server.config;

import java.util.concurrent.LinkedBlockingQueue;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.simple.server.dao.btx.BtxDaoImpl;
import com.simple.server.dao.crm.CrmDaoImpl;
import com.simple.server.dao.log.ILogDao;
import com.simple.server.dao.nav.INavDao;
import com.simple.server.dao.nav.NavDaoImpl;
import com.simple.server.dao.oktell.OktellDaoImpl;
import com.simple.server.dao.one.IOneDao;
import com.simple.server.dao.one.OneDaoImpl;
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
	
	public final static String DATEFORMAT = "dd.MM.yyyy HH:mm:ss";
	public final static String SERVICE_ID = "bridge"; 
	public final static String ROLE_ID = "BRIDGE-SERVICE"; 
	
	@Autowired
    private MessageChannel channelBusLog;	
	@Autowired
	private MessageChannel channelSrvLog;
	
	private LinkedBlockingQueue<String> queueDirty;
	private LinkedBlockingQueue<IContract> queueRead;
	private LinkedBlockingQueue<IContract> queueWrite;    
	private LinkedBlockingQueue<IContract> queuePub;      	
	private LinkedBlockingQueue<IContract> queueSub;
	private LinkedBlockingQueue<IContract> queueLog;
	
	
	private Mediator mediator = new Mediator();   
	
	@Autowired
	private JdbcTemplate navJdbcTemplate;
			
	@Autowired
	private JdbcTemplate logJdbcTemplate;

	@Autowired
	private JdbcTemplate oneJdbcTemplate;
	
	@Autowired
	private JdbcTemplate btxJdbcTemplate;
	
	@Autowired
	private JdbcTemplate crmJdbcTemplate;
	
	@Autowired
	private JdbcTemplate oktellJdbcTemplate;
	
	
	@Autowired
	private SessionFactory logSessionFactory;
		
	@Autowired
	private SessionFactory navSessionFactory;
	
	@Autowired
	private SessionFactory oneSessionFactory;
		
	@Autowired
	private SessionFactory crmSessionFactory;
	
	@Autowired
	private SessionFactory btxSessionFactory;
	
	@Autowired
	private SessionFactory oktellSessionFactory;
	
	
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
	private OneDaoImpl oneDao;
	
	@Autowired
	private BtxDaoImpl btxDao;
	
	@Autowired
	private CrmDaoImpl crmDao;
	
	@Autowired
	private OktellDaoImpl oktellDao;
	
	
	
	@Autowired
	private IService navService;
	
	@Autowired
	private IService logService;
	
	@Autowired
	private IService btxService;
	
	@Autowired
	private IService crmService;
	
	@Autowired
	private IService oktellService;
	
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
	
	public JdbcTemplate getOneJdbcTemplate() {
		return oneJdbcTemplate;
	}

	public JdbcTemplate getBtxJdbcTemplate() {
		return btxJdbcTemplate;
	}

	public JdbcTemplate getCrmJdbcTemplate() {
		return crmJdbcTemplate;
	}

	public JdbcTemplate getOktellJdbcTemplate() {
		return oktellJdbcTemplate;
	}

	public SessionFactory getLogSessionFactory() {
		return logSessionFactory;
	}

	public SessionFactory getNavSessionFactory() {
		return navSessionFactory;
	}	

	public SessionFactory getOneSessionFactory() {
		return oneSessionFactory;
	}

	public SessionFactory getCrmSessionFactory() {
		return crmSessionFactory;
	}

	public SessionFactory getBtxSessionFactory() {
		return btxSessionFactory;
	}

	public SessionFactory getOktellSessionFactory() {
		return oktellSessionFactory;
	}

	public ILogDao getLogDao() {
		return logDao;
	}

	public INavDao getNavDao() {
		return navDao;
	}
	
	public IOneDao getOneDao() {
		return oneDao;
	}

	public BtxDaoImpl getBtxDao() {
		return btxDao;
	}

	public CrmDaoImpl getCrmDao() {
		return crmDao;
	}

	public OktellDaoImpl getOktellDao() {
		return oktellDao;
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
	
	public IService getCrmService() {
		return crmService;
	}

	public IService getOktellService() {
		return oktellService;
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
	
	public void initPub(int size){
		this.queuePub = new LinkedBlockingQueue<>(size);
	}
	
	public void initSub(int size){
		this.queueSub = new LinkedBlockingQueue<>(size);
	}
	
	public void initLog(int size){
		this.queueLog = new LinkedBlockingQueue<>(size);
	}
}
