package InterfacesandAbstraction.Lab.BorderControl;

public class Robot implements Identifiable {

    private final String id;
    private final String model;

    public Robot(String model, String id) {
        this.id = id;
        this.model = model;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }
}
