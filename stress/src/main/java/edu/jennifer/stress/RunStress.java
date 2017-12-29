package edu.jennifer.stress;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import edu.jennifer.stress.model.CliParams;
import edu.jennifer.stress.runner.StressRunner;
import edu.jennifer.stress.runner.VirtualUser;

import java.util.Calendar;
import java.util.HashSet;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 10/24/17 2:48 PM.
 */

public class RunStress {

	final CliParams cliParams = new CliParams();
	private final String appName = "istress";

	
	public static void main(String[] args) {
		RunStress app = new RunStress();
		app.showInfo();
		app.handleInput(args);
		app.run();
	}

	private void run() {
		StressRunner runner = new StressRunner(this.cliParams);
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
