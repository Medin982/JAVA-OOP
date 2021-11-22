package SOLID.impl;

import SOLID.enums.ReportLevel;
import SOLID.interfeces.Appender;
import SOLID.interfeces.Logger;

import java.util.Arrays;

public class MessageLogger implements Logger {
    private Appender[] appenders;

    public MessageLogger(Appender... appender) {
        this.appenders = appender;
    }

    @Override
    public void logInfo(String time, String message) {
        extracted(time, message, ReportLevel.INFO);
    }

    @Override
    public void logWarning(String time, String message) {
        extracted(time, message, ReportLevel.WARNING);
    }

    @Override
    public void logError(String time, String message) {
        extracted(time, message, ReportLevel.ERROR);
    }

    @Override
    public void logCritical(String time, String message) {
        extracted(time, message, ReportLevel.CRITICAL);
    }

    @Override
    public void logFatal(String time, String message) {
        extracted(time, message, ReportLevel.FATAL);
    }

    private void extracted(String time, String message, ReportLevel reportLevel) {
        Arrays.stream(appenders).forEach(appender -> appender.append(time, message, reportLevel));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Logger info");
        sb.append(System.lineSeparator());
        for (Appender appender : appenders) {
            sb.append(appender);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
