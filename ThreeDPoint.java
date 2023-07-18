/**
 * An unmodifiable point in the three-dimensional space. The coordinates are specified by exactly three doubles (its
 * <code>x</code>, <code>y</code>, and <code>z</code> values).
 */
public class ThreeDPoint implements Point {

    private double xCoordinate, yCoordinate, zCoordinate;

    public ThreeDPoint(double x, double y, double z) {
        // TODO
        xCoordinate = x;
        yCoordinate = y;
        zCoordinate = z;
    }

    /**
     * @return the (x,y,z) coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        // TODO
        return new double[]{xCoordinate, yCoordinate, zCoordinate};
    }
}