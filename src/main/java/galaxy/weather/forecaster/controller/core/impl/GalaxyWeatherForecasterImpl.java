package galaxy.weather.forecaster.controller.core.impl;

import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import galaxy.weather.forecaster.data.PlanetEnum;
import galaxy.weather.forecaster.exception.ExceptionTypeEnum;
import galaxy.weather.forecaster.exception.GalaxyWeatherForecasterException;
import galaxy.weather.forecaster.domain.WeatherForecastTypeEnum;
import galaxy.weather.forecaster.model.WeatherForecast;
import galaxy.weather.forecaster.controller.core.spec.IGalaxyWeatherForecaster;
import galaxy.weather.forecaster.domain.Planet;
import galaxy.weather.forecaster.domain.Point;
import galaxy.weather.forecaster.repository.WeatherForecastRepository;
import galaxy.weather.forecaster.util.GeometricFunctions;

/**
 * Defines the class implementation for {@link IGalaxyWeatherForecaster} interface.
 */
@Component
public class GalaxyWeatherForecasterImpl implements IGalaxyWeatherForecaster {

  /* DEFINITIONS **************************************************/

  private static final int TOTAL_DAYS = 365 * 10;
  private static final int X          = 0;
  private static final int Y          = 0;

  private static final Logger logger = LogManager.getLogger(GalaxyWeatherForecasterImpl.class);

  /* MEMBERS DECLARATIONS *****************************************/

  @Autowired
  private WeatherForecastRepository mWeatherForecastRepository;

  private Point mSun;
  private Planet mBetasoide;
  private Planet mFerengi;
  private Planet mVulcano;

  /* CLASS CONSTRUCTORS *******************************************/

  /**
   * Initializes a default instance of the class.
   */
  public GalaxyWeatherForecasterImpl() {
  }

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Deletes all {@link WeatherForecast} records.
   *
   * @throws GalaxyWeatherForecasterException
   */
  private void deleteWeatherForecasts() throws GalaxyWeatherForecasterException {
    try {
      if (mWeatherForecastRepository.count() > 0) {
        mWeatherForecastRepository.deleteAll();
      }
    }
    catch (Exception ex) {
      logger.error(ex.getMessage());

      throw new GalaxyWeatherForecasterException(ExceptionTypeEnum.DATABASE_UNEXPECTED_ERROR, ex);
    }
  }

  /**
   * Initializes the galaxy properties.
   */
  private void initializeGalaxy() {
    mSun = new Point(X, Y);
    mBetasoide = new Planet(
      PlanetEnum.BETASOIDE.getRadius(),
      PlanetEnum.BETASOIDE.getVelocity(),
      PlanetEnum.BETASOIDE.getDirectionType());
    mFerengi = new Planet(
      PlanetEnum.FERENGI.getRadius(),
      PlanetEnum.FERENGI.getVelocity(),
      PlanetEnum.FERENGI.getDirectionType());
    mVulcano = new Planet(
      PlanetEnum.VULCANO.getRadius(),
      PlanetEnum.VULCANO.getVelocity(),
      PlanetEnum.VULCANO.getDirectionType());
  }

  /**
   * Checks if the planets are aligned.
   *
   * @param day An <code>int</code> which represents the day number.
   *
   * @return <code>true</code> if the planets are aligned; otherwise, <code>false</code>.
   */
  private boolean arePlanetsAligned(int day) {
    boolean areAligned = GeometricFunctions.arePointsAligned(
      mBetasoide.getLinearPosition(day),
      mFerengi.getLinearPosition(day),
      mVulcano.getLinearPosition(day));

    return areAligned;
  }

  /**
   * Checks if the planets are aligned with the sun.
   *
   * @param day An <code>int</code> which represents the day number.
   *
   * @return <code>true</code> if the planets are aligned with the sun; otherwise, <code>false</code>.
   */
  private boolean isSunAlignedWithPlanets(int day) {
    boolean isAlignedWithBetasoideAndFerengi = GeometricFunctions.arePointsAligned(
      mSun,
      mBetasoide.getLinearPosition(day),
      mFerengi.getLinearPosition(day));
    boolean isAlignedWithFerengiAndVulcano = GeometricFunctions.arePointsAligned(
      mSun,
      mFerengi.getLinearPosition(day),
      mVulcano.getLinearPosition(day));

    return (isAlignedWithBetasoideAndFerengi && isAlignedWithFerengiAndVulcano);
  }

  /**
   * Checks if the sun is inside a triangle.
   *
   * @param day An <code>int</code> which represents the day number.
   *
   * @return <code>true</code> if the sun is inside a triangle; otherwise, <code>false</code>.
   */
  private boolean isSunInsideTriangle(int day) {
    boolean isSunInsideTriangle = GeometricFunctions.isPointInsideTriangle(
      mSun,
      mBetasoide.getLinearPosition(day),
      mFerengi.getLinearPosition(day),
      mVulcano.getLinearPosition(day));

    return isSunInsideTriangle;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean calculateWeatherForecasts() {
    boolean result = true;

    try {
      deleteWeatherForecasts();
      initializeGalaxy();

      for (int day = 0; day <= TOTAL_DAYS; day++) {
        if (arePlanetsAligned(day) == true) {
          if (isSunAlignedWithPlanets(day)) {
            mWeatherForecastRepository.save(new WeatherForecast(WeatherForecastTypeEnum.DROUGHT, day));
          }
          else {
            mWeatherForecastRepository.save(new WeatherForecast(WeatherForecastTypeEnum.OPTIMAL, day));
          }
        }
        else {
          if (isSunInsideTriangle(day) == true) {
            double perimeter = GeometricFunctions.getTrianglePerimeter(
                mBetasoide.getLinearPosition(day),
                mFerengi.getLinearPosition(day),
                mVulcano.getLinearPosition(day));

            mWeatherForecastRepository.save(new WeatherForecast(WeatherForecastTypeEnum.RAIN, perimeter, day));
          }
          else {
            mWeatherForecastRepository.save(new WeatherForecast(WeatherForecastTypeEnum.NORMAL, day));
          }
        }
      }
    }
    catch (GalaxyWeatherForecasterException ex) {
      // logger.error(ex.getMessage());

      result = false;
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WeatherForecast getWeatherForecast(int day) throws GalaxyWeatherForecasterException {
    try {
      WeatherForecast weatherForecast = mWeatherForecastRepository.findByDay(day);

      return weatherForecast;
    }
    catch (Exception ex) {
      logger.error(ex.getMessage());

      throw new GalaxyWeatherForecasterException(ExceptionTypeEnum.DATABASE_UNEXPECTED_ERROR, ex);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int countWeatherForecastsByType(WeatherForecastTypeEnum weatherForecastType)
    throws GalaxyWeatherForecasterException {
    try {
      int count = mWeatherForecastRepository.countByWeatherForecastType(weatherForecastType);

      return count;
    }
    catch (Exception ex) {
      logger.error(ex.getMessage());

      throw new GalaxyWeatherForecasterException(ExceptionTypeEnum.DATABASE_UNEXPECTED_ERROR, ex);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public WeatherForecast getWeatherForecastWithMaximumRain() throws GalaxyWeatherForecasterException {
    try {
      List<WeatherForecast> rainyDays = mWeatherForecastRepository.findByWeatherForecastType(WeatherForecastTypeEnum.RAIN);
      WeatherForecast weatherForecast = null;

      if (rainyDays.size() > 0) {
        weatherForecast = rainyDays.stream().max(Comparator.comparing(WeatherForecast::getPerimeter)).orElse(null);
      }

      return weatherForecast;
    }
    catch (Exception ex) {
      logger.error(ex.getMessage());

      throw new GalaxyWeatherForecasterException(ExceptionTypeEnum.DATABASE_UNEXPECTED_ERROR, ex);
    }
  }
}
