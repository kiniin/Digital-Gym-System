package pojoTest;

import org.junit.Test;
import sample.pojo.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UserTest {
    User user = new User();

    @Test
    public void getVip() {
        assertEquals("Normal",user.getVip());
        user.setVip("VIP");
        assertEquals("VIP",user.getVip());
    }

    @Test
    public void getEmail() {
        user.setEmail("1136001674@qq.com");
        assertEquals("1136001674@qq.com",user.getEmail());
    }

    @Test
    public void getUsername() {
        user.setUsername("kiniin");
        assertEquals("kiniin",user.getUsername());
    }

    @Test
    public void getHeight() {
        user.setHeight("173cm");
        assertEquals("173cm",user.getHeight());
    }

    @Test
    public void getWeight() {
        user.setWeight("60kg");
        assertEquals("60kg",user.getWeight());
    }

    @Test
    public void getBirthday() {
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = "2000-05-16";
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday(date);
        assertEquals(date,user.getBirthday());
    }

    @Test
    public void getGender() {
        user.setGender("male");
        assertEquals("male",user.getGender());
    }

    @Test
    public void getPassword() {
        user.setPassword("sth161510");
        assertEquals("sth161510",user.getPassword());
    }

    @Test
    public void getPhone() {
        user.setPhone("13051621788");
        assertEquals("13051621788",user.getPhone());
    }

    @Test
    public void getTrainingTimeInMon() {
        user.setTrainingTimeInMon(1);
        assertEquals(1,user.getTrainingTimeInMon());
    }

    @Test
    public void getTrainingTimeInTue() {
        user.setTrainingTimeInTue(2);
        assertEquals(2,user.getTrainingTimeInTue());
    }

    @Test
    public void getTrainingTimeInWed() {
        user.setTrainingTimeInWed(3);
        assertEquals(3,user.getTrainingTimeInWed());
    }

    @Test
    public void getTrainingTimeInThu() {
        user.setTrainingTimeInThu(4);
        assertEquals(4,user.getTrainingTimeInThu());
    }

    @Test
    public void getTrainingTimeInFri() {
        user.setTrainingTimeInFri(5);
        assertEquals(5,user.getTrainingTimeInFri());
    }

    @Test
    public void getTrainingTimeInSat() {
        user.setTrainingTimeInSat(6);
        assertEquals(6,user.getTrainingTimeInSat());
    }

    @Test
    public void getTrainingTimeInSun() {
        user.setTrainingTimeInSun(7);
        assertEquals(7,user.getTrainingTimeInSun());
    }

    @Test
    public void getLastStudyDate() {
        user.setLastStudyDate("2021-5-30");
        assertEquals("2021-5-30",user.getLastStudyDate());
    }

    @Test
    public void testToString() {
        assertEquals("User{username='null', password='null', email='null', phone='null', height='null', weight='null', birthday=null, gender='null', vip=Normal}",user.toString());
    }
}