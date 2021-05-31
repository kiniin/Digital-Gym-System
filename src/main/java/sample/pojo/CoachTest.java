package sample.pojo;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoachTest {
  Coach coach = new Coach();
  @Test
  public void getName() {
    coach.setName("Tom");
    assertEquals("Tom",coach.getName());
  }

  @Test
  public void getAccount() {
    coach.setAccount("tom");
    assertEquals("tom",coach.getAccount());
  }

  @Test
  public void getPassword() {
    coach.setPassword("tom123");
    assertEquals("tom123",coach.getPassword());
  }

  @Test
  public void getPhotoURL() {
    coach.setPhotoURL("https://github.com/kiniin/Digital-Gym-System");
    assertEquals("https://github.com/kiniin/Digital-Gym-System",coach.getPhotoURL());
  }

  @Test
  public void testToString() {
    coach.setPhotoURL("https://github.com/kiniin/Digital-Gym-System");
    coach.setPassword("tom123");
    coach.setAccount("tom");
    coach.setName("Tom");

    assertEquals("Coach{name='Tom', photoURL='https://github.com/kiniin/Digital-Gym-System', account='tom', password='tom123'}",coach.toString());
  }
}
