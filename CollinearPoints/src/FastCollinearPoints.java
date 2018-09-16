import java.util.Comparator;

public class FastCollinearPoints {

  private final LineSegment[] segments;
  private int n, m;
  private final double[] slopes;

  public FastCollinearPoints(Point[] points) {
    if (points == null)
      throw new java.lang.IllegalArgumentException();
    for (int i = 0; i < points.length; ++i)
      if (points[i] == null)
        throw new java.lang.IllegalArgumentException();
    for (int j = 0; j < points.length; ++j)
      for (int k = j + 1; k < points.length; ++k){
        if (k != j && points[k].equals(points[j]))
          throw new java.lang.IllegalArgumentException();
      }
    segments = new LineSegment[points.length - 1];
    n = 0;
    m = 0;
    Point[] auxiliary;
    slopes = new double[points.length - 1];
    Comparator<Point> comparator;
    for (int i = 0; i < points.length; ++i) {
      auxiliary = makeAuxiliary(points, i);
      comparator = points[i].slopeOrder();
      java.util.Arrays.sort(auxiliary, comparator);
      for (int j = 0; j < auxiliary.length - 2; ++j)
        if (points[i].slopeTo(auxiliary[j]) == points[i].slopeTo(auxiliary[j+2]) && !isinSlopes(points[i].slopeTo(auxiliary[j]))) {
          segments[n++] = furthestPoints(points[i], auxiliary[j], auxiliary[j + 1], auxiliary[j + 2]);
          slopes[m++] = points[i].slopeTo(auxiliary[j]);
        }
    }
  }

  public int numberOfSegments() {
    return n;
  }

  public LineSegment[] segments() {
    LineSegment[] returned = new LineSegment[n];
    java.lang.System.arraycopy(segments, 0, returned, 0, n);
    return returned;
  }

  private boolean isinSlopes(double slope) {
    for (int i = 0; i < m; ++i) {
      if (slopes[i] == slope)
        return true;
    }
    return false;
  }

  private Point[] makeAuxiliary(Point[] points, int extra) {
    Point[] result =  new Point[points.length - 1];
    int j = 0;
    for (int i = 0; i < points.length; ++i) {
      if (i != extra)
        result[j++] = points[i];
    }
    return result;
  }

  private LineSegment furthestPoints(Point a, Point b, Point c, Point d) {
    Point[] sorted;
    sorted = new Point[] {a, b, c, d};
    java.util.Arrays.sort(sorted);
    return new LineSegment(sorted[0], sorted[3]);
  }
}
