package com.ayu.filter;

public class Timer {
	long Start_time,interval,temp,End_time;int count;
	public Timer()
	{
		this.Start_time=System.currentTimeMillis();
		this.temp = Start_time;
		this.count = 1;
		
		
	}
	public boolean check(long End_time)
	{
		
		if((End_time-temp)<=15000)
		{	//System.out.println("Start time is"+Start_time);
			//System.out.println("Difference is"+(End_time-temp));
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
