package galaxy.weather.forecaster.domain.dto;

import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;

/**
 * Defines the <code>WeatherForecastResponse</code> class which is used as a wrapper for
 * {@link galaxy.weather.forecaster.model.WeatherForecast} information.
 */
public class WeatherForecastResponse {

  /* MEMBERS DECLARATIONS *****************************************/

  private int mDay;
  private WeatherForecastTypeEnum mWeatherForecastType;

  /* CLASS CONSTRUCTORS *******************************************/

  /**
   * Initializes an instance of the class.
   *
   * @param day An <code>int</code> which represents the day number.
   * @param weatherForecastType A {@link WeatherForecastTypeEnum} which represents the weather forecast type.
   */
  public WeatherForecastResponse(int day, WeatherForecastTypeEnum weatherForecastType) {
    this.mDay = day;
    this.mWeatherForecastType = weatherForecastType;
  }

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Gets the <code>mDay</code> property value.
   *
   * @return An <code>int</code> which represents the <code>mDay</code> property value.
   */
  public int getDay() {
    return mDay;
  }

  /**
   * Gets the <code>mWeatherForecastType</code> property value.
   *
   * @return A <code>String</code> which represents the <code>mWeatherForecastType</code> property value.
   */
  public String getWeatherForecastType() {
    return mWeatherForecastType.getType();
  }
}
