package ExamPreparation.Exam10April21.aquarium.entities.fish;

public class FreshwaterFish extends BaseFish {

    private static final int SIZE = 3;
//    private static final String LIVE_AQUARIUM = "FreshwaterAquarium";

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(SIZE);
    }

    @Override
    public void eat() {
       this.setSize(this.getSize() + 3);
    }


//    public String getLiveAquarium() {
//        return LIVE_AQUARIUM;
//    }
}
