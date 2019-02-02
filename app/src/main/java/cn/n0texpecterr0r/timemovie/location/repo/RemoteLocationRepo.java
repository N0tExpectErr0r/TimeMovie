package cn.n0texpecterr0r.timemovie.location.repo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.base.util.Gsoner;
import cn.n0texpecterr0r.timemovie.base.util.JsonUtil;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 在线数据仓库
 *
 * @author N0tExpectErr0r
 * @time 2019/01/09
 */
public class RemoteLocationRepo implements BaseContract.Repository {
    public static final String LOCATION_LIST_URL = "https://api-m.mtime.cn/Showtime/HotCitiesByCinema.api";

    public Observable<List<Location>> fetchLocations(){
        return Observable.create((ObservableOnSubscribe<Response>) emitter -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url(LOCATION_LIST_URL)
                    .build();
            Call call = client.newCall(request);
            emitter.onNext(call.execute());
        }).map(response -> {
            String json = response.body().string();
            String realJson = JsonUtil.getNodeString(json, "p");
            List<Location> locations = new Gson().fromJson(realJson, new TypeToken<List<Location>>(){}.getType());
            saveLocationToLocal(locations);
            return locations;
        });
    }

    private void saveLocationToLocal(List<Location> locations) {
        for (Location location : locations) {
            TimeApplication.getDaoSession().insertOrReplace(location);
        }
    }
}
