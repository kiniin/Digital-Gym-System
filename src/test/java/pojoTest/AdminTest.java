package pojoTest;

import org.junit.Test;
import sample.pojo.Admin;

import static org.junit.Assert.*;

public class AdminTest {
  Admin admin = new Admin();

  @Test
  public void getName() {
    admin.setName("Wuruizheng");
    assertEquals("Wuruizheng",admin.getName());
  }

  @Test
  public void getAccount() {
    admin.setAccount("Wuruizheng");
    assertEquals("Wuruizheng",admin.getAccount());
  }

  @Test
  public void getPassword() {
    admin.setPassword("wrz123");
    assertEquals("wrz123",admin.getPassword());
  }

  @Test
  public void testToString() {
    admin.setPassword("wrz123");
    admin.setAccount("Wuruizheng");
    admin.setName("Wuruizheng");
    assertEquals("Admin{name='Wuruizheng', account='Wuruizheng', password='wrz123'}",admin.toString());
  }
}
