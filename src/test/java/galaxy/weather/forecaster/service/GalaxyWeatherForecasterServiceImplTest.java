package galaxy.weather.forecaster.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import galaxy.weather.forecaster.Application;
import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;
import galaxy.weather.forecaster.domain.dto.WeatherForecastResponse;
import galaxy.weather.forecaster.exception.GalaxyWeatherForecasterException;
import galaxy.weather.forecaster.model.WeatherForecast;
import galaxy.weather.forecaster.repository.WeatherForecastRepository;
import galaxy.weather.forecaster.service.impl.GalaxyWeatherForecasterServiceImpl;
import galaxy.weather.forecaster.service.spec.IGalaxyWeatherForecasterService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class GalaxyWeatherForecasterServiceImplTest {

  @TestConfiguration
  static class GalaxyWeatherForecasterServiceImplContextConfiguration {

    @Bean
    public IGalaxyWeatherForecasterService galaxyWeatherForecasterService() {
      return new GalaxyWeatherForecasterServiceImpl();
    }
  }

  @Autowired
  private IGalaxyWeatherForecasterService mGalaxyWeatherForecasterService;

  @MockBean
  private WeatherForecastRepository mWeatherForecastRepository;

  @Before
  public void setup() {
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    double perimeter = 42;
    int day = 314;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);

    Mockito.when(mWeatherForecastRepository.findByDay(day)).thenReturn(weatherForecast);
    Mockito.when(mWeatherForecastRepository.findByWeatherForecastType(weatherForecastType)).thenReturn(
        new ArrayList<WeatherForecast>() {{ add(weatherForecast); }});
    Mockito.when(mWeatherForecastRepository.countByWeatherForecastType(weatherForecastType)).thenReturn(1);
  }

  @Test
  public void whenNotValidDay_thenWeatherForecastMustBeNull() throws GalaxyWeatherForecasterException {
    // Given
    int day = 42;

    // When
    WeatherForecastResponse found = mGalaxyWeatherForecasterService.getWeatherForecast(day);

    // Then
    assertNull(found);
  }

  @Test
  public void whenValidDay_thenWeatherForecastMustBeFound() throws GalaxyWeatherForecasterException {
    // Given
    int day = 314;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;

    // When
    WeatherForecastResponse found = mGalaxyWeatherForecasterService.getWeatherForecast(day);

    // Then
    assertEquals(found.getDay(), day);
    assertEquals(found.getWeatherForecastType(), weatherForecastType.getType());
  }

  @Test
  public void whenThereIsNotWeatherForecastForCertainType_thenCountMustBeZero() throws GalaxyWeatherForecasterException {
    // Given
    int weatherForecastsCount = 0;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.DROUGHT;

    // When
    int count = mGalaxyWeatherForecasterService.countWeatherForecastsByType(weatherForecastType);

    // Then
    assertEquals(count, weatherForecastsCount);
  }

  @Test
  public void whenThereAreWeatherForecastsForCertainType_thenCountMustBeNonZero() throws GalaxyWeatherForecasterException {
    // Given
    int weatherForecastsCount = 1;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;

    // When
    int count = mGalaxyWeatherForecasterService.countWeatherForecastsByType(weatherForecastType);

    // Then
    assertEquals(count, weatherForecastsCount);
  }

  @Test
  public void whenThereIsWeatherForecastRain_thenDayWithMaximumRainMustBeFound() throws GalaxyWeatherForecasterException {
    // Given
    int day = 314;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;

    // When
    WeatherForecastResponse found = mGalaxyWeatherForecasterService.getWeatherForescastWithMaximumRain();

    // Then
    assertEquals(found.getDay(), day);
    assertEquals(found.getWeatherForecastType(), weatherForecastType.getType());
  }
}
