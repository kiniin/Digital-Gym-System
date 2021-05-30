package sample.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String height;
    private String weight;
    private Date birthday;
    private String gender;
    private String vip;
    private int trainingTimeInMon;
    private int trainingTimeInTue;
    private int trainingTimeInWed;
    private int trainingTimeInThu;
    private int trainingTimeInFri;
    private int trainingTimeInSat;
    private int trainingTimeInSun;
    private String lastStudyDate;

    public User() {
        this.vip = "Normal";
        this.trainingTimeInMon = 0;
        this.trainingTimeInTue = 0;
        this.trainingTimeInWed = 0;
        this.trainingTimeInThu = 0;
        this.trainingTimeInFri = 0;
        this.trainingTimeInSat = 0;
        this.trainingTimeInSun = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        this.lastStudyDate = df.format(date);
    }



    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTrainingTimeInMon() {
        return trainingTimeInMon;
    }

    public void setTrainingTimeInMon(int trainingTimeInMon) {
        this.trainingTimeInMon = trainingTimeInMon;
    }

    public int getTrainingTimeInTue() {
        return trainingTimeInTue;
    }

    public void setTrainingTimeInTue(int trainingTimeInTue) {
        this.trainingTimeInTue = trainingTimeInTue;
    }

    public int getTrainingTimeInWed() {
        return trainingTimeInWed;
    }

    public void setTrainingTimeInWed(int trainingTimeInWed) {
        this.trainingTimeInWed = trainingTimeInWed;
    }

    public int getTrainingTimeInThu() {
        return trainingTimeInThu;
    }

    public void setTrainingTimeInThu(int trainingTimeInThu) {
        this.trainingTimeInThu = trainingTimeInThu;
    }

    public int getTrainingTimeInFri() {
        return trainingTimeInFri;
    }

    public void setTrainingTimeInFri(int trainingTimeInFri) {
        this.trainingTimeInFri = trainingTimeInFri;
    }

    public int getTrainingTimeInSat() {
        return trainingTimeInSat;
    }

    public void setTrainingTimeInSat(int trainingTimeInSat) {
        this.trainingTimeInSat = trainingTimeInSat;
    }

    public int getTrainingTimeInSun() {
        return trainingTimeInSun;
    }

    public void setTrainingTimeInSun(int trainingTimeInSun) {
        this.trainingTimeInSun = trainingTimeInSun;
    }

    public String getLastStudyDate() {
        return lastStudyDate;
    }

    public void setLastStudyDate(String lastStudyDate) {
        this.lastStudyDate = lastStudyDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", vip=" + vip +
                '}';
    }
}
