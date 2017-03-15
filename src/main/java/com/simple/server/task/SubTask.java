package com.simple.server.task;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.server.config.AppConfig;
import com.simple.server.config.EndpointType;
import com.simple.server.config.ErrorType;
import com.simple.server.config.MiscType;
import com.simple.server.domain.contract.ErrMsg;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.RoutingPubConfirmMsg;
import com.simple.server.domain.contract.PubErrRouting;
import com.simple.server.domain.contract.PubSuccessRouting;
import com.simple.server.domain.contract.SubErrRouting;
import com.simple.server.domain.contract.SubRouting;
import com.simple.server.domain.contract.SuccessMsg;
import com.simple.server.domain.contract.BusPubMsg;
import com.simple.server.http.HttpImpl;
import com.simple.server.http.IHttp;
import com.simple.server.lifecycle.HqlStepsType;
import com.simple.server.mediators.CommandType;
import com.simple.server.service.IService;
import com.simple.server.statistics.time.Timing;

@Service("SubTask")
@Scope("prototype")
public class SubTask extends ATask {

	private final static Integer MAX_NUM_ELEMENTS = 100000;
	private static Map<String, MiscType> orderMap = new HashMap();

	@Autowired
	private AppConfig appConfig;
	private List<IContract> list = new ArrayList<IContract>();
	private ObjectMapper mapper = new ObjectMapper();
	private IHttp http = new HttpImpl();

	static {
		orderMap.put("id", MiscType.desc);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null && arg.getClass() == CommandType.class) {
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

		if (getAppConfig().getQueueSub().drainTo(list, MAX_NUM_ELEMENTS) == 0) {
			list.add(getAppConfig().getQueueSub().take());
		}
		Thread.currentThread().sleep(Timing.getTimeMaxSleep());
		while (basePhaser.getCurrNumPhase() != HqlStepsType.START.ordinal()) {
			if (getAppConfig().getQueueSub().size() > 0)
				getAppConfig().getQueueSub().drainTo(list, MAX_NUM_ELEMENTS);
		}
		
		List<ErrMsg> errList = new ArrayList();
		List<SuccessMsg> successList = new ArrayList();
		List<SubErrRouting> subErrRoutesList = null;
		Map<String, Object> map = null;
		SubErrRouting subErrRouting = null;
		BusPubMsg pubLog = null;
		
		for (IContract msg : list) {
			try {

				IService service = getAppConfig().getServiceFactory().getService(EndpointType.LOG);
				map = new HashMap();
				map.put("senderId", msg.getSenderId());
				map.put("eventId", msg.getEventId());								
				subErrRoutesList = service.<SubErrRouting>readbyCriteria(SubErrRouting.class, map, 1, orderMap);
				map = null;
				if (subErrRoutesList == null || subErrRoutesList.size() == 0){									
					this.collectError(errList, 
									  msg, null,
									  new Exception(String.format("[routing sub err] - no records found by filters %s: < %s >, %s: <%s> ",
											  		"senderId", msg.getSenderId(), "eventId", msg.getEventId())));
				}
				else								
					subErrRouting = subErrRoutesList.get(0);
				
				
				map = new HashMap();
				map.put("juuid", msg.getJuuid());								
				List<BusPubMsg> pubLogList = service.<BusPubMsg>readbyCriteria(BusPubMsg.class, map, 1, orderMap);
				if (pubLogList == null || pubLogList.size() == 0){									
					this.collectError(errList, 
									  msg, subErrRouting,
									  new Exception(String.format("[log pub] - no records found by filter %s: < %s > ","juuid", msg.getJuuid())));
					continue;
				}
				
																																	
				pubLog = pubLogList.get(0);			
						
				
				map = new HashMap();	
				map.put("eventId", pubLog.getEventId());
				List<RoutingPubConfirmMsg> confList = service.<RoutingPubConfirmMsg>readbyCriteria(RoutingPubConfirmMsg.class, map, 1, orderMap);
				
				if (confList == null || confList.size() == 0){ 					
					this.collectError(errList, 
							  		  msg, subErrRouting,
							  		  new Exception(String.format("[routing pub confirmation] - no records found by filters %s: < %s >","eventId", pubLog.getEventId())));	
					continue;
				}
				

				RoutingPubConfirmMsg confirm = confList.get(0);
								
				if ((confirm.getPublisherHandler() == null || confirm.getPublisherHandler().equals(""))
				   && (confirm.getPublisherStoreClass() == null || confirm.getPublisherStoreClass().equals(""))) {
					
					this.collectError(errList, msg, subErrRouting,
									  new Exception("[routing pub confirmation].[id]: %s,  [subscriber_handler] && [subscriber_store_class] both are empty or null"));
				}
				
				try{				
					if (confirm.getPublisherHandler() != null || !confirm.getPublisherHandler().equals("")){
						msg.setResponseURI(confirm.getPublisherHandler());
						http.sendHttp(msg);																		
					}else
						if(confirm.getPublisherStoreClass() != null || !confirm.getPublisherStoreClass().equals("")){
							Class<IContract> clazz = (Class<IContract>) Class.forName(confirm.getPublisherStoreClass());
							Constructor<IContract> ctor = clazz.getConstructor();
							IContract instance = ctor.newInstance();
							instance.setEndPointId(confirm.getEndPointId());
							instance.setIsDirectInsert(confirm.getIsDirectInsert());
							instance.copyFrom(msg);
							appConfig.getQueueWrite().put(instance);
						}								
				}catch(Exception e){
					this.collectError(errList, msg, subErrRouting, new Exception(e.getMessage()));
				}
			}catch(Exception e){
				this.collectError(errList, msg, subErrRouting, new Exception(e.getMessage()));
			}	
		}
		sendErrors(errList);
		list.clear();
	}
	
	

	private void sendErrors(List<ErrMsg> errList) {
		for (ErrMsg err : errList) {
			try {
				if (err.getResponseURI() != null && !err.getResponseURI().isEmpty()) {
					http.sendHttp(err);
				} else if (err.getStoreClass() != null && !err.getStoreClass().isEmpty()) {
					IContract contract = null;
					if (err.getClass().getName().equals(err.getStoreClass())) {
						err.setIsDirectInsert(true);
						contract = err;
					} else {
						Class<IContract> clazz = (Class<IContract>) Class.forName(err.getStoreClass());
						Constructor<IContract> ctor = clazz.getConstructor();
						IContract instance = ctor.newInstance();
						instance.setEndPointId(err.getSenderId());
						instance.setIsDirectInsert(false);
						instance.copyFrom(err);
						contract = instance;
					}
					appConfig.getQueueWrite().put(contract);
				}
				appConfig.getQueueLog().put(err);
			} catch (Exception e) {
				try {
					ErrMsg newErr = new ErrMsg();
					newErr.setErrorId(ErrorType.PubTask);
					newErr.setDetails(String.format("SubTask: %s", e.getMessage()));
					newErr.setEventId(err.getEventId());
					newErr.setJuuid(err.getJuuid());
					newErr.setSenderId(err.getSenderId());
					newErr.setEndPointId(err.getSenderId());
					newErr.setSubscriberId(err.getSubscriberId());
					appConfig.getQueueLog().put(err);
					appConfig.getQueueLog().put(newErr);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		errList = null;
	}

	
	private void collectError(List<ErrMsg> list, IContract msg, SubErrRouting subErrRouting, Exception e) {
		ErrMsg err = null;
		String logDatetime = new SimpleDateFormat(AppConfig.DATEFORMAT).format(Calendar.getInstance().getTime());

		if (subErrRouting == null) {
			err = new ErrMsg();
			err.setErrorId(ErrorType.PubTask);
			err.setDetails(String.format("SubTask: %s", e.getMessage()));
			err.setEventId(msg.getEventId());
			err.setJuuid(msg.getJuuid());
			err.setSenderId(msg.getSenderId());
			err.setEndPointId(msg.getSenderId());			
			err.setLogDatetime(logDatetime);
			list.add(err);
		} else {
			
				err = new ErrMsg();
				err.setErrorId(ErrorType.PubTask);
				err.setDetails(String.format("SubTask: %s", e.getMessage()));
				err.setEventId(msg.getEventId());
				err.setJuuid(msg.getJuuid());
				err.setSenderId(msg.getSenderId());
				err.setEndPointId(msg.getSenderId());								
				if (subErrRouting != null) {
					err.setResponseURI(subErrRouting.getSubscriberHandler());
					err.setSubscriberId(subErrRouting.getSubscriberId());
					err.setSubscriberHandler(subErrRouting.getSubscriberHandler());
					err.setStoreClass(subErrRouting.getSubscriberStoreClass());
				}
				err.setLogDatetime(logDatetime);

				list.add(err);
			}
	}
}
