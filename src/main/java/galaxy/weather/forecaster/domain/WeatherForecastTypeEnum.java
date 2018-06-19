package galaxy.weather.forecaster.domain;

/**
 * Defines the <code>WeatherForecastTypeEnum</code> enumeration.
 */
public enum WeatherForecastTypeEnum {

  /* DEFINITIONS **************************************************/

  DROUGHT("DROUGHT"),
  RAIN("RAIN"),
  OPTIMAL("OPTIMAL"),
  NORMAL("NORMAL"),
  UNKNOWN("UNKNOWN");

  /* MEMBERS DECLARATIONS *****************************************/

  private final String mType;

  /* ENUMERATION CONSTRUCTORS *************************************/

  /**
   * Initializes an instance oft the enumeration.
   *
   * @param type A <code>String</code> which represents the weather forecast type.
   */
  WeatherForecastTypeEnum(String type) {
    mType = type;
  }

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Gets the <code>mType</code> propery value.
   *
   * @return A <code>String</code> which represents the <code>mType</code> property value.
   */
  public String getType() {
    return mType;
  }
}
