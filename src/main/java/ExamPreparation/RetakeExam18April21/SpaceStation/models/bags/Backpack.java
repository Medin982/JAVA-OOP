package ExamPreparation.RetakeExam18April21.SpaceStation.models.bags;

import java.util.ArrayList;
import java.util.List;

public class Backpack implements Bag {

    private List<String> items;

    public Backpack() {
        items = new ArrayList<>();
    }

    @Override
    public List<String> getItems() {
        return this.items;
    }
}
