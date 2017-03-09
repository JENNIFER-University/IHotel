package edu.jennifer.ipayment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conf {

	private static Conf inst = null;
	private static Properties prop = null;
	
	public static synchronized Conf getInstance(){
		try{
			if(inst == null)
				inst = new Conf();
			return inst;
		}catch (Exception ex){
			return null;
		}

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

	public boolean icheckEnabled(){
		return prop.getProperty("icheck_enabled","false").equalsIgnoreCase("true");
	}

	public String getICheckIP(){
		return prop.getProperty("icheck_ip","localhost");
	}

	public String getICheckPort(){
		return prop.getProperty("icheck_port","1099");
	}

}
