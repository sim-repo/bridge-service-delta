package com.simple.server.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.server.config.EndpointType;
import com.simple.server.domain.contract.AContract;
import com.simple.server.domain.contract.EventSettingMsg;
import com.simple.server.domain.contract.IContract;
import com.simple.server.lifecycle.HqlStepsType;
import com.simple.server.mediators.CommandType;
import com.simple.server.response.HttpResponse;
import com.simple.server.service.IService;
import com.simple.server.statistics.time.Timing;

@Service("PubTask")
@Scope("prototype")
public class PubTask extends ATask {
	
	private final static Integer MAX_NUM_ELEMENTS = 100000;
	private List<IContract> list = new ArrayList<IContract>();
	private ObjectMapper mapper = new ObjectMapper();   
		
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
		if (getAppConfig() .getQueuePub().drainTo(list, MAX_NUM_ELEMENTS) == 0) {
            list.add(getAppConfig() .getQueuePub().take());
        }                      
    	Thread.currentThread().sleep(Timing.getTimeMaxSleep());                     
        while (basePhaser.getCurrNumPhase() != HqlStepsType.START.ordinal()) {
    		if(getAppConfig() .getQueuePub().size()>0)
    			getAppConfig() .getQueuePub().drainTo(list, MAX_NUM_ELEMENTS);
    	}     		
              
        
        for(IContract msg: list){
        	IService service = getAppConfig().getServiceFactory().getService(EndpointType.LOG);
        	
        	String q = new String(String.format("SELECT * FROM jdb.`bus event settings` WHERE `event_id` LIKE '%s' ;",msg.getEventId()));        	     
        	        	
        	List<EventSettingMsg> res = service.<EventSettingMsg>read(q, EventSettingMsg.class);
        	        	        	
        	
        	for(IContract r: res){
        		try{
        			if(!(r instanceof EventSettingMsg))
        				throw new Exception("Error has occured: bridge-service, PubTask, task");
        			
        			EventSettingMsg esm = (EventSettingMsg)r;
        			
        			if(esm.getSubscriberHandler()==null)
		        		throw new Exception("Error has occured: bridge-service, PubTask, task, getSubscriberHandler is null");
        			
        			        			
        			msg.setResponseURI(esm.getSubscriberHandler());
        	        		      				        		        	
        			HttpResponse response = new HttpResponse();
		    		response.reply(msg);
		    		response = null;
        		}catch(Exception e){        			
        		}
        	}
        }
        list.clear();
	}
	
}
