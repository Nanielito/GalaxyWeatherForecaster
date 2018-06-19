package galaxy.weather.forecaster.exception;

/**
 * Defines the <code>GalaxyWeatherForecasterException</code> class.
 */
public class GalaxyWeatherForecasterException extends Exception {

  /* DEFINITIONS **************************************************/

  private static final String MESSAGE_KEY = "galaxy.weather.forecaster.error";

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Initializes a default instance of the class.
   * @param databaseUnexpectedError
   * @param ex
   */
  public GalaxyWeatherForecasterException(ExceptionTypeEnum databaseUnexpectedError, Exception ex) {
    super(MESSAGE_KEY);
  }

  /**
   * Initializes an instance of the class.
   *
   * @param message A <code>String</code> which represents the exception's message.
   */
  public GalaxyWeatherForecasterException(String message) {
    super(message);
  }

  /**
   * Initializes an instance of the class.
   *
   * @param exception A <code>Throwable</code> which represents an exception instance.
   */
  public GalaxyWeatherForecasterException(Throwable exception) {
    super(exception);
  }

  /**
   * Initializes an instance of the class.
   *
   * @param message A <code>String</code> which represents the exception's message.
   * @param exception A <code>Throwable</code> which represents an exception instance.
   */
  public GalaxyWeatherForecasterException(String message, Throwable exception) {
    super(message, exception);
  }
}
