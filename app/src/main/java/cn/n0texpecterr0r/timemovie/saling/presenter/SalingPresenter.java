package cn.n0texpecterr0r.timemovie.saling.presenter;

import android.content.Context;

import java.util.List;

import cn.n0texpecterr0r.timemovie.location.bean.Location;
import cn.n0texpecterr0r.timemovie.saling.SalingContract;
import cn.n0texpecterr0r.timemovie.saling.bean.SalingMovie;
import cn.n0texpecterr0r.timemovie.saling.repo.LocalSalingRepo;
import cn.n0texpecterr0r.timemovie.saling.repo.RemoteSalingRepo;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 正在售票Presenter
 *
 * @author N0tExpectErr0r
 * @time 2019/01/10
 */
public class SalingPresenter extends SalingContract.Presenter {
    public SalingPresenter(Context context, SalingContract.View view,
                           RemoteSalingRepo remote, LocalSalingRepo local) {
        super(context, view, remote, local);
    }

    @Override
    public void fetchSalingMovies(int locationId) {
        Observable<List<SalingMovie>> remote = getRemoteRepository().fetchSalingMovies(locationId);
        Observable<List<SalingMovie>> local = getLocalRepository().fetchSalingMovies(locationId);
        Observable.concat(local, remote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(salingMovies ->
                    getView().onLoadSaling(salingMovies)
                ,throwable -> {
                    getView().onLoadError();
                    throwable.printStackTrace();
                });
    }
}
