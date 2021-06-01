package sample.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Abstract of the user of London Gym, record all the information about admin.
 * Record the useful attributes of an user, and give the getters and setters
 * of those attributes.
 * <p>The attributes of the class are as follows:</p>
 * <ul>
 * <li>username</li>
 * <li>password</li>
 * <li>email</li>
 * <li>phone</li>
 * <li>height</li>
 * <li>weight</li>
 * <li>birthday</li>
 * <li>gender</li>
 * <li>vip</li>
 * </ul>
 *
 * @author Tenghao Su
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @version 1.0
 */
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

    /**
     * Constructor of the User.class
     * Set "Normal" as the default value of a user.
     */
    public User(){
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
    /**
     * Get the VIP status of the user.
     * @return VIP status of the user.
     */
    public String getVip() {
        return vip;
    }

    /**
     * Set the new VIP status of an admin.
     * @param vip The VIP status of the admin.
     */
    public void setVip(String vip) {
        this.vip = vip;
    }

    /**
     * Get the email address of the user.
     * @return Email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the new email of an admin.
     * @param email The email of the admin.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the email address of the user.
     * @return Email address of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the new account of an admin.
     * @param username The account of the admin.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the height of the user.
     * @return Height of the user.
     */
    public String getHeight() {
        return height;
    }

    /**
     * Set the new height number of an admin.
     * @param height The height of the admin.
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * Get the weight of the user.
     * @return Weight of the user.
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Set the new weight number of an admin.
     * @param weight The weight of the admin.
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * Get the birthday of the user.
     * @return Birthday of the user.
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set the new birthday of an admin.
     * @param birthday The birthday of the admin.
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Get the gender of the user.
     * @return Gender of the user.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the new gender of an admin.
     * @param gender The gender of the admin.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the password of the user.
     * @return Password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the new password of an admin.
     * @param password The password of the admin.
     */
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
