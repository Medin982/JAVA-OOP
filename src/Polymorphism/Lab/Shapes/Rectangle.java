package Polymorphism.Lab.Shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    protected void calculatePerimeter() {
         super.setPerimeter(2 * (height * width));
    }

    @Override
    protected void calculateArea() {
        super.setArea(width * height);
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }
}
