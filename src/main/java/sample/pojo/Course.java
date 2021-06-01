package sample.pojo;

/**
 * Abstract of the courses of London Gym, record all the information about admin.
 * Record the useful attributes of a specific course, and give the getters and setters
 * of those attributes.
 * <p>The attributes of the class are as follows:</p>
 * <ul>
 * <li>name</li>
 * <li>account</li>
 * <li>password</li>
 * </ul>
 *
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @author Tenghao Su
 * @version 2.0
 */
public class Course {
  private String coach;
  private String time;

  /**
   * Get the coach of the class.
   * @return The coach of the class.
   */
  public String getCoach() {
    return coach;
  }

  /**
   * Set the coach of a class.
   * @param coach The coach of the class.
   */
  public void setCoach(String coach) {
    this.coach = coach;
  }

  /**
   * Get the time of the class.
   * @return The time of the class.
   */
  public String getTime() {
    return time;
  }

  /**
   * Set the time of a class.
   * @param time The time of a class.
   */
  public void setTime(String time) {
    this.time = time;
  }
}
