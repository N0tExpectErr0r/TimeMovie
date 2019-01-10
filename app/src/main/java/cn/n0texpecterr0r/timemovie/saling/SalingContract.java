package cn.n0texpecterr0r.timemovie.saling;

import android.content.Context;

import java.util.List;

import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.saling.bean.SalingMovie;
import cn.n0texpecterr0r.timemovie.saling.repo.RemoteSalingRepo;

/**
 * 售票中的Mvp契约类
 *
 * @author N0tExpectErr0r
 * @time 2019/01/10
 */
public interface SalingContract {

    interface View extends BaseContract.View{
        void onLoadSaling(List<SalingMovie> movieList);

        void onLoadError();
    }

    abstract class Presenter extends BaseContract.BaseRepoPresenter<SalingContract.View, RemoteSalingRepo> {

        public Presenter(Context context, SalingContract.View view, RemoteSalingRepo remote) {
            super(context, view, remote);
        }

        public abstract void fetchSalingMovies(int locationId);
    }
}
