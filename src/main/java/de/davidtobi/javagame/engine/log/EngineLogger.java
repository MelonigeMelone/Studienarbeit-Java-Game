package de.davidtobi.javagame.engine.log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EngineLogger {
    private static EngineLoggerLevel engineLoggerLevel = EngineLoggerLevel.INFORMATION;

    public static void setMEngineLoggerLevel(EngineLoggerLevel engineLoggerLevel) {
        EngineLogger.engineLoggerLevel = engineLoggerLevel;
    }
    public static void log(EngineLoggerLevel engineLoggerLevel, String message) {
        if (ignore(engineLoggerLevel)) {
            return;
        }

        String logMessage = createLogMessage(engineLoggerLevel, message);
        writeToConsole(engineLoggerLevel, logMessage);
    }

    public static void log(EngineLoggerLevel engineLoggerLevel, String message, Exception exception) {
        if (ignore(engineLoggerLevel)) {
            return;
        }

        String logMessage = createLogMessage(engineLoggerLevel, message);
        writeToConsole(engineLoggerLevel, logMessage);
    }

    private static boolean ignore(EngineLoggerLevel engineLoggerLevel) {
        return EngineLogger.engineLoggerLevel.getValue() > engineLoggerLevel.getValue();
    }

    private static String createLogMessage(EngineLoggerLevel engineLoggerLevel, String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return  simpleDateFormat.format(new Date()) + " [" + engineLoggerLevel.name() + "] " + message;
    }

    private static void writeToConsole(EngineLoggerLevel engineLoggerLevel, String message) {
        message = engineLoggerLevel.getAnsiColorCode() + message + "\n";
        OutputStream out = new BufferedOutputStream(System.out);
        try {
            out.write(message.getBytes());
            out.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
