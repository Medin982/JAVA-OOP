package Polymorphism.Lab.Shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    @Override
    protected void calculatePerimeter() {
        super.setPerimeter(2 * Math.PI * radius);
    }

    @Override
    protected void calculateArea() {
        super.setArea(Math.PI + radius * radius);
    }

    public final Double getRadius() {
        return radius;
    }
}
