package galaxy.weather.forecaster.controller.api;

import static org.hamcrest.Matchers.is;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;
import galaxy.weather.forecaster.domain.dto.WeatherForecastResponse;
import galaxy.weather.forecaster.model.WeatherForecast;
import galaxy.weather.forecaster.service.spec.IGalaxyWeatherForecasterService;

@RunWith(SpringRunner.class)
@WebMvcTest(GalaxyWeatherForecastRestController.class)
public class GalaxyWeatherForecastRestControllerTest {

  @Autowired
  private MockMvc mMvc;

  @MockBean
  private IGalaxyWeatherForecasterService mGalaxyWeatherForecasterService;

  @Test
  public void givenNoWeatherForecasts_whenGetWeatherForecasts_thenReturnEmptyString() throws Exception {
    int day = 314;
    WeatherForecastResponse response = null;

    given(mGalaxyWeatherForecasterService.getWeatherForecast(day)).willReturn(response);

    mMvc.perform(get(String.format("/weatherForecasts?day=%d", day)).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string(""));
  }

  @Test
  public void givenWeatherForecasts_whenGetWeatherForecasts_thenReturnJsonString() throws Exception {
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    double perimeter = 42;
    int day = 314;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);
    WeatherForecastResponse response = new WeatherForecastResponse(
      weatherForecast.getDay(),
      weatherForecast.getWeatherForecastType());

    given(mGalaxyWeatherForecasterService.getWeatherForecast(day)).willReturn(response);

    mMvc.perform(get(String.format("/weatherForecasts?day=%d", day)).contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.day", is(day)));
  }

  @Test
  public void givenNoWeatherForecasts_whenCountWeatherForecastByType_thenReturnZero() throws Exception {
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.DROUGHT;
    int count = 0;

    given(mGalaxyWeatherForecasterService.countWeatherForecastsByType(weatherForecastType)).willReturn(count);

    mMvc.perform(get(String.format("/weatherForecasts/%s/count", weatherForecastType.getType())))
      .andExpect(status().isOk())
      .andExpect(content().string(String.valueOf(count)));
  }

  @Test
  public void givenWeatherForecasts_whenCountWeatherForecastByType_thenReturnNonZero() throws Exception {
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    double perimeter = 42;
    int day = 314;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);
    int count = 1;

    given(mGalaxyWeatherForecasterService.countWeatherForecastsByType(weatherForecast.getWeatherForecastType())).willReturn(count);

    mMvc.perform(get(String.format("/weatherForecasts/%s/count", weatherForecastType.getType())))
      .andExpect(status().isOk())
      .andExpect(content().string(String.valueOf(count)));
  }

  @Test
  public void givenWeatherForecastRain_whenGetWeatherForecastWithMaximumRain_thenReturnJsonString() throws Exception {
    WeatherForecastTypeEnum weatherForecastType = WeatherForecastTypeEnum.RAIN;
    double perimeter = 42;
    int day = 314;
    WeatherForecast weatherForecast = new WeatherForecast(weatherForecastType, perimeter, day);
    WeatherForecastResponse response = new WeatherForecastResponse(
      weatherForecast.getDay(),
      weatherForecast.getWeatherForecastType());

    given(mGalaxyWeatherForecasterService.getWeatherForescastWithMaximumRain()).willReturn(response);

    mMvc.perform(get("/weatherForecasts/RAIN/maximum").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.day", is(day)));
  }
}
