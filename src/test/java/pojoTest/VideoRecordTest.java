package pojoTest;

import org.junit.Test;
import sample.pojo.VideoRecord;

import static org.junit.Assert.*;

public class VideoRecordTest {
  VideoRecord videoRecord = new VideoRecord();
  @Test
  public void getUserId() {
    videoRecord.setUserId("wrz123");
    assertEquals("wrz123",videoRecord.getUserId());
  }

  @Test
  public void getVideoId() {
    videoRecord.setVideoId("66666");
    assertEquals("66666",videoRecord.getVideoId());
  }

  @Test
  public void getVideoSort() {
    videoRecord.setVideoId("11");
    assertEquals("11",videoRecord.getVideoId());
  }
}
