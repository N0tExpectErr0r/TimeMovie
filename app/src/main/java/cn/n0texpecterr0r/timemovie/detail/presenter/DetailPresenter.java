package cn.n0texpecterr0r.timemovie.detail.presenter;

import android.annotation.SuppressLint;
import android.content.Context;

import cn.n0texpecterr0r.timemovie.detail.DetailContract;
import cn.n0texpecterr0r.timemovie.detail.repo.LocalDetailRepo;
import cn.n0texpecterr0r.timemovie.detail.repo.RemoteDetailRepo;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */
public class DetailPresenter extends DetailContract.Presenter {
    public DetailPresenter(Context context, DetailContract.View view, RemoteDetailRepo remote, LocalDetailRepo local) {
        super(context, view, remote, local);
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchMovieDetail(long locationId, long movieId) {
        getRemoteRepository().fetchMovieDetail(locationId, movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(detail->
                    getView().onLoadDetail(detail)
                , throwable -> {
                    getView().onLoadError();
                    throwable.printStackTrace();
                });
    }
}
