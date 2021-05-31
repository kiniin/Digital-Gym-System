package pojoTest;

import org.junit.Test;
import sample.pojo.Video;

import static org.junit.Assert.*;

public class VideoTest {
  Video video = new Video();

  @Test
  public void getVideoId() {
    video.setVideoId("0001");
    assertEquals("0001",video.getVideoId());
  }

  @Test
  public void getStar() {
    video.setStar("15000");
    assertEquals("15000",video.getStar());
  }

  @Test
  public void getVideoUrl() {
    video.setVideoUrl("https://github.com/kiniin/Digital-Gym-System");
    assertEquals("https://github.com/kiniin/Digital-Gym-System",video.getVideoUrl());
  }

  @Test
  public void getCoverUrl() {
    video.setCoverUrl("https://github.com/kiniin/Digital-Gym-System");
    assertEquals("https://github.com/kiniin/Digital-Gym-System",video.getCoverUrl());
  }

  @Test
  public void getTitle() {
    video.setTitle("Best fitting!");
    assertEquals("Best fitting!",video.getTitle());
  }

  @Test
  public void getSort() {
    video.setSort("football");
    assertEquals("football",video.getSort());
  }

  @Test
  public void getInstrument() {
    video.setInstrument("ball");
    assertEquals("ball",video.getInstrument());
  }

  @Test
  public void getFrequency() {
    video.setFrequency("three");
    assertEquals("three",video.getFrequency());
  }

  @Test
  public void getIntroduction() {
    video.setIntroduction("This course is amazing!");
    assertEquals("This course is amazing!",video.getIntroduction());
  }
}
