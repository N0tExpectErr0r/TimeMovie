package cn.n0texpecterr0r.timemovie.coming.repo;

import java.util.List;

import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.coming.bean.ComingMovie;
import cn.n0texpecterr0r.timemovie.db.ComingMovieDao;
import cn.n0texpecterr0r.timemovie.db.SalingMovieDao;
import cn.n0texpecterr0r.timemovie.saling.bean.SalingMovie;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * 即将上映的本地Repo
 *
 * @author N0tExpectErr0r
 * @time 2019/01/11
 */
public class LocalComingRepo implements BaseContract.Repository {

    public Observable<List<ComingMovie>> fetchComingMovies(int locationId) {
        return Observable.create(new ObservableOnSubscribe<List<ComingMovie>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ComingMovie>> emitter) throws Exception {
                List<ComingMovie> movieList = TimeApplication.getDaoSession()
                        .queryBuilder(ComingMovie.class)
                        .where(ComingMovieDao.Properties.LocationId.eq(locationId))
                        .list();
                emitter.onNext(movieList);
                emitter.onComplete();
            }
        });
    }
}
