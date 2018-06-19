package galaxy.weather.forecaster.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;
import galaxy.weather.forecaster.model.WeatherForecast;

/**
 * Declares custom methods for handling {@link WeatherForecast} model.
 */
public interface WeatherForecastRepository extends MongoRepository<WeatherForecast, String> {

  /* METHODS DECLARATIONS *****************************************/

  /**
   * Finds the {@link WeatherForecast} related to a day.
   *
   * @param day An <code>Integer</code> which represents the day to be searched.
   *
   * @return The {@link WeatherForecast} related to the day.
   */
  WeatherForecast findByDay(Integer day);

  /**
   * Finds a list of {@link WeatherForecast} related to a {@link WeatherForecastTypeEnum}.
   *
   * @param weatherForecastType A {@link WeatherForecastTypeEnum} which represents the weather forecast type to be
   *                            searched.
   *
   * @return The list which contains the {@link WeatherForecast}s related to the {@link WeatherForecastTypeEnum}.
   */
  List<WeatherForecast> findByWeatherForecastType(WeatherForecastTypeEnum weatherForecastType);

  /**
   * Counts the {@link WeatherForecast}s by type.
   *
   * @param weatherForecastType A {@link WeatherForecastTypeEnum} which represents the weather forecast type to be
   *                            searched.
   *
   * @return An <code>Integer</code> which represents the quantity of {@link WeatherForecast}s related to the
   *         {@link WeatherForecastTypeEnum}.
   */
  Integer countByWeatherForecastType(WeatherForecastTypeEnum weatherForecastType);
}
