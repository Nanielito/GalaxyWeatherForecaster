package galaxy.weather.forecaster.domain.dto;

/**
 * Defines the <code>JobResponse</code> class which is used as a wrapper for
 * {@link galaxy.weather.forecaster.model.WeatherForecast} information.
 */
public class JobResponse {

  /* ENUMERATION DECLARATIONS *************************************/

  public enum Status {

    /* DEFINITIONS **************************************************/

    OK("OK"),
    FAIL("FAIL");

    /* MEMBERS DECLARATIONS *****************************************/

    private String mStatus;

    /* ENUMERATION CONSTRUCTORS *************************************/

    /**
     * Initializes an instance of the enumeration.
     *
     * @param status A <code>String</code> which represents the response's status.
     */
    Status(String status) {
      mStatus = status;
    }

    /* METHODS IMPLEMENTATIONS **************************************/

    /**
     * Gets the <code>mStatus</code> property value.
     *
     * @return A <code>String</code> which represents the <code>mStatus</code> property value.
     */
    public String getStatus() {
      return mStatus;
    }
  }

  /* MEMBERS DECLARATIONS *****************************************/

  private Status mStatus;
  private String mMessage;

  /* CLASS CONSTRUCTORS *******************************************/

  /**
   * Initializes an instance of the class.
   *
   * @param status A {@link Status} which represents the response's status.
   * @param message A <code>String</code> which represents the response's message.
   */
  public JobResponse(Status status, String message) {
    mStatus = status;
    mMessage = message;
  }

  /* METHODS IMPLEMENTATIONS **************************************/

  /**
   * Gets the <code>mStatus</code> property value.
   *
   * @return A <code>String</code> which represents the <code>mStatus</code> property value.
   */
  public String getStatus() {
    return mStatus.getStatus();
  }

  /**
   * Gets the <code>mMessage</code> property value.
   *
   * @return A <code>String</code> which represents the <code>mMessage</code> property value.
   */
  public String getMessage() {
    return mMessage;
  }
}
