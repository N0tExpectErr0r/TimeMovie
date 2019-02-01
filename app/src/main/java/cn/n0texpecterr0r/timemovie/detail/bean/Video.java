package cn.n0texpecterr0r.timemovie.detail.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * 描述
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */


public class Video {
    /**
     * count : 40
     * hightUrl : https://vfx.mtime.cn/Video/2017/01/05/mp4/170105105137886980.mp4
     * img : http://img5.mtime.cn/mg/2017/01/05/105124.57142324_235X132X4.jpg
     * title : 极限特工：终极回归 中国版预告片
     * url : https://vfx.mtime.cn/Video/2017/01/05/mp4/170105105137886980_480.mp4
     * videoId : 64107
     * videoSourceType : 1
     */

    private int count;
    private String hightUrl;
    private String img;
    private String title;
    private String url;

    private Long videoId;
    private int videoSourceType;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getHightUrl() {
        return hightUrl;
    }

    public void setHightUrl(String hightUrl) {
        this.hightUrl = hightUrl;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public int getVideoSourceType() {
        return videoSourceType;
    }

    public void setVideoSourceType(int videoSourceType) {
        this.videoSourceType = videoSourceType;
    }
}
