package sample.pojo;

import java.util.ArrayList;
import java.util.List;

public class Arrange {
  private String date;
  private List<Course> course;

  public List<Course> getCourse() {
    return course;
  }

  public void setCourse(List<Course> course) {
    this.course = course;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
