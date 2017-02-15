package com.simple.server.mediators;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.simple.server.task.ITask;


public class Subscriber {
    private ConcurrentHashMap<ITask,Condition> concurrentHashMap = new ConcurrentHashMap<>();

    public void subscribeComplex(List<ITask> subscriberList, Object... events){
        for(ITask task: subscriberList) {
            if (!concurrentHashMap.containsKey(task)){
                Condition condition = new Condition(task, events);
                concurrentHashMap.put(task, condition);
                task.setSubscriber(this);
            }
            else{
                Condition condition = (Condition)concurrentHashMap.get(task);
                condition.setCondition(events);
            }
        }
    }

    public void subscribeComplex(ITask subscriber, Object... events){
            if (!concurrentHashMap.containsKey(subscriber)){
                Condition condition = new Condition(subscriber, events);
                concurrentHashMap.put(subscriber, condition);
                subscriber.setSubscriber(this);
            }
    }

    public Boolean checkCondition(ITask subscriber, Object event){
        if(event == null)
            return false;
        if (concurrentHashMap.containsKey(subscriber)) {
            Condition condition = (Condition)concurrentHashMap.get(subscriber);
            return condition.checkCondition(event);
        }
        return false;
    }

    private class Condition{
        Object task;
        private ConcurrentHashMap<Object,Boolean> or = new ConcurrentHashMap<>();
        private ConcurrentHashMap<Object, Integer> and = new ConcurrentHashMap<>();
        private int bitmask_events = 0;
        private int bitmask_invert = 0;

        public Condition(Object task, Object... events){
            this.task = task;
            setCondition(events);
        }

        private void setCondition(Object... events) {
            Object[] arrEvents = events;

            if(arrEvents.length==1)
                or.put(arrEvents[0],true);
            else {
                for (int i = 0; i < arrEvents.length; i++)
                    and.put(arrEvents[i], i);

                for (int i = arrEvents.length; i < 8; i++)
                    bitmask_invert |= (1 << i);
            }
        }

        public Boolean checkCondition(Object event){
            if(event == null)
                return false;

            if(or.containsKey(event))
                return true;

            if (and.containsKey(event)) {
                int num = and.get(event);
                bitmask_events |= (1 << num);
            }

            if((bitmask_invert | bitmask_events) == 255) {
                bitmask_events = 0;
                return true;
            }
            return false;
        }


        public int getBitmask_invert(){
            return bitmask_invert;
        }
    }
}
