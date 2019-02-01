package cn.n0texpecterr0r.timemovie.coming.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

/**
 * 即将上映Movie
 *
 * @author N0tExpectErr0r
 * @time 2019/01/11
 */
@Entity
public class ComingMovie {

    /**
     * actor1 : 海拉·希尔莫
     * actor2 : 罗伯特·席安
     * director : 克里斯蒂安·瑞沃斯
     * id : 237246
     * image : http://img5.mtime.cn/mt/2018/12/29/205105.92669475_1280X720X2.jpg
     * isFilter : false
     * isTicket : false
     * isVideo : true
     * locationName : 美国
     * rDay : 18
     * rMonth : 1
     * rYear : 2019
     * releaseDate : 1月18日上映
     * title : 掠食城市
     * type : 动作 / 冒险 / 奇幻
     * videoCount : 3
     * videos : [{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/12/19/081113.68130060.jpg","length":84,"title":"掠食城市：致命引擎 中文预告片","url":"http://vfx.mtime.cn/Video/2017/12/19/mp4/171219080949591626.mp4","videoId":68912},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2018/06/06/094742.90338154.jpg","length":158,"title":"掠食城市：致命引擎 正式预告","url":"http://vfx.mtime.cn/Video/2018/06/06/mp4/180606094810732384.mp4","videoId":70794},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2018/10/06/091129.62020381.jpg","length":155,"title":"掠食城市：致命引擎 中字终极预告","url":"http://vfx.mtime.cn/Video/2018/10/06/mp4/181006091533361281.mp4","videoId":72171}]
     * wantedCount : 1093
     */

    private String actor1;
    private String actor2;
    private String director;
    private Long id;
    @SerializedName("image")
    private String img;
    private boolean isVideo;
    private String locationName;
    private String releaseDate;
    @SerializedName("title")
    private String name;
    private String type;
    private int videoCount;
    private int wantedCount;
    private int locationId;

    @Keep
    public ComingMovie(String actor1, String actor2, String director, Long id, String img, boolean isVideo, String locationName, String releaseDate, String name, String type, int videoCount, int wantedCount, int locationId) {
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.director = director;
        this.id = id;
        this.img = img;
        this.isVideo = isVideo;
        this.locationName = locationName;
        this.releaseDate = releaseDate;
        this.name = name;
        this.type = type;
        this.videoCount = videoCount;
        this.wantedCount = wantedCount;
        this.locationId = locationId;
    }

    @Generated(hash = 297813870)
    public ComingMovie() {
    }

    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean isVideo) {
        this.isVideo = isVideo;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public int getWantedCount() {
        return wantedCount;
    }

    public void setWantedCount(int wantedCount) {
        this.wantedCount = wantedCount;
    }

    public boolean getIsVideo() {
        return this.isVideo;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void setIsVideo(boolean isVideo) {
        this.isVideo = isVideo;
    }
}
