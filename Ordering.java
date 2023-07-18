import java.util.*;

public class Ordering {

    /**
     * A comparator for two-dimensional shapes, based on the vertex with the least x-value. That is, sorting with this
     * comparator must order all the shapes in a collection in increasing order of their least x-valued vertex.
     */
    static class XLocationShapeComparator implements Comparator<TwoDShape> {

        @Override
        public int compare(TwoDShape o1, TwoDShape o2) {
            // TODO
            double o1X = o1.returnX();
            double o2X = o1.returnX();
            double o1Y = o1.returnY();
            double o2Y = o2.returnY();
            if(o1X == o2X) {
                if(o1Y > o2Y)
                    return 1;
                else if(o1Y == o2Y)
                    if(o1.getArea() > o2.getArea())
                        return 1;
                    else
                        return -1;
                else
                    return -1;
            }
            else if(o1X > o2X)
                return 1;
            else
                return -1;

        }
    }

    static class XLocationPointComparator implements Comparator<TwoDPoint> {

        @Override
        public int compare(TwoDPoint o1, TwoDPoint o2) {
            double o1X = o1.getxCoordinate();
            double o2X = o2.getxCoordinate();
            double o1Y = o1.getyCoordinate();
            double o2Y = o2.getyCoordinate();
            if(o1X == o2X) {
                if(o1Y > o2Y)
                    return 1;
                else
                    return -1;
            }
            else if(o1X > o2X)
                return 1;
            else
                return -1;
        }
    }

    // TODO: There's a lot wrong with this method. correct it so that it can work properly with generics.
    static <T> void copy(Collection<? extends T> source, Collection<T> destination) {
        destination.addAll(source);
    }

    /**
     * PLEASE READ ALL THE COMMENTS IN THIS CODE CAREFULLY BEFORE YOU START WRITING YOUR OWN CODE.
     */
    public static void main(String[] args) {
        List<TwoDShape> shapes = new ArrayList<>();
        List<TwoDPoint> points = new ArrayList<>();

        points.add(new TwoDPoint(1.0, 1.0));
        points.add(new TwoDPoint(3.0, 2.0));
        points.add(new TwoDPoint(2.0, 2.0));
        points.add(new TwoDPoint(2.0, 1.0));
        List<TwoDPoint> t = new ArrayList<>();
        List<TwoDPoint> q = new ArrayList<>();
        t.add(new TwoDPoint(2.0, 1.0));
        t.add(new TwoDPoint(1.0, 5.0));
        t.add(new TwoDPoint(1.0, 2.0));
        q.add(new TwoDPoint(2.0, 1.0));
        q.add(new TwoDPoint(4.0, 6.0));
        q.add(new TwoDPoint(1.0, 4.0));
        q.add(new TwoDPoint(1.0, 3.0));

        shapes.add(new Circle(2.0, 3.0, 1.0));
        shapes.add(new Triangle(t));
        shapes.add(new Quadrilateral(q));

        copy(new ArrayList<TwoDShape>(), shapes);

        shapes.sort(new XLocationShapeComparator());
        Collections.sort(shapes);

        points.sort(new XLocationPointComparator());
        Collections.sort(points);

        List<Number>       numbers   = new ArrayList<>();
        List<Double>       doubles   = new ArrayList<>();
        Set<Triangle>      triangles = new HashSet<>();
        Set<Quadrilateral> quads     = new LinkedHashSet<>();

        copy(doubles, numbers);
        copy(quads, shapes);
        copy(triangles, shapes);

        List<TwoDPoint> pointList = new ArrayList<>();
        List<TwoDShape> lst = new ArrayList<>();
        pointList.add(new TwoDPoint(2.25, 2.0));
        pointList.add(new TwoDPoint(1.345, 2.0));
        pointList.add(new TwoDPoint(3.555, 3.0));
        lst.add(new Circle(2.0, 3.0, 1.0));
        lst.add(new Triangle(pointList));
        printAllAndReturnLeast(lst, new Printer());
    }

    // NOTE: This method may compile after you implement just one thing, but pay attention to the warnings in your IDE.
    // Just because the method compiles doesn't mean it is fully correct.
    /**
     * This method prints each element of a list of various types of two-dimensional shapes (i.e., {@link TwoDShape}, as
     * defined in the {@link Printer<TwoDShape>#print} method. When the printing process is complete, it returns the
     * least instance, as per the natural order of the {@link TwoDShape} instances. SECTION 1 in the main method above
     * defines this natural order.
     *
     * Note that the natural ordering of shapes is not provided to you. This is something you must implement as part of
     * the assignment.
     *
     * @param aList the list of provided two-dimensional shape instances
     * @param aPrinter the specified printer instance
     * @return the least element from <code>aList</code>, as per the natural ordering of the shapes
     */
    static TwoDShape printAllAndReturnLeast(List<? extends TwoDShape> aList, AbstractPrinter aPrinter) {
        TwoDShape least =  aList.get(0);
        for (TwoDShape t : aList) {
            if (least.compareTo(t) < 0)
                least = t;
            aPrinter.print(t);
        }
        return least;
    }
}
