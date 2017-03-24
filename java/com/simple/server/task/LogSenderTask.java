package com.simple.server.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.IContract;
import com.simple.server.mediators.CommandType;
import com.simple.server.service.log.ILogService;
import com.simple.server.statistics.time.Timing;


@Service("LogSenderTask")
@Scope("prototype")
public class LogSenderTask extends ATask {
	
	@Autowired
	private AppConfig appConfig;
	
	private static Integer MAX_NUM_ELEMENTS = 1000;
    private List<IContract> list = new ArrayList<>();
    
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
        if(appConfig.getQueueLog().drainTo(list, MAX_NUM_ELEMENTS)==0){
            list.add(appConfig.getQueueLog().take());
        }             
        Thread.currentThread().sleep(Timing.getTimeMaxSleep());	
        appConfig.getQueueLog().drainTo(list, MAX_NUM_ELEMENTS);  
                 
        for (IContract msg : list) {        	
        	Thread.currentThread().sleep(Timing.getTimeMaxSleep());	     	        	
        	
        	ILogService logService = (ILogService)appConfig.getLogService(); 
        	logService.sendAsIs(appConfig.getChannelBusLog(), msg);          		
        }
                   
        throwToStatistic(list.size());
        list.clear();
    }    	
}
