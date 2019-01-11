package cn.n0texpecterr0r.timemovie.saling.repo;

import android.util.Log;

import java.util.List;

import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.db.SalingMovieDao;
import cn.n0texpecterr0r.timemovie.saling.bean.SalingMovie;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * 本地正在热映Repo
 *
 * @author N0tExpectErr0r
 * @time 2019/01/11
 */
public class LocalSalingRepo implements BaseContract.Repository {
    public Observable<List<SalingMovie>> fetchSalingMovies(int locationId){
        return Observable.create(new ObservableOnSubscribe<List<SalingMovie>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SalingMovie>> emitter) throws Exception {
                List<SalingMovie> movieList = TimeApplication.getDaoSession()
                        .queryBuilder(SalingMovie.class)
                        .where(SalingMovieDao.Properties.LocationId.eq(locationId))
                        .list();
                emitter.onNext(movieList);
                emitter.onComplete();
            }
        });
    }
}
