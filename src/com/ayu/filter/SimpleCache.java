package com.ayu.filter;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class SimpleCache {
 int num ;
 String str;int str1;
 LinkedHashMap<String, Integer> map;

 public SimpleCache(String str,int str1,final int num) throws IOException{
 try{
 this.str = str;
 this.str1 = str1;
 this.num = num;
 }
 catch(NumberFormatException ne){
 System.exit(0);
 }
 map = new LinkedHashMap<String,Integer>() {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public boolean removeEldestEntry (Map.Entry<String,Integer> eldest){
 return size() > num;
 }
 };
 map.put (str, str1);
 }

 public synchronized ArrayList<Entry<String, Integer>> getAll() {
 return new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
 }
}
