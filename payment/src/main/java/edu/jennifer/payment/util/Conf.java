package edu.jennifer.payment.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf {


	@Value("${icheck.enable}")
	private boolean icheckEnabled;

	@Value("${icheck.ip}")
	private String icheckIp;

	@Value("${icheck.port}")
	private String icheckPort;

	public boolean isIcheckEnabled() {return icheckEnabled;}

	public String getIcheckIp() { return icheckIp;}

	public String getIcheckPort() { return icheckPort;}

}
