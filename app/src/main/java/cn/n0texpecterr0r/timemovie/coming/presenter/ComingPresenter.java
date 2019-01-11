package cn.n0texpecterr0r.timemovie.coming.presenter;

import android.content.Context;

import java.util.List;
import cn.n0texpecterr0r.timemovie.coming.ComingContract;
import cn.n0texpecterr0r.timemovie.coming.bean.ComingMovie;
import cn.n0texpecterr0r.timemovie.coming.repo.LocalComingRepo;
import cn.n0texpecterr0r.timemovie.coming.repo.RemoteComingRepo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 即将上映Presenter
 *
 * @author N0tExpectErr0r
 * @time 2019/01/11
 */
public class ComingPresenter extends ComingContract.Presenter {

    public ComingPresenter(Context context, ComingContract.View view,
                           RemoteComingRepo remote, LocalComingRepo local) {
        super(context, view, remote, local);
    }

    @Override
    public void fetchComingMovies(int locationId) {
        Observable<List<ComingMovie>> remote = getRemoteRepository().fetchComingMovies(locationId);
        Observable<List<ComingMovie>> local = getLocalRepository().fetchComingMovies(locationId);
        Observable.concat(local, remote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(comingMovies ->
                                getView().onLoadComing(comingMovies)
                        ,throwable -> {
                            getView().onLoadError();
                            throwable.printStackTrace();
                        });
    }
}
