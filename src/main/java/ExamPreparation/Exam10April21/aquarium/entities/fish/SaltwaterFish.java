package ExamPreparation.Exam10April21.aquarium.entities.fish;

public class SaltwaterFish extends BaseFish {

    private static final int SIZE = 5;
//    private static final String LIVE_AQUARIUM = "SaltwaterAquarium";

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(SIZE);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize() + 2);
    }

//    public  String getLiveAquarium() {
//        return LIVE_AQUARIUM;
//    }
}
