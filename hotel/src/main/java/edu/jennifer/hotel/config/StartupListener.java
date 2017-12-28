package edu.jennifer.hotel.config;

import edu.jennifer.hotel.startup.UserMaker;
import edu.jennifer.hotel.util.ConnectionUtil;
import edu.jennifer.hotel.startup.DBCleaner;
import edu.jennifer.hotel.util.Version;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    Logger logger = LogManager.getRootLogger();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.printf("%s%n",loadSignature());
        System.out.printf("Version: %s%n", Version.getVersion());
        logger.info("Checking Database Connection");
        boolean dataSourceExists = ConnectionUtil.getInstance().getDataSource() == null ? false : true;

        if (!dataSourceExists) {
            logger.error("DataSource not found. Please create datasource in your tomcat configuration");
            logger.info(String.format("DataSource Name [%s]", ConnectionUtil.DATASOURCE_NAME));
        }else {
            logger.info("Cleaning up Reservation Table .... ");
            new DBCleaner().cleanReservationTable();

            logger.info("Creating Users .... ");
            new UserMaker().createUsers();
        }

        logger.info("iHotel Startup initialization completed");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}


    /**
     * Load the iHotel Signature and print it out to the console
     * @return iHotel Signature
     */
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