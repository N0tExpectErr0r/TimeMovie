package cn.n0texpecterr0r.timemovie.detail.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * 描述
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */
public class Actor {
    /**
     * actorId : 913378
     * img : http://img31.mtime.cn/ph/2014/09/01/170748.64755972_1280X720X2.jpg
     * name : 范·迪塞尔
     * nameEn : Vin Diesel
     * roleImg : http://img5.mtime.cn/mg/2017/01/05/162613.85098094.jpg
     * roleName : 桑德·凯奇
     */
    private Long actorId;
    private String img;
    private String name;
    private String nameEn;
    private String roleImg;
    private String roleName;

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getRoleImg() {
        return roleImg;
    }

    public void setRoleImg(String roleImg) {
        this.roleImg = roleImg;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
