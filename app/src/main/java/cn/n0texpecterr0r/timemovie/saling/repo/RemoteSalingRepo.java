package cn.n0texpecterr0r.timemovie.saling.repo;

import android.graphics.Movie;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.n0texpecterr0r.timemovie.AppConstants;
import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.base.util.JsonUtil;
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
 * 网络正在热映Repo
 *
 * @author N0tExpectErr0r
 * @time 2019/01/10
 */
public class RemoteSalingRepo implements BaseContract.Repository {
    public static final String SALING_MOVIE_URL = "https://api-m.mtime.cn/Showtime/LocationMovies.api";

    public Observable<List<SalingMovie>> fetchSalingMovies(int locationId){
        String url = SALING_MOVIE_URL +"?locationId="+locationId;
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
        }).map(new Function<Response, List<SalingMovie>>() {
            @Override
            public List<SalingMovie> apply(Response response) throws Exception {
                String json = response.body().string();
                String realJson = JsonUtil.getNodeString(json, "ms");
                List<SalingMovie> movies = new Gson().fromJson(realJson, new TypeToken<List<SalingMovie>>(){}.getType());
                saveMoviesToLocal(movies, locationId);
                return movies;
            }
        });
    }

    private void saveMoviesToLocal(List<SalingMovie> movies, int locationId) {
        for (SalingMovie movie : movies) {
            movie.setLocationId(locationId);
            TimeApplication.getDaoSession().insert(movie);
        }
    }
}
