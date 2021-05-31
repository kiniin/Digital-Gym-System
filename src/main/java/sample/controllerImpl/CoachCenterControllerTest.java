package sample.controllerImpl;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CoachCenterControllerTest {

  @org.junit.jupiter.api.Test
  void ensureCourse() throws IOException {
    assertEquals(true,new CoachCenterController().ensureCourse());
  }
}
