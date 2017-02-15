package com.simple.server.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.server.domain.contract.AContract;
import com.simple.server.domain.contract.IContract;
import com.simple.server.lifecycle.HqlStepsType;
import com.simple.server.mediators.CommandType;
import com.simple.server.response.HttpResponse;
import com.simple.server.service.IService;
import com.simple.server.statistics.time.Timing;

@Service("ReadTask")
@Scope("prototype")
public class ReadTask extends ATask {
	
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
		if (getAppConfig() .getQueueRead().drainTo(list, MAX_NUM_ELEMENTS) == 0) {
            list.add(getAppConfig() .getQueueRead().take());
        }                      
    	Thread.currentThread().sleep(Timing.getTimeMaxSleep());                     
        while (basePhaser.getCurrNumPhase() != HqlStepsType.START.ordinal()) {
    		if(getAppConfig() .getQueueRead().size()>0)
    			getAppConfig() .getQueueRead().drainTo(list, MAX_NUM_ELEMENTS);
    	}     		
        for(IContract msg: list){
        	IService service = getAppConfig().getServiceFactory().getService(msg.getEndPointId());
        	List<IContract> res = service.read(msg);
        	for(IContract r: res){
        		try{
        			AContract a = (AContract)r;        		
		        	if(a.getResponseURI()==null)
		        		throw new Exception("TODO");		        		
        			HttpResponse response = new HttpResponse();
		    		response.reply(r);
		    		response = null;
        		}catch(Exception e){        			
        		}
        	}
        }
        list.clear();
	}
}
