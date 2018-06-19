package galaxy.weather.forecaster.exception;

/**
 * Defines the <code>ExceptionTypeEnum</code> enumeration.
 */
public enum ExceptionTypeEnum {

  /* DEFINITIONS **************************************************/

  INVALID_WEATHER_FORECAST_TYPE("galaxy.weather.forecaster.invalid.weather.forecast.type"),
  DATABASE_UNEXPECTED_ERROR("galaxy.weather.forecaster.database.unexpected.error");

  /* MEMBERS DECLARATIONS *****************************************/

  private final String mExceptionType;

  /* ENUMERATION CONSTRUCTORS *************************************/

  /**
   * Initializes an instance of the enumeration.
   *
   * @param exceptionType A <code>String</code> which represents the exception's type.
   */
  ExceptionTypeEnum(String exceptionType) {
    mExceptionType = exceptionType;
  }

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Gets the <code>mExceptionType</code> property value.
   *
   * @return A <code>String</code> which represents the <code>mExceptionType</code> property value.
   */
  public String getExceptionType() {
    return mExceptionType;
  }
}
