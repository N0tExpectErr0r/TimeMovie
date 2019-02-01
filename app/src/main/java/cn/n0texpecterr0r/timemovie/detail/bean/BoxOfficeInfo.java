package cn.n0texpecterr0r.timemovie.detail.bean;


/**
 * 描述
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */
public class BoxOfficeInfo {
    /**
     * movieId : 125805
     * ranking : 0
     * todayBox : 0
     * todayBoxDes :
     * todayBoxDesUnit :
     * totalBox : 112780409590
     * totalBoxDes : 11.28亿元
     * totalBoxUnit : 累计票房(亿)
     */
    private Long movieId;
    private String todayBoxDes;
    private String todayBoxDesUnit;
    private String totalBoxDes;
    private String totalBoxUnit;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTodayBoxDes() {
        return todayBoxDes;
    }

    public void setTodayBoxDes(String todayBoxDes) {
        this.todayBoxDes = todayBoxDes;
    }

    public String getTodayBoxDesUnit() {
        return todayBoxDesUnit;
    }

    public void setTodayBoxDesUnit(String todayBoxDesUnit) {
        this.todayBoxDesUnit = todayBoxDesUnit;
    }

    public String getTotalBoxDes() {
        return totalBoxDes;
    }

    public void setTotalBoxDes(String totalBoxDes) {
        this.totalBoxDes = totalBoxDes;
    }

    public String getTotalBoxUnit() {
        return totalBoxUnit;
    }

    public void setTotalBoxUnit(String totalBoxUnit) {
        this.totalBoxUnit = totalBoxUnit;
    }
}