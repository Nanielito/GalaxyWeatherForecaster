package galaxy.weather.forecaster.domain;

/**
 * Defines the <code>Planet</code> class.
 */
public class Planet {

  /* DEFINITIONS **************************************************/

  private static final String PLANET_STRING_FORMAT = "Planet [radius='%d', velocity='%d', direction='%s']";

  /* MEMBERS DECLARATIONS *****************************************/

  private int mRadius;
  private int mVelocity;
  private DirectionTypeEnum mDirectionType;

  /* CLASS CONSTRUCTORS *******************************************/

  /**
   * Initializes an instance of the class.
   *
   * @param radius An <code>int</code> which represents the planet's distance from Sun.
   * @param velocity An <code>int</code> which represents the planet's angular velocity.
   * @param directionType A {@link DirectionTypeEnum} which represents the planet's direction type.
   */
  public Planet(int radius, int velocity, DirectionTypeEnum directionType) {
    mRadius = radius;
    mVelocity = velocity;
    mDirectionType = directionType;
  }

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Gets the <code>mRadius</code> property value.
   *
   * @return An <code>int</code> which represents the <code>mRadius</code> property value.
   */
  public int getRadius() {
    return mRadius;
  }

  /**
   * Gets the <code>mVelocity</code> property value.
   *
   * @return An <code>int</code> which represents the <code>mVelocity</code> property value.
   */
  public int getVelocity() {
    return mVelocity;
  }

  /**
   * Gets the <code>mDirectionType</code> property value.
   *
   * @return A {@link DirectionTypeEnum} which represents the <code>mDirectionType</code> property value.
   */
  public DirectionTypeEnum getDirection() {
    return mDirectionType;
  }

  /**
   * Gets the linear position related to the {@link Planet} instance at a specific day.
   *
   * @param day A <code>int</code> which represents the day number.
   *
   * @return A {@link Point} which represents a 2D point in cartesian space.
   */
  public Point getLinearPosition(int day) {
    double x = mRadius * Math.cos(mVelocity * mDirectionType.getDirectionType() * day);
    double y = mRadius * Math.sin(mVelocity * mDirectionType.getDirectionType() * day);
    Point position = new Point(x, y);

    return position;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format(PLANET_STRING_FORMAT, mRadius, mVelocity, mDirectionType.getDirectionValue());
  }
}
