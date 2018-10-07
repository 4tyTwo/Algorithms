import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {

    private final TreeSet<Point2D> tree;

    public PointSET() {
        tree = new TreeSet<Point2D>();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public void insert(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        tree.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        return tree.contains(p);
    }

    public void draw() {
        for (Point2D i : tree.descendingSet())
            i.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new java.lang.IllegalArgumentException();
        ArrayList<Point2D> pointList = new ArrayList<>();
        for (Point2D i : tree)
            if (rect.contains(i))
                pointList.add(i);
        return pointList;
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        double smallestDistance = Double.POSITIVE_INFINITY;
        Point2D champion = null;
        for (Point2D i : tree.descendingSet()) {
            if (i.distanceSquaredTo(p) < smallestDistance) {
                smallestDistance = i.distanceSquaredTo(p);
                champion = i;
            }
        }
        return champion;
    }
}
