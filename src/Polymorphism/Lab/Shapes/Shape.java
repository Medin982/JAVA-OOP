package Polymorphism.Lab.Shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getPerimeter() {
        if (perimeter == null) {
             calculateArea();
        }
        return perimeter;
    }

    public Double getArea() {
        if (area == null) {
            calculateArea();
        }
        return area;
    }

    abstract protected void calculatePerimeter();
    abstract protected void calculateArea();

}
