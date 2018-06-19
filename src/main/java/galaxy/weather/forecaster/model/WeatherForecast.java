package galaxy.weather.forecaster.model;

import org.springframework.data.annotation.Id;

import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;

/**
 * Defines the entity class related to the <code>WeatherForecast</code> model.
 */
public class WeatherForecast {

  /* DEFINITIONS **************************************************/

  private static final String WEATHER_FORECAST_FORMAT = "%s[id=%s, forecast='%s', perimeter=%.2f, day=%d]";

  /* MEMBERS DECLARATIONS *****************************************/

  @Id
  private String id;
  private WeatherForecastTypeEnum weatherForecastType;
  private double perimeter;
  private int day;

  /* CLASS CONSTRUCTORS *******************************************/

  /**
   * Initializes a default instance of the class.
   */
  public WeatherForecast() {
  }

  /**
   * Initializes an instance of the class.
   *
   * @param weatherForecastType A {@link WeatherForecastTypeEnum} which represents the weather forecast type.
   * @param day An <code>int</code> which represents the day number.
   */
  public WeatherForecast(WeatherForecastTypeEnum weatherForecastType, int day) {
    this.weatherForecastType = weatherForecastType;
    this.day = day;
  }

  /**
   * Initializes an instance of the class.
   *
   * @param weatherForecastType A {@link WeatherForecastTypeEnum} which represents the weather forecast type.
   * @param perimeter A <code>double</code> which represents the perimeter value related to rainy days.
   * @param day An <code>int</code> which represents the day number.
   */
  public WeatherForecast(WeatherForecastTypeEnum weatherForecastType, double perimeter, int day) {
    this.weatherForecastType = weatherForecastType;
    this.perimeter = perimeter;
    this.day = day;
  }

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Gets the <code>id</code> property value.
   *
   * @return A <code>String</code> which represents the <code>id</code> property value.
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the <code>weatherForecastType</code> property value.
   *
   * @return A {@link WeatherForecastTypeEnum} which represents the <code>weatherForecastType</code> property value.
   */
  public WeatherForecastTypeEnum getWeatherForecastType() {
    return weatherForecastType;
  }

  /**
   * Gets the <code>perimeter</code> property value.
   *
   * @return A <code>double</code> which represents the <code>perimeter</code> property value.
   */
  public double getPerimeter() {
    return perimeter;
  }

  /**
   * Gets the <code>day</code> property value.
   *
   * @return A <code>int</code> which represents the <code>day</code> property value.
   */
  public int getDay() {
    return day;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format(WEATHER_FORECAST_FORMAT, getClass().getName(), weatherForecastType.getType(), perimeter, day);
  }
}
