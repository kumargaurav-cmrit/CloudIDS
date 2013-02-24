package com.ayu.filter;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class SimpleCache {
 int num ;
 String str;Timer str1;
 LinkedHashMap<String, Timer> map;

 public SimpleCache(final int num) throws IOException{
 try{
 //this.str = str;
 //this.str1 = str1;
 this.num = num;
 }
 catch(NumberFormatException ne){
 System.exit(0);
 }
 map = new LinkedHashMap<String,Timer>() {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public boolean removeEldestEntry (Map.Entry<String,Timer> eldest){
 return size() > num;
 }
 };
 //map.put (str, str1);
 }

 public synchronized ArrayList<Entry<String, Timer>> getAll() {
 return new ArrayList<Map.Entry<String,Timer>>(map.entrySet());
 }
}
