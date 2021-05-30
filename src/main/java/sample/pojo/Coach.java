package sample.pojo;

/**
 *
 * Abstract of the coach of London Gym, record all the information about admin.
 * Record the useful attributes of an coach, and give the getters and setters
 * of those attributes.
 * <p>The attributes of the class are as follows:</p>
 * <ul>
 * <li>name</li>
 * <li>photoURL</li>
 * <li>account</li>
 * <li>password</li>
 * </ul>
 *
 * @author Ruizheng Wu
 * @iteration 2.0
 */
public class Coach {
  private String name;
  private String photoURL;
  private String account;
  private String password;

  /**
   * Get the name of the coach.
   * @return Name of the coach.
   */
  public String getName() {
    return name;
  }
  /**
   * Set the new name of an coach.
   * @param name The new name of the coach.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the account of the coach.
   * @return Account of the coach.
   */
  public String getAccount() {
    return account;
  }

  /**
   * Set the new account of an coach.
   * @param account The new account of the coach.
   */
  public void setAccount(String account) {
    this.account = account;
  }

  /**
   * Get the name of the coach.
   * @return Name of the coach.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the new password of an coach.
   * @param password The new password of the coach.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Get the photo's URL of the coach.
   * @return Photo's URL of the coach.
   */
  public String getPhotoURL() {
    return photoURL;
  }

  /**
   * Set the new photo of an coach.
   * @param photoURL The new photo of the coach.
   */
  public void setPhotoURL(String photoURL) {
    this.photoURL = photoURL;
  }

  /**
   * Rewrite the toString method of Coach.class,
   * let it output all the information when we call its toString() method.
   * @return
   */
  @Override
  public String toString() {
    return "Coach{" +
            "name='" + name + '\'' +
            ", photoURL='" + photoURL + '\'' +
            ", account='" + account + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
