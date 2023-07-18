import java.util.ArrayList;
import java.util.List;

public class Triangle extends Printer<TwoDShape> implements TwoDShape, Positionable, Comparable<TwoDShape> {

    private List<TwoDPoint> vertices = new ArrayList<>();
    private double xValue;
    private double yValue;
    private double area;
    private double perimeter;

    public Triangle(List<TwoDPoint> vertices) throws IllegalArgumentException {
        // TODO
        if(isMember(vertices))
            this.vertices = vertices;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public double returnX() {
        setPosition(vertices);
        return xValue;
    }

    @Override
    public double returnY() {
        setPosition(vertices);
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
        this.setPosition(vertices);
        double[] c1 = this.vertices.get(0).coordinates();
        double[] c2 = this.vertices.get(1).coordinates();
        double[] c3 = this.vertices.get(2).coordinates();
        double x1 = c1[0], y1 = c1[1];
        double x2 = c2[0], y2 = c2[1];
        double x3 = c3[0], y3 = c3[1];
        return "Triangle[(" + String.format("%.2f", x1) + ", " + String.format("%.2f", y1) +
                "), (" + String.format("%.2f", x2) + ", " + String.format("%.2f", y2) +
                "), (" + String.format("%.2f", x3) + ", " + String.format("%.2f", y3) + ")]";
    }

    @Override
    void print(TwoDShape s) {
        System.out.println(s.toString());
    }


    /**
     * Sets the position of this triangle according to the first three elements in the specified list of points. The
     * triangle is formed on the basis of these three points taken in a clockwise manner on the two-dimensional
     * x-y plane. If the input list has more than three elements, the subsequent elements are ignored.
     *
     * @param points the specified list of points.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        // TODO
        int index = 0;
        double[] c1 = points.get(0).coordinates();
        double minX = c1[0], minY = c1[1], x = c1[0], y = c1[1];
        for(int i = 1; i < 3; i++) {
            double[] c2 = points.get(i).coordinates();
            double currentX = c2[0];
            double currentY = c2[1];
            if(currentX < minX || currentX == minX && currentY < minY) {
                minX = currentX;
                xValue = minX;
                minY = currentY;
                yValue = currentY;
                index = i;
                x = c2[0];
                y = c2[1];
            }
        }

        int[] remaining = new int[2];
        int j = 0;
        for(int i = 0; i < 3; i++) {
            if(i != index) {
                remaining[j] = i;
                j++;
            }
        }
        double[] c2 = points.get(remaining[0]).coordinates();
        double[] c3 = points.get(remaining[1]).coordinates();
        double x2 = c2[0], y2 = c2[1];
        double x3 = c3[0], y3 = c3[1];
        double slope1 = (y - y2)/(x - x2);
        double slope2 = (y - y3)/(x - x3);
        if(slope1 > slope2) {
            vertices.set(0, new TwoDPoint(x, y));
            vertices.set(1, new TwoDPoint(x2, y2));
            vertices.set(2, new TwoDPoint(x3, y3));
        }
        else {
            vertices.set(0, new TwoDPoint(x, y));
            vertices.set(1, new TwoDPoint(x3, y3));
            vertices.set(2, new TwoDPoint(x2, y2));
        }
    }

    /**
     * Retrieve the position of an object as a list of points. The points are be retrieved and added to the returned
     * list in a clockwise manner on the two-dimensional x-y plane, starting with the point with the least x-value. If
     * two points have the same least x-value, then the clockwise direction starts with the point with the lower y-value.
     *
     * @return the retrieved list of points.
     */
    @Override
    public List<? extends Point> getPosition() {
        return vertices; // TODO
    }

    /**
     * @return the number of sides of this triangle, which is always set to three
     */
    @Override
    public int numSides() {
        return 3;
    }

    /**
     * Checks whether or not a list of vertices forms a valid triangle. The <i>trivial</i> triangle, where all three
     * corner vertices are the same point, is considered to be an invalid triangle.
     *
     * @param vertices the list of vertices to check against, where each vertex is a <code>Point</code> type.
     * @return <code>true</code> if <code>vertices</code> is a valid collection of points for a triangle, and
     * <code>false</code> otherwise. For example, three vertices are in a straight line is invalid.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices) throws IllegalArgumentException {
        // TODO
        double slope1, slope2;
        if (vertices.size() < 3) {
            return false;
        } else {
            double[] v1 = vertices.get(0).coordinates();
            double[] v2 = vertices.get(1).coordinates();
            double[] v3 = vertices.get(2).coordinates();
            double x1 = v1[0], y1 = v1[1];
            double x2 = v2[0], y2 = v2[1];
            double x3 = v3[0], y3 = v3[1];
            if (x1 == x2 && y1 == y2 || x1 == x3 && y1 == y3 || x2 == x3 && y2 == y3)
                return false;
            slope1 = ((y2 - y1) / (x2 - x1));
            slope2 = ((y3 - y1) / (x3 - x1));
            if (slope1 == slope2)
                return false;
            if(x1 == x2 && x2 == x3 || y1 == y2 && y2 == y3)
                return false;
        }
        return true;
    }

    /**
     * This method snaps each vertex of this triangle to its nearest integer-valued x-y coordinate. For example, if
     * a corner is at (0.8, -0.1), it will be snapped to (1,0). The resultant triangle will thus have all four
     * vertices in positions with integer x and y values. If the snapping procedure described above results in this
     * triangle becoming invalid (e.g., all corners collapse to a single point), then it is left unchanged. Snapping is
     * an in-place procedure, and the current instance is modified.
     */
    public void snap() {
        // TODO
        double[] c1 = vertices.get(0).coordinates();
        double[] c2 = vertices.get(1).coordinates();
        double[] c3 = vertices.get(2).coordinates();
        double x1 = Math.round(c1[0]), y1 = Math.round(c1[1]);
        double x2 = Math.round(c2[0]), y2 = Math.round(c2[1]);
        double x3 = Math.round(c3[0]), y3 = Math.round(c3[1]);
        List<TwoDPoint> check = new ArrayList<>();
        check.add(new TwoDPoint(x1, y1));
        check.add(new TwoDPoint(x2, y2));
        check.add(new TwoDPoint(x3, y3));
        if(isMember(check)) {
            vertices.set(0, new TwoDPoint(x1, y1));
            vertices.set(1, new TwoDPoint(x2, y2));
            vertices.set(2, new TwoDPoint(x3, y3));
        }
    }

    /**
     * @return the area of this triangle
     */
    public double area() {
        // TODO
        setPosition(vertices);
        double[] c1 = vertices.get(0).coordinates();
        double[] c2 = vertices.get(1).coordinates();
        double[] c3 = vertices.get(2).coordinates();
        double x1 = c1[0], y1 = c1[1];
        double x2 = c2[0], y2 = c2[1];
        double x3 = c3[0], y3 = c3[1];
        double product = Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))/2);
        area = Math.round(product*100.0)/100.0;
        return area;

    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this triangle
     */
    public double perimeter() {
        // TODO
        setPosition(vertices);
        double[] c1 = vertices.get(0).coordinates();
        double[] c2 = vertices.get(1).coordinates();
        double[] c3 = vertices.get(2).coordinates();
        double x1 = c1[0], y1 = c1[1];
        double x2 = c2[0], y2 = c2[1];
        double x3 = c3[0], y3 = c3[1];
        double a = Math.sqrt(Math.pow((x2 - x3), 2) + Math.pow((y2 - y3), 2));
        double b = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));;
        double c = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));;
        double sum = a + b + c;
        perimeter = Math.round(sum*100.0)/100.0;
        return perimeter;
    }
}



