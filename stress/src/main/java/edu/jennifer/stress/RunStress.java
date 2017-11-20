package edu.jennifer.stress;

import java.util.Calendar;
import java.util.HashSet;

public class RunStress {

	public static void main(String[] args) throws Exception{
		System.out.println("JENNIFER iHotel Simula v1.0");
		System.out.println("Copyright (c) JenniferSoft 1998,"+Calendar.getInstance().get(Calendar.YEAR)+". All rights reserved.\n");

		String ihotelUrl = "";
		int maxUser;
		if (args.length > 0)
		{
			try
			{
				maxUser = Integer.parseInt(args[0]);
				System.out.println("Max Users [" + maxUser + "]");
			}
			catch (NumberFormatException ex)
			{
				maxUser = 50;
				System.out.println("Failed to parse max user.Using default value [50]");
			}
			if ((args[1].length() > 0) && (args[1].startsWith("http://")))
			{
				ihotelUrl = args[1];
				System.out.println("Base Url " + ihotelUrl);
			}
			else
			{
				System.out.println("Invalid url. Exit application");
				System.exit(0);
			}
		}
		else
		{
			System.out.println("Using default params:");
			System.out.println("\tMax Users [50]");
			maxUser = 50;
			System.out.println("\tiHotel Base Url [http://localhost:8080]");
			ihotelUrl = "http://localhost:8080";
		}
		boolean debug = false;
		if (args.length == 3)
		{
			System.out.println("Debug mode is on");
			debug = true;
		}

		StressRunner runner = new StressRunner(maxUser, ihotelUrl, debug);
		runner.start();
	}
	

	static class StressRunner extends Thread{
		private String appUrl;
		private boolean debug;
		int maxUsers;
		
		public StressRunner(int maxUsers, String appUrl,boolean debug){
			this.maxUsers = maxUsers;
			this.appUrl = appUrl;
			this.debug = debug;
		}
		
		@Override
		public void run() {
			System.out.println("Starting Stress...");
			HashSet<String> activeUsers = new HashSet<String>();
			int activeUsersNumber = 0;
			for(;;){
				int neededUsers;
				synchronized (activeUsers) {
					neededUsers = this.maxUsers - activeUsers.size();
				}
				if(neededUsers > 0) {
					String userId = "User [" + activeUsersNumber+"]";
					synchronized (activeUsers){
						if(!activeUsers.contains(userId)){
							activeUsersNumber++;
							new VirtualUser(activeUsers, userId, this.appUrl, this.debug).start();
						}
					}

					if((activeUsersNumber >= neededUsers) || (activeUsersNumber >= this.maxUsers)){
						activeUsersNumber = 0;
					}
				}

				try{Thread.sleep(1000L);}catch (Exception ex){}
			}
		}
	}
}
