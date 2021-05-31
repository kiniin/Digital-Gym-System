package sample.pojo;


/**
 *
 * Abstract of the coach of London Gym, record all the information about admin.
 * Record the useful attributes of an coach, and give the getters and setters
 * of those attributes.
 * <p>The attributes of the class are as follows:</p>
 * <ul>
 * <li>userId</li>
 * <li>videoId</li>
 * <li>videoSort</li>
 * </ul>
 *
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @author Tenghao Su
 * @iteration 2.0
 */
public class VideoRecord {
    private String userId;
    private String videoId;
    private String videoSort;

    /**
     * Get the name of the user.
     * @return Name of the user.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * Set the name of the user.
     * @param userId Name of the user.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * Get the id of the video.
     * @return id of the user.
     */
    public String getVideoId() {
        return videoId;
    }
    /**
     * Set the id of the video.
     * @param videoId id of the video.
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
    /**
     * Get the sort of the video.
     * @return sort of the user.
     */
    public String getVideoSort() {
        return videoSort;
    }
    /**
     * Set the sort of the video.
     * @param videoSort sort of the user.
     */
    public void setVideoSort(String videoSort) {
        this.videoSort = videoSort;
    }
}
