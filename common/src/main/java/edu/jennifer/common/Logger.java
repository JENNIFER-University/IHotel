package edu.jennifer.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Khalid
 * @Created 3/7/19 5:02 PM.
 */
public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String INFO = "[INFO]";
    private static final String WARN = "[WARN]";
    private static final String ERROR = "[ERROR]";

    /**
     * Display Info log
     * @param msg log message
     */
    public static void info(String msg)
    {
        print(INFO, msg, null);
    }

    /**
     * Display Wanr Log
     * @param msg log message
     */
    public static void warn(String msg)
    {
        print(WARN, msg, null);
    }

    /**
     * Display error log
     * @param msg message
     * @param t Throwable
     */
    public static void error(String msg, Throwable t)
    {

        print(ERROR, msg, getStackTrace(t));
    }


    /**
     * Print the log message to the stdout
     * @param prefix Log Prefix (INFO, ERROR, WARN)
     * @param message the message itself
     * @param stackTrace stacktrace if any
     */
    private static void print(String prefix, String message, String stackTrace) {
        System.out.printf("%s %s %s %s%n", getTimeFormated(), prefix, getCallersName(), message);
        if (stackTrace != null) {
            System.out.printf("%s%n", stackTrace);
        }
    }

    /**
     * Get the stacktrace from  throwable
     * @param throwable
     * @return Stack trace as string
     */
    private static String getStackTrace(Throwable throwable) {
        if (throwable != null)
        {
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            throwable.printStackTrace(printWriter);
            printWriter.flush();
            printWriter.close();

            return writer.toString().trim();
        }

        return "";
    }

    /**
     * Get Caller class name
     * @return
     */
    private static String getCallersName() {
        try {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            String callerName = elements[elements.length -1 ].getClassName();
            return String.format("[%s]", callerName);
        }catch (Exception ex) {
            return "Unkown";
        }
    }

    /**
     * Get current time
     * @return
     */
    private static String getTimeFormated() {
        return formatter.format(LocalDateTime.now());
    }
}
