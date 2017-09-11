package edu.jennifer.ipayment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conf {

	private static Conf inst = null;
	private static Properties prop = null;
	
	public static Conf getInstance(){
		if (inst == null) {
			inst = new Conf();
		}
		return inst;
	}
	
	public Conf(){
		prop = load();
	}
	
	private Properties load(){
		try{
			File configFile = new File("app.conf");
			InputStream in  = new FileInputStream(configFile);
			Properties prop = new Properties();
			prop.load(in);
			return prop;
		}catch (IOException io) {
			return null;
		}
	}

	public boolean icheckEnabled(){
		if (prop == null)
			return false;
		return prop.getProperty("icheck_enabled","false").equalsIgnoreCase("false");
	}

	public String getICheckIP(){
		return prop.getProperty("icheck_ip","localhost");
	}

	public String getICheckPort(){
		return prop.getProperty("icheck_port","1099");
	}

}
