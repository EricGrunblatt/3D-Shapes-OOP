import java.util.Collections;
import java.util.List;

public class Circle extends Printer<TwoDShape> implements TwoDShape, Positionable, Comparable<TwoDShape> {

    private TwoDPoint center;
    private double    radius;
    private double    xValue;
    private double    yValue;
    private double    area;
    private double    perimeter;

    public Circle(double x, double y, double r) throws IllegalArgumentException {
        center = new TwoDPoint(x,y);
        radius = r;
        xValue = x;
        yValue = y;
        if(!(isMember(getPosition())))
            throw new IllegalArgumentException();

    }

    public double returnR() {
        return radius;
    }

    @Override
    public double returnX() {
        return xValue - returnR();
    }

    @Override
    public double returnY() {
        return yValue;
    }

    @Override
    public double getArea() {
        this.area();
        return area;
    }

    @Override
    public int compareTo(TwoDShape t) {
        if(this.area() > t.getArea())
            return 1;
        else if(this.area() < t.getArea())
            return -1;
        return 0;
    }


    @Override
    public String toString() {
        return "Circle[center: " + String.format("%.2f", xValue) + "," + String.format("%.2f", yValue) + "; radius: " + String.format("%.2f", radius) + "]";
    }

    @Override
    void print(TwoDShape s) {
        System.out.println(s.toString());
    }

    /**
     * Sets the position of this circle to be centered at the first element in the specified list of points.
     *
     * @param points the specified list of points.
     * @throws IllegalArgumentException if the input does not consist of {@link TwoDPoint} instances
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        // TODO
        for(Object point: points) {
            if(!(point instanceof TwoDPoint))
                throw new IllegalArgumentException();
        }
        Point position = points.get(0);

    }

    /**
     * @return the center of this circle as an immutable singleton list
     */
    @Override
    public List<? extends Point> getPosition() {
        // TODO
        List<? extends Point> position = Collections.singletonList(center);
        return position;
    }

    /**
     * @return the number of sides of this circle, which is always set to positive infinity
     */
    @Override
    public int numSides() {
        return (int) Double.POSITIVE_INFINITY;
    }

    /**
     * Checks whether or not a list of vertices is a valid collection of vertices for the type of two-dimensional shape.
     *
     * @param centers the list of vertices to check against, where each vertex is a <code>Point</code> type. For
     *                the Circle object, this list is expected to contain only its center.
     * @return <code>true</code> if and only if <code>centers</code> is a single point, and the radius of this circle is
     * a positive value.
     */
    @Override
    public boolean isMember(List<? extends Point> centers) {
        return centers.size() == 1 && radius > 0;
    }

    /**
     * @return the area of this circle
     */
    public double area() {
        area = (Math.PI) * Math.pow(radius, 2.0);
        area = Math.round(area*100.0)/100.0;
        return area; // TODO
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this quadrilateral
     */
    public double perimeter() {
        perimeter = 2 * Math.PI * radius;
        perimeter = Math.round(perimeter*100.0)/100.0;
        return perimeter; // TODO
    }
}

