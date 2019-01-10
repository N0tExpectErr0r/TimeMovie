package cn.n0texpecterr0r.timemovie.location.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Location {

    /**
     * count : 158
     * id : 974
     * name : 杭州
     * pinyinFull : Hangzhou
     * pinyinShort : hz
     */

    private int count;
    @Id
    private Long id;
    @SerializedName("n")
    private String name;
    private String pinyinFull;
    private String pinyinShort;

    @Generated(hash = 1631181786)
    public Location(int count, Long id, String name, String pinyinFull,
            String pinyinShort) {
        this.count = count;
        this.id = id;
        this.name = name;
        this.pinyinFull = pinyinFull;
        this.pinyinShort = pinyinShort;
    }

    @Generated(hash = 375979639)
    public Location() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyinFull() {
        return pinyinFull;
    }

    public void setPinyinFull(String pinyinFull) {
        this.pinyinFull = pinyinFull;
    }

    public String getPinyinShort() {
        return pinyinShort;
    }

    public void setPinyinShort(String pinyinShort) {
        this.pinyinShort = pinyinShort;
    }
}
