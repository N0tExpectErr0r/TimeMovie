package cn.n0texpecterr0r.timemovie.detail.repo;

import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.detail.bean.MovieDetail;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * 影片详情的 Repo
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */
public class LocalDetailRepo implements BaseContract.Repository {

    public Observable<MovieDetail> fetchMovieDetail(long movieId) {
        return null;
//        return Observable.create(new ObservableOnSubscribe<MovieDetail>() {
//            @Override
//            public void subscribe(ObservableEmitter<MovieDetail> emitter) throws Exception {
//                MovieDetail detail = TimeApplication.getDaoSession()
//                        .queryBuilder(MovieDetail.class)
//                        .where()
//            }
//        })
    }
}
