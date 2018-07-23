package edu.jennifer.stress;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import edu.jennifer.stress.factory.BrowserTypeFactory;
import edu.jennifer.stress.factory.UserFactory;
import edu.jennifer.stress.model.CliParams;
import edu.jennifer.stress.model.User;
import edu.jennifer.stress.simula.ThreadsController;

import edu.jennifer.stress.util.AppUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Calendar;

/**
 * @author Khalid
 * @Created 10/24/17 2:48 PM.
 */


public class Main {

	final CliParams cliParams = new CliParams();
	private final String appName = "istress";

	
	public static void main(String[] args) {

		Main app = new Main();
		app.showInfo();
		app.handleInput(args);
		app.run();
	}

	private void run() {
		ThreadsController runner = new ThreadsController(this.cliParams);
		runner.start();
	}

	private void handleInput(String[] args) {
		JCommander jCommander = new JCommander(cliParams);
		jCommander.setProgramName(appName);
		try {
			jCommander.parse(args);
		}catch (ParameterException e) {
			System.out.printf("Error: %s%n", e.getMessage());
			showUsage(jCommander);
		}

	}

	private void showUsage(JCommander jCommander) {
		jCommander.usage();
		System.exit(0);
	}

	private void showInfo() {
		System.out.println("JENNIFER iHotel Simula v2.0");
		System.out.println("Copyright (c) JenniferSoft 1998,"+Calendar.getInstance().get(Calendar.YEAR)+". All rights reserved.\n");
	}

}
