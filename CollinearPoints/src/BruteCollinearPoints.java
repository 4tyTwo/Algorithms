import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

  private final LineSegment[] segments;
  private int n;

  public BruteCollinearPoints(Point[] points) {
    if (points == null)
      throw new java.lang.IllegalArgumentException();
    for (int j = 0; j < points.length; ++j)
      for (int k = j + 1; k < points.length; ++k) {
        if (points[k] == null || points[j] == null)
          throw new java.lang.IllegalArgumentException();
        if (k!=j && points[k] == points[j])
          throw new java.lang.IllegalArgumentException();
    }
    segments = new LineSegment[points.length - 1];
    n = 0;
    for (int i1 = 0; i1 < points.length; ++i1)
      for (int i2 = i1 + 1; i2 < points.length; ++i2)
        for (int i3 = i2 + 1; i3 < points.length; ++i3)
          for (int i4 = i3 + 1; i4 < points.length; ++i4) {
            Point a = points[i1];
            Point b = points[i2];
            Point c = points[i3];
            Point d = points[i4];
            if (a.slopeTo(b) == b.slopeTo(c) && b.slopeTo(c) == c.slopeTo(d))
              segments[n++] = furthestPoints(a, b, c, d);
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

  public static void main(String[] args) {
    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }

  private LineSegment furthestPoints(Point a, Point b, Point c, Point d) {
    Point[] sorted;
    sorted = new Point[] {a, b, c, d};
    java.util.Arrays.sort(sorted);
    return new LineSegment(sorted[0], sorted[3]);
  }

}
