package SOLID;


import SOLID.enums.ReportLevel;
import SOLID.impl.factories.LoggerFactory;
import SOLID.interfeces.Logger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Input input = new Input();
        Scanner scan = new Scanner(System.in);
        LoggerFactory loggerFactory = new LoggerFactory();
        Logger logger = loggerFactory.produce(input.readLoggerInfo(scan));
        String line = scan.nextLine();
        while (!line.equals("END")) {
            String[] tokens = line.split("\\|");
            ReportLevel reportLevel = ReportLevel.valueOf(tokens[0]);
            String time = tokens[1];
            String massage = tokens[2];
            switch (reportLevel) {
                case INFO -> logger.logInfo(time,massage);
                case WARNING -> logger.logWarning(time, massage);
                case ERROR -> logger.logError(time, massage);
                case CRITICAL -> logger.logCritical(time, massage);
                case FATAL -> logger.logFatal(time, massage);
            }
            line = scan.nextLine();
        }
        System.out.println(logger);
    }
}
