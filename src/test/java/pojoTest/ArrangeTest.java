package pojoTest;

import sample.pojo.Arrange;

import static org.junit.Assert.*;

public class ArrangeTest {
  Arrange arrange = new Arrange();

  @org.junit.Test
  public void getCoach() {
    arrange.setCoach("Tom");
    assertEquals("Tom",arrange.getCoach());
  }

  @org.junit.Test
  public void getDate() {
    arrange.setDate("2021-5-24");
    assertEquals("2021-5-24",arrange.getDate());
  }

  @org.junit.Test
  public void getTime() {
    arrange.setTime("11:40");
    assertEquals("11:40",arrange.getTime());
  }

  @org.junit.Test
  public void getLocation() {
    arrange.setLocation("London");
    assertEquals("London",arrange.getLocation());
  }

  @org.junit.Test
  public void getItem() {
    arrange.setItem("Soccer");
    assertEquals("Soccer",arrange.getItem());
  }

  @org.junit.Test
  public void getUserId() {
    arrange.setUserId("wuruizheng");
    assertEquals("wuruizheng",arrange.getUserId());
  }
}
