package cn.n0texpecterr0r.timemovie.location.presenter;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.n0texpecterr0r.timemovie.location.LocationContract;
import cn.n0texpecterr0r.timemovie.location.bean.Location;
import cn.n0texpecterr0r.timemovie.location.repo.LocalLocationRepo;
import cn.n0texpecterr0r.timemovie.location.repo.RemoteLocationRepo;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Location的Mvp Presenter
 *
 * @author N0tExpectErr0r
 * @time 2019/01/09
 */
public class LocationPresenter extends LocationContract.Presenter {

    public LocationPresenter(Context context, LocationContract.View view, RemoteLocationRepo remote, LocalLocationRepo local) {
        super(context, view, remote,local);
    }

    @Override
    public void fetchLocations() {
        Observable<List<Location>> remote = getRemoteRepository().fetchLocations();
        Observable<List<Location>> local = getLocalRepository().fetchLocations();
        Observable.concat(local, remote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(locations ->
                    getView().onLoadLocation(locations)
                ,throwable -> {
                    getView().onLoadError();
                    throwable.printStackTrace();
                });

    }

    @Override
    public void searchLocations(String keyword) {
        getLocalRepository().searchLocation(keyword)
                .debounce(200, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(locations ->
                    getView().onLoadLocation(locations)
                ,throwable -> {
                    getView().onLoadError();
                    throwable.printStackTrace();
                });
    }
}
