import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

  private final int x,y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void draw() {
    StdDraw.point(x, y);
  }

  public void drawTo(Point that) {
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  public int compareTo(Point that) {
    if (y > that.y)
      return 1;
    if (y == that.y) {
      if (x > that.x)
        return 1;
      if (x == that.x)
        return 0;
      else
        return -1;
    }
    else
      return -1;
  }

  public double slopeTo(Point that) {
    if (that.x == x && that.y == y)
      return Double.NEGATIVE_INFINITY;
    if (that.x == x)
      return Double.POSITIVE_INFINITY;
    if (that.y == y)
      return 0.0;
    double dx = that.x - x;
    double dy = that.y - y;
    return dy/dx;
  }

  public Comparator<Point> slopeOrder() {
    return new SlopeOrder(this);
  }

  private static class SlopeOrder implements Comparator<Point> {
    Point invoker;

    public SlopeOrder(Point invoker) {
      this.invoker = invoker;
    }

    public int compare(Point first, Point second) {
      double firstSlope = invoker.slopeTo(first);
      double secondSlope = invoker.slopeTo(second);
      if (firstSlope == secondSlope)
        return 0;
      if (firstSlope > secondSlope)
        return 1;
      else
        return -1;
    }
  }
}
