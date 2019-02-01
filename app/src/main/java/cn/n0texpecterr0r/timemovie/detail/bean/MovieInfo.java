package cn.n0texpecterr0r.timemovie.detail.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

import cn.n0texpecterr0r.timemovie.detail.converter.StringConverter;

/**
 * 描述
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */

//@Entity
public class MovieInfo {
    /**
     * actors : [{"actorId":913378,"img":"http://img31.mtime.cn/ph/2014/09/01/170748.64755972_1280X720X2.jpg","name":"范·迪塞尔","nameEn":"Vin Diesel","roleImg":"http://img5.mtime.cn/mg/2017/01/05/162613.85098094.jpg","roleName":"桑德·凯奇"},{"actorId":893008,"img":"http://img31.mtime.cn/ph/2014/03/13/164116.22404345_1280X720X2.jpg","name":"甄子丹","nameEn":"Donnie Yen","roleImg":"http://img5.mtime.cn/mg/2017/01/05/162631.30237748.jpg","roleName":"项"},{"actorId":1403701,"img":"http://img31.mtime.cn/ph/2016/04/05/153734.26333599_1280X720X2.jpg","name":"迪皮卡·帕度柯妮","nameEn":"Deepika Padukone","roleImg":"http://img5.mtime.cn/mg/2017/01/05/163611.99195116.jpg","roleName":"赛琳娜"},{"actorId":1763914,"img":"http://img5.mtime.cn/ph/2017/02/08/172048.77104443_1280X720X2.jpg","name":"鲁比·罗丝","nameEn":"Ruby Rose","roleImg":"http://img5.mtime.cn/mg/2017/01/05/162801.28633700.jpg","roleName":"阿黛尔"},{"actorId":1981491,"img":"http://img5.mtime.cn/ph/2017/08/21/103819.54514950_1280X720X2.jpg","name":"吴亦凡","nameEn":"Kris Wu","roleImg":"http://img5.mtime.cn/mg/2017/01/05/162650.25424635.jpg","roleName":"DJ尼克斯"},{"actorId":1266158,"img":"http://img31.mtime.cn/ph/2016/05/04/162943.45619387_1280X720X2.jpg","name":"妮娜·杜波夫","nameEn":"Nina Dobrev","roleImg":"http://img5.mtime.cn/mg/2017/01/05/163459.85286563.jpg","roleName":"贝基"},{"actorId":913101,"img":"http://img31.mtime.cn/ph/2014/03/14/152328.88324192_1280X720X2.jpg","name":"塞缪尔·杰克逊","nameEn":"Samuel L. Jackson","roleImg":"http://img5.mtime.cn/mg/2017/02/09/143611.45346521.jpg","roleName":"吉布森"},{"actorId":938310,"img":"http://img31.mtime.cn/ph/2014/02/22/202102.28239250_1280X720X2.jpg","name":"托尼·贾","nameEn":"Tony Jaa","roleImg":"http://img5.mtime.cn/mg/2017/01/05/163434.84663336.jpg","roleName":"塔隆"},{"actorId":915029,"img":"http://img5.mtime.cn/ph/2017/01/12/165011.48068899_1280X720X2.jpg","name":"托妮·科莱特","nameEn":"Toni Collette","roleImg":"http://img5.mtime.cn/mg/2017/01/05/163402.87655748.jpg","roleName":"马珂"},{"actorId":926008,"img":"http://img31.mtime.cn/ph/2016/04/07/144735.68017937_1280X720X2.jpg","name":"罗伊·麦克凯恩","nameEn":"Rory McCann","roleImg":"http://img5.mtime.cn/mg/2017/01/05/163308.86236332.jpg","roleName":"泰尼逊"},{"actorId":2201494,"img":"http://img5.mtime.cn/ph/2016/11/02/092530.84997401_1280X720X2.jpg","name":"尼基·詹姆","nameEn":"Nicky Jam","roleImg":"http://img5.mtime.cn/mg/2017/01/05/163152.53635568.jpg","roleName":"拉撒路"},{"actorId":1416487,"img":"http://img31.mtime.cn/ph/2016/08/29/184141.26902742_1280X720X2.jpg","name":"迈克尔·比斯平","nameEn":"Michael Bisping","roleImg":"http://img5.mtime.cn/mg/2017/01/05/163040.43362207.jpg","roleName":"霍克"},{"actorId":2098727,"img":"http://img31.mtime.cn/ph/2015/07/01/114143.59711468_1280X720X2.jpg","name":"内马尔","nameEn":"Neymar","roleImg":"http://img5.mtime.cn/mg/2017/01/05/163524.98203922.jpg","roleName":"内马尔"},{"actorId":903983,"img":"http://img31.mtime.cn/ph/2014/02/22/194147.64436994_1280X720X2.jpg","name":"艾斯·库珀","nameEn":"Ice Cube","roleImg":"","roleName":"达里斯"},{"actorId":2084677,"img":"http://img31.mtime.cn/ph/2015/09/09/101940.19419320_1280X720X2.jpg","name":"赫敏·科菲尔德","nameEn":"Hermione Corfield","roleImg":"","roleName":"安斯利"},{"actorId":921201,"img":"http://img31.mtime.cn/ph/2014/04/15/111254.75720606_1280X720X2.jpg","name":"阿尔·萨皮恩扎","nameEn":"Al Sapienza","roleImg":"","roleName":"CIA Director"},{"actorId":1232892,"img":"http://img5.mtime.cn/ph/2017/01/05/145035.65755647_1280X720X2.jpg","name":"","nameEn":"Helena-Alexis Seymour","roleImg":"","roleName":"Ainsley's Girl"},{"actorId":2218074,"img":"http://img31.mtime.cn/ph/2016/08/29/184313.10828827_1280X720X2.jpg","name":"","nameEn":"Megan Soo","roleImg":"","roleName":"Ainsley's Girl"},{"actorId":2218075,"img":"http://img5.mtime.cn/ph/2017/01/05/144739.37346974_1280X720X2.jpg","name":"","nameEn":"Kristen Kurnik","roleImg":"","roleName":"Ainsley's Girl"},{"actorId":1023991,"img":"http://img5.mtime.cn/ph/2017/01/05/144625.10720932_1280X720X2.jpg","name":"","nameEn":"Gary 'Si-Jo' Foo","roleImg":"","roleName":"NSA Contractor       (uncredited)"}]
     * award : {"awardList":[],"totalNominateAward":0,"totalWinAward":0}
     * bigImage :
     * commentSpecial :
     * community : {}
     * director : {"directorId":903521,"img":"http://img5.mtime.cn/ph/2017/02/17/182200.43454182_1280X720X2.jpg","name":"D·J·卡卢索","nameEn":"D.J. Caruso"}
     * festivals : []
     * hotRanking : -1
     * img : http://img5.mtime.cn/mt/2017/01/05/105822.16893974_1280X720X2.jpg
     * is3D : true
     * isDMAX : true
     * isEggHunt : false
     * isFilter : false
     * isIMAX : false
     * isIMAX3D : true
     * isTicket : false
     * message : 该操作将清除您对该片的评分！是否确认？
     * mins : 107分钟
     * movieId : 125805
     * movieStatus : 1
     * name : 极限特工：终极回归
     * nameEn : xXx: The Return of Xander Cage
     * score : 6.6
     * personCount : 228
     * quizGame : {}
     * releaseArea : 中国
     * releaseDate : 20170210
     * sensitiveStatus : false
     * showCinemaCount : -1
     * showDay : -1
     * showtimeCount : -1
     * stageImg : {"count":198,"list":[{"imgId":7176277,"imgUrl":"http://img31.mtime.cn/pi/2016/02/23/094309.19731831_1280X720X2.jpg"},{"imgId":7326885,"imgUrl":"http://img5.mtime.cn/pi/2017/01/25/165612.20210487_1280X720X2.jpg"},{"imgId":7326886,"imgUrl":"http://img5.mtime.cn/pi/2017/01/25/165620.12577306_1280X720X2.jpg"},{"imgId":7326887,"imgUrl":"http://img5.mtime.cn/pi/2017/01/25/165625.43047270_1280X720X2.jpg"}]}
     * story : 范·迪塞尔扮演的桑德·凯奇在被认为已经死亡后又奇迹般归来，极度危险的反派研制出了能够毁灭世界的终极武器，在这一大危机下，凯奇重出江湖，并招募了一支各有所长、热爱刺激的团队。他们与甄子丹领衔的另一队人马争夺一个叫做“潘多拉魔盒”的武器，最终一起携手拯救世界。才华横溢的导演D·J·卡卢索动作场景拍得非常精彩，其中包括滑滑板下坡的镜头、摩托车冲浪的场景、一场徒步高速公路追逐戏、零重力飞机上的搏斗片段，还有可以终结一切枪战的boss级枪战。
     * style : {"isLeadPage":0,"leadImg":"https://img2.mtime.cn/mg/.jpg","leadUrl":""}
     * totalNominateAward : 0
     * totalWinAward : 0
     * type : ["动作","冒险","惊悚"]
     * url : https://movie.mtime.com/125805/
     * video : {"count":40,"hightUrl":"https://vfx.mtime.cn/Video/2017/01/05/mp4/170105105137886980.mp4","img":"http://img5.mtime.cn/mg/2017/01/05/105124.57142324_235X132X4.jpg","title":"极限特工：终极回归 中国版预告片","url":"https://vfx.mtime.cn/Video/2017/01/05/mp4/170105105137886980_480.mp4","videoId":64107,"videoSourceType":1}
     */

    private String img;
    private boolean is3D;
    private boolean isDMAX;
    private boolean isEggHunt;
    private boolean isFilter;
    private boolean isIMAX;
    private boolean isIMAX3D;
    private boolean isTicket;
    private String mins;
    private Long movieId;
    private String name;
    private String nameEn;
    @SerializedName("overallRating")
    private double score;
    private int personCount;
    private String releaseArea;
    private String releaseDate;
    private StageImgInfo stageImg;
    private String story;
    private String url;
    private Video video;
    private List<Actor> actors;
    private List<String> type;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isIsDMAX() {
        return isDMAX;
    }

    public void setIsDMAX(boolean isDMAX) {
        this.isDMAX = isDMAX;
    }

    public boolean isIsEggHunt() {
        return isEggHunt;
    }

    public void setIsEggHunt(boolean isEggHunt) {
        this.isEggHunt = isEggHunt;
    }

    public boolean isIsFilter() {
        return isFilter;
    }

    public void setIsFilter(boolean isFilter) {
        this.isFilter = isFilter;
    }

    public boolean isIsIMAX() {
        return isIMAX;
    }

    public void setIsIMAX(boolean isIMAX) {
        this.isIMAX = isIMAX;
    }

    public boolean isIsIMAX3D() {
        return isIMAX3D;
    }

    public void setIsIMAX3D(boolean isIMAX3D) {
        this.isIMAX3D = isIMAX3D;
    }

    public boolean isIsTicket() {
        return isTicket;
    }

    public void setIsTicket(boolean isTicket) {
        this.isTicket = isTicket;
    }

    public String getMins() {
        return mins;
    }

    public void setMins(String mins) {
        this.mins = mins;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public String getReleaseArea() {
        return releaseArea;
    }

    public void setReleaseArea(String releaseArea) {
        this.releaseArea = releaseArea;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public StageImgInfo getStageImg() {
        return stageImg;
    }

    public void setStageImg(StageImgInfo stageImg) {
        this.stageImg = stageImg;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

}