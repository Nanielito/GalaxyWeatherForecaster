package galaxy.weather.forecaster.data;

import galaxy.weather.forecaster.domain.DirectionTypeEnum;

/**
 * Defines the <code>PlanetEnum</code> enumeration which contains the static information related to the planets.
 */
public enum PlanetEnum {

  /* DEFINITIONS **************************************************/

  BETASOIDE(2000, 3, DirectionTypeEnum.CLOCKWISE),
  FERENGI  (500 , 1, DirectionTypeEnum.CLOCKWISE),
  VULCANO  (1000, 5, DirectionTypeEnum.ANTI_CLOCKWISE);

  /* MEMBERS DECLARATIONS *****************************************/

  private final int mRadius;
  private final int mVelocity;
  private final DirectionTypeEnum mDirectionType;

  /* ENUMERATION CONSTRUCTORS *************************************/

  /**
   * Initializes an instance of the enumeration.
   *
   * @param radius An <code>int</code> which represents the planet's distance from Sun.
   * @param velocity An <code>int</code> which represents the planet's angular velocity.
   * @param directionType A {@link DirectionTypeEnum} which represents the planet's direction type.
   */
  PlanetEnum(int radius, int velocity, DirectionTypeEnum directionType) {
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
  public DirectionTypeEnum getDirectionType() {
    return mDirectionType;
  }
}
