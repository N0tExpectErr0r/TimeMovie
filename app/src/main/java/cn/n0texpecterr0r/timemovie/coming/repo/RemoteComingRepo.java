package cn.n0texpecterr0r.timemovie.coming.repo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.base.util.JsonUtil;
import cn.n0texpecterr0r.timemovie.coming.bean.ComingMovie;
import cn.n0texpecterr0r.timemovie.db.ComingMovieDao;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import cn.n0texpecterr0r.timemovie.saling.bean.SalingMovie;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 即将上映的网络Repo
 *
 * @author N0tExpectErr0r
 * @time 2019/01/11
 */
public class RemoteComingRepo implements BaseContract.Repository {
    public static final String COMING_MOVIE_URL = "https://api-m.mtime.cn/Movie/MovieComingNew.api";

    public Observable<List<ComingMovie>> fetchComingMovies(int locationId) {

        String url = COMING_MOVIE_URL+"?locationId="+locationId;
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
        }).map(new Function<Response, List<ComingMovie>>() {
            @Override
            public List<ComingMovie> apply(Response response) throws Exception {
                String json = response.body().string();
                String realJson = JsonUtil.getNodeString(json,"attention");
                List<ComingMovie> movies = new Gson().fromJson(realJson, new TypeToken<List<ComingMovie>>(){}.getType());
                saveMoviesToLocal(movies, locationId);
                return movies;
            }
        });
    }

    private void saveMoviesToLocal(List<ComingMovie> movies, int locationId) {
        // 清空上一次的记录
        TimeApplication.getDaoSession()
                .queryBuilder(ComingMovie.class)
                .where(ComingMovieDao.Properties.LocationId.eq(locationId))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
        for (ComingMovie movie : movies) {
            movie.setLocationId(locationId);
            TimeApplication.getDaoSession().insert(movie);
        }
    }
}
