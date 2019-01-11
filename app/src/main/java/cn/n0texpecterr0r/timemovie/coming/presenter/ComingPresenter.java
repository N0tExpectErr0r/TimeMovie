package cn.n0texpecterr0r.timemovie.coming.presenter;

import android.content.Context;

import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.coming.ComingContract;
import cn.n0texpecterr0r.timemovie.coming.repo.RemoteComingRepo;

/**
 * 即将上映Presenter
 *
 * @author N0tExpectErr0r
 * @time 2019/01/11
 */
public class ComingPresenter extends ComingContract.Presenter {

    public ComingPresenter(Context context, ComingContract.View view, RemoteComingRepo remote) {
        super(context, view, remote);
    }

    @Override
    public void fetchComingMovies(int locationId) {

    }
}
