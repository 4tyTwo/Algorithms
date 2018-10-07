import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;

public class KdTree {
    private static final int COMPARE_X = 0;
    private static final int DIMENSIONS = 2;
    private Node tree = null;
    private int n = 0;
    private class Node {
        Point2D key;
        Node left = null;
        Node right = null;
        Node(Point2D key) {
            this.key = key;
        }
    }

    public KdTree() {

    }

    public boolean isEmpty() {
        return (n == 0);
    }

    public int size() {
        return n;
    }

    public void insert(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        if (tree == null) {
            tree = new Node(p);
            ++n;
            return;
        }
        if (contains(p))
            return;
        Node curr = tree;
        int depth = 0;
        while (true) {
            if (depth++ % DIMENSIONS == COMPARE_X) {
                if (p.x() < curr.key.x()) {
                    if (curr.left == null) {
                        curr.left = new Node(p);
                        ++n;
                        return;
                    }
                curr = curr.left;
                }
                else {
                    if (curr.right == null) {
                        curr.right = new Node(p);
                        ++n;
                        return;
                    }
                    curr = curr.right;
                }
            }
            else {
                if (p.y() < curr.key.y()) {
                    if (curr.left == null) {
                        curr.left = new Node(p);
                        ++n;
                        return;
                    }
                    curr = curr.left;
                }
                else {
                    if (curr.right == null) {
                        curr.right = new Node(p);
                        ++n;
                        return;
                    }
                    curr = curr.right;
                }
            }
        }
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        Node curr = tree;
        int depth = 0;
        while (curr != null) {
            if (curr.key.equals(p))
                return true;
            if (depth++ % DIMENSIONS == COMPARE_X) {
                if (p.x() < curr.key.x())
                    curr = curr.left;
                else
                    curr = curr.right;
            }
            else {
                if (p.y() < curr.key.y())
                    curr = curr.left;
                else
                    curr = curr.right;
            }
        }
        return false;
    }

    public void draw() {
        recursiveDraw(tree);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new java.lang.IllegalArgumentException();
        ArrayList<Point2D> pointList = new ArrayList<>();
        recursiveAdd(tree, rect, pointList, 0);
        return pointList;
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new java.lang.IllegalArgumentException();
        double smallestDistance = Double.POSITIVE_INFINITY;
        Point2D champion = null;
        int depth = 0;
        Node curr = tree;
        boolean updated;
        while (curr != null) {
            updated = false;
            if (curr.key.distanceSquaredTo(p) < smallestDistance) {
                smallestDistance = curr.key.distanceSquaredTo(p);
                champion = curr.key;
                updated = true;
            }
            if (depth++ % DIMENSIONS == COMPARE_X) {
                if (p.x() < curr.key.x())
                    curr = curr.left;
                else
                    if (!updated)
                        curr = curr.right;
            }
            else {
                if (p.y() < curr.key.y())
                    curr = curr.left;
                else
                    if (!updated)
                        curr = curr.right;
            }
        }
        return champion;
    }

    private void recursiveAdd(Node node, RectHV rect, ArrayList<Point2D> pointList, int depth) {
        if (node == null)
            return;
        Point2D p = node.key;
        if (rect.contains(p))
            pointList.add(p);
        if (depth % DIMENSIONS == COMPARE_X) {
            if (p.x() >= rect.xmin() && p.x() <= rect.xmax()) { // If intersects
                recursiveAdd(node.left, rect, pointList, depth + 1);
                recursiveAdd(node.right, rect, pointList, depth + 1);
            } else {
                if (p.x() > rect.xmax())
                    recursiveAdd(node.left, rect, pointList, depth + 1);
                if (p.x() < rect.xmin())
                    recursiveAdd(node.right, rect, pointList, depth + 1);
            }
        }
        else {
            if (p.y() >= rect.ymin() && p.y() <= rect.ymax()) { // If intersects
                recursiveAdd(node.left, rect, pointList, depth + 1);
                recursiveAdd(node.right, rect, pointList, depth + 1);
            } else {
                if (p.y() > rect.ymax())
                    recursiveAdd(node.left, rect, pointList, depth + 1);
                if (p.y() < rect.ymin())
                    recursiveAdd(node.right, rect, pointList, depth + 1);
            }
        }
    }

    private void recursiveDraw(Node node) {
        if (node == null)
            return;
        node.key.draw();
        recursiveDraw(node.left);
        recursiveDraw(node.right);
    }
}
