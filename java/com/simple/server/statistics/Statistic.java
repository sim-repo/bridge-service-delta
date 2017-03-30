package com.simple.server.statistics;

public interface Statistic {
    public void setCurrUnitSum(int currUnitSum);
    public void save();
    public void reset();
    public void clear();
	public void addStatToTask(Class clazz);
	public long getUnitTotalSum();
	public long getMeasurementQty();
}