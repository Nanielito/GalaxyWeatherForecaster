package galaxy.weather.forecaster.domain;

/**
 * Defines the <code>DirectionTypeEnum</code> enumeration.
 */
public enum DirectionTypeEnum {

  /* DEFINITIONS **************************************************/

  CLOCKWISE(-1, "CLOCKWISE"),
  ANTI_CLOCKWISE(+1, "ANTI-CLOCKWISE");

  /* MEMBERS DECLARATIONS *****************************************/

  private int mDirectionType;
  private String mDirectionValue;

  /* ENUMERATION CONSTRUCTORS *************************************/

  /**
   * Initializes an instance of the enumeration.
   *
   * @param directionType An <code>int</code> which represents the direction type.
   * @param directionValue A <code>String</code> which represents the direction value.
   */
  DirectionTypeEnum(int directionType, String directionValue) {
    mDirectionType = directionType;
    mDirectionValue = directionValue;
  }

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Gets the <code>mDirectionType</code> property value.
   *
   * @return An <code>int</code> which represents the <code>mDirectionType</code> property value.
   */
  public int getDirectionType() {
    return mDirectionType;
  }

  /**
   * Gets the <code>mDirectionValue</code> property value.
   *
   * @return A <code>String</code> which represents the <code>mDirectionValue</code> property value.
   */
  public String getDirectionValue() {
    return mDirectionValue;
  }
}
