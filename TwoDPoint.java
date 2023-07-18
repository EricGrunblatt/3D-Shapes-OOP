import java.util.*;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values.
 */
public class TwoDPoint implements Point, Comparable<TwoDPoint> {

    private double xCoordinate, yCoordinate;

    public TwoDPoint(double x, double y) {
        xCoordinate = x; //TODO
        yCoordinate = y;
    }

    /**
     * @return the coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        return new double[]{xCoordinate, yCoordinate}; // TODO
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    @Override
    public int compareTo(TwoDPoint o) {
        double thisDistance = Math.sqrt(Math.pow(this.xCoordinate - 0, 2) + Math.pow(this.yCoordinate - 0, 2));
        double oDistance = Math.sqrt(Math.pow(o.xCoordinate - 0, 2) + Math.pow(o.yCoordinate - 0, 2));
        if(thisDistance > oDistance)
            return 1;
        else if(thisDistance < oDistance)
            return -1;
        return 0;
    }

    /**
     * Returns a list of <code>TwoDPoint</code>s based on the specified array of doubles. A valid argument must always
     * be an even number of doubles so that every pair can be used to form a single <code>TwoDPoint</code> to be added
     * to the returned list of points.
     *
     * @param coordinates the specified array of doubles.
     * @return a list of two-dimensional point objects.
     * @throws IllegalArgumentException if the input array has an odd number of doubles.
     */
    public static List<TwoDPoint> ofDoubles(double... coordinates) throws IllegalArgumentException {
        // TODO
        if(coordinates.length % 2 == 1)
            throw new IllegalArgumentException();

        List<TwoDPoint> coordList = new ArrayList<>();
        for(int i = 0; i < coordinates.length; i += 2) {
            TwoDPoint point = new TwoDPoint(coordinates[i], coordinates[i+1]);
            coordList.add(point);
        }
        return coordList;
    }
}
