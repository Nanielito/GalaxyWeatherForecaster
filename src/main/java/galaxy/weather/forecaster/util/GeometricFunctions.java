package galaxy.weather.forecaster.util;

import galaxy.weather.forecaster.domain.Point;

/**
 * Defines a set of geometric basic methods.
 */
public class GeometricFunctions {

  /* DEFINITIONS **************************************************/

  private static final double ALMOST_ZERO  = 0.0000001;
  private static final int    DECIMAL_BASE = 10;
  private static final int    PRECISION    = 1;

  /* STATIC METHODS IMPLEMENTATIONS *******************************/

  /**
   * Checks if two decimal values are identical using an approximation error.
   *
   * @param value1 A <code>double</code> which represents the decimal to be compared.
   * @param value2 A <code>double</code> which represents the decimal to be compared.
   *
   * @return <code>true</code> if the decimal values are (almost) identical; otherwise, <code>false</code>.
   */
  private static boolean areEqual(double value1, double value2) {
    boolean areEqual = Math.abs(value1 - value2) < ALMOST_ZERO;

    return areEqual;
  }

  /**
   * Adjusts the precision for a decimal value.
   *
   * @param value A <code>double</code> which represents the decimal value to be adjusted.
   *
   * @return A <code>double</code> which represents the adjusted decimal value.
   */
  private static double adjustPrecision(double value) {
    int factor = (int) Math.pow(DECIMAL_BASE, PRECISION);
    double newValue = Math.rint(value * factor) / factor;

    return newValue;
  }

  /**
   *
   * @param p1
   * @param p2
   * @param p3
   * @return
   */
  private static double sign(Point p1, Point p2, Point p3) {
    double product = (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) -
                     (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());

    return product;
  }

  /**
   * Gets the distance between two {@link Point}s.
   *
   * @param p1 A {@link Point} which represents the first point.
   * @param p2 A {@link Point} which represents the second  point.
   *
   * @return A <code>double</code> which represents the distance existing between the points.
   */
  public static double getDistanceBetweenTwoPoints(Point p1, Point p2) {
    // Given two points p1 = (x1, y1) and p2 = (x2, y2), the distance d can be calculated using...
    // Formula: d^2 = (x2 - x1)^2 +(y2 - y1)^2
    double distance = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));

    return distance;
  }

  /**
   * Gets the slope related to the straight line defined between two {@link Point}s.
   *
   * @param p1 A {@link Point} which represents the first point.
   * @param p2 A {@link Point} which represents the second point.
   *
   * @return A <code>double</code> which represents the slope related to the straight line defined between the points.
   */
  public static double getSlope(Point p1, Point p2) {
    // Given two points p1 = (x1, y1) and p2 = (x2, y2), the slope for the straight line formed between them can be
    // calculated using...
    // Formula: m = (y2 - y1) / (x2 - x1)
    double deltaY = p2.getY() - p1.getY();
    double deltaX = p2.getX() - p1.getX();
    double slope = Double.POSITIVE_INFINITY;

    if (areEqual(deltaX, 0) == false) {
      slope = deltaY / deltaX;
    }

    return slope;
  }

  /**
   * Checks if three {@link Point}s are aligned.
   *
   * @param p1 A {@link Point} which represents the first point to be checked.
   * @param p2 A {@link Point} which represents the second point to be checked.
   * @param p3 A {@link Point} which represents the third point to be checked.
   *
   * @return <code>true</code> if the three points are aligned; otherwise, <code>false</code>.
   */
  public static boolean arePointsAligned(Point p1, Point p2, Point p3) {
    double m1 = getSlope(p1, p2);
    double m2 = getSlope(p2, p3);

    return (adjustPrecision(m1) == adjustPrecision(m2));
  }

  /**
   * Gets the perimeter related to a triangle defined by three {@link Point}s (vertices).
   *
   * @param v1 A {@link Point} which represents the triangle's first vertex.
   * @param v2 A {@link Point} which represents the triangle's second vertex.
   * @param v3 A {@link Point} which represents the triangle's third vertex.
   *
   * @return A <code>double</code> which represents the triangle's perimeter.
   */
  public static double getTrianglePerimeter(Point v1, Point v2, Point v3) {
    double d1 = getDistanceBetweenTwoPoints(v1, v2);
    double d2 = getDistanceBetweenTwoPoints(v2, v3);
    double d3 = getDistanceBetweenTwoPoints(v3, v1);

    return (d1 + d2 + d3);
  }

  /**
   * Checks if a {@link Point} is located inside a triangle defined by three {@link Point}s (vertices).
   *
   * @param p A {@link Point} which represents the point to be checked.
   * @param v1 A {@link Point} which represents the triangle's first vertex.
   * @param v2 A {@link Point} which represents the triangle's second vertex.
   * @param v3 A {@link Point} which represents the triangle's third vertex.
   *
   * @return <code>true</code> if the point is located inside the triangle; otherwise, <code>false</code>.
   */
  public static boolean isPointInsideTriangle(Point p, Point v1, Point v2, Point v3) {
    boolean b1 = sign(p, v1, v2) < 0.0;
    boolean b2 = sign(p, v2, v3) < 0.0;
    boolean b3 = sign(p, v3, v1) < 0.0;

    return ((b1 == b2) && (b2 == b3));
  }
}
