package ua.com.qatestlab.prestashopAutomation.Utility;

import org.apache.log4j.Logger;

public class Log {
    private static final Logger Log = Logger.getLogger(Log.class);

    public static void startTestLog() {
        Log.info("Logging service is started");
    }

    public static void endTestLog() {
        Log.info("Logging service is stopped");
    }

    public static void info(String message) {
        Log.info(message);
    }

}
