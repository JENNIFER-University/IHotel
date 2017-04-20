package edu.jennifer.ihotel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conf {

	public static final String IPAYMENT_URL 					= "ipaymenturl";
	public static final String DEFAULT_IPAYMENT_URL 			= "http://localhost:18080";
	
	private static Conf inst = null;
	private static Properties prop = null;
	
	public static synchronized Conf getInstance() throws Exception{
		if(inst == null)
			inst = new Conf();
		return inst;
	}
	
	public Conf() throws IOException{
		prop = load();
	}
	
	private Properties load() throws IOException{
		File configFile = new File("app.conf");
		InputStream in  = new FileInputStream(configFile);
		Properties prop = new Properties();
		prop.load(in);
		return prop;
	}
	
	public String getProperty(String key){
		return prop.getProperty(key);
	}
}
