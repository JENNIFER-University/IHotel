package edu.jennifer.hotel.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Get Application Version
 * @author Khalid Saeed <khalid@jennifersoft.com>
 * @Created 1/25/18 10:54 AM.
 */
public class Version {

    public static String getVersion() {
        try {
            Properties properties = new Properties();
            properties.load(Version.class.getClassLoader().getResourceAsStream("../../META-INF/MANIFEST.MF"));
            String version = properties.getProperty("Implementation-Version");
            return version;
        }catch (IOException io) {
            //Default (before this was implemented)
            return "2.0.0";
        }
    }
}
