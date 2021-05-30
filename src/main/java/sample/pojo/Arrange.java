package sample.pojo;


/**
 *
 * Abstract of the arrangements of London Gym, record all the information about admin.
 * Record the useful attributes of an order information, and give the getters and setters
 * of those attributes.
 * <p>The attributes of the class are as follows:</p>
 * <ul>
 * <li>coach</li>
 * <li>date</li>
 * <li>time</li>
 * <li>location</li>
 * <li>item</li>
 * <li>userId</li>
 * </ul>
 *
 * @author Ruizheng Wu
 * @iteration 2.0
 */
public class Arrange {
  private String coach;
  private String date;
  private String time;
  private String location;
  private String item;
  private String userId;

  /**
   * Get the coach of the class.
   * @return The coach of the class.
   */
  public String getCoach() {
    return coach;
  }

  /**
   * Set a new coach to the course.
   * @param coach New coach's name of the course.
   */
  public void setCoach(String coach) {
    this.coach = coach;
  }

  /**
   * Get the date of the course.
   * @return The date of the course.
   */
  public String getDate() {
    return date;
  }

  /**
   * Set a new date of the course.
   * @param date New date of the course.
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * Get the name of the coach.
   * @return The name of the Coach.
   */
  public String getTime() {
    return time;
  }

  /**
   * Set a new time of the course.
   * @param time New time of the course.
   */
  public void setTime(String time) {
    this.time = time;
  }

  /**
   * Get the location of a class.
   * @return The location of a class.
   */
  public String getLocation() {
    return location;
  }

  /**
   * Set a new location of the course.
   * @param location New location of a course.
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Get the kind of a class.
   * @return The kind of a class.
   */
  public String getItem() {
    return item;
  }

  /**
   * Set a new item of the course.
   * @param item New item of a course.
   */
  public void setItem(String item) {
    this.item = item;
  }

  /**
   * Get the account of the user.
   * @return The account of the user.
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Set a new ID of a user.
   * @param userId New ID of a user.
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }
}
