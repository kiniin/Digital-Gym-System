package sample.pojo;

/**
 *
 * Abstract of the videos in London Gym's system, record all the information about admin.
 * Record the useful attributes of a video, and give the getters and setters
 * of those attributes.
 * <p>The attributes of the class are as follows:</p>
 * <ul>
 * <li>videoUrl</li>
 * <li>coverUrl</li>
 * <li>sort</li>
 * <li>title</li>
 * <li>instrument</li>
 * <li>frequency</li>
 * <li>introduction</li>
 * <li>star</li>
 * </ul>
 *
 * @author Ruizheng Wu
 * @author Xiaojian Qi
 * @author Tenghao Su
 * @version 4.0
 */
public class Video {
    private String videoUrl;
    private String coverUrl;
    private String title;
    private String sort;
    private String instrument;
    private String frequency;
    private String introduction;
    private String star;
    private String videoId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
