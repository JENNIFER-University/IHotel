package edu.jennifer.ihotel.config;

import edu.jennifer.ihotel.util.ConnectionUtil;
import edu.jennifer.ihotel.util.DBCleaner;
import edu.jennifer.ihotel.util.Version;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Khalid Elshafie
 * @Created 9/7/17 12:00 PM.
 */
@WebListener
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.printf("%s%n",loadSignature());
        System.out.printf("Version: %s%n", Version.getVersion());
        System.out.printf("Checking Database Connection ... %n");
        boolean dataSourceExists = ConnectionUtil.getInstance().getDataSource() == null ? false : true;

        if (!dataSourceExists) {
            System.out.printf("DataSource not found. Please create datasource in your tomcat configuration%n");
            System.out.printf("DataSource Name [%s]%n", ConnectionUtil.DATASOURCE_NAME);
        }else {
            System.out.printf("Cleaning up Reservation Table ....%n ");
            new DBCleaner().cleanReservationTable();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}


    private String loadSignature() {
        StringBuilder signature = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        File signatureFile = new File(classLoader.getResource("sig").getFile());
        try(Scanner scanner = new Scanner(signatureFile)) {
            while(scanner.hasNextLine()) {
                signature.append(scanner.nextLine()).append("\n");
            }
            return signature.toString();
        }catch (IOException io){
            return "iHotel";
        }

    }
}
