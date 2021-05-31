package sample.pojo;

/**
 * Abstract of the admin of London Gym, record all the information about admin.
 * Record the useful attributes of an admin, and give the getters and setters
 * of those attributes.
 * <p>The attributes of the class are as follows:</p>
 * <ul>
 * <li>name</li>
 * <li>account</li>
 * <li>password</li>
 * </ul>
 *
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @author Tenghao Su
 * @iteration 3.0
 */
public class Admin {

    private String name;
    private String account;
    private String password;

    /**
     * Get the name of the admin.
     * @return Name of the admin.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the new name of an admin.
     * @param name The new name of the admin.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the Account of the admin.
     * @return Account of the admin.
     */
    public String getAccount() {
        return account;
    }

    /**
     * Set the new account of an admin.
     * @param account The new account of the admin.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Get the password of the admin.
     * @return Password of the admin.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the new password of an admin.
     * @param password The new password of the admin.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Rewrite the toString method of Admin.class,
     * let it output all the information when we call its toString() method.
     * @return
     */
    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
