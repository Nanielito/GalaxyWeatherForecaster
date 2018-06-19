package galaxy.weather.forecaster.domain;

/**
 * Defines the <code>Point</code> class.
 */
public class Point {

  /* DEFINITIONS **************************************************/

  private static final String POINT_STRING_FORMAT = "Point [x='%.2f', y='%.2f']";

  /* MEMBERS DECLARATIONS *****************************************/

  private double mX;
  private double mY;

  /* CLASS CONSTRUCTORS *******************************************/

  /**
   * Initializes an instance of the class.
   *
   * @param x A <code>double</code> which represents a X coordinate into a 2D cartesian space.
   * @param y A <code>double</code> which represents a Y coordinate into a 2D cartesian space.
   */
  public Point(double x, double y) {
    mX = x;
    mY = y;
  }

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Gets the <code>mX</code> property value.
   *
   * @return A <code>double</code> which represents the <code>mX</code> property value.
   */
  public double getX() {
    return mX;
  }

  /**
   * Gets the <code>mY</code> property value.
   *
   * @return A <code>double</code> which represents the <code>mY</code> property value.
   */
  public double getY() {
    return mY;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format(POINT_STRING_FORMAT, mX, mY);
  }
}
