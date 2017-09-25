package com.simple.server.task;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.server.config.AppConfig;
import com.simple.server.config.ContentType;
import com.simple.server.config.EndpointType;
import com.simple.server.config.ErrorType;
import com.simple.server.config.OperationType;
import com.simple.server.domain.contract.ErrPubMsg;
import com.simple.server.domain.contract.HotPubMsg;
import com.simple.server.domain.contract.SubRouting;
import com.simple.server.domain.contract.SuccessPubMsg;
import com.simple.server.domain.contract.UniMinMsg;
import com.simple.server.domain.contract.UniMsg;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.PubErrRouting;
import com.simple.server.domain.contract.PubSuccessRouting;
import com.simple.server.domain.contract.BusPubMsg;
import com.simple.server.http.HttpImpl;
import com.simple.server.http.IHttp;
import com.simple.server.mediators.CommandType;
import com.simple.server.service.IService;
import com.simple.server.statistics.time.Timing;
import com.simple.server.util.DateConvertHelper;

@Service("PubTask")
@Scope("prototype")
public class PubTask extends ATask {

	@Autowired
	private AppConfig appConfig;

	private final static Integer MAX_NUM_ELEMENTS = 100000;
	private List<IContract> list = new ArrayList<IContract>();

	private IHttp http = new HttpImpl();

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
		if (getAppConfig().getQueuePub().drainTo(list, MAX_NUM_ELEMENTS) == 0) {
			list.add(getAppConfig().getQueuePub().take());
		}
		Thread.currentThread().sleep(Timing.getTimeMaxSleep());
		// while (basePhaser.getCurrNumPhase() != HqlStepsType.START.ordinal())
		// {
		// if (getAppConfig().getQueuePub().size() > 0)
		// getAppConfig().getQueuePub().drainTo(list, MAX_NUM_ELEMENTS);
		// }

		IService service = getAppConfig().getServiceFactory().getService(EndpointType.LOG);
		List<PubErrRouting> pubErrRoutes = null;
		List<PubSuccessRouting> pubSuccessRoutes = null;
		List<ErrPubMsg> errList = new ArrayList();
		List<SuccessPubMsg> successList = new ArrayList();
		List<SubRouting> subRoutes = null;
		String logDatetime = DateConvertHelper.getCurDate();
		try {
			for (IContract msg : list) {
				try {
					if (msg.getPublisherId().equals(EndpointType.UNKNOWN)) {
						msg.setPublisherId(msg.getSenderId());
					}

					HotPubMsg hotPubMsg = new HotPubMsg();
					hotPubMsg.copyFrom(msg);
					hotPubMsg.setLogDatetime(logDatetime);
					service.insertAsIs(hotPubMsg);

					Map<String, Object> map = new HashMap();
					map.put("eventId", msg.getEventId());
					map.put("publisherId", msg.getSenderId());
					pubErrRoutes = service.<PubErrRouting>readbyCriteria(PubErrRouting.class, map, 1, null);
					if (pubErrRoutes == null || pubErrRoutes.size() == 0) {
						this.collectError(errList, msg, null,
								new Exception(String.format(
										"[routing PUB err] - no records found by filters %s: < %s >, %s: < %s > ",
										"[event_id]", msg.getEventId(), "[publisher_id]", msg.getSenderId())),
								null);
					}

					pubSuccessRoutes = service.<PubSuccessRouting>readbyCriteria(PubSuccessRouting.class, map, 1, null);
					if (pubSuccessRoutes == null || pubSuccessRoutes.size() == 0) {
						this.collectError(errList, msg, null,
								new Exception(String.format(
										"[routing PUB success] - no records by filters %s: < %s >, %s: < %s > ",
										"[event_id]", msg.getEventId(), "[publisher_id]", msg.getSenderId()

								)), null);
					}

					map = new HashMap();
					map.put("eventId", msg.getEventId());
					map.put("senderId", msg.getSenderId());
					if (msg.getSubscriberId() != null && msg.getSubscriberId() != EndpointType.UNKNOWN)
						map.put("subscriberId", msg.getSubscriberId());
					subRoutes = service.<SubRouting>readbyCriteria(SubRouting.class, map, 0, null);
					if (subRoutes == null || subRoutes.size() == 0) {
						this.collectError(errList, msg, null,
								new Exception(String.format(
										"[routing SUB] - no records found by filters %s: < %s >, %s: < %s > ",
										"eventId", msg.getEventId(), "senderId", msg.getSenderId())),
								pubErrRoutes);
						continue;
					}

					SubRouting subRoute = null;
					for (IContract r : subRoutes) {
						try {
							subRoute = (SubRouting) r;

							if ((subRoute.getSubscriberStoreClass() == null
									|| subRoute.getSubscriberStoreClass().equals(""))
									&& (subRoute.getSubscriberHandler() == null
											|| subRoute.getSubscriberHandler().equals(""))) {
								this.collectError(errList, msg, subRoute,
										new Exception(String.format(
												"[routing SUB].[id]: %s,  [subscriber_handler] && [subscriber_store_class] both are empty or null",
												subRoute.getId())),
										pubErrRoutes);
								continue;
							}

							if (subRoute.getSubscriberHandler() != null
									&& !subRoute.getSubscriberHandler().equals("")) {
								msg.setResponseURI(subRoute.getSubscriberHandler());

								IContract newMsg = null;
								if (msg instanceof UniMsg) {
									UniMinMsg uniMinMsg = new UniMinMsg();

									UniMsg oldMsg = (UniMsg) msg;
									uniMinMsg.setBody(oldMsg.getBody());
									uniMinMsg.setEventId(oldMsg.getEventId());
									uniMinMsg.setJuuid(oldMsg.getJuuid());
									uniMinMsg.setDatetime(oldMsg.getLogDatetime());
									uniMinMsg.setContentType(subRoute.getResponseContentType());
									uniMinMsg.setUrl(oldMsg.getResponseURI());
									uniMinMsg.bodyTransform(subRoute.getBodyContentType());
									http.sendHttp(uniMinMsg, uniMinMsg.getUrl(), uniMinMsg.getContentType(),
											subRoute.getUseAuth());
								} else {
									newMsg = msg;
									http.sendHttp(newMsg, newMsg.getResponseURI(), newMsg.getResponseContentType(),
											subRoute.getUseAuth());
								}

							} else if (subRoute.getSubscriberStoreClass() != null
									&& !subRoute.getSubscriberStoreClass().equals("")) {
								Class<IContract> clazz = (Class<IContract>) Class
										.forName(subRoute.getSubscriberStoreClass());
								Constructor<IContract> ctor = clazz.getConstructor();
								IContract instance = ctor.newInstance();
								instance.setEndPointId(subRoute.getSubscriberId());
								instance.setIsDirectInsert(subRoute.getIsDirectInsert());
								instance.copyFrom(msg);
								instance.bodyTransform(subRoute.getBodyContentType());
								appConfig.getQueueWrite().put(instance);
							}

							this.collectSuccess(successList, msg, subRoute, pubSuccessRoutes);

							BusPubMsg pubMsg = new BusPubMsg();
							pubMsg.copyFrom(msg);
							appConfig.getQueueLog().put(pubMsg);
						} catch (Exception e) {
							this.collectError(errList, msg, subRoute, new Exception(e.getMessage()), pubErrRoutes);
						}
					}
				} catch (Exception e) {
					this.collectError(errList, msg, null, new Exception(e.getMessage()), pubErrRoutes);
				}
			}
		} catch (Exception e) {
			// exception in collectError
			// TODO Exception to log
		} finally {
			sendErrors(errList);
			sendSuccess(successList);
			this.throwToStatistic(list.size());
			list.clear();
		}
	}

	private void sendErrors(List<ErrPubMsg> errList) {
		for (ErrPubMsg err : errList) {
			try {
				if (err.getResponseURI() != null && !err.getResponseURI().isEmpty()) {
					err.setResponseContentType(ContentType.ApplicationJson);
					http.sendHttp(err, err.getResponseURI(), err.getResponseContentType(), false);
				} else if (err.getStoreClass() != null && !err.getStoreClass().isEmpty()) {
					IContract contract = null;
					if (err.getClass().getName().equals(err.getStoreClass())) {
						err.setIsDirectInsert(true);
						err.setResponseContentType(ContentType.ApplicationJson);
						contract = err;
					} else {
						Class<IContract> clazz = (Class<IContract>) Class.forName(err.getStoreClass());
						Constructor<IContract> ctor = clazz.getConstructor();
						IContract instance = ctor.newInstance();
						instance.setEndPointId(err.getSenderId());
						instance.setIsDirectInsert(false);
						instance.setResponseContentType(ContentType.ApplicationJson);
						instance.copyFrom(err);
						contract = instance;
					}
					appConfig.getQueueWrite().put(contract);
				}
				appConfig.getQueueLog().put(err);
			} catch (Exception e) {
				try {
					ErrPubMsg newErr = new ErrPubMsg();
					newErr.setErrorId(ErrorType.PubTask.toValue());
					newErr.setOperationType(OperationType.PUB);
					newErr.setResponseContentType(ContentType.ApplicationJson);
					if (e.getCause() != null)
						newErr.setDetails(String.format("%s: %s", e.getMessage(), e.getCause()));
					else
						newErr.setDetails(String.format("%s", e.getMessage()));
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

	private void sendSuccess(List<SuccessPubMsg> successList) {
		for (SuccessPubMsg success : successList) {
			try {
				if (success.getResponseURI() != null && !success.getResponseURI().isEmpty()) {
					success.setResponseContentType(ContentType.ApplicationJson);
					http.sendHttp(success, success.getResponseURI(), success.getResponseContentType(), false);
				} else if (success.getStoreClass() != null && !success.getStoreClass().isEmpty()) {
					IContract contract = null;
					if (success.getClass().getName().equals(success.getStoreClass())) {
						success.setIsDirectInsert(true);
						success.setResponseContentType(ContentType.ApplicationJson);
						contract = success;
					} else {
						Class<IContract> clazz = (Class<IContract>) Class.forName(success.getStoreClass());
						Constructor<IContract> ctor = clazz.getConstructor();
						IContract instance = ctor.newInstance();
						instance.setEndPointId(success.getSenderId());
						instance.setIsDirectInsert(false);
						instance.setResponseContentType(ContentType.ApplicationJson);
						instance.copyFrom(success);
						contract = instance;
					}
					appConfig.getQueueWrite().put(contract);
				}
				appConfig.getQueueLog().put(success);
			} catch (Exception e) {
				try {
					ErrPubMsg newErr = new ErrPubMsg();
					newErr.setErrorId(ErrorType.PubTask.toString());
					if (e.getCause() != null)
						newErr.setDetails(String.format("%s: %s", e.getMessage(), e.getCause()));
					else
						newErr.setDetails(String.format("%s", e.getMessage()));
					newErr.setEventId(success.getEventId());
					newErr.setJuuid(success.getJuuid());
					newErr.setSenderId(success.getSenderId());
					newErr.setEndPointId(success.getSenderId());
					newErr.setSubscriberId(success.getSubscriberId());
					appConfig.getQueueLog().put(success);
					appConfig.getQueueLog().put(newErr);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		successList = null;
	}

	private void collectError(List<ErrPubMsg> list, IContract msg, SubRouting subRouting, Exception e,
			List<PubErrRouting> routings) {
		ErrPubMsg err = null;
		String logDatetime = DateConvertHelper.getCurDate();

		if (routings == null) {
			err = new ErrPubMsg();
			err.setErrorId(ErrorType.PubTask.toValue());
			err.setOperationType(OperationType.PUB);
			if (e.getCause() != null)
				err.setDetails(String.format("%s: %s", e.getMessage(), e.getCause()));
			else
				err.setDetails(String.format("%s", e.getMessage()));
			err.setEventId(msg.getEventId());
			err.setJuuid(msg.getJuuid());
			err.setSenderId(msg.getSenderId());
			err.setEndPointId(msg.getSenderId());
			if (subRouting != null) {
				err.setSubscriberId(subRouting.getSubscriberId());
			}
			err.setLogDatetime(logDatetime);
			list.add(err);

		} else {
			for (PubErrRouting routing : routings) {
				err = new ErrPubMsg();
				err.setErrorId(ErrorType.PubTask.toValue());
				err.setOperationType(OperationType.PUB);
				if (e.getCause() != null)
					err.setDetails(String.format("%s: %s", e.getMessage(), e.getCause()));
				else
					err.setDetails(String.format("%s", e.getMessage()));
				err.setEventId(msg.getEventId());
				err.setJuuid(msg.getJuuid());
				err.setSenderId(msg.getSenderId());
				err.setEndPointId(msg.getSenderId());

				if (routing.getPublisherHandler() != null && routing.getPublisherHandler() != "")
					err.setResponseURI(routing.getPublisherHandler());
				if (routing.getPublisherStoreClass() != null && routing.getPublisherStoreClass() != "")
					err.setStoreClass(routing.getPublisherStoreClass());

				if (subRouting != null) {
					err.setSubscriberId(subRouting.getSubscriberId());
					err.setSubscriberHandler(subRouting.getSubscriberHandler());
				}
				err.setLogDatetime(logDatetime);

				list.add(err);
			}
		}
	}

	private void collectSuccess(List<SuccessPubMsg> list, IContract msg, SubRouting subRouting,
			List<PubSuccessRouting> routings) {
		SuccessPubMsg success = null;
		String logDatetime = DateConvertHelper.getCurDate();

		if (routings == null || routings.size() == 0) {
			success = new SuccessPubMsg();
			success.setEventId(msg.getEventId());
			success.setJuuid(msg.getJuuid());
			success.setSenderId(msg.getSenderId());
			success.setEndPointId(msg.getSenderId());
			success.setResponseContentType(ContentType.ApplicationJson);
			if (subRouting != null) {
				success.setSubscriberHandler(subRouting.getSubscriberHandler());
				success.setSubscriberStoreClass(subRouting.getSubscriberStoreClass());
				success.setSubscriberId(subRouting.getSubscriberId());
			}
			success.setLogDatetime(logDatetime);
			list.add(success);
		} else {
			for (PubSuccessRouting routing : routings) {
				success = new SuccessPubMsg();
				success.setResponseContentType(ContentType.ApplicationJson);
				success.setEventId(msg.getEventId());
				success.setJuuid(msg.getJuuid());
				success.setSenderId(msg.getSenderId());
				success.setEndPointId(msg.getSenderId());
				success.setResponseURI(routing.getPublisherHandler());
				success.setStoreClass(routing.getPublisherStoreClass());
				success.setLogDatetime(logDatetime);
				if (subRouting != null) {
					success.setSubscriberId(subRouting.getSubscriberId());
					success.setSubscriberHandler(subRouting.getSubscriberHandler());
					success.setSubscriberStoreClass(subRouting.getSubscriberStoreClass());
				}
				list.add(success);
			}
		}
	}
}
