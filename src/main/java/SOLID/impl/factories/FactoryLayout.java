package SOLID.impl.factories;

import SOLID.impl.layouts.SimpleLayout;
import SOLID.impl.layouts.XmlLayout;
import SOLID.interfeces.Factory;
import SOLID.interfeces.Layout;

public class FactoryLayout implements Factory<Layout> {

    @Override
    public Layout produce(String input) {
        Layout layout = null;

        if (input.equals("SimpleLayout")) {
            layout = new SimpleLayout();
        } else if (input.equals("XmlLayout")) {
            layout = new XmlLayout();
        }
        return layout;
    }
}
