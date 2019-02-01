package cn.n0texpecterr0r.timemovie.detail.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * 描述
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */
public class StageImg {
    /**
     * imgId : 7176277
     * imgUrl : http://img31.mtime.cn/pi/2016/02/23/094309.19731831_1280X720X2.jpg
     */
    private Long imgId;
    private String imgUrl;

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}