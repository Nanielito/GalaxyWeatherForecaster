package galaxy.weather.forecaster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import galaxy.weather.forecaster.controller.core.spec.IGalaxyWeatherForecaster;
import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;
import galaxy.weather.forecaster.domain.dto.JobResponse;
import galaxy.weather.forecaster.domain.dto.WeatherForecastResponse;
import galaxy.weather.forecaster.exception.GalaxyWeatherForecasterException;
import galaxy.weather.forecaster.model.WeatherForecast;
import galaxy.weather.forecaster.service.spec.IGalaxyWeatherForecasterService;

/**
 * Defines the class implementation for {@link IGalaxyWeatherForecasterService} interface.
 */
@Component
public class GalaxyWeatherForecasterServiceImpl implements IGalaxyWeatherForecasterService {

  /* DEFINITIONS **************************************************/

  private static final String MESSAGE_OK = "Weather forecasts were calculated successfully.";
  private static final String MESSAGE_FAIL = "Weather forecasts were failed while trying to calculate them.";

  /* MEMBERS DECLARATIONS *****************************************/

  @Autowired
  private IGalaxyWeatherForecaster mGalaxyWeatherForecaster;

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * {@inheritDoc}
   */
  @Override
  public JobResponse calculateWeatherForecasts() {
    JobResponse response = null;
    boolean result = mGalaxyWeatherForecaster.calculateWeatherForecasts();

    if (result == true) {
      response = new JobResponse(JobResponse.Status.OK, MESSAGE_OK);
    }
    else {
      response = new JobResponse(JobResponse.Status.FAIL, MESSAGE_FAIL);
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WeatherForecastResponse getWeatherForecast(Integer day) throws GalaxyWeatherForecasterException {
    WeatherForecastResponse response = null;
    WeatherForecast weatherForecast = mGalaxyWeatherForecaster.getWeatherForecast(day);

    if (weatherForecast != null) {
      response = new WeatherForecastResponse(weatherForecast.getDay(), weatherForecast.getWeatherForecastType());
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Integer countWeatherForecastsByType(WeatherForecastTypeEnum weatherForecastType)
    throws GalaxyWeatherForecasterException {
    return mGalaxyWeatherForecaster.countWeatherForecastsByType(weatherForecastType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WeatherForecastResponse getWeatherForescastWithMaximumRain() throws GalaxyWeatherForecasterException {
    WeatherForecastResponse response = null;
    WeatherForecast weatherForecast = mGalaxyWeatherForecaster.getWeatherForecastWithMaximumRain();

    if (weatherForecast != null) {
      response = new WeatherForecastResponse(weatherForecast.getDay(), weatherForecast.getWeatherForecastType());
    }

    return response;
  }
}
