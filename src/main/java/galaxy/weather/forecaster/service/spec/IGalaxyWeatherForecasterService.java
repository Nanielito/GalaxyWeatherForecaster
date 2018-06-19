package galaxy.weather.forecaster.service.spec;

import galaxy.weather.forecaster.domain.dto.JobResponse;
import galaxy.weather.forecaster.domain.dto.WeatherForecastResponse;
import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;
import galaxy.weather.forecaster.exception.GalaxyWeatherForecasterException;

/**
 * Defines the <code>IGalaxyWeatherForecasterService</code> interface specification.
 */
public interface IGalaxyWeatherForecasterService {

  /* METHODS SPECIFICATIONS ***************************************/

  /**
   * Calculates the weather forecasts.
   *
   * @return A {@link JobResponse} which contains the information related to the process status.
   */
  JobResponse calculateWeatherForecasts();

  /**
   * Gets the weather forecast information for a specific day.
   *
   * @param day A <code>Integer</code> which represents the day number to be searched.
   *
   * @return A {@link WeatherForecastResponse} which contains the information related to the day.
   *
   * @throws GalaxyWeatherForecasterException
   */
  WeatherForecastResponse getWeatherForecast(Integer day) throws GalaxyWeatherForecasterException;

  /**
   * Counts the weather forecasts related to a weather forecast type.
   *
   * @param weatherForecastType A {@link WeatherForecastTypeEnum} which represents the weather forecast type
   *                            to be searched.
   *
   * @return An <code>Integer</code> which represents the number of weather forecasts related to
   *         the {@link WeatherForecastTypeEnum}.
   *
   * @throws GalaxyWeatherForecasterException
   */
  Integer countWeatherForecastsByType(WeatherForecastTypeEnum weatherForecastType) throws GalaxyWeatherForecasterException;

  /**
   * Gets the weather forecast information related to the day with maximum rain.
   *
   * @return A {@link WeatherForecastResponse} which contains the information related to the day with maximum rain.
   *
   * @throws GalaxyWeatherForecasterException
   */
  WeatherForecastResponse getWeatherForescastWithMaximumRain() throws GalaxyWeatherForecasterException;
}
