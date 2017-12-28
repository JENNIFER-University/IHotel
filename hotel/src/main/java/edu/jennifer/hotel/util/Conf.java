package edu.jennifer.hotel.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class Conf {

	Logger logger = LogManager.getRootLogger();


	public static final String KEY_IPAYMENT_IP		= "ipayment_ip";
	public static final String KEY_IPAYMENT_PORT	= "ipayment_port";

	private static Conf inst = null;
	private static Properties prop = null;

	public static synchronized Conf getInstance(){
		if(inst == null)
			inst = new Conf();
		return inst;
	}
	
	public Conf(){
		try {
			prop = load();
		}catch (IOException ex) {
			logger.error("Failed to load the configuration file. Reason: " + ex.getMessage());
		}
	}

	public void reload() {
		try {
			prop = load();
		}catch (Exception ex){
			logger.error("Failed to reload the configuration file. Reason: " + ex.getMessage());
		}
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

	public boolean isConfFileExists() {
		return new File("app.conf").exists();
	}

	public boolean saveProperty(String key, String value) {
		try{
			File configFile = new File("app.conf");
			if (configFile.exists()) {
				updateProperty(configFile, key, value);
			}else {
				String prop = String.format("%s=%s",key, value);
				FileWriter writer = new FileWriter(configFile);
				writer.append(prop + "\n");
				writer.close();
			}

			return true;
		}catch (Exception ex) {
			logger.error("Failed to save the property. Reason: " + ex.getMessage());
			return false;
		}

	}

	private void updateProperty(File configFile, String key, String value) throws IOException {
		final File tmpFile = new File(configFile.getName() + ".tmp");
		PrintWriter pw = new PrintWriter(tmpFile);
		BufferedReader br = new BufferedReader(new FileReader(configFile));
		boolean found = false;
		final String toAdd = key + '=' + value;
		for (String line; (line = br.readLine()) != null; ) {
			if (line.startsWith(key + '=')) {
				line = toAdd;
				found = true;
			}
			pw.println(line);
		}
		if (!found)
			pw.println(toAdd);
		br.close();
		pw.close();
		tmpFile.renameTo(configFile);
	}
}
