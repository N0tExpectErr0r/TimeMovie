package cn.n0texpecterr0r.timemovie.detail.repo;


import java.sql.Time;

import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.base.util.Gsoner;
import cn.n0texpecterr0r.timemovie.base.util.JsonUtil;
import cn.n0texpecterr0r.timemovie.detail.bean.MovieDetail;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 影片详情的网络 Repo
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */
public class RemoteDetailRepo implements BaseContract.Repository {
    public static final String MOVIE_DETAIL_URL = "https://ticket-api-m.mtime.cn/movie/detail.api";

    public Observable<MovieDetail> fetchMovieDetail(long locationId, long movieId) {
        String url = MOVIE_DETAIL_URL + "?locationId="+locationId+"&movieId="+movieId;
        return Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .get()
                        .url(url)
                        .build();
                Call call = client.newCall(request);
                emitter.onNext(call.execute());
            }
        }).map(new Function<Response, MovieDetail>() {
            @Override
            public MovieDetail apply(Response response) throws Exception {
                String json = response.body().string();
                String realJson = JsonUtil.getNodeString(json, "data");
                MovieDetail detail = Gsoner.fromJson(realJson, MovieDetail.class);
                return detail;
            }
        });
    }
}
