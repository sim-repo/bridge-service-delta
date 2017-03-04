package com.simple.server.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.server.config.EndpointType;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.PubConfirmationMsg;
import com.simple.server.domain.contract.PubMsg;
import com.simple.server.lifecycle.HqlStepsType;
import com.simple.server.mediators.CommandType;
import com.simple.server.response.HttpResponse;
import com.simple.server.service.IService;
import com.simple.server.statistics.time.Timing;

@Service("SubTask")
@Scope("prototype")
public class SubTask extends ATask {
	
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
	
	
	@SuppressWarnings("static-access")
	@Override
	public void task() throws Exception {
		
		if (getAppConfig() .getQueueSub().drainTo(list, MAX_NUM_ELEMENTS) == 0) {
            list.add(getAppConfig() .getQueueSub().take());
        }                      
    	Thread.currentThread().sleep(Timing.getTimeMaxSleep());                     
        while (basePhaser.getCurrNumPhase() != HqlStepsType.START.ordinal()) {
    		if(getAppConfig() .getQueueSub().size()>0)
    			getAppConfig() .getQueueSub().drainTo(list, MAX_NUM_ELEMENTS);
    	}     		
                      
        for(IContract msg: list){
        	IService service = getAppConfig().getServiceFactory().getService(EndpointType.LOG);          	      	             	        	        	
        	Map<String,String> map = new HashMap();
        	map.put("eventId","CHANGE_CUST");
        	        	
        	List<PubMsg> res1 = service.<PubMsg>readbyCriteria(PubMsg.class,map);
        	        	        	        	
        	for(IContract contract1: res1){
        		try{
        			if(!(contract1 instanceof PubMsg))
        				throw new Exception("Error has occured: bridge-service, SubTask, task, PubMsg");
        			
        			PubMsg pub = (PubMsg)contract1;        			        			        	
        			
        			String q2 = new String(String.format(
        					"SELECT * "
        					+ "	FROM jdb.`bus pub confirmation`; "
        					+ "WHERE `publisher_id` LIKE %s AND event_id LIKE %s "
        					+ "LIMIT 0, 1 ;"        					
        					,pub.getEndPointId()
        					,pub.getEventId()
        					));
        			
        			List<PubConfirmationMsg> res2 = null;//service.<PubConfirmationMsg>read(q, PubConfirmationMsg.class);
        			
        			for(IContract contract2: res2){        				
        				if(!(contract2 instanceof PubConfirmationMsg))
            				throw new Exception("Error has occured: bridge-service, SubTask, task, PubConfirmationMsg");
        				
        				PubConfirmationMsg con = (PubConfirmationMsg)contract2;
        				msg.setResponseURI(con.getPublisherHandler());
        				HttpResponse response = new HttpResponse();
    		    		response.reply(msg);
    		    		response = null;
        			}	               		        	        		      				        		 
        		}catch(Exception e){
        			e.printStackTrace();        		
        		}
        	}
        }
        list.clear();
	}
	
}
