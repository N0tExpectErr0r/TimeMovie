package cn.n0texpecterr0r.timemovie.saling;

import android.content.Context;

import java.util.List;

import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.saling.bean.SalingMovie;
import cn.n0texpecterr0r.timemovie.saling.repo.LocalSalingRepo;
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

    abstract class Presenter extends BaseContract.RepoPresenter<SalingContract.View, RemoteSalingRepo, LocalSalingRepo> {

        public Presenter(Context context, SalingContract.View view, RemoteSalingRepo remote, LocalSalingRepo local) {
            super(context, view, remote, local);
        }

        public abstract void fetchSalingMovies(int locationId);
    }
}
