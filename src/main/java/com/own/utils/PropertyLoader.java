package com.own.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

private  Properties prop = null;
	
	public  String get(String key)throws Exception {
		if(null == prop) {
			init();
		}
		
		return (String) prop.get(key);
	}
	
	private  void init()throws Exception {
		prop = new Properties();
	
		InputStream in = new FileInputStream(new File("config/FilePath.properties"));
		
		try {
			prop.load(in);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
