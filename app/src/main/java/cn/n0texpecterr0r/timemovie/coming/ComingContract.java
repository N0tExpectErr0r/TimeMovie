package cn.n0texpecterr0r.timemovie.coming;

import android.content.Context;

import java.util.List;

import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.coming.bean.ComingMovie;
import cn.n0texpecterr0r.timemovie.coming.repo.RemoteComingRepo;

/**
 * 即将上映的Mvp契约类
 *
 * @author N0tExpectErr0r
 * @time 2019/01/10
 */
public interface ComingContract {

    interface View extends BaseContract.View{
        void onLoadComing(List<ComingMovie> movieList);

        void onLoadError();
    }

    abstract class Presenter extends BaseContract.BaseRepoPresenter<ComingContract.View, RemoteComingRepo> {

        public Presenter(Context context, ComingContract.View view, RemoteComingRepo remote) {
            super(context, view, remote);
        }

        public abstract void fetchComingMovies(int locationId);
    }
}
