package sample.pojo;

import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {
  Course course = new Course();
  @Test
  public void getCoach() {
    course.setCoach("Tom");
    assertEquals("Tom",course.getCoach());
  }
  @Test
  public void getTime() {
    course.setTime("11:35");
    assertEquals("11:35",course.getTime());
  }
}
