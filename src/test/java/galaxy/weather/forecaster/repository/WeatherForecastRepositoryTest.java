package galaxy.weather.forecaster.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import galaxy.weather.forecaster.Application;
import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;
import galaxy.weather.forecaster.model.WeatherForecast;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class WeatherForecastRepositoryTest {

  @Autowired
  private WeatherForecastRepository mWeatherForecastRepository;

  @Autowired
  private MongoTemplate mMongoTemplate;

  @Before
  public void setup() throws Exception {
    mMongoTemplate.dropCollection(WeatherForecast.class);
  }

  @After
  public void tearDown() throws Exception {
    mMongoTemplate.dropCollection(WeatherForecast.class);
  }

  @Test
  public void whenCollectionIsEmpty_thenWeatherForecastMustBeNull() {
    // Given
    int documentsQuantity = 0;
    int day = 42;

    // When
    WeatherForecast found = mWeatherForecastRepository.findByDay(day);

    // Then
    assertEquals(mWeatherForecastRepository.count(), documentsQuantity);
    assertNull(found);
  }

  @Test
  public void whenCollectionIsEmpty_thenWeatherForecastListMustBeEmpty() {
    // Given
    int documentsQuantity = 0;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;

    // When
    List<WeatherForecast> founds = mWeatherForecastRepository.findByWeatherForecastType(weatherForecastType);

    // Then
    assertEquals(founds.size(), documentsQuantity);
  }

  @Test
  public void whenCollectionIsEmpty_thenCountMustBeZero() {
    // Given
    int documentsQuantity = 0;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;

    // When
    int count = mWeatherForecastRepository.countByWeatherForecastType(weatherForecastType);

    // Then
    assertEquals(count, documentsQuantity);
  }

  @Test
  public void whenCollectionIsNotEmpty_thenWeatherForecastMustBeNull_IfDayDoesNotExist() {
    // Given
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    double perimeter = 42;
    int day = 314;
    int anotherDay = 33;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);

    mWeatherForecastRepository.save(weatherForecast);

    // When
    WeatherForecast found = mWeatherForecastRepository.findByDay(anotherDay);

    // Then
    assertNull(found);
  }

  @Test
  public void whenCollectionIsNotEmpty_thenWeatherForecastMustBeFound_IfDayExists() {
    // Given
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    double perimeter = 42;
    int day = 314;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);

    mWeatherForecastRepository.save(weatherForecast);

    // When
    WeatherForecast found = mWeatherForecastRepository.findByDay(day);

    // Then
    assertEquals(weatherForecast.getPerimeter(), found.getPerimeter(), 0);
    assertEquals(weatherForecast.getWeatherForecastType(), found.getWeatherForecastType());
  }

  @Test
  public void whenCollectionIsNotEmpty_thenWeatherForecastListMustBeEmpty_IfWeatherForecastTypeDoesNotExists() {
    // Given
    int documentsQuantity = 0;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    WeatherForecastTypeEnum anotherWeatherForecastType = WeatherForecastTypeEnum.OPTIMAL;
    double perimeter = 42;
    int day = 314;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);

    mWeatherForecastRepository.save(weatherForecast);

    // When
    List<WeatherForecast> founds = mWeatherForecastRepository.findByWeatherForecastType(anotherWeatherForecastType);

    // Then
    assertEquals(founds.size(), documentsQuantity);
  }

  @Test
  public void whenCollectionIsNotEmpty_thenWeatherForecastListMustBeNonEmpty_IfWeatherForecastTypeExists() {
    // Given
    int documentsQuantity = 1;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    double perimeter = 42;
    int day = 314;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);

    mWeatherForecastRepository.save(weatherForecast);

    // When
    List<WeatherForecast> founds = mWeatherForecastRepository.findByWeatherForecastType(weatherForecastType);

    // Then
    assertEquals(founds.size(), documentsQuantity);
  }

  @Test
  public void whenCollectionIsNotEmpty_thenCountMustBeZero_IfWeatherForecastTypeDoesNotExists() {
    // Given
    int documentsQuantity = 0;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    WeatherForecastTypeEnum anotherWeatherForecastType = WeatherForecastTypeEnum.OPTIMAL;
    double perimeter = 42;
    int day = 314;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);

    mWeatherForecastRepository.save(weatherForecast);

    // When
    int count = mWeatherForecastRepository.countByWeatherForecastType(anotherWeatherForecastType);

    // Then
    assertEquals(count, documentsQuantity);
  }

  @Test
  public void whenCollectionIsNotEmpty_thenCountMusBeNonZero_IfWeatherForecastTypeExists() {
    // Given
    int documentsQuantity = 1;
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    double perimeter = 42;
    int day = 314;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);

    mWeatherForecastRepository.save(weatherForecast);

    // When
    int count = mWeatherForecastRepository.countByWeatherForecastType(weatherForecastType);

    // Then
    assertEquals(count, documentsQuantity);
  }
}
