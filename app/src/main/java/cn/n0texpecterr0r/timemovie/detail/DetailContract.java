package cn.n0texpecterr0r.timemovie.detail;

import android.content.Context;

import cn.n0texpecterr0r.timemovie.base.component.mvp.BaseContract;
import cn.n0texpecterr0r.timemovie.detail.bean.MovieDetail;
import cn.n0texpecterr0r.timemovie.detail.repo.LocalDetailRepo;
import cn.n0texpecterr0r.timemovie.detail.repo.RemoteDetailRepo;

/**
 * 影片详情 Mvp 契约类
 *
 * @author N0tExpectErr0r
 * @time 2019/01/28
 */
public interface DetailContract {
    interface View extends BaseContract.View{
        void onLoadDetail(MovieDetail detail);

        void onLoadError();
    }

    abstract class Presenter extends BaseContract.RepoPresenter<View,RemoteDetailRepo,LocalDetailRepo> {

        public Presenter(Context context, View view, RemoteDetailRepo remote, LocalDetailRepo local) {
            super(context, view, remote, local);
        }

        public abstract void fetchMovieDetail(long locationId, long movieId);
    }
}
