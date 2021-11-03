package WorkingwithAbstraction.Lab.PointinRectangle;

public class Rectangle {
    private Point pointX;
    private Point pointY;

    public Rectangle(Point pointX, Point pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public boolean isInside (Point p) {
        return p.isGreaterThanOrEqual(pointX) && p.isLessThanOrEqual(pointY);
    }
}
