package com.simple.server.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.config.EndpointType;
import com.simple.server.config.ErrorType;
import com.simple.server.domain.contract.ErrMsg;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.PubErrRouting;
import com.simple.server.http.HttpImpl;
import com.simple.server.http.IHttp;
import com.simple.server.mediators.CommandType;
import com.simple.server.service.IService;
import com.simple.server.statistics.time.Timing;

@Service("WriteTask")
@Scope("prototype")
public class WriteTask extends ATask {
	
	@Autowired
	private AppConfig appConfig;
	
	private final static Integer MAX_NUM_ELEMENTS = 100000;
	private List<IContract> list = new ArrayList<IContract>();
	
	
	@Override
	public void update(Observable o, Object arg) {
	    if(arg != null && arg.getClass() == CommandType.class) {
	        switch ((CommandType) arg) {
	            case WAKEUP_CONSUMER:
	            case WAKEUP_ALL:
	                arg = CommandType.WAKEUP_ALLOW;
	                super.update(o, arg);
	                break;
	            case AWAIT_CONSUMER:
	            case AWAIT_ALL:
	                arg = CommandType.AWAIT_ALLOW;
	                super.update(o, arg);
	                break;
	        } 
	    }
	}
	
	
	@Override
	public void task() throws Exception {
		if (getAppConfig() .getQueueWrite().drainTo(list, MAX_NUM_ELEMENTS) == 0) {
            list.add(getAppConfig() .getQueueWrite().take());
        }
                       
    	Thread.currentThread().sleep(Timing.getTimeMaxSleep());
                     
        //while (basePhaser.getCurrNumPhase() != HqlStepsType.START.ordinal()) {
    	//	if(getAppConfig() .getQueueWrite().size()>0)
    			getAppConfig() .getQueueWrite().drainTo(list, MAX_NUM_ELEMENTS);
    	//}  	
    			
        for(IContract msg: list){
        	List<PubErrRouting> pubRoutes = null;
        	try{
        		IService service = getAppConfig().getServiceFactory().getService(EndpointType.LOG);
        		Map<String, Object> map = new HashMap();
				map.put("eventId", msg.getEventId());
        		pubRoutes = service.<PubErrRouting>readbyCriteria(PubErrRouting.class, map, 0, null);
				if (pubRoutes == null || pubRoutes.size() == 0)
					throw new Exception(
							String.format("PubTask: [bus PUB routing] - no records found by filter %s: < %s > ",
									"eventId", msg.getEventId()));
        		        		
	        	service = getAppConfig().getServiceFactory().getService(msg.getEndPointId());
	        	if(msg.getIsDirectInsert())
	        		service.insertAsIs(msg);
	        	else
	        		service.insert(msg);       
        	}
        	catch(Exception e){
        		ErrMsg err = new ErrMsg();
				err.setErrorId(ErrorType.WriteTask);
				err.setDetails(String.format("WriteTask: %s", e.getMessage()));
				err.setEventId(msg.getEventId());
				err.setJuuid(msg.getJuuid());
				err.setSenderId(msg.getSenderId());
				appConfig.getQueueLog().put(err);
				for (PubErrRouting r : pubRoutes) {
					if (r.getPublisherHandler() != null && !r.getPublisherHandler().isEmpty()) {
						IHttp http = new HttpImpl();
						err.setResponseURI(r.getPublisherHandler());
						try{
							http.sendHttp(err);
						}catch(Exception e2){
							ErrMsg err2 = new ErrMsg();
							err2.setErrorId(ErrorType.WriteTask);
							err2.setDetails(String.format("WriteTask: %s", e2.getMessage()));
							err2.setEventId(msg.getEventId());
							err2.setJuuid(msg.getJuuid());
							err2.setSenderId(msg.getSenderId());
							appConfig.getQueueLog().put(err2);
						}
						http = null;
					}
				}

        	}
        }                        
        list.clear();                
	}	      
}
