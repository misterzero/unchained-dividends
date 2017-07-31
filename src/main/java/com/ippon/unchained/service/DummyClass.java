package com.ippon.unchained.service;

import java.util.HashMap;

public class DummyClass {
	public static final HashMap<Integer,Double> dividendsAmount;
	static{
		dividendsAmount= new HashMap<Integer,Double>();
		dividendsAmount.put(1, 24.2);
		dividendsAmount.put(2, 745.0);
		dividendsAmount.put(3, 1000.0);
		dividendsAmount.put(4, 10000.2);
	}
	public static final HashMap<Integer,Integer> valueOfTheCompany;
	static{
		valueOfTheCompany = new HashMap<Integer,Integer>();
		valueOfTheCompany.put(1, 100);
		valueOfTheCompany.put(2, 1000);
		valueOfTheCompany.put(3, 10000);
		valueOfTheCompany.put(4, 100000);
	}
	
	public static double getDividendsAmount(){
		double n;
		n = Math.random();
		if(n<0.25)
			return dividendsAmount.get(1);
		else if(n<0.5 && n>0.25)
			return dividendsAmount.get(2);
		else if(n<0.75 && n>0.5)
			return dividendsAmount.get(3);
		else
			return dividendsAmount.get(4);
	}
	
	public static double getValueOfTheCompany(){
		double n;
		n = Math.random();
		if(n<0.25)
			return valueOfTheCompany.get(1);
		else if(n<0.5 && n>0.25)
			return valueOfTheCompany.get(2);
		else if(n<0.75 && n>0.5)
			return valueOfTheCompany.get(3);
		else
			return valueOfTheCompany.get(4);
	}
	
	public static int getValueOfOneToken(double val){
		return (int) (val/200);
	}
}
