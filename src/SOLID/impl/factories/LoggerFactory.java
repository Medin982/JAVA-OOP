package SOLID.impl.factories;

import SOLID.impl.MessageLogger;
import SOLID.interfeces.Appender;
import SOLID.interfeces.Factory;
import SOLID.interfeces.Logger;

public class LoggerFactory implements Factory<Logger> {
    private AppenderFactory appenderFactory;

    public LoggerFactory() {
        this.appenderFactory = new AppenderFactory();
    }

    @Override
    public Logger produce(String input) {
        String[] tokens = input.split(System.lineSeparator());
        Appender[] appenders = new Appender[tokens.length];
        for (int i = 0; i < appenders.length; i++) {
            appenders[i] = this.appenderFactory.produce(tokens[i]);
        }
        return new MessageLogger(appenders);
    }
}
