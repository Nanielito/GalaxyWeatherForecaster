package galaxy.weather.forecaster.controller.core.spec;

import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;
import galaxy.weather.forecaster.exception.GalaxyWeatherForecasterException;
import galaxy.weather.forecaster.model.WeatherForecast;

/**
 * Defines the <code>IGalaxyWeatherForecaster</code> interface specification.
 */
public interface IGalaxyWeatherForecaster {

  /* METHODS SPECIFICATIONS ***************************************/

  /**
   * Calculates the weather forecasts.
   *
   * @return <code>true</code> if the process is successful; otherwise, <code>false</code>.
   */
  boolean calculateWeatherForecasts();

  /**
   * Gets the {@link WeatherForecast} for a specific day.
   *
   * @param day A <code>int</code> which represents the day number to be searched.
   *
   * @return The {@link WeatherForecast} related to the day.
   */
  WeatherForecast getWeatherForecast(int day) throws GalaxyWeatherForecasterException;

  /**
   * Counts the weather forecasts related to a weather forecast type.
   *
   * @param weatherForecastType A {@link WeatherForecastTypeEnum} which represents the weather forecast type
   *                            to be searched.
   *
   * @return An <code>int</code> which represents the number of weather forecasts related to the
   *         {@link WeatherForecastTypeEnum}.
   */
  int countWeatherForecastsByType(WeatherForecastTypeEnum weatherForecastType) throws GalaxyWeatherForecasterException;

  /**
   * Gets the {@link WeatherForecast} related to the day with maximum rain.
   *
   * @return The {@link WeatherForecast} related to the day with maximum rain.
   */
  WeatherForecast getWeatherForecastWithMaximumRain() throws GalaxyWeatherForecasterException;
}
