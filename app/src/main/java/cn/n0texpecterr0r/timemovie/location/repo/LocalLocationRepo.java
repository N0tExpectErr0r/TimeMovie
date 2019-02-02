package cn.n0texpecterr0r.timemovie.location.repo;

import android.util.Log;

import java.util.List;

import cn.n0texpecterr0r.timemovie.TimeApplication;
import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.db.LocationDao;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * 本地数据仓库
 *
 * @author N0tExpectErr0r
 * @time 2019/01/10
 */
public class LocalLocationRepo implements BaseContract.Repository {
    public Observable<List<Location>> fetchLocations(){
        return Observable.create(new ObservableOnSubscribe<List<Location>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Location>> emitter) throws Exception {
                List<Location> locations = TimeApplication.getDaoSession().loadAll(Location.class);
                if (!locations.isEmpty()) {
                    // 不为空，显示数据
                    emitter.onNext(locations);
                }else {
                    // 数据为空，从网络加载
                    emitter.onComplete();
                }
            }
        });
    }

    public Observable<List<Location>> searchLocation(String keyword){
        return Observable.create(new ObservableOnSubscribe<List<Location>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Location>> emitter) throws Exception {
                List<Location> locations = TimeApplication.getDaoSession()
                        .queryBuilder(Location.class)
                        .where(LocationDao.Properties.Name.like("%"+keyword+"%"))
                        .list();
                emitter.onNext(locations);
            }
        });
    }
}
