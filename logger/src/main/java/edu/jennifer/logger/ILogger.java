package edu.jennifer.logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Khalid
 * @Created 7/24/18 12:03 PM.
 */
public class ILogger {

    private static Logger LOGGER;
    static {
        LOGGER = LoggerFactory.getLogger(getCallersName());
    }

    public static void debug(String msg)
    {
        LOGGER.debug(msg);
    }

    public static void info(String msg)
    {
        LOGGER.info(msg);
    }

    public static void warn(Throwable t)
    {
        LOGGER.warn(toString(t));
    }

    public static void warn(String text)
    {
        LOGGER.warn(text);
    }

    public static void error(Throwable t)
    {
        LOGGER.error(toString(t));
    }

    public static void error(String text)
    {
        LOGGER.error(text);
    }

    public static void error(String text, Throwable t)
    {
        LOGGER.error(text + " throwable=" + toString(t));
    }

    private static String toString(Throwable t)
    {
        return getStackTrace(t).trim();
    }

    private static String getStackTrace(Throwable throwable) {
        if (throwable != null)
        {
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            throwable.printStackTrace(printWriter);
            printWriter.flush();
            printWriter.close();

            return writer.toString();
        }

        return "";
    }

    private static String getCallersName() {
        try {
            return Thread.currentThread().getStackTrace()[3].getClassName();
        }catch (Exception ex) {
            return "Unkown";
        }
    }
}
