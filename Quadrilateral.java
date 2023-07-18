import java.util.ArrayList;
import java.util.List;

public class Quadrilateral extends Printer<TwoDShape> implements TwoDShape, Positionable, Comparable<TwoDShape> {

    List<TwoDPoint> vertices;
    private double xValue;
    private double yValue;
    private double area;
    private double perimeter;

    public Quadrilateral(List<TwoDPoint> vertices) throws IllegalArgumentException {
        // TODO
        if(isMember(vertices)) {
            this.vertices = vertices;
        }
        else {
            throw new IllegalArgumentException();
        }
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
        double[] c4 = this.vertices.get(3).coordinates();
        double x1 = c1[0], y1 = c1[1];
        double x2 = c2[0], y2 = c2[1];
        double x3 = c3[0], y3 = c3[1];
        double x4 = c4[0], y4 = c4[1];
        return "Quadrilateral[(" + String.format("%.2f", x1) + ", " + String.format("%.2f", y1) +
                "), (" + String.format("%.2f", x2) + ", " + String.format("%.2f", y2) +
                "), (" + String.format("%.2f", x3) + ", " + String.format("%.2f", y3) +
                "), (" + String.format("%.2f", x4) + ", " + String.format("%.2f", y4) + ")]";
    }

    @Override
    void print(TwoDShape s) {
        System.out.println(s.toString());
    }


    /**
     * Sets the position of this quadrilateral according to the first four elements in the specified list of points. The
     * quadrilateral is formed on the basis of these four points taken in a clockwise manner on the two-dimensional
     * x-y plane. If the input list has more than four elements, the subsequent elements are ignored.
     *
     * @param points the specified list of points.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        // TODO
        //Find minimum x
        //Then find the slope from a to all 3
        //If two x coordinates share the same value, start from the lowest
        //Compare each slope from greatest to least
        int index = 0;
        double[] c1 = points.get(0).coordinates();
        double minX = c1[0], minY = c1[1], x = c1[0], y = c1[1];
        for(int i = 1; i < 4; i++) {
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
        int[] remaining = new int[3];
        int j = 0;
        for(int i = 0; i < 4; i++) {
            if(i != index) {
                remaining[j] = i;
                j++;
            }
        }
        double[] c2 = points.get(remaining[0]).coordinates();
        double[] c3 = points.get(remaining[1]).coordinates();
        double[] c4 = points.get(remaining[2]).coordinates();
        double x2 = c2[0], y2 = c2[1];
        double x3 = c3[0], y3 = c3[1];
        double x4 = c4[0], y4 = c4[1];
        double slope1 = (y - y2)/(x - x2);
        double slope2 = (y - y3)/(x - x3);
        double slope3 = (y - y4)/(x - x4);
        vertices.set(0, new TwoDPoint(x, y));

        if(x2 == minX) {
            vertices.set(1, new TwoDPoint(x2, y2));
            if(slope2 > slope3) {
                vertices.set(2, new TwoDPoint(x3, y3));
                vertices.set(3, new TwoDPoint(x4, y4));
            }
            else {
                vertices.set(2, new TwoDPoint(x4, y4));
                vertices.set(3, new TwoDPoint(x3, y3));
            }
        }
        else if(x3 == minX) {
            vertices.set(1, new TwoDPoint(x3, y3));
            if(slope1 > slope3) {
                vertices.set(2, new TwoDPoint(x2, y2));
                vertices.set(3, new TwoDPoint(x4, y4));
            }
            else {
                vertices.set(2, new TwoDPoint(x4, y4));
                vertices.set(3, new TwoDPoint(x2, y2));
            }
        }
        else if(x4 == minX) {
            vertices.set(1, new TwoDPoint(x4, y4));
            if(slope1 > slope2) {
                vertices.set(2, new TwoDPoint(x2, y2));
                vertices.set(3, new TwoDPoint(x3, y3));
            }
            else {
                vertices.set(2, new TwoDPoint(x3, y3));
                vertices.set(3, new TwoDPoint(x2, y2));
            }
        }
        else {
            if(slope1 > slope2 && slope1 > slope3) {
                vertices.set(1, new TwoDPoint(x2, y2));
                if(slope2 > slope3) {
                    vertices.set(2, new TwoDPoint(x3, y3));
                    vertices.set(3, new TwoDPoint(x4, y4));
                }
                else {
                    vertices.set(2, new TwoDPoint(x4, y4));
                    vertices.set(3, new TwoDPoint(x3, y3));
                }
            }
            else if(slope2 > slope1 && slope2 > slope3) {
                vertices.set(1, new TwoDPoint(x3, y3));
                if(slope1 > slope3) {
                    vertices.set(2, new TwoDPoint(x2, y2));
                    vertices.set(3, new TwoDPoint(x4, y4));
                }
                else {
                    vertices.set(2, new TwoDPoint(x4, y4));
                    vertices.set(3, new TwoDPoint(x2, y2));
                }
            }
            else {
                vertices.set(1, new TwoDPoint(x4, y4));
                if(slope1 > slope2) {
                    vertices.set(2, new TwoDPoint(x2, y2));
                    vertices.set(3, new TwoDPoint(x3, y3));
                }
                else {
                    vertices.set(2, new TwoDPoint(x3, y3));
                    vertices.set(3, new TwoDPoint(x2, y2));
                }
            }
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
    public List<? extends Point> getPosition()  {
        return vertices; // TODO
    }

    /**
     * @return the number of sides of this quadrilateral, which is always set to four
     */
    @Override
    public int numSides() { return 4; }

    /**
     * Checks whether or not a list of vertices forms a valid quadrilateral. The <i>trivial</i> quadrilateral, where all
     * four corner vertices are the same point, is considered to be an invalid quadrilateral.
     *
     * @param vertices the list of vertices to check against, where each vertex is a <code>Point</code> type.
     * @return <code>true</code> if <code>vertices</code> is a valid collection of points for a quadrilateral, and
     * <code>false</code> otherwise. For example, if three of the four vertices are in a straight line is invalid.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices) {
        // TODO
        if (vertices.size() < 4)
            return false;
        double[] v1 = vertices.get(0).coordinates();
        double[] v2 = vertices.get(1).coordinates();
        double[] v3 = vertices.get(2).coordinates();
        double[] v4 = vertices.get(3).coordinates();
        double aX = v1[0], aY = v1[1];
        double bX = v2[0], bY = v2[1];
        double cX = v3[0], cY = v3[1];
        double dX = v4[0], dY = v4[1];
        if(aX == bX && aY == bY || aX == cX && aY == cY || aX == dX && aY == dY ||
                bX == cX && bY == cY || bX == dX && bY == dY || cX == dX && cY == dY)
            return false;
        if(aX == bX && aX == cX || aX == bX && aX == dX || aX == cX && aX == dX || bX == cX && bX == dX)
            return false;
        if(aY == bY && aY == cY || aY == bY && aY == dY || aY == cY && aY == dY || bY == cY && bY == dY)
            return false;
        double AB = (bY - aY)/(bX - aX);
        double BC = (cY - bY)/(cX - bX);
        double CD = (dY - cY)/(dX - cX);
        double AD = (dY - aY)/(dX - aX);
        if(AB == BC || BC == CD || CD == AD || AD == AB) {
            return false;
        }
        return true;
    }

    /**
     * This method snaps each vertex of this quadrilateral to its nearest integer-valued x-y coordinate. For example, if
     * a corner is at (0.8, -0.1), it will be snapped to (1,0). The resultant quadrilateral will thus have all four
     * vertices in positions with integer x and y values. If the snapping procedure described above results in this
     * quadrilateral becoming invalid (e.g., all four corners collapse to a single point), then it is left unchanged.
     * Snapping is an in-place procedure, and the current instance is modified.
     */
    public void snap() {
        // TODO
        double[] c1 = vertices.get(0).coordinates();
        double[] c2 = vertices.get(1).coordinates();
        double[] c3 = vertices.get(2).coordinates();
        double[] c4 = vertices.get(3).coordinates();
        double x1 = Math.round(c1[0]), y1 = Math.round(c1[1]);
        double x2 = Math.round(c2[0]), y2 = Math.round(c2[1]);
        double x3 = Math.round(c3[0]), y3 = Math.round(c3[1]);
        double x4 = Math.round(c4[0]), y4 = Math.round(c4[1]);
        List<TwoDPoint> check = new ArrayList<>();
        check.add(new TwoDPoint(x1, y1));
        check.add(new TwoDPoint(x2, y2));
        check.add(new TwoDPoint(x3, y3));
        check.add(new TwoDPoint(x4, y4));
        if(isMember(check)) {
            vertices.set(0, new TwoDPoint(x1, y1));
            vertices.set(1, new TwoDPoint(x2, y2));
            vertices.set(2, new TwoDPoint(x3, y3));
            vertices.set(3, new TwoDPoint(x4, y4));
        }
    }

    /**
     * @return the area of this quadrilateral
     */
    public double area() {
        // TODO
        setPosition(vertices);

        double[] c1 = vertices.get(0).coordinates();
        double[] c2 = vertices.get(1).coordinates();
        double[] c3 = vertices.get(2).coordinates();
        double[] c4 = vertices.get(3).coordinates();
        double x1 = c1[0], y1 = c1[1];
        double x2 = c2[0], y2 = c2[1];
        double x3 = c3[0], y3 = c3[1];
        double x4 = c4[0], y4 = c4[1];
        double sum = ((x1*y2 - x2*y1) + (x2*y3 - x3*y2) + (x3*y4 - x4*y3) + (x4*y1 - x1*y4));
        sum = Math.abs(sum/2);
        area = Math.round(sum*100.0)/100.0;
        return area;
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this quadrilateral
     */
    public double perimeter() {
        // TODO
        setPosition(vertices);
        double[] v1 = vertices.get(0).coordinates();
        double[] v2 = vertices.get(1).coordinates();
        double[] v3 = vertices.get(2).coordinates();
        double[] v4 = vertices.get(3).coordinates();
        double aX = v1[0], aY = v1[1];
        double bX = v2[0], bY = v2[1];
        double cX = v3[0], cY = v3[1];
        double dX = v4[0], dY = v4[1];
        double distAB = Math.sqrt(Math.pow(bX - aX, 2) + Math.pow(bY - aY, 2));
        double distBC = Math.sqrt(Math.pow(cX - bX, 2) + Math.pow(cY - bY, 2));
        double distCD = Math.sqrt(Math.pow(dX - cX, 2) + Math.pow(dY - cY, 2));
        double distAD = Math.sqrt(Math.pow(dX - aX, 2) + Math.pow(dY - aY, 2));
        double sum = distAB + distBC + distCD + distAD;
        perimeter = Math.round(sum*100.0)/100.0;
        return perimeter;
    }

    public static void main(String [] args) {
        List<TwoDPoint> twoD = new ArrayList<>();
        twoD.add(new TwoDPoint(2.0, 2.0));
        twoD.add(new TwoDPoint(1.0, 1.0));
        twoD.add(new TwoDPoint(2.0, 1.0));
        twoD.add(new TwoDPoint(1.0, 2.0));
        Quadrilateral q = new Quadrilateral(twoD);
        double[] c1 = twoD.get(0).coordinates();
        double[] c2 = twoD.get(1).coordinates();
        double[] c3 = twoD.get(2).coordinates();
        double[] c4 = twoD.get(3).coordinates();
        double x1 = c1[0], y1 = c1[1];
        double x2 = c2[0], y2 = c2[1];
        double x3 = c3[0], y3 = c3[1];
        double x4 = c4[0], y4 = c4[1];
        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3 + " " + x4 + " " + y4);
        q.setPosition(twoD);
        //q.snap();
        c1 = q.vertices.get(0).coordinates();
        c2 = q.vertices.get(1).coordinates();
        c3 = q.vertices.get(2).coordinates();
        c4 = q.vertices.get(3).coordinates();
        x1 = c1[0];
        y1 = c1[1];
        x2 = c2[0];
        y2 = c2[1];
        x3 = c3[0];
        y3 = c3[1];
        x4 = c4[0];
        y4 = c4[1];
        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3 + " " + x4 + " " + y4);
        System.out.println(q.area());
        System.out.println(q.perimeter());
    }

}
