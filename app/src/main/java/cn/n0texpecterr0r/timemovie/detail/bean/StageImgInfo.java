package cn.n0texpecterr0r.timemovie.detail.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * 描述
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */
public class StageImgInfo {
    /**
     * count : 198
     * list : [{"imgId":7176277,"imgUrl":"http://img31.mtime.cn/pi/2016/02/23/094309.19731831_1280X720X2.jpg"},{"imgId":7326885,"imgUrl":"http://img5.mtime.cn/pi/2017/01/25/165612.20210487_1280X720X2.jpg"},{"imgId":7326886,"imgUrl":"http://img5.mtime.cn/pi/2017/01/25/165620.12577306_1280X720X2.jpg"},{"imgId":7326887,"imgUrl":"http://img5.mtime.cn/pi/2017/01/25/165625.43047270_1280X720X2.jpg"}]
     */

    private int count;

    private List<StageImg> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<StageImg> getList() {
        return list;
    }

    public void setList(List<StageImg> list) {
        this.list = list;
    }


}