package com.simple.server.statistics;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.factory.TaskRunner;
import com.simple.server.task.ITask;

@Service("perfomancerStat")
@Scope("prototype")
public class PerfomancerStat implements Statistic {
	@Autowired
	TaskRunner taskRunner;
	
    Long startTime;
    Long durationSum;
    Long measurementQty = 0l;
    Long unitTotalSum = 0l;
    Long unitSumPerTimeInterval = 0l;    
    
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Long endTime) {
        durationSum += endTime-startTime;
        measurementQty++;
    }

    public void setMeasurementQty(Long measurementQty) {
        this.measurementQty = measurementQty;
    }

    public Long getAvgDuration(){
        return durationSum/measurementQty;
    }

    public void setCurrUnitSum(int currUnitSum) {
        this.unitSumPerTimeInterval += currUnitSum;
    }
    
    @Override
    public void reset(){
    	this.startTime = 0l;
    	this.durationSum = 0l;
    	this.measurementQty = 0l;
    	this.unitTotalSum = 0l;
    	this.unitSumPerTimeInterval = 0l;
    }
    
    private void deleteStatFromTask(){
    	List<ITask> tasks = taskRunner.getAllTasks();
    	if(tasks != null)
	        for(ITask task: tasks){
	        	if(task.getStatistic() == this){
	        		task.setStatistic(null);
	        	}
	        }
    }
    
    @Override
    public void save(){
        unitTotalSum += unitSumPerTimeInterval;
        unitSumPerTimeInterval = 0l;
        measurementQty++;
        
    }

    public void addStatToTask(Class clazz){
        List<ITask> tasks = taskRunner.getTasksByClass(clazz);
        for(ITask task: tasks){
            task.setStatistic(this);
        }
    }

	@Override
	public void clear() {
		deleteStatFromTask();
		reset();		
	}

	@Override
	public long getUnitTotalSum() {
		return unitTotalSum;
	}

	@Override
	public long getMeasurementQty() {
		return measurementQty;
	}
}
