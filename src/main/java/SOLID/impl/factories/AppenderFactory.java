package SOLID.impl.factories;

import SOLID.enums.ReportLevel;
import SOLID.impl.appenders.ConsoleAppender;
import SOLID.impl.appenders.FileAppender;
import SOLID.interfeces.Appender;
import SOLID.interfeces.Factory;
import SOLID.interfeces.Layout;

public class AppenderFactory implements Factory<Appender> {

    private FactoryLayout factoryLayout;

    public AppenderFactory() {
        this.factoryLayout = new FactoryLayout();
    }

    @Override
    public Appender produce(String input) {
        String[] tokens = input.split("\\s+");
        String appenderType = tokens[0];
        String layoutType = tokens[1];
        Layout layout = factoryLayout.produce(layoutType);
        Appender appender = null;
        if (appenderType.equals("ConsoleAppender")) {
            appender = new ConsoleAppender(layout);
        } else if (appenderType.equals("FileAppender")) {
            appender = new FileAppender(layout);
        }

        if (tokens.length >= 3) {
            appender.setReportLevel(ReportLevel.valueOf(tokens[2]));
        }

        return appender;
    }
}
