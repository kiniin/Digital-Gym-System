package sample.pojo;

public class Coach {
  private String name;
  private String photoURL;
  private String account;
  private String password;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhotoURL() {
    return photoURL;
  }

  public void setPhotoURL(String photoURL) {
    this.photoURL = photoURL;
  }

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
