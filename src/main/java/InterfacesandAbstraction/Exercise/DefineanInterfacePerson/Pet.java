package InterfacesandAbstraction.Exercise.DefineanInterfacePerson;

public class Pet implements Birthable {
    private String birthDate;
    private String name;

    public Pet(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    public String getName() {
        return name;
    }
}
