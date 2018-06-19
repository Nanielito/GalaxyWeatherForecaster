package galaxy.weather.forecaster.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import galaxy.weather.forecaster.domain.dto.JobResponse;
import galaxy.weather.forecaster.domain.dto.WeatherForecastResponse;
import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;
import galaxy.weather.forecaster.exception.ExceptionTypeEnum;
import galaxy.weather.forecaster.exception.GalaxyWeatherForecasterException;
import galaxy.weather.forecaster.service.spec.IGalaxyWeatherForecasterService;

/**
 * Defines the RESTful resource entry points.
 */
@RestController
public class GalaxyWeatherForecastRestController {

  @Autowired
  IGalaxyWeatherForecasterService mGalaxyWeatherForecasterService;

  @PostMapping("/weatherForecasts")
  JobResponse calculateWeatherForecasts() {
    JobResponse response = mGalaxyWeatherForecasterService.calculateWeatherForecasts();

    return response;
  }

  @GetMapping("/weatherForecasts")
  WeatherForecastResponse getWeatherForecast(@RequestParam Integer day) throws GalaxyWeatherForecasterException {
    WeatherForecastResponse response = mGalaxyWeatherForecasterService.getWeatherForecast(day);

    return response;
  }

  @GetMapping("/weatherForecasts/{weatherType}/count")
  Integer getWeatherForecastCountByType(@PathVariable String weatherType) throws GalaxyWeatherForecasterException {
    WeatherForecastTypeEnum weatherForecastType;
    Integer count = 0;

    switch (weatherType.toUpperCase()) {
      case "DROUGHT":
        weatherForecastType = WeatherForecastTypeEnum.DROUGHT;
        break;
      case "RAIN":
        weatherForecastType = WeatherForecastTypeEnum.RAIN;
        break;
      case "OPTIMAL":
        weatherForecastType = WeatherForecastTypeEnum.OPTIMAL;
        break;
      case "NORMAL":
        weatherForecastType = WeatherForecastTypeEnum.NORMAL;
        break;
      default:
        weatherForecastType = WeatherForecastTypeEnum.UNKNOWN;
        break;
    }

    if (weatherForecastType.equals(WeatherForecastTypeEnum.UNKNOWN)) {
      throw new GalaxyWeatherForecasterException(ExceptionTypeEnum.INVALID_WEATHER_FORECAST_TYPE.getExceptionType());
    }

    count = mGalaxyWeatherForecasterService.countWeatherForecastsByType(weatherForecastType);

    return count;
  }

  @GetMapping("/weatherForecasts/RAIN/maximum")
  WeatherForecastResponse getWeatherForecastWithMaximumRain() throws GalaxyWeatherForecasterException {
    WeatherForecastResponse response = mGalaxyWeatherForecasterService.getWeatherForescastWithMaximumRain();

    return response;
  }
}
